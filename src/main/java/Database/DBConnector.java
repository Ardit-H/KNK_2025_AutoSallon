package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    private static final String DB_NAME="Projekti_KNK";
    private static final String DB_HOST="localhost";
    private static final String DB_USER="postgres";
    private static final String DB_PASSWORD="Grupi14KNK";
    private static final String DB_URL="jdbc:postgresql://"+ DB_HOST+"/"+DB_NAME;
    private static Connection connection=null;
    public static Connection getConnection(){
        if(connection==null){
            try{
                connection= DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return connection;
    }


}
