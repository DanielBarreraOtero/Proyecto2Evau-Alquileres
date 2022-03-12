package miscomparadores;

import java.util.Comparator;

import clases.Empleado;

public class EmpleadoOficina implements Comparator<Empleado> {

	@Override
	public int compare(Empleado o1, Empleado o2) {
		return o1.getOficinaActual().compareTo(o2.getOficinaActual());
	}

}
