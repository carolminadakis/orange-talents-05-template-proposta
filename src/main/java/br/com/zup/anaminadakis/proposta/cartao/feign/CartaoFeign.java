package br.com.zup.anaminadakis.proposta.cartao.feign;

import br.com.zup.anaminadakis.proposta.cartao.request.CartaoRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "cartao", url = "${proposta.cartao.host}")
public interface CartaoFeign {


    @GetMapping("${proposta.cartoes}")
    CartaoRequest buscarCartao(@RequestParam(name = "idProposta") String idProposta);
}
