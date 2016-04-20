package valueobjects;

/**
 * Klasse zur Repr�sentation der Accounts.
 */

public class Account {

	// Attribute zur Beschreibung des Accounts

	protected int accountNr;
	protected String accountname;
	protected String accountpasswort;

	public Account(String name, String passwort, int accnummer) {
		this.accountname = name;
		this.accountpasswort = passwort;
		this.accountNr = accnummer;
	}

	//�berpr�fe ob der Account schon vorhanden ist
	
	public boolean equals(Object obj) {
		if (obj instanceof Account) {
			Account anderesAccount = (Account) obj;
			return this.accountname.equals(anderesAccount.accountname);
		}

		return false;
	}

	// Getter und Setter
	public int getAccountNr() {
		return accountNr;
	}


	public void setAccountNr(int accountNr) {
		this.accountNr = accountNr;
	}

	
	public String getName() {
		return accountname;
	}


	public void setName(String name) {
		this.accountname = name;
	}


	public String getPasswort() {
		return accountpasswort;
	}
	

	public void setPasswort(String passwort) {
		this.accountpasswort = passwort;
	}
}