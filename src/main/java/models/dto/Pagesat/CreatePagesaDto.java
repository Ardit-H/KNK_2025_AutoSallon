package models.dto.Pagesat;

public class CreatePagesaDto {
    private int porosiaId;
    private String metodaPageses;
    private double shuma;
    private String dataPageses;

    public CreatePagesaDto(int porosiaId, String metodaPageses, double shuma, String dataPageses) {
        this.porosiaId = porosiaId;
        this.metodaPageses = metodaPageses;
        this.shuma = shuma;
        this.dataPageses = dataPageses;
    }

    public int getPorosiaId() {
        return porosiaId;
    }

    public String getMetodaPageses() {
        return metodaPageses;
    }

    public String getDataPageses() {
        return dataPageses;
    }

    public double getShuma() {
        return shuma;
    }

    public void setPorosiaId(int porosiaId) {
        this.porosiaId = porosiaId;
    }

    public void setMetodaPageses(String metodaPageses) {
        this.metodaPageses = metodaPageses;
    }

    public void setShuma(double shuma) {
        this.shuma = shuma;
    }

    public void setDataPageses(String dataPageses) {
        this.dataPageses = dataPageses;
    }
}
