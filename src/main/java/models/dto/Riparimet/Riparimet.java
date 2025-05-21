package models.dto.Riparimet;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Riparimet {

    private int id;
    private int veturaId;
    private int sherbimiId;
    private String statusi;
    private Double kostoRiparimit;

    private Riparimet(int id, int veturaId, int sherbimiId, String statusi, Double kostoRiparimit) {
        this.id = id;
        this.veturaId = veturaId;
        this.sherbimiId = sherbimiId;
        this.statusi = statusi;
        this.kostoRiparimit = kostoRiparimit;
    }

    public static Riparimet getInstance(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        int veturaId = resultSet.getInt("veturaId");
        int sherbimiId = resultSet.getInt("sherbimiId");
        String statusi = resultSet.getString("statusi");
        Double kostoRiparimit = resultSet.getDouble("kostoRiparimit");
        return new Riparimet(id, veturaId, sherbimiId, statusi, kostoRiparimit);
    }

    public int getId() {
        return id;
    }

    public int getVeturaId() {
        return veturaId;
    }

    public int getSherbimiId() {
        return sherbimiId;
    }

    public String getStatusi() {
        return statusi;
    }

    public Double getKostoRiparimit() {
        return kostoRiparimit;
    }

}
