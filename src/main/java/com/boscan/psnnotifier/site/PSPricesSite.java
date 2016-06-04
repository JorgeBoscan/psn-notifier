package com.boscan.psnnotifier.site;

import com.boscan.psnnotifier.config.Configuration;
import com.boscan.psnnotifier.model.PSPrice;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class PSPricesSite {

    public PSPrice processPSPrices(String id) throws IOException {
        Document doc = Jsoup.connect(Configuration.getInstance().getPsPricesUrl() + id).userAgent("Mozilla").get();
        Elements elements = doc.select(".content__game_card__offers").get(0).select("span");

        PSPrice psPrice = new PSPrice();
        psPrice.setGameName(doc.select(".content__game__title").text());
        for (Element element : elements) {
            final String className = element.className();
            if (className.contains("content__game_card__price_drop")) {
                psPrice.setPreviousPrice(element.text());
            } else if (className.contains("content__game_card__price")) {
                Elements priceElements = element.select("span");
                for (Element priceElement : priceElements) {
                    final String priceClassName = priceElement.className();
                    if (priceClassName.equalsIgnoreCase("content__game_card__price_plus")) {
                        psPrice.setPsplusPrice(priceElement.text().trim());
                    } else if (priceClassName.trim().length() == 0) {
                        psPrice.setDiscountedPrice(priceElement.text().trim());
                    }
                }
            }
        }

        validatePreviousPriceValue(psPrice);

        return psPrice;
    }

    private void validatePreviousPriceValue(PSPrice psPrice) {
        if (psPrice.getPreviousPrice() == null
                && psPrice.getDiscountedPrice() != null
                && psPrice.getPsplusPrice() == null) {
            psPrice.setPreviousPrice(psPrice.getDiscountedPrice());
            psPrice.setDiscountedPrice(null);
        }
    }
}
