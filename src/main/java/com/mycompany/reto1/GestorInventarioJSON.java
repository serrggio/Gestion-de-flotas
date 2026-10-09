/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package com.mycompany.reto1;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author sergi
 */
public class GestorInventarioJSON {
    
    private ArrayList<Vehiculo> listaVehiculos = new ArrayList<>();
    private HashMap<String, Vehiculo> mapaVehiculos = new HashMap<>();

    public void exportarJSON(File fichero) {
        fichero.getParentFile().mkdirs(); // Asegura que exista la ruta
        
        // Creamos Gson con formato de sangrado exigido
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        try (FileWriter fw = new FileWriter(fichero)) {
            gson.toJson(listaVehiculos, fw);
            System.out.println("Exportación JSON completada con éxito.");
        } catch (IOException e) {
            System.out.println("Error al exportar JSON: " + e.getMessage());
        }
    }
    
    public void importarJSON(File fichero) {
        if (!fichero.exists()) {
            System.out.println("El fichero JSON no existe aún.");
            return;
        }
        
        Gson gson = new Gson();
        // TypeToken le dice a Gson qué tipo exacto de lista debe construir
        Type tipoLista = new TypeToken<ArrayList<Vehiculo>>(){}.getType();
        
        try (FileReader fr = new FileReader(fichero)) {
            listaVehiculos = gson.fromJson(fr, tipoLista);
            
            if (listaVehiculos == null) {
                listaVehiculos = new ArrayList<>();
            }
            
            // Llenamos el HashMap para permitir búsquedas directas
            mapaVehiculos.clear();
            for (Vehiculo v : listaVehiculos) {
                mapaVehiculos.put(v.getMatricula(), v);
            }
            
            System.out.println("Datos JSON cargados. Vehículos: " + listaVehiculos.size());
        } catch (IOException e) {
            System.out.println("Error al leer JSON: " + e.getMessage());
        }
    }

    // Métodos para interactuar con los datos desde otras partes del programa
    public void agregarVehiculo(Vehiculo v) {
        listaVehiculos.add(v);
        mapaVehiculos.put(v.getMatricula(), v);
    }

    public ArrayList<Vehiculo> getListaVehiculos() { return listaVehiculos; }
    public HashMap<String, Vehiculo> getMapaVehiculos() { return mapaVehiculos; }
}