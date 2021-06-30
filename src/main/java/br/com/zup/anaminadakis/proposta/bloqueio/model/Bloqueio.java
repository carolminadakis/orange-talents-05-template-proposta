package br.com.zup.anaminadakis.proposta.bloqueio.model;

import br.com.zup.anaminadakis.proposta.cartao.model.Cartao;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Bloqueio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String ipAddress;
    @NotBlank
    private String userAgent;
    private LocalDateTime instante = LocalDateTime.now();

    @ManyToOne @NotNull
    Cartao cartao;

    @Deprecated
    public Bloqueio() {
    }

    public Bloqueio(String ipAddress, String userAgent, Cartao cartao) {
        this.ipAddress = ipAddress;
        this.userAgent = userAgent;
        this.cartao = cartao;
    }


    public Cartao getCartao() {
        return cartao;
    }
}
