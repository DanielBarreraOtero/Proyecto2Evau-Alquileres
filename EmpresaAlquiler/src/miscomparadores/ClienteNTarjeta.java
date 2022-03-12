package miscomparadores;

import java.util.Comparator;

import clases.Cliente;

public class ClienteNTarjeta implements Comparator<Cliente> {

	@Override
	public int compare(Cliente o1, Cliente o2) {
		return o1.getNtarjeta().compareTo(o2.getNtarjeta());
	}

}
