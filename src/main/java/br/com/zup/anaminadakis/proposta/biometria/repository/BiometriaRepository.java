package br.com.zup.anaminadakis.proposta.biometria.repository;

import br.com.zup.anaminadakis.proposta.biometria.model.Biometria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BiometriaRepository extends JpaRepository<Biometria, Long> {
}
