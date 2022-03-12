package miscomparadores;

import java.util.Comparator;

import clases.Empleado;

public class EmpleadoFechaAlta implements Comparator<Empleado> {

	@Override
	public int compare(Empleado o1, Empleado o2) {
		return o1.getFechaAlta().compareTo(o2.getFechaAlta());
	}

}
