package com.geekbrains.homework8;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.hasText;

public class ImgurSignInPage {

    private SelenideElement username = $(By.id("username"));

    private SelenideElement password = $(By.id("password"));

    private SelenideElement submitButton = $(By.name("submit"));

    public SelenideElement loggedInUserName = $(By.xpath("//div[@class='Dropdown NavbarUserMenu']/div[@class='Dropdown-title']/span[1]"));

    public SelenideElement newPostButton = $(By.xpath("//a[.='New post']"));

    @Step("Заполнить поле логина")
    public ImgurSignInPage typeUsername(String login) {
        username.sendKeys(login);
        return this;
    }

    @Step("Заполнить поле пароля")
    public ImgurSignInPage typePassword(String pass) {
        password.sendKeys(pass);
        return this;
    }

    @Step("Клик на кнопку Submit для отправки логина и пароля на сервер")
    public ImgurSignInPage clickSubmit() {
        submitButton.click();
        return this;
    }

    @Step("Проверка входа пользовтеля под своей учетной записью")
    public ImgurMainPage checkAuthorizedPage() {
        assertThat(loggedInUserName, hasText("pashtetrus"));
        assertTrue(newPostButton.isDisplayed());
        return page(ImgurMainPage.class);
    }
}
