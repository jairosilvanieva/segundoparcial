public class AislarExcepcion extends Exception {
    private Urgente urgente;

    public AislarExcepcion(Urgente urgente) {
        super("Persona con alta temperatura");
        this.urgente = urgente;
    }

    public Urgente getUrgente() {
        return urgente;
    }
}
