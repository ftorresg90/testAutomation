package org.example.Page.webPage;

import org.example.Page.WebBasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class TheInternetPage extends WebBasePage {

    public TheInternetPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 30), this);
    }

    @FindBy(css = "#username")
    private WebElement inputUsuario;

    @FindBy(css = "#password")
    private WebElement inputPass;

    @FindBy(css = "#login > button")
    private WebElement btnLogin;

    @FindBy(css = "#flash")
    private WebElement mensajeFlash;

    public void navegarALaPaginaDeLogin() {
        getDriver().get("https://the-internet.herokuapp.com/login");
    }

    public void ingresarUsuarioYPass(String usuario, String pass) {
        waitUntilElementIsVisible(inputUsuario);
        inputUsuario.sendKeys(usuario);
        inputPass.sendKeys(pass);
    }

    public void clickEnBotonLogin() {
        waitUntilElementIsVisible(btnLogin);
        btnLogin.click();
    }

    public boolean verificarMensaje(String mensaje) {
        waitUntilElementIsVisible(mensajeFlash);
        return mensajeFlash.getText().contains(mensaje);
    }
}