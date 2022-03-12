package clases;

import java.io.Serializable;
import java.util.GregorianCalendar;

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
	private GregorianCalendar FechaNac;

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
	public GregorianCalendar getFechaNac() {
		//			creamos objetos aparte para que no haya tampering
		GregorianCalendar fech = new GregorianCalendar(FechaNac.YEAR, FechaNac.MONTH, FechaNac.DAY_OF_MONTH);
		return fech;
	}
	private void setFechaNac(GregorianCalendar fechaNac) {
		GregorianCalendar fech = new GregorianCalendar(fechaNac.YEAR, fechaNac.MONTH, fechaNac.DAY_OF_MONTH);
		//			creamos objetos aparte para que no haya tampering
		FechaNac = fech;
	}


	//		CONSTRUCTORES

	/** Constructor completo
	 * @param String - dNI
	 * @param String - nombre
	 * @param String - ap1
	 * @param String - ap2
	 * @param GregorianCalendar - fechaNac
	 */
	public Persona(String dNI, String nombre, String ap1, String ap2, GregorianCalendar fechaNac) {
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
	public Persona(String dNI, String nombre, String ap1, GregorianCalendar fechaNac) {
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
		GregorianCalendar hoy = (GregorianCalendar) GregorianCalendar.getInstance();
		boolean esCumpleaños = (FechaNac.MONTH == hoy.MONTH && FechaNac.DAY_OF_MONTH == hoy.DAY_OF_MONTH);
		return esCumpleaños;
	}
	public int getEdad() {
		GregorianCalendar hoy = (GregorianCalendar) GregorianCalendar.getInstance();
		int edad = hoy.YEAR - FechaNac.YEAR;/*Calculamos la edad si ya hubiera sido su cumple*/

		//			Si todavia no ha sido su cumple, le restamos un año
		if (hoy.MONTH < FechaNac.MONTH || (hoy.MONTH == FechaNac.MONTH && hoy.DAY_OF_MONTH < FechaNac.DAY_OF_MONTH)) {
			edad =- 1;
		}

		return edad;
	}



}
