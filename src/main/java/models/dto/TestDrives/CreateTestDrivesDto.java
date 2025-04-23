package models.dto.TestDrives;

public class CreateTestDrivesDto {
    private String statusi;
    private String feedback;
    private int duration;
    private String location;

    public CreateTestDrivesDto(String statusi, String feedback, int duration, String location) {
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
}
