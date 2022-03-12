package miscomparadores;

import java.util.Comparator;

import clases.Oficina;

public class OficinaAeropuerto implements Comparator<Oficina> {

	@Override
	public int compare(Oficina o1, Oficina o2) {
		if (o1.getAeropuerto() == true && o2.getAeropuerto() == false) {
			return -1;
		}
		else if ((o1.getAeropuerto() == true && o2.getAeropuerto() == true) || (o1.getAeropuerto() == false && o2.getAeropuerto() == false)) {
			return 0;
		}
		else {
			return 1;
		}
	}

}
