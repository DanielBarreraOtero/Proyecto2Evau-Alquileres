package comparators;

import java.util.Comparator;

import clases.Vehiculo;

public class VehiculoFechaAlta implements Comparator<Vehiculo> {

	@Override
	public int compare(Vehiculo o1, Vehiculo o2) {
		return o1.getFechaAlta().compareTo(o2.getFechaAlta());
	}

}
