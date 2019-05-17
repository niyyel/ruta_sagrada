package com.depixel.web.tantamayo.Repository;

import com.depixel.web.tantamayo.Entity.CraftClass;
import com.depixel.web.tantamayo.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CraftRepository {
    private static CraftRepository repository = new CraftRepository();
    private HashMap<String, CraftClass> leads = new HashMap<>();

    public static CraftRepository getInstance() {
        return repository;
    }

    private CraftRepository() {
        saveRoute(new CraftClass(R.drawable.gallito,"ayar wasaca", "Lun dom","+51971444147","1"));
        saveRoute(new CraftClass(R.drawable.gallito,"wasi", "viernes","+51971444147","2"));

    }

    private void saveRoute(CraftClass lead) {
        leads.put(lead.getIdCraft(), lead);
    }

    public List<CraftClass> getData() {

        Map<String, CraftClass> treeMap = new TreeMap<String, CraftClass>(leads);
        // Log.d("myTag", "This is my message"+treeMap.values());
        return new ArrayList<>(treeMap.values());
    }
}
