package com.example.djnig.tantamayo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;


public class VideoFragment extends Fragment {

    VideoView video;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vx= inflater.inflate(R.layout.fragment_video, container, false);
        video= (VideoView)vx.findViewById(R.id.videoView);
        Uri uri =Uri.parse("http://techslides.com/demos/sample-videos/small.mp4");
        String path = "android.resource://" + getActivity().getPackageName() + "/" + R.raw.tantamayo;
        /*String path = "android.resource://" + getActivity().getPackageName() + "/" + R.raw.howtopray;
        view.setVideoURI(Uri.parse(path));*/
        video.setMediaController(new MediaController(getActivity()));
       // video.setVideoURI(uri);
        video.setVideoURI(Uri.parse(path));
        video.requestFocus();
        video.start();
        return vx;
    }
}
