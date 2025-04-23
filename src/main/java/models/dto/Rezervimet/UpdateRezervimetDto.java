package models.dto.Rezervimet;
import java.sql.SQLException;
import java.sql.ResultSet;

public class UpdateRezervimetDto {
    private int rezervimiId;
    private Integer klientiId;
    private Integer veturaId;
    private String dataRezervimit;
    private String statusi;

    public void setRezervimiId(int rezervimiId){
        this.rezervimiId = rezervimiId;
    }

    public int getRezervimiId(){
        return rezervimiId;
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

    public void setDataRezervimet(String dataRezervimit){
        this.dataRezervimit = dataRezervimit;
    }

    public String getDataRezervimit(){
        return  dataRezervimit;
    }

    public void setStatusi(String statusi){
        this.statusi = statusi;
    }

    public String getStatusi(){
        return statusi;
    }

    public UpdateRezervimetDto(){

    };
}
