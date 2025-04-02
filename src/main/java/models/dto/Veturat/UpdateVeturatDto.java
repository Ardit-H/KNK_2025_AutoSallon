package models.dto.Veturat;

public class UpdateVeturatDto {
    private int vetura_id;
    private String ngjyra;
    private double cmimi;

    public UpdateVeturatDto(int vetura_id){
        this.vetura_id = vetura_id;
    }
    public int getVetura_id(){
        return vetura_id;
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

