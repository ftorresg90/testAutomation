package org.example.Page;

import org.awaitility.core.ConditionTimeoutException;
import org.example.Utils.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Path;
import java.nio.file.Paths;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;


public class WebBasePage {

    private static final int WAIT_TIMEOUT = 90;
    private static final int POLLING = 100;

    final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(id ="loading-bar")
    private WebElement barraCargando;

    protected WebBasePage(WebDriver driver){
        this.driver=driver;
        this.wait= new WebDriverWait(driver, WAIT_TIMEOUT, POLLING);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,WAIT_TIMEOUT), this);
    }

    protected WebBasePage(WebDriver driver, int timeOutSec){
        this.driver=driver;
        this.wait= new WebDriverWait(driver, timeOutSec, POLLING);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, WAIT_TIMEOUT), this);
    }

    protected WebBasePage(WebDriver driver, int timeOutSec, int pollingSec){
        this.driver=driver;
        this.wait= new WebDriverWait(driver, timeOutSec, pollingSec);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, WAIT_TIMEOUT), this);
    }

    protected WebDriver getDriver(){
        return driver;
    }

    protected void waitForElementToAppear(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForElementToDisappear(WebElement element) {
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    protected void waitForTextToDisappear(WebElement element, String text) {
        wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(element, text)));
    }

    protected boolean isVisible(WebElement webElement){
        try {
            return webElement.isDisplayed();
        } catch (Exception e){
            return false;
        }
    }

    protected boolean isInvisible(WebElement element){
        try {
            return !element.isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException e){
            return true;
        }catch (Exception e){
            return false;
        }
    }

    protected boolean isInvisible(By by){
        try {
            return !getDriver().findElement(by).isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public void waitUntilElementIsVisible(WebElement element){
        try{
            await().atMost(WAIT_TIMEOUT, SECONDS).until(()->isVisible(element));
        }catch (ConditionTimeoutException e){
            throw new ConditionTimeoutException(String.format("No se encuentra el elemento despues de 30 segundos\nElemento: %s", element));
        }
    }

    public void waitUntilElementIsVisible(By element){
        try{
            await().atMost(WAIT_TIMEOUT, SECONDS).until(()->isVisible(element));
        }catch (ConditionTimeoutException e){
            throw new ConditionTimeoutException(String.format("No se encuentra el elemento despues de 30 segundos\nElemento: %s", element));
        }
    }

    public void waitUntilElementIsVisibleNonThrow(WebElement element, int WAIT_TIMEOUT){
        try{
            await().atMost(WAIT_TIMEOUT, SECONDS).until(()->isVisible(element));
        } catch (ConditionTimeoutException e) {
        }
    }

    public void waitUntilElementIsInVisible(WebElement element){
        try{
            await().atMost(WAIT_TIMEOUT, SECONDS).until(()->isInvisible(element));
        }catch (ConditionTimeoutException e){
            throw new ConditionTimeoutException(String.format("El elemento no desaparece despues de 30 segundos\nElemento: %s", element));
        }
    }

    protected void waitFor(int segundos){
        try {
            Thread.sleep(segundos*1000);
        }catch (InterruptedException ignored){

        }
    }

    public byte[] takeScreenshot(){
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    public void uploadArchivo(WebElement element, String nombreArchivo) {
        JavascriptExecutor JavascriptExecutor = ((JavascriptExecutor)getDriver());
        JavascriptExecutor.executeScript("var xxPathResult=document.evaluate('//*[@id=\"body-portal\"]/label', document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;");
        ((RemoteWebDriver) getDriver()).setFileDetector(new LocalFileDetector());
        Path root = Paths.get(".").normalize().toAbsolutePath();
        waitUntilElementIsVisibleNonThrow(element, 10);
        String filePath = root+"/src/test/resources/Datafile/" + nombreArchivo;
        element.sendKeys(filePath);
    }

    public void waitUntilElementIsVisibleNonThrow(By element, int WAIT_TIMEOUT){
        try{
            await().atMost(WAIT_TIMEOUT, SECONDS).until(()->isVisible(element));
        } catch (ConditionTimeoutException e) {
        }
    }

    public boolean isVisible(By MobileElement) {
        try {
            return getDriver().findElement(MobileElement).isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isVisibleMensaje(String mensaje){
        By selector = By.xpath("//*[contains(.,'"+mensaje+"')]");
        waitUntilElementIsVisibleNonThrow(selector,30);
        WebElement element = getDriver().findElement(selector);
        return isVisible(element);
    }

    public void waitUntilElementIsInVisibleNonThrow(WebElement element, int WAIT_TIMEOUT){
        try{
            await().atMost(WAIT_TIMEOUT, SECONDS).until(()->isInvisible(element));
        } catch (ConditionTimeoutException e) {
        }
    }

    public void waitUntilElementIsInVisibleNonThrow(By by, int WAIT_TIMEOUT){
        try{
            await().atMost(WAIT_TIMEOUT, SECONDS).until(()->isInvisible(by));
        } catch (ConditionTimeoutException e) {
        }
    }

    public void waitUntilPageIsLoaded(){
        waitUntilElementIsInVisibleNonThrow(barraCargando,30);
    }

    public boolean isVisibleMensajeError(String mensajeError){
        WebElement objMensaje = null;
        By selector = By.xpath("(//*[(contains(@class,'invalid') or contains(@class,'text-danger') or contains(@class, 'error')) and contains(text(),'"+mensajeError+"')])[last()]");
        waitUntilElementIsVisibleNonThrow(selector,10);
        if (isVisible(selector)){
            objMensaje = getDriver().findElement(selector) ;
            scrollDownToElement(objMensaje);
            return true;
        }else{
            By selectorAux = By.xpath("(//*[contains(@class,'invalid') or contains(@class,'error') or contains(@class,'warning')]//*[contains(text(),'"+mensajeError+"')])[last()]");
            waitUntilElementIsVisibleNonThrow(selectorAux,10);
            objMensaje = getDriver().findElement(selectorAux) ;
            scrollDownToElement(objMensaje);
            return isVisible(selectorAux);
        }

    }

    public boolean isVisibleMensajeInformativo(String mensaje){
        By selector = By.xpath("//*[contains(text(),'"+mensaje+"')]");
        waitUntilElementIsVisibleNonThrow(selector,20);
        if(isVisible(selector)){
            WebElement element = getDriver().findElement(selector);
            scrollDownToElement(element);
            return isVisible(element);
        }else {
            return false;
        }
    }

    public void scrollDownToElement(WebElement element) {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    public void scrollDown() {
        Dimension size = this.getDriver().manage().window().getSize();
        int startPoint = (int)((double)size.getHeight() * 0.7D);
        int endPoint = (int)((double)size.getHeight() * 0.4D);
        ((JavascriptExecutor)driver).executeScript("scroll("+startPoint+","+endPoint+")");
    }

    public void switchNewWindow(){
        for(String winHandle : driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
        }
    }

    public static void clickJS(WebElement elemento) {
        try{
            ((JavascriptExecutor) DriverFactory.getDriver()).executeScript("arguments[0].click();", elemento);
        }
        catch (Exception e){
        }
    }

    public boolean isElementPresent(By by) {
        try {
            getDriver().findElement(by);
            return true;
        }
        catch (NoSuchElementException e) {
            return false;
        }
    }

    public void esperarUnMinuto(){
        waitFor(60);
    }

    public void scrollDownBottom() {
        ((JavascriptExecutor) getDriver()).executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }
}