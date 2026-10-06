package nominas.laboral;


import java.util.ArrayList;
import java.util.Scanner;

public class CalculaNominas {
    public static void main(String[] args) {

        try {


            EmpleadoDAO dao = new EmpleadoDAO();

            LeerTxt l = new LeerTxt();
            ArrayList<Empleado> empleados = l.leer();

            for (Empleado empleado : empleados) {
                dao.insertarEmpleado(empleado);
                dao.insertarNomina(empleado);
            }

<<<<<<< HEAD
            EmpleadoDAO dao = new EmpleadoDAO();
            dao.altaEmpleado(empleados.get(0));

=======
>>>>>>> 659233cff8894ecc2573f9a909e0c6c28ddc78d4
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
                        mostrarEmpleados(dao);
                        break;

                    case 2:
                        mostrarSueldo(sc, dao);
                        break;

                    case 3:
                        modificarEmpleado(sc, dao);
                        break;

                    case 4:
                        recalcularSueldo(sc, dao);
                        break;

                    case 5:
                        recalcularTodosSueldos(dao);
                        break;

                    case 6:
                        realizarCopia(dao);
                        break;

                    default:
                        System.out.println("Opcion no valida");

                }

            }    while (opcion != 0) ;

            } catch(Exception e){
                System.out.println(e.getMessage());
            }

        }

    private static void mostrarEmpleados(EmpleadoDAO dao) throws Exception {

        ArrayList<Empleado> empleadosBD = dao.obtenerEmpleados();

        for (Empleado empleado : empleadosBD) {
            System.out.println(empleado);
        }
    }

    private static void mostrarSueldo(Scanner sc, EmpleadoDAO dao) throws Exception {

        System.out.print("Dni del empleado que desea ver el salario: ");
        String respuesta = sc.nextLine();

        int sueldo = dao.obtenerSueldo(respuesta);

        if (sueldo == -1) {
            System.out.println("Empleado no encontrado");
        } else {
            System.out.println("Sueldo: " + sueldo);
        }
    }

    private static void modificarEmpleado(Scanner sc, EmpleadoDAO dao) throws Exception {

        System.out.print("Dni del empleado que desea modificar: ");
        String dni = sc.nextLine();

        Empleado empleadoEncontrado = dao.obtenerEmpleadoPorDni(dni);

        if (empleadoEncontrado == null) {
            System.out.println("Empleado no encontrado");
            return;
        }

        System.out.println("===== MODIFICAR EMPLEADO =====");
        System.out.println("1. Modificar nombre");
        System.out.println("2. Modificar DNI");
        System.out.println("3. Modificar sexo");
        System.out.println("4. Modificar categoría");
        System.out.println("5. Modificar años trabajados");
        System.out.print("Selecciona una opción: ");

        int opcion = sc.nextInt();
        sc.nextLine();

        switch (opcion) {

            case 1:
                System.out.print("Nuevo nombre: ");
                empleadoEncontrado.nombre = sc.nextLine();
                break;

            case 2:
                System.out.print("Nuevo DNI: ");
                empleadoEncontrado.dni = sc.nextLine();
                break;

            case 3:
                System.out.print("Nuevo sexo: ");
                empleadoEncontrado.sexo = sc.nextLine().charAt(0);
                break;

            case 4:
                System.out.print("Nueva categoría: ");
                empleadoEncontrado.setCategoria(sc.nextInt());
                sc.nextLine();
                break;

            case 5:
                System.out.print("Nuevos años trabajados: ");
                empleadoEncontrado.anyosTrabajados = sc.nextInt();
                sc.nextLine();
                break;

            default:
                System.out.println("Opción no válida");
                return;
        }

        dao.actualizarEmpleado(empleadoEncontrado);

        System.out.println("Empleado modificado correctamente");
    }

    private static void recalcularSueldo(Scanner sc, EmpleadoDAO dao)
            throws Exception {

        System.out.print("DNI del empleado: ");
        String dni = sc.nextLine();

        Empleado empleado = dao.obtenerEmpleadoPorDni(dni);

        if (empleado == null) {
            System.out.println("Empleado no encontrado");
            return;
        }

        dao.actualizarNomina(empleado);

        System.out.println("Sueldo recalculado: " + Nomina.sueldo(empleado));
    }

    private static void recalcularTodosSueldos(EmpleadoDAO dao)
            throws Exception {

        ArrayList<Empleado> empleados = dao.obtenerEmpleados();

        for (Empleado empleado : empleados) {
            dao.actualizarNomina(empleado);
        }

        System.out.println("Sueldos recalculados correctamente");
    }

    private static void realizarCopia(EmpleadoDAO dao) throws Exception {

        ArrayList<Empleado> empleados = dao.obtenerEmpleados();

        FicherosDAO ficheros = new FicherosDAO();

        ficheros.realizarCopia(empleados, dao);

        System.out.println("Copia realizada correctamente");
    }

}