@mercadoLibreFeature
Feature: Verificar resultados de búsqueda en MercadoLibre
  @TC-004 @smokeTest
  Scenario: Verificar resultados de búsqueda en MercadoLibre
    Given me encuentro en la pagina principal de mercado libre
    When realizo una busqueda de "laptop"
    Then me muestra resultado de la busqueda