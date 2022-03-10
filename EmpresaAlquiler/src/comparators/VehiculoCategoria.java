package comparators;

import java.util.Comparator;

import clases.Vehiculo;

public class VehiculoCategoria implements Comparator<Vehiculo>{

	@Override
	public int compare(Vehiculo o1, Vehiculo o2) {
		return o1.getCategoria().compareTo(o2.getCategoria());
	}

}
