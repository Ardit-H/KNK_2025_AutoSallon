package models.dto.Perdoruesit;

public class CreatePerdoruesitDto {
    private String emri;
    private String mbiemri;
    private String email;
    private String nrtelefonit;
    private String adresa;
    private String fjalekalimi;
    private String roli;

    public CreatePerdoruesitDto() {
        this.roli = "User"; // Default roli
    }

    public CreatePerdoruesitDto(String emri, String mbiemri, String email, String nrtelefonit, String adresa, String fjalekalimi) {
        this.emri = emri;
        this.mbiemri = mbiemri;
        this.email = email;
        this.nrtelefonit = nrtelefonit;
        this.adresa = adresa;
        this.fjalekalimi = fjalekalimi;
        this.roli = "User"; // Default roli
    }

    public String getEmri() {
        return emri;
    }

    public void setEmri(String emri) {
        this.emri = emri;
    }

    public String getMbiemri() {
        return mbiemri;
    }

    public void setMbiemri(String mbiemri) {
        this.mbiemri = mbiemri;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNrtelefonit() {
        return nrtelefonit;
    }

    public void setNrtelefonit(String nrtelefonit) {
        this.nrtelefonit = nrtelefonit;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getFjalekalimi() {
        return fjalekalimi;
    }

    public void setFjalekalimi(String fjalekalimi) {
        this.fjalekalimi = fjalekalimi;
    }

    public String getRoli() {
        return roli;
    }

    public void setRoli(String roli) {
        this.roli = roli;
    }
}
