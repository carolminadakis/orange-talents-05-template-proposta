package br.com.zup.anaminadakis.proposta.carteira.controller;

import br.com.zup.anaminadakis.proposta.cartao.feign.CartaoFeign;
import br.com.zup.anaminadakis.proposta.cartao.model.Cartao;
import br.com.zup.anaminadakis.proposta.cartao.repository.CartaoRepository;
import br.com.zup.anaminadakis.proposta.carteira.dto.ResultadoCarteira;
import br.com.zup.anaminadakis.proposta.carteira.model.Carteira;
import br.com.zup.anaminadakis.proposta.carteira.repository.CarteiraRepository;
import br.com.zup.anaminadakis.proposta.carteira.request.CarteiraRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/carteira")
public class CarteiraController {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private CarteiraRepository carteiraRepository;

    @Autowired
    CartaoFeign cartaoFeign;

    @PostMapping("{numeroCartao}")
    public ResponseEntity<?> associaCarteira(@PathVariable String numeroCartao,
                                             @RequestBody @Valid CarteiraRequest request,
                                             UriComponentsBuilder builder) {

        Optional<Cartao> associarCartao = cartaoRepository.findByNumeroCartao(numeroCartao);

        if (associarCartao.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Cartao cartao = associarCartao.get();

        try {

            ResultadoCarteira resultadoCarteira = cartaoFeign.associaCarteira(cartao.getNumeroCartao(),
                                                                new CarteiraRequest(request.getEmail(),
                                                                                    request.getCarteira()));
            System.out.println(resultadoCarteira.getResultado());

            Carteira carteira = request.converter(cartao);
            carteiraRepository.save(carteira);
            URI uri = builder.path("/carteiras/{id}/{idCarteira}").buildAndExpand(cartao.getId(), carteira.getId()).toUri();
            return ResponseEntity.created(uri).build();

        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().body("Não foi possível fazer a associação do cartão");
        }
    }
}
