package br.com.zup.anaminadakis.proposta.novaproposta.controller.dto;

import br.com.zup.anaminadakis.proposta.novaproposta.dto.StatusProposta;
import br.com.zup.anaminadakis.proposta.novaproposta.model.Proposta;

public class PropostaDto {

    private String documento;
    private String email;
    private String nome;
    private StatusProposta statusProposta;


    public PropostaDto(Proposta proposta) {
        documento = proposta.getDocumento();
        email = proposta.getEmail();
        nome = proposta.getNome();
        statusProposta = proposta.getStatusProposta();
    }

    public String getDocumento() {
        return documento;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public StatusProposta getStatusProposta() {
        return statusProposta;
    }
}
