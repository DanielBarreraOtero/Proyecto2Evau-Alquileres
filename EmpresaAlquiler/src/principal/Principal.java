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
			System.out.println("No se ha encontrado el fichero, al realizar un guardado se crear� uno nuevo.");
		}

		if (emp == null) {
			System.out.println("No se ha encontrado ninguna empresa existente, tendr� que crear una nueva.");
			emp = InterfacesDeUsuario.CrearEmpresa();
		}



		m[0] = "A - GESTI�N DE OFICINAS";
		m[1] = "B - GESTI�N DE CLIENTES";
		m[2] = "C - GESTI�N DE EMPLEADOS";
		m[3] = "D - GESTI�N DE VEHICULOS";
		m[4] = "E - GESTI�N DE ALQUILERES";
		m[5] = "F - GESTI�N DE CATEGORIAS";
		m[6] = "G - GESTI�N DE CARNETS";
		m[7] = "H - GUARDAR PROGRESO";
		m[8] = "I - GUARDAR Y SALIR";


		do {
			eleccion = InterfacesDeUsuario.MenuOpciones("ALQUILER DE VEHICULOS", "-", m, "A-B-C-D-E-F-G-H-I", "-", "Introduzca que opci�n quiere elegir: ", "Esa opci�n no es v�lida, pruebe de nuevo.");

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
