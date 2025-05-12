package models.dto.StatistikatEShitjeve;

public class UpdateStatistikatEShitjeveDto {
    private int id;
    private String muaji;
    private Double fitimi;
    private Double shpenzimet;
    private Double totali_shitjeve;

    public UpdateStatistikatEShitjeveDto(int selectedId){}

    public int getId(){ return id; }

    public void setId(int id){ this.id = id; }

    public String getMuaji() { return muaji; }

    public void setMuaji(String muaji) { this.muaji = muaji; }

    public Double getFitimi(){ return fitimi; }

    public void setFitimi(Double fitimi){ this.fitimi = fitimi; }

    public Double getShpenzimet(){ return shpenzimet; }

    public void setShpenzimet(Double shpenzimet){ this.shpenzimet = shpenzimet; }

    public Double getTotaliShitjeve(){ return totali_shitjeve; }

    public void setTotaliShitjeve(Double totali_shitjeve){ this.totali_shitjeve = totali_shitjeve; }
}
