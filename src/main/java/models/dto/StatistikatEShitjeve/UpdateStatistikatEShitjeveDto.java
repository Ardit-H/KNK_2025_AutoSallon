package models.dto.StatistikatEShitjeve;

public class UpdateStatistikatEShitjeveDto {
    private int id;

    private Double fitimi;
    private Double shpenzimet;
    private Double totali_shitjeve;

    public UpdateStatistikatEShitjeveDto(){}

    public int getId(){ return id; }

    public void setId(int id){ this.id = id; }

    public Double getFitimi(){ return fitimi; }

    public void setFitimi(Double fitimi){ this.fitimi = fitimi; }

    public Double getShpenzimet(){ return shpenzimet; }

    public void setShpenzimet(Double shpenzimet){ this.shpenzimet = shpenzimet; }

    public Double getTotali_shitjeve(){ return totali_shitjeve; }

    public void setTotali_shitjeve(Double totali_shitjeve){ this.totali_shitjeve = totali_shitjeve; }
}
