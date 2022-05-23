package org.example.Page.webPage;

import org.example.Page.WebBasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class MercadoLibrePage extends WebBasePage {

    public MercadoLibrePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[contains(text(), 'Mercado Libre Chile - Donde comprar y vender de todo')]")
    private WebElement iconoMercadoLibre;

    @FindBy(id = "cb1-edit")
    private WebElement inputBuscarProductos;

    @FindBy(xpath = "//button[@class = 'nav-search-btn']")
    private WebElement iconoBuscar;

    @FindBy(xpath = "//span[contains(text(), 'resultados')]")
    private WebElement labelResultados;

    @FindBy(xpath = "//span[@class = 'andes-pagination__arrow-title' and contains(text(), 'Siguiente')]")
    private WebElement btnSiguiente;

    @FindBy(xpath = "//button[contains(text(), 'Entendido')]")
    private WebElement btnEntendido;

    private static final String LISTADO_NOMBRES_ARTICULOS = "//h2[@class = 'ui-search-item__title']";
    private static final String LISTADO_PRECIOS_ARTICULOS = "//div[@class = 'ui-search-price ui-search-price--size-medium']//div[@class = 'ui-search-price__second-line']//span[@class = 'price-tag ui-search-price__part']//span[@class = 'price-tag-text-sr-only']//following-sibling::span//span[2]";
    private static final String LISTADO_LINKS_ARTICULOS = "//div[@class = 'ui-search-item__group ui-search-item__group--title']//a[1]";


    public boolean isVisibleIconoMercadoLibre() {
        waitUntilElementIsVisibleNonThrow(iconoMercadoLibre, 10);
        return isVisible(iconoMercadoLibre);
    }

    public boolean isVisibleInputBuscarProductos() {
        waitUntilElementIsVisibleNonThrow(inputBuscarProductos, 10);
        return isVisible(inputBuscarProductos);
    }

    public void sendKeysInputBuscarProductos(String value) {
        waitUntilElementIsVisible(inputBuscarProductos);
        inputBuscarProductos.clear();
        inputBuscarProductos.sendKeys(value);
    }

    public void clickIconoBuscar() {
        waitUntilElementIsVisible(iconoBuscar);
        iconoBuscar.click();
    }

    public boolean isVisibleTextoBuscado(String texto){
        By selector = By.xpath("//h1[contains(text(), '"+texto+"') and @class = 'ui-search-breadcrumb__title']");
        waitUntilElementIsVisibleNonThrow(selector,10);
        return isVisible(selector);
    }

    public boolean isVisibleResultados() {
        waitUntilElementIsVisibleNonThrow(labelResultados,10);
        return isVisible(labelResultados);
    }

    public void clickBtnSiguiente() {
        waitUntilElementIsVisible(btnSiguiente);
        btnSiguiente.click();
    }

    public List<String> getNombresArticulos(){
        List<String> nombresArticulos = new ArrayList<>();
        By selector = By.xpath(LISTADO_NOMBRES_ARTICULOS);
        waitUntilElementIsVisible(selector);
        List<WebElement> elements = getDriver().findElements(selector);
        for(WebElement element: elements){
            nombresArticulos.add(element.getText());
        }
        return nombresArticulos;
    }

    public List<String> getPreciosArticulos(){
        List<String> preciosArticulos = new ArrayList<>();
        By selector = By.xpath(LISTADO_PRECIOS_ARTICULOS);
        waitUntilElementIsVisible(selector);
        List<WebElement> elements = getDriver().findElements(selector);
        for(WebElement element: elements){
            preciosArticulos.add(element.getText().replace(".",""));
        }
        return preciosArticulos;
    }

    public List<String> getLinksArticulos(){
        List<String> linksArticulos = new ArrayList<>();
        By selector = By.xpath(LISTADO_LINKS_ARTICULOS);
        waitUntilElementIsVisible(selector);
        List<WebElement> elements = getDriver().findElements(selector);
        for(WebElement element: elements){
            linksArticulos.add(element.getAttribute("href").toString());
        }
        return linksArticulos;
    }

    public void clickBtnEntendido() {
        waitUntilElementIsVisible(btnEntendido);
        btnEntendido.click();
    }
}
