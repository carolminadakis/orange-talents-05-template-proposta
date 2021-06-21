package br.com.zup.anaminadakis.proposta.novaproposta.dto;

import br.com.zup.anaminadakis.proposta.novaproposta.model.Proposta;

//AnaliseProposta
public class AnalisaPropostaDto {

    private String documento;
    private String nome;
    private Long idProposta;
    private ResultadoSolicitacao resultadoSolicitacao;

    public AnalisaPropostaDto(Proposta proposta) {
        this.documento = proposta.getDocumento();
        this.nome = proposta.getNome();
        this.idProposta = proposta.getId();
    }

    public AnalisaPropostaDto(String documento, String nome, Long idProposta, ResultadoSolicitacao resultadoSolicitacao) {
        this.documento = documento;
        this.nome = nome;
        this.idProposta = idProposta;
        this.resultadoSolicitacao = resultadoSolicitacao;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public Long getIdProposta() {
        return idProposta;
    }

    public ResultadoSolicitacao getResultadoSolicitacao() {
        return resultadoSolicitacao;
    }

    @Override
    public String toString() {
        return "AnalisaPropostaDto{" +
                "documento='" + documento + '\'' +
                ", nome='" + nome + '\'' +
                ", idProposta=" + idProposta +
                ", resultadoSolicitacao=" + resultadoSolicitacao +
                '}';
    }
}
