package clases;

import java.io.Serializable;

import excepciones.CodigoInvalidoExcepcion;

public class Categoria implements Comparable<Categoria>, Serializable{

//	PROPIEDADES
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String Codigo; /* Una letra */
	private String Descripcion; /* (ECONOMICO, MEDIO, PREMIUM...) */
	private double Recargo; /* % */
	
//	GETTERS Y SETTERS
	
	public String getCodigo() {
		return Codigo;
	}
	private void setCodigo(String codigo) {
		try {
			if (codigo.length() == 1 && Character.isLetter(codigo.charAt(0)))
			{
				Codigo = codigo;
			}else {
				throw new CodigoInvalidoExcepcion();
			}
		} catch (Exception e) {
		}
	}
	public String getDescripcion() {
		return Descripcion;
	}
	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}
	public double getRecargo() {
		return Recargo;
	}
	public void setRecargo(double recargo) {
		Recargo = recargo;
	}
	
//	CONSTRUCTORES
	
	public Categoria(String codigo, String descripcion, double recargo) {
		setCodigo(codigo);
		setDescripcion(descripcion);
		setRecargo(recargo);
	}
	
	public Categoria(String codigo, double recargo) {
		setCodigo(codigo);
		setDescripcion("");
		setRecargo(recargo);
	}
	
//	METODOS
	
	@Override
	public String toString() {
		return getCodigo() + ", " + getDescripcion() + " +" + getRecargo() + "%";
	}
	@Override
	public int compareTo(Categoria o) {
		return Codigo.compareTo(o.getCodigo());
	}
	@Override
	public boolean equals(Object o) {
		Categoria c = (Categoria) o;
		return Codigo.equals(c.getCodigo());
	}
	
	
}
