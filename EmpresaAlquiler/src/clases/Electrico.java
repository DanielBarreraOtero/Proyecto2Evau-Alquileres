package clases;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Electrico extends Vehiculo {

//	PROPIEDADEDS
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double Autonomia; /*en Km*/
	private double TiempoRecarga; /*en mins*/
	
//	SETTERS Y GETTERS
	
	public double getAutonomia() {
		return Autonomia;
	}
	private void setAutonomia(double autonomia) {
		Autonomia = autonomia;
	}
	public double getTiempoRecarga() {
		return TiempoRecarga;
	}
	private void setTiempoRecarga(double tiempoRecarga) {
		TiempoRecarga = tiempoRecarga;
	}
	
//	CONSTRUCTORES
	
	public Electrico(String matricula, String marca, String modelo, String color, LocalDate fechaAlta, double kms, Categoria categoria, Oficina oficinaActual, ArrayList<CarnetConducir> carnetsValidos, double autonomia, double tiempoRecarga) {
		super(matricula, marca, modelo, color, fechaAlta, kms, categoria, oficinaActual, carnetsValidos);
		setAutonomia(autonomia);
		setTiempoRecarga(tiempoRecarga);
	}
	public Electrico(String matricula, String marca, String modelo, LocalDate fechaAlta, Categoria categoria, Oficina oficinaActual ,ArrayList<CarnetConducir> carnetsValidos, double autonomia) {
		super(matricula, marca, modelo, fechaAlta, categoria, oficinaActual, carnetsValidos);
		setAutonomia(autonomia);
	}
	
//	METODOS
	
	@Override
		public String toString() {
			return super.toString() + "";
		}
}
