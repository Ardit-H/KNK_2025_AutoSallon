package models.dto.Lokacionet;

public class CreateLokacionetDto {
    private int lokacioni_id;
    private String emri_lokacionit;
    private String adresa;
    private String qyteti;
    private String nrtelefonit;

    public CreateLokacionetDto(int lokacioni_id, String emri_lokacionit, String adresa, String qyteti, String nrtelefonit){
        this.lokacioni_id = lokacioni_id;
        this.emri_lokacionit = emri_lokacionit;
        this.adresa = adresa;
        this.qyteti = qyteti;
        this.nrtelefonit = nrtelefonit;
    }

    public int getLokacioni_id(){ return lokacioni_id; }

    public String getEmri_lokacionit(){ return emri_lokacionit; }

    public String getAdresa(){ return adresa; }

    public String getQyteti(){ return qyteti; }

    public String getNrtelefonit(){ return nrtelefonit; }


    public void setLokacioni_id(int lokacioni_id){ this.lokacioni_id = lokacioni_id; }

    public void setEmri_lokacionit(String emri_lokacionit){ this.emri_lokacionit = emri_lokacionit; }

    public void setAdresa(String adresa){ this.adresa = adresa; }

    public void setQyteti(String qyteti){ this.qyteti = qyteti; }

    public void setNrtelefonit(String nrtelefonit){ this.nrtelefonit = nrtelefonit; }



}
