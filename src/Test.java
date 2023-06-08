public class Test {
    private String dni;
    private String barrio;
    private double temperatura;

    public Test(String dni, String barrio, double temperatura) {
        this.dni = dni;
        this.barrio = barrio;
        this.temperatura = temperatura;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public String getDni() {
        return dni;
    }

    public String getBarrio() {
        return barrio;
    }

    public double getTemperatura() {
        return temperatura;
    }


}