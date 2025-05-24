package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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
}