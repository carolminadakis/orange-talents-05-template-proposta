package br.com.zup.anaminadakis.proposta.carteira.repository;

import br.com.zup.anaminadakis.proposta.carteira.model.Carteira;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarteiraRepository extends JpaRepository<Carteira, Long> {
}
