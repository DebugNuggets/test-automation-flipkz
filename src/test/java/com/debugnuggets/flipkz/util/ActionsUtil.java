package com.debugnuggets.flipkz.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.Properties;

public class ActionsUtil {
    private static ActionsUtil instance;
    private Actions actions;

    private ActionsUtil(){

    }

    public static ActionsUtil getInstance() {
        if (instance == null) {
            instance = new ActionsUtil();
        }
        return instance;
    }

    public Actions getActions(WebDriver webDriver) {
        if (actions == null) {
            synchronized (this) {
                if (actions == null) {
                    actions = new Actions(webDriver);
                }
            }
        }
        return actions;
    }
}
