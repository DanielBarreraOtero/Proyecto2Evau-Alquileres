package clases;

import java.io.Serializable;

import excepciones.CodigoInvalidoExcepcion;

public class Oficina implements Comparable<Oficina>, Serializable{

//	PROPIEDADES
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String Codigo; /* (es un alfanumérico de 4 letras : JA01, MA10…) */
	private String Descrp;
	private String Localidad;
	private String Provincia;
	private boolean Aeropuerto;

	//	GETTERS Y SETTERS
		
	public String getCodigo() {
		return Codigo;
	}
	private void setCodigo(String codigo) {
		try {
			if (codigo.length() == 4) {
				Codigo = codigo;
			} else {
			throw new CodigoInvalidoExcepcion();	
			}
		} catch (Exception e) {
		}
	}
	public String getDescrp() {
		return Descrp;
	}
	public void setDescrp(String descrp) {
		Descrp = descrp;
	}
	public String getLocalidad() {
		return Localidad;
	}
	private void setLocalidad(String localidad) {
		Localidad = localidad;
	}
	public String getProvincia() {
		return Provincia;
	}
	private void setProvincia(String provincia) {
		Provincia = provincia;
	}
	public boolean isAeropuerto() {
		return Aeropuerto;
	}
	private void setAeropuerto(boolean aeropuerto) {
		Aeropuerto = aeropuerto;
	}
	public boolean getAeropuerto() {
		return Aeropuerto;
	}
	
//	CONSTRUCTORES
	
	public Oficina(String codigo, String descrp, String localidad, String provincia, boolean aeropuerto) {
		setCodigo(codigo);
		setDescrp(descrp);
		setLocalidad(localidad);
		setProvincia(provincia);
		setAeropuerto(aeropuerto);
	}
	public Oficina(String codigo, String localidad, String provincia, boolean aeropuerto) {
		setCodigo(codigo);
		setLocalidad(localidad);
		setProvincia(provincia);
		setAeropuerto(aeropuerto);
	}
	public Oficina(Oficina o) {
		setCodigo(o.getCodigo());
		setDescrp(o.getDescrp());
		setLocalidad(o.getLocalidad());
		setProvincia(o.getProvincia());
		setAeropuerto(o.getAeropuerto());
	}
	public Oficina(String codigo) {
		setCodigo(codigo);
	}
	
	
	
//	METODOS
	
	@Override
	public String toString() {
		if(Aeropuerto) {
			return getCodigo() + ", " + getProvincia() + ", " + getLocalidad() + " | " + getDescrp() + "Se encuentra en un aeropuerto.";
		}else {
			return getCodigo() + ", " + getProvincia() + ", " + getLocalidad() + ", " + getDescrp() + "No se encuentra en un aeropuerto.";
		}
	}
	@Override
	public int compareTo(Oficina o) {
		return Codigo.compareTo(o.getCodigo());
	}
	@Override
	public boolean equals(Object o) {
		Oficina c = (Oficina) o;
		return Codigo.equals(c.getCodigo());
	}	
}
