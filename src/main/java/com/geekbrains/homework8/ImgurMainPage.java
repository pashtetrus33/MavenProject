package com.geekbrains.homework8;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.hasText;

public class ImgurMainPage {

    private SelenideElement signInButton = $(By.xpath("//a[.='Sign in']"));

    public SelenideElement inputField = $(By.xpath("//input"));

    private SelenideElement newPostButton = $(By.xpath("//a[.='New post']"));

    private SelenideElement grabLinkButton = $(By.xpath("//button[@class='Button Button-hidden isActive']"));

    private SelenideElement inputNewPostField = $(By.xpath("//input[@placeholder]"));

    private SelenideElement postTitleField = $(By.xpath("//div[@class='Upload-container']//span[@placeholder='Give your post a title...']"));

    private SelenideElement closeDialogButton = $(By.xpath( "//div[@class='Dialog-wrapper']//img[contains(@src,'close')]"));

    private SelenideElement uploadDialog = $(By.xpath("//div[@class='Dialog-wrapper']"));

    private SelenideElement getUploadDialogText = $(By.xpath("//div[@class='CommonUploadDialog-head']/span"));

    private SelenideElement signOutHeader = $(By.xpath("//div[@id]//h1"));

    private SelenideElement loggedInUserName = $(By.xpath("//div[@class='Dropdown NavbarUserMenu']/div[@class='Dropdown-title']/span[1]"));

    private SelenideElement signOutButton = $(By.xpath("//span[.='Sign Out']"));

    List<String> tabs;

    @Step("Клик на кнопку логина главной страницы")
    public ImgurSignInPage clickSignInButton() {
        signInButton.click();
        return page(ImgurSignInPage.class);
    }

    @Step("Проверка страницы авторизации")
    public ImgurSignInPage checkAuthorizedPage() {
        webdriver().shouldHave(url("https://imgur.com/signin?redirect=%2F"));
        return page(ImgurSignInPage.class);
    }

    @Step("Клик на кнопку создания нового поста")
    public ImgurMainPage clickNewPostButton() {
        newPostButton.click();
        webdriver().shouldHave(url("https://imgur.com/upload"));
        return this;
    }
    @Step("Загрузить изображение")
    public ImgurMainPage sendImage(String s) {
        inputNewPostField.sendKeys(s);
        return this;
    }
    @Step("Добавление описания к новому посту")
    public ImgurMainPage sendPostTitle(String s) {
        postTitleField.sendKeys(s);
        return this;
    }
    @Step("Клик на кнопку  для копирования ссылки на новый пост")
    public ImgurMainPage clickGrabLinkButton() throws InterruptedException {
        grabLinkButton.click();
        assertThat(getUploadDialogText, hasText("Here's your link!"));
        return this;
    }
    @Step("Клик на кнопку закрытия диалогоа добовления нового поста")
    public ImgurMainPage closeDialogButton() {
        closeDialogButton.click();
        return this;
    }
    @Step("Клик на кнопку выхода из учетной записи")
    public ImgurMainPage clickSignOutButton() {
        signOutButton.click();
        return this;
    }

    @Step("Проверка выхода из учетной записи")
    public ImgurMainPage checkSignOutMessage() {
        assertThat(signOutHeader, hasText("You have been signed out"));
        return this;
    }

    @Step("Клик по изображению с именем авторизованного пользователя")
    public ImgurMainPage clickLoggedInUserName() {
        loggedInUserName.click();
        return this;
    }
    @Step("Открыть новое окно")
    public ImgurMainPage newWindowOpen(){
       executeJavaScript("window.open()");
        return this;
    }

    @Step("Заросить дискрипторы окон")
    public ImgurMainPage getWindowHandles (){
        tabs = new ArrayList<String>(getWebDriver().getWindowHandles());
        return this;
    }

    @Step("Переключиться между окнами")
    public ImgurMainPage switchToWindow (int tabNumber){
        Selenide.switchTo().window(tabs.get(tabNumber));
        return this;
    }

    @Step("Открыть вебсайт по URL адресу")
    public ImgurMainPage openWebSite (String url){
        Selenide.open(url);
        return this;
    }

    @Step("Проверить текущий URL адрес")
    public ImgurMainPage checkCurrentUrl (String webadress){
        webdriver().shouldHave(url(webadress));
        return this;
    }

    @Step("Создать алерт")
    public ImgurMainPage sendAlert(String text) {
        executeJavaScript("alert(\""+ text + "\")");
        return this;
    }

    @Step("Клик на кнопку подтверждения алерта")
    public ImgurMainPage acceptAlert() {
        Selenide.switchTo().alert().accept();
        return this;
    }

    @Step("Проверить текст алерта")
    public ImgurMainPage checkAlertText() {
        assertEquals(Selenide.switchTo().alert().getText(), "Alert test");
        return this;
    }
}
