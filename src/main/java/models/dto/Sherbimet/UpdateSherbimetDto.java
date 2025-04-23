package models.dto.Sherbimet;

public class UpdateSherbimetDto {
    private int id;
    private String emri;
    private String pershkrimi;
    private Double çmimi;

    public void setEmri(String emri) {
        this.emri = emri;
    }

    public void setPershkrimi(String pershkrimi) {
        this.pershkrimi = pershkrimi;
    }

    public void setÇmimi(Double çmimi) {
        this.çmimi = çmimi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmri() {
        return emri;
    }

    public String getPershkrimi() {
        return pershkrimi;
    }

    public Double getÇmimi() {
        return çmimi;
    }
}
