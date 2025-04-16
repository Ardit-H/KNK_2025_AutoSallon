package models.dto.Garancia;

import models.dto.Klientet.UpdateKlientiDto;

public class UpdateGaranciaDto {
    private int gid;
    private String dataFillimit;
    private String dataMbarimit;

    public UpdateGaranciaDto(){}

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public String getDataFillimit() {
        return dataFillimit;
    }

    public void setDataFillimit(String dataFillimit) {
        this.dataFillimit = dataFillimit;
    }

    public String getDataMbarimit() {
        return dataMbarimit;
    }

    public void setDataMbarimit(String dataMbarimit) {
        this.dataMbarimit = dataMbarimit;
    }
}
