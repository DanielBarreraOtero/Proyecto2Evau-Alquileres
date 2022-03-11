package clases;

import java.io.Serializable;

public class CarnetConducir implements Serializable, Comparable<CarnetConducir>{
//	PROPIEDADES
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String Codigo;
	private String Descrp;
	
//	GETTERS Y SETTERS
	
	public String getCodigo() {
		return Codigo;
	}
	private void setCodigo(String codigo) {
		Codigo = codigo;
	}
	public String getDescrp() {
		return Descrp;
	}
	public void setDescrp(String descrp) {
		Descrp = descrp;
	}
	
//	CONSTRUCTORES
	
	/**Constructor completo.
	 * 
	 * @param codigo
	 * @param descrp
	 */
	public CarnetConducir(String codigo, String descrp) {
		setCodigo(codigo);
		setDescrp(descrp);
	}
	/**Constructor simple.
	 * 
	 * @param codigo
	 */
	public CarnetConducir(String codigo) {
		setCodigo(codigo);
	}
	/**Constuctor de copia.
	 * 
	 * @param o
	 */
	public CarnetConducir(CarnetConducir o) {
		setCodigo(o.getCodigo());
		setDescrp(o.getDescrp());
	}

//	METODOS
	
	@Override
	public String toString () {
		return Codigo + ", " + Descrp;
	}
	@Override
	public int compareTo(CarnetConducir o) {
		return Codigo.compareTo(o.getCodigo());
	}
	@Override
	public boolean equals(Object o) {
		CarnetConducir c = (CarnetConducir) o;
		return Codigo.equals(c.getCodigo());
	}
	
	
	
	
	
	
	
	
	
	
}
