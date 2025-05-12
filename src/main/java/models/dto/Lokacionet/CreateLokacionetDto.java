package models.dto.Lokacionet;

public class CreateLokacionetDto {
    private String emri_lokacionit;
    private String adresa;
    private String qyteti;
    private String nrtelefonit;

    public CreateLokacionetDto( String emri_lokacionit, String adresa, String qyteti, String nrtelefonit){
        this.emri_lokacionit = emri_lokacionit;
        this.adresa = adresa;
        this.qyteti = qyteti;
        this.nrtelefonit = nrtelefonit;
    }


    public String getEmri_lokacionit(){ return emri_lokacionit; }

    public String getAdresa(){ return adresa; }

    public String getQyteti(){ return qyteti; }

    public String getNrtelefonit(){ return nrtelefonit; }



    public void setEmri_lokacionit(String emri_lokacionit){ this.emri_lokacionit = emri_lokacionit; }

    public void setAdresa(String adresa){ this.adresa = adresa; }

    public void setQyteti(String qyteti){ this.qyteti = qyteti; }

    public void setNrtelefonit(String nrtelefonit){ this.nrtelefonit = nrtelefonit; }



}
