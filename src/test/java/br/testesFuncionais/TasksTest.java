package br.testesFuncionais;

import java.net.MalformedURLException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.pages.TasksPages;

public class TasksTest {

    private TasksPages page;

    @Before
    public void setUp() throws InterruptedException, MalformedURLException {
        page = new TasksPages();
        page.configurarDriver();
        page.abrirNavegador("http://192.168.56.1:8001/tasks/");
    }

    @Test
    public void deveSalvarTaskComSucesso() throws InterruptedException {
        page.clicarAddTodo();
        page.setTask("TarefaTest");
        page.setDataFutura(2);
        page.clicarSalvar();

        Assert.assertEquals("Success!", page.getValidaSucesso());
    }

    @Test
    public void deveValidarCriticaAoInserirTaskComDataPassada() throws InterruptedException {
        page.clicarAddTodo();
        page.setTask("TarefaTest");
        page.setDataPassada(2);
        page.clicarSalvar();

        Assert.assertEquals("Due date must not be in past", page.getValidaSucesso());
    }

    @Test
    public void deveValidarCriticaAoInserirTaskSemDescricao() throws InterruptedException {
        page.clicarAddTodo();
        page.dataAtualFormatadoComBarras();
        page.clicarSalvar();

        Assert.assertEquals("Fill the task description", page.getValidaSucesso());
    }

    @Test
    public void deveValidarCriticaAoInserirTaskSemData() throws InterruptedException {
        page.clicarAddTodo();
        page.setTask("TarefaTest");
        page.clicarSalvar();

        Assert.assertEquals("Fill the due date", page.getValidaSucesso());
    }

    @After
    public void tearDown() {
        page.fecharNavegador();
    }
}
