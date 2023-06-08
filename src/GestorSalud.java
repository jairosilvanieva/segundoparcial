import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;

public class GestorSalud {
    private int kitsDisponibles = 100;
    private LinkedHashSet<Persona> personas;
    private HashMap<Integer, Test> testResults;
    private ObjectMapper objectMapper;
    private Random random;
    private int nextKitNum = 1;

    public GestorSalud() {
        personas = new LinkedHashSet<>();
        testResults = new HashMap<>();
        objectMapper = new ObjectMapper();
        random = new Random();
    }

    public int registrar(Persona persona) throws SinKitExcepcion {
        if (kitsDisponibles == 0) {
            throw new SinKitExcepcion("No hay más kits disponibles.");
        }


        for (Persona p : personas) {
            if (p.getDni().equals(persona.getDni())) {
                throw new IllegalArgumentException("El DNI ya está registrado.");
            }
        }


        int kitNum = nextKitNum;
        nextKitNum++;
        persona.setKitNum(kitNum);
        personas.add(persona);
        kitsDisponibles--;
        return kitNum;
    }
    public void testear() {
        for (Persona p : personas) {
            double temp = 36 + random.nextDouble() * 3; // Genera temperatura entre 36 y 39
            Test t = new Test(p.getDni(), p.getBarrio(), temp);
            testResults.put(p.getKitNum(), t);
        }
    }

    public void aislar() throws AislarExcepcion {
        for (Map.Entry<Integer, Test> entry : testResults.entrySet()) {
            Test t = entry.getValue();
            if (t.getTemperatura() >= 38) {
                Urgente u = new Urgente(entry.getKey(), t.getBarrio());
                throw new AislarExcepcion(u);
            }
        }
    }
    public void añadirKits(int cantidad) {
        kitsDisponibles += cantidad;
    }

    public int getKitsDisponibles() {
        return kitsDisponibles;
    }

    public void guardarUrgente(Urgente u) {
        try (FileOutputStream fos = new FileOutputStream("urgente.dat");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(u);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void generarJSON() {
        HashMap<String, Object> map = new HashMap<>();

        List<Persona> sanos = new ArrayList<>();
        List<Test> aislar = new ArrayList<>();

        for (Map.Entry<Integer, Test> entry : testResults.entrySet()) {
            if (entry.getValue().getTemperatura() < 38) {
                sanos.add(getPersonaByDNI(entry.getValue().getDni()));
            } else {
                aislar.add(entry.getValue());
            }
        }

        map.put("sanos", sanos);
        map.put("aislar", aislar);

        try {
            objectMapper.writeValue(new File("salud.json"), map);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Persona getPersonaByDNI(String dni) {
        return personas.stream().filter(p -> p.getDni().equals(dni)).findFirst().orElse(null);
    }
    public LinkedHashSet<Persona> getPersonas() {
        return personas;
    }
}