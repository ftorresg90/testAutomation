package org.example.StepDefinition;
import io.cucumber.java.en.And;
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

    @And("verifico la presencia del icono de MercadoLibre")
    public void verificoLaPresenciaDelIconoDeMercadoLibre() {
        Assert.assertTrue("Icono de MercadoLibre no visible", mercadoLibrePage.isVisibleIconoMercadoLibre());
    }
}