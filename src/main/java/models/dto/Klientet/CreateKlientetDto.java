package models.dto.Klientet;

public class CreateKlientetDto {
    private String emri;
    private String mbiemri;
    private String email;
    private String nrtelefonit;
    private String adresa;
    private String data_regjistrimit;

    public CreateKlientetDto(String emri,String mbiemri,String email,String nrtelefonit,String adresa,String data_regjistrimit){
      this.emri=emri;
      this.mbiemri=mbiemri;
      this.email=email;
      this.nrtelefonit=nrtelefonit;
      this.adresa=adresa;
      this.data_regjistrimit=data_regjistrimit;
    }

    public String getEmri() {
        return emri;
    }

    public String getMbiemri() {
        return mbiemri;
    }

    public String getEmail() {
        return email;
    }

    public String getNrtelefonit() {
        return nrtelefonit;
    }

    public String getAdresa() {
        return adresa;
    }

    public String getData_regjistrimit() {
        return data_regjistrimit;
    }

    public void setEmri(String emri) {
        this.emri = emri;
    }

    public void setMbiemri(String mbiemri) {
        this.mbiemri = mbiemri;
    }

    public void setNrtelefonit(String nrtelefonit) {
        this.nrtelefonit = nrtelefonit;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public void setData_regjistrimit(String data_regjistrimit) {
        this.data_regjistrimit = data_regjistrimit;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
