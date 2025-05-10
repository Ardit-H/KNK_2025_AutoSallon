package models.dto.Vleresimet;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateVleresimetDto {
    private int vleresimiId;
    private Integer perdoruesiId;
    private Integer veturaId;
    private Integer vleresimi;
    private String komenti;
    private String dataVleresimit;

    public int getVleresimiId() {
        return vleresimiId;
    }

    public void setVleresimiId(int vleresimiId) {
        this.vleresimiId = vleresimiId;
    }

    public Integer getPerdoruesiId() {
        return perdoruesiId;
    }

    public void setPerdoruesiId(Integer perdoruesiId) {
        this.perdoruesiId = perdoruesiId;
    }

    public Integer getVeturaId() {
        return veturaId;
    }

    public void setVeturaId(Integer veturaId) {
        this.veturaId = veturaId;
    }

    public Integer getVleresimi() {
        return vleresimi;
    }

    public void setVleresimi(Integer vleresimi) {
        this.vleresimi = vleresimi;
    }

    public String getKomenti() {
        return komenti;
    }

    public void setKomenti(String komenti) {
        this.komenti = komenti;
    }

    public String getDataVleresimit() {
        return dataVleresimit;
    }

    public void setDataVleresimit(String dataVleresimit) {
        this.dataVleresimit = dataVleresimit;
    }

    public UpdateVleresimetDto() {
    }

}

