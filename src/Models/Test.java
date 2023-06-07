package Models;
import com.fasterxml.jackson.annotation.*;
import java.util.*;
import java.io.Serializable;
class Test implements Serializable {
    private int numeroKit;
    private Persona persona;
    private double temperatura;

    public Test() {
        this.numeroKit = numeroKit;
        this.persona = persona;
        this.temperatura = temperatura;
    }

    public int getNumeroKit() {
        return numeroKit;
    }

    public void setNumeroKit(int numeroKit) {
        this.numeroKit = numeroKit;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }



    @Override
    public String toString() {
        return "Test{" +
                "numeroKit=" + numeroKit +
                ", persona=" + persona +
                ", temperatura=" + temperatura +
                '}';
    }
}
