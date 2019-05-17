package com.example.djnig.tantamayo.Repository;

import com.example.djnig.tantamayo.Entity.CoordinateClass;

import java.util.ArrayList;

public class CoordinateRepository {
        public void loadCoordinate(){
            ArrayList<CoordinateClass> milist= new ArrayList<>();
            CoordinateClass coor= new CoordinateClass();
            coor.LaLng=0;coor.Lng=0;
            milist.add(coor);
        }
}
