import java.io.Serializable;

public class Urgente implements Serializable {
    private int numTest;
    private String barrio;

    public Urgente(int numTest, String barrio) {
        this.numTest = numTest;
        this.barrio = barrio;
    }

    public int getNumTest() {
        return numTest;
    }

    public void setNumTest(int numTest) {
        this.numTest = numTest;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    @Override
    public String toString() {
        return "Urgente{" +
                "numTest=" + numTest +
                ", barrio='" + barrio + '\'' +
                '}';
    }
}
