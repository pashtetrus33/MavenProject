package com.geekbrains.homework6;

import com.geekbrains.homework7.CustomLoggerNew;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.events.EventFiringDecorator;

@Story("Story for education")
public class ImgurPagesTest {
    static WebDriver driver;
    private final static String IMGUR_URL = "https://imgur.com/";
    private final static String MAILRU_URL = "https://mail.ru/";

    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void initDriver() {
        driver = new EventFiringDecorator(new CustomLoggerNew()).decorate(new ChromeDriver());
        driver.get(IMGUR_URL);
        driver.manage().window().maximize();
    }

    @Test
    @Feature("Imgur Website")
    @Description("Sign In page opening check test")
    @DisplayName("Проверка перехода на страницу авторизации на сайте imgur.com")
    void imgurSwitchToSignInPageTest() {
        new ImgurMainPage(driver)
                .clickSignInButton();
    }

    @Test
    @Feature("Imgur Website")
    @Description("Sign in check test")
    @DisplayName("Проверка авторизации на сайте imgur.com")
    void imgurSignInTest() {
        new ImgurMainPage(driver)
                .clickSignInButton()
                .typeUsername("testerrus33@gmail.com")
                .typePassword("Student2020!")
                .clickSubmit();
    }

    @Test
    @Feature("Imgur Website")
    @Description("New post creation check test")
    @DisplayName("Проверка создания нового поста неавторизированным пользователем")
    void newImgurPostTest() throws InterruptedException {
        new ImgurMainPage(driver)
                .clickNewPostButton()
                .sendImage("https://cdnimg.rg.ru/img/content/178/22/40/kotik_d_850.jpg")
                .sendPostTitle("My new post :)")
                .clickGrabLinkButton()
                .closeDialogButton();

    }

    @Test
    @Feature("Imgur Website")
    @Description("Sign Out check test")
    @DisplayName("Проверка выхода из учетной записи на сайте imgur.com")
    void imgurSignOutTest() {
        new ImgurMainPage(driver)
                .clickSignInButton()
                .typeUsername("testerrus33@gmail.com")
                .typePassword("Student2020!")
                .clickSubmit()
                .clickLoggedInUserName()
                .clickSignOutButton();

    }

    @Test
    @Feature("WindowsSwitcher")
    @Description("Window switch check test")
    @DisplayName("Проверка перехода между окнами")
    void windowsTest() {
        new ImgurMainPage(driver)
                .newWindowOpen()
                .getWindowHandles()
                .switchToWindow(1)
                .openWebSite(MAILRU_URL)
                .checkCurrentUrl(MAILRU_URL)
                .switchToWindow(0)
                .checkCurrentUrl(IMGUR_URL);
    }

    @Test
    @Feature("Alerts")
    @Description("Alert check test")
    @DisplayName("Проверка отображения алерта")
    void alertTest() {
        new ImgurMainPage(driver)
                .sendAlert("Alert test")
                .checkAlertText()
                .acceptAlert();

    }

    @AfterEach
    void killDriver() {
        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        for (LogEntry log: logEntries) {
            Allure.addAttachment("Лог браузера:", log.getMessage());
        }
        driver.quit();
    }
}

