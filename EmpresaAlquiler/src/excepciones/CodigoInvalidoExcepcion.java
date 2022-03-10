package excepciones;

public class CodigoInvalidoExcepcion extends Exception {

	public CodigoInvalidoExcepcion() {
		super("El Código Introducido no es váido.");
	}
	
	public CodigoInvalidoExcepcion(String msg) {
		super(msg);
	}
}
