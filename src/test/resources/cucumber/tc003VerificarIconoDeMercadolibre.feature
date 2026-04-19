@mercadoLibreFeature
Feature: Verificar icono de MercadoLibre
  @TC-003 @smokeTest
  Scenario: Verificar icono de MercadoLibre
    Given me encuentro en la pagina principal de mercado libre
    Then obtengo la siguiente informacion de las 1 primeras paginas:
    And me muestra resultado de la busqueda