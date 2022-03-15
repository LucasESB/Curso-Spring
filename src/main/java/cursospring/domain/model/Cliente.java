package cursospring.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/* Gera os metodos equal e hascode automaticamente, somente nos campo que tiverem a notação @EqualsAndHashCode.Include */
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity // Informa para que esta classe é uma entidade no banco de dados para o JPA

/* @Table(name = "tb_cliente") caso o nome da tabela no banco seja diferente da classe */
public class Cliente {

    @EqualsAndHashCode.Include
    @Id // Informa que esse atributo é uma chave primaria no banco de dados para JPA
    /* Informa a strategira de geração de chaves que no banco é o auto_increment */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
//    @Column(name = "nome_cliente") caso o nome da coluna no banco seja diferente posso usar o campo nome para especificar
    @NotBlank // Indica que o valor dessa propriedade não pode ser vazia e nem nula, Para usar essa notção é necessario usar o Spring Boot start validation
    @Size(max = 60)
    private String nome;

    @Column
    @NotBlank
    @Email
    @Size(max = 255)
    private String email;

    @Column
    @NotBlank
    @Size(max = 20)
    private String telefone;

}
