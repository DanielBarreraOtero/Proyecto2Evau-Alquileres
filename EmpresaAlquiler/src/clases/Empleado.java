package clases;

import java.time.LocalDate;

public class Empleado extends Persona{

//	PROPIEDADES
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LocalDate FechaAlta;
	private Oficina OficinaActual;
	
// GETTERS Y SETTERS
	
	public LocalDate getFechaAlta() {
		return FechaAlta;
	}
	private void setFechaAlta(LocalDate fechaAlta) {
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
	public Empleado(String dNI, String nombre, String ap1, String ap2, LocalDate fechaNac, LocalDate fechaalta, Oficina oficinaactual) {
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
	public Empleado(String dNI, String nombre, String ap1, LocalDate fechaNac, LocalDate fechaalta, Oficina oficinaactual) {
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
		return getNombre() + ", " + getAp1() + " " + getAp2() + " | " + getDNI() + ", " + getEdad() + " años " + getFechaNac() + ", Oficina:" + getOficinaActual() + " | Fecha alta:" + FechaAlta;
	}
	
	
	
	

}
