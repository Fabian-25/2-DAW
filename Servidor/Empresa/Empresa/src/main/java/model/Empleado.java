package model;

/**
 * Representa a un empleado de la empresa.
 */
public class Empleado {

    private String nombre;
    private String dni;
    private char sexo;
    private int categoria;
    private int anyosTrabajados;
    private int sueldo;

    /**
     * Construye un empleado con todos sus datos.
     *
     * @param nombre          nombre del empleado
     * @param dni             documento de identidad
     * @param sexo            sexo del empleado
     * @param categoria       categoría profesional
     * @param anyosTrabajados años trabajados
     */
    public Empleado(String nombre, String dni, char sexo,
                    int categoria, int anyosTrabajados) {
        this.nombre = nombre;
        this.dni = dni;
        this.sexo = sexo;
        this.categoria = categoria;
        this.anyosTrabajados = anyosTrabajados;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return dni;
    }

    public char getSexo() {
        return sexo;
    }

    public int getCategoria() {
        return categoria;
    }

    public int getAnyosTrabajados() {
        return anyosTrabajados;
    }
    public int getSueldo() {
        return sueldo;
    }

    public void setSueldo(int sueldo) {
        this.sueldo = sueldo;
    }
}