package com.depixel.web.tantamayo.Repository;

import com.depixel.web.tantamayo.Entity.DescriptionPlaceClass;
import com.depixel.web.tantamayo.R;

import java.util.ArrayList;
import java.util.List;

public class PlaceDetailsRepository {
    static List<DescriptionPlaceClass> list;
    public static void load(){
        list= new ArrayList<>();
        //ruta 1
        DescriptionPlaceClass desc= new DescriptionPlaceClass();
        desc.place="Plaza de Tantamayo";
        desc.schedule="Todo el dia";
        desc.ubication="Pleno Centro";
        desc.history="Fue construida en la epoca colonial";
        desc.access="En bus";
        desc.imagenPlace= R.drawable.plaza;
        desc.videoPlace=-1;
        list.add(desc);

        desc= new DescriptionPlaceClass();
        desc.place="Iglesia Matriz de Tantamayo";
        desc.access=" Tramo : Tantamayo – Iglesia Matriz \n Distancia:10 m / 05 minutos a pie";
        desc.schedule="Todo el año - 06:30hrs / 19:00hrs";
        desc.ubication="Plaza de armas, Distrito de Tantamayo, 3641 m.s.n.m.";
        desc.history="En este importante templo colonial se puede observar una nave de grandes dimensiones, construida en el siglo XVIII, por los curas jesuitas, elaborados a base de adobe, sobre cimientos de piedra, contrafuertes laterales, techo a dos aguas inclinado, techado con tejas.\nEl retablo del altar mayor bellamente trabajado; dorado, de estilo churrigueresco en donde se encuentra la imagen de Santa Rosa de Lima Patrona del Distrito de Tantamayo, a la cual se le realiza la fiesta patronal el 30 de Agosto.\nAl frente de la fachada principal presenta una torre de tres cuerpos de base robusta, construida en piedra y adobe, en el interior se ubica una escalera, en la parte superior ocho vanos en arco que sirve de campanario, techado con tejas\n";
        desc.imagenPlace=R.drawable.iglesia_matriz;
        desc.videoPlace=-1;
        list.add(desc);

        desc= new DescriptionPlaceClass();
        desc.place="Museo del colegio";
        desc.access=" Tramo : Tantamayo – Iglesia Matriz \n Distancia:10 m / 05 minutos a pie";
        desc.schedule="Todo el año - 06:30hrs / 19:00hrs";
        desc.ubication="Plaza de armas, Distrito de Tantamayo, 3641 m.s.n.m.";
        desc.history="";
        desc.imagenPlace=R.drawable.ruta1_museo;
        desc.videoPlace=-1;
        list.add(desc);
        //ruta 2
        desc= new DescriptionPlaceClass();
        desc.place="Plaza de Pampa Florida";
        desc.access=" Tramo : Tantamayo – Iglesia Matriz \n Distancia:10 m / 05 minutos a pie";
        desc.schedule="Todo el año - 06:30hrs / 19:00hrs";
        desc.ubication="Plaza de armas, Distrito de Tantamayo, 3641 m.s.n.m.";
        desc.history="";
        desc.imagenPlace=R.drawable.ruta2_susupillo;
        desc.videoPlace=-1;
        list.add(desc);

        desc= new DescriptionPlaceClass();
        desc.place="Susupillo";
        desc.access=" Tramo : Tantamayo – Iglesia Matriz \n Distancia:10 m / 05 minutos a pie";
        desc.schedule="Todo el año - 06:30hrs / 19:00hrs";
        desc.ubication="Plaza de armas, Distrito de Tantamayo, 3641 m.s.n.m.";
        desc.history="";
        desc.imagenPlace=R.drawable.ruta2_susupillo;
        desc.videoPlace=-1;
        list.add(desc);

        desc= new DescriptionPlaceClass();
        desc.place="Isog";
        desc.access=" Tramo : Tantamayo – Iglesia Matriz \n Distancia:10 m / 05 minutos a pie";
        desc.schedule="Todo el año - 06:30hrs / 19:00hrs";
        desc.ubication="Plaza de armas, Distrito de Tantamayo, 3641 m.s.n.m.";
        desc.history="";
        desc.imagenPlace=R.drawable.ruta2_isoj;
        desc.videoPlace=-1;
        list.add(desc);

        desc= new DescriptionPlaceClass();
        desc.place="Jipango";
        desc.access=" Tramo : Tantamayo – Iglesia Matriz \n Distancia:10 m / 05 minutos a pie";
        desc.schedule="Todo el año - 06:30hrs / 19:00hrs";
        desc.ubication="Plaza de armas, Distrito de Tantamayo, 3641 m.s.n.m.";
        desc.history="";
        desc.imagenPlace=R.drawable.ruta2_jipango;
        desc.videoPlace=-1;
        list.add(desc);
        //8 ruta 3

    }
    public static DescriptionPlaceClass getDescriptionPlace(int id){
        load();
        DescriptionPlaceClass descx=list.get(id);
        /*for (DescriptionPlaceClass dx : list) {
            Log.d("myTag", "This is my message"+dx.access);
        }*/
        return descx;
    }


}
