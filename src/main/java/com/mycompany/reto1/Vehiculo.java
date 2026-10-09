package com.mycompany.reto1;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/**
 *
 * @author sergi
 */
public class Vehiculo implements Serializable{
    private String matricula;
    private String marca;
    private String modelo;
    private int anio;
    @SerializedName("kms_recorridos")
    private double kilometraje;
    private String categoria;

    public Vehiculo() {
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public double getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(double kilometraje) {
        this.kilometraje = kilometraje;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    public static void getTotalVehiculos() {
        
    }
    
    public static void getTotalKilometros() {
        
    }
}
