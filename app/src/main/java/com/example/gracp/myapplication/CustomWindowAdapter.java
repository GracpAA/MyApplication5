package com.example.gracp.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

/**
 * Created by gracp on 27.11.2017.
 */

public class CustomWindowAdapter implements GoogleMap.InfoWindowAdapter {

    private final View window;
    private Context context;

    public CustomWindowAdapter(Context context) {
        this.context = context;
        window = LayoutInflater.from(context).inflate(R.layout.custom_info_window, null);
    }

    private void setWindowText(Marker marker, View view){
        String title = marker.getTitle();
        String description = marker.getSnippet();
        TextView vTitle = view.findViewById(R.id.title);
        TextView vSnippet = view.findViewById(R.id.description);

        if(!title.equals("")){
            vTitle.setText(title);
        }
        if(!description.equals("")){
            vSnippet.setText(description);
        }
    }


    @Override
    public View getInfoWindow(Marker marker) {
        setWindowText(marker, window);
        return window;
    }

    @Override
    public View getInfoContents(Marker marker) {
        setWindowText(marker, window);
        return window;
    }
}
