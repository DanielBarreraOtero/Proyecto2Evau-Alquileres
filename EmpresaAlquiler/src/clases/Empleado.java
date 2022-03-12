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
	
	/**Constructor completo.
	 * 
	 * @param dNI
	 * @param nombre
	 * @param ap1
	 * @param ap2
	 * @param fechaNac
	 * @param fechaalta
	 * @param oficinaactual
	 */
	public Empleado(String dNI, String nombre, String ap1, String ap2, GregorianCalendar fechaNac, GregorianCalendar fechaalta, Oficina oficinaactual) {
		super(dNI, nombre, ap1, ap2, fechaNac);
		setFechaAlta(fechaalta);
		setOficinaActual(oficinaactual);
	}
	/**Constructor sin ap2
	 * 
	 * @param dNI
	 * @param nombre
	 * @param ap1
	 * @param fechaNac
	 * @param fechaalta
	 * @param oficinaactual
	 */
	public Empleado(String dNI, String nombre, String ap1, GregorianCalendar fechaNac, GregorianCalendar fechaalta, Oficina oficinaactual) {
		super(dNI, nombre, ap1, fechaNac);
		setFechaAlta(fechaalta);
		setOficinaActual(oficinaactual);
	}
	/**Constructor simple.
	 * 
	 * @param dNI
	 * @param nombre
	 * @param ap1
	 * @param oficinaactual
	 */
	public Empleado(String dNI, String nombre, String ap1, Oficina oficinaactual) {
		super(dNI, nombre, ap1);
		setOficinaActual(oficinaactual);
	}
	/**Constructor copia.
	 * 
	 * @param o
	 */
	public Empleado(Empleado o) {
		super(o.getDNI(), o.getNombre(), o.getAp1(), o.getAp2(), o.getFechaNac());
		setFechaAlta(getFechaAlta());
		setOficinaActual(getOficinaActual());
	}
	/**Constructor comparacion.
	 * 
	 * @param dNI
	 */
	public Empleado(String dNI) {
		super(dNI);
	}
	
//	METODOS
	
	@Override
	public String toString() {
		@SuppressWarnings("static-access")
		String FN = getFechaNac().DAY_OF_MONTH + "/" + getFechaNac().MONTH + "/" + getFechaNac().YEAR;
		@SuppressWarnings("static-access")
		String FA = FechaAlta.DAY_OF_MONTH + "/" + getFechaAlta().MONTH + "/" + FechaAlta.YEAR;
		return getNombre() + ", " + getAp1() + " " + getAp2() + " | " + getDNI() + ", " + getEdad() + " años " + FN + ", Oficina:" + getOficinaActual() + " | Fecha alta:" + FA;
	}
	
	
	
	

}
