package models.dto.Perdoruesit;

public class UpdatePerdoruesitDto {
    private int id;
    private String emri;
    private String mbimeri;
    private String email;
    private String nrtelefonit;
    private String adresa;
    private String fjalekalimi;
    private String roli;
    private String salt;
    private String passwordhash;

    public UpdatePerdoruesitDto() {
    }


    public int getId() { return id; }
    public String getEmri(){
        return emri;
    }
    public String getMbimeri(){
        return mbimeri;
    }
    public String getEmail() { return email; }
    public String getNrtelefonit() { return nrtelefonit; }
    public String getAdresa() { return adresa; }
    public String getFjalekalimi() { return fjalekalimi; }
    public String getRoli() { return roli; }
    public String getSalt() { return salt; }
    public String getPasswordhash() {
        return passwordhash;
    }

    public void setId(int id) { this.id = id; }
    public String setEmri(){
        return this.emri = emri;
    }
    public String setMbimeri(){
        return this.mbimeri = mbimeri;
    }
    public void setEmail(String email) { this.email = email; }
    public void setNrtelefonit(String nrtelefonit) { this.nrtelefonit = nrtelefonit; }
    public void setAdresa(String adresa) { this.adresa = adresa; }
    public void setFjalekalimi(String fjalekalimi) { this.fjalekalimi = fjalekalimi; }
    public void setRoli(String roli) { this.roli = roli; }
    public void setSalt(String salt) { this.salt = salt; }
    public void setPasswordhash(String passwordhash) {
        this.passwordhash = passwordhash;
    }
}