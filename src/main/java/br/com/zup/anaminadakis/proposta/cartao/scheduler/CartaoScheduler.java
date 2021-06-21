package br.com.zup.anaminadakis.proposta.cartao.scheduler;

import br.com.zup.anaminadakis.proposta.cartao.feign.CartaoFeign;
import br.com.zup.anaminadakis.proposta.cartao.model.Cartao;
import br.com.zup.anaminadakis.proposta.cartao.repository.CartaoRepository;
import br.com.zup.anaminadakis.proposta.cartao.request.CartaoRequest;
import br.com.zup.anaminadakis.proposta.novaproposta.dto.StatusProposta;
import br.com.zup.anaminadakis.proposta.novaproposta.model.Proposta;
import br.com.zup.anaminadakis.proposta.novaproposta.repository.PropostaRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CartaoScheduler {

    @Autowired
    CartaoRepository cartaoRepository;

    @Autowired
    PropostaRepository propostaRepository;

    @Autowired
    CartaoFeign cartaoFeign;

    @Scheduled(fixedDelay = 1000 * 60)
    public void associaCartaoProposta() {
        List<Proposta> propostas = propostaRepository.findByStatusDeCartaoElegivel(StatusProposta.ELEGIVEL);
        propostas.forEach(System.out::println);

        for (Proposta proposta : propostas) {
            try{
                CartaoRequest cartaoRequest = cartaoFeign.buscarCartao(String.valueOf(proposta.getId()));
                Cartao cartao = cartaoRequest.toModel(propostaRepository);

                cartaoRepository.save(cartao);

                proposta.setCartao(cartao);

                propostaRepository.save(proposta);
                System.out.println("Proposta de documento " + proposta.getDocumento()
                        + " e cartao "+ cartaoRequest.getId() +" criados com sucesso.");
            } catch (FeignException ex) {
                ex.printStackTrace();
            }
        }
    }
}
