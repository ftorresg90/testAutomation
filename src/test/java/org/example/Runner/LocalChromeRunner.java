package org.example.Runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features="Features",glue={"StepDefinition"})
public class LocalChromeRunner extends MainRun {
    @BeforeClass
    public static void config (){
        System.setProperty("labExecution", "local");
        System.setProperty("closeDriver", "false");
    }
}