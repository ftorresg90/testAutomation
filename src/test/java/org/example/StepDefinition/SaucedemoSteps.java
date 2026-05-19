
package org.example.StepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import org.junit.Assert;
import org.example.Utils.DriverFactory;
import org.example.Utils.ScenarioContext;
import org.example.Page.webPage.SaucedemoPage;

public class SaucedemoSteps {
    private ScenarioContext context;
    private SaucedemoPage page;

    public SaucedemoSteps(ScenarioContext context) {
        this.context = context;
        this.page = new SaucedemoPage(DriverFactory.getDriver());
    }

    @Given("navegar a \"{string}\"")
    public void navegarA(String url) {
        page.navegarA(url);
    }

    @When("ingresar usuario \"{string}\" y password \"{string}\"")
    public void ingresarUsuarioYPass(String usuario, String pass) {
        page.ingresarUsuarioYPass(usuario, pass);
    }

    @And("click en boton login")
    public void clickEnBotonLogin() {
        page.clickEnBotonLogin();
    }

    @And("agregar sauce labs backpack al carrito")
    public void agregarSauceLabsBackpackAlCarrito() {
        page.agregarSauceLabsBackpackAlCarrito();
    }

    @And("agregar sauce labs bike light al carrito")
    public void agregarSauceLabsBikeLightAlCarrito() {
        page.agregarSauceLabsBikeLightAlCarrito();
    }

    @Then("verificar que el contador del carrito muestre {int}")
    public void verificarQueElContadorDelCarritoMuestre(int cantidad) {
        Assert.assertTrue("El contador del carrito no muestra la cantidad esperada", page.getContadorCarrito() == cantidad);
    }
}