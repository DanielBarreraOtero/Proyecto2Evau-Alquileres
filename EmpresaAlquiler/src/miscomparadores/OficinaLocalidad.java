package comparators;

import java.util.Comparator;

import clases.Oficina;

public class OficinaLocalidad implements Comparator<Oficina> {

	@Override
	public int compare(Oficina o1, Oficina o2) {
		return o1.getLocalidad().compareTo(o2.getLocalidad());
	}

}
