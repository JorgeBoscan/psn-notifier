package com.boscan.psnnotifier.config;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Properties;

public class Configuration {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy|hh:mm");

    private String psnStoreUrl;
    private String psPricesUrl;
    private String dataJsonPath;
    private String phantomJsPath;
    private String pushbulletAppId;

    private static Configuration configuration;

    private Configuration() {
    }

    public static Configuration getInstance() {
        if (configuration == null) {
            if (prepareConfiguration()) return null;
        }

        return configuration;
    }

    private static boolean prepareConfiguration() {
        try {
            configuration = new Configuration();
            Properties prop = new Properties();
            InputStream input;

            input = new FileInputStream("./config.properties");
            prop.load(input);

            configuration.psnStoreUrl = prop.getProperty("psn.store.url");
            configuration.psPricesUrl = prop.getProperty("ps.prices.url");
            configuration.dataJsonPath = prop.getProperty("data.path");
            configuration.phantomJsPath = prop.getProperty("phantomjs.path");
            configuration.pushbulletAppId = prop.getProperty("pushbullet.app.id");
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
        return false;
    }

    public SimpleDateFormat getDateFormat() {
        return dateFormat;
    }

    public String getPsnStoreUrl() {
        return psnStoreUrl;
    }

    public String getPsPricesUrl() {
        return psPricesUrl;
    }

    public String getDataJsonPath() {
        return dataJsonPath;
    }

    public String getPhantomJsPath() {
        return phantomJsPath;
    }

    public String getPushbulletAppId() {
        return pushbulletAppId;
    }
}
