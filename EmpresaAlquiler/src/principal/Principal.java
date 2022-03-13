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
import metodosSer.Serializacion;
import metodosUI.InterfacesDeUsuario;
import miscomparadores.PersonaNombreCompleto;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Empresa emp = null;
		String[] m = new String[9];
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
		m[3] = "D - GESTIÓN DE VEHICULOS";
		m[4] = "E - GESTIÓN DE ALQUILERES";
		m[5] = "F - GESTIÓN DE CATEGORIAS";
		m[6] = "G - GESTIÓN DE CARNETS";
		m[7] = "H - GUARDAR PROGRESO";
		m[8] = "I - GUARDAR Y SALIR";


		do {
			eleccion = InterfacesDeUsuario.MenuOpciones("ALQUILER DE VEHICULOS", "-", m, "A-B-C-D-E-F-G-H-I", "-", "Introduzca que opción quiere elegir: ", "Esa opción no es válida, pruebe de nuevo.");

			switch (eleccion.toUpperCase()) {
			case "A": {
				InterfacesDeUsuario.GestionOficinas(emp);
				break;
			}
			case "B": {
				InterfacesDeUsuario.GestionClientes(emp);
				break;
			}
			case "C": {
				InterfacesDeUsuario.GestionEmpelados(emp);
				break;
			}
			case "D": {
				InterfacesDeUsuario.GestionVehiculos(emp);
				break;
			}
			case "E": {
				InterfacesDeUsuario.GestionAlquileres(emp);
				break;
			}
			case "F": {
				InterfacesDeUsuario.GestionCategorias(emp);
				break;
			}
			case "G": {
				InterfacesDeUsuario.GestionCarnets(emp);
				break;
			}
			case "H": {
				try {
					Serializacion.writeToFile(emp);
					System.out.println("El archivo se ha guardado correctamente.");
				} catch (IOException e) {
					System.out.println("No se ha podido gruardar el archivo.");
				}
				break;
			}
			}

		} while(!eleccion.equalsIgnoreCase("I") );



		try {
			Serializacion.writeToFile(emp);
			System.out.println("El archivo se ha guardado correctamente.");
		} catch (IOException e) {
			System.out.println("No se ha podido gruardar el archivo.");
		}

	}

}
