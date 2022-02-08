package com.geekbrains;


import com.codeborne.selenide.Selenide;
import com.geekbrains.homework8.ImgurMainPage;
import com.geekbrains.homework8.ImgurSignInPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ImgurTestStepDefinitions {

    @Given("Пользователь авторизован на вебсайте")
    public void userSignInMethod() {
        new ImgurMainPage()
                .clickSignInButton()
                .typeUsername("testerrus33@gmail.com")
                .typePassword("Student2020!")
                .clickSubmit();
    }

    @When("Пользователь кликает на свой никнейм в правом верхнем углу страницы")
    public void nickNameLoginClick() {
        new ImgurMainPage()
                .clickLoggedInUserName();
    }

    @When("Пользователь кликает на кнопку выхода")
    public void signOutButtonClick() {
        new ImgurMainPage()
                .clickSignOutButton();
    }

    @Then("Пользователь видит сообщение об успешной выходе из учетной записи")
    public void checkSignOutMessage() {
        new ImgurMainPage()
                .checkSignOutMessage();
    }

    @Given("Пользователь находится на главной странице вебсайте")
    public void webSiteOpen() {
        Selenide.open("https://imgur.com/");
    }

    @When("Пользователь кликает на кнопку перехода на странцу авторизации")
    public void signInButtonClick() {
        new ImgurMainPage()
                .clickSignInButton();
    }

    @Then("Пользователь видит в адресной строке адрес страницы авторизации")
    public void checkSignInPageAddress() {
        new ImgurMainPage()
                .checkAuthorizedPage();
    }



    @When("На страинце появляется алерта {string}")
    public void alertCreating(String text) {
        new ImgurMainPage()
                .sendAlert(text);
    }

    @Then("Пользователь видит валидный текст алерта")
    public void checkAlerttext() {
        new ImgurMainPage()
                .checkAlertText();
    }
}
