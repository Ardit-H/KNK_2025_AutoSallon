package models.dto.TestDrives;

public class UpdateTestDrivesDto {
    private int id;
    private String statusi;
    private String feedback;
    private int duration;
    private String location;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatusi() {
        return statusi;
    }

    public void setStatusi(String statusi) {
        this.statusi = statusi;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}

