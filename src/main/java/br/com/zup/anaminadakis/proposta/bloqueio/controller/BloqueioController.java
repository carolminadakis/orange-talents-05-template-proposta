package br.com.zup.anaminadakis.proposta.bloqueio.controller;

import br.com.zup.anaminadakis.proposta.cartao.model.Cartao;
import br.com.zup.anaminadakis.proposta.cartao.repository.CartaoRepository;
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


    @PostMapping("/{id}")
    public ResponseEntity<?> bloqueiaCartao(@PathVariable Long id, HttpServletRequest http) {

        Optional<Cartao> possivelCartao = cartaoRepository.findById(id);

        if (possivelCartao.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Cartao cartao = possivelCartao.get();

        if (cartao.estaBloqueado()) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }

        String ipAddress = http.getRemoteAddr();
        String userAgent = http.getHeader("User-Agent");
        cartao.bloqueia(ipAddress, userAgent);
        cartaoRepository.save(cartao);

        return ResponseEntity.status(HttpStatus.OK).body("Cartão bloqueado com sucesso!");
    }

}
