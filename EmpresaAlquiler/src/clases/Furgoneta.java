package clases;

import java.time.LocalDate;
import java.util.ArrayList;

public class Furgoneta extends Combustion {

//	PROPIEDADES
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double CapacidadCarga; /* En m^3 */
	private double PrecioBase = 70;
	
//	SETTERS Y GETTERS
	
	public double getCapacidadCarga() {
		return CapacidadCarga;
	}
	
	private void setCapacidadCarga(double capacidadCarga) {
		CapacidadCarga = capacidadCarga;
	}
	
//	CONSTUCTORES
	
	public Furgoneta(String matricula, String marca, String modelo, String color, LocalDate fechaAlta, double kms, Categoria categoria, Oficina oficinaActual, ArrayList<CarnetConducir> carnetsValidos, double consumo, double potencia, String nivelEmisiones, double capacidadCarga) {
		super(matricula, marca, modelo, color, fechaAlta, kms, categoria, oficinaActual, carnetsValidos, consumo, potencia, nivelEmisiones);
		setCapacidadCarga(capacidadCarga);
	}
	public Furgoneta(String matricula, String marca, String modelo, LocalDate fechaAlta, Categoria categoria, Oficina oficinaActual, ArrayList<CarnetConducir> carnetsValidos, double consumo, double potencia, double capacidadCarga) {
		super(matricula, marca, modelo, fechaAlta, categoria, oficinaActual, carnetsValidos, consumo, potencia);
		setCapacidadCarga(capacidadCarga);
	}
	
//	METODOS
	
	@Override
	/**Para calcular el primer importe que se le enseña al usuario en el listado.
	 * Si la oficina en la que se encuetra el coche es un aeropuerto se le aplica otro 10%.
	 * Si la oficina de devolucion es diferente a la actual se le aplica otro 10%.
	 * Si el cliente es menor de 25 años se le aplica otro 15%.
	 */
	public double CalcularImporte(int dias, boolean mismaOficina, Oficina oficinaDev, boolean menor25) {
		double aeropuerto = 0, OfiDif = 0, menor = 0;
		if (getOficinaActual().getAeropuerto() || oficinaDev.getAeropuerto()) {
			aeropuerto = 0.10 * PrecioBase;
		}
		
		if (!mismaOficina) {
			OfiDif = 0.10 * PrecioBase;
		}
		
		if (menor25) {
			menor = 0.15 * PrecioBase;
		}
		
		return (PrecioBase * dias) + (PrecioBase * (getCategoria().getRecargo() / 100)) + aeropuerto + OfiDif + menor;
	}
	
}
