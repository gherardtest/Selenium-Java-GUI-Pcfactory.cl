package Tests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class test {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\Chrome\\ChromeDriver-110.0.5481.77\\chromedriver.exe");

        //1. Entrar en pc Factory: www.pcfactory.cl
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.pcfactory.cl");

        // 2. Ingresar en el buscador "Notebook Gamer Nitro 5 Intel i5-11400H NVIDIA GTX 1650 15.6" FHD 144Hz 16GB RAM 512GB SSD Windows 11"
        WebElement buscador = driver.findElement(By.xpath("//*[@id=\"searchalgolia\"]/div/div/div/div/div/form/div/input"));
        buscador.sendKeys("Notebook Gamer Nitro 5 Intel i5-11400H NVIDIA GTX 1650 15.6 FHD 144Hz 16GB RAM 512GB SSD Windows 11\n");

        //3. Seleccionar un elemento de los listados y agregalo al carro de compras
        List<WebElement> products = driver.findElements(By.xpath("//*[@id=\"productsalgolia\"]/div/div/div[2]/div[2]/div"));
        if (products.size() > 0) {
            System.out.println("Cantidad de Productos encontrados: "+products.size());
            //Seleccionar el producto
            WebElement firstProduct = products.get(0);
            //Identificador Página Principal
            String parentwindowhandle = driver.getWindowHandle();
            System.out.println("ID HomePage: "+parentwindowhandle);
            //Presionar Botón agragar a carrito
            WebElement btnAdd = firstProduct.findElement(By.id("addtocart_47689_1"));
            driver.manage().timeouts().implicitlyWait(9000, TimeUnit.SECONDS);
            btnAdd.click();
            driver.manage().timeouts().implicitlyWait(9000, TimeUnit.SECONDS);
            // Verificar que hay al menos dos ventanas abiertas
            Set<String> handles = driver.getWindowHandles();
            if (handles.size() < 2) {
                System.out.println("No se encontró ninguna ventana emergente");
            } else {
                // Cambiar a la ventana emergente
                Iterator<String> it = handles.iterator();
                String parentWindowId = it.next();
                String popupWindowId = it.next();
                driver.switchTo().window(popupWindowId);
                // Imprimir el id de la ventana emergente
                System.out.println("ID Popup: " + popupWindowId);
            }
        }else {
            System.out.println("No se encontraron productos");
        }
        //driver.close();
    }
}