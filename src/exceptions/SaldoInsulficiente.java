package exceptions;

public class SaldoInsulficiente extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public SaldoInsulficiente(String message) {
		super(message);
	}

}
