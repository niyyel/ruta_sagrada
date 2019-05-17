package com.depixel.web.tantamayo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.depixel.web.tantamayo.AdapterClass.CraftAdapter;
import com.depixel.web.tantamayo.Entity.CraftClass;
import com.depixel.web.tantamayo.Repository.CraftRepository;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * create an instance of this fragment.
 * create an instance of this fragment.
 */
public class CraftsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ListView mLeadsList;
        final ArrayAdapter<CraftClass> mLeadsAdapter;
        // Instancia del ListView.
        View view = inflater.inflate(R.layout.fragment_crafts, container, false);
        mLeadsList = (ListView) view.findViewById(R.id.list_craft);
        mLeadsAdapter = new CraftAdapter(getActivity(), CraftRepository.getInstance().getData());
        mLeadsList.setAdapter(mLeadsAdapter);
        mLeadsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                CraftClass currentLead = mLeadsAdapter.getItem(position);
                Toast.makeText( getContext().getApplicationContext(), "nombre: "+currentLead.getNameCraft(), Toast.LENGTH_SHORT).show();

            }
        });
        return view;
    }



}
