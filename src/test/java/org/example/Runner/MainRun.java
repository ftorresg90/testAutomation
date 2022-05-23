package org.example.Runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = {"org.example.StepDefinition"},
        features = "src/test/resources/cucumber",
        plugin = {"pretty"})
public class MainRun {
    @BeforeClass
    public static void configPrincipal(){
        //settings project capabilities for project
    }
}
