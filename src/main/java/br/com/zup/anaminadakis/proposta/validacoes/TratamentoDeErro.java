package br.com.zup.anaminadakis.proposta.validacoes;

import java.util.Collection;

public class TratamentoDeErro {


    private Collection<String> mensagens;

    public TratamentoDeErro(Collection<String> mensagens) {
        this.mensagens = mensagens;
    }

    public Collection<String> getMensagens() {
        return mensagens;
    }
}
