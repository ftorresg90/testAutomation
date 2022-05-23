package org.example.StepDefinition;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import com.browserstack.local.Local;
import org.example.Utils.DriverFactory;
import org.example.Utils.LocalChromeBrowser;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.UUID;

public class Hooks {
    public String localIdentifier;
    public Local bsLocal;
    WebDriver driver;

    @Before
    public void prepareTest() throws Throwable {
        System.setProperty("browserstack.idleTimeout","300");
        buildDriver();
    }

    @After
    public void finalizarTest() throws Exception {
        shutdownDriver();
    }

    public void buildDriver() throws Throwable {
        String lab = System.getProperty("labExecution");
        DriverFactory.addDriver(LocalChromeBrowser.buildChromeBrowser());
    }

    public void shutdownDriver() {
        DriverFactory.getDriver().quit();
        DriverFactory.removeDriver();
    }

    public void shutdownBrowserStackLocal() throws Exception {
        if(bsLocal != null) {
            bsLocal.stop();
            bsLocal = null;
        }
    }

    public void prepareBrowserStackLocal() throws Exception {
        String browserstackLocal = System.getenv("BROWSERSTACK_LOCAL");
        if(browserstackLocal.equals("true")){
            if(!System.getProperty("os.name").toLowerCase().contains("win")){
                if(bsLocal != null){
                    bsLocal.stop();
                    bsLocal=null;
                }
                bsLocal = new Local();
                HashMap<String, String> bsLocalArgs = new HashMap<String, String>();
                bsLocalArgs.put("key", System.getenv("BROWSERSTACK_ACCESS_KEY"));
                bsLocalArgs.put("force", "true");
                bsLocalArgs.put("forcelocal", "true");
                localIdentifier = UUID.randomUUID().toString();
                System.setProperty("browserstack.localIdentifier", localIdentifier);
                bsLocalArgs.put("localIdentifier", localIdentifier);
                bsLocal.start(bsLocalArgs);
            }
        }
    }
}
