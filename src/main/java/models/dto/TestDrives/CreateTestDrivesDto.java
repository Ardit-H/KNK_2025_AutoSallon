package models.dto.TestDrives;

public class CreateTestDrivesDto {
    private int kid;
    private int vid;
    private String status;
    private String feedback;
    private int duration;

    public CreateTestDrivesDto(int kid, int vid, String status, String feedback, int duration) {
        this.kid = kid;
        this.vid = vid;
        this.status = status;
        this.feedback = feedback;
        this.duration = duration;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
