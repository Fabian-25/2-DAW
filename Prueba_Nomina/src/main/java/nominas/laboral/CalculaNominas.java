package nominas.laboral;

public class CalculaNominas {
    public static void main(String[] args) {

        try{
            Empleado e = new Empleado("James Cosling", "32000032G", 'M', 4, 7);

            Empleado e2 = new Empleado("Ada Lovelace", "32000031R", 'F');

            escribe(e, e2);

            e2.incrAnyo();
            e.setCategoria(9);

            escribe(e, e2);

        } catch (Exception e) {
            System.out.println("Datos no correctos");
        }


    }

    private static void escribe(Empleado e, Empleado e2) {
        Nomina n = new Nomina();
        System.out.println(e.toString() + " ,sueldo=" + n.sueldo(e) + "}");
        System.out.println(e2.toString() + " ,sueldo=" + n.sueldo(e2) + "}");
    }


}

