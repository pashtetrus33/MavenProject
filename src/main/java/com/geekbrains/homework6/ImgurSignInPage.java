package com.geekbrains.homework6;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.hasText;

public class ImgurSignInPage extends BasePage {

    @FindBy(id = "username")
    private WebElement username;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(name = "submit")
    public WebElement submitButton;

    private final static String LOGGEDINUSERNAME_BY_XPATH = "//div[@class='Dropdown NavbarUserMenu']/div[@class='Dropdown-title']/span[1]";
    @FindBy(xpath = LOGGEDINUSERNAME_BY_XPATH)
    public WebElement loggedInUserName;

    @FindBy(xpath = "//a[.='New post']")
    public WebElement newPostButton;

    public ImgurSignInPage(WebDriver driver) {
        super(driver);
    }

    public ImgurSignInPage typeUsername(String s) {
        username.sendKeys(s);
        return this;
    }

    public ImgurSignInPage typePassword(String s) {
        password.sendKeys(s);
        return this;
    }

    public ImgurMainPage clickSubmit() {
        submitButton.click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOGGEDINUSERNAME_BY_XPATH)));
        assertThat(loggedInUserName, hasText("pashtetrus"));
        assertTrue(newPostButton.isDisplayed());
        return new ImgurMainPage(driver);
    }

}

