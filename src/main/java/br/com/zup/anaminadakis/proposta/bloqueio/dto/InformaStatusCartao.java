package br.com.zup.anaminadakis.proposta.bloqueio.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

public class InformaStatusCartao {

    private String resultado;

    @JsonCreator
    public InformaStatusCartao(String resultado) {
        this.resultado = resultado;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
}
