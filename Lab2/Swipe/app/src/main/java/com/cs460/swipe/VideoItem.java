package com.cs460.swipe;

/**
 * Video item class used to represent a new video item to be displayed in the app
 */
public class VideoItem {
    public String videoURL, videoTitle, videoDescription;
    public int videoID;

    /**
     * Constructor to make creating new videoItems easier
     * @param videoURL URL of the new video
     * @param videoTitle Title of the new video
     * @param videoDescription Description of the new video
     * @param videoID ID of the new video (this is generated in the main activity class)
     */
    public VideoItem(String videoURL, String videoTitle, String videoDescription, int videoID) {
        this.videoURL = videoURL;
        this.videoTitle = videoTitle;
        this.videoDescription = videoDescription;
        this.videoID = videoID;
    }
}
