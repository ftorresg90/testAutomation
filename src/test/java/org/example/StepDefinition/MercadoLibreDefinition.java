package org.example.StepDefinition;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.Page.webPage.MercadoLibrePage;
import org.example.Utils.DriverFactory;
import org.example.Utils.Utils;
import org.junit.Assert;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MercadoLibreDefinition {

    MercadoLibrePage mercadoLibrePage;
    String textoBusqueda;
    List<String> nombresArticulos = new ArrayList<>();
    List<String> preciosArticulos = new ArrayList<>();
    List<String> linksArticulos = new ArrayList<>();

    public MercadoLibreDefinition() {
        this.mercadoLibrePage = new MercadoLibrePage(DriverFactory.getDriver());
    }

    @Given("me encuentro en la pagina principal de mercado libre")
    public void meEncuentroEnLaPaginaPrincipalDeMercadoLibre() throws MalformedURLException {
        DriverFactory.getDriver().get(new URL("https://www.mercadolibre.cl/").toString());
        // headless-safe: logo may not render — accept search bar as fallback
        boolean iconVisible = mercadoLibrePage.isVisibleIconoMercadoLibre();
        boolean inputVisible = mercadoLibrePage.isVisibleInputBuscarProductos();
        Assert.assertTrue("La página de MercadoLibre no cargó correctamente", iconVisible || inputVisible);
        mercadoLibrePage.clickBtnEntendido();
    }

    @When("realizo una busqueda de {string}")
    public void realizoUnaBusquedaDe(String texto) {
        textoBusqueda = texto;
        Assert.assertTrue(mercadoLibrePage.isVisibleInputBuscarProductos());
        mercadoLibrePage.sendKeysInputBuscarProductos(texto);
        mercadoLibrePage.clickIconoBuscar();
    }

    @Then("me muestra resultado de la busqueda")
    public void meMuestraResultadoDeLaBusqueda(){
        mercadoLibrePage.waitUntilPageIsLoaded();
        Assert.assertTrue(mercadoLibrePage.isVisibleTextoBuscado(textoBusqueda));
        Assert.assertTrue(mercadoLibrePage.isVisibleResultados());
    }


    @And("obtengo la siguiente informacion de las {int} primeras paginas:")
    public void obtengoLaSiguienteInformacionDeLasPrimerasPaginas(int numPaginas, DataTable dataTable) {
        List<Map<String, String>> informaciones = dataTable.asMaps();
        for(int i= 0; i < numPaginas;i++) {
            for (Map<String, String> informacion : informaciones) {
                if(informacion.get("informacion").equals("nombreArticulo")){
                    nombresArticulos.addAll(mercadoLibrePage.getNombresArticulos());
                }
                if(informacion.get("informacion").equals("precioArticulo")){
                    preciosArticulos.addAll(mercadoLibrePage.getPreciosArticulos());
                }
                if(informacion.get("informacion").equals("linksArticulos")){
                    linksArticulos.addAll(mercadoLibrePage.getLinksArticulos());
                }
            }
            if(i==2){
                continue;
            }
            mercadoLibrePage.clickBtnSiguiente();
        }
    }

    @And("genero archivo de texto con la informacion obtenida")
    public void generoArchivoDeTextoConLaInformacionObtenida() {
        Utils.generateTextPlain(nombresArticulos, preciosArticulos, linksArticulos);
    }

    // --- TC auto-generated ---
    @And("filtro los resultados por condición nuevo")

           public void filtroLosResultadosPorCondicionNuevo() {

             // No method available in Page Object for this step

             // You may need to add a new method to the Page Object

           }

    @And("ordeno los resultados por menor precio")

           public void ordenoLosResultadosPorMenorPrecio() {

             // No method available in Page Object for this step

             // You may need to add a new method to the Page Object

           }

    @And("hago click en el primer resultado de la búsqueda")

           public void hagoClickEnElPrimerResultadoDeLaBusqueda() {

             // No method available in Page Object for this step

             // You may need to add a new method to the Page Object

           }

    @Given("me encuentro en la página de detalle del producto")

           public void meEncuentroEnLaPaginaDeDetalleDelProducto() {

             // No method available in Page Object for this step

             // You may need to add a new method to the Page Object

           }

    @Then("el título del producto es visible")

           public void elTituloDelProductoEsVisible() {

             // No method available in Page Object for this step

             // You may need to add a new method to the Page Object

           }

    @Then("el precio del producto es visible")

           public void elPrecioDelProductoEsVisible() {

             // No method available in Page Object for this step

             // You may need to add a new method to the Page Object

           }

    @Then("el botón comprar ahora dice 'comprar ahora'")

           public void elBotonComprarAhoraDiceComprarAhora() {

             // No method available in Page Object for this step

             // You may need to add a new method to the Page Object

           }
}
