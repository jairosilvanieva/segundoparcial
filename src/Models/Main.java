package Models;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        SistemaDeSalud sistema = new SistemaDeSalud();

        // Crear algunas personas y registrarlas
        Persona persona1 = new Persona();
        persona1.setNombre("Juan");
        persona1.setApellido("Perez");
        persona1.setEdad(30);
        persona1.setBarrio("Barrio1");
        persona1.setDni("12345678");
        persona1.setOcupacion("Ingeniero");

        Persona persona2 = new Persona();
        persona2.setNombre("Ana");
        persona2.setApellido("Lopez");
        persona2.setEdad(25);
        persona2.setBarrio("Barrio2");
        persona2.setDni("87654321");
        persona2.setOcupacion("Doctora");

        Persona persona3 = new Persona();
        persona3.setNombre("Analia");
        persona3.setApellido("Pere");
        persona3.setEdad(50);
        persona3.setBarrio("Barrio3");
        persona3.setDni("43215678");
        persona3.setOcupacion("Paseadora");


        Persona persona4 = new Persona();
        persona4.setNombre("Pedro");
        persona4.setApellido("Pascal");
        persona4.setEdad(36);
        persona4.setBarrio("Barrio4");
        persona4.setDni("98765432");
        persona4.setOcupacion("Boxeador");



        try {
            sistema.registrarPersona(persona1);
            System.out.println("La cantidad de kit que quedan es: "+ sistema.getCantidadDeKits());
            sistema.registrarPersona(persona2);
            System.out.println("La cantidad de kit que quedan es: "+ sistema.getCantidadDeKits());
            sistema.registrarPersona(persona3);
            System.out.println("La cantidad de kit que quedan es: "+ sistema.getCantidadDeKits());
            sistema.registrarPersona(persona4);
            System.out.println("La cantidad de kit que quedan es: "+ sistema.getCantidadDeKits());
        } catch (ExcepcionSinKit e) {
            System.out.println(e.getMessage());


            Scanner scanner = new Scanner(System.in);
            System.out.println("Por favor ingresa la cantidad de kits adicionales que el SSM proporcionará: ");
            int kitsAdicionales = scanner.nextInt();
            sistema.setCantidadDeKits(sistema.getCantidadDeKits() + kitsAdicionales);
        }

        for (Persona persona : sistema.getPersonas()) {
            System.out.println("Nombre: " + persona.getNombre());
            System.out.println("Apellido: " + persona.getApellido());
            System.out.println("Edad: " + persona.getEdad());
            System.out.println("Barrio: " + persona.getBarrio());
            System.out.println("DNI: " + persona.getDni());
            System.out.println("Ocupación: " + persona.getOcupacion());
            System.out.println("-----------------");
        }


        sistema.testear();


        sistema.aislar();


        sistema.generarJson();
    }
}