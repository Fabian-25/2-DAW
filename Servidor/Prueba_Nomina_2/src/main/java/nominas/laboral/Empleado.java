package nominas.laboral;

import nominas.laboral.exceptions.DatosNoCorrectosException;

public class Empleado extends Persona{

    private int categoria;
    public int anyosTrabajados;

    public Empleado(String nombre, String dni, char sexo) {
        super(nombre, dni, sexo);
        categoria = 1;
        anyosTrabajados = 0;
    }


    public Empleado(String nombre, String dni, char sexo, int categoria, int anyosTrabajados) throws DatosNoCorrectosException {
        super(nombre, dni, sexo);
        if (categoria < 1 || categoria > 10 || anyosTrabajados < 0){
            throw new DatosNoCorrectosException("Datos erroneos");
        }
        this.categoria = categoria;
        this.anyosTrabajados = anyosTrabajados;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public int getCategoria() {
        return categoria;
    }

    public int getAnyosTrabajados() {
        return anyosTrabajados++;
    }

    public void incrAnyo (){
        this.anyosTrabajados++;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "categoria=" + categoria +
                ", anyosTrabajados=" + anyosTrabajados +
                ", nombre='" + nombre + '\'' +
                ", dni='" + dni + '\'' +
                ", sexo=" + sexo;
    }
}
