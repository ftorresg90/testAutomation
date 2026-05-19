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

    @FindBy(css = "input[name='user-name']")
    private WebElement inputUsuario;

    @FindBy(css = "input[name='password']")
    private WebElement inputPassword;

    @FindBy(css = "input[type='submit']")
    private WebElement botonLogin;

    @FindBy(css = "div.inventory_item_name:contains('Sauce Labs Backpack')")
    private WebElement sauceLabsBackpack;

    @FindBy(css = "div.inventory_item_name:contains('Sauce Labs Bike Light')")
    private WebElement sauceLabsBikeLight;

    @FindBy(css = "span.shopping_cart_badge")
    private WebElement contadorCarrito;

    @FindBy(css = "button[name='add-to-cart-sauce-labs-backpack']")
    private WebElement botonAgregarSauceLabsBackpack;

    @FindBy(css = "button[name='add-to-cart-sauce-labs-bike-light']")
    private WebElement botonAgregarSauceLabsBikeLight;

    public void navegarA() {
        getDriver().get("https://www.saucedemo.com/");
    }

    public void ingresarUsuarioYPasssword() {
        inputUsuario.sendKeys("standard_user");
        inputPassword.sendKeys("secret_sauce");
    }

    public void clickBotonLogin() {
        waitUntilElementIsVisible(botonLogin);
        botonLogin.click();
    }

    public void agregarSauceLabsBackpackAlCarrito() {
        waitUntilElementIsVisible(sauceLabsBackpack);
        botonAgregarSauceLabsBackpack.click();
    }

    public void agregarSauceLabsBikeLightAlCarrito() {
        waitUntilElementIsVisible(sauceLabsBikeLight);
        botonAgregarSauceLabsBikeLight.click();
    }

    public boolean contadorDelCarrito() {
        waitUntilElementIsVisible(contadorCarrito);
        return Integer.parseInt(contadorCarrito.getText()) == 2;
    }
}