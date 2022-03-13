package clases;

import java.time.LocalDate;

public class Cliente extends Persona{
	
//	PROPIEDADES
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CarnetConducir Carnet;
	private boolean TieneTarjeta;
	private String Ntarjeta;

//	GETTERS Y SETTERS
	
	public CarnetConducir getCarnet() {
		return Carnet;
	}

	public void setCarnet(CarnetConducir carnet) {
		CarnetConducir o = new CarnetConducir(carnet);
		Carnet = o;
	}

	public boolean isTieneTarjeta() {
		return TieneTarjeta;
	}

	public void setTieneTarjeta(boolean tieneTarjeta) {
		TieneTarjeta = tieneTarjeta;
	}

	public String getNtarjeta() {
		return Ntarjeta;
	}

	public void setNtarjeta(String ntarjeta) {
		Ntarjeta = ntarjeta;
	}
	
//	CONSTRUCTORES
	
	public Cliente(String dNI, String nombre, String ap1, String ap2, LocalDate fechaNac, CarnetConducir carnet, boolean tieneTarjeta, String nTarjeta) {
		super(dNI, nombre, ap1, ap2, fechaNac);
		setCarnet(carnet);
		setTieneTarjeta(tieneTarjeta);
		setNtarjeta(nTarjeta);
	}
	public Cliente(String dNI, String nombre, String ap1, String ap2, LocalDate fechaNac, CarnetConducir carnet, boolean tieneTarjeta) {
		super(dNI, nombre, ap1, ap2, fechaNac);
		setCarnet(carnet);
		setTieneTarjeta(tieneTarjeta);
		setNtarjeta("");
	}
	public Cliente(Cliente o) {
		super(o.getDNI(), o.getNombre(), o.getAp1(), o.getAp2(), o.getFechaNac());
		setCarnet(o.getCarnet());
		setTieneTarjeta(o.isTieneTarjeta());
		setNtarjeta(o.getNtarjeta());
	}
	public Cliente(String dNI) {
		super(dNI);
	}
	
//	METODOS
	
	@Override
	public String toString () {
		if (TieneTarjeta) {
			return getNombre() + ", " + getAp1() + " " + getAp2() + ", " + getEdad() + " años " + getFechaNac() + " | " + getDNI() + ", Carnet: " + Carnet.getCodigo() + ". Nº Tarjeta: " + Ntarjeta;
		}else {
			return getNombre() + ", " + getAp1() + " " + getAp2() + ", " + getEdad() + " años " + getFechaNac() + " | " + getDNI() + ", Carnet: " + Carnet.getCodigo() + ". No tiene tarjeta de cliente.";
		}
	}
	
}
