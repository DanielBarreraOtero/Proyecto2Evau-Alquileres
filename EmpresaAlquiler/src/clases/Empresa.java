package clases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeMap;

public class Empresa implements Serializable{

//	PROPIEDADES
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String CIF;
	private String Nombre;
	private TreeMap<String , Oficina > Oficinas;
	private TreeMap<String , Persona > Personas;
	private TreeMap<String , Vehiculo > Vehiculos;
	private TreeMap<String , Vehiculo > VehiculosAlquilados;
	private TreeMap<String , Alquiler > Alquileres;
	private TreeMap<String , Categoria > Categorias;
	private TreeMap<String , CarnetConducir > Carnets;
	
//	GETTERES Y SETTERS
	
	public String getCIF() {
		return CIF;
	}
	private void setCIF(String cIF) {
		CIF = cIF;
	}
	public String getNombre() {
		return Nombre;
	}
	private void setNombre(String nombre) {
		Nombre = nombre;
	}
	public TreeMap<String, Oficina> getOficinas() {
		return Oficinas;
	}
	private void setOficinas(TreeMap<String, Oficina> oficinas) {
		Oficinas = oficinas;
	}
	public TreeMap<String, Persona> getPersonas() {
		return Personas;
	}
	private void setPersonas(TreeMap<String, Persona> personas) {
		Personas = personas;
	}
	public TreeMap<String, Vehiculo> getVehiculos() {
		return Vehiculos;
	}
	private void setVehiculos(TreeMap<String, Vehiculo> vehiculos) {
		Vehiculos = vehiculos;
	}
//	
	public TreeMap<String, Vehiculo> getVehiculosAlquilados() {
		return VehiculosAlquilados;
	}
	private void setVehiculosAlquilados(TreeMap<String, Vehiculo> vehiculosAlquilados) {
		VehiculosAlquilados = vehiculosAlquilados;
	}
	public TreeMap<String, Alquiler> getAlquileres() {
		return Alquileres;
	}
	private void setAlquileres(TreeMap<String, Alquiler> alquileres) {
		Alquileres = alquileres;
	}
	public TreeMap<String, Categoria> getCategorias() {
		return Categorias;
	}
	private void setCategorias(TreeMap<String, Categoria> categorias) {
		Categorias = categorias;
	}
	public TreeMap<String, CarnetConducir> getCarnets() {
		return Carnets;
	}
	private void setCarnets(TreeMap<String, CarnetConducir> carnets) {
		Carnets = carnets;
	}
	
//	CONSTRUCTORES
	
	/**Constructor completo.
	 * 
	 * @param cIF
	 * @param nombre
	 * @param oficinas
	 * @param personas
	 * @param vehiculos
	 * @param vehiculosAlquilados
	 * @param alquileres
	 * @param categorias
	 * @param carnets
	 */
	public Empresa(String cIF, String nombre, TreeMap<String, Oficina> oficinas, TreeMap<String, Persona> personas, TreeMap<String, Vehiculo> vehiculos, TreeMap<String, Vehiculo> vehiculosAlquilados, TreeMap<String, Alquiler> alquileres, TreeMap<String, Categoria> categorias, TreeMap<String, CarnetConducir> carnets) {
		setCIF(cIF);
		setNombre(nombre);
		setOficinas(oficinas);
		setPersonas(personas);
		setVehiculos(vehiculos);
		setVehiculosAlquilados(vehiculosAlquilados);
		setAlquileres(alquileres);
		setCategorias(categorias);
		setCarnets(carnets);
	}
	/**Empresa vacia.
	 * 
	 * @param cIF
	 * @param nombre
	 */
	public Empresa(String cIF, String nombre) {
//		Generamos TreeMaps vacios para asegurarnos de que todas las empresas los tienen.
		TreeMap<String , Oficina > Oficinas = new TreeMap<String , Oficina >();
		TreeMap<String , Persona > Personas = new TreeMap<String , Persona >();
		TreeMap<String , Vehiculo > Vehiculos = new TreeMap<String , Vehiculo >();
		TreeMap<String , Vehiculo > VehiculosAlquilados = new TreeMap<String , Vehiculo >();
		TreeMap<String , Alquiler > Alquileres = new TreeMap<String , Alquiler >();
		TreeMap<String , Categoria > Categorias = new TreeMap<String , Categoria >();
		TreeMap<String, CarnetConducir> Carnets = new TreeMap<String, CarnetConducir>();
		
		setCIF(cIF);
		setNombre(nombre);
		setOficinas(Oficinas);
		setPersonas(Personas);
		setVehiculos(Vehiculos);
		setVehiculosAlquilados(VehiculosAlquilados);
		setAlquileres(Alquileres);
		setCategorias(Categorias);
		setCarnets(Carnets);
	}
	/**Constructor copia.
	 * 
	 * @param o
	 */
	public Empresa(Empresa o) {
		setCIF(o.getCIF());
		setNombre(o.getNombre());
		setOficinas(o.getOficinas());
		setPersonas(o.getPersonas());
		setVehiculos(o.getVehiculos());
		setVehiculosAlquilados(o.getVehiculosAlquilados());
		setAlquileres(o.getAlquileres());
		setCategorias(o.getCategorias());
		setCarnets(o.getCarnets());
	}
	
//	------METODOS------
	
//	Oficinas
	
	/**Añade la oficina o al TreeMap de Oficinas.
	 * 
	 * @param o
	 */
	public void AñadirOficina(Oficina o) {
		Oficinas.put(o.getCodigo(), o);
	}
	/**Elimina la oficina con key "codigo" del TreeMap de Oficinas.
	 * 
	 * @param codigo
	 */
	public void BorrarOficina(String codigo) {
		Oficinas.remove(codigo);
	}
	
//	Personas
	
	/**Añade una persona (cliente/empleado) al TreeMap de Personas.
	 * 
	 * @param o
	 */
	public void AñadirPersona(Persona o) {
		Personas.put(o.NombreCompleto(), o);
	}
	/**Elimina una persona (cliente/empleado) del TreeMap de Personas en base a su DNI.
	 * 
	 * @param dNI
	 */
	public void BorrarPersonaDNI(String dNI) {
		ArrayList<Persona> a = new ArrayList<Persona>(Personas.values()); /*Convertimos el TreeMap en un ArrayList*/
		
		int i = 0;
		while (!dNI.equals(a.get(i).getDNI())) { /*Recorremos el ArrayList hasta que damos con la persona que tiene el DNI que buscamos*/
			i++;
		}
		
//		Una vez sabemos a quien pertenece el DNI podemos utlizar el metodo NombreComopleto para obtener su Key
		Personas.remove(a.get(i).NombreCompleto());
	}
	/**Elimina una persona (cliente/empleado) del TreeMap de Personas en base a su NombreCompleto.
	 * 
	 * @param nombreCompleto
	 */
	public void BorrarPersonaNC(String nombreCompleto) {
		Personas.remove(nombreCompleto);
	}
	
//	Vehiculos
	
	/**Añade un vehiculo al TreeMap de Vehiculos.
	 * 
	 * @param o
	 */
	public void AñadirVehiculo(Vehiculo o) {
		Vehiculos.put(o.getMatricula(), o);
	}
	/**Elimina un vehiculo del TreeMap de Vehiculos en base a su matricula.
	 * 
	 * @param matricula
	 */
	public void BorrarVehiculo(String matricula) {
		Vehiculos.remove(matricula);
	}
	
//	Vehiculos Alquilados
	
	/**Añade un vehiculo al TreeMap de VehiculosAlquilados.
	 * 
	 * @param o
	 */
	public void AñadirVehiculoAlquilado(Vehiculo o) {
		VehiculosAlquilados.put(o.getMatricula(), o);
	}
	/**Elimina un vehiculo del TreeMap de VehiculosAlquilados.
	 * 
	 * @param matricula
	 */
	public void BorrarVehiculoAlquilado(String matricula) {
		VehiculosAlquilados.remove(matricula);
	}
	/**Añade un vehiculo al TreeMap de VehiculosAlquilados y lo elimina del de Vehiculos.
	 * 
	 * @param o
	 */
	public void AlquilarVehiculo(Vehiculo o) {
		AñadirVehiculoAlquilado(o);
		BorrarVehiculo(o.getMatricula());
	}
	/**Elimina un vehiculo del TreeMap de VehiculosAlquilados y lo añade al de Vehiculos.
	 * 
	 * @param o
	 */
	public void DesAlquilarVehiculo(Vehiculo o) {
		AñadirVehiculo(o);
		BorrarVehiculoAlquilado(o.getMatricula());
	}
	
//	Alquileres
	
	/**Añade un alquiler al TreeMap de Alquileres.
	 * 
	 * @param o
	 */
	public void AñadirAlquiler(Alquiler o) {
		Alquileres.put(o.getCodigo(), o);
	}
	/**Elimina un alquiler del TreeMap de Alquileres en base a su codigo.
	 * 
	 * @param codigo
	 */
	public void BorrarAlquiler(String codigo) {
		Alquileres.remove(codigo);
	}
	
//	Categorias
	
	/**Añade una categoria al TreeMap de Categorias.
	 * 
	 * @param o
	 */
	public void AñadirCategoria(Categoria o) {
		Categorias.put(o.getCodigo(), o);
	}
	/**Elimina una categoria del TreeMap de Categorias en base a su codigo.
	 * 
	 * @param codigo
	 */
	public void BorrarCategoria(String codigo) {
		Categorias.remove(codigo);
	}
	
//	Carnets de Conducir
	
	/**Añade un carnet al TreeMap de Carnets.
	 * 
	 * @param o
	 */
	public void AñadirCarnet(CarnetConducir o) {
		Carnets.put(o.getCodigo(), o);
	}
	/**Elimina un carnet del TreeMap de Carnets.
	 * 
	 * @param codigo
	 */
	public void BorrarCarnet(String codigo) {
		Carnets.remove(codigo);
	}
	
	
}
