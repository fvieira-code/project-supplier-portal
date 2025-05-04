package br.com.supplier.portal.repository;

import br.com.supplier.portal.model.entity.ConsultorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultorRepository extends JpaRepository<ConsultorEntity, Integer> {
}


