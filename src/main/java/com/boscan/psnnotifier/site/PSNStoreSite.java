package com.boscan.psnnotifier.site;

import com.boscan.psnnotifier.config.Configuration;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.LogManager;

public class PSNStoreSite {

    private static Logger logger = Logger.getLogger(PSNStoreSite.class);

    public PSNStoreSite() {
        try {
            LogManager.getLogManager().readConfiguration(
                    new FileInputStream(
                            new File(getClass().getClassLoader().getResource("logging.properties").getFile())));
        } catch (IOException e) {
            logger.error(e);
        }
    }

    public boolean processPSNStore() throws InterruptedException {
        logger.info("Checking PSN Store");
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

        if (isFlashSale) {
            logger.info("Flash sale in progress");
        } else {
            logger.info("No flash sale");
        }
        return isFlashSale;
    }

    private WebDriver prepareDriver() {
        File file = new File(Configuration.getInstance().getPhantomJsPath());
        System.setProperty("phantomjs.binary.path", file.getAbsolutePath());

        DesiredCapabilities dcap = new DesiredCapabilities();
        String[] phantomArgs = new  String[] {
                "--webdriver-loglevel=NONE"
        };
        dcap.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, phantomArgs);

        WebDriver webDriver;
        webDriver = new PhantomJSDriver(dcap);

        return webDriver;
    }
}
