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
        desc.access=" ";
        desc.schedule="";
        desc.ubication="";
        desc.history="";
        desc.imagenPlace=R.drawable.ruta1_museo;
        desc.videoPlace=-1;
        list.add(desc);
        //ruta 2
        desc= new DescriptionPlaceClass();
        desc.place="Plaza de Pampa Florida";
        desc.access="";
        desc.schedule="";
        desc.ubication="";
        desc.history="";
        desc.imagenPlace=R.drawable.ruta2_plaza_florida;
        desc.videoPlace=-1;
        list.add(desc);

        desc= new DescriptionPlaceClass();
        desc.place="Susupillo";
        desc.access="";
        desc.schedule="";
        desc.ubication="";
        desc.history="";
        desc.imagenPlace=R.drawable.ruta2_susupillo;
        desc.videoPlace=-1;
        list.add(desc);

        desc= new DescriptionPlaceClass();
        desc.place="Isog";
        desc.access="";
        desc.schedule="";
        desc.ubication="";
        desc.history="";
        desc.imagenPlace=R.drawable.ruta2_isoj;
        desc.videoPlace=-1;
        list.add(desc);

        desc= new DescriptionPlaceClass();
        desc.place="Jipango";
        desc.access="";
        desc.schedule="";
        desc.ubication="";
        desc.history="";
        desc.imagenPlace=R.drawable.ruta2_jipango;
        desc.videoPlace=-1;
        list.add(desc);
        //7 ruta 3
        desc= new DescriptionPlaceClass();
        desc.place="Coyllarbamba";
        desc.access=" ";
        desc.schedule="";
        desc.ubication="";
        desc.history="";
        desc.imagenPlace=R.drawable.ruta3_coyllarbamba;
        desc.videoPlace=-1;
        list.add(desc);

        desc= new DescriptionPlaceClass();
        desc.place="Piruro I";
        desc.access=" ";
        desc.schedule="";
        desc.ubication="";
        desc.history="";
        desc.imagenPlace=R.drawable.ruta3_piruro1;
        desc.videoPlace=-1;
        list.add(desc);

        desc= new DescriptionPlaceClass();
        desc.place="Piruro II";
        desc.access=" ";
        desc.schedule="";
        desc.ubication="";
        desc.history="";
        desc.imagenPlace=R.drawable.ruta3_piruro2;
        desc.videoPlace=-1;
        list.add(desc);

        desc= new DescriptionPlaceClass();
        desc.place="Ango";
        desc.access=" ";
        desc.schedule="";
        desc.ubication="";
        desc.history="";
        desc.imagenPlace=R.drawable.no_disponible;
        desc.videoPlace=-1;
        list.add(desc);

        desc= new DescriptionPlaceClass();
        desc.place="Huqlla wagra";
        desc.access="";
        desc.schedule="";
        desc.ubication="";
        desc.history="";
        desc.imagenPlace=R.drawable.no_disponible;
        desc.videoPlace=-1;
        list.add(desc);
        //ruta 4-12
        desc= new DescriptionPlaceClass();
        desc.place="San Pedro de Pariarca";
        desc.access="";
        desc.schedule="";
        desc.ubication="";
        desc.history="";
        desc.imagenPlace=R.drawable.ruta4_san_pedro_pariarca;
        desc.videoPlace=-1;
        list.add(desc);

        desc= new DescriptionPlaceClass();
        desc.place="Pariash";
        desc.access="";
        desc.schedule="";
        desc.ubication="";
        desc.history="";
        desc.imagenPlace=R.drawable.ruta4_pariash;
        desc.videoPlace=-1;
        list.add(desc);

        desc= new DescriptionPlaceClass();
        desc.place="Usuy Huayragh";
        desc.access="";
        desc.schedule="";
        desc.ubication="";
        desc.history="";
        desc.imagenPlace=R.drawable.ruta4_usuy;
        desc.videoPlace=-1;
        list.add(desc);

        desc= new DescriptionPlaceClass();
        desc.place="Llama Llama";
        desc.access="";
        desc.schedule="";
        desc.ubication="";
        desc.history="";
        desc.imagenPlace=R.drawable.ruta4_llama;
        desc.videoPlace=-1;
        list.add(desc);

        desc= new DescriptionPlaceClass();
        desc.place="Japallan";
        desc.access="";
        desc.schedule="";
        desc.ubication="";
        desc.history="";
        desc.imagenPlace=R.drawable.ruta4_japallan;
        desc.videoPlace=-1;
        list.add(desc);

        desc= new DescriptionPlaceClass();
        desc.place="Laguna Blanca";
        desc.access="";
        desc.schedule="";
        desc.ubication="";
        desc.history="";
        desc.imagenPlace=R.drawable.ruta4_laguna_blanca;
        desc.videoPlace=-1;
        list.add(desc);
//ruta 5 -18
        desc= new DescriptionPlaceClass();
        desc.place="Alojamiento Rural";
        desc.access="";
        desc.schedule="";
        desc.ubication="";
        desc.history="";
        desc.imagenPlace=R.drawable.ruta5_alojamiento;
        desc.videoPlace=-1;
        list.add(desc);

        desc= new DescriptionPlaceClass();
        desc.place="Laguna Huascacocha";
        desc.access="";
        desc.schedule="";
        desc.ubication="";
        desc.history="";
        desc.imagenPlace=R.drawable.ruta5_laguna_huscacocha;
        desc.videoPlace=-1;
        list.add(desc);

        desc= new DescriptionPlaceClass();
        desc.place="Miskicocha";
        desc.access="";
        desc.schedule="";
        desc.ubication="";
        desc.history="";
        desc.imagenPlace=R.drawable.ruta5_miskicocha;
        desc.videoPlace=-1;
        list.add(desc);

        desc= new DescriptionPlaceClass();
        desc.place="Angelcocha";
        desc.access="";
        desc.schedule="";
        desc.ubication="";
        desc.history="";
        desc.imagenPlace=R.drawable.ruta5_angelcocha;
        desc.videoPlace=-1;
        list.add(desc);

        desc= new DescriptionPlaceClass();
        desc.place="Huella de Serpiente";
        desc.access="";
        desc.schedule="";
        desc.ubication="";
        desc.history="";
        desc.imagenPlace=R.drawable.ruta5_huella_serpiente;
        desc.videoPlace=-1;
        list.add(desc);

        desc= new DescriptionPlaceClass();
        desc.place="Mancacocha";
        desc.access="";
        desc.schedule="";
        desc.ubication="";
        desc.history="";
        desc.imagenPlace=R.drawable.ruta5_mancacocha;
        desc.videoPlace=-1;
        list.add(desc);

        desc= new DescriptionPlaceClass();
        desc.place="Rima Rima";
        desc.access="";
        desc.schedule="";
        desc.ubication="";
        desc.history="";
        desc.imagenPlace=R.drawable.ruta5_rima;
        desc.videoPlace=-1;
        list.add(desc);

        desc= new DescriptionPlaceClass();
        desc.place="waswacocha";
        desc.access="";
        desc.schedule="";
        desc.ubication="";
        desc.history="";
        desc.imagenPlace=R.drawable.ruta5_waswacocha;
        desc.videoPlace=-1;
        list.add(desc);

        desc= new DescriptionPlaceClass();
        desc.place="Selmin Granero";
        desc.access="";
        desc.schedule="";
        desc.ubication="";
        desc.history="";
        desc.imagenPlace=R.drawable.ruta5_selmin_granero;
        desc.videoPlace=-1;
        list.add(desc);
//ruta 6 -27
        desc= new DescriptionPlaceClass();
        desc.place="Shucsha";
        desc.access="";
        desc.schedule="";
        desc.ubication="";
        desc.history="";
        desc.imagenPlace=R.drawable.ruta6_shucsha;
        desc.videoPlace=-1;
        list.add(desc);

        desc= new DescriptionPlaceClass();
        desc.place="Silla del Inca";
        desc.access="";
        desc.schedule="";
        desc.ubication="";
        desc.history="";
        desc.imagenPlace=R.drawable.no_disponible;
        desc.videoPlace=-1;
        list.add(desc);

        desc= new DescriptionPlaceClass();
        desc.place="Ayllish Alta";
        desc.access="";
        desc.schedule="";
        desc.ubication="";
        desc.history="";
        desc.imagenPlace=R.drawable.ruta6_ayllish_alta;
        desc.videoPlace=-1;
        list.add(desc);

        desc= new DescriptionPlaceClass();
        desc.place="Mirador del río Marañon";
        desc.access="";
        desc.schedule="";
        desc.ubication="";
        desc.history="";
        desc.imagenPlace=R.drawable.ruta6_mirador_maranion;
        desc.videoPlace=-1;
        list.add(desc);

        desc= new DescriptionPlaceClass();
        desc.place="Ayllish Baja";
        desc.access="";
        desc.schedule="";
        desc.ubication="";
        desc.history="";
        desc.imagenPlace=R.drawable.ruta6_ayllish_bajo;
        desc.videoPlace=-1;
        list.add(desc);

        desc= new DescriptionPlaceClass();
        desc.place="Entierro de la Bruja";
        desc.access="";
        desc.schedule="";
        desc.ubication="";
        desc.history="";
        desc.imagenPlace=R.drawable.ruta6_entierro_bruja;
        desc.videoPlace=-1;
        list.add(desc);
//ruta 7 -33
        desc= new DescriptionPlaceClass();
        desc.place="Tecllo Cocha";
        desc.access="";
        desc.schedule="";
        desc.ubication="";
        desc.history="";
        desc.imagenPlace=R.drawable.ruta7_tecllococha;
        desc.videoPlace=-1;
        list.add(desc);

        desc= new DescriptionPlaceClass();
        desc.place="Jaracocha";
        desc.access="";
        desc.schedule="";
        desc.ubication="";
        desc.history="";
        desc.imagenPlace=R.drawable.ruta7_jaracocha;
        desc.videoPlace=-1;
        list.add(desc);

        desc= new DescriptionPlaceClass();
        desc.place="Asnacocha";
        desc.access="";
        desc.schedule="";
        desc.ubication="";
        desc.history="";
        desc.imagenPlace=R.drawable.ruta7_asnacocha;
        desc.videoPlace=-1;
        list.add(desc);

        desc= new DescriptionPlaceClass();
        desc.place="Mirador de Carpa";
        desc.access="";
        desc.schedule="";
        desc.ubication="";
        desc.history="";
        desc.imagenPlace=R.drawable.ruta7_mirador_carpa;
        desc.videoPlace=-1;
        list.add(desc);




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
