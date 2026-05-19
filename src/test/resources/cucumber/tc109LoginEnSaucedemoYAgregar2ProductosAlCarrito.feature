Feature: Login en SauceDemo y agregar 2 productos al carrito

  @TC-109 @smokeTest
  Scenario: Login en SauceDemo y agregar 2 productos al carrito
    Given navegar a "https://www.saucedemo.com/"
    When ingresar usuario "standard_user" y password "secret_sauce"
    And click en boton login
    And agregar sauce labs backpack al carrito
    And agregar sauce labs bike light al carrito
    Then verificar que el contador del carrito muestre 2
