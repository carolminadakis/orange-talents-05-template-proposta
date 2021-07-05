package br.com.zup.anaminadakis.proposta.viagem.repository;

import br.com.zup.anaminadakis.proposta.viagem.model.Viagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViagemRepository extends JpaRepository<Viagem, Long> {
}
