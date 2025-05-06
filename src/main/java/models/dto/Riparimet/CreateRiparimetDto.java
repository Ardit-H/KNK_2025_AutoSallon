package models.dto.Riparimet;

public class CreateRiparimetDto {
    private int veturaId;
    private int sherbimiId;
    private String statusi;
    private double kostoRiparimit;

    public CreateRiparimetDto(int veturaId, int sherbimiId, String statusi, double kostoRiparimit) {
        this.veturaId = veturaId;
        this.sherbimiId = sherbimiId;
        this.statusi = statusi;
        this.kostoRiparimit = kostoRiparimit;
    }

    public int getVeturaId() {
        return veturaId;
    }

    public void setVeturaId(int veturaId) {
        this.veturaId = veturaId;
    }

    public int getSherbimiId() {
        return sherbimiId;
    }

    public void setSherbimiId(int sherbimiId) {
        this.sherbimiId = sherbimiId;
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
