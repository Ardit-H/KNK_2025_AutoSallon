package models.dto.Rezervimet;

import java.sql.ResultSet;
import java.sql.SQLException;
public class Rezervimet {
    private int rezervimiId;
    private int klientiId;
    private int veturaId;
    private String dataRezervimit;
    private String statusi;

    public Rezervimet(int rezervimiId, int klientiId, int veturaId, String dataRezervimit, String statusi){
        this.rezervimiId = rezervimiId;
        this.klientiId = klientiId;
        this.veturaId = veturaId;
        this.dataRezervimit = dataRezervimit;
        this.statusi = statusi;
    }

    public static Rezervimet getInstance(ResultSet resultSet) throws SQLException{
        int rezervimiId = resultSet.getInt("id");
        int klientiId = resultSet.getInt("klienti_id");
        int veturaId = resultSet.getInt("vetura_id");
        String dataRezervimit = resultSet.getString("data_rezervimit");
        String statusi = resultSet.getString("statusi");
        return new Rezervimet(rezervimiId, klientiId, veturaId, dataRezervimit, statusi);
    }
    public int getRezervimiId(){
        return rezervimiId;
    }

    public int getKlientiId(){
        return klientiId;
    }

    public int getVeturaId(){
        return veturaId;
    }

    public String getDataRezervimit(){
        return dataRezervimit;
    }

    public String getStatusi(){
        return  statusi;
    }
}
