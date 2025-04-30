package models.dto.Ofertat;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Oferta {
    private int ofertaId;
    private int veturaId;
    private double zbritja;
    private double cmimiFinal;
    private String dataFillimit;
    private String dataMbarimit;

    private Oferta(int ofertaId, int veturaId, double zbritja, double cmimiFinal, String dataFillimit, String dataMbarimit) {
        this.ofertaId = ofertaId;
        this.veturaId = veturaId;
        this.zbritja = zbritja;
        this.cmimiFinal = cmimiFinal;
        this.dataFillimit = dataFillimit;
        this.dataMbarimit = dataMbarimit;
    }

    public static Oferta getInstance(ResultSet resultSet) throws SQLException {
        int ofertaId = resultSet.getInt("id");
        int veturaId = resultSet.getInt("veturaId");
        double zbritja = resultSet.getDouble("zbritja");
        double cmimiFinal = resultSet.getDouble("cmimiFinal");
        String dataFillimit = resultSet.getString("dataFillimit");
        String dataMbarimit = resultSet.getString("dataMbarimit");
        return new Oferta(ofertaId, veturaId, zbritja, cmimiFinal, dataFillimit, dataMbarimit);
    }

    public int getOfertaId() {
        return ofertaId;
    }

    public int getVeturaId() {
        return veturaId;
    }

    public double getZbritja() {
        return zbritja;
    }

    public double getCmimiFinal() {
        return cmimiFinal;
    }

    public String getDataFillimit() {
        return dataFillimit;
    }

    public String getDataMbarimit() {
        return dataMbarimit;
    }
}
