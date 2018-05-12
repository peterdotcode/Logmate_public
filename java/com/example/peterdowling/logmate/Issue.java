package com.example.peterdowling.logmate;

/**
 * Created by peterdowling on 14/04/2018.
 */
public class Issue {
    private String issueID;
    private String title;
    private String des;
    private String priority;
    private String timeDate;
    private String createdBy;
    private String assignee;
    private String status;
    private String longitude;
    private String latitude;


    /**
     * Instantiates a new Issue.
     */
    public Issue() {

    }

    /**
     * Instantiates a new Issue.
     *
     * @param issueID   the issue id
     * @param title     the title
     * @param des       the des
     * @param priority  the priority
     * @param timeDate  the time date
     * @param createdBy the created by
     * @param assignee  the assignee
     * @param status    the status
     * @param longitude the longitude
     * @param latitude  the latitude
     */
    public Issue(String issueID, String title, String des, String priority, String timeDate, String createdBy,
                 String assignee, String status, String longitude, String latitude) {
        this.issueID = issueID;
        this.title = title;
        this.des = des;
        this.priority = priority;
        this.timeDate = timeDate;
        this.createdBy = createdBy;
        this.assignee = assignee;
        this.status = status;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    /**
     * Gets issue id.
     *
     * @return the issue id
     */
    public String getIssueID() {
        return issueID;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets des.
     *
     * @return the des
     */
    public String getDes() {
        return des;
    }

    /**
     * Gets priority.
     *
     * @return the priority
     */
    public String getPriority() {
        return priority;
    }

    /**
     * Gets by.
     *
     * @return the by
     */
    public String getcreatedBy() {
        return createdBy;
    }


    /**
     * Gets time date.
     *
     * @return the time date
     */
    public String getTimeDate() {
        return timeDate;
    }

    /**
     * Sets issue id.
     *
     * @param issueID the issue id
     */
    public void setIssueID(String issueID) {
        this.issueID = issueID;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Sets des.
     *
     * @param des the des
     */
    public void setDes(String des) {
        this.des = des;
    }

    /**
     * Sets priority.
     *
     * @param priority the priority
     */
    public void setPriority(String priority) {
        this.priority = priority;
    }

    /**
     * Sets time date.
     *
     * @param timeDate the time date
     */
    public void setTimeDate(String timeDate) {
        this.timeDate = timeDate;
    }

    /**
     * Sets by.
     *
     * @param createdBy the created by
     */
    public void setcreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }


    /**
     * Gets assignee.
     *
     * @return the assignee
     */
    public String getAssignee() {
        return assignee;
    }

    /**
     * Sets assignee.
     *
     * @param assignee the assignee
     */
    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets longitude.
     *
     * @return the longitude
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * Sets longitude.
     *
     * @param longitude the longitude
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     * Gets latitude.
     *
     * @return the latitude
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * Sets latitude.
     *
     * @param latitude the latitude
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return title + "\n" + des;
    }


}


