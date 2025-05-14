package models.dto.TestDrives;

public class CreateTestDrivesDto {
    private int vid;
    private int kid;
    private String statusi;
    private String feedback;
    private int duration;
    private String location;

    public CreateTestDrivesDto(int kid, int vid, String statusi, String feedback, int duration, String location) {
        this.kid = kid;
        this.vid = vid;
        this.statusi = statusi;
        this.feedback = feedback;
        this.duration = duration;
        this.location = location;
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

    public int getVid() {
        return vid;
    }

    public void setVid(int vid) {
        this.vid = vid;
    }

    public int getKid() {
        return kid;
    }

    public void setKid(int kid) {
        this.kid = kid;
    }
}
