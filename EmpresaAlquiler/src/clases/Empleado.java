package clases;

import java.util.GregorianCalendar;

public class Empleado extends Persona{

//	PROPIEDADES
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GregorianCalendar FechaAlta;
	private Oficina OficinaActual;
	
// GETTERS Y SETTERS
	
	public GregorianCalendar getFechaAlta() {
		return FechaAlta;
	}
	private void setFechaAlta(GregorianCalendar fechaAlta) {
		FechaAlta = fechaAlta;
	}
	public Oficina getOficinaActual() {
		return OficinaActual;
	}
	public void setOficinaActual(Oficina oficinaActual) {
		Oficina c = new Oficina(oficinaActual);
		OficinaActual = c;
	}
	
//	CONSTRUCTORES
	

	public Empleado(String dNI, String nombre, String ap1, String ap2, GregorianCalendar fechaNac, GregorianCalendar fechaalta, Oficina oficinaactual) {
		super(dNI, nombre, ap1, ap2, fechaNac);
		setFechaAlta(fechaalta);
		setOficinaActual(oficinaactual);
	}
	public Empleado(String dNI, String nombre, String ap1, Oficina oficinaactual) {
		super(dNI, nombre, ap1);
		setOficinaActual(oficinaactual);
	}
	public Empleado(Empleado o) {
		super(o.getDNI(), o.getNombre(), o.getAp1(), o.getAp2(), o.getFechaNac());
		setFechaAlta(getFechaAlta());
		setOficinaActual(getOficinaActual());
	}
	public Empleado(String dNI) {
		super(dNI);
	}
	
//	METODOS
	
	@Override
	public String toString() {
//		return getNombre() + ", " + getAp1() + " " + getAp2() + " | " + getDNI() + ", " + getFechaNac() + ", " + getOficinaActual() + " | " + getFechaAlta();
		return getNombre() + ", " + getAp1() + " " + getAp2() + " | " + getDNI() + ", " + getOficinaActual().getCodigo();
	}
	
	
	
	

}
