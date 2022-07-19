package org.example.StepDefinition;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.example.Utils.DriverFactory;

import org.example.Utils.LocalChromeBrowser;
import org.example.Utils.ScenarioContext;

public class Hooks {
    public Scenario scenario;
    public ScenarioContext scenarioContext;

    public Hooks(ScenarioContext scenarioContext){
        this.scenarioContext = scenarioContext;
    }

    @Before
    public void prepareTest(Scenario scenario) throws Throwable {
        this.scenario = scenario;
        buildDriver();
    }

    @After
    public void finalizarTest() {
        shutdownDriver();
    }

    public void buildDriver() throws Throwable {
        DriverFactory.addDriver(LocalChromeBrowser.buildChromeBrowser());
    }

    public void shutdownDriver() {
        DriverFactory.getDriver().quit();
        DriverFactory.removeDriver();
    }
}
