package br.com.zup.anaminadakis.proposta.bloqueio.controller;

import br.com.zup.anaminadakis.proposta.bloqueio.request.BloqueioRequest;
import br.com.zup.anaminadakis.proposta.cartao.feign.CartaoFeign;
import br.com.zup.anaminadakis.proposta.cartao.model.Cartao;
import br.com.zup.anaminadakis.proposta.cartao.repository.CartaoRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@RequestMapping("/bloqueios")
public class BloqueioController {

    @Autowired
    CartaoRepository cartaoRepository;

    @Autowired
    CartaoFeign cartaoFeign;


    @PostMapping("/{id}")
    public ResponseEntity<?> bloqueiaCartao(@PathVariable("id") String id, HttpServletRequest http) {

        Optional<Cartao> possivelCartao = cartaoRepository.findByNumeroCartao(id);

        if (possivelCartao.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Cartao cartao = possivelCartao.get();

        if (cartao.estaBloqueado()) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
            String ipAddress = http.getRemoteAddr();
            String userAgent = http.getHeader("User-Agent");

        try {
            cartaoFeign.notificaSistema(cartao.getNumeroCartao(), new BloqueioRequest("proposta"));
            cartao.bloqueia(ipAddress, userAgent);
            cartaoRepository.save(cartao);
            return ResponseEntity.status(HttpStatus.OK).build();

        } catch (FeignException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
    }
}
