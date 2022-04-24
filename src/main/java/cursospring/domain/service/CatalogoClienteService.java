package cursospring.domain.service;

import cursospring.domain.exception.NegocioException;
import cursospring.domain.model.Cliente;
import cursospring.domain.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service //Essa notação torna a classe um componente do Spring porem com uma semantica de Serviço
//Esta classe serve para centralizar as regras de negocio, cada classe é um caso de uso, por exemplo: CRUD, Bloqueio de clientes
public class CatalogoClienteService {

    private ClienteRepository clienteRepository;

    public Cliente buscar(Long clienteId){
        return clienteRepository.findById(clienteId)
                .orElseThrow(() -> new NegocioException("Cliente não encontrado"));
    }

    /*Essa notação indica que esse metodo deve ser executado dentro de uma transação, se algo dentro desse metodo der errado todas operações dentro dessa transação não vão ser executadas */
    @Transactional
    public Cliente salvar(Cliente cliente) {
        //Em cada metodo posso implementar alguma validação caso seja necessario, também poderia criar um pacote Rules e criar minhas validações

//        boolean emaulEmUso = clienteRepository.findByEmail(cliente.getEmail())
//                .stream()
//                .anyMatch(clienteExistente -> !clienteExistente.equals(cliente));

        Cliente cli = clienteRepository.findByEmail(cliente.getEmail()).get();

        if(cli != null && !cli.equals(cliente)){
            throw new NegocioException("já existe um cliente cadastrado com esse e-mail.");
        }

        return clienteRepository.save(cliente);
    }

    @Transactional
    public void excluir(Long clienteId){
        clienteRepository.deleteById(clienteId);
    }
}
