import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GestorSalud gestor = new GestorSalud();

        Persona p1 = new Persona("Juan", "Pérez", 30, "Barrio Norte", "DNI1", "Ingeniero");
        Persona p2 = new Persona("María", "Gómez", 35, "Barrio Sur", "DNI2", "Médica");
        Persona p3 = new Persona("Carlos", "Rodríguez", 40, "Barrio Este", "DNI3", "Profesor");

        try {
            gestor.registrar(p1);
            gestor.registrar(p2);
            gestor.registrar(p3);
        } catch (SinKitExcepcion e) {
            System.out.println(e.getMessage());
            Scanner sc = new Scanner(System.in);
            System.out.println("Ingrese la nueva cantidad de kits: ");
            int cantidad = sc.nextInt();
            gestor.añadirKits(cantidad);
        }

        gestor.testear();

        try {
            gestor.aislar();
        } catch (AislarExcepcion e) {
            System.out.println(e.getMessage());
            Urgente u = e.getUrgente();
            System.out.println("Número de test: " + u.getNumTest() + ", Barrio: " + u.getBarrio());
            gestor.guardarUrgente(u);
        }

        System.out.println("Kits restantes: " + gestor.getKitsDisponibles());

        gestor.generarJSON();

        System.out.println("Las personas que se atendieron son:");
        for (Persona persona : gestor.getPersonas()) {
            System.out.println(persona.getNombre() + " " + persona.getApellido());
        }
    }
}