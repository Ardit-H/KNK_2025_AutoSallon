package models.dto.Pagesat;

public class UpdatePagesaDto {
    private int pagesaId;
    private String metodaPageses;
    private double shuma;
    private String dataPageses;

    public UpdatePagesaDto(int pagesaId, String metodaPageses, double shuma, String dataPageses) {
        this.pagesaId = pagesaId;
        this.metodaPageses = metodaPageses;
        this.shuma = shuma;
        this.dataPageses = dataPageses;
    }

    public int getPagesaId() {
        return pagesaId;
    }

    public String getMetodaPageses() {
        return metodaPageses;
    }

    public double getShuma() {
        return shuma;
    }

    public String getDataPageses() {
        return dataPageses;
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
