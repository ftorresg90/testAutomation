package org.example.StepDefinition;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.example.Utils.DriverFactory;
import org.example.Utils.ScenarioContext;
import org.example.Page.webPage.MercadoLibrePage;

public class MercadoLibreSteps {
    private ScenarioContext context;
    private MercadoLibrePage mercadoLibrePage;

    public MercadoLibreSteps(ScenarioContext context) {
        this.context = context;
        this.mercadoLibrePage = new MercadoLibrePage(DriverFactory.getDriver());
    }

    @Then("el icono de MercadoLibre es visible")
    public void elIconoDeMercadoLibreEsVisible() {
        Assert.assertTrue("Icono de MercadoLibre no es visible", mercadoLibrePage.isVisibleIconoMercadoLibre());
    }
}