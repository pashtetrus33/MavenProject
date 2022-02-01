package com.geekbrains.homework5;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.hasText;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ImgurTest {
    static WebDriver driver;
    static WebDriverWait webDriverWait;
    static Actions actions;
    private final static String IMGUR_URL = "https://imgur.com";

    @BeforeAll
    static void registerDriver() {
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
    webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
    actions = new Actions(driver);
    driver.get(IMGUR_URL);
    driver.manage().window().maximize();
    }
    /*@Test
    void imgurCookieTest() throws InterruptedException {

        // C помощью cookie не получилось авторизоваться :(
       Cookie cookie = new Cookie("accesstoken", "f27dc504e9da5b4b4ad6abd742c61fd2efb9f29c");
        Cookie cookie2 = new Cookie("is_authed", "1");
        Cookie cookie3 = new Cookie("authautologin", "3c46dc9bdd4f2de374a8f6042bf40cda%7EGeGmJqivZrxTW9ySKrEaQHa8GuH6bHJw");
        Cookie cookie4 = new Cookie("uuid2", "6688093845741700813");
        driver.manage().addCookie(cookie);
        driver.manage().addCookie(cookie2);
        driver.manage().addCookie(cookie3);
        driver.manage().addCookie(cookie4);
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
        Thread.sleep(5000);
    }*/

    @Test
    @Order(1)
    @DisplayName("Проверка авторизации на сайте imgur.com")
    void imgurSignInTest() throws InterruptedException {

        driver.findElement(By.xpath("//a[.='Sign in']")).click();
        driver.findElement(By.id("username")).sendKeys("testerrus33@gmail.com");
        driver.findElement(By.id("password")).sendKeys("Student2020!");
        driver.findElement(By.name("submit")).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='Dropdown NavbarUserMenu']/div[@class='Dropdown-title']/span[1]")));
        assertThat(driver.findElement(By.xpath("//div[@class='Dropdown NavbarUserMenu']/div[@class='Dropdown-title']/span[1]")), hasText("pashtetrus"));
        //Для примера работы с URL (хотя он не отличается от незарегистрировнного)
        Assertions.assertTrue(driver.getCurrentUrl().contentEquals("https://imgur.com/"));
        Assertions.assertTrue(driver.findElement(By.xpath(" //a[.='New post']")).isDisplayed());
    }

    @Test
    @Order(2)
    @DisplayName("Проверка создания нового поста авторизированным пользователемвыхода из учетной записи на сайте imgur.com")
    void newImgurPostTest() throws InterruptedException {
        driver.findElement(By.xpath(" //a[.='New post']")).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Paste image or URL']")));
        driver.findElement(By.xpath("//input[@placeholder]")).sendKeys("https://cdnimg.rg.ru/img/content/178/22/40/kotik_d_850.jpg");
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='Upload-container']//span[@placeholder='Give your post a title...']")));
        driver.findElement(By.xpath("//div[@class='Upload-container']//span[@placeholder='Give your post a title...']")).sendKeys("My new post :)");
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='Button Button-community isActive']")));
        driver.findElement(By.xpath("//button[@class='Button Button-community isActive']")).click();
        driver.findElement(By.xpath("//button[.='Post publicly']")).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='Dialog-wrapper']//img[contains(@src,'close')]")));
        assertThat(driver.findElement(By.xpath("//div[@class='CommonUploadDialog-head']/span")),hasText("Here's your link!"));
        driver.findElement(By.xpath("//div[@class='Dialog-wrapper']//img[contains(@src,'close')]")).click();
    }
    @Test
    @Order(3)
    @DisplayName("Проверка выхода из учетной записи на сайте imgur.com")
    void imgurSignOutTest() throws InterruptedException {
        driver.findElement(By.xpath("//span[@class='UserAvatar NavbarAvatar-navbar NavbarAvatar']")).click();
        driver.findElement(By.xpath("//span[.='Sign Out']")).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id]//h1")));
        assertThat(driver.findElement(By.xpath("//div[@id]//h1")), hasText("You have been signed out"));


    }

    @Test
    @Order(4)
    @DisplayName("Проверка перехода между окнами")
    void windowsTest() throws InterruptedException {
        ((JavascriptExecutor)driver).executeScript("window.open()");
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        driver.get("https://mail.ru");
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(@class,\"ph-login\")]")));
        Assertions.assertTrue(driver.getCurrentUrl().contentEquals("https://mail.ru/"));
        driver.switchTo().window(tabs.get(0));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input")));
        Assertions.assertTrue(driver.getCurrentUrl().contentEquals("https://imgur.com/"));
    }

    @Test
    @Order(5)
    @DisplayName("Проверка отображения алерта")
    void alertTest() throws InterruptedException {
        ((JavascriptExecutor)driver).executeScript("alert(\"Alert test\")");
        Assertions.assertEquals(driver.switchTo().alert().getText(),"Alert test");
        driver.switchTo().alert().accept();

    }
  @AfterAll
    static void tearDown() {
        driver.quit();  }
}
