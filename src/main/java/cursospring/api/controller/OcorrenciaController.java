package cursospring.api.controller;

import cursospring.api.input.OcorrenciaInput;
import cursospring.api.model.OcorrenciaModel;
import cursospring.assembler.OcorrenciaAssembler;
import cursospring.domain.model.Entrega;
import cursospring.domain.model.Ocorrencia;
import cursospring.domain.service.BuscaEntregaService;
import cursospring.domain.service.RegistroOcorrenciaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas/{entregaId}/ocorrencias")
public class OcorrenciaController {

    private RegistroOcorrenciaService registroOcorrenciaService;
    private OcorrenciaAssembler ocorrenciaAssembler;
    private BuscaEntregaService buscaEntregaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OcorrenciaModel registrar(@PathVariable Long entregaId, @Valid @RequestBody OcorrenciaInput ocorrenciaInput){
        Ocorrencia ocorrencia = registroOcorrenciaService.registrar(entregaId, ocorrenciaInput.getDescricao());

        return ocorrenciaAssembler.toModel(ocorrencia);
    }

    @GetMapping
    public List<OcorrenciaModel> listar(@PathVariable Long entregaId){
        Entrega entrega = buscaEntregaService.buscar(entregaId);

        return ocorrenciaAssembler.toCollectionModel(entrega.getOcorrencias());
    }
}
