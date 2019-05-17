package com.example.djnig.tantamayo.Repository;

import android.util.Log;

import com.example.djnig.tantamayo.Entity.DetailRoute;
import com.example.djnig.tantamayo.R;

import java.io.Console;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class RouteRepository {
    private static RouteRepository repository = new RouteRepository();
    private HashMap<String, DetailRoute> leads = new HashMap<>();

    public static RouteRepository getInstance() {
        return repository;
    }

    private RouteRepository() {

            saveRoute(new DetailRoute("1","Ruta 1", "Full Day","Tantamayo-Coyllarbamba", "1.38km", R.drawable.id_action_route));
            saveRoute(new DetailRoute("2","Ruta 2", "Full Day", "Pampa Florida","5.77km", R.drawable.id_action_route));
            saveRoute(new DetailRoute("3","Ruta 3", "Full Day", "Coyllarbamba","8.44km", R.drawable.id_action_route));
            saveRoute(new DetailRoute("4","Ruta 4", "Full Day", "Coyllarbamba","13.9km", R.drawable.id_action_route));
            saveRoute(new DetailRoute("5","Ruta 5", "Full Day", "Opcional a la Ruta 4","5.77km", R.drawable.id_action_route));
            saveRoute(new DetailRoute("6","Ruta 6", "Full Day", "Coyllarbamba-Carpa","16.0km", R.drawable.id_action_route));
            saveRoute(new DetailRoute("7","Ruta 7", "2D/1N", "Carpa - Tingo Chico","19.0km", R.drawable.id_action_route));
            saveRoute(new DetailRoute("8","Ruta 8", "Full Day", "Chapacara","1.63km", R.drawable.id_action_route));

    }

    private void saveRoute(DetailRoute lead) {
        leads.put(lead.getIdRoute(), lead);
    }

    public List<DetailRoute> getRoutes() {

        Map<String, DetailRoute> treeMap = new TreeMap<String, DetailRoute>(leads);
       // Log.d("myTag", "This is my message"+treeMap.values());
        return new ArrayList<>(treeMap.values());
    }
}
