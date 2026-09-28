package nominas.laboral;

import nominas.laboral.conexionDB.ConexionDB;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

public class CalculaNominas {
    public static void main(String[] args) {

        try {

            Connection conexion = ConexionDB.conectar();
            System.out.println("Conexión realizada correctamente");
            conexion.close();
            /*Empleado e = new Empleado("James Cosling", "32000032G", 'M', 4, 7);

            Empleado e2 = new Empleado("Ada Lovelace", "32000031R", 'F');

            System.out.println("Se escribe los empleados");
            escribe(e, e2);

            e2.incrAnyo();
            e.setCategoria(9);

            System.out.println("Se modifican los empleados");
            escribe(e, e2);*/

            LeerTxt l = new LeerTxt();
            ArrayList<Empleado> empleados = l.leer();

            for (Empleado empleado : empleados) {

                System.out.println(empleado);
                if (empleado.dni.equals("32000032G")) {
                    empleado.setCategoria(9);
                }

                if (empleado.dni.equals("32000031R")) {
                    empleado.incrAnyo();
                }

                System.out.println(empleado);
            }

            EmpleadoDAO dao = new EmpleadoDAO();
            dao.insertarEmpleado(empleados.get(0));

            Scanner sc = new Scanner(System.in);
            int opcion;
            do {
                System.out.println("\n===== GESTIÓN DE NÓMINAS =====");
                System.out.println("0. Salir");
                System.out.println("1. Mostrar información de todos los empleados");
                System.out.println("2. Mostrar salario de un empleado");
                System.out.println("3. Modificar datos de un empleado");
                System.out.println("4. Recalcular sueldo de un empleado");
                System.out.println("5. Recalcular sueldos de todos los empleados");
                System.out.println("6. Realizar copia de seguridad");
                System.out.println("==============================");
                System.out.print("Selecciona una opción: ");
                opcion = sc.nextInt();
                sc.nextLine();

                switch (opcion) {
                    case 0:
                        System.out.println("Saliendo del programa...");
                        break;
                    case 1:
                        for (Empleado empleado : empleados){
                            System.out.println(empleado);
                        }
                        break;

                    case 2:
                        String respuesta;
                        System.out.print("Dni del empleado que desea ver el salario: ");
                        respuesta = sc.nextLine();
                        boolean encontrado = false;
                        for (Empleado empleado : empleados){
                            if (empleado.dni.equals(respuesta)){
                                encontrado = true;
                                int sueldo = Nomina.sueldo(empleado);
                                System.out.println("Sueldo: "+sueldo);
                                break;
                            }
                        }

                        if (!encontrado){
                            System.out.println("Empleado no encontrado");
                        }
                        break;

                    case 3:
                        System.out.print("Dni del empleado que desea modificar: ");
                        String dniModificar = sc.nextLine();

                        Empleado empleadoEncontrado = null;

                        for (Empleado empleado : empleados) {
                            if (empleado.dni.equals(dniModificar)) {
                                empleadoEncontrado = empleado;
                                break;
                            }
                        }

                        if (empleadoEncontrado == null) {
                            System.out.println("Empleado no encontrado");
                            break;
                        }

                        int opcionModificar;

                        do {
                            System.out.println("===== MODIFICAR EMPLEADO =====");
                            System.out.println("1. Modificar nombre");
                            System.out.println("2. Modificar DNI");
                            System.out.println("3. Modificar sexo");
                            System.out.println("4. Modificar categoría");
                            System.out.println("5. Modificar años trabajados");
                            System.out.println("0. Volver");
                            System.out.println("==============================");
                            System.out.print("Selecciona una opción: ");

                            opcionModificar = sc.nextInt();
                            sc.nextLine();

                            switch (opcionModificar) {

                                case 1:
                                    System.out.println("Introduzca nuevo nombre: ");
                                    break;

                                case 2:
                                    System.out.println("Modificar DNI");
                                    break;

                                case 3:
                                    System.out.println("Modificar sexo");
                                    break;

                                case 4:
                                    System.out.println("Introduzca nueva categoria: ");
                                    int cambioCategoria = sc.nextInt();
                                    sc.nextLine();
                                    empleadoEncontrado.setCategoria(cambioCategoria);
                                    break;

                                case 5:
                                    System.out.println("Modificar años trabajados");
                                    break;

                                case 0:
                                    System.out.println("Volviendo al menú principal...");
                                    break;

                                default:
                                    System.out.println("Opción no válida");
                            }

                        } while (opcionModificar != 0);

                        break;

                    case 4:
                        System.out.println("Recalcular sueldo de un empleado");
                        break;

                    case 5:
                        System.out.println("Recalcular sueldos de todos los empleados");
                        break;

                    case 6:
                        System.out.println("Realizar copia de seguridad");
                        break;

                    default:
                        System.out.println("Opcion no valida");

                }

            }    while (opcion != 0) ;

            } catch(Exception e){
                System.out.println(e.getMessage());
            }

        }


    private static void escribe(Empleado e, Empleado e2) {
        Nomina n = new Nomina();
        System.out.println(e.toString() + " ,sueldo=" + n.sueldo(e) + "}");
        System.out.println(e2.toString() + " ,sueldo=" + n.sueldo(e2) + "}");
    }
}



