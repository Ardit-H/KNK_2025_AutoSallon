package models.dto.Porosite;

public class CreatePorosiaDto {
    private int kid;
    private int vetura_id;
    private double cmimi_ofruar;
    private String statusi_porosise;

    public CreatePorosiaDto(int kid, int vetura_id, double cmimi_ofruar, String statusi_porosise) {
        this.kid = kid;
        this.vetura_id = vetura_id;
        this.cmimi_ofruar = cmimi_ofruar;
        this.statusi_porosise = statusi_porosise;
    }

    public int getKid() {
        return kid;
    }

    public int getVetura_id() {
        return vetura_id;
    }

    public double getCmimi_ofruar() {
        return cmimi_ofruar;
    }

    public String getStatusi_porosise() {
        return statusi_porosise;
    }

    public void setKid(int kid) {
        this.kid = kid;
    }

    public void setVetura_id(int vetura_id) {
        this.vetura_id = vetura_id;
    }

    public void setCmimi_ofruar(double cmimi_ofruar) {
        this.cmimi_ofruar = cmimi_ofruar;
    }

    public void setStatusi_porosise(String statusi_porosise) {
        this.statusi_porosise = statusi_porosise;
    }
}
