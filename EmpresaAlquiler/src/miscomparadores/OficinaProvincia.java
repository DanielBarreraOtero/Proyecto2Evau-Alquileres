package miscomparadores;

import java.util.Comparator;

import clases.Oficina;

public class OficinaProvincia implements Comparator<Oficina> {

	@Override
	public int compare(Oficina o1, Oficina o2) {
		return o1.getProvincia().compareTo(o2.getProvincia());
	}

}
