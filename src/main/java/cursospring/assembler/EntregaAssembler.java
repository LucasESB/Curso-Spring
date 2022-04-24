package cursospring.assembler;

import cursospring.api.input.EntregaInput;
import cursospring.api.model.EntregaModel;
import cursospring.domain.model.Entrega;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class EntregaAssembler {
    //A classe serve para centralizar as conversões de uma classe model para uma DTO, para que nm futuro caso eu troque de biblioteca de precise mudar em todas as classes

    private ModelMapper modelMapper; // serve para realizar os mapeamentos de objetos e conversão de uma camada para outra

    public EntregaModel toModel(Entrega entrega) {
        return modelMapper.map(entrega, EntregaModel.class);
    }

    public List<EntregaModel> toCollectionModel(List<Entrega> listEntregas) {
        return listEntregas.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public Entrega toEntity(EntregaInput entregaInput) {
        return modelMapper.map(entregaInput, Entrega.class);
    }
}
