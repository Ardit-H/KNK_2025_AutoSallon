package models.dto.Porosite;

public class UpdatePorosiaDto {
    private int porosia_id;
    private double cmimi_ofruar;
    private String statusi_porosise;

    public UpdatePorosiaDto(int porosia_id, double cmimi_ofruar, String statusi_porosise) {
        this.porosia_id = porosia_id;
        this.cmimi_ofruar = cmimi_ofruar;
        this.statusi_porosise = statusi_porosise;
    }

    public int getPorosia_id() {
        return porosia_id;
    }

    public double getCmimi_ofruar() {
        return cmimi_ofruar;
    }

    public String getStatusi_porosise() {
        return statusi_porosise;
    }

    public void setCmimi_ofruar(double cmimi_ofruar) {
        this.cmimi_ofruar = cmimi_ofruar;
    }

    public void setStatusi_porosise(String statusi_porosise) {
        this.statusi_porosise = statusi_porosise;
    }
}
