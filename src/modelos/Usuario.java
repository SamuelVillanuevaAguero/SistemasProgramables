/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

/**
 *
 * @author Samue
 */
public class Usuario {
    private String nombre;
    private String alias;
    private String contraseña;
    private String correo;
    private int edad;
    private double peso;
    private String sexo;

    public Usuario(String nombre, String alias, String contraseña, String correo, int edad, double peso, String sexo) {
        this.nombre = nombre;
        this.alias = alias;
        this.contraseña = contraseña;
        this.correo = correo;
        this.edad = edad;
        this.peso = peso;
        this.sexo = sexo;
    }

    @Override
    public String toString() {
        return nombre + alias + contraseña + correo + edad + peso + sexo;
    }
    
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String Correo) {
        this.correo = Correo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    
    public boolean isEmpty(){
        return nombre.isEmpty() || alias.isEmpty() || contraseña.isEmpty() || correo.isEmpty() || edad == 0 || peso == 0 || sexo.isEmpty();
    }
    
}
