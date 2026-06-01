Feature: Login exitoso en The Internet

  @TC-401 @smokeTest
  Scenario: Login exitoso en The Internet
    Given navegar a la pagina de login de the internet
    When ingresar usuario "tomsmith" y password "SuperSecretPassword!"
    And click en boton login
    Then verificar mensaje "You logged into a secure area!"
