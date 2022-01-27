package com.geekbrains;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class App
{
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        // Сценарий авторизации на сайте imgur.com
        driver.get("https://imgur.com");
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.findElement(By.xpath("//a[.='Sign in']")).click();
        driver.findElement(By.id("username")).sendKeys("testerrus33@gmail.com");
        driver.findElement(By.id("password")).sendKeys("Student2020!");
        driver.findElement(By.name("submit")).click();

        // Cценарий создания нового поста авторизированным пользователем
        driver.findElement(By.xpath(" //a[.='New post']")).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Paste image or URL']")));
        driver.findElement(By.xpath("//input[@placeholder]")).sendKeys("https://cdnimg.rg.ru/img/content/178/22/40/kotik_d_850.jpg");
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='Upload-container']//span[@placeholder='Give your post a title...']")));
        driver.findElement(By.xpath("//div[@class='Upload-container']//span[@placeholder='Give your post a title...']")).sendKeys("My new post :)");
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='Button Button-community isActive']")));
        driver.findElement(By.xpath("//button[@class='Button Button-community isActive']")).click();

        driver.findElement(By.xpath("//button[.='Post publicly']")).click();

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='Dialog-wrapper']//img[contains(@src,'close')]")));
        driver.findElement(By.xpath("//div[@class='Dialog-wrapper']//img[contains(@src,'close')]")).click();

        // Сценарий выхода из учетной записи
        driver.findElement(By.xpath("//span[@class='UserAvatar NavbarAvatar-navbar NavbarAvatar']")).click();
        driver.findElement(By.xpath("//span[.='Sign Out']")).click();


        // Ждем 3 сек и закрываем сессию браузера
        Thread.sleep(3000);
        driver.quit();

    }
}
