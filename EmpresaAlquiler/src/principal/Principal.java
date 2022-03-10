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
			System.out.println("No se ha encontrado el fichero, al realizar un guardado se crear� uno nuevo.");
		}

		if (emp == null) {
			System.out.println("No se ha encontrado ninguna empresa existente, tendr� que crear una nueva.");
			emp = InterfacesDeUsuario.CrearEmpresa();
		}



		m[0] = "A - GESTI�N DE OFICINAS";
		m[1] = "B - GESTI�N DE CLIENTES";
		m[2] = "C - GESTI�N DE EMPLEADOS";
		m[3] = "D - GESTI�N DE ALQUILERES";
		m[4] = "E - GESTI�N DE CATEGORIAS";
		m[4] = "F - GESTI�N DE VEHICULOS";
		m[5] = "G - GUARDAR PROGRESO";
		m[6] = "H - GUARDAR Y SALIR";


		do {
			eleccion = InterfacesDeUsuario.MenuOpciones("ALQUILER DE VEHICULOS", "-", m, "A-B-C-D-E-F-G", "-", "Introduzca que opci�n quiere elegir: ", "Esa opci�n no es v�lida, pruebe de nuevo.");

			switch (eleccion.toUpperCase()) {
			case "A": {
				String[] o = {"1 - CREAR NUEVA OFICINA", "2 - MODIFICAR OFICINA", "3 - ELIMINAR OFICINA","4 - LISTADOS", "5 - VOLVER AL MEN�"};

				do {
					eleccion = InterfacesDeUsuario.MenuOpciones("GESTI�N DE OFICINAS", "-", o, "1-2-3-4", "-", "Introduzca que opci�n quiere elegir: ", "Esa opci�n no es v�lida, pruebe de nuevo.");

					switch (eleccion.toUpperCase()) {
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
			case "B": {
				String[] o = {"1 - CREAR NUEVO CLIENTE", "2 - MODIFICAR CLIENTE", "3 - ELIMINAR CLIENTE","4 - LISTADOS", "5 - VOLVER AL MEN�"};

				do {
					eleccion = InterfacesDeUsuario.MenuOpciones("GESTI�N DE OFICINAS", "-", o, "1-2-3-4", "-", "Introduzca que opci�n quiere elegir: ", "Esa opci�n no es v�lida, pruebe de nuevo.");

					switch (eleccion.toUpperCase()) {
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
			case "C": {
			}
			case "D": {
			}
			case "E": {
			}
			case "F": {
			}
			case "G": {
				try {
					Serializacion.writeToFile(emp);
				} catch (IOException e) {
					System.out.println("El archivo no se ha guardado correctamente.");
				}
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
