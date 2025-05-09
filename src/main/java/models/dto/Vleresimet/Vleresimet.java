package models.dto.Vleresimet;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Vleresimet {
    private int vleresimiId;
    private int perdoruesiId;
    private int veturaId;
    private int vleresimi;
    private String komenti;
    private String dataVleresimit;

    private Vleresimet(int vleresimiId, int perdoruesiId, int veturaId, int vleresimi, String komenti, String dataVleresimit) {
        this.vleresimiId = vleresimiId;
        this.perdoruesiId = perdoruesiId;
        this.veturaId = veturaId;
        this.vleresimi = vleresimi;
        this.komenti = komenti;
        this.dataVleresimit=dataVleresimit;
    }
    public static Vleresimet getInstance(ResultSet resultset) throws SQLException {
        int vleresimiId = resultset.getInt("id");
        int perdoruesiId =resultset.getInt("perdoruesi_id");
        int veturaId=resultset.getInt("vetura_id");
        int vleresimi=resultset.getInt("vleresimi");
        String komenti=resultset.getString("komenti");
        String dataVleresimit=resultset.getString("data_vleresimit");
        return new Vleresimet(vleresimiId,perdoruesiId,veturaId,vleresimi,komenti,dataVleresimit);
    }

    public int getVleresimiId() {
        return vleresimiId;
    }

    public int getPerdoruesiId() {
        return perdoruesiId;
    }

    public int getVeturaId() {
        return veturaId;
    }

    public int getVleresimi() {
        return vleresimi;
    }

    public String getKomenti() {
        return komenti;
    }

    public String getDataVleresimit() {
        return dataVleresimit;
    }
}
