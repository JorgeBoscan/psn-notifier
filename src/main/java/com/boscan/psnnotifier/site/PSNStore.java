package com.boscan.psnnotifier.site;

import com.boscan.psnnotifier.config.Configuration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import java.io.File;
import java.util.List;

public class PSNStore {

    public static boolean processPSNStore() throws InterruptedException {
        final WebDriver webDriver = prepareDriver();
        webDriver.get(Configuration.getInstance().getPsnStoreUrl());
        Thread.sleep(25 * 1000);

        boolean isFlashSale = false;
        List<WebElement> elements = webDriver.findElement(By.cssSelector(".leftContent")).findElements(By.cssSelector(".panelQuickLinks")).get(0).findElements(By.cssSelector(".menuItem"));
        for (WebElement element : elements) {
            if (element.getText().toLowerCase().contains("flash")) {
                isFlashSale = true;
            }
        }
        webDriver.quit();

        return isFlashSale;
    }

    private static WebDriver prepareDriver() {
        File file = new File(Configuration.getInstance().getPhantomJsPath());
        System.setProperty("phantomjs.binary.path", file.getAbsolutePath());

        WebDriver webDriver;
        webDriver = new PhantomJSDriver();

        return webDriver;
    }
}
