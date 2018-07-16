/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linkia_dami_c12;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author dmorenoar
 */
public class Files {


    public static void main(String[] args) throws IOException {

        //Definir el separador para las rutas de las carpetas según el SO -> /
        String separador = File.separator;

        //Definir la ruta hasta dónde está nuestro proyecto
        String rutaProyecto = System.getProperty("user.dir");

        //Montar la ruta del proyecto para generar la carpeta
        String rutaCarpeta = rutaProyecto + separador + "archivos";

        //Utilizaremos el objeto file para crear nuestra carpeta en la ruta que le hemos indicado        
        File carpeta = new File(rutaCarpeta);

        //Comprobar si la carpeta existe, y si no la creamos
        if (!carpeta.exists()) {
            carpeta.mkdir(); // Creamos la carpeta utilizando el método mkdir
        } else {
            System.out.println("La carpeta ya existe");
        }

        //Creamos la instancia del fichero que queremos trabajar
        File f = new File(rutaCarpeta + separador + "starwars.txt");

        fInputDocument(f);

        fOutputDocument(f);
        
        randomAccessWrite(f);
        
        randomAccessRead(f);
        
    }
    /*Igual que con FileReader, usando el método read(), cuando llega al 
    final del fichero devuelve -1. Su diferencia básica es que con FileReader leemos caracteres y FileInputStream lee bytes.*/
    public static void fInputDocument(File f) {
        
        FileInputStream fileInput = null;
        try {
            System.out.println("Vamos a leer del fichero usando InputStream");
            fileInput = new FileInputStream(f);
            int cont = 0;
            String txtDocument = "";

            while (cont != -1) {
                cont = fileInput.read();
                txtDocument = txtDocument + (char) cont;
            }
            System.out.println(txtDocument);
            fileInput.close();
        } catch (Exception e) {
            System.out.println("No he podido leer lo que me pides");
        }

    }

    /*Vamos a ver un ejemplo de como escribir en el fichero, usaremos el método write con el que 
    podemos usar un numero que corresponderá a la tabla ASCII o un array de bytes.*/
    public static void fOutputDocument(File f) {
        FileOutputStream fileOut = null;
        String cadenaTxt = "";
        try {
            System.out.println("Vamos a grabar algo en el fichero usando OutputStream");
            fileOut = new FileOutputStream(f);
            cadenaTxt = "Anakin se entrego al lado oscuro de la fuerza";
            //Copiamos el texto en un array de bytes
            byte[] txtBytes = cadenaTxt.getBytes();
            fileOut.write(txtBytes);

            fileOut.close();

        } catch (Exception ex) {
            System.out.println("No he podido guardar el contenido en el documento");
        }

    }
    
     public static void randomAccessWrite(File f) throws IOException{
        RandomAccessFile random = new RandomAccessFile(f,"rw");
        random.seek(2); //Escribir a partir de una posición
        random.write("La fuerza es poderosa en ti...".getBytes());
        random.writeBytes("Puedo escribir con texto");
        //random.writeChars("La fuerza es poderosa en ti...");
        random.close();     
    }
    
    public static void randomAccessRead(File f) throws IOException{
        
        RandomAccessFile random = new RandomAccessFile(f,"rw");
        //Movemos el puntero a la posición que queremos del fichero
        random.seek(5);
        
        //Leemos a partir de esa posición
        System.out.println(random.readLine());
        
        random.close();     
    }
    

}
