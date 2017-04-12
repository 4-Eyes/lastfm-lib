package com.last.testapplication.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.last.fm.api.Lfm;
import com.last.fm.api.LfmError;
import com.last.fm.api.LfmParameters;
import com.last.fm.api.LfmRequest;
import com.last.fm.api.ScrobbleParameters;
import com.last.fm.api.Session;
import com.last.fm.api.LfmApi;
import com.last.fm.api.methods.LfmApiTrack;
import com.last.testapplication.LoginActivity;
import com.last.testapplication.R;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

public class MainFragment extends Fragment {

    private static final int LAYOUT = R.layout.main_fragment;
    private View mainView;

    private ImageView profileImage;
    private String imageUrl;
    private TextView mUserName;
    private Button mLogoutButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView = inflater.inflate(LAYOUT, container, false);

        ScrobbleParameters params = new ScrobbleParameters();
        params.put("track","track1","track2","track3");
        params.put("artist","artist1","artist2","artist3");

        String timestamp = String.valueOf(System.currentTimeMillis()/1000);
        params.put("timestamp",timestamp,timestamp,timestamp);

        LfmRequest request = LfmApi.track().scrobble(params);

        request.executeWithListener(new LfmRequest.LfmRequestListener() {
            @Override
            public void onComplete(JSONObject response) {

            }

            @Override
            public void onError(LfmError error) {

            }
        });

        mLogoutButton = (Button) mainView.findViewById(R.id.logout_button);
        profileImage = (ImageView) mainView.findViewById(R.id.profile_img);
        mUserName = (TextView) mainView.findViewById(R.id.username_tv);
        mUserName.setText(Session.username);

        mLogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Lfm.logout();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        LfmRequest lfmRequest = LfmApi.user().getInfo();
        lfmRequest.executeWithListener(new LfmRequest.LfmRequestListener() {
            @Override
            public void onComplete(JSONObject response) {
                imageUrl = response
                        .optJSONObject("user")
                        .optJSONArray("image")
                        .optJSONObject(2)
                        .optString("#text");

                Picasso.with(getActivity()).load(imageUrl).fit().into(profileImage);
                unloveTrack();
            }

            @Override
            public void onError(LfmError error) {

            }
        });

        return mainView;
    }

    private void unloveTrack() {
        LfmApiTrack apiTrack = new LfmApiTrack();
        LfmParameters params = new LfmParameters();
        params.put("artist", "sgsgsgljwnwnng");
        apiTrack.unlove(params).executeWithListener(new LfmRequest.LfmRequestListener() {
            @Override
            public void onComplete(JSONObject response) {

            }

            @Override
            public void onError(LfmError error) {

            }
        });
    }

}
