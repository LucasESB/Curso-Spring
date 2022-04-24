package cursospring.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Entrega {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne /* Esta notação indica que há um relacionamento entre a entidade Entrega e a entidade Cliente do tipo de muitos para 1*/
    private Cliente cliente;

    @Embedded /* Esta notação serve para abstrair os dados, ou seja Não será criado uma outra tabela no banco de dados, os dado do Destinario ficaram na tabela Entrega, para usar esta notação a classe deverá usar a notação @Embeddable */
    private Destinatario destinatario;

    @NotNull
    private BigDecimal taxa;

    @Enumerated(EnumType.STRING) /* Esta expecifica que na coluna no banco será armazenado o valor das contantes Enum e não seus indices */
    private StatusEntrega status;

    private OffsetDateTime dataPedido;
    private OffsetDateTime dataFinalizacao;

    @OneToMany(mappedBy = "entrega", cascade = CascadeType.ALL) /* Esta notação indica que há um relacionamento entre a entidade Entrega e a entidade Ocorrencia do tipo de 1 para muitos, no mappedBy indica o nome da propriedade que tem o relacionamento inverso o @ManyToOne, o cascade indica que quando ouver alguma alteração na lista essa alteração será persistida no banco de dados */
    private List<Ocorrencia> ocorrencias =  new ArrayList<>();

    public Ocorrencia adicionarOcorrencia(String descricao) {
        Ocorrencia ocorrencia = new Ocorrencia();
        ocorrencia.setDescricao(descricao);
        ocorrencia.setDataRegistro(OffsetDateTime.now());
        ocorrencia.setEntrega(this);

        this.getOcorrencias().add(ocorrencia);

        return ocorrencia;
    }
}
