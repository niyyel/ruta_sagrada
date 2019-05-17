package com.depixel.web.tantamayo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class TouristAttractionFragment extends Fragment {


    private TextView textNamePlace;
    private TextView textUbication;
    private TextView textSchedule;
    private TextView textHistoryJustify;
    private TextView textAccess;
    private ImageView avatar ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_tourist_attraction, container, false);
        textNamePlace = (TextView) root.findViewById(R.id.namePlace);
        textUbication= (TextView) root.findViewById(R.id.textUbication);
        textSchedule = (TextView) root.findViewById(R.id.textSchedule);
        textHistoryJustify=(TextView) root.findViewById(R.id.texViewHistory);
        textAccess=(TextView) root.findViewById(R.id.textAcces);
        avatar = (ImageView) root.findViewById(R.id.imagePlaceDetail);

        TextView textUbicationTitle = (TextView) root.findViewById(R.id.textUbicationTitle);
        TextView textScheduleTitle = (TextView) root.findViewById(R.id.textScheduleTitle);
        TextView textHistoryTitle = (TextView) root.findViewById(R.id.textHistoryTitle);
        TextView textAccesTitle = (TextView) root.findViewById(R.id.textAccesTitle);

        textUbicationTitle.setCompoundDrawablesWithIntrinsicBounds(R.drawable.location, 0, 0, 0);
        textScheduleTitle.setCompoundDrawablesWithIntrinsicBounds(R.drawable.schedule, 0, 0, 0);
        textHistoryTitle.setCompoundDrawablesWithIntrinsicBounds(R.drawable.history, 0, 0, 0);
        textAccesTitle.setCompoundDrawablesWithIntrinsicBounds(R.drawable.acces, 0, 0, 0);

        if (getArguments() != null) {
            textNamePlace.setText(getArguments().getString("titulo"));
            textUbication.setText(getArguments().getString("ubication"));
            textHistoryJustify.setText(getArguments().getString("history"));
            textSchedule.setText(getArguments().getString("schedule"));
            textAccess.setText(getArguments().getString("access"));
            avatar.setImageResource(getArguments().getInt("imagePlace"));
        }

        return root;
    }



}
