package miscomparadores;

import java.util.Comparator;

import clases.Categoria;

public class CategoriaRecargo implements Comparator<Categoria> {

	@Override
	public int compare(Categoria o1, Categoria o2) {
		if (o1.getRecargo() > o2.getRecargo()) {
			return -1;
		}
		else if (o1.getRecargo() == o2.getRecargo()) {
			return 0;
		}
		else {
			return 1;
		}
	}

}
