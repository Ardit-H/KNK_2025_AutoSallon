package models.dto.Veturat;

public class UpdateVeturatDto {
    private int vetura_id;

    public UpdateVeturatDto(int vetura_id){
        this.vetura_id = vetura_id;
    }
    public int getVetura_id(){
        return vetura_id;
    }
}
