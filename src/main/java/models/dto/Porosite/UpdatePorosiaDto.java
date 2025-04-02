package models.dto.Porosite;

public class UpdatePorosiaDto {
    private int porosiaId;
    private double cmimiOfruar;
    private String statusiPorosise;

    public UpdatePorosiaDto(int porosiaId, double cmimiOfruar, String statusiPorosise) {
        this.porosiaId = porosiaId;
        this.cmimiOfruar = cmimiOfruar;
        this.statusiPorosise = statusiPorosise;
    }

    public int getPorosiaId() {
        return porosiaId;
    }

    public double getCmimiOfruar() {
        return cmimiOfruar;
    }

    public String getStatusiPorosise() {
        return statusiPorosise;
    }

    public void setCmimiOfruar(double cmimiOfruar) {
        this.cmimiOfruar = cmimiOfruar;
    }

    public void setStatusiPorosise(String statusiPorosise) {
        this.statusiPorosise = statusiPorosise;
    }
}
