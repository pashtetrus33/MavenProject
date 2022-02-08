package com.geekbrains.homework6;

import io.qameta.allure.Step;
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

    @Step("Открыть новое окно")
    public BasePage newWindowOpen(){
        ((JavascriptExecutor) driver).executeScript("window.open()");
        return this;
    }

    @Step("Заросить дискрипторы окон")
    public BasePage getWindowHandles (){
        tabs = new ArrayList<>(driver.getWindowHandles());
        return this;
    }

    @Step("Переключиться между окнами")
    public BasePage switchToWindow (int tabNumber){
        driver.switchTo().window(tabs.get(tabNumber));
        return this;
    }

    @Step("Открыть вебсайт по URL адресу")
    public BasePage openWebSite (String url){
        driver.get(url);
        return this;
    }

    @Step("Проверить текущий URL адрес")
    public BasePage checkCurrentUrl (String url){
        assertTrue(driver.getCurrentUrl().contentEquals(url));
        return this;
    }

    @Step("Создать алерт")
    public BasePage sendAlert(String text) {
        ((JavascriptExecutor)driver).executeScript("alert(\""+ text + "\")");
        return this;
    }

    @Step("Клик на кнопку подтверждения алерта")
    public BasePage acceptAlert() {
        driver.switchTo().alert().accept();
        return this;
    }

    @Step("Проверить текст алерта")
    public BasePage checkAlertText() {
        assertEquals(driver.switchTo().alert().getText(), "Alert test");
        return this;
    }
}
