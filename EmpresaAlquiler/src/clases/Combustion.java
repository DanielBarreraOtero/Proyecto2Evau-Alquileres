package clases;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Combustion extends Vehiculo {
	
//	PROPIEDADES
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double Consumo; /*En L/100Kms*/
	private double Potencia; /*En CV*/
	private String NivelEmisiones; /*A/B/C*/
	
//	GETTERS Y SETTERS
	
	public double getConsumo() {
		return Consumo;
	}
	private void setConsumo(double consumo) {
		Consumo = consumo;
	}
	public double getPotencia() {
		return Potencia;
	}
	private void setPotencia(double potencia) {
		Potencia = potencia;
	}
	public String getNivelEmisiones() {
		return NivelEmisiones;
	}
	private void setNivelEmisiones(String nivelEmisiones) {
		NivelEmisiones = nivelEmisiones;
	}
	
//	CONSTRUCTORES
	
	public Combustion(String matricula, String marca, String modelo, String color, LocalDate fechaAlta, double kms, Categoria categoria, Oficina oficinaActual, ArrayList<CarnetConducir> carnetsValidos, double consumo, double potencia, String nivelEmisiones) {
		super(matricula, marca, modelo, color, fechaAlta, kms, categoria, oficinaActual, carnetsValidos);
		setConsumo(consumo);
		setPotencia(potencia);
		setNivelEmisiones(nivelEmisiones);
	}
	public Combustion(String matricula, String marca, String modelo, LocalDate fechaAlta, Categoria categoria, Oficina oficinaActual, ArrayList<CarnetConducir> carnetsValidos, double consumo, double potencia) {
		super(matricula, marca, modelo, fechaAlta, categoria, oficinaActual, carnetsValidos);
		setConsumo(consumo);
		setPotencia(potencia);
	}
	
//	METODOS
	
		
}
