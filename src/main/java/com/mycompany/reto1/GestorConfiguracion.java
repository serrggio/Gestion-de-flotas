package com.mycompany.reto1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 *
 * @author sergi
 */
public class GestorConfiguracion {
    private String nombre;
    private double version;
    private int vehiculos;
    private int kilometraje;
    
    File fichero = new File("datos/config/empresa.dat");
    
    public void guardarConfiguracion(File fichero){
        fichero.getParentFile().mkdirs();
        
        try(FileOutputStream fos = new FileOutputStream(fichero);
            DataOutputStream dos = new DataOutputStream(fos)) {
            
            dos.writeUTF(nombre);
            dos.writeDouble(version);
            dos.writeInt(vehiculos);
            dos.writeInt(kilometraje);
            
            System.out.println("Se ha guardado correctamente");
        } catch(IOException e){
            System.out.println("Error: "+e.getMessage());
        }
    }
    
    public void leerConfiguracion(File fichero) {
        try (FileInputStream fis = new FileInputStream(fichero);
             DataInputStream dis = new DataInputStream(fis)) {

            while(true) {
                this.nombre = dis.readUTF();
                this.version = dis.readDouble();
                this.vehiculos = dis.readInt();
                this.kilometraje = dis.readInt();

                System.out.println("Datos leídos: " + this.nombre + " - v" + this.version);
            }

        } catch (EOFException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (IOException e){
            System.out.println("Fin de lectura del fichero");
        }
    }
    
    public GestorConfiguracion(String nombre, double version, int vehiculos, int kilometraje) {
        this.nombre = nombre;
        this.version = version;
        this.vehiculos = vehiculos;
        this.kilometraje = kilometraje;
    }

    public GestorConfiguracion() {
    }
}
