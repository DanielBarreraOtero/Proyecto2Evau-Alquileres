package clases;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Vehiculo implements Comparable<Vehiculo>, Serializable {

//	PROPIEDADES
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String Matricula;
	private String Marca;
	private String Modelo;
	private String Color;
	private LocalDate FechaAlta;
	private double Kms;
	private Categoria Categoria;
	private Oficina OficinaActual;
	private ArrayList<CarnetConducir> CarnetsValidos;
	
//	GETTERS Y SETTERS
	
	public String getMatricula() {
		return Matricula;
	}
	private void setMatricula(String matricula) {
		Matricula = matricula;
	}
	public String getMarca() {
		return Marca;
	}
	private void setMarca(String marca) {
		Marca = marca;
	}
	public String getModelo() {
		return Modelo;
	}
	private void setModelo(String modelo) {
		Modelo = modelo;
	}
	public String getColor() {
		return Color;
	}
	public void setColor(String color) {
		Color = color;
	}
	public LocalDate getFechaAlta() {
		return FechaAlta;
	}
	private void setFechaAlta(LocalDate fechaAlta) {
		FechaAlta = fechaAlta;
	}
	public double getKms() {
		return Kms;
	}
	private void setKms(double kms) {
		Kms = kms;
	}
	public Categoria getCategoria() {
		return Categoria;
	}
	private void setCategoria(Categoria categoria) {
		Categoria = categoria;
	}
	public Oficina getOficinaActual() {
		return OficinaActual;
	}
	public void setOficinaActual(Oficina oficinaActual) {
		OficinaActual = oficinaActual;
	}
	public ArrayList<CarnetConducir> getCarnetsValidos() {
		return CarnetsValidos;
	}
	private void setCarnetsValidos(ArrayList<CarnetConducir> carnetsValidos) {
		CarnetsValidos = carnetsValidos;
	}
	
//	CONSTRUCTORES
		
	public Vehiculo(String matricula, String marca, String modelo, String color, LocalDate fechaAlta, double kms, Categoria categoria, Oficina oficinaActual, ArrayList<CarnetConducir> carnetsValidos) {
		setMatricula(matricula);
		setMarca(marca);
		setModelo(modelo);
		setColor(color);
		setFechaAlta(fechaAlta);
		setKms(kms);
		setCategoria(categoria);
		setOficinaActual(oficinaActual);
		setCarnetsValidos(carnetsValidos);
	}
	public Vehiculo(String matricula, String marca, String modelo, LocalDate fechaAlta, Categoria categoria, Oficina oficinaActual, ArrayList<CarnetConducir> carnetsValidos) {
		setMatricula(matricula);
		setMarca(marca);
		setModelo(modelo);
		setFechaAlta(fechaAlta);
		setKms(0);
		setCategoria(categoria);
		setOficinaActual(oficinaActual);
		setCarnetsValidos(carnetsValidos);
	}
	public Vehiculo(Vehiculo o) {
		setMatricula(o.getMatricula());
		setMarca(o.getMarca());
		setModelo(o.getModelo());
		setColor(o.getColor());
		setFechaAlta(o.getFechaAlta());
		setKms(o.getKms());
		setCategoria(o.getCategoria());
		setOficinaActual(o.getOficinaActual());
		setCarnetsValidos(o.getCarnetsValidos());
	}
	public Vehiculo(String matricula) {
		setMatricula(matricula);
	}
	
//	METODOS
	
	@Override
	public String toString () {
		return Matricula + ", " + Marca + ", " + Modelo + " " + Kms + "Kms | " + Categoria + " | Fecha Alta: " + FechaAlta + ". \nOficina Actual :" + OficinaActual;
	}
	@Override
	public int compareTo(Vehiculo o) {
		return this.getMatricula().compareTo(o.getMatricula());
	}
	@Override
	public boolean equals(Object o) {
		Vehiculo p = (Vehiculo) o;
		return Matricula.equals(p.getMatricula());
	}
	/**Para calcular el primer importe que se le enseña al usuario en el listado.
	 * Si la oficina en la que se encuetra el coche es un aeropuerto se le aplica otro 10%.
	 * Si la oficina de devolucion es diferente a la actual se le aplica otro 10%.
	 * Si el cliente es menor de 25 años se le aplica otro 5%.
	 */
	abstract public double CalcularImporte(int dias, boolean mismaOficina, Oficina oficinaDev, boolean menor25);
	public void AñadirKms(Double kms) {
		setKms(Kms+kms);
	}
	public void TransladarDeOficina(Oficina o) {
	setOficinaActual(o);
	}
	

}
