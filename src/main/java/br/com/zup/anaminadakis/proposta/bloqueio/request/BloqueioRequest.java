package br.com.zup.anaminadakis.proposta.bloqueio.request;

import com.fasterxml.jackson.annotation.JsonCreator;

public class BloqueioRequest {

    private String sistemaResponsavel;

    @JsonCreator
    public BloqueioRequest(String sistemaResponsavel) {
        this.sistemaResponsavel = sistemaResponsavel;
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }
}
