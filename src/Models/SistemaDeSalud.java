package Models;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

class SistemaDeSalud {
    private LinkedHashSet<Persona> personas;
    private List<Test> tests;
    private int cantidadDeKits;

    public SistemaDeSalud() {
        this.personas = new LinkedHashSet<>();
        this.tests = new ArrayList<>();
        this.cantidadDeKits = 10;
    }

    public LinkedHashSet<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(LinkedHashSet<Persona> personas) {
        this.personas = personas;
    }

    public List<Test> getTests() {
        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }

    public int getCantidadDeKits() {
        return cantidadDeKits;
    }

    public void setCantidadDeKits(int cantidadDeKits) {
        this.cantidadDeKits = cantidadDeKits;
    }

    public void registrarPersona(Persona persona) throws ExcepcionSinKit {
        if (this.cantidadDeKits == 0) {
            throw new ExcepcionSinKit("No hay más kits disponibles.");
        } else {
            if (!this.personas.contains(persona)) {
                this.personas.add(persona);
                this.cantidadDeKits--;
            } else {
                System.out.println("La persona con DNI: " + persona.getDni() + " ya ha sido registrada.");
            }
        }
    }

    public void testear() {
        for (Persona persona : this.personas) {
            double temperatura = ThreadLocalRandom.current().nextDouble(36, 40);
            Test test = new Test();
            test.setNumeroKit(this.cantidadDeKits++);
            test.setPersona(persona);
            test.setTemperatura(temperatura);
            this.tests.add(test);
        }
    }



    public void aislar() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("urgente.dat"))) {
            for (Test test : this.tests) {
                if (test.getTemperatura() >= 38.0) {
                    oos.writeObject(test);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void generarJson() {
        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, List<Object>> resultado = new HashMap<>();
        resultado.put("sanos", new ArrayList<>());
        resultado.put("aislar", new ArrayList<>());

        for (Test test : this.tests) {
            if (test.getTemperatura() < 38.0) {
                resultado.get("sanos").add(test.getPersona());
            } else {
                resultado.get("aislar").add(test);
            }
        }

        try {
            objectMapper.writeValue(new File("resultado.json"), resultado);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}