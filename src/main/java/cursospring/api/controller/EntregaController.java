package cursospring.api.controller;

import cursospring.api.input.EntregaInput;
import cursospring.api.model.EntregaModel;
import cursospring.assembler.EntregaAssembler;
import cursospring.domain.model.Entrega;
import cursospring.domain.repository.EntregaRepository;
import cursospring.domain.service.SolicitaEntregaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
public class EntregaController {

    private EntregaRepository entregaRepository;
    private SolicitaEntregaService solicitaEntregaService;
    private EntregaAssembler entregaAssembler; // serve para realizar os mapeamentos de objetos e conversão de uma camada para outra

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntregaModel solicitar(@Valid @RequestBody EntregaInput entregaInput) {
        Entrega entrega = entregaAssembler.toEntity(entregaInput);
        return entregaAssembler.toModel(solicitaEntregaService.solicitar(entrega));
    }

    @GetMapping
    public List<EntregaModel> listar() {
        return entregaAssembler.toCollectionModel(entregaRepository.findAll());
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<EntregaModel> buscar(@PathVariable Long clienteId) {
        return entregaRepository.findById(clienteId)
                .map(entrega -> ResponseEntity.ok(entregaAssembler.toModel(entrega)))
                .orElse(ResponseEntity.notFound().build());
    }
}
