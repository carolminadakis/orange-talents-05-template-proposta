package br.com.zup.anaminadakis.proposta.cartao.request;

import br.com.zup.anaminadakis.proposta.cartao.model.Cartao;
import br.com.zup.anaminadakis.proposta.novaproposta.model.Proposta;
import br.com.zup.anaminadakis.proposta.novaproposta.repository.PropostaRepository;

import java.time.LocalDateTime;

public class CartaoRequest {

    private String id;
    private String titular;
    private LocalDateTime emitidoEm;
    private Integer limite;
    private Long idProposta;

    public String getId() {
        return id;
    }

    public String getTitular() {
        return titular;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }

    public Integer getLimite() {
        return limite;
    }

    public Long getIdProposta() {
        return idProposta;
    }

    public Cartao toModel(PropostaRepository propostaRepository) {
        Proposta proposta = propostaRepository.findById(idProposta).get();
        return new Cartao(id, emitidoEm, titular, limite, proposta);
    }
}
