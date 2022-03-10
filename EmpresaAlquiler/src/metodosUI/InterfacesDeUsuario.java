package metodosUI;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

import clases.Alquiler;
import clases.Categoria;
import clases.Empresa;
import clases.Oficina;
import clases.Persona;
import clases.Vehiculo;
import metodos.Metodos;

public class InterfacesDeUsuario {

//	--- METODOS GENERICOS
	
	/**Menu con titulo y marco adpatable que muestra opciones y devuelve la eleccion introducida por el usuario validada.
	 * 
	 * @param titulo
	 * @param relleno
	 * @param opciones[]
	 * @param opcionesValidas
	 * @param separador - que separa las opciones validas
	 * @param MensOpc
	 * @param MensError
	 * @return String - eleccion validada
	 */
	public static String MenuOpciones(String titulo, String relleno, String[] opciones, String opcionesValidas, String separador, String MensOpc, String MensError) {
		String eleccion = "";
		String Titular;
		int longitudMenu;
		
//		Vamos a adaptar la longitud del menu a la opcion mas larga.
		if (opciones[Metodos.MayorVector(opciones)].length() > titulo.length() + 5) {
			longitudMenu = opciones[Metodos.MayorVector(opciones)].length() + 10;
		} else {
			longitudMenu = titulo.length() + 10;
		}
				
		Titular = Metodos.RodeaCadena(titulo, relleno, ((longitudMenu - titulo.length()) / 2 ) -1);
		Metodos.Titular(Titular, relleno);
		
		for (int i = 0; i < opciones.length; i++) {
			System.out.println(Metodos.CuadrarCadena(longitudMenu, opciones[i], "-", 2));
		}
		System.out.println(relleno.repeat(longitudMenu));
		
		System.out.print(MensOpc);
		eleccion = Metodos.ValidarCad(opcionesValidas, MensError, separador);
		
		System.out.println(eleccion);
		return eleccion;
	}
	
	public static void MostrarListado(TreeMap<Object, Object> lista, Object comparator) {
		
	}

	public static void MostrarListado(TreeMap<Object, Object> lista) {
		ArrayList<Object> L = new ArrayList<Object>(lista.values());
		
		for (Object i: L) {
			System.out.println(i);
		}
		
	}
	
//	--- GESTION DE OFICINAS ---
	
	/**Llama a otros metodos que gestionan oficinas dependiendo de la eleccion.
	 * 
	 * @param eleccion
	 * @param emp
	 */
	public static void GestionOficinas(String eleccion, Empresa emp) {
		String[] o = {"1 - CREAR NUEVA OFICINA", "2 - MODIFICAR OFICINA", "3 - ELIMINAR OFICINA","4 - LISTADOS", "5 - BUSCAR OFICINA", "6 - VOLVER AL MENÚ"};

		do {
			eleccion = MenuOpciones("GESTIÓN DE OFICINAS", "-", o, "1-2-3-4-5-6", "-", "Introduzca que opción quiere elegir: ", "Esa opción no es válida, pruebe de nuevo.");

			switch (eleccion) {
			case "1": {
				CrearOficina(emp);
				break;
			}
			case "2": {
				ModificaOficina(emp);
				break;
			}
			case "3": {
				EliminaOficina(emp);
				break;
			}
			case "4": {
				ListadosOficina(emp);
				break;
			}
			case "5": {
				BuscarOficina(emp);
				break;
			}
			}

		} while (!eleccion.equals("6"));
	}
	/**Pide al usuario datos y los valída, después añade la nueva oficina al TreeMap.
	 * 
	 * @param emp
	 */
	public static void CrearOficina(Empresa emp) {
		@SuppressWarnings("resource")
		Scanner lector = new Scanner(System.in);
		String codigo, descrp, localidad, provincia, aero;
		boolean aeropuerto = false;
		Oficina o;
		
		Metodos.Titular(Metodos.RodeaCadena("NUEVA OFICINA", "-", 5), "-");
		
		do {
			System.out.println("Código: ");
			codigo = lector.nextLine();
			
			if (codigo.length() != 4 ) {
				System.out.println("Código ínvalido, el código debe de tener 4 caracteres.");
			}
		} while(codigo.length() != 4 );
		
		System.out.println("Descripción: ");
		descrp = lector.nextLine();
		
		System.out.println("Provincia: ");
		provincia = lector.nextLine();
		
		System.out.println("Localidad: ");
		localidad = lector.nextLine();
		
		do {
			System.out.println("Está en un aeropuerto(S/N): ");
			aero = lector.nextLine();
			
			if (!aero.equalsIgnoreCase("S") && !aero.equalsIgnoreCase("N")) {
				System.out.println("Carácter ínvalido.");
			} else if(aero.equalsIgnoreCase("S")) {
				aeropuerto = true;
			}
		} while(!aero.equalsIgnoreCase("S") && !aero.equalsIgnoreCase("N"));
		
		if (!descrp.equals("")) {
			o = new Oficina(codigo, descrp, localidad, provincia, aeropuerto);
		} else {
			o = new Oficina(codigo, localidad, provincia, aeropuerto);
		}
		
		emp.AñadirOficina(o);
	}
	/**TODO Le pide al usuario la key de una oficina, la borra y llama al metodo de crear una nueva oficina pero colocando la misma key.
	 * 
	 * @param emp
	 */
	public static void ModificaOficina(Empresa emp) {
		
	}
	/**Le pide al usuario la key de una oficina, la borra.
	 * 
	 * @param emp
	 */
	public static void EliminaOficina(Empresa emp) {
		Scanner lector = new Scanner(System.in);
		String cod;
		
		Metodos.Titular(Metodos.RodeaCadena("ELIMINAR OFICINA", "-", 5), "-");
		System.out.print("Código de la oficina que desea eliminar: ");
		cod = lector.nextLine();
		
		emp.BorrarOficina(cod);
	}
	/**TODO Muestra el menu de listados de oficina al usuario y llama al metodo del listado que elija.
	 * 
	 * @param emp
	 */
	public static void ListadosOficina(Empresa emp) {
		String[] l = {"1 - CÓDIGO", "2 - PROVINCIA", "3 - LOCALIDAD","4 - SI ES AEROPUERTO", "5 - ATRÁS"};
		String eleccion;
		
		do {
			eleccion = MenuOpciones("ORDENAR POR:", "-", l, "1-2-3-4-5", "-", "Introduzca que opción quiere elegir: ", "Esa opción no es válida, pruebe de nuevo.");

			switch (eleccion) {
			case "1": {

			}
			case "2": {

			}
			case "3": {

			}
			case "4": {

			}
			}
		} while (!eleccion.equals("5"));
	}
	/**TODO Pide al usuario la key de una oficina y muestra los datos de esta.
	 * 
	 * @param emp
	 */
	public static void BuscarOficina(Empresa emp) {
		
	}
	
	
//	--- GESTION DE CLIENTES ---
	
	/**TODO Llama a otros metodos que gestionan clientes dependiendo de la eleccion.
	 * 
	 * @param eleccion
	 * @param emp
	 */
	public static void GestionClientes(String eleccion, Empresa emp) {
		String[] o = {"1 - CREAR NUEVO CLIENTE", "2 - MODIFICAR CLIENTE", "3 - ELIMINAR CLIENTE","4 - LISTADOS", "5 - ATRÁS"};
//		TODO HACER 
		do {
			eleccion = InterfacesDeUsuario.MenuOpciones("GESTIÓN DE CLIENTES", "-", o, "1-2-3-4", "-", "Introduzca que opción quiere elegir: ", "Esa opción no es válida, pruebe de nuevo.");

			switch (eleccion) {
			case "1": {

			}
			case "2": { 

			}
			case "3": {

			}
			case "4": {

			}
			}

		} while (!eleccion.equalsIgnoreCase("5"));
	}
	
	
	
	
	
//	--- GESTION DE EMPLEADOS ---
//	TODO
	
//	--- GESTION DE ALQUILERES ---	
//	TODO
	
//	--- GESTION DE CATEGORIAS ---
//	TODO	
	
//	--- GESTION DE VEHICULOS ---
//	TODO
	
	
	
	/**Pide datos al usuario y crea una empresa vacia, solo con nombre y cif.
	 * 
	 * @return
	 */
	public static Empresa CrearEmpresa() {
		@SuppressWarnings("resource")
		Scanner lector = new Scanner(System.in);
		
		String nombre;
		String cIF;
		
		Metodos.Titular(Metodos.RodeaCadena("NUEVA EMPRESA", "-", 5), "-");
		
		System.out.print("Nombre de la Empresa: ");
		nombre = lector.nextLine();
		

		System.out.print("CIF de la Empresa: ");
		cIF = lector.nextLine();
				
		Empresa E = new Empresa(cIF, nombre);
		return E;
	}
	
	

	
	
	
	
	
	
	
	
	
}
