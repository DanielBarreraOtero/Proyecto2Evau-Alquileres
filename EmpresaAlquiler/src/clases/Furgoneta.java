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
	
//	TODO
	@Override
	/**Para calcular el primer importe que se le enseña al usuario en el listado.
	 * Si el usuario indica que es menor de 25 años se le aplica un 5% extra y si la oficina en la que se encuetra el coche es un aeropuerto se le aplica otro 10%.
	 */
	public double CalcularImporte(int dias) {
		double aeropuerto = 0;
		if (getOficinaActual().getAeropuerto()) {
			aeropuerto = 0.10 * PrecioBase;
		}
		return (PrecioBase * dias) + (PrecioBase * (getCategoria().getRecargo() / 100)) + aeropuerto;
	}
	
}
