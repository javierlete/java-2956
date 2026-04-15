package bibliotecas.controladorfrontal;

public class ControladorFrontalException extends RuntimeException {

	private static final long serialVersionUID = 1043779091258488066L;

	public ControladorFrontalException() {
		super();
	}

	public ControladorFrontalException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ControladorFrontalException(String message, Throwable cause) {
		super(message, cause);
	}

	public ControladorFrontalException(String message) {
		super(message);
	}

	public ControladorFrontalException(Throwable cause) {
		super(cause);
	}

	
}
