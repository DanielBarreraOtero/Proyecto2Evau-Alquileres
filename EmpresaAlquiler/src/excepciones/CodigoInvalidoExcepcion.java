package excepciones;

public class CodigoInvalidoExcepcion extends Exception {

	public CodigoInvalidoExcepcion() {
		super("El C�digo Introducido no es v�ido.");
	}
	
	public CodigoInvalidoExcepcion(String msg) {
		super(msg);
	}
}
