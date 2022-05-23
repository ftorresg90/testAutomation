package org.example.Utils;

import com.google.gson.Gson;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LocalChromeBrowser {


    public static String downloadPath = System.getProperty("user.dir")+ File.separator+"target"+File.separator+"downloads";
    public static String headless = System.getProperty("headless", "false");
    public static String windowSize = System.getProperty("window-size", "1440,808");
    public static boolean isChromeDriverOnDefaultPath(){
        File driverFile = new File("/usr/bin/chromedriver");
        return driverFile.exists();
    }
    public static String getDownloadsPath(){
        return downloadPath;
    }
    public static void setDownloadPath(){
        System.setProperty("webdriverfactory.chrome.downloadpath", getDownloadsPath());
    }
    public static ChromeOptions prepareChromeOptions(HashMap<String, Object> chromePrefs){
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);
        options.setExperimentalOption("useAutomationExtension", false);
        options.addArguments("--disable-notifications");
        options.addArguments("disable-infobars");
        options.addArguments("--allow-running-insecure-content");
        options.addArguments("--disable-extensions");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-web-security");
        options.addArguments("--disable-gpu");
        if("true".equals(headless)) options.addArguments("headless");
        options.addArguments(String.format("--window-size=%s", windowSize));
        return options;
    }
    public static HashMap<String, Object> prepareChromePrefs(){
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("cmd", "Page.setDownloadBehavior");
        chromePrefs.put("behavior", "allow");
        chromePrefs.put("download.prompt_for_download", "false");
        chromePrefs.put("download.directory_upgrade", "true");
        chromePrefs.put("safebrowsing.enabled", "false");
        chromePrefs.put("safebrowsing.disable_download_protection", "true");
        chromePrefs.put("download.default_directory", getDownloadsPath());
        return chromePrefs;
    }
    public static ChromeDriverService getChromeDriverService(){
        return ChromeDriverService.createDefaultService();
    }
    public static void setdownloadPathOnChrome(String downloadPath, ChromeDriverService driverService, ChromeDriver driver){
        try {
            Map<String, Object> commandParams = new HashMap<>();
            commandParams.put("cmd", "Page.setDownloadBehavior");
            Map<String, String> params = new HashMap<>();
            params.put("behavior", "allow");
            params.put("downloadPath", downloadPath);
            commandParams.put("params", params);
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            Gson gson = new Gson();
            String command = gson.toJson(commandParams);
            HttpPost request = new HttpPost(prepareUrlToChrome(driverService.getUrl().toString(), driver.getSessionId().toString()));
            request.addHeader("content-type", "application/json");
            request.setEntity(new StringEntity(command));
            httpClient.execute(request);
        }catch (IOException e){
            e.printStackTrace();
        }

    }
    public static String prepareUrlToChrome(String url, String sessionId){
        String u = url + "/session/" + sessionId + "/chromium/send_command";
        System.setProperty("webdriverfactory.chrome.chromeServiceUrl"+Thread.currentThread(), u);
        return u;
    }

    /**
     * @author esalinasr
     * @return Chromedriver
     */
    public static ChromeDriver buildChromeBrowser() throws Throwable {
        if(!isChromeDriverOnDefaultPath()) WebDriverManager.chromedriver().setup();
        setDownloadPath();
        ChromeDriverService driverService = getChromeDriverService();
        ChromeDriver driver = new ChromeDriver(driverService,prepareChromeOptions(prepareChromePrefs()));
        setdownloadPathOnChrome(getDownloadsPath(), driverService, driver);
        return driver;
    }
}
