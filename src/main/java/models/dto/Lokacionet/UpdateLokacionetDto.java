package models.dto.Lokacionet;

public class UpdateLokacionetDto {
    private int id;

    private String adresa;
    private String qyteti;
    private String nrtelefonit;

    public UpdateLokacionetDto(){}

    public int getId (){ return id; }

    public void setId( int id){ this.id = id; }

    public String getAdresa(){ return adresa; }

    public void setAdresa(String adresa){ this.adresa = adresa; }

    public String getQyteti(){ return qyteti; }

    public void setQyteti(String qyteti){ this.qyteti = qyteti; }

    public String getNrtelefonit(){ return nrtelefonit; }

    public void setNrtelefonit( String nrtelefonit){ this.nrtelefonit = nrtelefonit; }



}
