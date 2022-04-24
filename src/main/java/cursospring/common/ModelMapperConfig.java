package cursospring.common;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //Esta notação indica para o Sprring que esta classe tem como função a configuração de beans
public class ModelMapperConfig {
    //Esta classe dentro deste pacote serve para conigurar uma biblioteca que não faz parte do sistema Spring funcione dentro do sistema

    @Bean //Esta notação indica que este metodo inicializa e configura um bean, por tanto ele será disponibilizado para realizar injeção de dependência no sistema
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
