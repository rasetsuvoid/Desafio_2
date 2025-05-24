package util;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;

public class UtilidadCapturaPantalla {

public static void tomarCaptura(WebDriver driver, String rutaArchivo) {
    // Obtener la carpeta donde se guardará el screenshot
    String rutaCarpeta = new File(rutaArchivo).getParent();

    // Llamar al método para crear la carpeta si no existe
    crearCarpetaSiNoExiste(rutaCarpeta);

    TakesScreenshot ts = (TakesScreenshot) driver;
    File origen = ts.getScreenshotAs(OutputType.FILE);
    File destino = new File(rutaArchivo);

    try {
        FileHandler.copy(origen, destino);
        System.out.println("Captura guardada en: " + rutaArchivo);
    } catch (IOException e) {
        System.out.println("Error al guardar la captura: " + e.getMessage());
    }
}


    public static void crearCarpetaSiNoExiste(String rutaCarpeta) {
        File carpeta = new File(rutaCarpeta);
        if (!carpeta.exists()) {
            boolean creada = carpeta.mkdirs();
            if (creada) {
                System.out.println("Carpeta creada: " + rutaCarpeta);
            } else {
                System.out.println("No se pudo crear la carpeta: " + rutaCarpeta);
            }
        }
    }

}
