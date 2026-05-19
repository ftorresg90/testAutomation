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
    private WebElement btnLogin;

    @FindBy(css = "div#item_4_title_link > div")
    private WebElement sauceLabsBackpack;

    @FindBy(css = "div#item_0_title_link > div")
    private WebElement sauceLabsBikeLight;

    @FindBy(css = "span.shopping_cart_badge")
    private WebElement contadorCarrito;

    public void navegarA(String url) {
        getDriver().get(url);
    }

    public void ingresarUsuarioYPass(String usuario, String pass) {
        waitUntilElementIsVisible(inputUsuario);
        inputUsuario.sendKeys(usuario);
        waitUntilElementIsVisible(inputPass);
        inputPass.sendKeys(pass);
    }

    public void clickEnBotonLogin() {
        waitUntilElementIsVisible(btnLogin);
        btnLogin.click();
    }

    public void agregarSauceLabsBackpackAlCarrito() {
        waitUntilElementIsVisible(sauceLabsBackpack);
        sauceLabsBackpack.click();
        waitUntilElementIsVisible(By.cssSelector("button[name='add-to-cart-sauce-labs-backpack']"));
        getDriver().findElement(By.cssSelector("button[name='add-to-cart-sauce-labs-backpack']")).click();
    }

    public void agregarSauceLabsBikeLightAlCarrito() {
        waitUntilElementIsVisible(sauceLabsBikeLight);
        sauceLabsBikeLight.click();
        waitUntilElementIsVisible(By.cssSelector("button[name='add-to-cart-sauce-labs-bike-light']"));
        getDriver().findElement(By.cssSelector("button[name='add-to-cart-sauce-labs-bike-light']")).click();
    }

    public boolean getContadorCarrito() {
        waitUntilElementIsVisible(contadorCarrito);
        return contadorCarrito.getText().equals("2");
    }
}