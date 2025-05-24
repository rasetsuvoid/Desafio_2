package pages;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertTrue;

public class ContactoPage {
    WebDriver driver;

    public ContactoPage(WebDriver driver) {
        this.driver = driver;
    }

    By nombre = By.id("nombre");
    By email = By.name("email");
    By varrio = By.name("barrio");
    By asunto = By.name("asunto");
    By mensaje = By.name("Mensaje");
    By enviar = By.xpath("//*[@id=\"formulario\"]/div[7]/button");

    public void ingresarNombre(String texto) {
        driver.findElement(nombre).sendKeys(texto);
    }

    public void ingresarEmail(String texto) {
        driver.findElement(email).sendKeys(texto);
    }

    public void ingresarBarrio(String texto) {
        driver.findElement(varrio).sendKeys(texto);
    }

    public void ingresarAsunto(String texto) {
        driver.findElement(asunto).sendKeys(texto);
    }

    public void ingresarMensaje(String texto) {
        driver.findElement(mensaje).sendKeys(texto);
    }

    public void presionarEnviar() {
        driver.findElement(enviar).click();
    }

    public void limpiarCampos() {
        driver.findElement(nombre).clear();
        driver.findElement(email).clear();
        driver.findElement(varrio).clear();
        driver.findElement(asunto).clear();
        driver.findElement(mensaje).clear();
    }

    public void verificarErrores() {
        // Manejar alerta si está presente
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
            Alert alerta = wait.until(ExpectedConditions.alertIsPresent());
            System.out.println("Alerta detectada: " + alerta.getText());
            alerta.accept(); // o alerta.dismiss();
        } catch (Exception e) {
            // No hay alerta, continuar normalmente
        }

        // Verificar que existan errores visibles
        List<WebElement> errores = driver.findElements(By.className("formulario__input-error-activo"));
        assert !errores.isEmpty() : "No se encontraron mensajes de error visibles.";
    }

    public void verificarMensajeErrorCampo(String campo, boolean esValido) {
        String idGrupo = "";

        switch (campo) {
            case "Tu Nombre":
                idGrupo = "grupo__nombre";
                break;
            case "Tu Email":
                idGrupo = "grupo__email";
                break;
            case "Tu Barrio":
                idGrupo = "grupo__barrio";
                break;
            case "Asunto":
                idGrupo = "grupo__asunto";
                break;
            case "Mensaje":
                idGrupo = "grupo__mensaje";
                break;
            default:
                throw new IllegalArgumentException("Campo no reconocido: " + campo);
        }

        // Buscar el <div> del grupo
        WebElement grupo = driver.findElement(By.id(idGrupo));

        // Buscar el <p> del mensaje de error dentro del grupo
        WebElement mensajeError = grupo.findElement(By.cssSelector("p.formulario__input-error"));

        // Verificar que tenga la clase 'formulario__input-error-activo'
        String clases = mensajeError.getAttribute("class");

        if (esValido) {
            assertTrue("No se muestra el mensaje de error para el campo: " + campo,
                clases.contains("formulario__input-error-activo"));
        }else {
            assertTrue("Se muestra el mensaje de error para el campo: " + campo,
                !clases.contains("formulario__input-error-activo"));
        }

    }

}