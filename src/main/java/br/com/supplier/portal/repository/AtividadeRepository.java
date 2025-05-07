package br.com.supplier.portal.repository;

import br.com.supplier.portal.model.entity.AtividadeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AtividadeRepository extends JpaRepository<AtividadeEntity, Integer> {

    //List<AtividadeEntity> listarPorStatus(StatusAtividade status);
    //List<AtividadeEntity> listarPorDescricao(String descricao);
    List<AtividadeEntity> findByDescricaoAtividade(String descricao);
}


