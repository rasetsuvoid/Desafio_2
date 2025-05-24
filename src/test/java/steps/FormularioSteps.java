package steps;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.ContactoPage;
import util.UtilidadCapturaPantalla;

public class FormularioSteps {
    WebDriver driver;
    ContactoPage contacto;

    @Before
    public void setUp() {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        contacto = new ContactoPage(driver);
    }

    @Given("El usuario está en la página de contacto de Caso 1")
    public void abrirPagina() {
        driver.get("http://64.227.54.255/Softesting/Frontend/Caso1.html");
        driver.manage().window().maximize();
    }

    @When("El usuario ingresa {string} en el campo {string}")
    public void ingresarCampo(String valor, String campo) {
        switch (campo) {
            case "Tu Nombre":
                contacto.ingresarNombre(valor);
                break;
            case "Tu Email":
                contacto.ingresarEmail(valor);
                break;
            case "Tu Barrio":
                contacto.ingresarBarrio(valor);
                break;
            case "Asunto":
                contacto.ingresarAsunto(valor);
                break;
        }
    }

    @When("ingresa {string} en el campo {string}")
    public void ingresa_en_el_campo(String valor, String campo) {
        ingresarCampo(valor, campo); // reutiliza tu método anterior
    }

    @When("escribe {string} en el campo {string}")
    public void escribe_en_el_campo(String valor, String campo) {
        if (campo.equals("Mensaje")) {
            contacto.ingresarMensaje(valor);
        } else {
            throw new IllegalArgumentException("Campo no soportado: " + campo);
        }
    }

    @When("presiona el botón {string}")
    public void presiona_el_botón(String boton) throws InterruptedException {
        // Tomar captura con timestamp
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String rutaCaptura = "src/test/resources/pantallazos/captura_" + timestamp + ".png";
        UtilidadCapturaPantalla.tomarCaptura(driver, rutaCaptura);

        Thread.sleep(1000);
        
        // Acción sobre el botón
        if (boton.equals("Enviar")) {
            contacto.presionarEnviar();
        } else {
            throw new IllegalArgumentException("Botón no soportado: " + boton);
        }
    }

    @Then("El formulario se envía correctamente")
    public void el_formulario_se_envía_correctamente() {

        Alert alerta = driver.switchTo().alert();
        String mensaje = alerta.getText();
        alerta.accept();
        // Validamos que el mensaje NO sea de error
        Assert.assertNotEquals("UPPPPS ALGO HA FALLADO :(", mensaje);
    }

    @Then("Se muestran mensajes de error indicando que los campos son obligatorios")
    public void verificarErroresCamposObligatorios() {
        contacto.verificarErrores();
    }

    @Then("Se muestra un mensaje de error indicando que el {string} no es válido")
    public void verificarMensajeErrorCampo(String campo) {
        contacto.verificarMensajeErrorCampo(campo, true);
    }

    @Then("No se muestra un mensaje de error para el campo {string}")
    public void noDebeMostrarseMensajeError(String campo) {
        contacto.verificarMensajeErrorCampo(campo, false);
    }

    @After
    public void cerrarDriver() {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String rutaCaptura = "src/test/resources/pantallazos/captura_" + timestamp + ".png";
        UtilidadCapturaPantalla.tomarCaptura(driver, rutaCaptura);
        if (driver != null) {
            driver.quit();
        }
    }
}