package br.com.zup.anaminadakis.proposta.viagem.controller;

import br.com.zup.anaminadakis.proposta.cartao.feign.CartaoFeign;
import br.com.zup.anaminadakis.proposta.cartao.model.Cartao;
import br.com.zup.anaminadakis.proposta.cartao.repository.CartaoRepository;
import br.com.zup.anaminadakis.proposta.viagem.dto.ResultadoAvisoViagem;
import br.com.zup.anaminadakis.proposta.viagem.model.Viagem;
import br.com.zup.anaminadakis.proposta.viagem.repository.ViagemRepository;
import br.com.zup.anaminadakis.proposta.viagem.request.ViagemRequest;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/viagem")
public class ViagemController {

    @Autowired
    CartaoRepository cartaoRepository;

    @Autowired
    ViagemRepository viagemRepository;

    @Autowired
    CartaoFeign cartaoFeign;

    @PostMapping("/{cartao}")
    public ResponseEntity<?> cadastraAvisoDeViagem(@PathVariable String cartao, @RequestBody @Valid ViagemRequest viagemRequest,
                                                   HttpServletRequest http) {

        Optional<Cartao> cartaoViagem = cartaoRepository.findByNumeroCartao(cartao);

        if (cartaoViagem.isEmpty()) {
            return ResponseEntity.status(404).body("Cartão não encontrado");
        }
        Cartao verificaCartao = cartaoViagem.get();
        if (verificaCartao.estaBloqueado()) {
            return ResponseEntity.status(422).body("Cartão bloqueado");
        }

        String ipClient = http.getRemoteAddr();
        String userAgent = http.getHeader("User-Agent");

        try {
           ResultadoAvisoViagem notificacao = cartaoFeign.notificaViagem(cartao, new ViagemRequest(viagemRequest.getDestino(), viagemRequest.getValidoAte()));
            System.out.println(notificacao.getResultado());
            Viagem avisoViagem = viagemRequest.converte(ipClient, userAgent, verificaCartao);
            viagemRepository.save(avisoViagem);
            return ResponseEntity.status(200).build();

        } catch ( FeignException error) {
            error.printStackTrace();
            return ResponseEntity.status(422).body("Não foi possível realizar a notificação de viagem.");
        }
    }
}
