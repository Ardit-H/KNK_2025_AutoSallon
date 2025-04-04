package models.dto.Klientet;

public class UpdateKlientiDto {
    private String email;
    private String adresa;
    private int nrtelefonit;

    public String getEmail() {
        return email;
    }

    public String getAdresa() {
        return adresa;
    }

    public int getNrtelefonit() {
        return nrtelefonit;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public void setNrtelefonit(int nrtelefonit) {
        this.nrtelefonit = nrtelefonit;
    }
}
