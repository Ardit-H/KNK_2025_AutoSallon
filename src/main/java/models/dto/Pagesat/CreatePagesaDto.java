package models.dto.Pagesat;

public class CreatePagesaDto {
    private int pororsiaId;
    private String metodaPageses;
    private double shuma;
    private String dataPageses;

    public CreatePagesaDto(int pororsiaId, String metodaPageses, double shuma, String dataPageses) {
        this.pororsiaId = pororsiaId;
        this.metodaPageses = metodaPageses;
        this.shuma = shuma;
        this.dataPageses = dataPageses;
    }

    public int getPororsiaId() {
        return pororsiaId;
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

    public void setPororsiaId(int pororsiaId) {
        this.pororsiaId = pororsiaId;
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
