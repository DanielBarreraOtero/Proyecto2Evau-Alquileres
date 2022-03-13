package metodosUI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

import clases.Alquiler;
import clases.CarnetConducir;
import clases.Categoria;
import clases.Cliente;
import clases.CocheC;
import clases.CocheE;
import clases.Empleado;
import clases.Empresa;
import clases.Furgoneta;
import clases.Moto;
import clases.Oficina;
import clases.Persona;
import clases.Vehiculo;
import metodos.Metodos;
import miscomparadores.CategoriaRecargo;
import miscomparadores.ClienteNTarjeta;
import miscomparadores.EmpleadoFechaAlta;
import miscomparadores.EmpleadoOficina;
import miscomparadores.OficinaAeropuerto;
import miscomparadores.OficinaLocalidad;
import miscomparadores.OficinaProvincia;
import miscomparadores.VehiculoCategoria;
import miscomparadores.VehiculoFechaAlta;
import miscomparadores.VehiculoKms;
import miscomparadores.VehiculoMarca;
import miscomparadores.VehiculoOficinaActual;

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
	/**Convierte el Treemap en Arraylist, lo ordena por el comparator elegido y muestra un listado.
	 * 
	 * @param <T>
	 * @param lista
	 * @param comparator
	 */
	@SuppressWarnings("unchecked")
	public static <T> void MostrarListado(TreeMap<String, Object> lista, Object comparator) {
		//		Para poder hacer que el metodo funcione con todos los objetos, tenemos que castear el arraylist y el comparator
		ArrayList<T> L = (ArrayList<T>) new ArrayList<Object>(lista.values());
		Comparator<? super T> compa = (Comparator<? super T>) comparator;
		Collections.sort(L, compa);

		for (Object i: L) {
			System.out.println(i);
		}
	}
	/**Convierte el Treemap en Arraylist y muestra un listado.
	 * 
	 * @param lista
	 */
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
				Metodos.Titular(Metodos.RodeaCadena("NUEVA OFICINA", "-", 5), "-");
				CrearOficina(emp);
				break;
			}
			case "2": {
				Metodos.Titular(Metodos.RodeaCadena("MODIFICAR OFICINA", "-", 5), "-");
				ModificaOficina(emp);
				break;
			}
			case "3": {
				Metodos.Titular(Metodos.RodeaCadena("ELIMINAR OFICINA", "-", 5), "-");
				EliminaOficina(emp);
				break;
			}
			case "4": {
				Metodos.Titular(Metodos.RodeaCadena("LISTADOS DE OFICINA", "-", 5), "-");
				ListadosOficina(emp);
				break;
			}
			case "5": {
				Metodos.Titular(Metodos.RodeaCadena("BUSCAR OFICINA", "-", 5), "-");
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

		do {
			System.out.print("Código (salir si quiere cancelar): ");
			codigo = lector.nextLine();

			if (codigo.length() != 4 ) {
				System.out.println("Código ínvalido, el código debe de tener 4 caracteres.");
			} 
			else if (emp.getOficinas().containsKey(codigo)) {
				System.out.println("Código ínvalido, ya existe una oficina con este código.");
			}
		} while((codigo.length() != 4 || emp.getOficinas().containsKey(codigo)) && !codigo.equalsIgnoreCase("SALIR"));

		if (!codigo.equalsIgnoreCase("SALIR")) {
		
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
	}
	/**Le pide al usuario la key de una oficina y crea una nueva oficina pero colocando la misma key.
	 * 
	 * @param emp
	 */
	public static void ModificaOficina(Empresa emp) {
		@SuppressWarnings("resource")
		Scanner lector = new Scanner(System.in);
		String cod, descrp, localidad, provincia, aero;
		boolean aeropuerto = false, ofiExiste = false;
		Oficina o;

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

		if (ofiExiste) { //Solo sobreescribe la oficina si esta existe. 
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

			System.out.println(emp.getOficinas().get(cod));
			System.out.println("");
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

		System.out.print("Código de la oficina que desea eliminar: ");
		cod = lector.nextLine();

		if(!emp.getOficinas().containsKey(cod)) { //Comprueba que la oficina exista dentro del TreeMap.
			System.out.println("La oficina que busca no existe, mire el listado e intentelo de nuevo.");
		} else {
			emp.BorrarOficina(cod);
		}
		System.out.println();
	}
	/**Muestra el menu de listados de oficina al usuario y llama al metodo del listado que elija.
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
				MostrarListado(new TreeMap<String, Object>(emp.getOficinas()));
				break;
			}
			case "2": {
				MostrarListado(new TreeMap<String, Object>(emp.getOficinas()), new OficinaProvincia());
				break;
			}
			case "3": {
				MostrarListado(new TreeMap<String, Object>(emp.getOficinas()), new OficinaLocalidad());
				break;
			}
			case "4": {
				MostrarListado(new TreeMap<String, Object>(emp.getOficinas()), new OficinaAeropuerto());
				break;
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

	/**Pide al usuario una eleccion y llama a otros metodos que gestionan clientes dependiendo de esta.
	 * 
	 * @param eleccion
	 * @param emp
	 */
	public static void GestionClientes(Empresa emp) {
		String[] o = {"1 - CREAR NUEVO CLIENTE", "2 - MODIFICAR CLIENTE", "3 - ELIMINAR CLIENTE","4 - LISTADOS", "5 - BUSCAR CLIENTE", "6 - ATRÁS"};
		String eleccion;
		do {
			eleccion = InterfacesDeUsuario.MenuOpciones("GESTIÓN DE CLIENTES", "-", o, "1-2-3-4-5-6", "-", "Introduzca que opción quiere elegir: ", "Esa opción no es válida, pruebe de nuevo.");

			switch (eleccion) {
			case "1": {
				Metodos.Titular(Metodos.RodeaCadena("NUEVO CLIENTE", "-", 5), "-");
				CrearCliente(emp);
				break;
			}
			case "2": { 
				Metodos.Titular(Metodos.RodeaCadena("MODIFICAR CLIENTE", "-", 5), "-");
				ModificarCliente(emp);
				break;
			}
			case "3": {
				Metodos.Titular(Metodos.RodeaCadena("ELIMINAR CLIENTE", "-", 5), "-");
				EliminarCliente(emp);
				break;
			}
			case "4": {
				Metodos.Titular(Metodos.RodeaCadena("LISTADOS CLIENTE", "-", 5), "-");
				ListadosCliente(emp);
				break;
			}
			case "5": {
				Metodos.Titular(Metodos.RodeaCadena("BUSCAR CLIENTE", "-", 5), "-");
				BuscarCliente(emp);
				break;
			}
			}
		} while (!eleccion.equalsIgnoreCase("6"));
	}
	/**Pide al usuario datos y después añade el nuevo Cliente al TreeMap.
	 * 
	 * @param emp
	 */
	public static void CrearCliente(Empresa emp) {
		@SuppressWarnings("resource")
		Scanner lector = new Scanner(System.in);
		String dNI, nombre, ap1, ap2, nomcompl, carnet, Ntarjeta, TJ;
		Set<String> Carnets;
		int dia, mes, año;
		Cliente o;
		boolean cliExiste, TieneTarjeta = false;

		System.out.print("Nombre: ");
		nombre = lector.nextLine();

		System.out.print("Primer apellido: ");
		ap1 = lector.nextLine();

		System.out.print("Segundo apellido: ");
		ap2 = lector.nextLine();

		nomcompl = ap1 + " " + ap2 + ", " + nombre; 

		cliExiste = emp.getClientes().containsKey(nomcompl);

		if (!cliExiste) {
//			TODO CONTROLAR QUE EL DNI NO EXISTA
			do {
				System.out.print("DNI: ");
				dNI = lector.nextLine();

				if (!Metodos.ValidarDNI(dNI)) {
					System.out.println("DNI inválido.");
				}
			} while(!Metodos.ValidarDNI(dNI));

			Carnets = emp.getCarnets().keySet();

			for (String i: Carnets) {
				System.out.print(i + " / ");
			}
			System.out.println("");

			do {
				System.out.println("Elija un carnet de conducir: ");
				carnet = lector.nextLine();

				if(!emp.getCarnets().containsKey(carnet) && !carnet.equalsIgnoreCase("SALIR")) {
					System.out.println("Ese carnet no existe, pruebe de nuevo o escriba salir para cancelar (no se creará el cliente).");
				}

			} while (!emp.getCarnets().containsKey(carnet) && !carnet.equalsIgnoreCase("SALIR"));

			if(!carnet.equalsIgnoreCase("SALIR")) {
				System.out.println("");
				System.out.println("Fecha de Nacimiento.");
				System.out.print("Día: ");
				dia = lector.nextInt();

				System.out.print("Mes: ");
				mes = lector.nextInt();

				System.out.print("Año: ");
				año = lector.nextInt();
				lector.nextLine();

				GregorianCalendar FecNac = new GregorianCalendar(año, mes, dia);

				System.out.println("");

				do {
					System.out.print("¿Tiene tarjeta de Cliente?(S/N): ");
					TJ = lector.nextLine();

					if (!TJ.equalsIgnoreCase("S") && !TJ.equalsIgnoreCase("N")) {
						System.out.println("Carácter ínvalido.");
					} else if(TJ.equalsIgnoreCase("S")) {
						TieneTarjeta = true;
					}
				} while(!TJ.equalsIgnoreCase("S") && !TJ.equalsIgnoreCase("N"));

				if(!TieneTarjeta) {
					o = new Cliente(dNI, nombre, ap1, ap2, FecNac, emp.getCarnets().get(carnet), TieneTarjeta);
				} else {
					System.out.print("Número de Tarjeta: ");
					Ntarjeta = lector.nextLine();

					o = new Cliente(dNI, nombre, ap1, ap2, FecNac, emp.getCarnets().get(carnet), TieneTarjeta, Ntarjeta);
				}
				emp.AñadirCliente(o);
			} else {
				System.out.println("Es necesario un carnet de conducir, el cliente no se ha creado.");
			}
		} else {
			System.out.println("El cliente " + nomcompl + " ya existe.");

		}
		System.out.println("");
	}
	/**Le pide al usuario la key de un cliente y llama al metodo de crear un nuevo cliente pero colocando la misma key.
	 * 
	 * @param emp
	 */
	public static void ModificarCliente(Empresa emp) {
		@SuppressWarnings("resource")
		Scanner lector = new Scanner(System.in);
		String nomComp, dNI, carnet, Ntarjeta, TJ;
		int dia, mes, año;
		boolean cliExiste = false, TieneTarjeta = false;;
		Cliente o, c;
		Set<String> Carnets;

		System.out.println("Nombre completo del cliente que quiere modificar: ");
		nomComp = lector.nextLine();

		//Comprueba que el cliente exista dentro del TreeMap.
		if(!emp.getClientes().containsKey(nomComp)) { 
			System.out.println("El cliente que intenta modificar no existe, mire el listado e intentelo de nuevo.");
		} else {
			cliExiste = true;
		}

		if (cliExiste) { //Solo sobreescribe el empleado si este existe. 

			c = (Cliente) emp.getClientes().get(nomComp);

			do {
				System.out.print("DNI: ");
				dNI = lector.nextLine();

				if (!Metodos.ValidarDNI(dNI)) {
					System.out.println("DNI inválido.");
				}
			} while(!Metodos.ValidarDNI(dNI));

			Carnets = emp.getCarnets().keySet();

			for (String i: Carnets) {
				System.out.print(i + " / ");
			}
			System.out.println("");

			do {
				System.out.println("Elija un carnet de conducir: ");
				carnet = lector.nextLine();

				if(!emp.getCarnets().containsKey(carnet) && !carnet.equalsIgnoreCase("SALIR")) {
					System.out.println("Ese carnet no existe, pruebe de nuevo o escriba salir para cancelar (no se modificará el cliente).");
				}
			} while (!emp.getCarnets().containsKey(carnet) && !carnet.equalsIgnoreCase("SALIR"));

			System.out.println("");
			System.out.println("Fecha de Nacimiento.");
			System.out.print("Día: ");
			dia = lector.nextInt();

			System.out.print("Mes: ");
			mes = lector.nextInt();

			System.out.print("Año: ");
			año = lector.nextInt();
			lector.nextLine();

			GregorianCalendar FecNac = new GregorianCalendar(año, mes, dia);

			do {
				System.out.print("¿Tiene tarjeta de Cliente?(S/N): ");
				TJ = lector.nextLine();

				if (!TJ.equalsIgnoreCase("S") && !TJ.equalsIgnoreCase("N")) {
					System.out.println("Carácter ínvalido.");
				} else if(TJ.equalsIgnoreCase("S")) {
					TieneTarjeta = true;
				}
			} while(!TJ.equalsIgnoreCase("S") && !TJ.equalsIgnoreCase("N"));


			if(!carnet.equalsIgnoreCase("SALIR")) {

				if(!TieneTarjeta) {
					o = new Cliente(dNI, c.getNombre(), c.getAp1(), c.getAp2(), FecNac, emp.getCarnets().get(carnet), TieneTarjeta);
				} else {
					System.out.print("Número de Tarjeta: ");
					Ntarjeta = lector.nextLine();

					o = new Cliente(dNI, c.getNombre(), c.getAp1(), c.getAp2(), FecNac, emp.getCarnets().get(carnet), TieneTarjeta, Ntarjeta);
				}
				emp.AñadirCliente(o);
				System.out.println(emp.getClientes().get(nomComp));
			} else {
				System.out.println("Es necesaria un carnet, el cliente no se ha modificado.");
			}

			System.out.println("");
		}
	}
	/**Le pide al usuario la key de un cliente y lo borra.
	 * 
	 * @param emp
	 */
	public static void EliminarCliente(Empresa emp) {
		@SuppressWarnings("resource")
		Scanner lector = new Scanner(System.in);
		String nom;
		boolean cliExiste;

		System.out.println("Nombre completo del cliente que desea eliminar: ");
		nom = lector.nextLine();

//		Comprueba que el nombre exista dentro del Treemap de Empleados.
		cliExiste = (emp.getClientes().containsKey(nom));

		if(!cliExiste) { 
			System.out.println("El cliente que busca no existe, mire el listado e intentelo de nuevo.");
		} else {
			emp.BorrarCliente(nom);
		}
		System.out.println();
	}
	/**Muestra el menu de listados de cliente al usuario y llama al metodo del listado que elija.
	 * 
	 * @param emp
	 */
	public static void ListadosCliente(Empresa emp) {
		String[] l = {"1 - NOMBRE", "2 - Número tarjeta", "3 - ATRÁS"};
		String eleccion;

		do {
			eleccion = MenuOpciones("ORDENAR POR:", "-", l, "1-2-3", "-", "Introduzca que opción quiere elegir: ", "Esa opción no es válida, pruebe de nuevo.");

			switch (eleccion) {
			case "1": {
				MostrarListado(new TreeMap<String, Object>(emp.getClientes()));
				break;
			}
			case "2": {
				MostrarListado(new TreeMap<String, Object>(emp.getClientes()), new ClienteNTarjeta());
				break;
			}
			}
		} while (!eleccion.equals("3"));
	}
	/**Pide al usuario la key de un cliente y muestra los datos de este.
	 * 
	 * @param emp
	 */
	public static void BuscarCliente(Empresa emp) {
		@SuppressWarnings("resource")
		Scanner lector = new Scanner(System.in);
		String nom;
		boolean clilExiste = false;

		System.out.println("Puede escribir 'salir' para cancelar.");
		do {
			System.out.println("Nombre Completo del cliente que quiere buscar (Ap1 Ap2, Nombre): ");
			nom = lector.nextLine();

//			Comprueba que el nombre exista dentro del Treemap de Clientes.
			if(!emp.getClientes().containsKey(nom)) {
				if (!nom.equalsIgnoreCase("SALIR")) {
					System.out.println("El cliente que busca no existe, mire el listado e intentelo de nuevo.");
				}
			} else {
				clilExiste = true;
			}
		} while(!emp.getClientes().containsKey(nom) && !nom.equalsIgnoreCase("SALIR"));

		if (clilExiste) {
//			TODO CHECKEAR EL TOSTRING FECHAS MAL
			System.out.println(emp.getClientes().get(nom));
		}
		System.out.println();
	}
	

	
	//	--- GESTION DE EMPLEADOS ---
	
//	TODO PONERLE TITULOS A GESTION
	/**Pide al usuario una eleccion y llama a otros metodos que gestionan Empleados dependiendo de esta.
	 * 
	 * @param emp
	 */
	public static void GestionEmpelados(Empresa emp) {
		String[] o = {"1 - CREAR NUEVO EMPLEADO", "2 - MODIFICAR EMPLEADO", "3 - ELIMINAR EMPLEADO", "4 - LISTADOS", "5 - BUSCAR EMPLEADO", "6 - TRANSLADAR EMPLEADO", "7 - VOLVER AL MENÚ"};
		String eleccion;

		do {
			eleccion = MenuOpciones("GESTIÓN DE EMPLEADOS", "-", o, "1-2-3-4-5-6-7", "-", "Introduzca que opción quiere elegir: ", "Esa opción no es válida, pruebe de nuevo.");

			switch (eleccion) {
			case "1": {
				Metodos.Titular(Metodos.RodeaCadena("NUEVO EMPLEADO", "-", 5), "-");
				CrearEmpleados(emp);
				break;
			}
			case "2": {
				ModificaEmpleados(emp);
				break;
			}
			case "3": {
				EliminaEmpleados(emp);
				break;
			}
			case "4": {
				ListadosEmpleados(emp);
				break;
			}
			case "5": {
				BuscarEmpleados(emp);
				break;
			}
			case "6": {
				TransladarEmpleado(emp);
				break;
			}
			}

		} while (!eleccion.equals("7"));
	}
	/**Pide al usuario datos y después añade el nuevo Empleado al TreeMap.
	 * 
	 * @param emp
	 */
	public static void CrearEmpleados(Empresa emp) {
		@SuppressWarnings("resource")
		Scanner lector = new Scanner(System.in);
		String dNI, nombre, ap1, ap2, ofi, nomcompl;
		int dia, mes, año;
		Empleado o;
		boolean emplExiste;

		System.out.print("Nombre: ");
		nombre = lector.nextLine();

		System.out.print("Primer apellido: ");
		ap1 = lector.nextLine();

		System.out.print("Segundo apellido: ");
		ap2 = lector.nextLine();

		nomcompl = ap1 + " " + ap2 + ", " + nombre; 

		emplExiste = emp.getEmpleados().containsKey(nomcompl);

		if (!emplExiste) {
			
//			TODO CONTROLAR QUE EL DNI NO EXISTA
			do {
				System.out.print("DNI: ");
				dNI = lector.nextLine();

				if (!Metodos.ValidarDNI(dNI)) {
					System.out.println("DNI inválido.");
				}
			} while(!Metodos.ValidarDNI(dNI));

			System.out.println("");
			System.out.println("Fecha de Nacimiento.");
			System.out.print("Día: ");
			dia = lector.nextInt();

			System.out.print("Mes: ");
			mes = lector.nextInt();

			System.out.print("Año: ");
			año = lector.nextInt();

			GregorianCalendar FecNac = new GregorianCalendar(año, mes, dia);

			System.out.println("");
			System.out.println("Fecha de Alta.");
			System.out.print("Día: ");
			dia = lector.nextInt();

			System.out.print("Mes: ");
			mes = lector.nextInt();

			System.out.print("Año: ");
			año = lector.nextInt();
			lector.nextLine();

			GregorianCalendar FecAlta = new GregorianCalendar(año, mes, dia);
			System.out.println("");

			MostrarListado(new TreeMap<String, Object>(emp.getOficinas()));
			System.out.println("");

			do {
				System.out.println("Elija una oficina: ");
				ofi = lector.nextLine();

				if(!emp.getOficinas().containsKey(ofi) && !ofi.equalsIgnoreCase("SALIR")) {
					System.out.println("Esa oficina no existe, pruebe de nuevo o escriba salir para cancelar (no se creará el empleado).");
				}

			} while (!emp.getOficinas().containsKey(ofi) && !ofi.equalsIgnoreCase("SALIR"));

			if(!ofi.equalsIgnoreCase("SALIR")) {
				if(ap2 != "") {
					o = new Empleado(dNI, nombre, ap1, ap2, FecNac, FecAlta, emp.getOficinas().get(ofi));
				} else {
					o = new Empleado(dNI, nombre, ap1, FecNac, FecAlta, emp.getOficinas().get(ofi));
				}
				emp.AñadirEmpleado(o);
			} else {
				System.out.println("Es necesaria una oficina, el empleado no se ha creado.");
			}
		} else {
			System.out.println("El empleado " + nomcompl + " ya existe.");

		}
		System.out.println("");
	}
	/**Le pide al usuario la key de un empleado y llama al metodo de crear un nuevo empleado pero colocando la misma key.
	 * 
	 * @param emp
	 */
	public static void ModificaEmpleados(Empresa emp) {
		@SuppressWarnings("resource")
		Scanner lector = new Scanner(System.in);
		String nomComp, dNI, ofi;
		int dia, mes, año;
		boolean empExiste = false;
		Empleado o, c;

			System.out.println("Nombre completo del empleado que quiere modificar: ");
			nomComp = lector.nextLine();

			//Comprueba que el empleado exista dentro del TreeMap.
			if(!emp.getEmpleados().containsKey(nomComp)) { 
				System.out.println("El empleado que intenta modificar no existe, mire el listado e intentelo de nuevo.");
			} else {
				empExiste = true;
			}

		if (empExiste) { //Solo sobreescribe el empleado si este existe. 
			
			c = (Empleado) emp.getEmpleados().get(nomComp);
			
			do {
				System.out.print("DNI: ");
				dNI = lector.nextLine();

				if (!Metodos.ValidarDNI(dNI)) {
					System.out.println("DNI inválido.");
				}
			} while(!Metodos.ValidarDNI(dNI));
			
			
			System.out.println("");
			System.out.println("Fecha de Nacimiento.");
			System.out.print("Día: ");
			dia = lector.nextInt();

			System.out.print("Mes: ");
			mes = lector.nextInt();

			System.out.print("Año: ");
			año = lector.nextInt();

			GregorianCalendar FecNac = new GregorianCalendar(año, mes, dia);

			System.out.println("");
			System.out.println("Fecha de Alta.");
			System.out.print("Día: ");
			dia = lector.nextInt();

			System.out.print("Mes: ");
			mes = lector.nextInt();

			System.out.print("Año: ");
			año = lector.nextInt();
			lector.nextLine();

			GregorianCalendar FecAlta = new GregorianCalendar(año, mes, dia);
			System.out.println("");

			MostrarListado(new TreeMap<String, Object>(emp.getOficinas()));
			System.out.println("");

			do {
				System.out.println("Elija una oficina: ");
				ofi = lector.nextLine();

				if(!emp.getOficinas().containsKey(ofi) && !ofi.equalsIgnoreCase("SALIR")) {
					System.out.println("Esa oficina no existe, pruebe de nuevo o escriba salir para cancelar (no se creará el empleado).");
				}

			} while (!emp.getOficinas().containsKey(ofi) && !ofi.equalsIgnoreCase("SALIR"));

			if(!ofi.equalsIgnoreCase("SALIR")) {
				o = new Empleado(dNI, c.getNombre(), c.getAp1(), c.getAp2(), FecNac, FecAlta, emp.getOficinas().get(ofi));
				emp.AñadirEmpleado(o);
			} else {
				System.out.println("Es necesaria una oficina, el empleado no se ha modificado.");
			}

			System.out.println(emp.getEmpleados().get(nomComp));
			System.out.println("");
		}
	}
	/**Le pide al usuario la key de un empleado y lo borra.
	 * 
	 * @param emp
	 */
	public static void EliminaEmpleados(Empresa emp) {
		@SuppressWarnings("resource")
		Scanner lector = new Scanner(System.in);
		String nom;
		boolean emplExiste;

		System.out.print("Nombre completo del empleado que desea eliminar: ");
		nom = lector.nextLine();

//		Comprueba que el nombre exista dentro del Treemap de Empleados.
		emplExiste = (emp.getEmpleados().containsKey(nom));

		if(!emplExiste) { 
			System.out.println("El empleado que busca no existe, mire el listado e intentelo de nuevo.");
		} else {
			emp.BorrarEmpleado(nom);
		}
		System.out.println();
	}
	/**Muestra el menu de listados de empleado al usuario y llama al metodo del listado que elija.
	 * 
	 * @param emp
	 */
	public static void ListadosEmpleados(Empresa emp) {
		String[] l = {"1 - NOMBRE", "2 - FECHA DE ALTA", "3 - OFICINA", "4 - ATRÁS"};
		String eleccion;

		do {
			eleccion = MenuOpciones("ORDENAR POR:", "-", l, "1-2-3-4", "-", "Introduzca que opción quiere elegir: ", "Esa opción no es válida, pruebe de nuevo.");

			switch (eleccion) {
			case "1": {
				MostrarListado(new TreeMap<String, Object>(emp.getEmpleados()));
				break;
			}
			case "2": {
				MostrarListado(new TreeMap<String, Object>(emp.getEmpleados()), new EmpleadoFechaAlta());
				break;
			}
			case "3": {
				MostrarListado(new TreeMap<String, Object>(emp.getEmpleados()), new EmpleadoOficina());
				break;
			}
			}
		} while (!eleccion.equals("4"));
	}
	/**Pide al usuario la key de un empleado y muestra los datos de este.
	 * 
	 * @param emp
	 */
	public static void BuscarEmpleados(Empresa emp) {
		@SuppressWarnings("resource")
		Scanner lector = new Scanner(System.in);
		String nom;
		boolean emplExiste = false;

		System.out.println("Puede escribir 'salir' para cancelar.");
		do {
			System.out.println("Nombre Completo del empleado que quiere buscar (Ap1 Ap2, Nombre): ");
			nom = lector.nextLine();

//			Comprueba que el nombre exista dentro del Treemap de Empleados.
			if(!emp.getEmpleados().containsKey(nom)) {
				if (!nom.equalsIgnoreCase("SALIR")) {
					System.out.println("El empleado que busca no existe, mire el listado e intentelo de nuevo.");
				}
			} else {
				emplExiste = true;
			}
		} while(!emp.getEmpleados().containsKey(nom) && !nom.equalsIgnoreCase("SALIR"));

		if (emplExiste) {
//			TODO CHECKEAR EL TOSTRING DE EMPLEADO, FECHAS MAL
			System.out.println(emp.getEmpleados().get(nom));
		}
		System.out.println();
	}
	/**Pide al usuario la key de un empleado y la nueva oficina, después cambia esta.
	 * 
	 * @param emp
	 */
	public static void TransladarEmpleado(Empresa emp) {
		@SuppressWarnings("resource")
		Scanner lector = new Scanner(System.in);
		String nom, nuevaofi;
		boolean emplExiste = false;
		
		System.out.println("Puede escribir 'salir' para cancelar.");
		do {
			System.out.println("Nombre Completo del empleado que quiere transladar (Ap1 Ap2, Nombre): ");
			nom = lector.nextLine();

//			Comprueba que el nombre exista dentro del Treemap de Empleados.
			if(!emp.getEmpleados().containsKey(nom)) {
				if (!nom.equalsIgnoreCase("SALIR")) {
					System.out.println("El empleado que busca no existe, mire el listado e intentelo de nuevo.");
				}
			} else {
				emplExiste = true;
			}
		} while(!emp.getEmpleados().containsKey(nom) && !nom.equalsIgnoreCase("SALIR"));
		
		if (emplExiste) {
			System.out.println("");
			System.out.println("Oficinas:");
			MostrarListado(new TreeMap<String, Object>(emp.getOficinas()));
			System.out.println("");

			do {
				System.out.println("Elija una nueva oficina: ");
				nuevaofi = lector.nextLine();

				if(!emp.getOficinas().containsKey(nuevaofi) && !nuevaofi.equalsIgnoreCase("SALIR")) {
					System.out.println("Esa oficina no existe, pruebe de nuevo o escriba salir para cancelar (no se transladará el empleado).");
				}
			} while (!emp.getOficinas().containsKey(nuevaofi) && !nuevaofi.equalsIgnoreCase("SALIR"));
			
			if (!nuevaofi.equalsIgnoreCase("SALIR")) {
				emp.getEmpleados().get(nom).setOficinaActual(emp.getOficinas().get(nuevaofi));
				System.out.println(emp.getEmpleados().get(nom));
			} else {
				System.out.println("El empleado no ha sido transladado.");
			}
		}
		
		
		System.out.println("");
	}



	//	--- GESTION DE VEHICULOS ---

	/**Pide al usuario una eleccion y llama a otros metodos que gestionan Vehiculos dependiendo de esta.
	 * 
	 * @param emp
	 */
	public static void GestionVehiculos(Empresa emp) {
		String[] o = {"1 - CREAR NUEVO VEHICULO", "2 - MODIFICAR VEHICULO", "3 - ELIMINAR VEHICULO", "4 - LISTADOS", "5 - BUSCAR VEHICULO", "6 - TRANSLADAR VEHICULO", "7 - ALQUILAR/DESALQULAR VEHICULO", "8 - VOLVER AL MENÚ"};
		String eleccion;

		do {
			eleccion = MenuOpciones("GESTIÓN DE VEHICULOS", "-", o, "1-2-3-4-5-6-7-8", "-", "Introduzca que opción quiere elegir: ", "Esa opción no es válida, pruebe de nuevo.");

			switch (eleccion) {
			case "1": {
				Metodos.Titular(Metodos.RodeaCadena("NUEVO VEHICULO", "-", 5), "-");
				CrearVehiculo(emp);
				break;
			}
			case "2": {
				Metodos.Titular(Metodos.RodeaCadena("MODIFICAR VEHICULO", "-", 5), "-");
				ModificarVehiculo(emp);
				break;
			}
			case "3": {
				Metodos.Titular(Metodos.RodeaCadena("ELIMINAR VEHICULO", "-", 5), "-");
				EliminaVehiculo(emp);
				break;
			}
			case "4": {
				ListadosVehiculo(emp);
				break;
			}
			case "5": {
				Metodos.Titular(Metodos.RodeaCadena("BUSCAR VEHICULO", "-", 5), "-");
				BuscarVehiculo(emp);
				break;
			}
			case "6": {
				Metodos.Titular(Metodos.RodeaCadena("TRANSLADAR VEHICULO", "-", 5), "-");
				TransladarVehiculo(emp);
				break;
			}
			case "7": {
				Metodos.Titular(Metodos.RodeaCadena("ALQUILAR/DESALQULAR VEHICULO", "-", 5), "-");
				AlquilarDesalquilar(emp);
				break;
			}
			}

		} while (!eleccion.equals("8"));
	}
	/**Pide al usuario datos y después añade el nuevo Vehiculo al TreeMap.
	 * 
	 * @param emp
	 */
	public static void CrearVehiculo(Empresa emp) {
		@SuppressWarnings("resource")
		Scanner lector = new Scanner(System.in);
		String matri, tipo, marca, modelo, color, cate = "", ofi = "", carnet = "";
		int año, mes, dia;
		double kms;
		Set<String> Categorias, Carnets;
		ArrayList<CarnetConducir> CarnetsValids = new ArrayList<CarnetConducir>();

		do {
			System.out.println("Matrícula del vehículo que desea crear (salir para cancelar): ");
			matri = lector.nextLine();

			if (emp.getVehiculos().containsKey(matri) || emp.getVehiculosAlquilados().containsKey(matri)) {
				System.out.println("El vehículo que intenta crear ya existe.");
			}

		} while((emp.getVehiculos().containsKey(matri) || emp.getVehiculosAlquilados().containsKey(matri)) && !matri.equalsIgnoreCase("SALIR"));

		if (!matri.equalsIgnoreCase("SALIR")) {
			do {
				System.out.println("Tipos: CocheC, CocheE, Moto y Furgoneta");
				System.out.println("Elija el tipo de vehículo que quiere crear: ");
				tipo = lector.nextLine();

				if(!tipo.equalsIgnoreCase("CocheC") && !tipo.equalsIgnoreCase("CocheE") && !tipo.equalsIgnoreCase("Moto") && !tipo.equalsIgnoreCase("Furgoneta")) {
					System.out.println("El tipo de vehículo que quiere crear es inválido.");
					System.out.println("");
				}
			} while(!tipo.equalsIgnoreCase("CocheC") && !tipo.equalsIgnoreCase("CocheE") && !tipo.equalsIgnoreCase("Moto") && !tipo.equalsIgnoreCase("Furgoneta"));

			System.out.print("Marca: ");
			marca = lector.nextLine();

			System.out.print("Modelo: ");
			modelo = lector.nextLine();

			System.out.print("Color: ");
			color = lector.nextLine();

			System.out.print("Kms: ");
			kms = lector.nextDouble();
			lector.nextLine();
			System.out.println("");

			Categorias = emp.getCategorias().keySet();

			for (String i: Categorias) {
				System.out.print(i + ", " + emp.getCategorias().get(i).getDescripcion() + ". / ");
			}
			System.out.println("");

			do {
				System.out.print("Categoría: ");
				cate = lector.nextLine();

				if(!emp.getCategorias().containsKey(cate) && !cate.equalsIgnoreCase("SALIR")) {
					System.out.println("Esa categoría no existe, pruebe de nuevo o escriba salir para cancelar (no se creará el vehículo).");
				}
				System.out.println("");
			} while (!emp.getCategorias().containsKey(cate) && !cate.equalsIgnoreCase("SALIR"));

			if (!cate.equalsIgnoreCase("SALIR")) {

				System.out.println("Oficinas:");
				MostrarListado(new TreeMap<String, Object>(emp.getOficinas()));
				System.out.println("");

				do {
					System.out.println("Elija una oficina: ");
					ofi = lector.nextLine();

					if(!emp.getOficinas().containsKey(ofi) && !ofi.equalsIgnoreCase("SALIR")) {
						System.out.println("Esa oficina no existe, pruebe de nuevo o escriba salir para cancelar (no se creará el vehículo).");
					}
					System.out.println("");
				} while (!emp.getOficinas().containsKey(ofi) && !ofi.equalsIgnoreCase("SALIR"));

				if (!ofi.equalsIgnoreCase("SALIR")) {

					System.out.println("Fecha de Alta.");
					System.out.print("Día: ");
					dia = lector.nextInt();

					System.out.print("Mes: ");
					mes = lector.nextInt();

					System.out.print("Año: ");
					año = lector.nextInt();
					lector.nextLine();

					GregorianCalendar FecAlta = new GregorianCalendar(año, mes, dia);

					Carnets = emp.getCarnets().keySet();

					System.out.println("");
					for (String i: Carnets) {
						System.out.print(i + " / ");
					}
					System.out.println("");

					do {
						System.out.println("Elija un carnet de conducir: ");
						carnet = lector.nextLine();

						if(!emp.getCarnets().containsKey(carnet) && !carnet.equalsIgnoreCase("SALIR") && !carnet.equalsIgnoreCase("YA")) {
							System.out.println("Ese carnet no existe, pruebe de nuevo o escriba salir para cancelar (no se creará el vehículo).");
						} 
						else if(!carnet.equalsIgnoreCase("YA")) {
							CarnetsValids.add(emp.getCarnets().get(carnet));
							System.out.println("Carnet añadido, puede añadir mas o escribir ya para parar.");
						}
					} while (!carnet.equalsIgnoreCase("SALIR") && !carnet.equalsIgnoreCase("YA"));

					System.out.println("");
					System.out.print("Carnets Añadidos: ");
					if (carnet.equalsIgnoreCase("YA")) {
						for (CarnetConducir i: CarnetsValids) {
							System.out.print(i.getCodigo() + " / ");
						}
					}
					System.out.println("");

					if (!carnet.equalsIgnoreCase("SALIR")) {

						switch (tipo.toUpperCase()) {
						case "COCHEC": {
							CocheC v = CreaCocheC(matri, marca, modelo, color, FecAlta, kms, emp.getCategorias().get(cate), emp.getOficinas().get(ofi), CarnetsValids);
							emp.AñadirVehiculo(v);
							break;
						}
						case "COCHEE": {
							CocheE v = CreaCocheE(matri, marca, modelo, color, FecAlta, kms, emp.getCategorias().get(cate), emp.getOficinas().get(ofi), CarnetsValids);
							emp.AñadirVehiculo(v);
							break;
						}
						case "MOTO": {
							Moto v = CreaMoto(matri, marca, modelo, color, FecAlta, kms, emp.getCategorias().get(cate), emp.getOficinas().get(ofi), CarnetsValids);
							emp.AñadirVehiculo(v);
							break;
						}
						case "FURGONETA": {
							Furgoneta v = CreaFurgoneta(matri, marca, modelo, color, FecAlta, kms, emp.getCategorias().get(cate), emp.getOficinas().get(ofi), CarnetsValids);
							emp.AñadirVehiculo(v);
							break;
						}
						}
						System.out.println("Vehículo creado.");
						System.out.println("");
					}
				}
			} 			
		} 

		if (matri.equalsIgnoreCase("SALIR") || cate.equalsIgnoreCase("SALIR") ||  ofi.equalsIgnoreCase("SALIR") || carnet.equalsIgnoreCase("SALIR")) {
			System.out.println("El vehículo no ha sido creado.");
		}
		System.out.println("");
	}
	/**TODO Le pide al usuario la key de un Vehiculo y llama al metodo de crear un nuevo Vehiculo pero colocando la misma key.
	 * 
	 * @param emp
	 */
	public static void ModificarVehiculo(Empresa emp) {
		
	}
	/**Le pide al usuario la key de un Vehiculo y lo borra.
	 * 
	 * @param emp
	 */
	public static void EliminaVehiculo(Empresa emp) {
		@SuppressWarnings("resource")
		Scanner lector = new Scanner(System.in);
		String mat;
		boolean vehiExiste;

		System.out.print("Matrícula del vehiculo que desea eliminar: ");
		mat = lector.nextLine();

//		Comprueba que el vehiculo exista.
		vehiExiste = (emp.getVehiculos().containsKey(mat) || emp.getVehiculosAlquilados().containsKey(mat));

		if(!vehiExiste) { 
			System.out.println("El vehiculo que busca no existe, mire el listado e intentelo de nuevo.");
		} else {
			if (emp.getVehiculos().containsKey(mat)) {
				emp.BorrarVehiculo(mat);
			} else {
				emp.BorrarVehiculoAlquilado(mat);
			}
		}
		System.out.println();
	}
	/**Muestra el menu de Vehiculos al usuario y llama al menu de listados que elija.
	 * 
	 * @param emp
	 */
	public static void ListadosVehiculo(Empresa emp) {
		String[] o = {"1 - VEHICULOS DISPONIBLES", "2 - VEHICULOS ALQUILADOS", "3 - ATRÁS"};
		String eleccion;

		do {
			eleccion = MenuOpciones("LISTADOS DE VEHICULOS", "-", o, "1-2-3", "-", "Introduzca que opción quiere elegir: ", "Esa opción no es válida, pruebe de nuevo.");

			switch (eleccion) {
			case "1": {
				Metodos.Titular(Metodos.RodeaCadena("VEHICULOS DISPONIBLES", "-", 5), "-");
				ListadosVehiculoDisponibles(emp);
				break;
			}
			case "2": {
				Metodos.Titular(Metodos.RodeaCadena("VEHICULOS ALQUILADOS", "-", 5), "-");
				ListadosVehiculoAlquilados(emp);
				break;
			}
			}
			System.out.println("");
		} while (!eleccion.equals("3"));
	}
	/**Pide al usuario la key de un Vehiculo y muestra los datos de este.
	 * 
	 * @param emp
	 */
	public static void BuscarVehiculo(Empresa emp) {
		@SuppressWarnings("resource")
		Scanner lector = new Scanner(System.in);
		String matr;
		boolean vehiExiste = false;

		System.out.println("Puede escribir 'salir' para cancelar.");
		do {
			System.out.println("MAtrícula del vehículo que quiere buscar : ");
			matr = lector.nextLine();

//			Comprueba que el vehiculo exista.
			if(!emp.getVehiculos().containsKey(matr) && !emp.getVehiculosAlquilados().containsKey(matr)) {
				if (!matr.equalsIgnoreCase("SALIR")) {
					System.out.println("El vehículo que busca no existe, mire el listado e intentelo de nuevo.");
				}
			} else {
				vehiExiste = true;
			}
		} while(!emp.getVehiculos().containsKey(matr) && !emp.getVehiculosAlquilados().containsKey(matr) && !matr.equalsIgnoreCase("SALIR"));

		if (vehiExiste) {
//			TODO CHECKEAR EL TOSTRING DE VEHICULO, FECHAS MAL
			if (emp.getVehiculos().containsKey(matr)) {
				System.out.println(emp.getVehiculos().get(matr));
			} else {
				System.out.println(emp.getVehiculosAlquilados().get(matr));
			}
		}
		System.out.println();
	}
	/**Pide al usuario la key de un Vehiculo y la nueva oficina, después cambia esta.
	 * 
	 * @param emp
	 */
	public static void TransladarVehiculo(Empresa emp) {
		@SuppressWarnings("resource")
		Scanner lector = new Scanner(System.in);
		String mat, ofi;
		boolean vehiExiste, mismaOfi = false;

		System.out.print("Matrícula del vehiculo que desea transladar: ");
		mat = lector.nextLine();

//		Comprueba que el vehiculo exista.
		vehiExiste = (emp.getVehiculos().containsKey(mat) || emp.getVehiculosAlquilados().containsKey(mat));
		System.out.println();
		if(!vehiExiste) { 
			System.out.println("El vehiculo que busca no existe, mire el listado e intentelo de nuevo.");
		} else {
			
			System.out.println("Oficinas:");
			MostrarListado(new TreeMap<String, Object>(emp.getOficinas()));
			System.out.println("");
			
			do {
				System.out.println("Elija una nueva oficina: ");
				ofi = lector.nextLine();
				
				if (emp.getVehiculos().containsKey(mat)) {
					mismaOfi = emp.getVehiculos().get(mat).getOficinaActual().getCodigo().equalsIgnoreCase(ofi);
				} else {
					mismaOfi =emp.getVehiculosAlquilados().get(mat).getOficinaActual().getCodigo().equalsIgnoreCase(ofi);
				}
				
				if(!emp.getOficinas().containsKey(ofi) && !ofi.equalsIgnoreCase("SALIR") && !mismaOfi) {
					System.out.println("Esa oficina no existe, pruebe de nuevo o escriba salir para cancelar (no se creará el vehículo).");
				}
				else if (mismaOfi) {
					System.out.println("No puede ser transladar a la misma oficina en la que está actualmente.");
				}
				
				if (emp.getOficinas().containsKey(ofi) && !ofi.equalsIgnoreCase("SALIR") && !mismaOfi) {
					if (emp.getVehiculos().containsKey(mat)) {
						emp.getVehiculos().get(mat).TransladarDeOficina(emp.getOficinas().get(ofi));
					} else {
						emp.getVehiculosAlquilados().get(mat).TransladarDeOficina(emp.getOficinas().get(ofi));
					}
				}
				System.out.println("");
			} while (!emp.getOficinas().containsKey(ofi) && !ofi.equalsIgnoreCase("SALIR") && mismaOfi);
			
			
			
			
		}
		System.out.println();
	}
	/**Pide al usuario la key de un Vehiculo y cambia su TreeMap (Vehiculos/VehiculosAlquilados).
	 * 
	 * @param emp
	 */
	public static void AlquilarDesalquilar(Empresa emp) {
		@SuppressWarnings("resource")
		Scanner lector = new Scanner(System.in);
		String mat;
		boolean vehiExiste;

		System.out.print("Matrícula del vehiculo que desea cambiar su estado: ");
		mat = lector.nextLine();

//		Comprueba que el vehiculo exista.
		vehiExiste = (emp.getVehiculos().containsKey(mat) || emp.getVehiculosAlquilados().containsKey(mat));
		System.out.println();
		if(!vehiExiste) { 
			System.out.println("El vehiculo que busca no existe, mire el listado e intentelo de nuevo.");
		} else {
			if (emp.getVehiculos().containsKey(mat)) {
				emp.AlquilarVehiculo(emp.getVehiculos().get(mat));
				System.out.println("El estado del vehiculo ha cambiado a alquilado");
			} else {
				emp.DesAlquilarVehiculo(emp.getVehiculosAlquilados().get(mat));
				System.out.println("El estado del vehiculo ha cambiado a disponible");
			}
		}
	}
	/**Muestra el menu de listados de Vehiculos Disponibles al usuario y llama al metodo del listado que elija.
	 * 
	 * @param emp
	 */
	public static void ListadosVehiculoDisponibles(Empresa emp) {
		String[] l = {"1 - MATRÍCULA", "2 - CATEGORÍA", "3 - FECHA ALTA", "4 - KMS", "5 - MARCA", "6 - OFICINA", "7 - ATRÁS"};
		String eleccion;
		
		do {
			eleccion = MenuOpciones("ORDENAR POR:", "-", l, "1-2-3-4-5-6-7", "-", "Introduzca que opción quiere elegir: ", "Esa opción no es válida, pruebe de nuevo.");

			switch (eleccion) {
			case "1": {
				MostrarListado(new TreeMap<String, Object>(emp.getVehiculos()));
				break;
			}
			case "2": {
				MostrarListado(new TreeMap<String, Object>(emp.getVehiculos()), new VehiculoCategoria());
				break;
			}
			case "3": {
				MostrarListado(new TreeMap<String, Object>(emp.getVehiculos()), new VehiculoFechaAlta());
				break;
			}
			case "4": {
				MostrarListado(new TreeMap<String, Object>(emp.getVehiculos()), new VehiculoKms());
				break;
			}
			case "5": {
				MostrarListado(new TreeMap<String, Object>(emp.getVehiculos()), new VehiculoMarca());
				break;
			}
			case "6": {
				MostrarListado(new TreeMap<String, Object>(emp.getVehiculos()), new VehiculoOficinaActual());
				break;
			}
			}
		} while (!eleccion.equals("7"));
	}
	/**Muestra el menu de listados de Vehiculos Alquilados al usuario y llama al metodo del listado que elija.
	 * 
	 * @param emp
	 */
	public static void ListadosVehiculoAlquilados(Empresa emp) {
		String[] l = {"1 - MATRÍCULA", "2 - CATEGORÍA", "3 - FECHA ALTA", "4 - KMS", "5 - MARCA", "6 - OFICINA", "7 - ATRÁS"};
		String eleccion;

		do {
			eleccion = MenuOpciones("ORDENAR POR:", "-", l, "1-2-3-4-5-6-7", "-", "Introduzca que opción quiere elegir: ", "Esa opción no es válida, pruebe de nuevo.");

			switch (eleccion) {
			case "1": {
				MostrarListado(new TreeMap<String, Object>(emp.getVehiculosAlquilados()));
				break;
			}
			case "2": {
				MostrarListado(new TreeMap<String, Object>(emp.getVehiculosAlquilados()), new VehiculoCategoria());
				break;
			}
			case "3": {
				MostrarListado(new TreeMap<String, Object>(emp.getVehiculosAlquilados()), new VehiculoFechaAlta());
				break;
			}
			case "4": {
				MostrarListado(new TreeMap<String, Object>(emp.getVehiculosAlquilados()), new VehiculoKms());
				break;
			}
			case "5": {
				MostrarListado(new TreeMap<String, Object>(emp.getVehiculosAlquilados()), new VehiculoMarca());
				break;
			}
			case "6": {
				MostrarListado(new TreeMap<String, Object>(emp.getVehiculosAlquilados()), new VehiculoOficinaActual());
				break;
			}
			}
		} while (!eleccion.equals("7"));
	}

	
//	--- CREADORES DE VEHICULOS ---
	
	/**Recibe los datos de vehiculo y pide el resto de datos necesarios para crear un CocheC.
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
	 * @return CocheC
	 */
	public static CocheC CreaCocheC(String matricula, String marca, String modelo, String color, GregorianCalendar fechaAlta, double kms, Categoria categoria, Oficina oficinaActual, ArrayList<CarnetConducir> carnetsValidos) {
		@SuppressWarnings("resource")
		Scanner lector = new Scanner(System.in);
		String tipo, nivelEmisiones;
		int nplazas;
		double consumo, potencia; 
		
		System.out.print("Consumo: ");
		consumo = lector.nextDouble();
		
		System.out.print("Potencia: ");
		potencia = lector.nextDouble();
		
		System.out.print("Número de plazas: ");
		nplazas = lector.nextInt();
		lector.nextLine();
		
		System.out.println("Tipo: ");
		tipo = lector.nextLine();
				
		do {
			System.out.println("Nivel de Emisiones: ");
			nivelEmisiones = lector.nextLine();
		} while(!nivelEmisiones.equalsIgnoreCase("A") && !nivelEmisiones.equalsIgnoreCase("B") && !nivelEmisiones.equalsIgnoreCase("C"));
		
		
		CocheC c = new CocheC(matricula, marca, modelo, color, fechaAlta, kms, categoria, oficinaActual, carnetsValidos, consumo, potencia, nivelEmisiones, nplazas, tipo);
		return c;
	}
	/**Recibe los datos de vehiculo y pide el resto de datos necesarios para crear un CocheE.
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
	 * @return CocheE
	 */
	public static CocheE CreaCocheE(String matricula, String marca, String modelo, String color, GregorianCalendar fechaAlta, double kms, Categoria categoria, Oficina oficinaActual, ArrayList<CarnetConducir> carnetsValidos) {
		@SuppressWarnings("resource")
		Scanner lector = new Scanner(System.in);
		String tipo;
		int nplazas;
		double autonomia, tiempoRecarga;
		
		System.out.print("Autonomia(Kms): ");
		autonomia = lector.nextDouble();
		
		System.out.print("Tiempo de Recarga(mins): ");
		tiempoRecarga = lector.nextDouble();
		
		System.out.print("Número de plazas: ");
		nplazas = lector.nextInt();
		lector.nextLine();
		
		System.out.println("Tipo: ");
		tipo = lector.nextLine();
				
		CocheE c = new CocheE(matricula, marca, modelo, color, fechaAlta, kms, categoria, oficinaActual, carnetsValidos, autonomia, tiempoRecarga, nplazas, tipo);
		return c;
	}
	/**Recibe los datos de vehiculo y pide el resto de datos necesarios para crear una Moto.
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
	 * @return Moto
	 */
	public static Moto CreaMoto(String matricula, String marca, String modelo, String color, GregorianCalendar fechaAlta, double kms, Categoria categoria, Oficina oficinaActual, ArrayList<CarnetConducir> carnetsValidos) {
		@SuppressWarnings("resource")
		Scanner lector = new Scanner(System.in);
		double autonomia, tiempoRecarga, cilindrada;
		
		System.out.print("Autonomia(Kms): ");
		autonomia = lector.nextDouble();
		
		System.out.print("Tiempo de Recarga(mins): ");
		tiempoRecarga = lector.nextDouble();
		
		System.out.print("Cilindrada: ");
		cilindrada = lector.nextDouble();
		lector.nextLine();
		
		Moto m = new Moto(matricula, marca, modelo, color, fechaAlta, kms, categoria, oficinaActual, carnetsValidos, autonomia, tiempoRecarga, cilindrada);
		return m;
	}
	/**Recibe los datos de vehiculo y pide el resto de datos necesarios para crear una Furgoneta.
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
	 * @return Furgoneta
	 */
	public static Furgoneta CreaFurgoneta(String matricula, String marca, String modelo, String color, GregorianCalendar fechaAlta, double kms, Categoria categoria, Oficina oficinaActual, ArrayList<CarnetConducir> carnetsValidos) {
		@SuppressWarnings("resource")
		Scanner lector = new Scanner(System.in);
		String nivelEmisiones;
		double consumo, potencia, capCarga; 
		
		System.out.print("Consumo: ");
		consumo = lector.nextDouble();
		
		System.out.print("Potencia: ");
		potencia = lector.nextDouble();
		
		System.out.print("Capacidad de Carga(m^3): ");
		capCarga = lector.nextDouble();
		lector.nextLine();
		
		do {
			System.out.println("Nivel de Emisiones: ");
			nivelEmisiones = lector.nextLine();
		} while(!nivelEmisiones.equalsIgnoreCase("A") && !nivelEmisiones.equalsIgnoreCase("B") && !nivelEmisiones.equalsIgnoreCase("C"));
		
		
		Furgoneta f = new Furgoneta(matricula, marca, modelo, color, fechaAlta, kms, categoria, oficinaActual, carnetsValidos, consumo, potencia, nivelEmisiones, capCarga);
		return f;
	}
	
	
	//	--- GESTION DE ALQUILERES ---	
	
	/**Pide al usuario una eleccion y llama a otros metodos que gestionan Alquileres dependiendo de esta.
	 * 
	 * @param emp
	 */
	public static void GestionAlquileres(Empresa emp) {
		String[] o = {"1 - CREAR NUEVO ALQUILER", "2 - COMPLETAR ALQUILER", "3 - MODIFICAR ALQUILER", "4 - ELIMINAR ALQUILER", "5 - LISTADOS DE ALQUILER", "6 - BUSCAR ALQUILER", "7 - VOLVER AL MENÚ"};
		String eleccion;

		do {
			eleccion = MenuOpciones("GESTIÓN DE ALQUILERES", "-", o, "1-2-3-4-5-6-7-8", "-", "Introduzca que opción quiere elegir: ", "Esa opción no es válida, pruebe de nuevo.");

			switch (eleccion) {
			case "1": {
				Metodos.Titular(Metodos.RodeaCadena("NUEVO ALQUILER", "-", 5), "-");
				CrearAlquiler(emp);
				break;
			}
			case "2": {
				Metodos.Titular(Metodos.RodeaCadena("COMPLETAR ALQUILER", "-", 5), "-");
				CompletarAlquiler(emp);
				break;
			}
			case "3": {
				Metodos.Titular(Metodos.RodeaCadena("MODIFICAR ALQUILER", "-", 5), "-");
				ModificarAlquiler(emp);
				break;
			}
			case "4": {
				Metodos.Titular(Metodos.RodeaCadena("ELIMINAR ALQUILER", "-", 5), "-");
				EliminarAlquiler(emp);
				break;
			}
			case "5": {
				Metodos.Titular(Metodos.RodeaCadena("LISTADOS ALQUILER", "-", 5), "-");
				ListadosAlquiler(emp);
				break;
			}
			case "6": {
				Metodos.Titular(Metodos.RodeaCadena("BUSCAR ALQUILER", "-", 5), "-");
				BuscarAlquiler(emp);
				break;
			}
			}

		} while (!eleccion.equals("7"));
	}
	/**TODO Pide al usuario datos y después añade el nuevo Alquiler al TreeMap.
	 * 
	 * @param emp
	 */
	public static void CrearAlquiler(Empresa emp) {
		@SuppressWarnings("resource")
		Scanner lector = new Scanner(System.in);
		String codigo, ofi;
		Oficina OficinaOriginal, OficinaPrev;
		int año, mes, dia;
		boolean mismaOficina = false, menor25 = false;
		GregorianCalendar FechaInicio, FechaFinPrev, FechaNac;
		Set<String> Vehiculos;
		
		codigo = "" + (emp.getAlquileres().size() + 1);
		
		System.out.println("Oficinas:");
		MostrarListado(new TreeMap<String, Object>(emp.getOficinas()));
		System.out.println("");

		do {
			System.out.println("Elija una oficina de inicio: ");
			ofi = lector.nextLine();

			if(!emp.getOficinas().containsKey(ofi) && !ofi.equalsIgnoreCase("SALIR")) {
				System.out.println("Esa oficina no existe, pruebe de nuevo o escriba salir para cancelar (no se creará el alquiler).");
			}
			System.out.println("");
		} while (!emp.getOficinas().containsKey(ofi) && !ofi.equalsIgnoreCase("SALIR"));

		if (!ofi.equalsIgnoreCase("SALIR")) {
			OficinaOriginal = emp.getOficinas().get(ofi);
			
			do {
				System.out.println("Elija una oficina de devolución: ");
				ofi = lector.nextLine();

				if(!emp.getOficinas().containsKey(ofi)) {
					System.out.println("Esa oficina no existe, pruebe de nuevo o escriba salir para cancelar (no se creará el alquiler).");
				}
				System.out.println("");
			} while (!emp.getOficinas().containsKey(ofi));
			
			OficinaPrev = emp.getOficinas().get(ofi);
			
			mismaOficina = OficinaOriginal.equals(OficinaPrev);
			
			System.out.println("Fecha Inicio.");
			System.out.print("Día: ");
			dia = lector.nextInt();

			System.out.print("Mes: ");
			mes = lector.nextInt();

			System.out.print("Año: ");
			año = lector.nextInt();
			lector.nextLine();

			FechaInicio = new GregorianCalendar(año, mes, dia);
			System.out.println();
			
			System.out.println("Fecha Fin Prevista.");
			System.out.print("Día: ");
			dia = lector.nextInt();

			System.out.print("Mes: ");
			mes = lector.nextInt();

			System.out.print("Año: ");
			año = lector.nextInt();
			lector.nextLine();

			FechaFinPrev = new GregorianCalendar(año, mes, dia);
			System.out.println();
			
			System.out.println("Fecha z.");
			System.out.print("Día: ");
			dia = lector.nextInt();

			System.out.print("Mes: ");
			mes = lector.nextInt();

			System.out.print("Año: ");
			año = lector.nextInt();
			lector.nextLine();

			FechaNac = new GregorianCalendar(año, mes, dia);
			System.out.println();
			
			Vehiculos = emp.getVehiculos().keySet();

			for (String i: Vehiculos) {
				if(emp.getVehiculos().get(i).getOficinaActual().equals(OficinaOriginal)) {
					System.out.println(emp.getVehiculos().get(i) + " | " + emp.getVehiculos().get(i).CalcularImporte(dia));
				}
			}
			System.out.println("");
			
			
			
		}
		
		
		
		
		
	}
	/**TODO Pide al usuario datos y después completa el Alquiler.
	 * 
	 * @param emp
	 */
	public static void CompletarAlquiler(Empresa emp) {
		
	}
	/**TODO Le pide al usuario la key de un Alquiler y crea un nuevo Alquiler pero colocando la misma key.
	 * 
	 * @param emp
	 */
	public static void ModificarAlquiler(Empresa emp) {
		
	}
	/**TODO Le pide al usuario la key de un Alquiler y lo borra.
	 * 
	 * @param emp
	 */
	public static void EliminarAlquiler(Empresa emp) {
		
	}
	/**TODO Pide al usuario la key de un Alquiler y muestra los datos de este.
	 * 
	 * @param emp
	 */
	public static void BuscarAlquiler(Empresa emp) {
		
	}
	/**TODO Muestra el menu de listados de Alquiler al usuario y llama al metodo del listado que elija.
	 * 
	 * @param emp
	 */
	public static void ListadosAlquiler(Empresa emp) {
		String[] l = {"1 - NOMBRE", "2 - FECHA DE ALTA", "3 - OFICINA", "4 - ATRÁS"};
		String eleccion;

		do {
			eleccion = MenuOpciones("ORDENAR POR:", "-", l, "1-2-3-4", "-", "Introduzca que opción quiere elegir: ", "Esa opción no es válida, pruebe de nuevo.");

			switch (eleccion) {
			case "1": {
				MostrarListado(new TreeMap<String, Object>(emp.getEmpleados()));
				break;
			}
			case "2": {
				MostrarListado(new TreeMap<String, Object>(emp.getEmpleados()), new EmpleadoFechaAlta());
				break;
			}
			case "3": {
				MostrarListado(new TreeMap<String, Object>(emp.getEmpleados()), new EmpleadoOficina());
				break;
			}
			}
		} while (!eleccion.equals("4"));
	}
	
	

	//	--- GESTION DE CATEGORIAS ---

	/**Pide al usuario una eleccion y llama a otros metodos que gestionan categorias dependiendo de esta.
	 * 
	 * @param emp
	 */
	public static void GestionCategorias(Empresa emp) {
		String[] o = {"1 - CREAR NUEVA CATEGORÍA", "2 - MODIFICAR CATEGORÍA", "3 - ELIMINAR CATEGORÍA","4 - LISTADOS", "5 - BUSCAR CATEGORÍA", "6 - VOLVER AL MENÚ"};
		String eleccion;

		do {
			eleccion = InterfacesDeUsuario.MenuOpciones("GESTIÓN DE CATEGORÍAS", "-", o, "1-2-3-4-5-6", "-", "Introduzca que opción quiere elegir: ", "Esa opción no es válida, pruebe de nuevo.");

			switch (eleccion) {
			case "1": {
				Metodos.Titular(Metodos.RodeaCadena("NUEVA CATEGORÍA", "-", 5), "-");
				CrearCategoria(emp);
				break;
			}
			case "2": { 
				Metodos.Titular(Metodos.RodeaCadena("MODIFICAR CATEGORÍA", "-", 5), "-");
				ModificarCategoria(emp);
				break;
			}
			case "3": {
				Metodos.Titular(Metodos.RodeaCadena("ELIMINAR CATEGORÍA", "-", 5), "-");
				EliminarCategoria(emp);
				break;
			}
			case "4": {
				Metodos.Titular(Metodos.RodeaCadena("LISTADO DE CATEGORÍAS", "-", 5), "-");
				ListadosCategoria(emp);
				break;
			}
			case "5": {
				Metodos.Titular(Metodos.RodeaCadena("BUSCAR CATEGORÍA", "-", 5), "-");
				BuscarCategoria(emp);
				break;
			}
			}
		} while (!eleccion.equalsIgnoreCase("6"));
	}
	/**Pide al usuario datos y después añade la nueva categoria al TreeMap.
	 * 
	 * @param emp
	 */
	public static void CrearCategoria(Empresa emp) {
		@SuppressWarnings("resource")
		Scanner lector = new Scanner(System.in);
		String cod, desc;
		double recargo;
		Categoria o;

		do {
			System.out.println("Código de la nueva categoría ('salir' para cancelar): ");
			cod = lector.nextLine();

			if (emp.getCategorias().containsKey(cod)) {
				System.out.println("Ya existe una categoría con este Código. Escoja otro o escriba salir para cancelar.");
			}
			else if (cod.length() != 1 && !cod.equalsIgnoreCase("SALIR")) {
				System.out.println("Longitud del código inválida, solo puede ser una letra.");
			}
			
		} while(emp.getCategorias().containsKey(cod) || (cod.length() != 1 && !cod.equalsIgnoreCase("SALIR"))) ;

		if (!cod.equalsIgnoreCase("SALIR")) {

			System.out.println("Descripción: ");
			desc = lector.nextLine();
			
			System.out.println("Recargo: ");
			recargo = lector.nextDouble();
			lector.nextLine();
			
			
			if (desc.equals("")) {
				o = new Categoria(cod, recargo);
			} else {
				o = new Categoria(cod, desc, recargo);
			}
			
			emp.AñadirCategoria(o);
			System.out.println(emp.getCategorias().get(cod));
		}
		else {
			System.out.println("La categoría no ha sido creada.");
		}
		System.out.println("");
	}
	/**Le pide al usuario la key de una categoria y llama al metodo de crear una nueva categoria pero colocando la misma key.
	 * 
	 * @param emp
	 */
	public static void ModificarCategoria(Empresa emp) {
		@SuppressWarnings("resource")
		Scanner lector = new Scanner(System.in);
		String cod, descrp;
		double recargo;
		boolean catExiste = false;
		Categoria o;

		System.out.print("Código de la categoría que quiere modificar: ");
		cod = lector.nextLine();

		if(!emp.getCategorias().containsKey(cod)) { //Comprueba que el carnet exista dentro del TreeMap.
			System.out.println("La categoría que intenta modificar no existe, mire el listado e intentelo de nuevo.");
		} else {
			catExiste = true;
		}

		if (catExiste) { //Solo sobreescribe la categoría si este existe. 
			System.out.print("Descripción: ");
			descrp = lector.nextLine();
			
			System.out.print("Recargo: ");
			recargo = lector.nextDouble();
			lector.nextLine();
			
			if (!descrp.equals("")) {
				o = new Categoria(cod, descrp, recargo);
			} else {
				o = new Categoria(cod, recargo);
			}

			emp.AñadirCategoria(o);
		}
	}
	/**Le pide al usuario la key de una categoria y la borra.
	 * 
	 * @param emp
	 */
	public static void EliminarCategoria(Empresa emp) {
		@SuppressWarnings("resource")
		Scanner lector = new Scanner(System.in);
		String cod;

		System.out.print("Código de la categoría que desea eliminar: ");
		cod = lector.nextLine();

		if(!emp.getCategorias().containsKey(cod)) { 
			System.out.println("La categoría que busca no existe, mire el listado e intentelo de nuevo.");
		} else {
			emp.BorrarCarnet(cod);
		}
		System.out.println();
	}
	/**Muestra el menu de listados de categorias al usuario y llama al metodo del listado que elija.
	 * 
	 * @param emp
	 */
	public static void ListadosCategoria(Empresa emp) {
		String[] l = {"1 - CÓDIGO", "2 - RECARGO", "3 - ATRÁS"};
		String eleccion;

		do {
			eleccion = MenuOpciones("ORDENAR POR:", "-", l, "1-2-3", "-", "Introduzca que opción quiere elegir: ", "Esa opción no es válida, pruebe de nuevo.");

			switch (eleccion) {
			case "1": {
				MostrarListado(new TreeMap<String, Object>(emp.getCategorias()));
				break;
			}
			case "2": {
				MostrarListado(new TreeMap<String, Object>(emp.getCategorias()), new CategoriaRecargo());
				break;
			}
			}
			System.out.println("");
		} while (!eleccion.equals("3"));
	}
	/**Pide al usuario la key de una categoria y muestra los datos de esta.
	 * 
	 * @param emp
	 */
	public static void BuscarCategoria(Empresa emp) {
		@SuppressWarnings("resource")
		Scanner lector = new Scanner(System.in);
		String cod;
		do {
			
			System.out.println("Código de la categoría que quiere buscar (salir para cancelar): ");
			cod = lector.nextLine();
			
			if (!emp.getCategorias().containsKey(cod)) {
				System.out.println("La categoría que busca no existe. Mire el listado y pruebe de nuevo.");
			}
			
		} while(!emp.getCategorias().containsKey(cod) && !cod.equalsIgnoreCase("SALIR"));
		
		if (!cod.equalsIgnoreCase("SALIR")) {
			System.out.println(emp.getCategorias().get(cod));
		}
		
	}
	
	
	
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
				Metodos.Titular(Metodos.RodeaCadena("NUEVO CARNET", "-", 5), "-");
				CrearCarnet(emp);
				break;
			}
			case "2": { 
				Metodos.Titular(Metodos.RodeaCadena("MODIFICAR CARNET", "-", 5), "-");
				ModificarCarnet(emp);
				break;
			}
			case "3": {
				Metodos.Titular(Metodos.RodeaCadena("ELIMINAR CARNET", "-", 5), "-");
				Eliminacarnet(emp);
				break;
			}
			case "4": {
				Metodos.Titular(Metodos.RodeaCadena("LISTADO DE CARNETS", "-", 5), "-");
				ListadoCarnet(emp);
				break;
			}
			case "5": {
				Metodos.Titular(Metodos.RodeaCadena("BUSCAR CARNET", "-", 5), "-");
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
		
		do {
		System.out.print("Código ('salir' para cancelar): ");
		codigo = lector.nextLine();
		
		if(emp.getCarnets().containsKey(codigo)) {
			System.out.println("Código inválido, ya existe un carnet con este código.");
		}

		} while(emp.getCarnets().containsKey(codigo) && !codigo.equalsIgnoreCase("SALIR"));
		
		if (!codigo.equalsIgnoreCase("SALIR")) {
			System.out.print("Descripción: ");
			descrp = lector.nextLine();

			if (!descrp.equals("")) {
				o = new CarnetConducir(codigo, descrp);
			} else {
				o = new CarnetConducir(codigo);
			}

			emp.AñadirCarnet(o);
		}
	}
	/**Le pide al usuario la key de un carnet y llama al metodo de crear un nuevo carnet pero colocando la misma key.
	 * 
	 * @param emp
	 */
	public static void ModificarCarnet(Empresa emp) {
		@SuppressWarnings("resource")
		Scanner lector = new Scanner(System.in);
		String cod, descrp;
		boolean carExiste = false;
		CarnetConducir o;

		System.out.print("Código del carnet que quiere modificar: ");
		cod = lector.nextLine();

		if(!emp.getCarnets().containsKey(cod)) { //Comprueba que el carnet exista dentro del TreeMap.
			System.out.println("El carnet que intenta modificar no existe, mire el listado e intentelo de nuevo.");
		} else {
			carExiste = true;
		}

		if (carExiste) { //Solo sobreescribe el carnet si este existe. 
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

		System.out.print("Código del carnet que desea eliminar: ");
		cod = lector.nextLine();

		if(!emp.getCarnets().containsKey(cod)) { //Comprueba que el carnet exista dentro del TreeMap.
			System.out.println("El carnet que busca no existe, mire el listado e intentelo de nuevo.");
		} else {
			emp.BorrarCarnet(cod);
		}
		System.out.println();
	}
	/**muestra un listado de todos los carnets.
	 * 
	 * @param emp
	 */
	public static void ListadoCarnet(Empresa emp) {
		MostrarListado(new TreeMap<String, Object>(emp.getCarnets()));
		System.out.println("");
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
