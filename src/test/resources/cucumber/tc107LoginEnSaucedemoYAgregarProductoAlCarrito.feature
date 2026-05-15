@saucedemoFeature
Feature: Login en SauceDemo y agregar producto al carrito
  @TC-107 @smokeTest
  Scenario: Login en SauceDemo y agregar producto al carrito
    Given me encuentro en la pagina de SauceDemo
    When ingreso el usuario "standard_user" y la contraseña "secret_sauce"
    And presiono el botón de inicio de sesión
    And agrego el primer producto al carrito
    Then verifico el carrito
    And el carrito tiene 1 producto