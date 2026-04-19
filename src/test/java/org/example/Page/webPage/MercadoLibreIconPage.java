package org.example.Page.webPage;

import org.example.Page.WebBasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class MercadoLibreIconPage extends WebBasePage {

    @FindBy(id = "logo")
    private WebElement iconoMercadoLibre;

    public MercadoLibreIconPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 30), this);
    }

    public boolean isVisibleIconoMercadoLibre() {
        return isVisible(iconoMercadoLibre);
    }
}