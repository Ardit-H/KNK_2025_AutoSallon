package models.dto.Rezervimet;
import java.sql.SQLException;
import java.sql.ResultSet;

public class UpdateRezervimetDto {
    private int id;
    private Integer klientiId;
    private Integer veturaId;
    private String dataRezervimit;
    private String statusi;

    public UpdateRezervimetDto() {}

    public void setRezervimiId(int id){
        this.id = id;
    }

    public int getRezervimiId(){
        return id;
    }

    public void setKlientiId(Integer klientiId){
        this.klientiId = klientiId;
    }

    public Integer getKlientiId(){
        return klientiId;
    }

    public void setVeturaId(Integer veturaId){
        this.veturaId = veturaId;
    }

    public Integer getVeturaId(){
        return veturaId;
    }

    public void setDataRezervimit(String dataRezervimit){
        this.dataRezervimit = dataRezervimit;
    }

    public String getDataRezervimit(){
        return dataRezervimit;
    }

    public void setStatusi(String statusi){
        this.statusi = statusi;
    }

    public String getStatusi(){
        return statusi;
    }
}
