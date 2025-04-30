package models.dto.Porosite;

public class UpdatePorosiaDto {
    private int porosiaId;
    private double cmimiOfruar;
    private String statusiPorosise;

    public UpdatePorosiaDto() {}

    public int getPorosiaId() {
        return porosiaId;
    }

    public double getCmimiOfruar() {
        return cmimiOfruar;
    }

    public String getStatusiPorosise() {
        return statusiPorosise;
    }

    public void setPorosiaId(int porosiaId) {
        this.porosiaId = porosiaId;
    }

    public void setCmimiOfruar(double cmimiOfruar) {
        this.cmimiOfruar = cmimiOfruar;
    }

    public void setStatusiPorosise(String statusiPorosise) {
        this.statusiPorosise = statusiPorosise;
    }
}
