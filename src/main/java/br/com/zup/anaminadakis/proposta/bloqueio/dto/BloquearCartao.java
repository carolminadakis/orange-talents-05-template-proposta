package br.com.zup.anaminadakis.proposta.bloqueio.dto;

import br.com.zup.anaminadakis.proposta.bloqueio.request.BloqueioRequest;
import br.com.zup.anaminadakis.proposta.cartao.feign.CartaoFeign;
import br.com.zup.anaminadakis.proposta.cartao.model.Cartao;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class BloquearCartao {

    @Autowired
    CartaoFeign cartaoFeign;

    public void bloquear(Cartao cartao, HttpServletRequest request) throws FeignException {
        cartaoFeign.notificaSistema(cartao.getNumeroCartao(), new BloqueioRequest("proposta"));
    }

}
