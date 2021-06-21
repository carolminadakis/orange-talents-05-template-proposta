package br.com.zup.anaminadakis.proposta.novaproposta.repository;

import br.com.zup.anaminadakis.proposta.novaproposta.dto.StatusProposta;
import br.com.zup.anaminadakis.proposta.novaproposta.model.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, Long> {

    Optional<Proposta> findByDocumento(String documento);

    @Query("select p from Proposta p where p.statusProposta = :status and p.cartao is null")
    List<Proposta> findByStatusDeCartaoElegivel(StatusProposta status);
}
