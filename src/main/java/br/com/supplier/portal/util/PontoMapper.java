package br.com.supplier.portal.util;

import br.com.supplier.portal.model.dto.PontoDto;
import br.com.supplier.portal.model.entity.PontoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface PontoMapper {
    @Mapping(source = "idPonto", target = "idPonto")
    PontoEntity toModel(PontoDto pontoDto);

    PontoDto toDTO(PontoEntity pontoEntity);
}
