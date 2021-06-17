package br.com.zup.anaminadakis.proposta.novaproposta.controller;

import br.com.zup.anaminadakis.proposta.novaproposta.controller.request.PropostaRequest;
import br.com.zup.anaminadakis.proposta.novaproposta.model.Proposta;
import br.com.zup.anaminadakis.proposta.novaproposta.repository.PropostaRepository;
import br.com.zup.anaminadakis.proposta.validacoes.ApiErroException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/propostas")
public class PropostaController {

    @Autowired
    PropostaRepository propostaRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<PropostaRequest> cadastro(@RequestBody @Valid PropostaRequest propostaRequest, UriComponentsBuilder uriBuilder) {
    //caso o documento exista, vai jogar uma exceção
        if (documentoExistente(propostaRequest.getDocumento())) {
            throw new ApiErroException(HttpStatus.UNPROCESSABLE_ENTITY, "Não é permitido mais de uma proposta por CPF/CNPJ");
        }
        //senão irá salvar no banco de dados
        Proposta proposta = propostaRequest.converte();
        propostaRepository.save(proposta);


        URI uri = uriBuilder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri();
        return ResponseEntity.created(uri).build();

    }

    //Verifica se existe documento no banco de dados
    private boolean documentoExistente(String documento) {
        return propostaRepository.findByDocumento(documento).isPresent();
    }

}
