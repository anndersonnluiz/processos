package br.com.processos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.processos.model.Processos;
import br.com.processos.repository.ProcessosRepository;

@Controller
public class ProcessosController {
	
	@Autowired
	private ProcessosRepository processosRepository;
	
	
	@ResponseBody
	@RequestMapping("/listarProcessos")
	public List<Processos> listar(){
		return processosRepository.findAll();
	}
	
	@ResponseBody
	@Transactional
	@RequestMapping(path = "/cadastrarProcessos", method = RequestMethod.POST)
	public String salvar(@RequestBody Processos processos) {
        if (processos.getNumProcessos() != null && !processos.getNumProcessos().isEmpty()) {
            Processos proceOptional = processosRepository.findByNumProcessos(processos.getNumProcessos());
            if (proceOptional != null) {
                return "Processo já cadastrado: " + proceOptional.getNumProcessos();
            }else{
                processosRepository.save(processos);
		        return "Processo cadastrado com Sucesso!";
            }
        }else{
            return "Informe o Número do processo!";
        }
	}

    @ResponseBody
	@Transactional
	@RequestMapping(path = "/consultarProcessos", method = RequestMethod.GET)
	public String consultar(@RequestParam String numProcessos) {
		Processos processos = processosRepository.findByNumProcessos(numProcessos);
        if(processos != null && processos.getIdProcessos() > 0){
            return "Número do processo cadastrado: " + processos.getNumProcessos();
        }
		return "Sem Dados cadastrados";
	}


    @ResponseBody
	@Transactional
	@RequestMapping(path = "/cadastrarReu", method = RequestMethod.PUT)
	public String salvarReu(@RequestBody Processos processos) {
        if (processos.getNumProcessos() != null && !processos.getNumProcessos().isEmpty()) {
            Processos proceOptional = processosRepository.findByNumProcessos(processos.getNumProcessos());
            if (proceOptional != null) {
                Processos processosAlteracao = proceOptional;
                processosAlteracao.setReu(processos.getReu());
                processosRepository.save(processosAlteracao);
                return "Cadastro de Réu feito com sucesso do processo: " + processosAlteracao.getNumProcessos();
            }else{
                return "Nenhum Processo encontrado com este ID";
            }
        }else{
            return "Informe o Número do processo!";
        }
	}


    @ResponseBody
	@Transactional
	@RequestMapping(path = "/excluirProcesso/{numProcessos}", method = RequestMethod.DELETE)
	public String excluirProcesso(@PathVariable String numProcessos) {
        Processos proceOptional = processosRepository.findByNumProcessos(numProcessos);
        if (proceOptional != null && proceOptional.getIdProcessos() > 0) {
            processosRepository.deleteById(proceOptional.getIdProcessos().intValue());
            return "Excluido com sucesso!";
        }else{
            return "Nenhum Processo encontrado com este ID";
        }
	}

}
