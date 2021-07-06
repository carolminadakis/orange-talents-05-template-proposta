package br.com.zup.anaminadakis.proposta.carteira.request;

import br.com.zup.anaminadakis.proposta.cartao.model.Cartao;
import br.com.zup.anaminadakis.proposta.carteira.CarteiraTipo;
import br.com.zup.anaminadakis.proposta.carteira.model.Carteira;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CarteiraRequest {

    @Email @NotBlank
    private String email;

    @NotNull @Enumerated(EnumType.STRING)
    private CarteiraTipo carteira;

    public CarteiraRequest(String email, CarteiraTipo carteira) {
        this.email = email;
        this.carteira = carteira;
    }

    @Deprecated
    public CarteiraRequest() {
    }

    public String getEmail() {
        return email;
    }

    public CarteiraTipo getCarteira() {
        return carteira;
    }

    public Carteira converter(Cartao cartao) {
        return new Carteira(this.email, cartao, carteira);
    }
}
