package metodosUI;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

import clases.Alquiler;
import clases.CarnetConducir;
import clases.Categoria;
import clases.Cliente;
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
		
		System.out.println();
		return eleccion;
	}
//	TODO 
	public static void MostrarListado(TreeMap<String, Object> lista, Object comparator) {
		
	}
//	TODO 
	public static void MostrarListado(TreeMap<String, Object> lista) {
		ArrayList<Object> L = new ArrayList<Object>(lista.values());
		
		for (Object i: L) {
			System.out.println(i);
		}
		
	}
	
//	--- GESTION DE OFICINAS ---
	
	/**Pide al usuario una eleccion y llama a otros metodos que gestionan oficinas dependiendo de esta..
	 * 
	 * @param eleccion
	 * @param emp
	 */
	public static void GestionOficinas(Empresa emp) {
		String[] o = {"1 - CREAR NUEVA OFICINA", "2 - MODIFICAR OFICINA", "3 - ELIMINAR OFICINA","4 - LISTADOS", "5 - BUSCAR OFICINA", "6 - VOLVER AL MENÚ"};
		String eleccion;

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
			System.out.print("Código: ");
			codigo = lector.nextLine();
			
			if (codigo.length() != 4 ) {
				System.out.println("Código ínvalido, el código debe de tener 4 caracteres.");
			}
		} while(codigo.length() != 4 );
		
		System.out.print("Descripción: ");
		descrp = lector.nextLine();
		
		System.out.print("Provincia: ");
		provincia = lector.nextLine();
		
		System.out.print("Localidad: ");
		localidad = lector.nextLine();
		
		do {
			System.out.print("Está en un aeropuerto(S/N): ");
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
//	TODO COMPROBAR SI AL CREAR UNA NUEVA OFICINA CON UNA MISMA KEY, SOBRESCRIBE LA ANTERIOR, cuando tenga listados.
	/**Le pide al usuario la key de una oficina, la borra y crea una nueva oficina pero colocando la misma key.
	 * 
	 * @param emp
	 */
	public static void ModificaOficina(Empresa emp) {
		@SuppressWarnings("resource")
		Scanner lector = new Scanner(System.in);
		String cod, descrp, localidad, provincia, aero;
		boolean aeropuerto = false, ofiExiste = false;
		Oficina o;
		
		Metodos.Titular(Metodos.RodeaCadena("MODIFICAR OFICINA", "-", 5), "-");
		
		do {
			System.out.print("Código de la oficina que quiere modificar: ");
			cod = lector.nextLine();

			if (cod.length() != 4 ) {
				System.out.println("Código ínvalido, el código debe de tener 4 caracteres.");
			} 
			else if(!emp.getOficinas().containsKey(cod)) { //Comprueba que la oficina exista dentro del TreeMap.
				System.out.println("La oficina que intenta modificar no existe, mire el listado e intentelo de nuevo.");
			} else {
				ofiExiste = true;
			}
		} while(cod.length() != 4 );

		if (ofiExiste) { //Solo borra y crea la oficina si esta existe. 
			emp.BorrarOficina(cod);

			
			System.out.print("Descripción: ");
			descrp = lector.nextLine();

			System.out.print("Provincia: ");
			provincia = lector.nextLine();

			System.out.print("Localidad: ");
			localidad = lector.nextLine();

			do {
				System.out.print("Está en un aeropuerto(S/N): ");
				aero = lector.nextLine();

				if (!aero.equalsIgnoreCase("S") && !aero.equalsIgnoreCase("N")) {
					System.out.println("Carácter ínvalido.");
				} else if(aero.equalsIgnoreCase("S")) {
					aeropuerto = true;
				}
			} while(!aero.equalsIgnoreCase("S") && !aero.equalsIgnoreCase("N"));

			if (!descrp.equals("")) {
				o = new Oficina(cod, descrp, localidad, provincia, aeropuerto);
			} else {
				o = new Oficina(cod, localidad, provincia, aeropuerto);
			}

			emp.AñadirOficina(o);
		}
	}
	/**Le pide al usuario la key de una oficina y la borra.
	 * 
	 * @param emp
	 */
	public static void EliminaOficina(Empresa emp) {
		@SuppressWarnings("resource")
		Scanner lector = new Scanner(System.in);
		String cod;
		
		Metodos.Titular(Metodos.RodeaCadena("ELIMINAR OFICINA", "-", 5), "-");
		System.out.print("Código de la oficina que desea eliminar: ");
		cod = lector.nextLine();
		
		if(!emp.getOficinas().containsKey(cod)) { //Comprueba que la oficina exista dentro del TreeMap.
			System.out.println("La oficina que busca no existe, mire el listado e intentelo de nuevo.");
		} else {
			emp.BorrarOficina(cod);
		}
		System.out.println();
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
//				TODO
				MostrarListado(emp.getOficinas());
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
	/**Pide al usuario la key de una oficina y muestra los datos de esta.
	 * 
	 * @param emp
	 */
	public static void BuscarOficina(Empresa emp) {
		@SuppressWarnings("resource")
		Scanner lector = new Scanner(System.in);
		String cod;
		boolean ofiExiste = false;
		
		do {
			System.out.print("Código de la oficina que quiere buscar: ");
			cod = lector.nextLine();

			if (cod.length() != 4 ) {
				System.out.println("Código ínvalido, el código debe de tener 4 caracteres.");
			} 
			else if(!emp.getOficinas().containsKey(cod)) { //Comprueba que la oficina exista dentro del TreeMap.
				System.out.println("La oficina que busca no existe, mire el listado e intentelo de nuevo.");
			} else {
				ofiExiste = true;
			}
		} while(cod.length() != 4 );
		
		if (ofiExiste) {
			System.out.println(emp.getOficinas().get(cod));
		}
		System.out.println();
	}
	
//	--- GESTION DE CLIENTES ---
	
	/**TODO Pide al usuario una eleccion y llama a otros metodos que gestionan clientes dependiendo de esta.
	 * 
	 * @param eleccion
	 * @param emp
	 */
	public static void GestionClientes(Empresa emp) {
		String[] o = {"1 - CREAR NUEVO CLIENTE", "2 - MODIFICAR CLIENTE", "3 - ELIMINAR CLIENTE","4 - LISTADOS", "5 - ATRÁS"};
		String eleccion;
//		TODO HACER 
		do {
			eleccion = InterfacesDeUsuario.MenuOpciones("GESTIÓN DE CLIENTES", "-", o, "1-2-3-4-5", "-", "Introduzca que opción quiere elegir: ", "Esa opción no es válida, pruebe de nuevo.");

			switch (eleccion) {
			case "1": {
				
				break;
			}
			case "2": { 
				
				break;
			}
			case "3": {

				break;
			}
			case "4": {

				break;
			}
			}

		} while (!eleccion.equalsIgnoreCase("5"));
	}
	
	public static void CrearCliente(Empresa emp) {
		@SuppressWarnings("resource")
		Scanner lector = new Scanner(System.in);
		String dNI, nombre, ap1, ap2, carnet, Ntarjeta;
		int dia, mes, año;
		boolean TieneTarjeta = false;
		Oficina o;
		
		Metodos.Titular(Metodos.RodeaCadena("NUEVO CLIENTE", "-", 5), "-");
		
		
		
		Cliente c = new Cliente(dNI, nombre, ap1, ap2, null, null, TieneTarjeta, Ntarjeta);
	}
	
	
	
//	--- GESTION DE EMPLEADOS ---
//	TODO
	
	
//	--- GESTION DE VEHICULOS ---
//	TODO
	
	
	
	
	
	
	
	
	
//	--- GESTION DE ALQUILERES ---	
//	TODO
	
//	--- GESTION DE CATEGORIAS ---
//	TODO	
	
//	--- GESTION DE CARNETS ---
	
	/**Pide al usuario una eleccion y llama a otros metodos que gestionan carnets dependiendo de esta.
	 * 
	 * @param emp
	 */
	public static void GestionCarnets(Empresa emp) {
		String[] o = {"1 - CREAR NUEVO CARNET", "2 - MODIFICAR CARNET", "3 - ELIMINAR CARNET","4 - LISTADO", "5 - BUSCAR CARNET", "6 - VOLVER AL MENÚ"};
		String eleccion;

		do {
			eleccion = InterfacesDeUsuario.MenuOpciones("GESTIÓN DE CARNETS", "-", o, "1-2-3-4-5-6", "-", "Introduzca que opción quiere elegir: ", "Esa opción no es válida, pruebe de nuevo.");

			switch (eleccion) {
			case "1": {
				CrearCarnet(emp);
				break;
			}
			case "2": { 
				ModificarCarnet(emp);
				break;
			}
			case "3": {
				Eliminacarnet(emp);
				break;
			}
			case "4": {
				ListadoCarnet(emp);
				break;
			}
			case "5": {
				BuscarCarnet(emp);
				break;
			}
			}

		} while (!eleccion.equalsIgnoreCase("6"));
	}
	/**Pide al usuario datos y después añade el nuevo carnet al TreeMap.
	 * 
	 * @param emp
	 */
	public static void CrearCarnet(Empresa emp) {
		@SuppressWarnings("resource")
		Scanner lector = new Scanner(System.in);
		String codigo, descrp;
		CarnetConducir o;
		
		Metodos.Titular(Metodos.RodeaCadena("NUEVO CARNET", "-", 5), "-");
		
		System.out.print("Código: ");
		codigo = lector.nextLine();
		
		System.out.print("Descripción: ");
		descrp = lector.nextLine();
		
		if (!descrp.equals("")) {
			o = new CarnetConducir(codigo, descrp);
		} else {
			o = new CarnetConducir(codigo);
		}
		
		emp.AñadirCarnet(o);
	}
	/**Le pide al usuario la key de un carnet, lo borra y llama al metodo de crear un nuevo carnet pero colocando la misma key.
	 * 
	 * @param emp
	 */
	public static void ModificarCarnet(Empresa emp) {
		@SuppressWarnings("resource")
		Scanner lector = new Scanner(System.in);
		String cod, descrp;
		boolean carExiste = false;
		CarnetConducir o;

		Metodos.Titular(Metodos.RodeaCadena("MODIFICAR CARNET", "-", 5), "-");

		System.out.print("Código del carnet que quiere modificar: ");
		cod = lector.nextLine();

		if(!emp.getCarnets().containsKey(cod)) { //Comprueba que el carnet exista dentro del TreeMap.
			System.out.println("El carnet que intenta modificar no existe, mire el listado e intentelo de nuevo.");
		} else {
			carExiste = true;
		}

		if (carExiste) { //Solo borra y crea el carnet si este existe. 
			emp.BorrarCarnet(cod);

			System.out.print("Descripción: ");
			descrp = lector.nextLine();

			if (!descrp.equals("")) {
				o = new CarnetConducir(cod, descrp);
			} else {
				o = new CarnetConducir(cod);
			}

			emp.AñadirCarnet(o);
		}
	}
	/**Le pide al usuario la key de un carnet y lo borra.
	 * 
	 * @param emp
	 */
	public static void Eliminacarnet(Empresa emp) {
		@SuppressWarnings("resource")
		Scanner lector = new Scanner(System.in);
		String cod;
		
		Metodos.Titular(Metodos.RodeaCadena("ELIMINAR CARNET", "-", 5), "-");
		System.out.print("Código del carnet que desea eliminar: ");
		cod = lector.nextLine();
		
		if(!emp.getCarnets().containsKey(cod)) { //Comprueba que el carnet exista dentro del TreeMap.
			System.out.println("El carnet que busca no existe, mire el listado e intentelo de nuevo.");
		} else {
			emp.BorrarCarnet(cod);
		}
		System.out.println();
	}
	/**TODO muestra un listado de todos los carnets.
	 * 
	 * @param emp
	 */
	public static void ListadoCarnet(Empresa emp) {
	
	}
	/**Pide al usuario la key de un carnet y muestra los datos de este.
	 * 
	 * @param emp
	 */
	public static void BuscarCarnet(Empresa emp) {
		@SuppressWarnings("resource")
		Scanner lector = new Scanner(System.in);
		String cod;
		boolean carExiste = false;

		System.out.print("Código del carnet que quiere buscar: ");
		cod = lector.nextLine();

		if(!emp.getOficinas().containsKey(cod)) { //Comprueba que el carnet exista dentro del TreeMap.
			System.out.println("La oficina que busca no existe, mire el listado e intentelo de nuevo.");
		} else {
			carExiste = true;
		}
		
		if (carExiste) {
			System.out.println(emp.getCarnets().get(cod));
		}
		System.out.println();
	}
	
	
	
	
	
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
