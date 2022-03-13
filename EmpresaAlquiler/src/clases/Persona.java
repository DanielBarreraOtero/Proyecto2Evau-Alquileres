package clases;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

import excepciones.DniInvalidoExcepcion;
import metodos.Metodos;

public abstract class Persona implements Comparable<Persona>, Serializable {

	//		PROPIEDADES

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String DNI;
	private String Nombre;
	private String Ap1;
	private String Ap2;
	private LocalDate FechaNac;

	//		GETTERS & SETTERS

	public String getDNI() {
		return DNI;
	}
	private void setDNI(String dNI) {
		try {
			if (Metodos.ValidarDNI(dNI)) {
				DNI = dNI;
			}else {
				throw new DniInvalidoExcepcion();
			}
		} catch (DniInvalidoExcepcion e) {
		}
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getAp1() {
		return Ap1;
	}
	public void setAp1(String ap1) {
		Ap1 = ap1;
	}
	public String getAp2() {
		return Ap2;
	}
	public void setAp2(String ap2) {
		Ap2 = ap2;
	}
	public LocalDate getFechaNac() {
//		creamos objetos aparte para que no haya tampering
		LocalDate fech = FechaNac;
		return fech;
	}
	private void setFechaNac(LocalDate fechaNac) {
//		creamos objetos aparte para que no haya tampering
		LocalDate fech = fechaNac;
		FechaNac = fech;
	}


	//		CONSTRUCTORES

	/** Constructor completo
	 * @param String - dNI
	 * @param String - nombre
	 * @param String - ap1
	 * @param String - ap2
	 * @param LocalDate - fechaNac
	 */
	public Persona(String dNI, String nombre, String ap1, String ap2, LocalDate fechaNac) {
		setDNI(dNI);
		setNombre(nombre);
		setAp1(ap1);
		setAp2(ap2);
		setFechaNac(fechaNac);
	}
	/**Constructor sin ap2.
	 * 
	 * @param dNI
	 * @param nombre
	 * @param ap1
	 * @param fechaNac
	 */
	public Persona(String dNI, String nombre, String ap1, LocalDate fechaNac) {
		setDNI(dNI);
		setNombre(nombre);
		setAp1(ap1);
		setAp2("");
		setFechaNac(fechaNac);
	}
	/**Constructor simple.
	 * 
	 * @param dNI
	 * @param nombre
	 * @param ap1
	 */
	public Persona(String dNI, String nombre, String ap1) {
		setDNI(dNI);
		setNombre(nombre);
		setAp1(ap1);
		setAp2("");
	}
	/**Constructor copia.
	 * 
	 * @param o
	 */
	public Persona(Persona o) {
		setDNI(o.getDNI());
		setNombre(o.getNombre());
		setAp1(o.getAp1());
		setAp2(o.getAp2());
		setFechaNac(o.getFechaNac());
	}
	/**Constructor comparacion.
	 * 
	 * @param dni
	 */
	public Persona (String dni) {
		setDNI(dni);
	}

	//		METODOS

	@Override
	public String toString () {
		return Nombre + ", " + Ap1 + " " + Ap2 + " | " + DNI + ", " + FechaNac;
	}
	@Override
	public int compareTo(Persona o) {
		return this.NombreCompleto().compareTo(o.NombreCompleto());
	}
	@Override
	public boolean equals(Object o) {
		Persona p = (Persona) o;
		return DNI.equals(p.getDNI());
	}
	public String NombreCompleto() {
		return Ap1 + " " + Ap2 + ", " + Nombre;
	}
	public boolean esCumpleaños() {
		LocalDate hoy = LocalDate.now();
		boolean esCumpleaños = (FechaNac.getMonth().equals(hoy.getMonth()) && FechaNac.getDayOfMonth() == hoy.getDayOfMonth());
		return esCumpleaños;
	}
	public int getEdad() {
		LocalDate hoy = LocalDate.now();
		return Period.between(FechaNac, hoy).getYears();
	}



}
