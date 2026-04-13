@tc002VerificarPrimerResultadoDeBusquedaTieneNombreYPrecio
Feature: Verificar primer resultado de busqueda tiene nombre y precio

  @TC-002 @smokeTest
  Scenario: Verificar primer resultado de busqueda tiene nombre y precio
    Given abro mercado libre y acepto cookies si aparecen
    When realizo una busqueda de laptop
    And verifico que se muestran resultados para laptop
    Then verifico que el primer resultado tiene nombre y precio visibles