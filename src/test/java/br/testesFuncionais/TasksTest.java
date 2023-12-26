package br.testesFuncionais;

import org.junit.Assert;
import org.junit.Test;

import br.pages.TasksPages;

public class TasksTest {

	TasksPages page = new TasksPages();

	public void acessarAplicacao() {
		page.abrirNavegador("http://localhost:8001/tasks/");
	}

	@Test
	public void deveSalvarTaskComSucesso() throws InterruptedException {
		acessarAplicacao();

		page.clicarAddTodo();
		page.setTask("TarefaTest");
		page.setDataFutura(2);
		page.clicarSalvar();

		Assert.assertEquals("Success!", page.getValidaSucesso());

		page.fecharNavegador();
	}

	@Test
	public void deveValidarCriticaAoInserirTaskComDataPassada() throws InterruptedException {
		acessarAplicacao();

		page.clicarAddTodo();
		page.setTask("TarefaTest");
		page.setDataPassada(2);
		page.clicarSalvar();

		Assert.assertEquals("Due date must not be in past", page.getValidaSucesso());

		page.fecharNavegador();
	}

	@Test
	public void deveValidarCriticaAoInserirTaskSemDescricao() throws InterruptedException {
		acessarAplicacao();

		page.clicarAddTodo();
		page.dataAtualFormatadoComBarras();
		page.clicarSalvar();

		Assert.assertEquals("Fill the task description", page.getValidaSucesso());

		page.fecharNavegador();
	}

	@Test
	public void deveValidarCriticaAoInserirTaskSemData() throws InterruptedException {
		acessarAplicacao();

		page.clicarAddTodo();
		page.setTask("TarefaTest");
		page.clicarSalvar();

		Assert.assertEquals("Fill the due date", page.getValidaSucesso());

		page.fecharNavegador();
	}

}
