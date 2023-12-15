package br.com.processos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.processos.controller.ProcessosController;
import br.com.processos.model.Processos;

@SpringBootTest
class ProcessosApplicationTests {

	@Autowired
	ProcessosController processosController;

	@Test
	void cadastrarProcessosComSucesso() {
		Processos processos = new Processos();
		processos.setNumProcessos("123M123");
		assertEquals(true, processos.getIdProcessos() == null);
		processosController.salvar(processos);
		assertEquals(true, processos.getIdProcessos() > 0);
		assertEquals("123M123", processos.getNumProcessos());
	}

	@Test
	void cadastrarProcessosSemNumProcesso() {
		Processos processos = new Processos();
		assertEquals("Informe o Número do processo!", 
		processosController.salvar(processos));
	}

	@Test
	void cadastrarProcessosProcessoCadastrado() {
		Processos processos = new Processos();
		processos.setNumProcessos("123M123");
		assertEquals("Processo já cadastrado: " + processos.getNumProcessos() + "", 
		processosController.salvar(processos));
	}

	@Test
	void consultarProcessosComSucesso(){
		Processos processos = new Processos();
		processos.setNumProcessos("123M123");
		assertEquals("Número do processo cadastrado: " + processos.getNumProcessos() + "", processosController.consultar(processos.getNumProcessos()));
	}

	@Test
	void consultarProcessosSemProcesso(){
		Processos processos = new Processos();
		processos.setNumProcessos("000000");
		assertEquals("Sem Dados cadastrados", processosController.consultar(processos.getNumProcessos()));
	}


	@Test
	void cadastrarReuComSucesso(){
		Processos processos = new Processos();
		processos.setNumProcessos("123M123");
		processos.setReu("Reu Teste");
		assertEquals("Cadastro de Réu feito com sucesso do processo: " + processos.getNumProcessos()
		 + "", processosController.salvarReu(processos));
	}


	@Test
	void cadastrarReuSemProcesso(){
		Processos processos = new Processos();
		processos.setNumProcessos("00000");
		processos.setReu("Reu Teste");
		assertEquals("Nenhum Processo encontrado com este ID", processosController.salvarReu(processos));
	}

	@Test
	void cadastrarReuSemNumProcesso(){
		Processos processos = new Processos();
		processos.setReu("Reu Teste");
		assertEquals("Informe o Número do processo!", processosController.salvarReu(processos));
	}

	@Test
	void excluirProcessoSemProcesso(){
		Processos processos = new Processos();
		processos.setNumProcessos("00000");
		assertEquals("Nenhum Processo encontrado com este ID", processosController.excluirProcesso(processos.getNumProcessos()));
	}

	@Test
	void excluirProcessoComSucesso(){
		Processos processos = new Processos();
		processos.setNumProcessos("123M123");
		assertEquals("Excluido com sucesso!", processosController.excluirProcesso(processos.getNumProcessos()));
	}

}
