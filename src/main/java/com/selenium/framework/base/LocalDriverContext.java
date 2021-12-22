package com.selenium.framework.base;

import org.openqa.selenium.remote.RemoteWebDriver;

/*
Class to support Selenium Grid feature
 */
public class LocalDriverContext {

    private static ThreadLocal<RemoteWebDriver> remoteWebDriverThreadLocal = new ThreadLocal<>();


    public static RemoteWebDriver getRemoteWebDriver() {
        return remoteWebDriverThreadLocal.get();
    }

    static void setRemoteWebDriverThreadLocal(RemoteWebDriver driverThreadLocal) {
        remoteWebDriverThreadLocal.set(driverThreadLocal);
    }

}
