package cursospring.domain.repository;

import cursospring.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// JpaRepository => ela possui as funcinalidades para a gente poder criar, cunsultar... as entidade no banco
/* Essa notação indica que a classe é um componente do repository spring que gerencia as instancia da classe, assim eu não precisa criar uma implementação dessa interface o spring faz isso automaticamente, basta apenas usar a notação @Autowired onde eu for declar ou passar pelo contrutor */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    // O spring gera uma implementação automatica de metodo, porem ele necessita que o nome do metodo seja, find ou query logo depois By e depois o nome da propriedade da classe
    List<Cliente> findByNome(String nome);

    // quando quisermos uma implementação com like usamos Containing
    List<Cliente> findByNomeContaining(String nome);
}
