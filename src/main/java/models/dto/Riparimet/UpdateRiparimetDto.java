package models.dto.Riparimet;

public class UpdateRiparimetDto {
    private int id;
    private String statusi;
    private double kostoRiparimit;

    public UpdateRiparimetDto() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatusi() {
        return statusi;
    }

    public void setStatusi(String statusi) {
        this.statusi = statusi;
    }

    public double getKostoRiparimit() {
        return kostoRiparimit;
    }

    public void setKostoRiparimit(double kostoRiparimit) {
        this.kostoRiparimit = kostoRiparimit;
    }
}
