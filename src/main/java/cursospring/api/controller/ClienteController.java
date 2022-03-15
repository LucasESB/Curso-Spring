package cursospring.api.controller;

import cursospring.domain.model.Cliente;
import cursospring.domain.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor // gera um construtor com todas propriedades da classe
@RestController
@RequestMapping("/clientes") // define a rota da classe
public class ClienteController {

    private ClienteRepository clienteRepository;

    @GetMapping
    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    // Diferente do express no Spring para criar uma variavel na rota bastar usar chaves {} e no paramtero da função usar a notação @PathVariable para vincular a variavel da rota com a variavel da função
    @GetMapping("/{clienteId}")
    public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
        return clienteRepository.findById(clienteId)
                .map(cliente -> ResponseEntity.ok(cliente))
                .orElse(ResponseEntity.notFound().build());
        // Duas forma para fazer a mesma coisa

//        Optional<Cliente> cliente = clienteRepository.findById(clienteId);
//
//        //Optional é um container que pode estar ou não com algo dentro o metodo isPresentd verifica isso
//        if (cliente.isPresent()) {
//            return ResponseEntity.ok(cliente.get());
//        }
//
//        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // informa qual o status http de retorno neste caso o 201
    public Cliente inserir(@Valid @RequestBody Cliente cliente) { // A notação @RequestBodyvincula o corpo da requisição a variavel cliente
        return clienteRepository.save(cliente);
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<Cliente> ataulizar(@Valid @RequestBody Cliente cliente, @PathVariable Long clienteId) {// A notação @Valid indica que o corpo da requisição será validado e caso alguma informção que esteja com problema não entrará no metodo
        if (!clienteRepository.existsById(clienteId)) {
            return ResponseEntity.notFound().build();
        }

        cliente.setId(clienteId);
        cliente = clienteRepository.save(cliente);

        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{clienteId}")
    public ResponseEntity<Void> deletar(@PathVariable Long clienteId) {
        if (!clienteRepository.existsById(clienteId)) {
            return ResponseEntity.notFound().build();
        }

        clienteRepository.deleteById(clienteId);
        return ResponseEntity.noContent().build(); // coidgo 204 usado quando não é retornado nada no corpo da ResponseStatus
    }
}
