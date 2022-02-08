package com.geekbrains.homework6;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.hasText;

public class ImgurMainPage extends BasePage {

    @FindBy(xpath = "//a[.='Sign in']")
    private WebElement signInButton;

    private final static String INPUT_FIELD_BY_XPATH = "//input";
    @FindBy(xpath = INPUT_FIELD_BY_XPATH)
    public WebElement inputField;

    @FindBy(xpath = "//a[.='New post']")
    private WebElement newPostButton;

    private final static String GRAB_LINK_BUTTON_BY_XPATH = "//button[@class='Button Button-hidden isActive']";
    @FindBy(xpath = GRAB_LINK_BUTTON_BY_XPATH)
    private WebElement grabLinkButton;

    private final static String INPUT_NEW_POST_FIELD_BY_XPATH = "//input[@placeholder]";
    @FindBy(xpath = INPUT_NEW_POST_FIELD_BY_XPATH)
    private WebElement inputNewPostField;

    private final static String POST_TITLE_FIELD_BY_XPATH = "//div[@class='Upload-container']//span[@placeholder='Give your post a title...']";
    @FindBy(xpath = POST_TITLE_FIELD_BY_XPATH)
    private WebElement postTitleField;

    @FindBy(xpath = "//div[@class='Dialog-wrapper']//img[contains(@src,'close')]")
    private WebElement closeDialogButton;

    private final static String UPLOAD_DIALOG_BY_XPATH = "//div[@class='Dialog-wrapper']";
    @FindBy(xpath = UPLOAD_DIALOG_BY_XPATH)
    private WebElement uploadDialog;

    @FindBy(xpath = "//div[@class='CommonUploadDialog-head']/span")
    private WebElement getUploadDialogText;

    private final static String SIGN_OUT_HEADER = "//div[@id]//h1";
    @FindBy(xpath = SIGN_OUT_HEADER)
    private WebElement signOutHeader;

    private final static String LOGGEDINUSERNAME_BY_XPATH = "//div[@class='Dropdown NavbarUserMenu']/div[@class='Dropdown-title']/span[1]";
    @FindBy(xpath = LOGGEDINUSERNAME_BY_XPATH)
    public WebElement loggedInUserName;

    private final static String SIGN_OUT_BUTTON_BY_XPATH = "//span[.='Sign Out']";
    @FindBy(xpath = SIGN_OUT_BUTTON_BY_XPATH)
    private WebElement signOutButton;

    public ImgurMainPage(WebDriver driver) {
       super(driver);
    }

    public ImgurSignInPage clickSignInButton() {
        signInButton.click();
        webDriverWait.until(ExpectedConditions.titleIs("Sign In - Imgur"));
        assertTrue(driver.getCurrentUrl().contentEquals("https://imgur.com/signin?redirect=%2F"));
        return new ImgurSignInPage(driver);
    }

    public ImgurMainPage clickNewPostButton() {
        newPostButton.click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(INPUT_NEW_POST_FIELD_BY_XPATH)));
        assertTrue(driver.getCurrentUrl().contentEquals("https://imgur.com/upload"));
        return this;
    }

    public ImgurMainPage sendImage(String s) {
        inputNewPostField.sendKeys(s);
        return this;
    }
    public ImgurMainPage sendPostTitle(String s) {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(POST_TITLE_FIELD_BY_XPATH)));
        postTitleField.sendKeys(s);
        return this;
    }
    public ImgurMainPage clickGrabLinkButton() throws InterruptedException {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(GRAB_LINK_BUTTON_BY_XPATH)));
        //webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(GRAB_LINK_BUTTON_BY_XPATH)));
        grabLinkButton.click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UPLOAD_DIALOG_BY_XPATH)));
        assertThat(getUploadDialogText, hasText("Here's your link!"));
        return this;
    }
    public ImgurMainPage closeDialogButton() {
        closeDialogButton.click();
        return this;
    }
    public ImgurMainPage clickSignOutButton() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(SIGN_OUT_BUTTON_BY_XPATH)));
        signOutButton.click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SIGN_OUT_HEADER)));
        assertThat(signOutHeader, hasText("You have been signed out"));
        return this;
    }

    public ImgurMainPage clickLoggedInUserName() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOGGEDINUSERNAME_BY_XPATH)));
        loggedInUserName.click();
        return this;
    }
}


