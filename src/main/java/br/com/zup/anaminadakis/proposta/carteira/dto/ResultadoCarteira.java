package br.com.zup.anaminadakis.proposta.carteira.dto;

public class ResultadoCarteira {

    private String resultado;
    private String id;


    public ResultadoCarteira(String resultado, String id) {
        this.resultado = resultado;
        this.id = id;
    }

    @Deprecated
    public ResultadoCarteira() {
    }

    public String getResultado() {
        return resultado;
    }

    public String getId() {
        return id;
    }
}
