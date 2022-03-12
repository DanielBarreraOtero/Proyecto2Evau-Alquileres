package comparators;

import java.util.Comparator;

import clases.Vehiculo;

public class VehiculoOficinaActual implements Comparator<Vehiculo> {

	@Override
	public int compare(Vehiculo o1, Vehiculo o2) {
		return o1.getOficinaActual().compareTo(o2.getOficinaActual());
	}

}
