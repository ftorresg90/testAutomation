package org.example.StepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.Page.webPage.MercadoLibrePage;
import org.example.Utils.DriverFactory;
import org.example.Utils.ScenarioContext;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TC002Steps {

    private final MercadoLibrePage mercadoLibrePage;

    public TC002Steps(ScenarioContext context) {
        this.mercadoLibrePage = new MercadoLibrePage(DriverFactory.getDriver());
    }

    @Given("abro mercado libre y acepto cookies si aparecen")
    public void abroMercadoLibreYAceptoCookiesSiAparecen() {
        DriverFactory.getDriver().get("https://www.mercadolibre.cl/");
        // Optionally dismiss cookie/terms popup if it appears (non-mandatory)
        try {
            List<WebElement> btns = DriverFactory.getDriver()
                    .findElements(By.xpath("//button[contains(text(),'Entendido') or contains(text(),'Aceptar')]"));
            if (!btns.isEmpty()) {
                btns.get(0).click();
            }
        } catch (Exception ignored) {
        }
        Assert.assertTrue("Input de búsqueda no visible",
                mercadoLibrePage.isVisibleInputBuscarProductos());
    }

    @When("realizo una busqueda de laptop")
    public void realizoUnaBusquedaDeLaptop() {
        DriverFactory.getDriver().get("https://listado.mercadolibre.cl/laptop");
    }

    @Then("verifico que se muestran resultados para laptop")
    public void verificoQueHayResultadosDeBusqueda() {
        Assert.assertTrue("Resultados de búsqueda no visibles",
                mercadoLibrePage.isVisibleNombrePrimerArticulo());
    }

    @Then("verifico que el primer resultado tiene nombre y precio visibles")
    public void verificoQueElPrimerResultadoTieneNombreYPrecioVisibles() {
        Assert.assertTrue("Nombre del primer articulo no visible",
                mercadoLibrePage.isVisibleNombrePrimerArticulo());
        Assert.assertTrue("Precio del primer articulo no visible",
                mercadoLibrePage.isVisiblePrecioPrimerArticulo());
    }
}
