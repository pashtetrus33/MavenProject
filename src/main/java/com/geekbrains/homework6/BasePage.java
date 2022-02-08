package com.geekbrains.homework6;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

abstract public class BasePage {
    WebDriver driver;
    WebDriverWait webDriverWait;
    Actions actions;
    List<String> tabs;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    public BasePage newWindowOpen(){
        ((JavascriptExecutor) driver).executeScript("window.open()");
        return this;
    }

    public BasePage getWindowHandles (){
        tabs = new ArrayList<>(driver.getWindowHandles());
        return this;
    }

    public BasePage switchToWindow (int tabNumber){
        driver.switchTo().window(tabs.get(tabNumber));
        return this;
    }

    public BasePage openWebSite (String url){
        driver.get(url);
        return this;
    }

    public BasePage checkCurrentUrl (String url){
        assertTrue(driver.getCurrentUrl().contentEquals(url));
        return this;
    }
    public BasePage sendAlert(String text) {
        ((JavascriptExecutor)driver).executeScript("alert(\""+ text + "\")");
        return this;
    }

    public BasePage acceptAlert() {
        driver.switchTo().alert().accept();
        return this;
    }
    public BasePage checkAlertText() {
        assertEquals(driver.switchTo().alert().getText(), "Alert test");
        return this;
    }
}
