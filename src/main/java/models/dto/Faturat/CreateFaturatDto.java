package models.dto.Faturat;

public class CreateFaturatDto {


        private int shitjeId;
        private String dataFatures;
        private double shumaTotale;
        private String llojiPageses;

        public CreateFaturatDto(int shitjeId, String dataFatures, double shumaTotale, String llojiPageses) {
            this.shitjeId = shitjeId;
            this.dataFatures = dataFatures;
            this.shumaTotale = shumaTotale;
            this.llojiPageses = llojiPageses;
        }

        public int getShitjeId() {
            return shitjeId;
        }

        public void setShitjeId(int shitjeId) {
            this.shitjeId = shitjeId;
        }

        public String getDataFatures() {
            return dataFatures;
        }

        public void setDataFatures(String dataFatures) {
            this.dataFatures = dataFatures;
        }

        public double getShumaTotale() {
            return shumaTotale;
        }

        public void setShumaTotale(double shumaTotale) {
            this.shumaTotale = shumaTotale;
        }

        public String getLlojiPageses() {
            return llojiPageses;
        }

        public void setLlojiPageses(String llojiPageses) {
            this.llojiPageses = llojiPageses;
        }
    }


