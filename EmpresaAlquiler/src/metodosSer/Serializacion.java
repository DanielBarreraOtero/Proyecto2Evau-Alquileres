package metodosSer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import clases.Empresa;

public class Serializacion {
	
	
	public static void writeToFile(Empresa empresa) throws IOException {
		
		FileOutputStream fs = new FileOutputStream("Empresa.ser");//Creamos el archivo
        ObjectOutputStream os = new ObjectOutputStream(fs);
        
        os.writeObject(empresa);
        os.close();
	}
	public static Empresa readFile() throws IOException, ClassNotFoundException {
		 FileInputStream fis = new FileInputStream("Empresa.ser");
         ObjectInputStream ois = new ObjectInputStream(fis);
         Empresa empresa = (Empresa) ois.readObject();
         ois.close(); fis.close();
         return empresa;
	}
}
