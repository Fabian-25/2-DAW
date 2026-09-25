package nominas.laboral;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class CalculaNominas {
    public static void main(String[] args) {

        try {
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
                        int respuesta;
                        System.out.println("=========SUBMENU==========");
                        System.out.println("0. Salir");
                        System.out.println("1. Modificar nombre");
                        System.out.println("2. Modificar DNI");
                        System.out.println("3. Modificar sexo");
                        System.out.println("4. Modificar categoria");
                        System.out.println("5. Modificar años trabajados");
                        respuesta = sc.nextInt()
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



