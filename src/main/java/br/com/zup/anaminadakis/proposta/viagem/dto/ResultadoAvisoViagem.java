package br.com.zup.anaminadakis.proposta.viagem.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

public class ResultadoAvisoViagem {

    private String resultado;

    @JsonCreator
    public ResultadoAvisoViagem(String resultado) {
        this.resultado = resultado;
    }

    public String getResultado() {
        return resultado;
    }
}
