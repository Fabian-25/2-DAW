package nominas.laboral;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class FicherosDAO {

    public void realizarCopia(ArrayList<Empleado> empleados, EmpleadoDAO dao)
            throws Exception {

        PrintWriter empleadosTxt =
                new PrintWriter(new FileWriter("copia_empleados.txt"));

        PrintWriter nominasTxt =
                new PrintWriter(new FileWriter("copia_nominas.txt"));

        for (Empleado empleado : empleados) {

            empleadosTxt.println(
                    empleado.nombre + ";" +
                            empleado.dni + ";" +
                            empleado.sexo + ";" +
                            empleado.getCategoria() + ";" +
                            empleado.anyosTrabajados
            );

            int sueldo = dao.obtenerSueldo(empleado.dni);

            nominasTxt.println(
                    empleado.dni + ";" + sueldo
            );
        }

        empleadosTxt.close();
        nominasTxt.close();
    }
}