package br.com.zup.anaminadakis.proposta.novaproposta.controller;

import br.com.zup.anaminadakis.proposta.novaproposta.controller.dto.PropostaDto;
import br.com.zup.anaminadakis.proposta.novaproposta.controller.request.AnalisePropostaRequest;
import br.com.zup.anaminadakis.proposta.novaproposta.controller.request.PropostaRequest;
import br.com.zup.anaminadakis.proposta.novaproposta.dto.AnalisaPropostaDto;
import br.com.zup.anaminadakis.proposta.novaproposta.dto.StatusProposta;
import br.com.zup.anaminadakis.proposta.novaproposta.metricas.MetricasPropostas;
import br.com.zup.anaminadakis.proposta.novaproposta.model.Proposta;
import br.com.zup.anaminadakis.proposta.novaproposta.repository.PropostaRepository;
import br.com.zup.anaminadakis.proposta.novaproposta.swagger.ConsultaSwagger;
import br.com.zup.anaminadakis.proposta.validacoes.ApiErroException;
import br.com.zup.anaminadakis.proposta.validacoes.TratamentoDeErro;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/propostas")
public class PropostaController {

    @Autowired
    PropostaRepository propostaRepository;

    @Autowired
    ConsultaSwagger consultaSwagger;

    @Autowired
    MetricasPropostas metricasPropostas;

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
        metricasPropostas.incrementa();

        //analisa a proposta para torná-la elegível ou não, em caso de restrições
        AnalisePropostaRequest analisePropostaRequest = new AnalisePropostaRequest(proposta.getDocumento(),
                proposta.getNome(), proposta.getId());
        try{
            AnalisaPropostaDto analisaPropostaDto = consultaSwagger.analiseProposta(analisePropostaRequest);
            proposta.atualizaStatusProposta(StatusProposta.ELEGIVEL);
        }catch (FeignException.UnprocessableEntity ex) {
            proposta.atualizaStatusProposta(StatusProposta.NAO_ELEGIVEL);
        }
        propostaRepository.save(proposta);


        URI uri = uriBuilder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri();
        return ResponseEntity.created(uri).build();

    }

    //Verifica se existe documento no banco de dados
    private boolean documentoExistente(String documento) {
        return propostaRepository.findByDocumento(documento).isPresent();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> acompanhamentoProposta(@PathVariable Long id) {
        Optional<Proposta> proposta = propostaRepository.findById(id);

        if (proposta.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body(new TratamentoDeErro("Proposta id", "ID de proposta inválido!"));
        }
        return ResponseEntity.ok(new PropostaDto(proposta.get()));
    }
}
