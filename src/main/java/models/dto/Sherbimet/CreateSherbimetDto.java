package models.dto.Sherbimet;

public class CreateSherbimetDto {
    private String emri;
    private String pershkrimi;
    private double çmimi;

    public CreateSherbimetDto(String emri, String pershkrimi, double çmimi) {
        this.emri = emri;
        this.pershkrimi = pershkrimi;
        this.çmimi = çmimi;
    }

    public String getEmri() {
        return emri;
    }

    public String getPershkrimi() {
        return pershkrimi;
    }

    public double getÇmimi() {
        return çmimi;
    }

    public void setEmri(String emri) {
        this.emri = emri;
    }

    public void setPershkrimi(String pershkrimi) {
        this.pershkrimi = pershkrimi;
    }

    public void setÇmimi(double çmimi) {
        this.çmimi = çmimi;
    }
}
