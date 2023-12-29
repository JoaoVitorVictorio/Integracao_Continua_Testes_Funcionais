package br.basePages;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    public WebDriver driver;

    public void configurarDriver() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        String gridUrl = "http://192.168.56.1:4444/wd/hub";

        this.driver = new RemoteWebDriver(new URL(gridUrl), options);
    }

    public void abrirNavegador(String urlNavegador) {
        driver.navigate().to(urlNavegador);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete';"));
    }

    public void fecharNavegador() {
        driver.quit();
    }

    public void delay(int segundos) throws InterruptedException {
        Thread.sleep(segundos * 1000);
    }

    public void clicar(By by) {
        driver.findElement(by).click();
    }

    public void escrever(By by, String texto, Boolean apagar) {
        if (apagar) {
            int tamanho = driver.findElement(by).getAttribute("value").length();

            for (int i = 0; i < tamanho; i++) {
                driver.findElement(by).sendKeys(Keys.BACK_SPACE);
            }
            driver.findElement(by).sendKeys(Keys.BACK_SPACE);
            driver.findElement(by).sendKeys(Keys.HOME);
        }

        driver.findElement(by).sendKeys(texto);
    }

    public void escrever(By by, String texto) {
        escrever(by, texto, false);
    }

    public String dataAtual() {
        String data = new SimpleDateFormat("ddMMyyyy").format(new Date());
        return data;
    }

    public String dataAtualFormatadoComBarras() {
        String data = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        return data;
    }

    public String adicionarDiasFormatado(Date data, int diferencaDias) {
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(data);
        calendario.add(Calendar.DATE, diferencaDias);
        return new SimpleDateFormat("ddMMyyyy").format((Date) calendario.getTime());
    }

    public String adicionarDiasFormatadoComBarras(Date data, int diferencaDias) {
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(data);
        calendario.add(Calendar.DATE, diferencaDias);
        return new SimpleDateFormat("dd/MM/yyyy").format((Date) calendario.getTime());
    }

    public String obterTexto(By by) {
        return driver.findElement(by).getText();
    }
}
