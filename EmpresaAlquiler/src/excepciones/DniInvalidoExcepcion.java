package excepciones;

public class DniInvalidoExcepcion extends Exception{

	public DniInvalidoExcepcion() {
		super("El Dni Introducido no es v�ido.");
	}
	
	public DniInvalidoExcepcion(String msg) {
		super(msg);
	}
}
