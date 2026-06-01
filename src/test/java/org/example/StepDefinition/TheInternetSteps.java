
package org.example.StepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import org.junit.Assert;
import org.example.Utils.DriverFactory;
import org.example.Utils.ScenarioContext;
import org.example.Page.webPage.TheInternetPage;

public class TheInternetSteps {
    private ScenarioContext context;
    private TheInternetPage page;

    public TheInternetSteps(ScenarioContext context) {
        this.context = context;
        this.page = new TheInternetPage(DriverFactory.getDriver());
    }

    @Given("navegar a la pagina de login de the internet")
    public void navegarALaPaginaDeLoginDeTheInternet() {
        page.navegarALaPaginaDeLogin();
    }

    @When("ingresar usuario {string} y password {string}")
    public void ingresarUsuarioYPass(String usuario, String password) {
        page.ingresarUsuarioYPass(usuario, password);
    }

    @And("click en boton login")
    public void clickEnBotonLogin() {
        page.clickEnBotonLogin();
    }

    @Then("verificar mensaje {string}")
    public void verificarMensaje(String mensaje) {
        Assert.assertTrue("Mensaje de login exitoso no encontrado", page.verificarMensaje(mensaje));
    }
}