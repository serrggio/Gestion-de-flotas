/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.reto1;

import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author sergi
 */
public class GestorBackup {

    public void crearBackup(File fichero, ArrayList<Vehiculo> listaVehiculos) {
        fichero.getParentFile().mkdirs();
        
        try (FileOutputStream fos = new FileOutputStream(fichero);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
             
            oos.writeObject(listaVehiculos);
            System.out.println("Backup binario creado con éxito.");
            
        } catch (IOException e) {
            System.out.println("Error al crear backup: " + e.getMessage());
        }
    }

    public ArrayList<Vehiculo> restaurarBackup(File fichero) {
        ArrayList<Vehiculo> listaRestaurada = new ArrayList<>();
        
        if (!fichero.exists()) {
            System.out.println("No hay archivo de backup para restaurar.");
            return listaRestaurada;
        }

        try (FileInputStream fis = new FileInputStream(fichero);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
             
            listaRestaurada = (ArrayList<Vehiculo>) ois.readObject();
            System.out.println("Backup restaurado correctamente.");
            
        } catch (ClassNotFoundException e) {
            System.out.println("Clase no encontrada al restaurar: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error de lectura del backup: " + e.getMessage());
        }
        
        return listaRestaurada;
    }
}
