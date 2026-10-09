/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.reto1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 *
 * @author sergi
 */
public class Main {

public static void main(String[] args) {
        inicializarCarpetas();

        File fileConfig = new File("datos/config/empresa.dat");
        File fileJson = new File("datos/json/flota.json");
        File fileBackup = new File("datos/backup/flota_backup.ser");
        File fileXml = new File("datos/reports/informe_flota.xml");

        GestorConfiguracion config = new GestorConfiguracion("Logistica Express", 1.0, 1, 15000);        GestorInventarioJSON inventario = new GestorInventarioJSON();
        GestorBackup backup = new GestorBackup();
        GestorReportesXML reportes = new GestorReportesXML();

        // 1. Crear datos para probar
        Vehiculo v1 = new Vehiculo();
        v1.setMatricula("1234ABC");
        v1.setMarca("Ford");
        v1.setModelo("Transit");
        v1.setAnio(2022);
        v1.setKilometraje(15000.5);
        v1.setCategoria("Furgoneta");

        inventario.agregarVehiculo(v1);

        // 2. Ejecutar la escritura en todos los formatos
        config.guardarConfiguracion(fileConfig);
        inventario.exportarJSON(fileJson);
        backup.crearBackup(fileBackup, inventario.getListaVehiculos());
        reportes.generarInformeDOM(fileXml, inventario.getListaVehiculos());

        System.out.println("Proceso finalizado. Revisa las carpetas, los archivos ya deben estar ahí.");
    }

    private static void inicializarCarpetas() {
        String[] directorios = {
            "datos/config",
            "datos/json",
            "datos/backup",
            "datos/reports"
        };

        for (String dir : directorios) {
            try {
                Path ruta = Paths.get(dir);
                Files.createDirectories(ruta);
            } catch (IOException e) {
                System.out.println("Error de I/O al crear la carpeta " + dir + ": " + e.getMessage());
            }
        }
    }
}
