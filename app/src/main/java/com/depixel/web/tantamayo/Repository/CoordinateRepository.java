package com.depixel.web.tantamayo.Repository;

import com.depixel.web.tantamayo.Entity.CoordinateClass;

import java.util.ArrayList;

public class CoordinateRepository {
        public void loadCoordinate(){
            ArrayList<CoordinateClass> milist= new ArrayList<>();
            CoordinateClass coor= new CoordinateClass();
            coor.LaLng=0;coor.Lng=0;
            milist.add(coor);
        }
}
