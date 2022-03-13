package clases;

import java.time.LocalDate;
import java.util.ArrayList;

public class Moto extends Electrico {

//	PROPIEDADES
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double Cilindrada;
	private double PrecioBase = 10;

//	GETTERS Y SETTERS	
	
	public double getCilindrada() {
		return Cilindrada;
	}

	private void setCilindrada(double cilindrada) {
		Cilindrada = cilindrada;
	}
	
//	CONSTRUCTORES
	
	public Moto(String matricula, String marca, String modelo, String color, LocalDate fechaAlta, double kms, Categoria categoria, Oficina oficinaActual, ArrayList<CarnetConducir> carnetsValidos, double autonomia, double tiempoRecarga, double cilindrada) {
		super(matricula, marca, modelo, color, fechaAlta, kms, categoria, oficinaActual, carnetsValidos, autonomia, tiempoRecarga);
		setCilindrada(cilindrada);
	}

	public Moto(String matricula, String marca, String modelo, LocalDate fechaAlta, Categoria categoria, Oficina oficinaActual, ArrayList<CarnetConducir> carnetsValidos, double autonomia, double cilindrada) {
		super(matricula, marca, modelo, fechaAlta, categoria, oficinaActual, carnetsValidos, autonomia);
		setCilindrada(cilindrada);
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
		return (PrecioBase * dias) + (PrecioBase * (getCategoria().getRecargo() / 100)) + aeropuerto + (PrecioBase * 0.15);
	}
	
}
