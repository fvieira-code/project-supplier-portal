package com.portal.supplierportal.repository;

import com.portal.supplierportal.model.Consultor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConsultorRepository extends JpaRepository<Consultor, Integer> {
    Optional<Consultor> findByCpf(String cpf);

}
