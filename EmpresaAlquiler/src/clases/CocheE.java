package clases;

import java.time.LocalDate;
import java.util.ArrayList;

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
	public CocheE(String matricula, String marca, String modelo, String color, LocalDate fechaAlta, double kms, Categoria categoria, Oficina oficinaActual, ArrayList<CarnetConducir> carnetsValidos, double autonomia, double tiempoRecarga, int nplazas, String tipo) {
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
	public CocheE(String matricula, String marca, String modelo, LocalDate fechaAlta, Categoria categoria, Oficina oficinaActual, ArrayList<CarnetConducir> carnetsValidos, double autonomia, int nplazas, String tipo){
		super(matricula, marca, modelo, fechaAlta, categoria, oficinaActual, carnetsValidos, autonomia);
		setNplazas(nplazas);
		setTipo(tipo);
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
