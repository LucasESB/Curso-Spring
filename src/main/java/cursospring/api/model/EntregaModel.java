package cursospring.api.model;

import cursospring.domain.model.StatusEntrega;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class EntregaModel {
//Esta classe faz parte do pradrão DTO, que serve para transferir dados entre camadas de uma aplicação, ela é o modelo de representação de resposta da api

    private Long id;
    //Caso eu queria mostrar somente algumas propriedades do entidade cliente eu posso criar outra classe com as informações que e quero mostrar
    private ClienteResumoModel cliente;
    private DestinatarioModel destinatario;
    private BigDecimal taxa;
    private StatusEntrega status;
    private OffsetDateTime dataPedido;
    private OffsetDateTime dataFinalizacao;
}
