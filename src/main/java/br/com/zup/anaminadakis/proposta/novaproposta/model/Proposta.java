package br.com.zup.anaminadakis.proposta.novaproposta.model;

import br.com.zup.anaminadakis.proposta.cartao.model.Cartao;
import br.com.zup.anaminadakis.proposta.novaproposta.dto.StatusProposta;
import br.com.zup.anaminadakis.proposta.seguranca.CriptografaDocumentos;
import br.com.zup.anaminadakis.proposta.validacoes.CPFouCNPJ;
import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @NotBlank
    @Column(nullable = false)
    private String email;

    @NotBlank
    @CPFouCNPJ
    @Convert(converter = CriptografaDocumentos.class)
    @Column(nullable = false)
    private String documento;

    @NotBlank
    @Column(nullable = false)
    private String endereco;

    @NotNull
    @Positive
    @Column(nullable = false)
    private BigDecimal salario;

    @Enumerated(EnumType.STRING)
    private StatusProposta statusProposta;

    @OneToOne
    private Cartao cartao;

    @Deprecated
    public Proposta() {
    }

    public Proposta(String nome, String email, String documento, String endereco, BigDecimal salario) {
        this.nome = nome;
        this.email = email;
        this.documento = documento;
        this.endereco = endereco;
        this.salario = salario;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getDocumento() {
        return documento;
    }

    public String getEndereco() {
        return endereco;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public StatusProposta getStatusProposta() {
        return statusProposta;
    }

    public Cartao getCartao() { return cartao; }

    public void setCartao(Cartao cartao) { this.cartao = cartao; }

    public void atualizaStatusProposta(StatusProposta restricao){

        this.statusProposta = restricao;
    }

    @Override
    public String toString() {
        return "Proposta{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", documento='" + documento + '\'' +
                ", endereco='" + endereco + '\'' +
                ", salario=" + salario +
                '}';
    }
}
