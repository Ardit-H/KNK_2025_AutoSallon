package models.dto.Shitjet;

public class CreateShitjetDto {
    private int kid ;
    private int vetura_id;
    private int punetor_id;
    private String data_shitjes;
    private double cmimi_final;

    public CreateShitjetDto( int kid, int vetura_id, int punetor_id, double cmimi_final){
        this.kid = kid;
        this.vetura_id = vetura_id;
        this.punetor_id = punetor_id;
        this.cmimi_final = cmimi_final;
    }

    public int getKid() { return kid; }

    public int getVetura_id() { return vetura_id; }

    public int getPunetor_id() { return punetor_id; }

    public double getCmimi_final() { return cmimi_final; }

    public String getData_shitjes() { return data_shitjes; }


    public void setKid(int kid) { this.kid = kid; }

    public void setVetura_id(int vetura_id) { this.vetura_id = vetura_id; }

    public void setPunetor_id(int punetor_id) { this.punetor_id = punetor_id; }

    public void setCmimi_final(double cmimi_final) { this.cmimi_final = cmimi_final; }

    public void setData_shitjes(String data_shitjes) { this.data_shitjes = data_shitjes;}
}
