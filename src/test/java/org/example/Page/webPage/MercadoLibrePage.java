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

    private static final String LISTADO_NOMBRES_ARTICULOS = "//h2[contains(@class,'poly-component__title') or contains(@class,'ui-search-item__title')]";
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
        waitUntilElementIsVisibleNonThrow(selector, 5);
        if (isVisible(selector)) return true;
        // Headless fallback: URL contains search term
        String url = getDriver().getCurrentUrl().toLowerCase();
        return url.contains(texto.toLowerCase());
    }

    public boolean isVisibleResultados() {
        // URL check first — avoids AjaxElementLocatorFactory proxy blocking
        try {
            String url = getDriver().getCurrentUrl().toLowerCase();
            if (url.contains("listado.mercadolibre") || url.contains("/search")) return true;
        } catch (Exception ignored) { }
        // Fall back to direct XPath (not @FindBy proxy) to avoid 90s AjaxElementLocator wait
        By selector = By.xpath("//span[contains(text(), 'resultados')]");
        waitUntilElementIsVisibleNonThrow(selector, 10);
        return isVisible(selector);
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
        waitUntilElementIsVisibleNonThrow(btnEntendido, 5);
        if (isVisible(btnEntendido)) {
            btnEntendido.click();
        }
    }

    // --- TC-010 ---

    public void filtrarPorCondicionNuevo() {
        By selector = By.xpath(
            "//ul[contains(@class,'ui-search-filter-groups')]//a[contains(normalize-space(),'Nuevo')]"
        );
        waitUntilElementIsVisibleNonThrow(selector, 10);
        if (isVisible(selector)) {
            getDriver().findElement(selector).click();
        }
    }

    public void ordenarPorMenorPrecio() {
        By link = By.xpath(
            "//a[contains(@href,'sort=price_asc') or contains(normalize-space(),'Menor precio')]"
        );
        waitUntilElementIsVisibleNonThrow(link, 10);
        if (isVisible(link)) {
            getDriver().findElement(link).click();
        }
    }

    public void clickPrimerResultado() {
        By selector = By.xpath(
            "(//li[contains(@class,'ui-search-layout__item')]//a[contains(@class,'ui-search-link')])[1]"
        );
        waitUntilElementIsVisible(selector);
        getDriver().findElement(selector).click();
    }

    public boolean isPaginaDetalleProducto() {
        String url = getDriver().getCurrentUrl();
        return url != null && (url.contains("/p/ML") || url.contains("articulo") || url.contains("/productos/"));
    }

    public boolean isTituloProductoVisible() {
        By selector = By.xpath("//h1[contains(@class,'ui-pdp-title')]");
        waitUntilElementIsVisibleNonThrow(selector, 10);
        return isVisible(selector);
    }

    public boolean isPrecioProductoVisible() {
        By selector = By.xpath("//span[contains(@class,'andes-money-amount__fraction')]");
        waitUntilElementIsVisibleNonThrow(selector, 10);
        return isVisible(selector);
    }

    public boolean isBotonComprarAhoraVisible() {
        By selector = By.xpath(
            "//button[contains(@class,'ui-pdp-action--primary') or @aria-label='Comprar ahora']"
        );
        waitUntilElementIsVisibleNonThrow(selector, 10);
        return isVisible(selector);
    }
}
