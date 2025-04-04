package models.dto.Porosite;

public class CreatePorosiaDto {
    private int kid;
    private int veturaId;
    private double cmimiOfruar;
    private String statusiPorosise;

    public CreatePorosiaDto(int kid, int veturaId, double cmimiOfruar, String statusiPorosise) {
        this.kid = kid;
        this.veturaId = veturaId;
        this.cmimiOfruar = cmimiOfruar;
        this.statusiPorosise = statusiPorosise;
    }

    public int getKid() {
        return kid;
    }

    public int getVeturaId() {
        return veturaId;
    }

    public double getCmimiOfruar() {
        return cmimiOfruar;
    }

    public String getStatusiPorosise() {
        return statusiPorosise;
    }

    public void setKid(int kid) {
        this.kid = kid;
    }

    public void setVeturaId(int veturaId) {
        this.veturaId = veturaId;
    }

    public void setCmimiOfruar(double cmimiOfruar) {
        this.cmimiOfruar = cmimiOfruar;
    }

    public void setStatusiPorosise(String statusiPorosise) {
        this.statusiPorosise = statusiPorosise;
    }
}
