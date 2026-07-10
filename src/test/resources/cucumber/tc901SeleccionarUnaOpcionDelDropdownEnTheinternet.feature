Feature: Seleccionar una opcion del dropdown en the-internet

  @TC-901 @smokeTest
  Scenario: Seleccionar una opcion del dropdown en the-internet
    Given abrir la pagina del dropdown
    When seleccionar la opcion 1 del dropdown
    Then verificar que la opcion 1 queda seleccionada
