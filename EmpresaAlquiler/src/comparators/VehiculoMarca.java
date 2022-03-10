package comparators;

import java.util.Comparator;

import clases.Vehiculo;

public class VehiculoMarca implements Comparator<Vehiculo> {

	@Override
	public int compare(Vehiculo o1, Vehiculo o2) {
		return o1.getMarca().compareTo(o2.getMarca());
	}

}
