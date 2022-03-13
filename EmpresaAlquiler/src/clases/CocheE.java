package clases;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class CocheE extends Electrico {

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
	/**
	 * 
	 * @param matricula
	 * @param marca
	 * @param modelo
	 * @param color
	 * @param fechaAlta
	 * @param kms
	 * @param categoria
	 * @param oficinaActual
	 * @param carnetsValidos
	 * @param autonomia
	 * @param tiempoRecarga
	 * @param nplazas
	 * @param tipo
	 */
	public CocheE(String matricula, String marca, String modelo, String color, GregorianCalendar fechaAlta, double kms, Categoria categoria, Oficina oficinaActual, ArrayList<CarnetConducir> carnetsValidos, double autonomia, double tiempoRecarga, int nplazas, String tipo) {
		super(matricula, marca, modelo, color, fechaAlta, kms, categoria, oficinaActual, carnetsValidos, autonomia, tiempoRecarga);
		setNplazas(nplazas);
		setTipo(tipo);
	}
	/**
	 * 
	 * @param matricula
	 * @param marca
	 * @param modelo
	 * @param fechaAlta
	 * @param categoria
	 * @param oficinaActual
	 * @param carnetsValidos
	 * @param autonomia
	 * @param nplazas
	 * @param tipo
	 */
	public CocheE(String matricula, String marca, String modelo, GregorianCalendar fechaAlta, Categoria categoria, Oficina oficinaActual, ArrayList<CarnetConducir> carnetsValidos, double autonomia, int nplazas, String tipo){
		super(matricula, marca, modelo, fechaAlta, categoria, oficinaActual, carnetsValidos, autonomia);
		setNplazas(nplazas);
		setTipo(tipo);
	}
	
//	METODOS
	
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
	@Override
		public String toString() {
			return super.toString() + "";
		}
	
}
