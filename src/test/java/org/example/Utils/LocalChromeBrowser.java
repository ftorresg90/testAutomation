package  org.example.Utils;
import com.google.gson.Gson;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LocalChromeBrowser {

    public static String downloadPath;
    public static String headless;
    public static String windowSize;

    public LocalChromeBrowser(){

    }

    public static boolean isChromeDriverOnDefaultPath(){
        File driverFile = new File("usr/bin/chromedriver");
        return driverFile.exists();
    }

    public static String getDownloadPath(){
        return downloadPath;
    }

    public static void setDownloadPath(){
        System.setProperty("webdriverfactory.chrome.downloadpath", getDownloadPath());
    }

    public static ChromeOptions prepareChromeOptions(HashMap<String, Object> chromePrefs){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("prefs", chromePrefs);
        chromeOptions.setExperimentalOption("useAutomationExtension", false);

        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--disable-infobars");
        chromeOptions.addArguments("--allow-running-insecure-content");
        chromeOptions.addArguments("--disable-extensions");
        chromeOptions.addArguments("--ignore-certificate-errors");
        chromeOptions.addArguments("--disable-web-security");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments(String.format("--window-size=%s", windowSize));

        if("true".equals(headless)){
            chromeOptions.addArguments("headless");
        }

        return chromeOptions;
    }

    public static HashMap<String,Object> prepareChromePrefs(){
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.default_content_settings_popup",0);
        chromePrefs.put("cmd","Page.setDownloadBehavior");
        chromePrefs.put("behavior","allow");
        chromePrefs.put("download.propmpt_for_download","false");
        chromePrefs.put("download.directory_upgrade","true");
        chromePrefs.put("safebrowsing.enabled","false");
        chromePrefs.put("safebrowsing.disable_download_protection","true");
        chromePrefs.put("download.default_directory",getDownloadPath());

        return chromePrefs;
    }

    public static ChromeDriverService getChromeDriverService(){
        return ChromeDriverService.createDefaultService();
    }

    public static void setDownloadPathOnChrome(String downloadPath, ChromeDriverService chromeDriverService, ChromeDriver chromeDriver){
        try {
            Map<String,Object> commandParams = new HashMap();
            commandParams.put("cmd", "Page.setDownloadBehavior");
            Map<String,Object> params = new HashMap();
            params.put("behavior", "allow");
            params.put("downloadPath", downloadPath);
            commandParams.put("params", params);

            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            Gson gson = new Gson();
            String command = gson.toJson(commandParams);
            HttpPost request = new HttpPost(prepareUrlToChrome(chromeDriverService.getUrl().toString(), chromeDriver.getSessionId().toString()));
            request.addHeader("content-type","application/json");
            request.setEntity(new StringEntity(command));
            httpClient.execute(request);
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    public static String prepareUrlToChrome(String url, String sessionId){
        String urlToReturn = url + "/session/" + sessionId + "/chromium/send_command";
        System.setProperty("webdriverfactory.chrome.chromeServiceUrl" + Thread.currentThread(), urlToReturn);
        return urlToReturn;
    }

    public static ChromeDriver buildChromeBrowser(){
        if(!isChromeDriverOnDefaultPath()){
            WebDriverManager.chromedriver().setup();
        }

        setDownloadPath();
        ChromeDriverService chromeDriverService =  getChromeDriverService();
        ChromeDriver chromeDriver = new ChromeDriver(chromeDriverService, prepareChromeOptions(prepareChromePrefs()));
        setDownloadPathOnChrome(getDownloadPath(),chromeDriverService,chromeDriver);

        return chromeDriver;
    }

    static {
        downloadPath = System.getProperty("user.dir") + File.separator + "target" + File.separator + "downloads";
        headless = System.getProperty("headless", "false");
        windowSize = System.getProperty("window-size", "1440,808");
    }
}