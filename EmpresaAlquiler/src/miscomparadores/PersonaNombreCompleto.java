package miscomparadores;

import java.util.Comparator;

import clases.Persona;

public class PersonaNombreCompleto implements Comparator<Persona>{

	@Override
	public int compare(Persona o1, Persona o2) {
		return o1.NombreCompleto().compareTo(o2.NombreCompleto());
	}

}
