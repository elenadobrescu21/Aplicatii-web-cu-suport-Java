package hello;

public class Persoana {

    
    private final String nume;
	private final String prenume;

    public Persoana( String nume, String prenume) {
        this.nume = nume;
        this.prenume = prenume;
    }

    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }
}
