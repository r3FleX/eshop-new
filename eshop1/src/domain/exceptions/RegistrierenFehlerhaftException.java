package domain.exceptions;

public class RegistrierenFehlerhaftException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public RegistrierenFehlerhaftException(String name, String passwort, String strasse, int plz, String ort) {
		super("Bitte f�llen Sie alle Felder aus!");
	}
}