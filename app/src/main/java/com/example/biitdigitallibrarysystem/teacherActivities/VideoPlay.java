package com.example.biitdigitallibrarysystem.teacherActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ActivityChooserView;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.biitdigitallibrarysystem.R;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;

public class VideoPlay extends AppCompatActivity {

    VideoView videoView;
    private SimpleExoPlayer player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.video_play);
//        videoView=findViewById(R.id.videoView);

//        MediaController mediaController = new MediaController(this);
//        mediaController.setAnchorView(videoView);
//        videoView.setMediaController(mediaController);
//        videoView.setVideoURI(Uri.parse("https://www.youtube.com/watch?v=kp-oGImpO_c&ab_channel=WsCubeTech"));
//                videoView.start();

        player = new SimpleExoPlayer.Builder(this).build();

        // Bind the player to the view
        PlayerView playerView = findViewById(R.id.video_player_view);
        playerView.setPlayer(player);

        // Create a MediaSource representing the media to be played
        MediaSource mediaSource = new ProgressiveMediaSource.Factory(
                new DefaultDataSourceFactory(this, "exoplayer-example")
        ).createMediaSource(Uri.parse("https://www.youtube.com/watch?v=kp-oGImpO_c&ab_channel=WsCubeTech"));

        // Prepare the player with the source
        player.setMediaSource(mediaSource);
        player.prepare();

    }
}
