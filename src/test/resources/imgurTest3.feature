Feature: Проверка авторизации на сайте imgur.com

  Background:
    Given Пользователь находится на главной странице вебсайте

  Scenario Outline:
    When На страинце появляется алерта '<alerttext>'
    Then Пользователь видит валидный текст алерта
    Examples:
      | alerttext      |
      | Alert test     |



