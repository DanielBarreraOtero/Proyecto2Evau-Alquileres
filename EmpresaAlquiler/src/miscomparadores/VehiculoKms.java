package comparators;

import java.util.Comparator;

import clases.Vehiculo;

public class VehiculoKms implements Comparator<Vehiculo> {

	@Override
	public int compare(Vehiculo o1, Vehiculo o2) {
		if (o1.getKms() > o2.getKms()) {
			return -1;
		}
		else if (o1.getKms() < o2.getKms()) {
			return 1;
		}
		else {
			return 0;
		}	
	}

}
