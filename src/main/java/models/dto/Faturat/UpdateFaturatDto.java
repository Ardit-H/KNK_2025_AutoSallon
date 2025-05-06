package models.dto.Faturat;


    public class UpdateFaturatDto {
        private int id;
        private String dataFatures;
        private double shumaTotale;
        private String llojiPageses;

        public UpdateFaturatDto() {}

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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


