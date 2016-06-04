package com.boscan.psnnotifier;

import com.boscan.psnnotifier.config.Configuration;
import com.boscan.psnnotifier.model.Game;
import com.boscan.psnnotifier.model.Member;
import com.boscan.psnnotifier.model.PSPrice;
import com.boscan.psnnotifier.notification.Notification;
import com.boscan.psnnotifier.notification.PushBulletNotification;
import com.boscan.psnnotifier.site.PSNStoreSite;
import com.boscan.psnnotifier.site.PSPricesSite;
import com.boscan.psnnotifier.util.PSNUtil;
import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class PSNNotifier {

    private static Logger logger = Logger.getLogger(PSNNotifier.class);

    public static void main(String args[]) throws IOException {
        String jsonData = Files.toString(new File(Configuration.getInstance().getDataJsonPath()), Charsets.UTF_8);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        List<Member> members = gson.fromJson(jsonData, new TypeToken<List<Member>>(){}.getType());
        boolean isFlashSale = validateFlashSale();

        for (Member member : members) {
            saveAndNotifyFlashSale(isFlashSale, member);
            for (Game game : member.getGames()) {
                validateGameDiscount(member, game);
            }
        }

        Files.write(gson.toJson(members), new File(Configuration.getInstance().getDataJsonPath()), Charsets.UTF_8);
    }

    private static boolean validateFlashSale() {
        boolean isFlashSale = false;

        try {
            PSNStoreSite psnStoreSite = new PSNStoreSite();
            isFlashSale = psnStoreSite.processPSNStore();
        } catch (Exception e) {
            logger.error(e);
        }
        return isFlashSale;
    }

    private static void validateGameDiscount(Member member, Game game) {
        try {
            PSPricesSite psPricesSite = new PSPricesSite();
            PSPrice psPrice = psPricesSite.processPSPrices(game.getId());
            boolean isNew = (game.getLastUpdateDate() == null);
            boolean sendMessage = fillGameData(game, psPrice, isNew);

            if (sendMessage && !isNew) {
                notifyDiscount(member, psPrice);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
        }
    }

    private static void notifyDiscount(Member member, PSPrice psPrice) {
        String message = psPrice.getGameName() + ":\n";
        if (psPrice.getDiscountedPrice() == null) {
            message += "Actual Price: " + psPrice.getPreviousPrice();
        } else {
            message += "Previous Price: " + psPrice.getPreviousPrice()
                    + "\nDiscounted Price: " + psPrice.getDiscountedPrice();
        }
        if (psPrice.getPsplusPrice() != null && psPrice.getPsplusPrice().length() != 0) {
            message += "\nPS+ Price: " + psPrice.getPsplusPrice();
        }
        Notification notification = new PushBulletNotification();
        notification.sendNotification(member.getEmail(), message);
    }

    private static boolean fillGameData(Game game, PSPrice psPrice, boolean isNew) {
        logger.info("Validating: " + psPrice.getGameName());
        boolean sendMessage = false;
        if (!isNew && PSNUtil.isBetterPrice(game.getLowestPrice(), psPrice.getPsplusPrice())) {
            game.setLowestPrice(PSNUtil.parsePrice(psPrice.getPsplusPrice()));
            sendMessage = true;
        } else if (!isNew && PSNUtil.isBetterPrice(game.getLowestPrice(), psPrice.getDiscountedPrice())) {
            game.setLowestPrice(PSNUtil.parsePrice(psPrice.getDiscountedPrice()));
            sendMessage = true;
        } else if (!isNew && PSNUtil.isBetterPrice(game.getLowestPrice(), psPrice.getPreviousPrice())) {
            game.setLowestPrice(PSNUtil.parsePrice(psPrice.getPreviousPrice()));
            sendMessage = true;
        }

        if (sendMessage && game.getNotifyWhenLowerThan() > 0f
                && game.getNotifyWhenLowerThan() < game.getLowestPrice()) {
            sendMessage = false;
        }

        game.setName(psPrice.getGameName());
        if (PSNUtil.validateFree(psPrice.getDiscountedPrice())) {
            game.setDiscountedFree(true);
            game.setDiscountedPrice(0);
        } else {
            game.setDiscountedFree(false);
            game.setDiscountedPrice(PSNUtil.parsePrice(psPrice.getDiscountedPrice()));
        }
        if (PSNUtil.validateFree(psPrice.getPsplusPrice())) {
            game.setPsplusFree(true);
            game.setPsplusPrice(0);
        } else {
            game.setPsplusFree(false);
            game.setPsplusPrice(PSNUtil.parsePrice(psPrice.getPsplusPrice()));
        }
        if (PSNUtil.validateFree(psPrice.getPreviousPrice())) {
            game.setPreviousPrice(0);
        } else {
            game.setPreviousPrice(PSNUtil.parsePrice(psPrice.getPreviousPrice()));
        }
        game.setLastUpdateDate(new Date());
        return sendMessage;
    }

    private static void saveAndNotifyFlashSale(boolean isFlashSale, Member member) {
        if (!member.isAnnouncedFlashSale() && isFlashSale) {
            Notification notification = new PushBulletNotification();
            notification.sendNotification(member.getEmail(),
                    Configuration.getInstance().getDateFormat().format(new Date()) + " New Flash Sale");
        }
        member.setAnnouncedFlashSale(isFlashSale);
    }

}