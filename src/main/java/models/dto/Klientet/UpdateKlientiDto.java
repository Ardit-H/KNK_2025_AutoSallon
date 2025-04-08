package models.dto.Klientet;

public class UpdateKlientiDto {
    private int id;
    private String email;
    private String adresa;
    private String nrtelefonit;

    public UpdateKlientiDto(){}

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }

    public String getAdresa() {
        return adresa;
    }

    public String  getNrtelefonit() {
        return nrtelefonit;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public void setNrtelefonit(String nrtelefonit) {
        this.nrtelefonit = nrtelefonit;
    }
}
