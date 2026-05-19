package org.example.Page.webPage;

import org.example.Page.WebBasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class SaucedemoPage extends WebBasePage {
    public SaucedemoPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 30), this);
    }

    @FindBy(css = "input[placeholder='Username']")
    private WebElement inputUsuario;

    @FindBy(css = "input[placeholder='Password']")
    private WebElement inputPass;

    @FindBy(css = "input[type='submit']")
    private WebElement botonLogin;

    @FindBy(css = "div.inventory_item:nth-child(1) > div.inventory_item_description > div.inventory_item_price")
    private WebElement sauceLabsBackpack;

    @FindBy(css = "div.inventory_item:nth-child(2) > div.inventory_item_description > div.inventory_item_price")
    private WebElement sauceLabsBikeLight;

    @FindBy(css = "span.shopping_cart_badge")
    private WebElement contadorCarrito;

    public void navegarAUrl(String url) {
        getDriver().get(url);
    }

    public void ingresarUsuarioYPass(String usuario, String pass) {
        inputUsuario.sendKeys(usuario);
        inputPass.sendKeys(pass);
    }

    public void clickBotonLogin() {
        waitUntilElementIsVisible(botonLogin);
        botonLogin.click();
    }

    public void agregarSauceLabsBackpackAlCarrito() {
        waitUntilElementIsVisible(sauceLabsBackpack);
        sauceLabsBackpack.click();
    }

    public void agregarSauceLabsBikeLightAlCarrito() {
        waitUntilElementIsVisible(sauceLabsBikeLight);
        sauceLabsBikeLight.click();
    }

    public boolean contadorCarrito() {
        waitUntilElementIsVisible(contadorCarrito);
        return contadorCarrito.getText().equals("2");
    }
}