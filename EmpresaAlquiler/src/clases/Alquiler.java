package clases;

import java.io.Serializable;
import java.time.LocalDate;

public class Alquiler implements Comparable<Alquiler>, Serializable{

//	PROPIEDADES
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String Codigo;/* Se generará solo al introducir uno nuevo a la empresa */
	private Vehiculo Vehiculo; /* A alquilar */
	private Empleado EmpleadoAlquiler; /* Empleado que gestiona el alquiler */
	private Cliente Cliente; /* Que alquila */
	private Oficina OficinaOriginal; /* En la que se encuentra el coche */
	private LocalDate FechaInicio; /* Cuando se realiza el alquiler */
	private LocalDate FechaFinPrev; /* La que se prevee que el cliente devolvera */
	private double ImporteTotal; /* Importe total */
	private Oficina OficinaPrev; /* en la que se prevee que se devolvera */
	private Oficina OficinaDevolucion; /* en la que se devuelve el vehiculo realmente*/
	private double KmsRecorridos; /* se añaden al vehiculo */
	private LocalDate FechaFinReal; /* Fecha en la que realmente se ha devuelto el vehiculo */
	private Empleado EmpleadoDevolucion; /* Empleado que gestiona la devolucion */
	private double ImporteFinal; /* Importe real en el caso de que se tenga que aplicar alguna amonestacion */
	
//	GETTERS Y SETTERS
	
	private void setCodigo(String codigo) {
		Codigo = codigo;
	}
	public String getCodigo() {
		return Codigo;
	}
	public Vehiculo getVehiculo() {
		return Vehiculo;
	}
	private void setVehiculo(Vehiculo vehiculo) {
		Vehiculo = vehiculo;
	}
	public Empleado getEmpleadoAlquiler() {
		return EmpleadoAlquiler;
	}
	private void setEmpleadoAlquiler(Empleado empleadoAlquiler) {
		EmpleadoAlquiler = empleadoAlquiler;
	}
	public Cliente getCliente() {
		return Cliente;
	}
	private void setCliente(Cliente cliente) {
		Cliente = cliente;
	}
	public Oficina getOficinaOriginal() {
		return OficinaOriginal;
	}
	private void setOficinaOriginal(Oficina oficinaOriginal) {
		OficinaOriginal = oficinaOriginal;
	}
	public LocalDate getFechaInicio() {
		return FechaInicio;
	}
	private void setFechaInicio(LocalDate fechaInicio) {
		FechaInicio = fechaInicio;
	}
	public LocalDate getFechaFinPrev() {
		return FechaFinPrev;
	}
	private void setFechaFinPrev(LocalDate fechaFinPrev) {
		FechaFinPrev = fechaFinPrev;
	}
	public double getImporteTotal() {
		return ImporteTotal;
	}
	private void setImporteTotal(double importeTotal) {
		ImporteTotal = importeTotal;
	}
	public Oficina getOficinaPrev() {
		return OficinaPrev;
	}
	private void setOficinaPrev(Oficina oficinaPrev) {
		OficinaPrev = oficinaPrev;
	}
	public Oficina getOficinaDevolucion() {
		return OficinaDevolucion;
	}
	public void setOficinaDevolucion(Oficina oficinaDevolucion) {
		OficinaDevolucion = oficinaDevolucion;
	}
	public double getKmsRecorridos() {
		return KmsRecorridos;
	}
	public void setKmsRecorridos(double kmsRecorridos) {
		KmsRecorridos = kmsRecorridos;
	}
	public LocalDate getFechaFinReal() {
		return FechaFinReal;
	}
	public void setFechaFinReal(LocalDate fechaFinReal) {
		FechaFinReal = fechaFinReal;
	}
	public Empleado getEmpleadoDevolucion() {
		return EmpleadoDevolucion;
	}
	public void setEmpleadoDevolucion(Empleado empleadoDevolucion) {
		EmpleadoDevolucion = empleadoDevolucion;
	}
	public double getImporteFinal() {
		return ImporteFinal;
	}
	public void setImporteFinal(double importeFinal) {
		ImporteFinal = importeFinal;
	}
		
//	CONSTRUCTORES
	
	
	/**Constructor total.
	 * 
	 * @param codigo
	 * @param vehiculo
	 * @param empleadoAlquiler
	 * @param cliente
	 * @param oficinaOriginal
	 * @param fechaInicio
	 * @param fechaFinPrev
	 * @param importeTotal
	 * @param oficinaPrev
	 * @param oficinaDevolucion
	 * @param kmsRecorridos
	 * @param fechaFinReal
	 * @param empleadoDevolucion
	 * @param importeFinal
	 */
	public Alquiler(String codigo, Vehiculo vehiculo, Empleado empleadoAlquiler, Cliente cliente, Oficina oficinaOriginal, LocalDate fechaInicio, LocalDate fechaFinPrev, double importeTotal, Oficina oficinaPrev, Oficina oficinaDevolucion, double kmsRecorridos, LocalDate fechaFinReal, Empleado empleadoDevolucion, double importeFinal) {
		setCodigo(codigo);
		setVehiculo(vehiculo);
		setEmpleadoAlquiler(empleadoAlquiler);
		setCliente(cliente);
		setOficinaOriginal(oficinaOriginal);
		setFechaInicio(fechaInicio);
		setFechaFinPrev(fechaFinPrev);
		setImporteTotal(importeTotal);
		setOficinaPrev(oficinaPrev);
		setOficinaDevolucion(oficinaDevolucion);
		setKmsRecorridos(kmsRecorridos);
		setFechaFinReal(fechaFinReal);
		setEmpleadoDevolucion(empleadoDevolucion);
		setImporteFinal(importeFinal);
	}
	/**Constructor De inicio
	 * 
	 * @param vehiculo
	 * @param empleadoAlquiler
	 * @param cliente
	 * @param oficinaOriginal
	 * @param fechaInicio
	 * @param fechaFinPrev
	 * @param importeTotal
	 * @param oficinaPrev
	 */
	public Alquiler(String codigo, Vehiculo vehiculo, Empleado empleadoAlquiler, Cliente cliente, Oficina oficinaOriginal, LocalDate fechaInicio, LocalDate fechaFinPrev, double importeTotal, Oficina oficinaPrev) {
		setCodigo(codigo);
		setVehiculo(vehiculo);
		setEmpleadoAlquiler(empleadoAlquiler);
		setCliente(cliente);
		setOficinaOriginal(oficinaOriginal);
		setFechaInicio(fechaInicio);
		setFechaFinPrev(fechaFinPrev);
		setImporteTotal(importeTotal);
		setOficinaPrev(oficinaPrev);
	}
	/**Constructor copia
	 * 
	 * @param o
	 */
	public Alquiler(Alquiler o) {
		setCodigo(o.getCodigo());
		setVehiculo(o.getVehiculo());
		setEmpleadoAlquiler(o.getEmpleadoAlquiler());
		setCliente(o.getCliente());
		setOficinaOriginal(o.getOficinaOriginal());
		setFechaInicio(o.getFechaInicio());
		setFechaFinPrev(o.getFechaFinPrev());
		setImporteTotal(o.getImporteTotal());
		setOficinaPrev(o.getOficinaPrev());
		setOficinaDevolucion(o.getOficinaDevolucion());
		setKmsRecorridos(o.getKmsRecorridos());
		setFechaFinReal(o.getFechaFinReal());
		setEmpleadoDevolucion(o.getEmpleadoDevolucion());
		setImporteFinal(o.getImporteFinal());
	}
	
//	METODOS
	
	@Override
		public String toString() {
			if (OficinaDevolucion == null) {
				return "Nº"+Codigo+", Cliente: "+Cliente+"\n"+"Vehículo: "+Vehiculo+"\n"+"Por empleado: "+EmpleadoAlquiler+"\n"+"Previsto desde el día: "+FechaInicio+" hasta "+FechaFinPrev+"\n"+"Con devolcion prevista en: "+OficinaPrev+"\n"+"Y precio previsto de: "+ImporteTotal+" €.";
			} else {
				return "Nº"+Codigo+", Cliente: "+Cliente+"\n"+"Vehículo: "+Vehiculo+"\n"+"Por empleado: "+EmpleadoAlquiler+"\n"+"Previsto desde el día: "+FechaInicio+" hasta "+FechaFinPrev+"\n"+"Con devolcion prevista en: "+OficinaPrev+"\n"+"Y precio previsto de: "+ImporteTotal+"\n"+"Con devolución el día: "+FechaFinReal+"\n"+"En oficina: "+OficinaDevolucion+"\n"+"Por empleado: "+EmpleadoDevolucion+"\n"+"Con un total de kilómetros de : "+KmsRecorridos+" y un precio real de: "+ImporteFinal+" €.";
			}
		}
	
	/**Rellena el resto de datos de un alquiler ya empezado para finalizarlo.
	 * 
	 * @param oficinaDevolucion
	 * @param kmsRecorridos
	 * @param fechaFinReal
	 * @param empleadoDevolucion
	 * @param importeFinal
	 */
	public void rellenarAlquiler(Oficina oficinaDevolucion, double kmsRecorridos, LocalDate fechaFinReal, Empleado empleadoDevolucion, double importeFinal) {
		setOficinaDevolucion(oficinaDevolucion);
		setKmsRecorridos(kmsRecorridos);
		setFechaFinReal(fechaFinReal);
		setEmpleadoDevolucion(empleadoDevolucion);
		setImporteFinal(importeFinal);
	}
	@Override
	public int compareTo(Alquiler o) {
		return Codigo.compareTo(o.getCodigo());
	}
	@Override
		public boolean equals(Object o) {
			Alquiler a = (Alquiler) o;
			return Codigo.equals(a.getCodigo());
		}
	
	
	
	
}
