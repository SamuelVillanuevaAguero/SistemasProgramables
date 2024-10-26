/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Samue
 */
public class Registro {
    private final SimpleStringProperty fecha;
    private final SimpleIntegerProperty ritmoCardiaco;
    private final SimpleIntegerProperty nivelOxigenacion;

    public Registro(String fecha, int ritmoCardiaco, int nivelOxigenacion) {
        this.fecha = new SimpleStringProperty(fecha);
        this.ritmoCardiaco = new SimpleIntegerProperty(ritmoCardiaco);
        this.nivelOxigenacion = new SimpleIntegerProperty(nivelOxigenacion);
    }

    // Getters y setters
    public String getFecha() { return fecha.get(); }
    public void setFecha(String value) { fecha.set(value); }

    public int getRitmoCardiaco() { return ritmoCardiaco.get(); }
    public void setRitmoCardiaco(int value) { ritmoCardiaco.set(value); }

    public int getNivelOxigenacion() { return nivelOxigenacion.get(); }
    public void setNivelOxigenacion(int value) { nivelOxigenacion.set(value); }
}


