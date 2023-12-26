package br.basePages;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BasePage {

	public WebDriver driver = new ChromeDriver();

	public void abrirNavegador(String urlNavegador) {
		driver.navigate().to(urlNavegador);
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
