Feature: Consulta paginas mercado libre
  BDD que detalla el flujo para obtener informacion de las 3 primera paginas de mercado libre cuando se busca camiseta

  @smokeTest @busquedaMercadoLibre
  Scenario: Busqueda en mercado libre
    Given me encuentro en la pagina principal de mercado libre
    When realizo una busqueda de "Camisetas"
    Then me muestra resultado de la busqueda
    And obtengo la siguiente informacion de las 3 primeras paginas:
    |informacion    |
    |nombreArticulo |
    |precioArticulo |
    |linksArticulos |

@TC-106 @smokeTest
  Scenario: Agregar primer producto al carrito en Mercado Libre
    Given me encuentro en la pagina principal de mercado libre
    When realizo una busqueda de "laptop"
    Then me muestra resultado de la busqueda
    And ingresar al primer resultado
    And agregar al carrito
    And verificar que el carrito tiene un producto
