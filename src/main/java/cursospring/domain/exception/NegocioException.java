package cursospring.domain.exception;

public class NegocioException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NegocioException(String menssage){
        super(menssage);
    }
}
