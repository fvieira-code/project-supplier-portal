package br.com.supplier.portal.repository;

import br.com.supplier.portal.model.entity.AtividadeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtividadeRepository extends JpaRepository<AtividadeEntity, Integer> {

}


