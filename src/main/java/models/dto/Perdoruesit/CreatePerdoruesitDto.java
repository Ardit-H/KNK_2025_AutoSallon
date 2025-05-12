package models.dto.Perdoruesit;

public class CreatePerdoruesitDto {
    private String emri;
    private String mbiemri;
    private String email;
    private String nrtelefonit;
    private String adresa;
    private String fjalekalimi;

    public CreatePerdoruesitDto(String emri, String mbiemri, String email, String nrtelefonit, String adresa, String fjalekalimi) {
        this.emri = emri;
        this.mbiemri = mbiemri;
        this.email = email;
        this.nrtelefonit = nrtelefonit;
        this.adresa = adresa;
        this.fjalekalimi = fjalekalimi;
    }

    public void setEmri(String emri){
        this.emri = emri;
    }

    public String getEmri(){
        return emri;
    }

    public void setNrtelefonit(String nrtelefonit) {
        this.nrtelefonit = nrtelefonit;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public void setMbiemri(String mbiemri) {
        this.mbiemri = mbiemri;
    }

    public String getMbiemri() {
        return mbiemri;
    }

    public String getNrtelefonit() {
        return nrtelefonit;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return email;
    }

    public void setFjalekalimi(String fjalekalimi){
        this.fjalekalimi = fjalekalimi;
    }

    public String getFjalekalimi(){
        return fjalekalimi;
    }

}
