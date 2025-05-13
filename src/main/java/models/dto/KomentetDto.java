package models.dto;

import java.util.List;

public class KomentetDto{
    private List<String> komentetPozitive;
    private List<String> komentetNegative;

    public KomentetDto(List<String> komentetPozitive,List<String> komentetNegative) {
        this.komentetPozitive = komentetPozitive;
        this.komentetNegative = komentetNegative;
    }

    public List<String> getKomentetPozitive(){
        return komentetPozitive;
    }

    public List<String> getKomentetNegative(){
        return komentetNegative;
    }
}

