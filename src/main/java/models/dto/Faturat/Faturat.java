package models.dto.Faturat;

import java.sql.ResultSet;
import java.sql.SQLException;

    public class Faturat {

        private int id;
        private int shitjeId;
        private String dataFatures;
        private Double shumaTotale;
        private String llojiPageses;

        private Faturat(int id, int shitjeId, String dataFatures, Double shumaTotale, String llojiPageses) {
            this.id = id;
            this.shitjeId = shitjeId;
            this.dataFatures = dataFatures;
            this.shumaTotale = shumaTotale;
            this.llojiPageses = llojiPageses;
        }

        public static Faturat getInstance(ResultSet resultSet) throws SQLException {
            int id = resultSet.getInt("id");
            int shitjeId = resultSet.getInt("shitje_id");
            String dataFatures = resultSet.getString("dataFatures");
            double shumaTotale = resultSet.getDouble("shumaTotale");
            String llojiPageses = resultSet.getString("llojiPageses");

            return new Faturat(id, shitjeId, dataFatures, shumaTotale, llojiPageses);
        }

        public int getId() {
            return id;
        }

        public int getShitjeId() {
            return shitjeId;
        }

        public String getDataFatures() {
            return dataFatures;
        }

        public Double getShumaTotale() {
            return shumaTotale;
        }

        public String getLlojiPageses() {
            return llojiPageses;
        }
    }

