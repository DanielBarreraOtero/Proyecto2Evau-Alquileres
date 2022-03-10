package excepciones;

public class DniInvalidoExcepcion extends Exception{

	public DniInvalidoExcepcion() {
		super("El Dni Introducido no es váido.");
	}
	
	public DniInvalidoExcepcion(String msg) {
		super(msg);
	}
}
