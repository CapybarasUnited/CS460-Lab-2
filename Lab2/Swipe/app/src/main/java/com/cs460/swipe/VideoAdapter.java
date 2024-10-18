package com.cs460.swipe;

import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * VideoAdapter class. I'm honestly not sure what this does but it adds all the new video info and scales it to fit on the screen
 */
public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder>{


    private List<VideoItem> videoItems;

    /**
     * Constructor to set the given list of videos as this objects videoItems
     * @param videoItems
     */
    public VideoAdapter(List<VideoItem> videoItems) {
        this.videoItems = videoItems;
    }

    /**
     * Create a new VideoViewHolder with given parent
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return the new VideoViewHolder
     */
    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VideoViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_container_video, parent, false)
        );
    }

    /**
     * Set the video data to the video at the given position
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        holder.setVideoData(videoItems.get(position));
    }

    /**
     * Returns the number of items in videoItems
     * @return number of videos in videoItems
     */
    @Override
    public int getItemCount() {
        return videoItems.size();
    }

    /**
     * View holder used to hold videos on the screen
     */
    static class VideoViewHolder extends RecyclerView.ViewHolder {
        TextView textVideoTitle, textVideoDescription;
        VideoView videoView;
        ProgressBar progressBar;

        /**
         * Set all the fields to their respective elements
         * @param itemView The view
         */
        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            videoView = itemView.findViewById(R.id.videoView);
            textVideoTitle = itemView.findViewById(R.id.textVideoTitle);
            textVideoDescription = itemView.findViewById(R.id.textViewDescription);
            progressBar = itemView.findViewById(R.id.videoProgressBar);
        }

        /**
         * Set the video data in the view to the provided videoItems data
         * @param videoItem The video item whose information will be displayed
         */
        void setVideoData(VideoItem videoItem) {
            textVideoTitle.setText(videoItem.videoTitle + "         ID: " + String.valueOf(videoItem.videoID));
            textVideoDescription.setText(videoItem.videoDescription);
            videoView.setVideoPath(videoItem.videoURL);

            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

                /**
                 * When the media player is ready, remove the progress bar and play the video with the calculated ratio
                 * @param mediaPlayer the MediaPlayer that is ready for playback
                 */
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    progressBar.setVisibility(View.GONE);
                    mediaPlayer.start();

                    float videoRatio = mediaPlayer.getVideoWidth() / (float) mediaPlayer.getVideoHeight();
                    float screenRatio = videoView.getWidth()/(float) videoView.getHeight();
                    float scale = videoRatio/screenRatio;

                    if(scale >= 1f) {
                        videoView.setScaleX(scale);
                    }

                    else {
                        videoView.setScaleY(1f / scale);
                    }
                }
            });

            /**
             * When the video finishes playing, restart the video so it loops
             */
            videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();
                }
            });
        }
    }

}
