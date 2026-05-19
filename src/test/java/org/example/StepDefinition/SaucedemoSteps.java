
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

    @Given("navegar a \"https://www.saucedemo.com/\"")
    public void navegar_a() {
        page.navegarA();
    }

    @When("ingresar usuario \"standard_user\" y password \"secret_sauce\"")
    public void ingresar_usuario_y_password() {
        page.ingresarUsuarioYPasssword();
    }

    @And("click en boton login")
    public void click_en_boton_login() {
        page.clickBotonLogin();
    }

    @And("agregar sauce labs backpack al carrito")
    public void agregar_sauce_labs_backpack_al_carrito() {
        page.agregarSauceLabsBackpackAlCarrito();
    }

    @And("agregar sauce labs bike light al carrito")
    public void agregar_sauce_labs_bike_light_al_carrito() {
        page.agregarSauceLabsBikeLightAlCarrito();
    }

    @Then("verificar que el contador del carrito muestre {int}")
    public void verificar_que_el_contador_del_carrito_muestre(int cantidad) {
        Assert.assertTrue("El contador del carrito no muestra la cantidad correcta", page.contadorDelCarrito() == cantidad);
    }
}