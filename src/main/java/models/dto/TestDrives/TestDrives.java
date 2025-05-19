package models.dto.TestDrives;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TestDrives {
    private int id;
    private int kid;
    private int vid;
    private String status;
    private String feedback;
    private int duration;

    public TestDrives(int id, int kid, int vid, String status, String feedback, int duration) {
        this.id = id;
        this.kid = kid;
        this.vid = vid;
        this.status = status;
        this.feedback = feedback;
        this.duration = duration;
    }

    public static TestDrives getInstance(ResultSet resultSet)throws SQLException{
        int id=resultSet.getInt("id");
        int kid=resultSet.getInt("kid");
        int vid=resultSet.getInt("vid");
        String status=resultSet.getString("status");
        String feedback=resultSet.getString("feedback");
        int duration=resultSet.getInt("duration");
        return new TestDrives(id,vid,kid,status,feedback,duration);
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

    public String getStatus() {
        return status;
    }

    public String getFeedback() {
        return feedback;
    }

    public int getDuration() {
        return duration;
    }

}
