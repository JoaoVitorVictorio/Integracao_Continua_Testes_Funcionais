package br.pages;

import java.net.MalformedURLException;
import java.util.Date;

import org.junit.Before;
import org.openqa.selenium.By;

import br.basePages.BasePage;

public class TasksPages extends BasePage {

    @Before
    public void setUp() throws MalformedURLException {
        configurarDriver();
    }

    public void clicarAddTodo() throws InterruptedException {
        delay(2);
        clicar(By.id("addTodo"));
    }

    public void setTask(String task) {
        escrever(By.id("task"), task);
    }

    public void setDataAtual() {
        escrever(By.id("dueDate"), dataAtual());
    }
    
    public void clicarSalvar() {
        clicar(By.id("saveButton"));
    }

    public void setDataFutura(int quantidadedias) {
        escrever(By.id("dueDate"), adicionarDiasFormatadoComBarras(new Date(), +quantidadedias));
    }

    public void setDataPassada(int quantidadedias) {
        escrever(By.id("dueDate"), adicionarDiasFormatadoComBarras(new Date(), -quantidadedias));
    }

    public String getValidaSucesso() {
        return obterTexto(By.id("message"));
    }
}
