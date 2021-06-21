package br.com.zup.anaminadakis.proposta.cartao.repository;

import br.com.zup.anaminadakis.proposta.cartao.model.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, Long> {
}
