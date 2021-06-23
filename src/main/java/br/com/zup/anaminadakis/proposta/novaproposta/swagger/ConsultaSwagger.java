package br.com.zup.anaminadakis.proposta.novaproposta.swagger;

import br.com.zup.anaminadakis.proposta.novaproposta.controller.request.AnalisePropostaRequest;
import br.com.zup.anaminadakis.proposta.novaproposta.dto.AnalisaPropostaDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(url = "${proposta.analise-financeira.host}", name = "consultaSwagger")
public interface ConsultaSwagger {

    @RequestMapping(value = "${proposta.analise-financeira.solicitacao}", method = RequestMethod.POST, consumes = "application/json")
    AnalisaPropostaDto analiseProposta(AnalisePropostaRequest analisePropostaRequest);
}
