package metodosUI;
import java.util.Scanner;

import clases.Empresa;
import clases.Oficina;
import metodos.Metodos;

public class InterfacesDeUsuario {

	
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
	/**Pide datos al usuario y crea una empresa vacia, solo con nombre y cif.
	 * 
	 * @return
	 */
	public static Empresa CrearEmpresa() {
		Scanner lector = new Scanner(System.in);
		
		String nombre;
		String cIF;
		
		Metodos.Titular(Metodos.RodeaCadena("NUEVA EMPRESA", "-", 5), "-");
		
		System.out.print("Nombre de la Empresa: ");
		nombre = lector.nextLine();
		

		System.out.print("CIF de la Empresa: ");
		cIF = lector.nextLine();
		
		Empresa E = new Empresa(nombre, cIF);
		return E;
	}
	
	public static void CrearOficina(Empresa emp) {
		
		Metodos.Titular(Metodos.RodeaCadena("NUEVA OFICINA", "-", 5), "-");
		
		
		
		return o;
	}
	
	
	
	
	
	
	
	
	
}
