package principal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;

import clases.Categoria;
import clases.Empleado;
import clases.Empresa;
import clases.Oficina;
import clases.Persona;
import comparators.PersonaNombreCompleto;
import metodosSer.Serializacion;
import metodosUI.InterfacesDeUsuario;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Empresa emp = null;
		String[] m = new String[7];
		String eleccion = "";

		try {
			emp = Serializacion.readFile();
		} catch (ClassNotFoundException | IOException e) {
			System.out.println("No se ha encontrado el fichero, al realizar un guardado se creará uno nuevo.");
		}

		if (emp == null) {
			System.out.println("No se ha encontrado ninguna empresa existente, tendrá que crear una nueva.");
			emp = InterfacesDeUsuario.CrearEmpresa();
		}



		m[0] = "A - GESTIÓN DE OFICINAS";
		m[1] = "B - GESTIÓN DE CLIENTES";
		m[2] = "C - GESTIÓN DE EMPLEADOS";
		m[3] = "D - GESTIÓN DE ALQUILERES";
		m[4] = "E - GESTIÓN DE CATEGORIAS";
		m[4] = "F - GESTIÓN DE VEHICULOS";
		m[5] = "G - GUARDAR PROGRESO";
		m[6] = "H - GUARDAR Y SALIR";


		do {
			eleccion = InterfacesDeUsuario.MenuOpciones("ALQUILER DE VEHICULOS", "-", m, "A-B-C-D-E-F-G-H", "-", "Introduzca que opción quiere elegir: ", "Esa opción no es válida, pruebe de nuevo.");

			switch (eleccion.toUpperCase()) {
			case "A": {
				InterfacesDeUsuario.GestionOficinas(eleccion, emp);
				break;
			}
			case "B": {
				InterfacesDeUsuario.GestionClientes(eleccion, emp);
				break;
			}
			case "C": {
//				TODO
				break;
			}
			case "D": {
//				TODO
				break;
			}
			case "E": {
//				TODO
				break;
			}
			case "F": {
//				TODO
				break;
			}
			case "G": {
				try {
					Serializacion.writeToFile(emp);
				} catch (IOException e) {
					System.out.println("No se ha podido gruardar el archivo.");
				}
//				TODO preguntarle a sito si hay algo contrario al break para controlar que el archivo se ha guardado.
				break;
			}
			}

		} while(!eleccion.equalsIgnoreCase("H") );



		try {
			Serializacion.writeToFile(emp);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
