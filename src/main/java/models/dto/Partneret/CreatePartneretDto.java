package models.dto.Partneret;

public class CreatePartneretDto {
    private String emriKompanise;
    private String llojiPartnerit;
    private String personKontakti;
    private String email;
    private String telefoni;
    private String adresa;

    public CreatePartneretDto(String emriKompanise, String llojiPartnerit, String personKontakti,
                              String email, String telefoni, String adresa) {
        this.emriKompanise = emriKompanise;
        this.llojiPartnerit = llojiPartnerit;
        this.personKontakti = personKontakti;
        this.email = email;
        this.telefoni = telefoni;
        this.adresa = adresa;
    }

    public String getEmriKompanise() {
        return emriKompanise;
    }

    public void setEmriKompanise(String emriKompanise) {
        this.emriKompanise = emriKompanise;
    }

    public String getLlojiPartnerit() {
        return llojiPartnerit;
    }

    public void setLlojiPartnerit(String llojiPartnerit) {
        this.llojiPartnerit = llojiPartnerit;
    }

    public String getPersonKontakti() {
        return personKontakti;
    }

    public void setPersonKontakti(String personKontakti) {
        this.personKontakti = personKontakti;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefoni() {
        return telefoni;
    }

    public void setTelefoni(String telefoni) {
        this.telefoni = telefoni;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }
}