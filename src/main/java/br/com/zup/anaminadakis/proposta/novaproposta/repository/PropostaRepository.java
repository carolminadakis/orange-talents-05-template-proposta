package br.com.zup.anaminadakis.proposta.novaproposta.repository;

import br.com.zup.anaminadakis.proposta.novaproposta.model.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, Long> {
}