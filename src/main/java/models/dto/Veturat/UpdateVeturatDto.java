package models.dto.Veturat;

public class UpdateVeturatDto {
    private int veturaid;
    private String ngjyra;
    private double cmimi;

    public UpdateVeturatDto(int veturaid){
        this.veturaid = veturaid;
    }
    public int getVeturaid(){
        return veturaid;
    }

    public String getNgjyra() {
        return ngjyra;
    }

    public void setNgjyra(String ngjyra) {
        this.ngjyra = ngjyra;
    }

    public double getCmimi() {
        return cmimi;
    }

    public void setCmimi(double cmimi) {
        this.cmimi = cmimi;
    }
}

