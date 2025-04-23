package models.dto.Rezervimet;

public class CreateRezervimetDto {
    private int klientiId;
    private int veturaId;
    private String dataRezervimit;
    private String statusi;

    public CreateRezervimetDto(int klientiId, int veturaId, String dataRezervimit, String statusi){
        this.klientiId = klientiId;
        this.veturaId = veturaId;
        this.dataRezervimit = dataRezervimit;
        this.statusi = statusi;
    }

    public void setKlientiId(int klientiId){
        this.klientiId = klientiId;
    }

    public int getKlientiId(){
        return klientiId;
    }

    public void setVeturaId(int veturaId){
        this.veturaId = veturaId;
    }

    public int getVeturaId(){
        return veturaId;
    }

    public void setDataRezervimit(String dataRezervimit){
        this.dataRezervimit = dataRezervimit;
    }

    public String getDataRezervimit(){
        return dataRezervimit;
    }

    public void setStatusi(String statusi){
        this.statusi = statusi;
    }

    public String getStatusi(){
        return statusi;
    }

}
