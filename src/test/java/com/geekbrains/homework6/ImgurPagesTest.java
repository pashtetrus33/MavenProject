package com.geekbrains.homework6;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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
        driver = new ChromeDriver();
        driver.get(IMGUR_URL);
        driver.manage().window().maximize();
    }

    @Test
    @DisplayName("Проверка перехода на страницу авторизации на сайте imgur.com")
    void imgurSwitchToSignInPageTest() {
        new ImgurMainPage(driver)
                .clickSignInButton();
    }

    @Test
    @DisplayName("Проверка авторизации на сайте imgur.com")
    void imgurSignInTest() {
        new ImgurMainPage(driver)
                .clickSignInButton()
                .typeUsername("testerrus33@gmail.com")
                .typePassword("Student2020!")
                .clickSubmit();
    }

    @Test
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
    @DisplayName("Проверка отображения алерта")
    void alertTest() {
        new ImgurMainPage(driver)
                .sendAlert("Alert test")
                .checkAlertText()
                .acceptAlert();

    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}

