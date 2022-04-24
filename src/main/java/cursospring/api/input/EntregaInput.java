package cursospring.api.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class EntregaInput {
    // Esta classe é o modelo de representação de entrada (Corpo das Requisições) da api, aqui eu coloco somente as propriedades que serão persistidas no banco de dados

    @Valid // Esta notação indica que o que está dentro do cliente também será validado
    @NotNull
    private ClienteIdInput cliente;

    @Valid
    @NotNull
    private DestinatarioInput destinatario;

    @NotNull
    private BigDecimal taxa;
}
