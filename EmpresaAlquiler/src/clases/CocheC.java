package clases;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class CocheC extends Combustion {

//	PROPIEDADES
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int Nplazas;
	private String Tipo; /* (Deportivo, Familiar, 4x4) */
	private double PrecioBase = 50;
	
//	GETTERS Y SETTERS
	
	public int getNplazas() {
		return Nplazas;
	}
	private void setNplazas(int nplazas) {
		Nplazas = nplazas;
	}
	public String getTipo() {
		return Tipo;
	}
	private void setTipo(String tipo) {
		Tipo = tipo;
	}
	
//	CONSTRUCTORES
	
	public CocheC(String matricula, String marca, String modelo, String color, GregorianCalendar fechaAlta, double kms, Categoria categoria, Oficina oficinaActual, ArrayList<CarnetConducir> carnetsValidos, double consumo, double potencia, String nivelEmisiones, int nplazas, String tipo) {
		super(matricula, marca, modelo, color, fechaAlta, kms, categoria, oficinaActual, carnetsValidos, consumo, potencia, nivelEmisiones);
		setNplazas(nplazas);
		setTipo(tipo);
	}
	public CocheC(String matricula, String marca, String modelo, GregorianCalendar fechaAlta, Categoria categoria, Oficina oficinaActual, ArrayList<CarnetConducir> carnetsValidos, double consumo, double potencia, int nplazas, String tipo) {
		super(matricula, marca, modelo, fechaAlta, categoria, oficinaActual, carnetsValidos, consumo, potencia);
		setNplazas(nplazas);
		setTipo(tipo);
	}
//	METODOS
	
//	TODO
	@Override
	/**Para calcular el primer importe que se le enseña al usuario en el listado.
	 * Si la oficina en la que se encuetra el coche es un aeropuerto se le aplica otro 10%.
	 */
	public double CalcularImporte(int dias) {
		double aeropuerto = 0;
		if (getOficinaActual().getAeropuerto()) {
			aeropuerto = 0.10 * PrecioBase;
		}
		return (PrecioBase * dias) + (PrecioBase * (getCategoria().getRecargo() / 100)) + aeropuerto;
	}
	
}
