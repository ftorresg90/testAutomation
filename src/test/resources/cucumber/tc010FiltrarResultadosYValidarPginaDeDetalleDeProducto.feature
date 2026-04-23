Feature: Filtrar resultados y validar página de detalle de producto

  @TC-010 @smokeTest
  Scenario: Filtrar resultados y validar página de detalle de producto
    Given me encuentro en la pagina principal de mercado libre
    When realizo una busqueda de "notebook"
    And filtro los resultados por condición nuevo
    And ordeno los resultados por menor precio
    And hago click en el primer resultado de la búsqueda
    Given me encuentro en la página de detalle del producto
    Then el título del producto es visible
    And el precio del producto es visible
    And el botón comprar ahora dice 'comprar ahora'
