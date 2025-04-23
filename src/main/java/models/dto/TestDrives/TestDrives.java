package models.dto.TestDrives;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TestDrives {
    private int id;
    private int kid;
    private int vid;
    private String statusi;
    private String feedback;
    private int duration;
    private String location;

    public TestDrives(int id, int kid, int vid, String statusi, String feedback, int duration, String location) {
        this.id = id;
        this.kid = kid;
        this.vid = vid;
        this.statusi = statusi;
        this.feedback = feedback;
        this.duration = duration;
        this.location = location;
    }

    public static TestDrives getInstance(ResultSet resultSet)throws SQLException{
        int id=resultSet.getInt("id");
        int kid=resultSet.getInt("kid");
        int vid=resultSet.getInt("vid");
        String statusi=resultSet.getString("statusi");
        String feedback=resultSet.getString("feedback");
        int duration=resultSet.getInt("kohezgjatja");
        String location=resultSet.getString("location");
        return new TestDrives(id,kid,vid,statusi,feedback,duration,location);
    }

    public int getId() {
        return id;
    }

    public int getKid() {
        return kid;
    }

    public int getVid() {
        return vid;
    }

    public String getStatusi() {
        return statusi;
    }

    public String getFeedback() {
        return feedback;
    }

    public int getDuration() {
        return duration;
    }

    public String getLocation() {
        return location;
    }
}
