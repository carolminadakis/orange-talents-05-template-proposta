package br.com.zup.anaminadakis.proposta.cartao.feign;


import br.com.zup.anaminadakis.proposta.bloqueio.dto.InformaStatusCartao;
import br.com.zup.anaminadakis.proposta.bloqueio.request.BloqueioRequest;
import br.com.zup.anaminadakis.proposta.cartao.request.CartaoRequest;
import br.com.zup.anaminadakis.proposta.viagem.dto.ResultadoAvisoViagem;
import br.com.zup.anaminadakis.proposta.viagem.request.ViagemRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "cartao", url = "${proposta.cartao.host}")
public interface CartaoFeign {


    @GetMapping("${proposta.cartoes}")
    CartaoRequest buscarCartao(@RequestParam(name = "idProposta") String idProposta);

//essa variável de ambiente busca no properties
    @PostMapping("${cartao.bloqueio}")
    InformaStatusCartao notificaSistema(@PathVariable String id, @RequestBody BloqueioRequest bloqueioRequest);

    @PostMapping("${avisos.cartoes}")
    public ResultadoAvisoViagem notificaViagem(@PathVariable String id, @RequestBody ViagemRequest request);
}
