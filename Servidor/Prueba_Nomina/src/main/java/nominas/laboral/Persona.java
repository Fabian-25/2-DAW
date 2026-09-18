package nominas.laboral;

/**
 * Representa una persona dentro del mundo laboral
 */
public class Persona {
    /**
     *
     */
    public String nombre;
    public String dni;
    public char sexo;

    public Persona(String nombre, String dni, char sexo) {
        this.nombre = nombre;
        this.dni = dni;
        this.sexo = sexo;
    }

    public Persona(String nombre, char sexo) {
        this.nombre = nombre;
        this.sexo = sexo;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", dni='" + dni + '\'' +
                ", sexo=" + sexo +
                '}';
    }



}
