package com.cs460.swipe;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

/**
 * Main activity class that runs the program
 */
public class MainActivity extends AppCompatActivity {

    private int currentID = 0;

    /**
     * On creation of this app (instantiation) run this code
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    final ViewPager2 videoViewPager = findViewById(R.id.videoViewPager);

    List<VideoItem> videoItems = new ArrayList<>();
    addNewVideoToVideoList("https://firebasestorage.googleapis.com/v0/b/test-project-68c46.appspot.com/o/SampleVideo_1280x720_10mb.mp4?alt=media&token=8c76ca10-9e2b-49ee-9c50-f2cb3a076154", "Celebration!", "It's my birthday! just turned 102!", videoItems);
    addNewVideoToVideoList("https://firebasestorage.googleapis.com/v0/b/test-project-68c46.appspot.com/o/sample-4.mp4?alt=media&token=916fadbe-bf1d-459b-8fba-d598b68dae7e", "Flowers!", "Look at these pretty flowers I'm watering! I'm so cool!", videoItems);
    addNewVideoToVideoList("https://firebasestorage.googleapis.com/v0/b/test-project-68c46.appspot.com/o/12353436_1920_1080_60fps.mp4?alt=media&token=19dcce29-bf27-470f-a66d-ff99d3c8eb84", "Cool bird I saw!", "Look at this cool bird I saw!!!", videoItems);

    videoViewPager.setAdapter(new VideoAdapter(videoItems));
    }

    /**
     * Simple method to generate a new video id. Uses an int in this class and adds one on each call
     * @return New video ID
     */
    private int getNewVideoID() {
        return currentID++;
    }

    /**
     * Creates a new VideoItem and adds it to the given VideoItems List
     * @param url URL of the new video
     * @param title Title of the new video
     * @param desc Description of the new video
     * @param list The list that this video will be added to
     */
    private void addNewVideoToVideoList(String url, String title, String desc, List<VideoItem> list) {
        VideoItem newItem = new VideoItem(url, title, desc, getNewVideoID());
        list.add(newItem);
    }
}

