package models.dto.Klientet;

public class CreateKlientetDto {
    private String emri;
    private String mbiemri;
    private String email;
    private String nrtelefonit;
    private String adresa;
    private Integer perdoruesiId;

    public CreateKlientetDto(String emri,String mbiemri,String email,String nrtelefonit,String adresa,Integer perdoruesiId){
      this.emri=emri;
      this.mbiemri=mbiemri;
      this.email=email;
      this.nrtelefonit=nrtelefonit;
      this.adresa=adresa;
      this.perdoruesiId=perdoruesiId;
    }
    public Integer getPerdoruesiId(){
        return perdoruesiId;
    }

    public void setPerdoruesiId(Integer perdoruesiId){
        this.perdoruesiId = perdoruesiId;
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

    public void setEmail(String email) {
        this.email = email;
    }
}
