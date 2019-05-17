package com.depixel.web.tantamayo.Entity;

import java.util.Arrays;

public class DetailRoute {
    public String idRoute="";
    public String nameRoute="";
    public String typeRoute="";
    public String stretchRoute="";
    public String kmRoute="";
    public String [] placeRoute=null;
    public int imagenRoute=-1;

    public DetailRoute(String id,String nameRoute, String typeRoute,String stretchRoute,String kmRoute, int imagenRoute) {
       // this.idRoute = UUID.randomUUID().toString();;
        this.idRoute = id;
        this.nameRoute = nameRoute;
        this.typeRoute = typeRoute;
        this.kmRoute = kmRoute;
        this.imagenRoute = imagenRoute;
        this.stretchRoute=stretchRoute;
    }

    public String getIdRoute() {
        return idRoute;
    }

    public void setIdRoute(String idRoute) {
        this.idRoute = idRoute;
    }

    public String getNameRoute() {
        return nameRoute;
    }

    public void setNameRoute(String nameRoute) {
        this.nameRoute = nameRoute;
    }

    public String getTypeRoute() {
        return typeRoute;
    }

    public void setTypeRoute(String typeRoute) {
        this.typeRoute = typeRoute;
    }

    public String getStretchRoute() {
        return stretchRoute;
    }

    public void setStretchRoute(String stretchRoute) {
        this.stretchRoute = stretchRoute;
    }

    public String getKmRoute() {
        return kmRoute;
    }

    public void setKmRoute(String kmRoute) {
        this.kmRoute = kmRoute;
    }

    public String[] getPlaceRoute() {
        return placeRoute;
    }

    public void setPlaceRoute(String[] placeRoute) {
        this.placeRoute = placeRoute;
    }

    public int getImagenRoute() {
        return imagenRoute;
    }

    public void setImagenRoute(int imagenRoute) {
        this.imagenRoute = imagenRoute;
    }

    @Override
    public String toString() {
        return "DetailRoute{" +
                "idRoute='" + idRoute + '\'' +
                ", nameRoute='" + nameRoute + '\'' +
                ", typeRoute='" + typeRoute + '\'' +
                ", stretchRoute='" + stretchRoute + '\'' +
                ", kmRoute='" + kmRoute + '\'' +
                ", placeRoute=" + Arrays.toString(placeRoute) +
                ", imagenRoute='" + imagenRoute + '\'' +
                '}';
    }
}
