package models.dto.Shitjet;

public class UpdateShitjeDto {
    private int shitjet_id;
    private int vetura_id;
    private int punetor_id;
    private String data_shitjes;
    private double cmimi_final;

    public UpdateShitjeDto(){}

    public int getShitjet_id(){ return shitjet_id; }
    public void setShitjet_id(int shitjet_id){ this.shitjet_id = shitjet_id; }

    public int getVetura_id(){ return vetura_id; }
    public void setVetura_id(int vetura_id){ this.vetura_id = vetura_id; }

    public int getPunetor_id(){ return punetor_id; }
    public void setPunetor_id(int punetor_id){ this.punetor_id = punetor_id; }

    public String getData_shitjes(){ return data_shitjes; }
    public void setData_shitjes( String data_shitjes){ this.data_shitjes = data_shitjes; }

    public double getCmimi_final(){ return cmimi_final; }
    public void setCmimi_final( double cmimi_final){ this.cmimi_final = cmimi_final; }

}
