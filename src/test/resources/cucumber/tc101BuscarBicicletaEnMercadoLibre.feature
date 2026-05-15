@mercadoLibreFeature
Feature: Buscar bicicleta en Mercado Libre
  @TC-101 @smokeTest
  Scenario: Buscar bicicleta en Mercado Libre
    Given me encuentro en la pagina principal de mercado libre
    When realizo una busqueda de "bicicleta"
    Then me muestra resultado de la busqueda