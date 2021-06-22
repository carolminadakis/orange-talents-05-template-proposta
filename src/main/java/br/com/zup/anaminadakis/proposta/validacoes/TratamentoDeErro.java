package br.com.zup.anaminadakis.proposta.validacoes;

public class TratamentoDeErro {


    private String campo;
    private String erro;

    public TratamentoDeErro(String campo, String erro) {
        this.campo = campo;
        this.erro = erro;
    }

    public String getCampo() {
        return campo;
    }

    public String getErro() {
        return erro;
    }
}
