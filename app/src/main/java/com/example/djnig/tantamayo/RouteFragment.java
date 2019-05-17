package com.example.djnig.tantamayo;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

//import android.app.Fragment;

import com.example.djnig.tantamayo.Entity.DescriptionPlaceClass;
import com.example.djnig.tantamayo.Repository.PlaceDetailsRepository;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;


public class RouteFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnPolylineClickListener,
        GoogleMap.OnPolygonClickListener,GoogleMap.OnMarkerClickListener,GoogleMap.OnInfoWindowClickListener {
    // TODO: Rename parameter arguments, choose names that match
    SupportMapFragment mapFragment;
    GoogleMap mMap;
    private Marker marcador;
    double lat = 0.00;
    double lgn = 0.00;
    Dialog detalleHistorialDialog;
    Button btnInfo;
    LocationManager locationManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment == null) {
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            mapFragment = SupportMapFragment.newInstance();
            ft.replace(R.id.map, mapFragment).commit();
        }
        mapFragment.getMapAsync(this);
        mapFragment.getMapAsync(this);

        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        checkLocation();

        return inflater.inflate(R.layout.fragment_route, container, false);
    }
//para activar gps
    private boolean checkLocation() {
        if (!isLocationEnabled())
            showAlert();
        return isLocationEnabled();
    }
    private void showAlert() {
            final AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
            dialog.setTitle("Active Ubicación")
                    .setMessage("Su ubicación esta desactivada.\npor favor active su ubicación " +
                            "para mejor uso de la aplicación")
                    .setPositiveButton("Configuración de ubicación", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                            Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            startActivity(myIntent);
                        }
                    })
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        }
                    });
            dialog.show();
    }
    private boolean isLocationEnabled() {
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        //trazar las rutas
        ruta1(googleMap);
        ruta2(googleMap);
        ruta3(googleMap);
        ruta4(googleMap);
        ruta5(googleMap);
        ruta6(googleMap);
        ruta7(googleMap);
        ruta8(googleMap);
        ruta9(googleMap);

        mMap = googleMap;
        LatLng latLng = new LatLng(-9.39283, -76.719);
        /*MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        markerOptions.title("Tantamayo");
        markerOptions.snippet("Localidad");
        mMap.addMarker(markerOptions);*/
        puntoRuta1();
        puntoRuta2();
        puntoRuta3();
        puntoRuta4();
        puntoRuta5();
        puntoRuta6();
        puntoRuta7();
        puntoRuta8();
        puntoRuta9();

        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-9.34087, -76.68433), 12));
       // mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        //  mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
       // mMap.setOnPolylineClickListener(this);
       // mMap.setOnPolygonClickListener(this);
        mMap.setOnMarkerClickListener(this);
        mMap.setOnInfoWindowClickListener(this);
        mMap.setOnPolylineClickListener(this);


        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        //mostrar boton de mi ubicacion y botones de zoom
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        // miUbicacionx();

    }
    public void puntoRuta1(){
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(new LatLng(-9.392404948274953, -76.72009985893965));
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
        markerOptions.title("Plaza de Tantamayo");
        markerOptions.snippet("Clic para ver más");
        mMap.addMarker(markerOptions);

        markerOptions.position(new LatLng(-9.392718528851981, -76.7202852666378));
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
    //    markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.iglesia));

        markerOptions.title("Iglesia Matriz de Tantamayo");
        markerOptions.snippet("Clic para ver más");
        mMap.addMarker(markerOptions);
        //otra ruta
        markerOptions.position(new LatLng(-9.393137628188352, -76.72013506293298));
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
        markerOptions.title("Museo del colegio");
        markerOptions.snippet("Clic para ver más");
        mMap.addMarker(markerOptions);
    }
    public void puntoRuta2(){
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(new LatLng(-9.39808177126022, -76.70378666371107));
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        markerOptions.title("Plaza de Pampa Florida");
        markerOptions.snippet("Clic para ver más");
        mMap.addMarker(markerOptions);

        markerOptions.position(new LatLng(-9.401450376901794, -76.69296193867922));
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        markerOptions.title("Susupillo");
        markerOptions.snippet("Clic para ver más");
        mMap.addMarker(markerOptions);

        markerOptions.position(new LatLng(-9.39876912295273, -76.69499035924673));
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        markerOptions.title("Isog");
        markerOptions.snippet("Clic para ver más");

        mMap.addMarker(markerOptions);

        markerOptions.position(new LatLng(-9.391904806514011, -76.69261928647757));
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        markerOptions.title("Jipango");
        markerOptions.snippet("Clic para ver más");
        mMap.addMarker(markerOptions);
    }
    public void puntoRuta3(){
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(new LatLng(-76.7134416103363, -76.7134416103363));
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        markerOptions.title("Coyllarbamba");
        markerOptions.snippet("Clic para ver más");
        mMap.addMarker(markerOptions);

        markerOptions.position(new LatLng(-9.382555462837361, -76.70729365199804));
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        markerOptions.title("Piruro I");
        markerOptions.snippet("Clic para ver más");
        mMap.addMarker(markerOptions);

        markerOptions.position(new LatLng(-9.381823422134588, -76.70646887272596));
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        markerOptions.title("Piruro II");
        markerOptions.snippet("Clic para ver más");
        mMap.addMarker(markerOptions);

        markerOptions.position(new LatLng(-9.375696118014575, -76.68429471552372));
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        markerOptions.title("Ango");
        markerOptions.snippet("Clic para ver más");
        mMap.addMarker(markerOptions);

        markerOptions.position(new LatLng(-9.376501939280768, -76.69193599373102));
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        markerOptions.title("Huqlla wagra");
        markerOptions.snippet("Clic para ver más");
        mMap.addMarker(markerOptions);
    }
    public void puntoRuta4(){
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(new LatLng(-9.37320586707029, -76.72044653445481));
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        markerOptions.title("San Pedro de Pariarca");
        markerOptions.snippet("Clic para ver más");
        mMap.addMarker(markerOptions);

        markerOptions.position(new LatLng(-9.365791575013995, -76.72152243554592));
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        markerOptions.title("Pariash");
        markerOptions.snippet("Clic para ver más");
        mMap.addMarker(markerOptions);

        markerOptions.position(new LatLng(-9.363579463379306, -76.72289438545704));
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        markerOptions.title("Usuy Huayragh");
        markerOptions.snippet("Clic para ver más");
        mMap.addMarker(markerOptions);

        markerOptions.position(new LatLng(-9.353120453914146, -76.71710383147001));
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        markerOptions.title("Llama Llama");
        markerOptions.snippet("Clic para ver más");
        mMap.addMarker(markerOptions);

        markerOptions.position(new LatLng(-9.351672787891497, -76.72926932573318));
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        markerOptions.title("Japallan");
        markerOptions.snippet("Clic para ver más");
        mMap.addMarker(markerOptions);

        markerOptions.position(new LatLng(-9.341878388322941, -76.70793436467648));
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        markerOptions.title("Laguna Blanca");
        markerOptions.snippet("Clic para ver más");
        mMap.addMarker(markerOptions);
    }
    public void puntoRuta5(){
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(new LatLng(-9.344360593002225, -76.70971669256687));
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
        markerOptions.title("Alojamiento Rural");
        markerOptions.snippet("Clic para ver más");
        mMap.addMarker(markerOptions);

        markerOptions.position(new LatLng(-9.336167232339788, -76.7035224661231));
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
        markerOptions.title("Laguna Huascacocha");
        markerOptions.snippet("Clic para ver más");
        mMap.addMarker(markerOptions);

        markerOptions.position(new LatLng(-9.334991112304248, -76.7035110667348));
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
        markerOptions.title("Miskicocha");
        markerOptions.snippet("Clic para ver más");
        mMap.addMarker(markerOptions);

        markerOptions.position(new LatLng(-9.33273546508155, -76.7030205577612));
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
        markerOptions.title("Angelcocha");
        markerOptions.snippet("Clic para ver más");
        mMap.addMarker(markerOptions);

        markerOptions.position(new LatLng(-9.33139060539995, -76.70290119946003));
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
        markerOptions.title("Huella de Serpiente");
        markerOptions.snippet("Clic para ver más");
        mMap.addMarker(markerOptions);

        markerOptions.position(new LatLng(-9.336182450744605, -76.69957961887121));
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
        markerOptions.title("Mancacocha");
        markerOptions.snippet("Clic para ver más");
        mMap.addMarker(markerOptions);

        markerOptions.position(new LatLng(-9.338628966096817, -76.70162882655859));
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
        markerOptions.title("Rima Rima");
        markerOptions.snippet("Clic para ver más");
        mMap.addMarker(markerOptions);

        markerOptions.position(new LatLng(-9.341660371632933, -76.70177835971117));
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
        markerOptions.title("waswacocha");
        markerOptions.snippet("Clic para ver más");
        mMap.addMarker(markerOptions);

        markerOptions.position(new LatLng(-9.361547629577098, -76.71969350427389));
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
        markerOptions.title("Selmin Granero");
        markerOptions.snippet("Clic para ver más");
        mMap.addMarker(markerOptions);
    }
    public void puntoRuta6(){
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(new LatLng(-9.365469369111231, -76.72611203044653));
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        markerOptions.title("Shucsha");
        markerOptions.snippet("Clic para ver más");
        mMap.addMarker(markerOptions);

        markerOptions.position(new LatLng(-9.3651934759344, -76.72604497522116));
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        markerOptions.title("Silla del Inca");
        markerOptions.snippet("Clic para ver más");
        mMap.addMarker(markerOptions);

        markerOptions.position(new LatLng(-9.358274572420099, -76.73409394919872));
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        markerOptions.title("Ayllish Alta");
        markerOptions.snippet("Clic para ver más");
        mMap.addMarker(markerOptions);

        markerOptions.position(new LatLng(-9.354755687332995, -76.73599630594254));
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        markerOptions.title("Mirador del río Marañon");
        markerOptions.snippet("Clic para ver más");
        mMap.addMarker(markerOptions);

        markerOptions.position(new LatLng(-9.361341865217994, -76.73394843935966));
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        markerOptions.title("Ayllish Baja");
        markerOptions.snippet("Clic para ver más");
        mMap.addMarker(markerOptions);

        markerOptions.position(new LatLng(-9.364470661236751, -76.73185832798481));
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        markerOptions.title("Entierro de la Bruja");
        markerOptions.snippet("Clic para ver más");
        mMap.addMarker(markerOptions);
    }
    public void puntoRuta7(){
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(new LatLng(-9.373491677863175, -76.65466837584972));
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE));
        markerOptions.title("Tecllo Cocha");
        markerOptions.snippet("Clic para ver más");
        mMap.addMarker(markerOptions);

        markerOptions.position(new LatLng(-9.353574998630064, -76.6552048176527));
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE));
        markerOptions.title("Jaracocha");
        markerOptions.snippet("Clic para ver más");
        mMap.addMarker(markerOptions);

        markerOptions.position(new LatLng(-9.346078243138601, -76.65140848606825));
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE));
        markerOptions.title("Asnacocha");
        markerOptions.snippet("Clic para ver más");
        mMap.addMarker(markerOptions);

        markerOptions.position(new LatLng(-9.345162186175507, -76.6514490544796));
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE));
        markerOptions.title("Mirador de Carpa");
        markerOptions.snippet("Clic para ver más");
        mMap.addMarker(markerOptions);
    }
    public void puntoRuta8(){
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(new LatLng(-9.326982142156805, -76.6589290648699));
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));
        markerOptions.title("Carpa");
        markerOptions.snippet("Clic para ver más");
        mMap.addMarker(markerOptions);

        markerOptions.position(new LatLng(-9.327205792365438, -76.65475554764271));
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));
        markerOptions.title("Criadero de Trucha");
        markerOptions.snippet("Clic para ver más");
        mMap.addMarker(markerOptions);

        markerOptions.position(new LatLng(-9.332177340672406, -76.6473150998354));
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));
        markerOptions.title("Laguna de Carpa");
        markerOptions.snippet("Clic para ver más");
        mMap.addMarker(markerOptions);

        markerOptions.position(new LatLng(-9.342927116168894, -76.63931977003813));
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));
        markerOptions.title("Pacharahuay");
        markerOptions.snippet("Clic para ver más");
        mMap.addMarker(markerOptions);
    }
    public void puntoRuta9(){
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(new LatLng(-9.321300849435044, -76.63780096918343));
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET));
        markerOptions.title("Laguna Asiag");
        markerOptions.snippet("Clic para ver más");
        mMap.addMarker(markerOptions);

        markerOptions.position(new LatLng(-9.32021367794029, -76.63297198712826));
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET));
        markerOptions.title("Tayta Mayo");
        markerOptions.snippet("Clic para ver más");
        mMap.addMarker(markerOptions);

        markerOptions.position(new LatLng(-9.312270871021871, -76.6223879903555));
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET));
        markerOptions.title("Laguna Arcuma");
        markerOptions.snippet("Clic para ver más");
        mMap.addMarker(markerOptions);

        markerOptions.position(new LatLng(-9.313362368377089, -76.62058554589748));
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET));
        markerOptions.title("Cuchimachay");
        markerOptions.snippet("Clic para ver más");
        mMap.addMarker(markerOptions);
    }

    public void ruta1(GoogleMap googleMap){
        googleMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .add(
                        new LatLng(-9.39238708608164,-76.7200958356261),
                        new LatLng(-9.39256835422288,-76.7202031239867),
                        new LatLng(-9.39270695151505,-76.7200240865349),
                        new LatLng(-9.39302615509156,-76.7202175408601),
                        new LatLng(-9.39313266647857,-76.7201360687613),
                        new LatLng(-9.39301557010721,-76.720098182559),
                        new LatLng(-9.39265898825793,-76.7199312150478),
                        new LatLng(-9.39256835422288,-76.7200931534171),
                        new LatLng(-9.39240858686978,-76.7200515791773)

                        ).color(Color.rgb(222, 79, 13)).width(5));
    }
    public void ruta2(GoogleMap googleMap){
         googleMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .add(
                        new LatLng(-9.39237484717087,-76.7201417684555),
                        new LatLng(-9.3921876248604,-76.7200227454304),
                        new LatLng(-9.39299770794538,-76.719096712768),
                        new LatLng(-9.39301457776492,-76.718919686973),
                        new LatLng(-9.39308238781549,-76.7187212035059),
                        new LatLng(-9.39311711978748,-76.7184744402766),
                        new LatLng(-9.39320543821484,-76.7181864380836),
                        new LatLng(-9.39320576899543,-76.7180426046252),
                        new LatLng(-9.39309164967503,-76.7181170359253),
                        new LatLng(-9.39309594982402,-76.7179302871227),
                        new LatLng(-9.39318393747618,-76.7176939174532),
                        new LatLng(-9.39318393747618,-76.7174381017684),
                        new LatLng(-9.3931806296701,-76.7172583937644),
                        new LatLng(-9.39325472451845,-76.7170857265591),
                        new LatLng(-9.39334271213027,-76.7169023305177),
                        new LatLng(-9.39339067529272,-76.7168312519788),
                        new LatLng(-9.39349487110548,-76.7167685553431),
                        new LatLng(-9.39365761497889,-76.716681048274),
                        new LatLng(-9.39373534830607,-76.7165670543909),
                        new LatLng(-9.39374130234744,-76.7164359614253),
                        new LatLng(-9.39367415398614,-76.7163008451461),
                        new LatLng(-9.39369168533296,-76.7161717638373),
                        new LatLng(-9.39385641379365,-76.7160081490874),
                        new LatLng(-9.39385641379365,-76.7160081490874),
                        new LatLng(-9.39411673752665,-76.7158582806587),
                        new LatLng(-9.39432347478635,-76.7156695201992),
                        new LatLng(-9.39450606503151,-76.7155018821358),
                        new LatLng(-9.39445975592047,-76.7154525965452),
                        new LatLng(-9.39432479790443,-76.7154981940984),
                        new LatLng(-9.39420472491947,-76.7156219109892),
                        new LatLng(-9.3940879596903,-76.7157214879989),
                        new LatLng(-9.39390669234439,-76.7157811671495),
                        new LatLng(-9.39384549805471,-76.7157580330967),
                        new LatLng(-9.39385079053425,-76.7157177999615),
                        new LatLng(-9.39399434901074,-76.7156366631388),
                        new LatLng(-9.39415841576813,-76.7155005410313),
                        new LatLng(-9.39433604440776,-76.715335920453),
                        new LatLng(-9.3944994494446,-76.7151733115315),
                        new LatLng(-9.39458015959628,-76.7150650173425),
                        new LatLng(-9.39472338699128,-76.7147572338581),
                        new LatLng(-9.39485735251497,-76.7144551500677),
                        new LatLng(-9.39495063218234,-76.714310310781),
                        new LatLng(-9.3949499706245,-76.7141363024711),
                        new LatLng(-9.39507037412988,-76.7138603702187),
                        new LatLng(-9.39526454123302,-76.7135710269212),
                        new LatLng(-9.39536542869678,-76.7132890596985),
                        new LatLng(-9.39551064043728,-76.712946742773),
                        new LatLng(-9.39562773596499,-76.712650693953),
                        new LatLng(-9.39571539219571,-76.7125175893306),
                        new LatLng(-9.39592510550289,-76.712396889925),
                        new LatLng(-9.39618675079165,-76.7122946307063),
                        new LatLng(-9.39630880775507,-76.7121937125921),
                        new LatLng(-9.3963590859497,-76.7120103165507),
                        new LatLng(-9.39651918225766,-76.7118379846215),
                        new LatLng(-9.39671070233048,-76.7116964980959),
                        new LatLng(-9.39694059243343,-76.7115134373307),
                        new LatLng(-9.39715956674924,-76.7113276943564),
                        new LatLng(-9.39735274035092,-76.7111225053668),
                        new LatLng(-9.3974334498375,-76.7109562084078),
                        new LatLng(-9.39742914974238,-76.710821762681),
                        new LatLng(-9.39733818617921,-76.7107342556118),
                        new LatLng(-9.39742551120032,-76.7106584832072),
                        new LatLng(-9.39741724178638,-76.7106085270643),
                        new LatLng(-9.39743708837949,-76.7105193436145),
                        new LatLng(-9.39737523316055,-76.7104345187544),
                        new LatLng(-9.39727996948623,-76.7103704810142),
                        new LatLng(-9.39722539132767,-76.7103470116853),
                        new LatLng(-9.39720422161536,-76.7102618515491),
                        new LatLng(-9.39716849772292,-76.7101575806736),
                        new LatLng(-9.39716485917811,-76.7100808024406),
                        new LatLng(-9.39726872307849,-76.7100067064166),
                        new LatLng(-9.39740996470196,-76.7099138349294),
                        new LatLng(-9.3974430423572,-76.7098129168152),
                        new LatLng(-9.39744568856949,-76.7097133398056),
                        new LatLng(-9.39740235684081,-76.709643267095),
                        new LatLng(-9.39734480171188,-76.7095953226089),
                        new LatLng(-9.39724027628085,-76.7096090689301),
                        new LatLng(-9.39720422161536,-76.7095420137047),
                        new LatLng(-9.39717610558921,-76.7094434425234),
                        new LatLng(-9.397142035578,-76.7093069851398),
                        new LatLng(-9.39705801825444,-76.7091286182403),
                        new LatLng(-9.39695878516886,-76.7089777439832),
                        new LatLng(-9.3968535980671,-76.7088275402784),
                        new LatLng(-9.39679471973832,-76.7087588086724),
                        new LatLng(-9.39665347786387,-76.7086947709321),
                        new LatLng(-9.39658864550875,-76.7085318267345),
                        new LatLng(-9.39661510769598,-76.7084453254938),
                        new LatLng(-9.39663429278046,-76.7083024978637),
                        new LatLng(-9.3966462007634,-76.7081486061215),
                        new LatLng(-9.3966296618981,-76.7080366238951),
                        new LatLng(-9.39652315158668,-76.7079826444387),
                        new LatLng(-9.39640539480672,-76.7078424990177),
                        new LatLng(-9.39637529405036,-76.7077697440981),
                        new LatLng(-9.39639745614595,-76.7076886072754),
                        new LatLng(-9.39634783951206,-76.7075638845562),
                        new LatLng(-9.39631410019695,-76.7074361443519),
                        new LatLng(-9.39633560074128,-76.7073214799165),
                        new LatLng(-9.39629061498547,-76.7072497308254),
                        new LatLng(-9.39643648789297,-76.7072822526097),
                        new LatLng(-9.39653439801863,-76.7073342204093),
                        new LatLng(-9.39661080759069,-76.7073167860507),
                        new LatLng(-9.396692509582,-76.7072953283786),
                        new LatLng(-9.39665083164553,-76.7072621360421),
                        new LatLng(-9.39671268699391,-76.7072309553623),
                        new LatLng(-9.39691148405473,-76.7070076614618),
                        new LatLng(-9.39694125398746,-76.7068913206458),
                        new LatLng(-9.39714038169386,-76.7068118602037),
                        new LatLng(-9.39727798482607,-76.7066194117069),
                        new LatLng(-9.39734314782872,-76.7064521089196),
                        new LatLng(-9.39743576527332,-76.7063176631927),
                        new LatLng(-9.39737821014994,-76.7060792818665),
                        new LatLng(-9.39742484964721,-76.7058647051453),
                        new LatLng(-9.39745991196016,-76.7057389765977),
                        new LatLng(-9.39737225617112,-76.7055770382285),
                        new LatLng(-9.39741062625509,-76.7054154351353),
                        new LatLng(-9.39745561186536,-76.7051985114812),
                        new LatLng(-9.39735307112753,-76.7046845331788),
                        new LatLng(-9.39729882375719,-76.7043274641036),
                        new LatLng(-9.39734050161565,-76.7041259631514),
                        new LatLng(-9.39790612919877,-76.7041531205177),
                        new LatLng(-9.39791208316841,-76.7036210373044),
                        new LatLng(-9.39753003657541,-76.7036046087741),
                        new LatLng(-9.39730742395061,-76.7034996673464),
                        new LatLng(-9.39748273553931,-76.7028894647955),
                        new LatLng(-9.39755947567852,-76.7025243490934),
                        new LatLng(-9.39740533382997,-76.7019459977746),
                        new LatLng(-9.39725218424294,-76.7014870047569),
                        new LatLng(-9.39739574130922,-76.7009975016117),
                        new LatLng(-9.39745693497147,-76.7003762349486),
                        new LatLng(-9.39750423601106,-76.7001153901219),
                        new LatLng(-9.39748472019831,-76.7000225186347),
                        new LatLng(-9.39760115350561,-76.7000889033079),
                        new LatLng(-9.39778804211301,-76.7002189904451),
                        new LatLng(-9.39790149833342,-76.7002940922975),
                        new LatLng(-9.39787702090125,-76.700218655169),
                        new LatLng(-9.39807350186196,-76.7002867162227),
                        new LatLng(-9.3982435206504,-76.700424849987),
                        new LatLng(-9.39842412417468,-76.7005405202507),
                        new LatLng(-9.39863317429025,-76.7005301266908),
                        new LatLng(-9.39883197024822,-76.7005753889679),
                        new LatLng(-9.39903076609202,-76.7005958408117),
                        new LatLng(-9.39919416891308,-76.7006112635135),
                        new LatLng(-9.39940818018351,-76.7005448788404),
                        new LatLng(-9.39955901339506,-76.7005311325192),
                        new LatLng(-9.39991856510574,-76.7005566135048),
                        new LatLng(-9.40013356825013,-76.7005408555269),
                        new LatLng(-9.40031747852612,-76.7005257681012),
                        new LatLng(-9.40050965804463,-76.7004332318902),
                        new LatLng(-9.40061782099692,-76.7003698647022),
                        new LatLng(-9.40071738380692,-76.7003591358661),
                        new LatLng(-9.40081330808181,-76.7003440484404),
                        new LatLng(-9.40106535725673,-76.7002887278795),
                        new LatLng(-9.40130384456158,-76.7002699524164),
                        new LatLng(-9.40145203076532,-76.7002163082361),
                        new LatLng(-9.40131409851969,-76.7000315710902),
                        new LatLng(-9.40120626655797,-76.6999759152531),
                        new LatLng(-9.40116723534881,-76.6998947784304),
                        new LatLng(-9.4012942521489,-76.6999299824237),
                        new LatLng(-9.40143218440244,-76.6998753324151),
                        new LatLng(-9.40136503753288,-76.6998190060257),
                        new LatLng(-9.40154365479258,-76.6997606679797),
                        new LatLng(-9.40178114945064,-76.699700653553),
                        new LatLng(-9.40199714375439,-76.6996416449546),
                        new LatLng(-9.40208281373952,-76.699586994946),
                        new LatLng(-9.40217972995194,-76.6994324326515),
                        new LatLng(-9.40249197858824,-76.6992601007223),
                        new LatLng(-9.40261998721682,-76.6992523893713),
                        new LatLng(-9.40256011755406,-76.6990398243069),
                        new LatLng(-9.40267555683928,-76.6987712681293),
                        new LatLng(-9.40254324819969,-76.6987749561667),
                        new LatLng(-9.40264611817135,-76.6984333097934),
                        new LatLng(-9.40281911165602,-76.6981057450175),
                        new LatLng(-9.40281183468517,-76.6979039087891),
                        new LatLng(-9.40278338834314,-76.6977064311504),
                        new LatLng(-9.4029560509879,-76.6973758488893),
                        new LatLng(-9.40305561312511,-76.6973255574703),
                        new LatLng(-9.40276089588499,-76.6971562430262),
                        new LatLng(-9.4025806253955,-76.696954742074),
                        new LatLng(-9.4024390551104,-76.6967421770095),
                        new LatLng(-9.40227962308445,-76.6965842619538),
                        new LatLng(-9.40235702370374,-76.6964729502797),
                        new LatLng(-9.40219329160407,-76.6962711140513),
                        new LatLng(-9.4021889915681,-76.6960327327251),
                        new LatLng(-9.40205039807196,-76.6957477480173),
                        new LatLng(-9.40187178107384,-76.6956203430891),
                        new LatLng(-9.40207851370216,-76.6955643519759),
                        new LatLng(-9.40194322788414,-76.69537961483),
                        new LatLng(-9.40207586752529,-76.6953460872173),
                        new LatLng(-9.40199945915967,-76.6952495276927),
                        new LatLng(-9.40213838344826,-76.6952059417963),
                        new LatLng(-9.40191841996558,-76.6950191929936),
                        new LatLng(-9.40212382947778,-76.6949849948287),
                        new LatLng(-9.4023024463458,-76.694956831634),
                        new LatLng(-9.40242218575014,-76.6948844119906),
                        new LatLng(-9.40217410682773,-76.6946135088801),
                        new LatLng(-9.40206925208305,-76.694504879415),
                        new LatLng(-9.40192305077718,-76.6944445297122),
                        new LatLng(-9.40155754724225,-76.6941149532794),
                        new LatLng(-9.40149569275967,-76.6940770670771),
                        new LatLng(-9.40130417533445,-76.693794094026),
                        new LatLng(-9.40138058385359,-76.6937230154871),
                        new LatLng(-9.40144343067488,-76.6936217620969),
                        new LatLng(-9.40122809757134,-76.6936693713068),
                        new LatLng(-9.40126613645498,-76.6934366896748),
                        new LatLng(-9.40130334842822,-76.6932958737167),
                        new LatLng(-9.40134056034567,-76.6931550577282),
                        new LatLng(-9.40143714599326,-76.6929753497243),
                        new LatLng(-9.40131277542835,-76.6931500285864),
                        new LatLng(-9.4012638210448,-76.693291850388),
                        new LatLng(-9.4012330591651,-76.6934380307793),
                        new LatLng(-9.40119105100238,-76.6936676949262),
                        new LatLng(-9.40120560501209,-76.6937307268381),
                        new LatLng(-9.40140439949248,-76.6936428844928),
                        new LatLng(-9.40135081430267,-76.6937102749943),
                        new LatLng(-9.40126977495668,-76.6937746480107),
                        new LatLng(-9.4012763904142,-76.6938272863626),
                        new LatLng(-9.40146096162826,-76.6941008716821),
                        new LatLng(-9.40155027024488,-76.6941588744521),
                        new LatLng(-9.4019078352531,-76.6944747045636),
                        new LatLng(-9.40204742112275,-76.6945347189903),
                        new LatLng(-9.40213706036006,-76.6946296021342),
                        new LatLng(-9.40236330836864,-76.694885417819),
                        new LatLng(-9.40228226925977,-76.6949041932821),
                        new LatLng(-9.40209670616754,-76.6949229687452),
                        new LatLng(-9.40185259627967,-76.69497795403),
                        new LatLng(-9.40205039807196,-76.6951875016093),
                        new LatLng(-9.40190948911445,-76.6952639445662),
                        new LatLng(-9.40169746402565,-76.6953021660447),
                        new LatLng(-9.40133427566219,-76.6952696442604),
                        new LatLng(-9.4010693265336,-76.6951928660273),
                        new LatLng(-9.40089269366853,-76.6951331868767),
                        new LatLng(-9.40073623789064,-76.6952562332153),
                        new LatLng(-9.40069158348017,-76.6953574866056),
                        new LatLng(-9.40041108749597,-76.6953594982624),
                        new LatLng(-9.40015473778329,-76.6952595859766),
                        new LatLng(-9.39999828167178,-76.695165708661),
                        new LatLng(-9.3999079803321,-76.6951077058911),
                        new LatLng(-9.39970290027903,-76.6950909420847),
                        new LatLng(-9.39949054306375,-76.6949658840894),
                        new LatLng(-9.39931787868986,-76.6949005052447),
                        new LatLng(-9.39918457644191,-76.6947881877422),
                        new LatLng(-9.39905292801763,-76.6947305202484),
                        new LatLng(-9.39885942458952,-76.6948227211833),
                        new LatLng(-9.39877011527852,-76.6949699074029),
                        new LatLng(-9.39888489427769,-76.6946664825082),
                        new LatLng(-9.39879095411981,-76.6943633928894),
                        new LatLng(-9.39863185118865,-76.6937407851219),
                        new LatLng(-9.39851442588184,-76.693754866724),
                        new LatLng(-9.39837616170948,-76.6937706246972),
                        new LatLng(-9.39802156985479,-76.6937686130383),
                        new LatLng(-9.39784427392744,-76.6937676072105),
                        new LatLng(-9.39750192035191,-76.6936693712089),
                        new LatLng(-9.39699037388729,-76.6936026512101),
                        new LatLng(-9.39655531917493,-76.6936319879818),
                        new LatLng(-9.39613382634971,-76.6937324032187),
                        new LatLng(-9.395923451558,-76.693652607452),
                        new LatLng(-9.39575260472846,-76.6934489271287),
                        new LatLng(-9.39561094898133,-76.6932629326836),
                        new LatLng(-9.39551250112157,-76.6930514153662),
                        new LatLng(-9.39535428565165,-76.6928368595664),
                        new LatLng(-9.39509076868023,-76.6926631969592),
                        new LatLng(-9.39492659381968,-76.6925562491695),
                        new LatLng(-9.39459311409827,-76.6924323672194),
                        new LatLng(-9.39433144046001,-76.6923529919451),
                        new LatLng(-9.39420060363423,-76.6923133043529),
                        new LatLng(-9.39404504777444,-76.6921013472559),
                        new LatLng(-9.39396726979719,-76.6919953687788),
                        new LatLng(-9.39365184855923,-76.6919117017223),
                        new LatLng(-9.39339440760506,-76.6918195767827),
                        new LatLng(-9.39313696662705,-76.6917274519801),
                        new LatLng(-9.39292526690136,-76.6916089317968),
                        new LatLng(-9.3927135671362,-76.6914904117584),
                        new LatLng(-9.39263285654953,-76.6917633265256),
                        new LatLng(-9.39269438183452,-76.6920064017176),
                        new LatLng(-9.39257761609616,-76.6921864449977),
                        new LatLng(-9.39246217344362,-76.6924563422799),
                        new LatLng(-9.39218960954974,-76.6929029300808),
                        new LatLng(-9.39193126905912,-76.6926256567239),
                        new LatLng(-9.39192994593193,-76.6926300153136),
                        new LatLng(-9.39224187303145,-76.6929723322391),
                        new LatLng(-9.3923712085757,-76.6933622583746),
                        new LatLng(-9.3924383571896,-76.6936338320374),
                        new LatLng(-9.39264873404347,-76.6939033940434),
                        new LatLng(-9.39257000812884,-76.6941307112574),
                        new LatLng(-9.39286671873053,-76.6944569349288),
                        new LatLng(-9.39247242766361,-76.6944690048694),
                        new LatLng(-9.3927939469231,-76.6945545002818),
                        new LatLng(-9.39285679530315,-76.6951318457722),
                        new LatLng(-9.39300796214951,-76.695406101644),
                        new LatLng(-9.3932302467578,-76.6955770924687),
                        new LatLng(-9.39328052539942,-76.6958583891391),
                        new LatLng(-9.39334701227614,-76.6961604729294),
                        new LatLng(-9.39342276099078,-76.6964732855558),
                        new LatLng(-9.39367713100736,-76.6968259960412),
                        new LatLng(-9.39387692215076,-76.697044596076),
                        new LatLng(-9.39347370116511,-76.6972769424319),
                        new LatLng(-9.39351901806713,-76.6975287348031),
                        new LatLng(-9.39367646944709,-76.6978700459003),
                        new LatLng(-9.39382168189581,-76.6982381790876),
                        new LatLng(-9.39407605161939,-76.6987813264131),
                        new LatLng(-9.39433042115614,-76.6991658881306),
                        new LatLng(-9.39427319629615,-76.699624210596),
                        new LatLng(-9.39411276817004,-76.700166016817),
                        new LatLng(-9.39417197773452,-76.7006585374474),
                        new LatLng(-9.39415742342911,-76.700982414186),
                        new LatLng(-9.39403305024881,-76.7012901976704),
                        new LatLng(-9.39400741482447,-76.7013810575075),
                        new LatLng(-9.39398177937692,-76.7014719173312),
                        new LatLng(-9.39413559196985,-76.7015738412737),
                        new LatLng(-9.39426228057035,-76.7017441615462),
                        new LatLng(-9.39467145466321,-76.7017867416143),
                        new LatLng(-9.39505350441048,-76.7018417268991),
                        new LatLng(-9.39521988611672,-76.702071391046),
                        new LatLng(-9.39555860329947,-76.7023154720664),
                        new LatLng(-9.39576203191696,-76.7026302963495),
                        new LatLng(-9.39593701351022,-76.702972613275),
                        new LatLng(-9.39610008701374,-76.7033494636416),
                        new LatLng(-9.39625687566478,-76.7037514597177),
                        new LatLng(-9.39644144955541,-76.7039190977811),
                        new LatLng(-9.39686319060287,-76.7040116339921),
                        new LatLng(-9.3970996961419,-76.7040921002626),
                        new LatLng(-9.39734480171188,-76.704119592905),
                        new LatLng(-9.39787999788634,-76.7041430622339),
                        new LatLng(-9.39812377980031,-76.7038423195481)
                    ).color(Color.DKGRAY).width(5));
    }
    public void ruta3(GoogleMap googleMap){
        //Polyline ruta3 = googleMap.addPolyline(new PolylineOptions()
           googleMap.addPolyline(new PolylineOptions()
                        .clickable(true)
                .add(
                        new LatLng(-9.39236029278995,-76.7201280221343),
                        new LatLng(-9.39225741974942,-76.7200829274818),
                        new LatLng(-9.39215454670317,-76.7200378328561),
                        new LatLng(-9.39259713218548,-76.719527542591),
                        new LatLng(-9.39245754250547,-76.7194192484021),
                        new LatLng(-9.39242677984332,-76.719289496541),
                        new LatLng(-9.39235003856664,-76.7191171646118),
                        new LatLng(-9.39232390683494,-76.7189636081457),
                        new LatLng(-9.39241387937127,-76.7187751829624),
                        new LatLng(-9.39255446141249,-76.7185609415173),
                        new LatLng(-9.39251708313415,-76.7183684930205),
                        new LatLng(-9.3925243603214,-76.7181297764182),
                        new LatLng(-9.39249889016536,-76.7178991064429),
                        new LatLng(-9.39257298515955,-76.7176751419901),
                        new LatLng(-9.39269570495881,-76.7173713818192),
                        new LatLng(-9.39268710465085,-76.7172268778085),
                        new LatLng(-9.39257496984669,-76.7171584814786),
                        new LatLng(-9.39258886265625,-76.717016659677),
                        new LatLng(-9.39262624092683,-76.7168540507554),
                        new LatLng(-9.39262326389659,-76.716744415462),
                        new LatLng(-9.392506167353,-76.7167018353939),
                        new LatLng(-9.39241718718466,-76.7166220396757),
                        new LatLng(-9.39233283793335,-76.7165415734052),
                        new LatLng(-9.39238708608164,-76.7164942994713),
                        new LatLng(-9.39240296358685,-76.716387681663),
                        new LatLng(-9.39247507391388,-76.7162927985191),
                        new LatLng(-9.39247441235132,-76.7161959037184),
                        new LatLng(-9.39228950556488,-76.7160899564623),
                        new LatLng(-9.39190182947755,-76.7161325365304),
                        new LatLng(-9.39164018095155,-76.7161580175161),
                        new LatLng(-9.39141061811237,-76.7161959037184),
                        new LatLng(-9.39138911726228,-76.7161580175161),
                        new LatLng(-9.3915713782722,-76.7160741984844),
                        new LatLng(-9.39174206181702,-76.7159256711602),
                        new LatLng(-9.39180557195178,-76.7158515751361),
                        new LatLng(-9.39195276987557,-76.715798266232),
                        new LatLng(-9.39206722035285,-76.715761385858),
                        new LatLng(-9.39213701527853,-76.7156567797064),
                        new LatLng(-9.3919378846951,-76.7156882956624),
                        new LatLng(-9.39169906015668,-76.715758703649),
                        new LatLng(-9.39145758919559,-76.71585123986),
                        new LatLng(-9.39130807558461,-76.7158991843461),
                        new LatLng(-9.39102459495479,-76.7159977555274),
                        new LatLng(-9.39079900110229,-76.7160698398947),
                        new LatLng(-9.39059755426821,-76.7161442711949),
                        new LatLng(-9.39046259474734,-76.7163052037358),
                        new LatLng(-9.39033160457386,-76.7164728417992),
                        new LatLng(-9.39011858004458,-76.716585829854),
                        new LatLng(-9.39001471399989,-76.7166106402873),
                        new LatLng(-9.38986983073901,-76.7165841534733),
                        new LatLng(-9.38967731453155,-76.7165573313832),
                        new LatLng(-9.38954665484525,-76.716543585062),
                        new LatLng(-9.38939780197806,-76.7165502905845),
                        new LatLng(-9.38925821100894,-76.7165606841444),
                        new LatLng(-9.38915037529287,-76.7166257277131),
                        new LatLng(-9.38900615323978,-76.7166263982653),
                        new LatLng(-9.38885101523034,-76.7166485264897),
                        new LatLng(-9.38870778540729,-76.7166807129979),
                        new LatLng(-9.38849641380508,-76.7167336866259),
                        new LatLng(-9.38830852782821,-76.7167873308062),
                        new LatLng(-9.38810443326256,-76.716818511486),
                        new LatLng(-9.38798601204941,-76.716834269464),
                        new LatLng(-9.38782161158309,-76.7167729139328),
                        new LatLng(-9.38768036604973,-76.7166780307888),
                        new LatLng(-9.38754838246654,-76.7165613546967),
                        new LatLng(-9.38749347198855,-76.7164936289191),
                        new LatLng(-9.38743591521312,-76.7164282500743),
                        new LatLng(-9.38749016412816,-76.7163608595728),
                        new LatLng(-9.38754606696463,-76.7163598537445),
                        new LatLng(-9.38746105495529,-76.7162183672189),
                        new LatLng(-9.38742334534136,-76.7161606997251),
                        new LatLng(-9.38747130932328,-76.7161623761057),
                        new LatLng(-9.3874405462184,-76.7160792276263),
                        new LatLng(-9.38739192065995,-76.7160141840577),
                        new LatLng(-9.38742334534136,-76.7159219831228),
                        new LatLng(-9.38745741630851,-76.7158522456884),
                        new LatLng(-9.3876525800364,-76.7158207297325),
                        new LatLng(-9.38765621868113,-76.7156403511762),
                        new LatLng(-9.38766316518459,-76.715468019247),
                        new LatLng(-9.38759039228418,-76.7152805998921),
                        new LatLng(-9.38752258115868,-76.7151257023215),
                        new LatLng(-9.38732940207476,-76.714989580214),
                        new LatLng(-9.38720965745156,-76.7149231955409),
                        new LatLng(-9.38738298943418,-76.7149087786674),
                        new LatLng(-9.38735189553528,-76.7148219421505),
                        new LatLng(-9.38723545878271,-76.7147793620824),
                        new LatLng(-9.38729764659862,-76.7147351056337),
                        new LatLng(-9.3871666552287,-76.7146418988704),
                        new LatLng(-9.38705650335662,-76.7144695669412),
                        new LatLng(-9.38698670740752,-76.7143780365586),
                        new LatLng(-9.38683289164072,-76.714297235012),
                        new LatLng(-9.38665327443309,-76.7142439261078),
                        new LatLng(-9.38651103606266,-76.7142147570848),
                        new LatLng(-9.38645050204079,-76.7140135914087),
                        new LatLng(-9.38650276638816,-76.7137701809406),
                        new LatLng(-9.38652360596751,-76.7136447876691),
                        new LatLng(-9.3863340649854,-76.7136773094534),
                        new LatLng(-9.38608597453805,-76.7137735337018),
                        new LatLng(-9.38590635694335,-76.7134375870227),
                        new LatLng(-9.38597979177232,-76.71321362257),
                        new LatLng(-9.38588981756531,-76.7132806777954),
                        new LatLng(-9.38562915686317,-76.712952107191),
                        new LatLng(-9.3857485712447,-76.7129460722208),
                        new LatLng(-9.38571714641143,-76.7129091918468),
                        new LatLng(-9.38604396453805,-76.7126413062214),
                        new LatLng(-9.38591462663207,-76.7125125601887),
                        new LatLng(-9.38620340403859,-76.7121253162622),
                        new LatLng(-9.38617197924658,-76.7120559141039),
                        new LatLng(-9.38637805967286,-76.7118748649954),
                        new LatLng(-9.3865725624347,-76.7116133496165),
                        new LatLng(-9.38637673652445,-76.711345128715),
                        new LatLng(-9.38624475244482,-76.7111067473888),
                        new LatLng(-9.38616007090359,-76.711149662733),
                        new LatLng(-9.38606248863317,-76.7110614851117),
                        new LatLng(-9.38599864665833,-76.7110376805067),
                        new LatLng(-9.38601187815667,-76.7109625786542),
                        new LatLng(-9.38595895216027,-76.7110081762075),
                        new LatLng(-9.38594042805962,-76.710882782936),
                        new LatLng(-9.38586103904564,-76.7107325792312),
                        new LatLng(-9.38585574644405,-76.7105532065033),
                        new LatLng(-9.3857895889175,-76.7103523761034),
                        new LatLng(-9.38574195549056,-76.7100918665528),
                        new LatLng(-9.38595200562264,-76.7099768668413),
                        new LatLng(-9.38592587340842,-76.7097592726349),
                        new LatLng(-9.38606116548355,-76.7095550894737),
                        new LatLng(-9.38592223474552,-76.7093559354543),
                        new LatLng(-9.38579554309541,-76.7091564461588),
                        new LatLng(-9.38571284617087,-76.7090233415365),
                        new LatLng(-9.3854726941902,-76.709119901061),
                        new LatLng(-9.38525437406338,-76.7087712138891),
                        new LatLng(-9.38514951419602,-76.7086424678564),
                        new LatLng(-9.38482170286034,-76.7077181115746),
                        new LatLng(-9.38453060878072,-76.7075363919138),
                        new LatLng(-9.38452531615883,-76.707467995584),
                        new LatLng(-9.38424513787207,-76.7075031995773),
                        new LatLng(-9.38411315298084,-76.7075571790337),
                        new LatLng(-9.38390343252402,-76.7075085639953),
                        new LatLng(-9.38373704538363,-76.7071947455406),
                        new LatLng(-9.3833821079366,-76.7069040611386),
                        new LatLng(-9.3831336845865,-76.7070006206631),
                        new LatLng(-9.38295439625255,-76.7070643231272),
                        new LatLng(-9.38275790671736,-76.707121990621),
                        new LatLng(-9.38261698991142,-76.7070546001195),
                        new LatLng(-9.38234441827453,-76.7071330547332),
                        new LatLng(-9.38222963377657,-76.7071447894016),
                        new LatLng(-9.38211484927822,-76.7071565240621),
                        new LatLng(-9.38187535638809,-76.7072312906384),
                        new LatLng(-9.38171426093941,-76.7069952562451),
                        new LatLng(-9.38167985861623,-76.7068346589803),
                        new LatLng(-9.38196863955005,-76.7067602276802),
                        new LatLng(-9.38184393120377,-76.7065205052495),
                        new LatLng(-9.38201263479148,-76.706804819405),
                        new LatLng(-9.38165769557929,-76.7068604752421),
                        new LatLng(-9.38148469152591,-76.7064024880528),
                        new LatLng(-9.38151115486662,-76.7057637870311),
                        new LatLng(-9.38151644753453,-76.7052679136395),
                        new LatLng(-9.38156606629217,-76.7050519958138),
                        new LatLng(-9.38145822818318,-76.7047180607914),
                        new LatLng(-9.38125049087255,-76.704272814095),
                        new LatLng(-9.38118995593219,-76.7036880925297),
                        new LatLng(-9.38093723071183,-76.7032907903194),
                        new LatLng(-9.38078523176298,-76.7029359003836),
                        new LatLng(-9.38063323246063,-76.7025810107588),
                        new LatLng(-9.38042317910636,-76.7022350057959),
                        new LatLng(-9.38018930852918,-76.701936610043),
                        new LatLng(-9.38003449736731,-76.7015979811549),
                        new LatLng(-9.38006129176631,-76.7012650519866),
                        new LatLng(-9.38013704323012,-76.7009817436337),
                        new LatLng(-9.38010495630211,-76.7007477208971),
                        new LatLng(-9.38010925661222,-76.7005207389593),
                        new LatLng(-9.38002093484769,-76.7003480717539),
                        new LatLng(-9.37979665700749,-76.7000081017613),
                        new LatLng(-9.37984065252444,-76.6996859014034),
                        new LatLng(-9.37996470000462,-76.6993529722094),
                        new LatLng(-9.38002325039987,-76.6990669816732),
                        new LatLng(-9.37995510700107,-76.6986804082989),
                        new LatLng(-9.37993989051214,-76.6983679309487),
                        new LatLng(-9.37993492861343,-76.6981506720185),
                        new LatLng(-9.38005037543836,-76.6979391127824),
                        new LatLng(-9.38016383746669,-76.6976873204112),
                        new LatLng(-9.38000935708663,-76.6972330212593),
                        new LatLng(-9.38002920467678,-76.6967552527785),
                        new LatLng(-9.37980658080879,-76.6964219883084),
                        new LatLng(-9.37986314647082,-76.6959811002016),
                        new LatLng(-9.37987505503006,-76.6954064369201),
                        new LatLng(-9.37977846337101,-76.6948029398918),
                        new LatLng(-9.37964250725741,-76.6939577087759),
                        new LatLng(-9.37936629455105,-76.6934340074658),
                        new LatLng(-9.37908809686041,-76.6931480169296),
                        new LatLng(-9.37880692179773,-76.692974679172),
                        new LatLng(-9.37846554188589,-76.6927547380328),
                        new LatLng(-9.37859289780315,-76.692528091371),
                        new LatLng(-9.37847513493065,-76.6921958327293),
                        new LatLng(-9.37858198158351,-76.6918602213263),
                        new LatLng(-9.37828922829212,-76.6915048286318),
                        new LatLng(-9.37794917105142,-76.6911789402365),
                        new LatLng(-9.3775555246075,-76.6907886788249),
                        new LatLng(-9.37728361058459,-76.690596230328),
                        new LatLng(-9.37699846450682,-76.6904238983988),
                        new LatLng(-9.37647084440618,-76.6902716830372),
                        new LatLng(-9.37615956471035,-76.6898911446332),
                        new LatLng(-9.37571811604998,-76.6894721332542),
                        new LatLng(-9.37546687541591,-76.6890901699662),
                        new LatLng(-9.37502062928809,-76.6888085380196),
                        new LatLng(-9.37470405551959,-76.6884571686387),
                        new LatLng(-9.37503419200317,-76.6882207989692),
                        new LatLng(-9.37535275025621,-76.6877822577953),
                        new LatLng(-9.37561176469501,-76.6868568956851),
                        new LatLng(-9.37615956471035,-76.6859898716211),
                        new LatLng(-9.37570637273045,-76.6843765228986),
                        new LatLng(-9.37601599884791,-76.6860297694802),
                        new LatLng(-9.37550822510218,-76.6868203505873),
                        new LatLng(-9.37526707366238,-76.6876924037933),
                        new LatLng(-9.37495943166465,-76.6881222277879),
                        new LatLng(-9.37448407462223,-76.6884283348917),
                        new LatLng(-9.37492105909527,-76.6888605058193),
                        new LatLng(-9.37521745400378,-76.6891015693545),
                        new LatLng(-9.37545331271773,-76.6894204169511),
                        new LatLng(-9.37560647193702,-76.6898965090513),
                        new LatLng(-9.37595711697933,-76.690297499299),
                        new LatLng(-9.37610597561656,-76.6906441748142),
                        new LatLng(-9.37649201538492,-76.691032089293),
                        new LatLng(-9.37654361964018,-76.6914008930325),
                        new LatLng(-9.37644636546042,-76.6917468979954),
                        new LatLng(-9.37661573326609,-76.6919477283954),
                        new LatLng(-9.37666551813412,-76.6920503228755),
                        new LatLng(-9.37671530297263,-76.6921529173851),
                        new LatLng(-9.37685208719807,-76.6921814158446),
                        new LatLng(-9.37692047930993,-76.6921956650828),
                        new LatLng(-9.37698887142123,-76.6922099143266),
                        new LatLng(-9.37710861955423,-76.6922364011315),
                        new LatLng(-9.37722836768525,-76.6922628879547),
                        new LatLng(-9.3774079897748,-76.6923849284648),
                        new LatLng(-9.37767163381514,-76.6924801468849),
                        new LatLng(-9.37784629374658,-76.6925582662224),
                        new LatLng(-9.378146159385,-76.6926477848709),
                        new LatLng(-9.37844602500094,-76.6927373036742),
                        new LatLng(-9.37844800976895,-76.6927383095026),
                        new LatLng(-9.37906130254074,-76.6927993297576),
                        new LatLng(-9.3797265288035,-76.6928080469369),
                        new LatLng(-9.38045493521218,-76.6930420696735),
                        new LatLng(-9.38109534939222,-76.693447753787),
                        new LatLng(-9.38171161460697,-76.6935966163873),
                        new LatLng(-9.38215322105187,-76.6939540207386),
                        new LatLng(-9.38259780405079,-76.6942517459392),
                        new LatLng(-9.38284722015625,-76.6948783770203),
                        new LatLng(-9.38294612649321,-76.6953149065375),
                        new LatLng(-9.38301063061082,-76.6959653422236),
                        new LatLng(-9.38302022352985,-76.6967311128973),
                        new LatLng(-9.3831694099261,-76.6971337795257),
                        new LatLng(-9.38329047910515,-76.6976185888051),
                        new LatLng(-9.38344297328418,-76.6983146220445),
                        new LatLng(-9.38342709536845,-76.6986525803804),
                        new LatLng(-9.38362093820656,-76.6990297660231),
                        new LatLng(-9.38401523934486,-76.6993603482842),
                        new LatLng(-9.38434205907598,-76.6997013241052),
                        new LatLng(-9.38462157570686,-76.6999038308858),
                        new LatLng(-9.38478796242273,-76.700121089816),
                        new LatLng(-9.38493317859655,-76.7002183198928),
                        new LatLng(-9.38504035404803,-76.7006343975663),
                        new LatLng(-9.38527256741254,-76.7010994255542),
                        new LatLng(-9.38540190555808,-76.701370999217),
                        new LatLng(-9.38565165043402,-76.7016680538654),
                        new LatLng(-9.38569465284483,-76.7020130529999),
                        new LatLng(-9.385776026623,-76.7025699466466),
                        new LatLng(-9.38591231111925,-76.7031466215848),
                        new LatLng(-9.38586699322232,-76.7035244777798),
                        new LatLng(-9.38585409250604,-76.7039097100496),
                        new LatLng(-9.38594373593481,-76.7042151466012),
                        new LatLng(-9.38619513435676,-76.7045333236456),
                        new LatLng(-9.38640783051083,-76.7046047374606),
                        new LatLng(-9.38647795736348,-76.704949401319),
                        new LatLng(-9.38663243486154,-76.7051314562559),
                        new LatLng(-9.38654213003699,-76.7053198814392),
                        new LatLng(-9.38670851583099,-76.7056383937597),
                        new LatLng(-9.3869982849356,-76.7060816287994),
                        new LatLng(-9.38742169141082,-76.7064457386732),
                        new LatLng(-9.38766514989985,-76.7066475749015),
                        new LatLng(-9.38785733643956,-76.7071035504341),
                        new LatLng(-9.38807565492677,-76.7075125873088),
                        new LatLng(-9.38817819841117,-76.707682237029),
                        new LatLng(-9.38807466257032,-76.7077730968594),
                        new LatLng(-9.38811832625139,-76.7078304290771),
                        new LatLng(-9.38802736024296,-76.7081227898597),
                        new LatLng(-9.38800751311067,-76.7084610834717),
                        new LatLng(-9.38790496957576,-76.7088181525468),
                        new LatLng(-9.38771212149081,-76.7092154547572),
                        new LatLng(-9.38759601564522,-76.7094954103231),
                        new LatLng(-9.38729863895729,-76.7097428441047),
                        new LatLng(-9.38697116044064,-76.710036881268),
                        new LatLng(-9.38683355321406,-76.7102092131972),
                        new LatLng(-9.38704790290867,-76.7104871571063),
                        new LatLng(-9.38708362784489,-76.7105773463845),
                        new LatLng(-9.38698902291317,-76.7108080163598),
                        new LatLng(-9.38685174648033,-76.7110480740666),
                        new LatLng(-9.38693742021226,-76.7111966013908),
                        new LatLng(-9.38656693905707,-76.7116669937968),
                        new LatLng(-9.38643462426282,-76.7119647189974),
                        new LatLng(-9.38622622835943,-76.7120891064405),
                        new LatLng(-9.38599170012151,-76.7125631868839),
                        new LatLng(-9.38609622894672,-76.7126979678869),
                        new LatLng(-9.38581009775209,-76.7129806056618)
                ).color(Color.rgb(35, 172, 16)).width(5));
    }
    public void ruta4(GoogleMap googleMap){
        googleMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .add(
                        new LatLng(-9.39237319326398,-76.7201434448361),
                        new LatLng(-9.39213569215212,-76.7200408503413),
                        new LatLng(-9.39257463906549,-76.7195302248001),
                        new LatLng(-9.39244398047195,-76.7194259539246),
                        new LatLng(-9.39241586405931,-76.7192955315113),
                        new LatLng(-9.39233680731033,-76.7191195115447),
                        new LatLng(-9.39230935245186,-76.7189595848321),
                        new LatLng(-9.3924019712428,-76.7187674716114),
                        new LatLng(-9.39254089938276,-76.718560270965),
                        new LatLng(-9.3925055057905,-76.7183658108115),
                        new LatLng(-9.39251377532173,-76.7181358113884),
                        new LatLng(-9.39248995907124,-76.7179034650325),
                        new LatLng(-9.39256074625536,-76.7176855355501),
                        new LatLng(-9.3926305410816,-76.717513538897),
                        new LatLng(-9.39268710465085,-76.7173673585057),
                        new LatLng(-9.39267982746702,-76.7172359302639),
                        new LatLng(-9.39256438484854,-76.7171638458967),
                        new LatLng(-9.3925789392209,-76.7170173302292),
                        new LatLng(-9.39261830217943,-76.7168527096509),
                        new LatLng(-9.39261598671141,-76.7167514562606),
                        new LatLng(-9.39249822860285,-76.7167082056403),
                        new LatLng(-9.39232324527208,-76.7165425792336),
                        new LatLng(-9.39238146279837,-76.7164899408817),
                        new LatLng(-9.39239667874114,-76.7163839936256),
                        new LatLng(-9.39246647360043,-76.7162917926907),
                        new LatLng(-9.39246845828817,-76.7162022739648),
                        new LatLng(-9.3922885132205,-76.7160976678133),
                        new LatLng(-9.39205564299415,-76.7161211371421),
                        new LatLng(-9.39187635927573,-76.7161409184336),
                        new LatLng(-9.39168020558085,-76.7161620408296),
                        new LatLng(-9.39162694966811,-76.7161670699715),
                        new LatLng(-9.39140598716015,-76.7162049561738),
                        new LatLng(-9.39137952457489,-76.7161556705832),
                        new LatLng(-9.39157038592575,-76.7160668224096),
                        new LatLng(-9.39174007712513,-76.7159166187047),
                        new LatLng(-9.39180623351561,-76.7158401757478),
                        new LatLng(-9.39206589722616,-76.7157556861639),
                        new LatLng(-9.3921204761979,-76.7156681790947),
                        new LatLng(-9.39194483111273,-76.7156956717371),
                        new LatLng(-9.39170038328477,-76.7157660797238),
                        new LatLng(-9.39152672267972,-76.715832799673),
                        new LatLng(-9.39129352115885,-76.7159149423241),
                        new LatLng(-9.39107322000346,-76.7159883677959),
                        new LatLng(-9.39083671034886,-76.7160641402006),
                        new LatLng(-9.39060483149579,-76.7161482945084),
                        new LatLng(-9.39033722789041,-76.7164792120456),
                        new LatLng(-9.39011791847752,-76.716593876481),
                        new LatLng(-9.39001471399989,-76.7166193574666),
                        new LatLng(-9.38978812713826,-76.7165814712643),
                        new LatLng(-9.38970162714059,-76.7165724188066),
                        new LatLng(-9.3896151271427,-76.7165633663535),
                        new LatLng(-9.38942128765691,-76.7166280746459),
                        new LatLng(-9.38923538688779,-76.7167061939835),
                        new LatLng(-9.38911630449216,-76.7167869955301),
                        new LatLng(-9.38900350696301,-76.7169251292943),
                        new LatLng(-9.38893139591341,-76.7170411348342),
                        new LatLng(-9.38878055807305,-76.7171088606119),
                        new LatLng(-9.38859829559501,-76.7171447351574),
                        new LatLng(-9.38837236937755,-76.7171883210539),
                        new LatLng(-9.38821855422575,-76.7172034084796),
                        new LatLng(-9.38802802181401,-76.7172342538833),
                        new LatLng(-9.3879221704289,-76.7171853035688),
                        new LatLng(-9.38786659843877,-76.7171400412917),
                        new LatLng(-9.38768003526387,-76.7170257121324),
                        new LatLng(-9.38744550800969,-76.7170002311468),
                        new LatLng(-9.38727647627955,-76.7170102894306),
                        new LatLng(-9.38707998919414,-76.717027388513),
                        new LatLng(-9.38686464715946,-76.7170042544603),
                        new LatLng(-9.38675217968417,-76.7170434817671),
                        new LatLng(-9.38652525990232,-76.7171869799494),
                        new LatLng(-9.3862970168232,-76.7172764986753),
                        new LatLng(-9.38601717075586,-76.7173234373331),
                        new LatLng(-9.38582531398341,-76.7173968628048),
                        new LatLng(-9.38552528947292,-76.7173730581998),
                        new LatLng(-9.38516307651503,-76.7173469066619),
                        new LatLng(-9.38479722450396,-76.7173298075795),
                        new LatLng(-9.38447338230223,-76.7173626646399),
                        new LatLng(-9.38432121936549,-76.7174595594406),
                        new LatLng(-9.38426630837626,-76.7176862061023),
                        new LatLng(-9.38413928533172,-76.7180020362138),
                        new LatLng(-9.38392460304908,-76.718213930726),
                        new LatLng(-9.38357065816339,-76.7183661460876),
                        new LatLng(-9.3831326922159,-76.7185254022479),
                        new LatLng(-9.38278370837984,-76.7186963930726),
                        new LatLng(-9.38248401202797,-76.7187289148569),
                        new LatLng(-9.38215355184301,-76.7187057808041),
                        new LatLng(-9.38200171867946,-76.7187922820448),
                        new LatLng(-9.38190876631778,-76.7189894244074),
                        new LatLng(-9.38181052126784,-76.7192241176962),
                        new LatLng(-9.38165174133032,-76.7194899916648),
                        new LatLng(-9.38155978124993,-76.7195748165249),
                        new LatLng(-9.38141324049589,-76.7195717990398),
                        new LatLng(-9.38116150781449,-76.719495691359),
                        new LatLng(-9.38080259822215,-76.7193773388862),
                        new LatLng(-9.38063422483838,-76.7192982137203),
                        new LatLng(-9.38044004953793,-76.7191932722926),
                        new LatLng(-9.38023098844557,-76.7191436514258),
                        new LatLng(-9.3800067107412,-76.7190081998705),
                        new LatLng(-9.3797731706766,-76.7189180105924),
                        new LatLng(-9.37946950219881,-76.7189039289951),
                        new LatLng(-9.37915987916342,-76.7188512906432),
                        new LatLng(-9.37898588148164,-76.7187577486038),
                        new LatLng(-9.37886348762278,-76.7186937108635),
                        new LatLng(-9.37869180535338,-76.7186943814158),
                        new LatLng(-9.37840963758538,-76.7186769470572),
                        new LatLng(-9.37815691033925,-76.7186635360121),
                        new LatLng(-9.37800805258194,-76.7185713350772),
                        new LatLng(-9.37781189103977,-76.718491539359),
                        new LatLng(-9.37759555086295,-76.7184050381183),
                        new LatLng(-9.37749035788419,-76.7183906212449),
                        new LatLng(-9.37718337944938,-76.7184321954846),
                        new LatLng(-9.37696604649242,-76.7184114083647),
                        new LatLng(-9.37667329183747,-76.7183611169457),
                        new LatLng(-9.37640633907238,-76.7183943092823),
                        new LatLng(-9.37613310096136,-76.7184700816869),
                        new LatLng(-9.37593594596796,-76.7185663059353),
                        new LatLng(-9.37566800024366,-76.7186370491981),
                        new LatLng(-9.37540203909791,-76.7187205329537),
                        new LatLng(-9.3750682641875,-76.7188355326652),
                        new LatLng(-9.37473515055249,-76.7189371213316),
                        new LatLng(-9.37441196055335,-76.7191138118505),
                        new LatLng(-9.3741400440671,-76.7192713916301),
                        new LatLng(-9.37390583845562,-76.7194293066859),
                        new LatLng(-9.37373845412233,-76.7196271196007),
                        new LatLng(-9.37358463248004,-76.719775982201),
                        new LatLng(-9.37337391325942,-76.7199570313096),
                        new LatLng(-9.3731655095088,-76.7201424390077),
                        new LatLng(-9.37313077553821,-76.7201769724488),
                        new LatLng(-9.3732660726041,-76.7203560099005),
                        new LatLng(-9.37338185244744,-76.7205075547099),
                        new LatLng(-9.3729055008445,-76.7206916213035),
                        new LatLng(-9.37271760642134,-76.7204301059246),
                        new LatLng(-9.3726087731614,-76.7205843329429),
                        new LatLng(-9.37242550978337,-76.7206238955259),
                        new LatLng(-9.37231932283595,-76.7205880209803),
                        new LatLng(-9.37226275594415,-76.7204428464174),
                        new LatLng(-9.37227234916038,-76.720254085958),
                        new LatLng(-9.37231336842672,-76.7200750485062),
                        new LatLng(-9.37197380152603,-76.7197536863369),
                        new LatLng(-9.37190226584785,-76.7196491639837),
                        new LatLng(-9.37183073013903,-76.7195446416735),
                        new LatLng(-9.37161074742067,-76.7194373533129),
                        new LatLng(-9.37137620930123,-76.7192908376455),
                        new LatLng(-9.37112513094134,-76.7191268876194),
                        new LatLng(-9.37087272919234,-76.7190377041697),
                        new LatLng(-9.37070005056371,-76.7191580682992),
                        new LatLng(-9.37047113546771,-76.7191392928361),
                        new LatLng(-9.37028059330739,-76.7190977185964),
                        new LatLng(-9.37019160743628,-76.7189190164208),
                        new LatLng(-9.36999775709535,-76.7187929525971),
                        new LatLng(-9.36975230138894,-76.7186440899968),
                        new LatLng(-9.36955712759019,-76.7185106500983),
                        new LatLng(-9.36944101566805,-76.7184288427233),
                        new LatLng(-9.36930968675205,-76.718433201313),
                        new LatLng(-9.36940198088208,-76.7187041044235),
                        new LatLng(-9.36933582021789,-76.7190293222665),
                        new LatLng(-9.36923161714622,-76.7194413766264),
                        new LatLng(-9.36928520730126,-76.71963583678),
                        new LatLng(-9.36926138945559,-76.7199094220995),
                        new LatLng(-9.36905529886165,-76.7198926582932),
                        new LatLng(-9.36891338407978,-76.7199127748608),
                        new LatLng(-9.36870034639624,-76.7201172932982),
                        new LatLng(-9.36842908704486,-76.7200656607747),
                        new LatLng(-9.36818793068747,-76.7201917245984),
                        new LatLng(-9.36792924151437,-76.7204280942678),
                        new LatLng(-9.36770264022616,-76.720708720386),
                        new LatLng(-9.36743501895231,-76.7208274081349),
                        new LatLng(-9.36726134619826,-76.721081547439),
                        new LatLng(-9.36717831405087,-76.7213497683405),
                        new LatLng(-9.36694774260523,-76.7215153947472),
                        new LatLng(-9.36682964497624,-76.7217276245355),
                        new LatLng(-9.36655970738777,-76.7218882218003),
                        new LatLng(-9.36643400108626,-76.7221762239933),
                        new LatLng(-9.36606812932874,-76.7219361662864),
                        new LatLng(-9.36614487642013,-76.7216203361749),
                        new LatLng(-9.36582994859235,-76.7215301468968),
                        new LatLng(-9.36609856352218,-76.7216394469141),
                        new LatLng(-9.36603074819619,-76.7219401895999),
                        new LatLng(-9.36593249865166,-76.7223230749368),
                        new LatLng(-9.36580679212315,-76.7224602028727),
                        new LatLng(-9.3656843936175,-76.7227069661021),
                        new LatLng(-9.36557291169778,-76.7229758575558),
                        new LatLng(-9.36549054076082,-76.7231200262904),
                        new LatLng(-9.36527915501396,-76.7232534661889),
                        new LatLng(-9.36516568812027,-76.7234110459685),
                        new LatLng(-9.36506512267914,-76.7237694561481),
                        new LatLng(-9.36496323397882,-76.7241282016038),
                        new LatLng(-9.36493081484064,-76.7245244979858),
                        new LatLng(-9.3649291608029,-76.7246076464653),
                        new LatLng(-9.36469958028769,-76.7246482148766),
                        new LatLng(-9.36461489348872,-76.7246331274509),
                        new LatLng(-9.36446338346097,-76.724498681724),
                        new LatLng(-9.36428540871515,-76.7243360728025),
                        new LatLng(-9.36397577213712,-76.7241905629634),
                        new LatLng(-9.3637547920174,-76.7240178957581),
                        new LatLng(-9.36330985407452,-76.7238234356045),
                        new LatLng(-9.36298169130103,-76.7237379401922),
                        new LatLng(-9.36296614325871,-76.7234187573194),
                        new LatLng(-9.36294034012311,-76.7231277376413),
                        new LatLng(-9.36325328571531,-76.7230251431465),
                        new LatLng(-9.36338759420226,-76.723052971065),
                        new LatLng(-9.36343754636029,-76.7229597643017),
                        new LatLng(-9.36348022071475,-76.7228427529335),
                        new LatLng(-9.36356027646636,-76.7228903621435),
                        new LatLng(-9.36346500350368,-76.7228581756353),
                        new LatLng(-9.36337336941424,-76.7230201140046),
                        new LatLng(-9.36321259618808,-76.7229902744293),
                        new LatLng(-9.36290990565301,-76.7230932042002),
                        new LatLng(-9.36292479207893,-76.7234160751104),
                        new LatLng(-9.36293570879087,-76.723724193871),
                        new LatLng(-9.36269686428773,-76.7236487567424),
                        new LatLng(-9.36240112031259,-76.723304092884),
                        new LatLng(-9.3618751320839,-76.7229909449815),
                        new LatLng(-9.3614139820008,-76.7227948084473),
                        new LatLng(-9.36095977834378,-76.7226596921682),
                        new LatLng(-9.36066998749935,-76.7224246636033),
                        new LatLng(-9.36050755896375,-76.7222053930163),
                        new LatLng(-9.36013870365704,-76.7220089212059),
                        new LatLng(-9.3597645549595,-76.7219760641455),
                        new LatLng(-9.35946020735766,-76.7219334840774),
                        new LatLng(-9.35908109573312,-76.7217423766851),
                        new LatLng(-9.35867849593749,-76.7215636745095),
                        new LatLng(-9.35846776767451,-76.7213688790798),
                        new LatLng(-9.35835231367407,-76.7213232815265),
                        new LatLng(-9.35793648046652,-76.7211931943893),
                        new LatLng(-9.35769200868274,-76.7210255563259),
                        new LatLng(-9.35715906621461,-76.7206272482872),
                        new LatLng(-9.35670088721376,-76.720214523375),
                        new LatLng(-9.35640613058434,-76.7195553705096),
                        new LatLng(-9.35597772403889,-76.7189719900488),
                        new LatLng(-9.35565716312031,-76.7186062037944),
                        new LatLng(-9.35529558063563,-76.7183245718479),
                        new LatLng(-9.35499189075603,-76.7181726917624),
                        new LatLng(-9.35446589131753,-76.7176855355501),
                        new LatLng(-9.35422373281806,-76.7174666002392),
                        new LatLng(-9.35391904954847,-76.717407926917),
                        new LatLng(-9.35373941531333,-76.7172466591),
                        new LatLng(-9.35341686742072,-76.7170682922005),
                        new LatLng(-9.35332622312719,-76.7170511931136),
                        new LatLng(-9.35323557883285,-76.7170340940356),
                        new LatLng(-9.35313534075922,-76.7170964553952),
                        new LatLng(-9.35323127819003,-76.7170327529311),
                        new LatLng(-9.35308439466453,-76.7169794440269),
                        new LatLng(-9.35305296687512,-76.7169197648763),
                        new LatLng(-9.3528217244215,-76.7168268933892),
                        new LatLng(-9.35275324486711,-76.7166773602366),
                        new LatLng(-9.35259974475419,-76.7166300863027),
                        new LatLng(-9.3525977598385,-76.7167937010526),
                        new LatLng(-9.35259478246494,-76.7168962955474),
                        new LatLng(-9.35234600627248,-76.7167564854026),
                        new LatLng(-9.35245616915624,-76.7169972136616),
                        new LatLng(-9.35243003442121,-76.717175245285),
                        new LatLng(-9.3525405280977,-76.7173861339688),
                        new LatLng(-9.35264043552349,-76.7176670953631),
                        new LatLng(-9.35264936764294,-76.7179001122713),
                        new LatLng(-9.35274398193118,-76.7182347178459),
                        new LatLng(-9.35268145710722,-76.7185368016362),
                        new LatLng(-9.35279327398026,-76.7188080400228),
                        new LatLng(-9.35276548517493,-76.7190025001764),
                        new LatLng(-9.35291666948185,-76.7192586511373),
                        new LatLng(-9.35314427286597,-76.7194219306111),
                        new LatLng(-9.35313732567185,-76.7197411134839),
                        new LatLng(-9.35305693670129,-76.7200227454304),
                        new LatLng(-9.3528677082693,-76.7204679921269),
                        new LatLng(-9.35280386019122,-76.7209018394351),
                        new LatLng(-9.35281378476372,-76.7213105410337),
                        new LatLng(-9.3526301801268,-76.7217816039919),
                        new LatLng(-9.3524422747404,-76.7221299558877),
                        new LatLng(-9.35236883281883,-76.7228544875979),
                        new LatLng(-9.35225999318582,-76.7234489321708),
                        new LatLng(-9.35225139187598,-76.7237895727157),
                        new LatLng(-9.35202974266459,-76.7240601405501),
                        new LatLng(-9.35211674826739,-76.7244732007384),
                        new LatLng(-9.35192222620521,-76.7248530685901),
                        new LatLng(-9.35167080297051,-76.7252607643604),
                        new LatLng(-9.35142566514173,-76.7255765944719),
                        new LatLng(-9.35137438797621,-76.7259316518902),
                        new LatLng(-9.35120368458367,-76.7260221764445),
                        new LatLng(-9.35117721893391,-76.7264446243643),
                        new LatLng(-9.35138795161428,-76.7274330183863),
                        new LatLng(-9.35089072814648,-76.7270746082067),
                        new LatLng(-9.35100089149104,-76.727503426373),
                        new LatLng(-9.35070249099953,-76.7273170128464),
                        new LatLng(-9.35082092492811,-76.7276291549205),
                        new LatLng(-9.35042592446181,-76.7274222895503),
                        new LatLng(-9.35054038875219,-76.727740131214),
                        new LatLng(-9.35065485275991,-76.7280579730868),
                        new LatLng(-9.35100022984943,-76.7284351587295),
                        new LatLng(-9.35147065671299,-76.7288683354854),
                        new LatLng(-9.35166484820749,-76.7292300984263),
                        new LatLng(-9.35145874717998,-76.7289032042026),
                        new LatLng(-9.35098170388399,-76.7284562811255),
                        new LatLng(-9.3506373192395,-76.7280727252364),
                        new LatLng(-9.3505106147168,-76.7277320846915),
                        new LatLng(-9.35039284232536,-76.7274045199155),
                        new LatLng(-9.3504159998212,-76.7273864150047),
                        new LatLng(-9.35078916611322,-76.7275889217853),
                        new LatLng(-9.35066841642601,-76.7272955551743),
                        new LatLng(-9.35069785950429,-76.7272828146815),
                        new LatLng(-9.35096516284257,-76.7274491116404),
                        new LatLng(-9.35084838306781,-76.7270132526755),
                        new LatLng(-9.35135255382602,-76.72736864537),
                        new LatLng(-9.35114612179288,-76.7264553532004),
                        new LatLng(-9.35115339984742,-76.726007759571),
                        new LatLng(-9.3513495764418,-76.7258387804031),
                        new LatLng(-9.3513945680229,-76.7255856469273),
                        new LatLng(-9.35162548060492,-76.7252269014716),
                        new LatLng(-9.35187161075282,-76.7248282581567),
                        new LatLng(-9.35207605743685,-76.7244571074843),
                        new LatLng(-9.35197218001048,-76.7240634933114),
                        new LatLng(-9.35220375384844,-76.723779849708),
                        new LatLng(-9.35220143811081,-76.7234355211257),
                        new LatLng(-9.35230465382963,-76.7228454351425),
                        new LatLng(-9.35237544920878,-76.7221473902463),
                        new LatLng(-9.3526096693325,-76.7217189073562),
                        new LatLng(-9.35277177073823,-76.7212753370404),
                        new LatLng(-9.35273935046312,-76.7208609357476),
                        new LatLng(-9.35282238605965,-76.7204958200454),
                        new LatLng(-9.35300499813843,-76.7199637368321),
                        new LatLng(-9.35308671039627,-76.7197122797369),
                        new LatLng(-9.35309795823592,-76.7194678634405),
                        new LatLng(-9.35290475999836,-76.7193330824375),
                        new LatLng(-9.35266127713518,-76.7189874127507),
                        new LatLng(-9.35269766724784,-76.718829832971),
                        new LatLng(-9.35261330834449,-76.7185720056295),
                        new LatLng(-9.35267087089253,-76.7182407528162),
                        new LatLng(-9.3525779106809,-76.7179333046078),
                        new LatLng(-9.35255045267771,-76.7176439613103),
                        new LatLng(-9.35244425965698,-76.7173901572823),
                        new LatLng(-9.35235229184336,-76.7171910032629),
                        new LatLng(-9.35240389968421,-76.7169968783855),
                        new LatLng(-9.35221566335593,-76.7166716605424),
                        new LatLng(-9.35256004643812,-76.7167843133211),
                        new LatLng(-9.35250976189789,-76.7165663838386),
                        new LatLng(-9.35223485089494,-76.7163461074232),
                        new LatLng(-9.35188980585094,-76.7160212248563),
                        new LatLng(-9.35166749476884,-76.7157952487468),
                        new LatLng(-9.35147628065899,-76.715476065874),
                        new LatLng(-9.35111634793183,-76.7153134569525),
                        new LatLng(-9.35077064013654,-76.7150854691863),
                        new LatLng(-9.35000677356041,-76.7143877595663),
                        new LatLng(-9.34959688517407,-76.7139203846454),
                        new LatLng(-9.34937920413986,-76.713670939207),
                        new LatLng(-9.34924753682207,-76.7132662609219),
                        new LatLng(-9.348957074573,-76.7129852995276),
                        new LatLng(-9.34858026726222,-76.712682545185),
                        new LatLng(-9.3483162703256,-76.7123043537139),
                        new LatLng(-9.34807807742898,-76.7120475322008),
                        new LatLng(-9.34770457740593,-76.7118296027183),
                        new LatLng(-9.34756199226043,-76.7116740345954),
                        new LatLng(-9.34666115714873,-76.7110132053494),
                        new LatLng(-9.34570044023389,-76.7102755978703),
                        new LatLng(-9.34499346469517,-76.7098098993301),
                        new LatLng(-9.34442411180222,-76.7095436900854),
                        new LatLng(-9.34352657685031,-76.708916388452),
                        new LatLng(-9.34225354874166,-76.7080906033515)
                        ).color(Color.RED).width(5));
    }
    public void ruta5(GoogleMap googleMap){
        Polyline ruta4 = googleMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .add(
                        new LatLng(-9.34451178096027,-76.7095802351832),
                        new LatLng(-9.34460507415327,-76.7094806581735),
                        new LatLng(-9.34458919446262,-76.7093492299318),
                        new LatLng(-9.34441815691522,-76.7091953381896),
                        new LatLng(-9.34427689373244,-76.7090454697608),
                        new LatLng(-9.34410221693074,-76.7088728025555),
                        new LatLng(-9.34390438211385,-76.7086914181709),
                        new LatLng(-9.34374359988893,-76.7085817828774),
                        new LatLng(-9.34352426105496,-76.7084319144487),
                        new LatLng(-9.34333403495585,-76.7082991451025),
                        new LatLng(-9.34316167347892,-76.7081848159432),
                        new LatLng(-9.34298666528936,-76.7080751806497),
                        new LatLng(-9.34285334141107,-76.7079179361462),
                        new LatLng(-9.34270876930669,-76.7077623680233),
                        new LatLng(-9.34260720488627,-76.7076245695352),
                        new LatLng(-9.34244377543096,-76.7075353860855),
                        new LatLng(-9.34230912802858,-76.7074592784047),
                        new LatLng(-9.34220028525003,-76.7074448615312),
                        new LatLng(-9.34201469003856,-76.7073617130517),
                        new LatLng(-9.34187607251662,-76.7072996869683),
                        new LatLng(-9.34174076323557,-76.7072547599673),
                        new LatLng(-9.34155880690676,-76.7071226611733),
                        new LatLng(-9.34135501570567,-76.707018725574),
                        new LatLng(-9.34120316471693,-76.706913113594),
                        new LatLng(-9.34103741879092,-76.7068158835172),
                        new LatLng(-9.34087630441199,-76.7066747322678),
                        new LatLng(-9.34070857334694,-76.7065158113837),
                        new LatLng(-9.34050709746382,-76.7063166573643),
                        new LatLng(-9.34032447882867,-76.7061399668455),
                        new LatLng(-9.3401494692135,-76.7059699818491),
                        new LatLng(-9.33997214369143,-76.7058455944061),
                        new LatLng(-9.33980871299974,-76.7057309299707),
                        new LatLng(-9.33966645545266,-76.7056494578719),
                        new LatLng(-9.33951162624237,-76.7054865136742),
                        new LatLng(-9.33935415030834,-76.7053376510739),
                        new LatLng(-9.33919303515089,-76.7052152752876),
                        new LatLng(-9.33903456657634,-76.7051123455166),
                        new LatLng(-9.33884235303111,-76.7050570249557),
                        new LatLng(-9.3386584101932,-76.7049638181924),
                        new LatLng(-9.33848273807568,-76.7049108445644),
                        new LatLng(-9.33827894507507,-76.7048729583621),
                        new LatLng(-9.33805497113659,-76.7049235850572),
                        new LatLng(-9.33784522288641,-76.7049701884388),
                        new LatLng(-9.33760966949594,-76.7049842700362),
                        new LatLng(-9.33739462763807,-76.705012768507),
                        new LatLng(-9.33723781241496,-76.7050771415233),
                        new LatLng(-9.33701218363681,-76.705057695508),
                        new LatLng(-9.3368034272618,-76.7050295323133),
                        new LatLng(-9.33662643087125,-76.7050362378358),
                        new LatLng(-9.3363842599632,-76.7049983516335),
                        new LatLng(-9.3361394422075,-76.704957447946),
                        new LatLng(-9.33608204237503,-76.7047973535691),
                        new LatLng(-9.33602464247096,-76.7046372592449),
                        new LatLng(-9.33594193370359,-76.7044424638152),
                        new LatLng(-9.33590057931255,-76.704217493534),
                        new LatLng(-9.3358562473999,-76.7039687186479),
                        new LatLng(-9.33584301399205,-76.7037638649344),
                        new LatLng(-9.3357245749694,-76.7034772038459),
                        new LatLng(-9.33567164131498,-76.7032891139388),
                        new LatLng(-9.33567594217469,-76.7030433565378),
                        new LatLng(-9.33555551808243,-76.7028180509805),
                        new LatLng(-9.33535403921961,-76.7027463018894),
                        new LatLng(-9.33544402650935,-76.7029377445578),
                        new LatLng(-9.33559720334985,-76.7030695080757),
                        new LatLng(-9.33563359524589,-76.7032995074987),
                        new LatLng(-9.33567825802223,-76.7035110667348),
                        new LatLng(-9.33570704069727,-76.7037129029631),
                        new LatLng(-9.33573549253462,-76.7038621008396),
                        new LatLng(-9.33566436293687,-76.7040294036269),
                        new LatLng(-9.33552739706588,-76.7041343450546),
                        new LatLng(-9.33543112392143,-76.7042493447661),
                        new LatLng(-9.3353747164569,-76.7043469101348),
                        new LatLng(-9.33531830896577,-76.7044444754719),
                        new LatLng(-9.33522898331516,-76.7045105248689),
                        new LatLng(-9.33515388358376,-76.7045675218105),
                        new LatLng(-9.33511848414529,-76.7046231776475),
                        new LatLng(-9.33504371522606,-76.7045638337731),
                        new LatLng(-9.33497457050329,-76.7045199126005),
                        new LatLng(-9.33486771045022,-76.7044555395841),
                        new LatLng(-9.33473471430123,-76.7043673619627),
                        new LatLng(-9.33465696777273,-76.7042617499828),
                        new LatLng(-9.33457789788128,-76.704161837697),
                        new LatLng(-9.33450345968256,-76.7040619254112),
                        new LatLng(-9.33441810386179,-76.704045496881),
                        new LatLng(-9.3343456506487,-76.7040414735674),
                        new LatLng(-9.33427849080758,-76.7039090394973),
                        new LatLng(-9.33419578162587,-76.7037739232182),
                        new LatLng(-9.33407932706474,-76.7036250606179),
                        new LatLng(-9.3339873544009,-76.7035308480262),
                        new LatLng(-9.33384244778058,-76.7034460231661),
                        new LatLng(-9.33371143626379,-76.703485250473),
                        new LatLng(-9.33354833347332,-76.7035469412803),
                        new LatLng(-9.33337795218069,-76.7036552354693),
                        new LatLng(-9.33320029237521,-76.7036512121558),
                        new LatLng(-9.33305207704923,-76.7036002501845),
                        new LatLng(-9.33294670516311,-76.7035680636666),
                        new LatLng(-9.3328413332741,-76.7035358771681),
                        new LatLng(-9.3328413332741,-76.7035358771681),
                        new LatLng(-9.33269245611913,-76.7034352943301),
                        new LatLng(-9.33251678099449,-76.7033333703875),
                        new LatLng(-9.33237518214786,-76.7032777145504),
                        new LatLng(-9.33221869550514,-76.7032099887728),
                        new LatLng(-9.33204037343132,-76.7031506448984),
                        new LatLng(-9.33191465466025,-76.7030953243374),
                        new LatLng(-9.3317806648675,-76.7031164467334),
                        new LatLng(-9.33164402830977,-76.7031687498092),
                        new LatLng(-9.33157819130522,-76.7032257467508),
                        new LatLng(-9.33157786046597,-76.7030617967247),
                        new LatLng(-9.33150044407277,-76.7029763013124),
                        new LatLng(-9.33141541835617,-76.702911928296),
                        new LatLng(-9.3315143393241,-76.7029608786106),
                        new LatLng(-9.33160498928345,-76.7030540853738),
                        new LatLng(-9.33159274823191,-76.7031902074813),
                        new LatLng(-9.33163774236515,-76.7031566798686),
                        new LatLng(-9.33177934151127,-76.7031006887555),
                        new LatLng(-9.33191465466025,-76.7030802369117),
                        new LatLng(-9.33204467433584,-76.7031375691294),
                        new LatLng(-9.33221968802107,-76.7031969130039),
                        new LatLng(-9.33238279143315,-76.7032599449157),
                        new LatLng(-9.33253431542711,-76.7033202946186),
                        new LatLng(-9.3327037046175,-76.7034175246953),
                        new LatLng(-9.3328446416546,-76.7035174369812),
                        new LatLng(-9.33295514154525,-76.7035519704222),
                        new LatLng(-9.33305770129265,-76.7035821452736),
                        new LatLng(-9.33322146598807,-76.7036388069391),
                        new LatLng(-9.33337199710487,-76.7036381363868),
                        new LatLng(-9.33353079909171,-76.7035372182726),
                        new LatLng(-9.33369687942555,-76.7034701630473),
                        new LatLng(-9.33382094336834,-76.7034275829792),
                        new LatLng(-9.3339933094662,-76.7033206298947),
                        new LatLng(-9.3341719613779,-76.7032609507441),
                        new LatLng(-9.33427551327739,-76.7032384872436),
                        new LatLng(-9.33432811630719,-76.7031824961304),
                        new LatLng(-9.33448592534899,-76.7031493037939),
                        new LatLng(-9.33455110013158,-76.703110076487),
                        new LatLng(-9.33460800399269,-76.7030319571495),
                        new LatLng(-9.33466953955299,-76.7029471322894),
                        new LatLng(-9.33473008259364,-76.7028415203094),
                        new LatLng(-9.33467086289824,-76.7027164623141),
                        new LatLng(-9.33461395904738,-76.7026085034012),
                        new LatLng(-9.33459443970106,-76.7025317251682),
                        new LatLng(-9.334549115113,-76.702436171472),
                        new LatLng(-9.33460866566544,-76.7023443058133),
                        new LatLng(-9.3346745020977,-76.7022822797298),
                        new LatLng(-9.33471320994402,-76.7022175714373),
                        new LatLng(-9.33480237030059,-76.7021692916874),
                        new LatLng(-9.33489153065064,-76.7021210119128),
                        new LatLng(-9.33493917104661,-76.7019795253872),
                        new LatLng(-9.33503445181902,-76.7019084468483),
                        new LatLng(-9.33523063749406,-76.7018675431609),
                        new LatLng(-9.33523063749406,-76.7018675431609),
                        new LatLng(-9.33548074925694,-76.701883636415),
                        new LatLng(-9.3356792505283,-76.7019044235348),
                        new LatLng(-9.33578611033257,-76.701865196228),
                        new LatLng(-9.33590355682886,-76.7017461732029),
                        new LatLng(-9.33593366282578,-76.7016449198126),
                        new LatLng(-9.33583970564001,-76.7015456780791),
                        new LatLng(-9.33571597325111,-76.7014246433973),
                        new LatLng(-9.33567362632717,-76.7013005912303),
                        new LatLng(-9.33574872594656,-76.7011946439742),
                        new LatLng(-9.33576989940461,-76.7010736092925),
                        new LatLng(-9.3357341691934,-76.7009036242961),
                        new LatLng(-9.33569182227167,-76.7007135227322),
                        new LatLng(-9.33559985003333,-76.7006186395883),
                        new LatLng(-9.33556014977905,-76.7004868760704),
                        new LatLng(-9.33545196656322,-76.7003383487462),
                        new LatLng(-9.33547810257257,-76.7002012208104),
                        new LatLng(-9.3356266477024,-76.7001522704958),
                        new LatLng(-9.33580728378834,-76.7000791803002),
                        new LatLng(-9.3360262966461,-76.7000181600451),
                        new LatLng(-9.33632570221837,-76.7002018913626),
                        new LatLng(-9.33661617500287,-76.700448654592),
                        new LatLng(-9.33706743292591,-76.7008157819509),
                        new LatLng(-9.33732482171413,-76.7010886967182),
                        new LatLng(-9.33765400118552,-76.7014729231595),
                        new LatLng(-9.33796299952192,-76.7016680538654),
                        new LatLng(-9.3382336209662,-76.7019681259989),
                        new LatLng(-9.33857867954443,-76.7021595686674),
                        new LatLng(-9.33879769066034,-76.7022313177585),
                        new LatLng(-9.33902960409371,-76.7023845389485),
                        new LatLng(-9.33931213465844,-76.7025065794587),
                        new LatLng(-9.33954636357118,-76.7025337368249),
                        new LatLng(-9.33976967488796,-76.7026145383715),
                        new LatLng(-9.34015972497788,-76.7024888098239),
                        new LatLng(-9.34035259945808,-76.702343635261),
                        new LatLng(-9.34070394171875,-76.7021458223462),
                        new LatLng(-9.34101657648326,-76.7021126300096),
                        new LatLng(-9.34119290898327,-76.7019607499241),
                        new LatLng(-9.34142118169313,-76.7021984606981),
                        new LatLng(-9.34160082228568,-76.7025159671902),
                        new LatLng(-9.34180957578605,-76.7029786482453),
                        new LatLng(-9.34199417861875,-76.7035556584596),
                        new LatLng(-9.34218407462053,-76.7042060941457),
                        new LatLng(-9.3423488275138,-76.7048615589737),
                        new LatLng(-9.34265616754216,-76.7053812369704),
                        new LatLng(-9.34274846874636,-76.7058539763092),
                        new LatLng(-9.34278452906681,-76.7062747478485),
                        new LatLng(-9.34243054227332,-76.7067481577396),
                        new LatLng(-9.34251821193327,-76.7071947455406),
                        new LatLng(-9.34268263383977,-76.7075430974364),
                        new LatLng(-9.34274681460314,-76.7077013477683),
                        new LatLng(-9.34291752214297,-76.7078790441155),
                        new LatLng(-9.34303761286212,-76.7080265656113),
                        new LatLng(-9.34339623063098,-76.7082471773028),
                        new LatLng(-9.34379587066154,-76.7085371911525),
                        new LatLng(-9.34397617168414,-76.7086689546704),
                        new LatLng(-9.34415316434029,-76.7088399454951),
                        new LatLng(-9.34446414187335,-76.7091209068894),
                        new LatLng(-9.3446255854193,-76.7093029618263),
                        new LatLng(-9.34487569043692,-76.7096261680126),
                        new LatLng(-9.34505003602416,-76.7097579315304),
                        new LatLng(-9.34552841075438,-76.710081808269),
                        new LatLng(-9.34570970335729,-76.7102108895778),
                        new LatLng(-9.34627078351551,-76.7106464132666),
                        new LatLng(-9.34703267334395,-76.7112053185701),
                        new LatLng(-9.34769796092716,-76.7116985097527),
                        new LatLng(-9.34777239630614,-76.7118363082408),
                        new LatLng(-9.34817236213671,-76.7120495438575),
                        new LatLng(-9.34839302133533,-76.712330840528),
                        new LatLng(-9.34865635656769,-76.7126734927296),
                        new LatLng(-9.34906459194857,-76.7130188271403),
                        new LatLng(-9.34930311499244,-76.7132548615336),
                        new LatLng(-9.34944966929198,-76.7136789858341),
                        new LatLng(-9.34998560096794,-76.7143032699823),
                        new LatLng(-9.35051094553808,-76.7147988080978),
                        new LatLng(-9.35092248695209,-76.7151307314634),
                        new LatLng(-9.35113950537953,-76.7152916640043),
                        new LatLng(-9.35152193386431,-76.7154435440897),
                        new LatLng(-9.35172406501303,-76.7157540097832),
                        new LatLng(-9.35224841449945,-76.716268658638),
                        new LatLng(-9.35255574578692,-76.7165412381291),
                        new LatLng(-9.35279360479936,-76.7166317626833),
                        new LatLng(-9.35288722659136,-76.716786660254),
                        new LatLng(-9.35313302502781,-76.7169093713164),
                        new LatLng(-9.35327626952776,-76.7168959602713),
                        new LatLng(-9.35345325745434,-76.7169496044516),
                        new LatLng(-9.35364083147636,-76.717069633305),
                        new LatLng(-9.35392566590895,-76.7170971259474),
                        new LatLng(-9.3541655088914,-76.7170783504843),
                        new LatLng(-9.35427137056897,-76.7170159891247),
                        new LatLng(-9.35437094668004,-76.7169650271534),
                        new LatLng(-9.35448673281985,-76.7169489338994),
                        new LatLng(-9.35462766104086,-76.7171014845371),
                        new LatLng(-9.35479273886093,-76.7172667756676),
                        new LatLng(-9.35502728817394,-76.7174511775374),
                        new LatLng(-9.35521982359899,-76.7175225913524),
                        new LatLng(-9.35538523195462,-76.717569194734),
                        new LatLng(-9.35555328676337,-76.7176624014973),
                        new LatLng(-9.35570480067489,-76.7177321389317),
                        new LatLng(-9.35584473595373,-76.717645637691),
                        new LatLng(-9.35608722414049,-76.7179165408015),
                        new LatLng(-9.35638429675,-76.7181046307086),
                        new LatLng(-9.35678458349509,-76.7184560000896),
                        new LatLng(-9.35713590916768,-76.7186652123928),
                        new LatLng(-9.35736880282655,-76.7187215387821),
                        new LatLng(-9.35778099775519,-76.7189746722579),
                        new LatLng(-9.35804763402177,-76.7192415520548),
                        new LatLng(-9.35838241772946,-76.7194531112909),
                        new LatLng(-9.3586662558378,-76.7195721343159),
                        new LatLng(-9.35899773081783,-76.7195198312401),
                        new LatLng(-9.35925609582759,-76.719545982778),
                        new LatLng(-9.35962561369607,-76.719704568386),
                        new LatLng(-9.36012315548754,-76.7198718711733),
                        new LatLng(-9.36054097094978,-76.7199691012501),
                        new LatLng(-9.36095250049447,-76.7201189696788),
                        new LatLng(-9.36125684678967,-76.7200495675206),
                        new LatLng(-9.36167730737426,-76.7202074825763),
                        new LatLng(-9.36178382838576,-76.719765253365),
                        new LatLng(-9.36154134417243,-76.7190715670585),
                        new LatLng(-9.36150379715173,-76.7190045118259),
                        new LatLng(-9.36146625011843,-76.7189374566078),
                        new LatLng(-9.36150429336367,-76.7188727483153),
                        new LatLng(-9.3616706911613,-76.7190722376108),
                        new LatLng(-9.36173619166416,-76.7188449203968),
                        new LatLng(-9.36180069972309,-76.7186390608549),
                        new LatLng(-9.36208982800422,-76.718837544322),
                        new LatLng(-9.36226978873508,-76.7189837247133),
                        new LatLng(-9.36237829442484,-76.7191580682992),
                        new LatLng(-9.36244611046373,-76.719009540975),
                        new LatLng(-9.3625628863436,-76.7187966406345),
                        new LatLng(-9.36259563651248,-76.7186575010418),
                        new LatLng(-9.36264095744818,-76.7185186967253),
                        new LatLng(-9.36262276291266,-76.7182719334959),
                        new LatLng(-9.36259232841476,-76.718100272119),
                        new LatLng(-9.36269355619096,-76.7180107533931),
                        new LatLng(-9.36282191032232,-76.7178870365023),
                        new LatLng(-9.36285995341915,-76.7177442088723),
                        new LatLng(-9.36296746649638,-76.7176446318626),
                        new LatLng(-9.36312526255256,-76.7175252735614),
                        new LatLng(-9.36326751050825,-76.7174441367387),
                        new LatLng(-9.36336112947988,-76.7173754051327),
                        new LatLng(-9.36342960694493,-76.7172184959053),
                        new LatLng(-9.36351528645946,-76.716802753508),
                        new LatLng(-9.36363338521516,-76.716258265078),
                        new LatLng(-9.36362842308338,-76.7157355695962),
                        new LatLng(-9.36383517851434,-76.7151702940464),
                        new LatLng(-9.36396783273406,-76.7148936912417),
                        new LatLng(-9.36400620651386,-76.7144990712404),
                        new LatLng(-9.36412331267774,-76.7145617678761),
                        new LatLng(-9.36431418902409,-76.7147066071629),
                        new LatLng(-9.36457519654461,-76.7148993909358),
                        new LatLng(-9.36478592110444,-76.714973822236),
                        new LatLng(-9.36502873386102,-76.7151119560003),
                        new LatLng(-9.36523879653124,-76.7153416201472),
                        new LatLng(-9.36546043732117,-76.7156175523996),
                        new LatLng(-9.36562683322424,-76.7158113420009),
                        new LatLng(-9.3659540010796,-76.7158626392483),
                        new LatLng(-9.36605506245963,-76.7158919759009),
                        new LatLng(-9.36615612383725,-76.7159213125705),
                        new LatLng(-9.36626231266937,-76.716091632843),
                        new LatLng(-9.36638305694058,-76.7161586880683),
                        new LatLng(-9.36655309126778,-76.7160322889685),
                        new LatLng(-9.36663380792314,-76.7159323766827),
                        new LatLng(-9.3665825330008,-76.7157788202166),
                        new LatLng(-9.36678465539273,-76.7158297821879),
                        new LatLng(-9.36699504780671,-76.7159880325198),
                        new LatLng(-9.36702614353003,-76.7158146947622),
                        new LatLng(-9.36692888668397,-76.7156490683555),
                        new LatLng(-9.36695171227273,-76.7155028879642),
                        new LatLng(-9.36712207712702,-76.7155853658914),
                        new LatLng(-9.36727788646428,-76.7155266925692),
                        new LatLng(-9.36763217877356,-76.7156527563929),
                        new LatLng(-9.36813797921252,-76.715832799673),
                        new LatLng(-9.36843371820014,-76.7159586957515),
                        new LatLng(-9.36872945714332,-76.7160845920443),
                        new LatLng(-9.36902254930175,-76.7163276672363),
                        new LatLng(-9.36922136223904,-76.7166337743401),
                        new LatLng(-9.36933185057763,-76.7168966308236),
                        new LatLng(-9.36960211681455,-76.7170619219541),
                        new LatLng(-9.36986080474148,-76.7173546180129),
                        new LatLng(-9.37009699789774,-76.7176432907581),
                        new LatLng(-9.37023031133098,-76.7176624014973),
                        new LatLng(-9.37040133618154,-76.7179668322205),
                        new LatLng(-9.37040133618154,-76.7179668322205),
                        new LatLng(-9.37062297367686,-76.7181897908449),
                        new LatLng(-9.37073313076742,-76.7183440178632),
                        new LatLng(-9.37087802202251,-76.7183862626552),
                        new LatLng(-9.3709997570942,-76.718544177711),
                        new LatLng(-9.37114861785755,-76.7187523841857),
                        new LatLng(-9.3712968169541,-76.7188801243901),
                        new LatLng(-9.37145262441819,-76.7190269753336),
                        new LatLng(-9.3717516686969,-76.7192257940769),
                        new LatLng(-9.37202325625337,-76.7195986211299),
                        new LatLng(-9.3722604403402,-76.7200499027967),
                        new LatLng(-9.37242220177927,-76.7200160399079),
                        new LatLng(-9.37272455322401,-76.7204240709543),
                        new LatLng(-9.37311324315172,-76.7202071473002),
                        new LatLng(-9.37318006469545,-76.7204032838344)

                ).color(Color.rgb(213, 158, 32)).width(5));
    }
    public void ruta6(GoogleMap googleMap){
      googleMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .add(
                        new LatLng(-9.37328426658204,-76.7203338816761),
                        new LatLng(-9.37343180316795,-76.7205112427473),
                        new LatLng(-9.37288829924662,-76.7207254841923),
                        new LatLng(-9.37272124522276,-76.7204820737242),
                        new LatLng(-9.37261505836579,-76.7206302657723),
                        new LatLng(-9.37243708779749,-76.7206661403179),
                        new LatLng(-9.37229881320374,-76.720624230802),
                        new LatLng(-9.37223596109745,-76.7204676568508),
                        new LatLng(-9.37222769108263,-76.7202611267566),
                        new LatLng(-9.37226540234865,-76.7200941592454),
                        new LatLng(-9.37197297452934,-76.7198936641216),
                        new LatLng(-9.37166896842435,-76.7197065800428),
                        new LatLng(-9.37145196281553,-76.7195728048682),
                        new LatLng(-9.37121510897788,-76.7194624990224),
                        new LatLng(-9.37105400857979,-76.7193803563714),
                        new LatLng(-9.37050520809995,-76.7194762453436),
                        new LatLng(-9.37031631997042,-76.7195258662104),
                        new LatLng(-9.37025313670275,-76.7195818573236),
                        new LatLng(-9.37035370064169,-76.7197454720735),
                        new LatLng(-9.37053498030013,-76.7198453843593),
                        new LatLng(-9.37059055506697,-76.7199805006384),
                        new LatLng(-9.37060279474657,-76.7201384156942),
                        new LatLng(-9.37056376009121,-76.7202148586511),
                        new LatLng(-9.37058890105619,-76.7203509807586),
                        new LatLng(-9.37054291955308,-76.7204877734184),
                        new LatLng(-9.37047973632665,-76.7206346243619),
                        new LatLng(-9.3704019977862,-76.7207821458578),
                        new LatLng(-9.37033881453414,-76.7209041863679),
                        new LatLng(-9.37016745885023,-76.7209856584668),
                        new LatLng(-9.37005829398818,-76.7211690545082),
                        new LatLng(-9.36996269200506,-76.7214352637529),
                        new LatLng(-9.36990347830681,-76.7217041552067),
                        new LatLng(-9.3698842917432,-76.7219479009509),
                        new LatLng(-9.36982706284889,-76.7221849411725),
                        new LatLng(-9.36969804969996,-76.7224390804767),
                        new LatLng(-9.36952768610934,-76.7227029427886),
                        new LatLng(-9.36934508271164,-76.7227495461702),
                        new LatLng(-9.36913899216735,-76.7229131609201),
                        new LatLng(-9.36895109570579,-76.723100580275),
                        new LatLng(-9.3688065344505,-76.7232893407344),
                        new LatLng(-9.3687165557898,-76.7234643548726),
                        new LatLng(-9.36870365443581,-76.7236829549074),
                        new LatLng(-9.36866858921484,-76.7238844558596),
                        new LatLng(-9.36843371830385,-76.7239796742796),
                        new LatLng(-9.36822498078344,-76.7240353301167),
                        new LatLng(-9.36794346611584,-76.7241275310516),
                        new LatLng(-9.36766492846458,-76.724149659276),
                        new LatLng(-9.36745652128724,-76.7242971807718),
                        new LatLng(-9.36721900311363,-76.7244856059551),
                        new LatLng(-9.36690837673336,-76.7244819179177),
                        new LatLng(-9.36664108565344,-76.724540926516),
                        new LatLng(-9.36631987295732,-76.7247256636619),
                        new LatLng(-9.36601023819255,-76.7248946428298),
                        new LatLng(-9.36584251924641,-76.725146099925),
                        new LatLng(-9.36567116134404,-76.7253569886088),
                        new LatLng(-9.36558382832651,-76.7255527898669),
                        new LatLng(-9.36536748052947,-76.7256993055343),
                        new LatLng(-9.36528014743562,-76.7259031534194),
                        new LatLng(-9.36545481360138,-76.726092249155),
                        new LatLng(-9.36526129142383,-76.72596283257),
                        new LatLng(-9.36518652898107,-76.7260205000638),
                        new LatLng(-9.36487788562893,-76.7258937656879),
                        new LatLng(-9.36455237079968,-76.7257901653647),
                        new LatLng(-9.36429897184956,-76.7257378622889),
                        new LatLng(-9.36417425715479,-76.7257267981767),
                        new LatLng(-9.36396981758484,-76.7257666960358),
                        new LatLng(-9.36372468842734,-76.7256594076752),
                        new LatLng(-9.36341372811365,-76.7255189269781),
                        new LatLng(-9.36313551763969,-76.7254300788044),
                        new LatLng(-9.36296316597392,-76.7254076153039),
                        new LatLng(-9.36257181820814,-76.7252111434936),
                        new LatLng(-9.36238160252461,-76.7250891029834),
                        new LatLng(-9.36215069708538,-76.7249080538749),
                        new LatLng(-9.36196412008546,-76.7248674854636),
                        new LatLng(-9.36184337427857,-76.7249342054128),
                        new LatLng(-9.36181591700704,-76.7250834032893),
                        new LatLng(-9.36171700464944,-76.7253117263317),
                        new LatLng(-9.36154200579398,-76.7254190146923),
                        new LatLng(-9.36151289444463,-76.7255856469273),
                        new LatLng(-9.36132300899248,-76.7256520316004),
                        new LatLng(-9.36119498511865,-76.7258257046341),
                        new LatLng(-9.36118340672979,-76.7259621620178),
                        new LatLng(-9.36112882289145,-76.7262025550007),
                        new LatLng(-9.36103388007342,-76.7263876274228),
                        new LatLng(-9.36093165937973,-76.7265281081199),
                        new LatLng(-9.36091346475473,-76.7267349734902),
                        new LatLng(-9.36094621507905,-76.7269495502114),
                        new LatLng(-9.36082613054143,-76.727305278182),
                        new LatLng(-9.36072357897509,-76.7274390533566),
                        new LatLng(-9.36071861680176,-76.7276301607489),
                        new LatLng(-9.3607255638444,-76.7277055978775),
                        new LatLng(-9.36063029010458,-76.7276325076818),
                        new LatLng(-9.360591915952,-76.7277357727289),
                        new LatLng(-9.36060382517222,-76.7278574779629),
                        new LatLng(-9.36056115046454,-76.7279775068163),
                        new LatLng(-9.36058430728332,-76.7280968651175),
                        new LatLng(-9.36054262500841,-76.7282111942768),
                        new LatLng(-9.36051516763412,-76.72835201025),
                        new LatLng(-9.36048605619877,-76.7284519225358),
                        new LatLng(-9.36042617926157,-76.728526353836),
                        new LatLng(-9.36041294678787,-76.7286044731736),
                        new LatLng(-9.36032792813234,-76.7287234961986),
                        new LatLng(-9.36026937441585,-76.7288311198353),
                        new LatLng(-9.36022008342653,-76.7289213091135),
                        new LatLng(-9.36020089632812,-76.7290225625038),
                        new LatLng(-9.36024059377193,-76.7291301861405),
                        new LatLng(-9.36029153881817,-76.729226745665),
                        new LatLng(-9.36035108496665,-76.7293249815702),
                        new LatLng(-9.36032726650848,-76.7294252291321),
                        new LatLng(-9.36031535727879,-76.7295140773057),
                        new LatLng(-9.36033818330201,-76.729559674859),
                        new LatLng(-9.36030940266379,-76.7296666279435),
                        new LatLng(-9.36028095283515,-76.7297621816396),
                        new LatLng(-9.36028955394638,-76.7298362776637),
                        new LatLng(-9.36026738954394,-76.729940213263),
                        new LatLng(-9.36027102847577,-76.7301470786333),
                        new LatLng(-9.36032395838917,-76.7303851246834),
                        new LatLng(-9.36035240821427,-76.7304525151848),
                        new LatLng(-9.36034876928328,-76.7306000366806),
                        new LatLng(-9.36037291855187,-76.7307817563414),
                        new LatLng(-9.36040037593739,-76.7308913916349),
                        new LatLng(-9.36037324936375,-76.7310301959514),
                        new LatLng(-9.36029352368994,-76.7311472073197),
                        new LatLng(-9.36024985650816,-76.7312980815768),
                        new LatLng(-9.36019725739555,-76.7313899472355),
                        new LatLng(-9.36002523508487,-76.7314234748482),
                        new LatLng(-9.35992797627923,-76.7314549908041),
                        new LatLng(-9.35986545274695,-76.7315321043133),
                        new LatLng(-9.35980524489043,-76.7315817251801),
                        new LatLng(-9.35979664376721,-76.7317171767354),
                        new LatLng(-9.35974007483616,-76.7318268120288),
                        new LatLng(-9.35967424314458,-76.7319394648075),
                        new LatLng(-9.35961304281711,-76.7320024967193),
                        new LatLng(-9.35961138875406,-76.7320655286312),
                        new LatLng(-9.35957929992925,-76.7321034148335),
                        new LatLng(-9.35954423378741,-76.7321600764989),
                        new LatLng(-9.35948799562802,-76.7323260381817),
                        new LatLng(-9.3594436667195,-76.7324742302298),
                        new LatLng(-9.35940363837187,-76.7325899004936),
                        new LatLng(-9.35931365727608,-76.7326891422271),
                        new LatLng(-9.35919787274356,-76.7327132821083),
                        new LatLng(-9.35911516948243,-76.732802465558),
                        new LatLng(-9.35905529230911,-76.7329365760087),
                        new LatLng(-9.35896464949669,-76.7330478876829),
                        new LatLng(-9.35887731479377,-76.733146123588),
                        new LatLng(-9.35884357183449,-76.7332788929343),
                        new LatLng(-9.35894116175677,-76.7334428429603),
                        new LatLng(-9.35908903524782,-76.7336926236748),
                        new LatLng(-9.35920878957256,-76.733835786581),
                        new LatLng(-9.35904801441992,-76.7338461801409),
                        new LatLng(-9.35893189898567,-76.7338854074478),
                        new LatLng(-9.35875028245944,-76.733862273395),
                        new LatLng(-9.35867584942949,-76.7339906841516),
                        new LatLng(-9.35862986634968,-76.7341150715947),
                        new LatLng(-9.35848133103595,-76.7340382933616),
                        new LatLng(-9.35838043284677,-76.7340094596147),
                        new LatLng(-9.35832783345129,-76.7341482639312),
                        new LatLng(-9.35829574450801,-76.7342213541269),
                        new LatLng(-9.3581892224276,-76.7341154068708),
                        new LatLng(-9.35829475206641,-76.7342578992247),
                        new LatLng(-9.35835562181215,-76.7341663688421),
                        new LatLng(-9.35840325899705,-76.7340651154518),
                        new LatLng(-9.35854881702154,-76.7341462522745),
                        new LatLng(-9.35869139766372,-76.7341918498277),
                        new LatLng(-9.35876384580986,-76.7341328412294),
                        new LatLng(-9.35883497068748,-76.7340892553329),
                        new LatLng(-9.35880122772409,-76.7340047657489),
                        new LatLng(-9.35894149257003,-76.7339930310845),
                        new LatLng(-9.35909962126715,-76.7339551448822),
                        new LatLng(-9.35924054761861,-76.7340433225035),
                        new LatLng(-9.35925146444626,-76.7341294884681),
                        new LatLng(-9.35909895964095,-76.7342139780521),
                        new LatLng(-9.35894314663626,-76.7342532053589),
                        new LatLng(-9.35874862839229,-76.7343604937195),
                        new LatLng(-9.35861001753637,-76.7344510182738),
                        new LatLng(-9.35836190727442,-76.7344201728701),
                        new LatLng(-9.35822296545039,-76.7344389483332),
                        new LatLng(-9.35807972298796,-76.7343765869736),
                        new LatLng(-9.35786403214798,-76.7343973740935),
                        new LatLng(-9.35766521271223,-76.7344775050878),
                        new LatLng(-9.35745547627908,-76.7345402017235),
                        new LatLng(-9.35727319735193,-76.7346766591072),
                        new LatLng(-9.35722688327174,-76.734789982438),
                        new LatLng(-9.35722225186339,-76.734900958836),
                        new LatLng(-9.35721464312094,-76.7350370809435),
                        new LatLng(-9.35712333819869,-76.7351205646991),
                        new LatLng(-9.35697976446812,-76.7351172119379),
                        new LatLng(-9.35681766502365,-76.7350300401449),
                        new LatLng(-9.35670121802914,-76.7349576205015),
                        new LatLng(-9.3565702151137,-76.7349884659051),
                        new LatLng(-9.35640050671806,-76.7350709438323),
                        new LatLng(-9.35617952178243,-76.7352399230003),
                        new LatLng(-9.35605017275046,-76.7352952435612),
                        new LatLng(-9.35590891428973,-76.7352325469255),
                        new LatLng(-9.35577956515713,-76.7352251708507),
                        new LatLng(-9.35566642597865,-76.735294573009),
                        new LatLng(-9.35548348448063,-76.7353304475545),
                        new LatLng(-9.35539416400359,-76.7354052141308),
                        new LatLng(-9.35539813380305,-76.7355319485068),
                        new LatLng(-9.3553905250207,-76.7356422543525),
                        new LatLng(-9.35527209264786,-76.735706962645),
                        new LatLng(-9.35517185516113,-76.7358437553048),
                        new LatLng(-9.35499685301109,-76.7359436675906),
                        new LatLng(-9.35479340049534,-76.7360083758831),
                        new LatLng(-9.35479340049534,-76.7360083758831),
                        new LatLng(-9.35502067183442,-76.7360060289502),
                        new LatLng(-9.35519832050697,-76.7359101399779),
                        new LatLng(-9.35530252778664,-76.7357475310564),
                        new LatLng(-9.35542956138056,-76.73568315804),
                        new LatLng(-9.35545238772456,-76.735543012619),
                        new LatLng(-9.35544808710914,-76.7354253306984),
                        new LatLng(-9.35549473224277,-76.7353787273168),
                        new LatLng(-9.35568495169461,-76.7353555932641),
                        new LatLng(-9.35580404555932,-76.7352617159485),
                        new LatLng(-9.35588641879187,-76.7352744564414),
                        new LatLng(-9.35604587214243,-76.7353314533829),
                        new LatLng(-9.35620334052476,-76.7352818325161),
                        new LatLng(-9.35642134810444,-76.7351447045803),
                        new LatLng(-9.35658245528719,-76.735025011003),
                        new LatLng(-9.35670320292141,-76.7349988594651),
                        new LatLng(-9.3568044324126,-76.7350675910711),
                        new LatLng(-9.35696587023299,-76.7351487278938),
                        new LatLng(-9.3571345859078,-76.7351628094911),
                        new LatLng(-9.35724044668158,-76.7350588738918),
                        new LatLng(-9.35725831068397,-76.734781935811),
                        new LatLng(-9.35730065498228,-76.7347011342644),
                        new LatLng(-9.35747499434365,-76.7345566302537),
                        new LatLng(-9.35766223538204,-76.734499298036),
                        new LatLng(-9.35787131006194,-76.7344285547733),
                        new LatLng(-9.35807939217389,-76.7343993857502),
                        new LatLng(-9.35821667998561,-76.7344560474157),
                        new LatLng(-9.35837117006072,-76.7344486713409),
                        new LatLng(-9.35860207801073,-76.734475493431),
                        new LatLng(-9.35878865681533,-76.7343806102871),
                        new LatLng(-9.35895307103355,-76.7342890799045),
                        new LatLng(-9.35910590671596,-76.7342451587319),
                        new LatLng(-9.35927561379108,-76.7341569811105),
                        new LatLng(-9.35927660622987,-76.7340312525629),
                        new LatLng(-9.3593493850669,-76.7339481040835),
                        new LatLng(-9.3594112470664,-76.7340144887566),
                        new LatLng(-9.35945590679182,-76.7339222878217),
                        new LatLng(-9.35958095399246,-76.7340453341603),
                        new LatLng(-9.35967722045755,-76.7339963838458),
                        new LatLng(-9.3597810955824,-76.7340768501162),
                        new LatLng(-9.35987239980664,-76.733999736607),
                        new LatLng(-9.36001961127711,-76.7341516166925),
                        new LatLng(-9.36016086806769,-76.7341807857155),
                        new LatLng(-9.36032825894427,-76.7340758442878),
                        new LatLng(-9.36047348535093,-76.7339685559272),
                        new LatLng(-9.36073813468312,-76.7339752614498),
                        new LatLng(-9.36090420203617,-76.7340513691306),
                        new LatLng(-9.36111790612275,-76.7339739203453),
                        new LatLng(-9.36125717760072,-76.733949445188),
                        new LatLng(-9.36138718631719,-76.7340161651372),
                        new LatLng(-9.36147749768704,-76.7340939491987),
                        new LatLng(-9.36140505010649,-76.7339159175753),
                        new LatLng(-9.36137643498233,-76.7337922006743),
                        new LatLng(-9.3613478198153,-76.7336684837937),
                        new LatLng(-9.36125982408906,-76.7334917932748),
                        new LatLng(-9.36126213976636,-76.7332949861884),
                        new LatLng(-9.3612849657274,-76.733108907938),
                        new LatLng(-9.361240306237,-76.7329566925764),
                        new LatLng(-9.36133524899863,-76.7328483983874),
                        new LatLng(-9.3615671474119,-76.7330274358391),
                        new LatLng(-9.36178085109085,-76.7330458760261),
                        new LatLng(-9.36203292863609,-76.7332473769783),
                        new LatLng(-9.36228037465753,-76.7333214730024),
                        new LatLng(-9.36252351997488,-76.7334418371319),
                        new LatLng(-9.36257545711586,-76.733344271779),
                        new LatLng(-9.36284109727603,-76.7333191260695),
                        new LatLng(-9.36298797667965,-76.7330954968929),
                        new LatLng(-9.36304454508204,-76.7329613864421),
                        new LatLng(-9.36309317405218,-76.7327947542071),
                        new LatLng(-9.36307233306581,-76.732606999576),
                        new LatLng(-9.36306472445143,-76.7324631661176),
                        new LatLng(-9.36301675709605,-76.7323079332709),
                        new LatLng(-9.36310872208882,-76.7322019860148),
                        new LatLng(-9.36317256827452,-76.7321151494979),
                        new LatLng(-9.36326982617218,-76.7320014908909),
                        new LatLng(-9.36347790505225,-76.7318791151046),
                        new LatLng(-9.36383815579164,-76.7317312583327),
                        new LatLng(-9.36403994897191,-76.7316859960556),
                        new LatLng(-9.36417491877133,-76.7316839843988),
                        new LatLng(-9.36426357537575,-76.7316836491227),
                        new LatLng(-9.36434065367389,-76.731739975512),
                        new LatLng(-9.36444750467695,-76.7318174242973),
                        new LatLng(-9.3645219364708,-76.7317711561918),
                        new LatLng(-9.36451366627227,-76.7316249758005),
                        new LatLng(-9.36451829758347,-76.7314596846699),
                        new LatLng(-9.36458809805196,-76.7313074693083),
                        new LatLng(-9.36467344647303,-76.7311549186706),
                        new LatLng(-9.36480345391134,-76.7310617119073),
                        new LatLng(-9.36492221384428,-76.7309936508536),
                        new LatLng(-9.36504891311517,-76.7308957502246),
                        new LatLng(-9.36514120837741,-76.7307720333337),
                        new LatLng(-9.36527353129125,-76.7305634915828),
                        new LatLng(-9.36546705346197,-76.7304887250065),
                        new LatLng(-9.36545911409298,-76.7300682887434),
                        new LatLng(-9.36550774272518,-76.7297417297959),
                        new LatLng(-9.36564965889856,-76.7294413223862),
                        new LatLng(-9.36574162319443,-76.7289457842707),
                        new LatLng(-9.36597285705378,-76.7286390066146),
                        new LatLng(-9.36645418002895,-76.7287593705954),
                        new LatLng(-9.36687198810833,-76.728883087635),
                        new LatLng(-9.36705360038981,-76.7289538308978),
                        new LatLng(-9.3673387546365,-76.7291509732604),
                        new LatLng(-9.36766327443985,-76.7294228821992),
                        new LatLng(-9.3679616603727,-76.729678697884),
                        new LatLng(-9.36839236777491,-76.7299516126513),
                        new LatLng(-9.36851278050148,-76.7299569770693),
                        new LatLng(-9.36853593679027,-76.7298463359475),
                        new LatLng(-9.36844794288466,-76.7297196015715),
                        new LatLng(-9.36835631010965,-76.7295985668897),
                        new LatLng(-9.36828320235483,-76.72945138067),
                        new LatLng(-9.36819156953635,-76.7292314395308),
                        new LatLng(-9.36816576678888,-76.7289327085018),
                        new LatLng(-9.36812573944605,-76.7286571115255),
                        new LatLng(-9.36810192152086,-76.7282775789499),
                        new LatLng(-9.36809265899396,-76.7279013991355),
                        new LatLng(-9.36817701414058,-76.7275480180978),
                        new LatLng(-9.36829279568101,-76.7272194474935),
                        new LatLng(-9.36842412498158,-76.726639419794),
                        new LatLng(-9.36866495037096,-76.7261831089854),
                        new LatLng(-9.36894117159408,-76.7256999760866),
                        new LatLng(-9.36919125912275,-76.7252999916672),
                        new LatLng(-9.36955249634618,-76.7250357940793),
                        new LatLng(-9.36992994253074,-76.7248111590743),
                        new LatLng(-9.37006094040948,-76.7246981710195),
                        new LatLng(-9.37023626577593,-76.7243960872292),
                        new LatLng(-9.3704099370422,-76.7240132018923),
                        new LatLng(-9.37045261054004,-76.72367926687),
                        new LatLng(-9.37050653132496,-76.7235590703972),
                        new LatLng(-9.37056045206937,-76.723438873887),
                        new LatLng(-9.37065076105436,-76.7232031747698),
                        new LatLng(-9.37071394424966,-76.723047606647),
                        new LatLng(-9.37067821762754,-76.722807213664),
                        new LatLng(-9.37063422094897,-76.7226318642497),
                        new LatLng(-9.37071493665577,-76.722471602261),
                        new LatLng(-9.37084361862495,-76.7223032936453),
                        new LatLng(-9.37103746849362,-76.7221507430076),
                        new LatLng(-9.37094715960922,-76.7219448834657),
                        new LatLng(-9.37083964900194,-76.7214892432093),
                        new LatLng(-9.37088166084321,-76.7211190983652),
                        new LatLng(-9.37103217566587,-76.7207362130284),
                        new LatLng(-9.37107054866517,-76.7204515635967),
                        new LatLng(-9.37098023985685,-76.7202138527609),
                        new LatLng(-9.3708899308901,-76.7199761420488),
                        new LatLng(-9.37058030019995,-76.7198222503066),
                        new LatLng(-9.37039571254176,-76.7196958512067),
                        new LatLng(-9.37031003472441,-76.7195848748087),
                        new LatLng(-9.37056243688249,-76.7195188254117),
                        new LatLng(-9.37106029381233,-76.7194266244769),
                        new LatLng(-9.37121874779505,-76.719503737986),
                        new LatLng(-9.37144964720616,-76.7196482419967),
                        new LatLng(-9.37165739038463,-76.7197595536708),
                        new LatLng(-9.37194320245248,-76.7199466377496),
                        new LatLng(-9.37220188863508,-76.7201276868581),
                        new LatLng(-9.37216186175754,-76.7202601209282),
                        new LatLng(-9.37221677466274,-76.7205223068594),
                        new LatLng(-9.37226970275592,-76.7206912860274),
                        new LatLng(-9.37242716378541,-76.7207425832748),
                        new LatLng(-9.37261009636233,-76.7206735163927),
                        new LatLng(-9.37272124522276,-76.7205652222037),
                        new LatLng(-9.37285323444827,-76.7208005860447),
                        new LatLng(-9.37350987182929,-76.7205487936735),
                        new LatLng(-9.37333421731658,-76.7203208059072)

                        ).color(Color.BLUE).width(5));
    }
    public void ruta7(GoogleMap googleMap){
        googleMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .add(
                        new LatLng(-9.38593050443387,-76.7134416103363),
                        new LatLng(-9.38600228532043,-76.7131918296218),
                        new LatLng(-9.38589080992801,-76.7132448032498),
                        new LatLng(-9.38574195549056,-76.7130500078201),
                        new LatLng(-9.38583788391314,-76.7130057513713),
                        new LatLng(-9.38613162319369,-76.7127123847603),
                        new LatLng(-9.38601452445628,-76.7125685513019),
                        new LatLng(-9.38624442165758,-76.7120991647243),
                        new LatLng(-9.38644520944821,-76.7119831591844),
                        new LatLng(-9.38658149368135,-76.7116830870509),
                        new LatLng(-9.38697182201373,-76.7111939191818),
                        new LatLng(-9.38687820941137,-76.7110497504472),
                        new LatLng(-9.38700060044116,-76.710835173726),
                        new LatLng(-9.38710016716597,-76.7105900868773),
                        new LatLng(-9.38707138874679,-76.7104821279644),
                        new LatLng(-9.38686133929307,-76.7102129012346),
                        new LatLng(-9.38698108403661,-76.710060685873),
                        new LatLng(-9.38761487044331,-76.7095098271965),
                        new LatLng(-9.38773593806972,-76.7092285305261),
                        new LatLng(-9.38792613985642,-76.7088322341442),
                        new LatLng(-9.38803364516794,-76.7084627598524),
                        new LatLng(-9.38804786894513,-76.7081291601061),
                        new LatLng(-9.38814214280238,-76.707827411592),
                        new LatLng(-9.38809616362608,-76.707779802382),
                        new LatLng(-9.38820333809949,-76.7076778784394),
                        new LatLng(-9.38808888634582,-76.7075001820921),
                        new LatLng(-9.38794069442363,-76.7072004452347),
                        new LatLng(-9.38787751436611,-76.7070844396948),
                        new LatLng(-9.38768268155075,-76.7066304758191),
                        new LatLng(-9.38751464229434,-76.7064886540174),
                        new LatLng(-9.38700787774429,-76.7060635238886),
                        new LatLng(-9.38674986417694,-76.7056719213724),
                        new LatLng(-9.38656396197476,-76.7053171992301),
                        new LatLng(-9.38665360521993,-76.7051277682185),
                        new LatLng(-9.38649185041753,-76.7049306258559),
                        new LatLng(-9.38642701616062,-76.7045805975794),
                        new LatLng(-9.38620737348581,-76.7045051604509),
                        new LatLng(-9.3859695373602,-76.704209446907),
                        new LatLng(-9.38587757842506,-76.7039167508482),
                        new LatLng(-9.3858855173269,-76.7035258188843),
                        new LatLng(-9.38593348152163,-76.7031452804803),
                        new LatLng(-9.38579223521881,-76.7025541886687),
                        new LatLng(-9.38572938555736,-76.7020948603749),
                        new LatLng(-9.38566885139899,-76.7016492784023),
                        new LatLng(-9.38542439914368,-76.7013602703809),
                        new LatLng(-9.38506152450365,-76.7006276920437),
                        new LatLng(-9.38495335669335,-76.7002096027135),
                        new LatLng(-9.38479755529258,-76.7001029849052),
                        new LatLng(-9.3846407614555,-76.699893772602),
                        new LatLng(-9.38435231353625,-76.6996785253286),
                        new LatLng(-9.38401292381935,-76.6993224620819),
                        new LatLng(-9.38363880085172,-76.699016354978),
                        new LatLng(-9.3835822358151,-76.6989159397617),
                        new LatLng(-9.38344330407408,-76.6986482217907),
                        new LatLng(-9.38350846967887,-76.698339767754),
                        new LatLng(-9.3833361281289,-76.6975381225347),
                        new LatLng(-9.38330536465703,-76.6973634436726),
                        new LatLng(-9.38320513526202,-76.6970533132553),
                        new LatLng(-9.38308175052145,-76.6967234015464),
                        new LatLng(-9.38306918049179,-76.6963341459631),
                        new LatLng(-9.38305131781728,-76.6958885639905),
                        new LatLng(-9.38297225893205,-76.6952763497829),
                        new LatLng(-9.38313864643945,-76.6950587555766),
                        new LatLng(-9.38329378700563,-76.6948411613702),
                        new LatLng(-9.38332785837867,-76.6946262493729),
                        new LatLng(-9.38331991941825,-76.6943694278597),
                        new LatLng(-9.38338343109644,-76.6940656676888),
                        new LatLng(-9.38331793467812,-76.693711951375),
                        new LatLng(-9.38328287093395,-76.6934775933623),
                        new LatLng(-9.38333678970891,-76.6934273019433),
                        new LatLng(-9.38331164966763,-76.6932308301329),
                        new LatLng(-9.38325376140778,-76.6929280757904),
                        new LatLng(-9.38306289547679,-76.692692041397),
                        new LatLng(-9.38294612649321,-76.6924218088388),
                        new LatLng(-9.38290378532231,-76.6920439526438),
                        new LatLng(-9.38276617648119,-76.6917556151747),
                        new LatLng(-9.38248599677286,-76.6915705427527),
                        new LatLng(-9.38232225528008,-76.6912996396422),
                        new LatLng(-9.38220780158466,-76.6910129785537),
                        new LatLng(-9.38204869103782,-76.6908285766839),
                        new LatLng(-9.38178438874145,-76.6906180232763),
                        new LatLng(-9.38153993374737,-76.6903360560536),
                        new LatLng(-9.38140067040575,-76.6901077330112),
                        new LatLng(-9.38122005800768,-76.6899025440216),
                        new LatLng(-9.38110229602714,-76.6897597163915),
                        new LatLng(-9.38110560394848,-76.6896242648363),
                        new LatLng(-9.38137486864036,-76.6895290464162),
                        new LatLng(-9.38150718536566,-76.6893463209271),
                        new LatLng(-9.38171029144074,-76.6891863942146),
                        new LatLng(-9.38188759566964,-76.6889376193284),
                        new LatLng(-9.38205266053259,-76.6886891797184),
                        new LatLng(-9.38212477301319,-76.6884256526827),
                        new LatLng(-9.38224418860176,-76.6882053762674),
                        new LatLng(-9.38237815894995,-76.6881819069385),
                        new LatLng(-9.38265205372294,-76.6881292685866),
                        new LatLng(-9.38295241151033,-76.6881285980343),
                        new LatLng(-9.38309432055064,-76.688030026853),
                        new LatLng(-9.3832550845681,-76.6879203915595),
                        new LatLng(-9.38334902893887,-76.6878077387809),
                        new LatLng(-9.38324846876639,-76.687564663589),
                        new LatLng(-9.38311185243271,-76.6873145475983),
                        new LatLng(-9.38302220827168,-76.6870979592204),
                        new LatLng(-9.38297953631972,-76.6869216039776),
                        new LatLng(-9.38295108834884,-76.6866403073072),
                        new LatLng(-9.38288757659146,-76.6863694041967),
                        new LatLng(-9.38280289423018,-76.686092801392),
                        new LatLng(-9.3828263803559,-76.6859070584177),
                        new LatLng(-9.38286640600308,-76.6856851056218),
                        new LatLng(-9.38289683872346,-76.6855251789092),
                        new LatLng(-9.38286673679353,-76.6853629052639),
                        new LatLng(-9.38276882280555,-76.6852720454335),
                        new LatLng(-9.38280090948709,-76.6850940138101),
                        new LatLng(-9.38290213137021,-76.6848841309547),
                        new LatLng(-9.38287666050698,-76.684610210359),
                        new LatLng(-9.38284192750866,-76.6844291612505),
                        new LatLng(-9.38278602391356,-76.6842149198055),
                        new LatLng(-9.3828161258505,-76.6840533167123),
                        new LatLng(-9.38278139284611,-76.6838836669921),
                        new LatLng(-9.38265172293229,-76.6837857663631),
                        new LatLng(-9.3824952589156,-76.6836144402623),
                        new LatLng(-9.38231630104252,-76.6833569481968),
                        new LatLng(-9.38217339931045,-76.6831058263778),
                        new LatLng(-9.38199907234922,-76.6829016432166),
                        new LatLng(-9.3818627863147,-76.6827423870563),
                        new LatLng(-9.38178041924361,-76.6826475039124),
                        new LatLng(-9.3818522009894,-76.6825734078884),
                        new LatLng(-9.38192431351169,-76.6825164109468),
                        new LatLng(-9.38189321912317,-76.6823544725775),
                        new LatLng(-9.38188445316143,-76.6822696477153),
                        new LatLng(-9.38187568717949,-76.6821848228573),
                        new LatLng(-9.38191736689321,-76.6821341961622),
                        new LatLng(-9.38188561092132,-76.6820195317268),
                        new LatLng(-9.38188561092132,-76.6820195317268),
                        new LatLng(-9.3818627863147,-76.6819343715906),
                        new LatLng(-9.38176917233278,-76.6818881034851),
                        new LatLng(-9.38169606740352,-76.6818264126777),
                        new LatLng(-9.38169011315522,-76.6817620396614),
                        new LatLng(-9.38176090254519,-76.6816825792193),
                        new LatLng(-9.38185914760917,-76.6816031187772),
                        new LatLng(-9.38198451753256,-76.6815605387091),
                        new LatLng(-9.38206920009368,-76.6815296933054),
                        new LatLng(-9.38217339931045,-76.6815876960754),
                        new LatLng(-9.38226072815371,-76.6815987601876),
                        new LatLng(-9.38240561459516,-76.6815736144781),
                        new LatLng(-9.38258589558503,-76.6815836727619),
                        new LatLng(-9.3826881099028,-76.6816037893295),
                        new LatLng(-9.38274202877026,-76.6816138476133),
                        new LatLng(-9.38282042612699,-76.6815769672393),
                        new LatLng(-9.3828584670321,-76.6814840957522),
                        new LatLng(-9.38290411611273,-76.6814224049448),
                        new LatLng(-9.38298780607827,-76.6813986003399),
                        new LatLng(-9.38306752654048,-76.6813516616821),
                        new LatLng(-9.38308770474586,-76.681274883449),
                        new LatLng(-9.38309432055064,-76.6811984404921),
                        new LatLng(-9.38316709439495,-76.6810381785035),
                        new LatLng(-9.38318462627333,-76.6808983683586),
                        new LatLng(-9.3831971962988,-76.6807971149682),
                        new LatLng(-9.38323457558237,-76.6807240247726),
                        new LatLng(-9.38333447417887,-76.6806164011359),
                        new LatLng(-9.38339170084535,-76.6805071011185),
                        new LatLng(-9.38345885119918,-76.6803662851452),
                        new LatLng(-9.38353394049569,-76.6802707314491),
                        new LatLng(-9.38355378788413,-76.6801597550511),
                        new LatLng(-9.38357297369187,-76.6800377145409),
                        new LatLng(-9.38361630715026,-76.6799193620681),
                        new LatLng(-9.38367518771864,-76.6797460243105),
                        new LatLng(-9.38371157458173,-76.6796588525176),
                        new LatLng(-9.38370396641977,-76.6795535758137),
                        new LatLng(-9.3837506077579,-76.6794613748788),
                        new LatLng(-9.38383032804474,-76.6793752089142),
                        new LatLng(-9.38392824173294,-76.6793088242411),
                        new LatLng(-9.38397918330302,-76.6792488098144),
                        new LatLng(-9.38401226224064,-76.6791468858718),
                        new LatLng(-9.38396826725293,-76.6790137812495),
                        new LatLng(-9.38396892883172,-76.678877659142),
                        new LatLng(-9.38398348356509,-76.6787479072809),
                        new LatLng(-9.38397256751513,-76.6786168143153),
                        new LatLng(-9.38395305094009,-76.6784652695059),
                        new LatLng(-9.38390409410295,-76.6783157363533),
                        new LatLng(-9.38387498462893,-76.6782641038298),
                        new LatLng(-9.38389681673467,-76.6781916841864),
                        new LatLng(-9.38384455199441,-76.6780924424529),
                        new LatLng(-9.38378004803194,-76.6780193522572),
                        new LatLng(-9.38383363594007,-76.6779657080769),
                        new LatLng(-9.38386340699654,-76.6778701543807),
                        new LatLng(-9.38389185489258,-76.677799411118),
                        new LatLng(-9.38387068436552,-76.6776810586452),
                        new LatLng(-9.38390607883971,-76.6775623708963),
                        new LatLng(-9.38394544278341,-76.6774282604455),
                        new LatLng(-9.38399406882534,-76.6773129254579),
                        new LatLng(-9.38407974326356,-76.6772264242172),
                        new LatLng(-9.3841445779595,-76.6771100834012),
                        new LatLng(-9.38419088844917,-76.6769796609878),
                        new LatLng(-9.38421867474002,-76.6768938302993),
                        new LatLng(-9.38417831845973,-76.6767805069684),
                        new LatLng(-9.38417037951881,-76.6766282916069),
                        new LatLng(-9.38419221160592,-76.6764321550726),
                        new LatLng(-9.38417104109722,-76.676248088479),
                        new LatLng(-9.38420378922734,-76.6760730743408),
                        new LatLng(-9.38432254252174,-76.6758263111114),
                        new LatLng(-9.38432585041237,-76.675640232861),
                        new LatLng(-9.38446015074516,-76.6754611954092),
                        new LatLng(-9.38458585051689,-76.6752355545759),
                        new LatLng(-9.38465796247063,-76.6750571876764),
                        new LatLng(-9.38496195719327,-76.6747909784317),
                        new LatLng(-9.38520475583356,-76.6743933409452),
                        new LatLng(-9.38538602773207,-76.6741516068577),
                        new LatLng(-9.38545516242751,-76.6738739982247),
                        new LatLng(-9.38548162546518,-76.6734103113412),
                        new LatLng(-9.3855623377176,-76.6729224845767),
                        new LatLng(-9.38570424568959,-76.6724628210067),
                        new LatLng(-9.3858504538424,-76.6719478368759),
                        new LatLng(-9.3859344738842,-76.6716316714882),
                        new LatLng(-9.38603437170269,-76.6714774444699),
                        new LatLng(-9.38598376122209,-76.6712122410535),
                        new LatLng(-9.38602213256755,-76.670896410942),
                        new LatLng(-9.38611872248727,-76.6705483943223),
                        new LatLng(-9.38617991814166,-76.6703063249588),
                        new LatLng(-9.38616370956399,-76.6700910776853),
                        new LatLng(-9.38608200508945,-76.6699070110917),
                        new LatLng(-9.38588882520261,-76.6697034984827),
                        new LatLng(-9.38558053105057,-76.6693953797221),
                        new LatLng(-9.38523088810215,-76.6689548268914),
                        new LatLng(-9.38472378942448,-76.6683053970336),
                        new LatLng(-9.3845888276162,-76.6681179776787),
                        new LatLng(-9.38440358583301,-76.6678718850016),
                        new LatLng(-9.38382271988537,-76.6674286499619),
                        new LatLng(-9.38365997139292,-76.6673853993415),
                        new LatLng(-9.38324185296455,-76.6668429225683),
                        new LatLng(-9.38249261258914,-76.6662669181823),
                        new LatLng(-9.38161601583435,-76.6656825318932),
                        new LatLng(-9.38105036165785,-76.6652309149503),
                        new LatLng(-9.38051183156116,-76.6648101434111),
                        new LatLng(-9.3800612916118,-76.6643229871988),
                        new LatLng(-9.37943807679649,-76.6638599708676),
                        new LatLng(-9.37895048650527,-76.6637583822011),
                        new LatLng(-9.37858131999442,-76.6637349128723),
                        new LatLng(-9.37806991125774,-76.6633614152669),
                        new LatLng(-9.37767428017839,-76.6630807891488),
                        new LatLng(-9.37727831785209,-76.6626784577965),
                        new LatLng(-9.37688566303549,-76.6622637212276),
                        new LatLng(-9.37658596158748,-76.6621148586273),
                        new LatLng(-9.37615923391349,-76.6620947420597),
                        new LatLng(-9.37576227746655,-76.6619391739368),
                        new LatLng(-9.37547216817602,-76.6617145389318),
                        new LatLng(-9.37544471198204,-76.6618114337325),
                        new LatLng(-9.37547845332856,-76.6616890579462),
                        new LatLng(-9.37504378514279,-76.6614030674099),
                        new LatLng(-9.37436234077248,-76.6611485928297),
                        new LatLng(-9.37362002800378,-76.6608575731515),
                        new LatLng(-9.3726792336041,-76.6607217863202),
                        new LatLng(-9.37198554496103,-76.6605685651302),
                        new LatLng(-9.3711912912646,-76.6601749509572),
                        new LatLng(-9.37036329391065,-76.6595261916518),
                        new LatLng(-9.36979762139092,-76.659132912755),
                        new LatLng(-9.3694952673972,-76.6590450704097),
                        new LatLng(-9.36927759882296,-76.6586209461092),
                        new LatLng(-9.36880322641191,-76.6578900441527),
                        new LatLng(-9.36865237981904,-76.6574350744485),
                        new LatLng(-9.36829908096353,-76.656770221889),
                        new LatLng(-9.36686140232581,-76.6560624539852),
                        new LatLng(-9.36498738292673,-76.6554472222924),
                        new LatLng(-9.36317984607733,-76.6550864651799),
                        new LatLng(-9.36155656146774,-76.6545520350337),
                        new LatLng(-9.35981483845068,-76.6538838297128),
                        new LatLng(-9.35845519675369,-76.6532967612147),
                        new LatLng(-9.35696289289682,-76.6525222733616),
                        new LatLng(-9.35588079491718,-76.651933863759),
                        new LatLng(-9.35428394164105,-76.6506641730666),
                        new LatLng(-9.3533728685568,-76.6497689858078),
                        new LatLng(-9.35198739772455,-76.6495946422219),
                        new LatLng(-9.35122187971671,-76.6496741026639),
                        new LatLng(-9.3503379259719,-76.6497277468442),
                        new LatLng(-9.34930873897347,-76.6501146554946),
                        new LatLng(-9.34833314231626,-76.6503426432609),
                        new LatLng(-9.34743594826646,-76.6507691144943),
                        new LatLng(-9.34688115565595,-76.650514639914),
                        new LatLng(-9.34593168741951,-76.6503413021564),
                        new LatLng(-9.34536465184396,-76.6508070006966),
                        new LatLng(-9.34531271213305,-76.6515546664595),
                        new LatLng(-9.34479066798416,-76.6516803950071),
                        new LatLng(-9.34433710427638,-76.6516099870204),
                        new LatLng(-9.34412107408968,-76.6518852487206),
                        new LatLng(-9.34365394557077,-76.6515912115573),
                        new LatLng(-9.34325959862792,-76.6518875956535),
                        new LatLng(-9.34296780806898,-76.6516575962305),
                        new LatLng(-9.34237760963776,-76.6519462689757),
                        new LatLng(-9.34172322926621,-76.651841327548),
                        new LatLng(-9.3412739623539,-76.6517642140388),
                        new LatLng(-9.34066556536854,-76.6516358032822),
                        new LatLng(-9.34034168203751,-76.6516254097223),
                        new LatLng(-9.33991854901961,-76.6515600308775),
                        new LatLng(-9.33952088953039,-76.6517011821269),
                        new LatLng(-9.33919965179128,-76.6521397233009),
                        new LatLng(-9.33894888103313,-76.6524947807192),
                        new LatLng(-9.33857901037703,-76.6529148817062),
                        new LatLng(-9.33843410566591,-76.6525745764374),
                        new LatLng(-9.33810790442899,-76.6522346064448),
                        new LatLng(-9.33776086038425,-76.651714593172),
                        new LatLng(-9.33707835044906,-76.6511281952262),
                        new LatLng(-9.33701648447998,-76.6507047414779),
                        new LatLng(-9.33676306547598,-76.6503855586051),
                        new LatLng(-9.33627243782045,-76.6499527171254),
                        new LatLng(-9.33635646984823,-76.649505123496),
                        new LatLng(-9.33638624497133,-76.6490682587027),
                        new LatLng(-9.33644744938303,-76.6486005485057),
                        new LatLng(-9.33610867455848,-76.6481770947575),
                        new LatLng(-9.33590752685059,-76.6483038291335),
                        new LatLng(-9.33556908233666,-76.6484644263982),
                        new LatLng(-9.33520350895874,-76.6487507522106),
                        new LatLng(-9.33499607484438,-76.6490008682012),
                        new LatLng(-9.33474860942365,-76.6493257507681),
                        new LatLng(-9.33453488914619,-76.6497331112623),
                        new LatLng(-9.33433804140626,-76.6500315070152),
                        new LatLng(-9.33408263543349,-76.6503235325217),
                        new LatLng(-9.3337326098456,-76.6503530368208),
                        new LatLng(-9.33337894469331,-76.6505122929811),
                        new LatLng(-9.33331939393077,-76.6508415341377),
                        new LatLng(-9.33309674015632,-76.651053763926),
                        new LatLng(-9.33320889415559,-76.6512485593557),
                        new LatLng(-9.33335413187682,-76.6518366336822),
                        new LatLng(-9.33320723996706,-76.6520361229777),
                        new LatLng(-9.33271495311552,-76.652438789606),
                        new LatLng(-9.33221042453899,-76.652898453176),
                        new LatLng(-9.33184749435082,-76.6535445302724),
                        new LatLng(-9.33153452052147,-76.6543501988053),
                        new LatLng(-9.3312817592111,-76.6551431268453),
                        new LatLng(-9.33070378207078,-76.6557895392179),
                        new LatLng(-9.3300778320781,-76.656838953495),
                        new LatLng(-9.32919779477598,-76.6577043011784),
                        new LatLng(-9.32836638907434,-76.6586323454976),
                        new LatLng(-9.32736691306297,-76.6592277958989),
                        new LatLng(-9.32706551473655,-76.6589961200952)

                ).color(Color.rgb(234, 55, 150)).width(5));
    }
    public void ruta8(GoogleMap googleMap){
        Polyline ruta4 = googleMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .add(
                        new LatLng(-9.32727295356873,-76.6589692980051),
                        new LatLng(-9.32681903621898,-76.6586568206548),
                        new LatLng(-9.32657619681122,-76.6586454212665),
                        new LatLng(-9.32676940965075,-76.6579510644078),
                        new LatLng(-9.32699570674456,-76.6577220708131),
                        new LatLng(-9.32719388200291,-76.6569653525948),
                        new LatLng(-9.32735599523571,-76.6566934436559),
                        new LatLng(-9.32771859925516,-76.6562354564666),
                        new LatLng(-9.32792405264269,-76.6557446122169),
                        new LatLng(-9.32793761719381,-76.6554271057248),
                        new LatLng(-9.32769577109352,-76.6544380411505),
                        new LatLng(-9.32781752127167,-76.6537467017769),
                        new LatLng(-9.32796937809193,-76.6534382477402),
                        new LatLng(-9.32805870560237,-76.6530365869402),
                        new LatLng(-9.32822280346358,-76.6529353335976),
                        new LatLng(-9.32838690129616,-76.6528340801596),
                        new LatLng(-9.32858110565906,-76.6523687168955),
                        new LatLng(-9.32881931188498,-76.6520220413803),
                        new LatLng(-9.32886397553422,-76.6516824066638),
                        new LatLng(-9.32916272557432,-76.6508998721837),
                        new LatLng(-9.32939431457662,-76.6506769135594),
                        new LatLng(-9.32960042865933,-76.6503768414258),
                        new LatLng(-9.32986080052869,-76.6501625999808),
                        new LatLng(-9.3299382172859,-76.6500201076269),
                        new LatLng(-9.32993259299216,-76.649891026318),
                        new LatLng(-9.32985054446127,-76.6497146710753),
                        new LatLng(-9.32974996881646,-76.6493052989244),
                        new LatLng(-9.32966428099091,-76.6490625590085),
                        new LatLng(-9.32948099499221,-76.6487903147935),
                        new LatLng(-9.32943302300915,-76.6483477503061),
                        new LatLng(-9.32921665274145,-76.6481791064143),
                        new LatLng(-9.32888945035382,-76.64773453027),
                        new LatLng(-9.32878060338433,-76.6474917903542),
                        new LatLng(-9.32886199048326,-76.647327505052),
                        new LatLng(-9.32891591769688,-76.6471960768103),
                        new LatLng(-9.32888647277759,-76.6470861062407),
                        new LatLng(-9.32878655853855,-76.6469791531562),
                        new LatLng(-9.32872833035954,-76.6468038037419),
                        new LatLng(-9.32869326111069,-76.6466562822461),
                        new LatLng(-9.32857746639601,-76.6465114429593),
                        new LatLng(-9.32854074292133,-76.6463096067309),
                        new LatLng(-9.32850931291739,-76.6461560502648),
                        new LatLng(-9.32841006025482,-76.6460252925753),
                        new LatLng(-9.32838888634983,-76.6458874940872),
                        new LatLng(-9.3283141159875,-76.6457104682922),
                        new LatLng(-9.32820063704413,-76.6455200314521),
                        new LatLng(-9.3281109787275,-76.6452467814087),
                        new LatLng(-9.32802330544356,-76.6449896246195),
                        new LatLng(-9.3280223129157,-76.6448535025119),
                        new LatLng(-9.32794324151973,-76.6447552666068),
                        new LatLng(-9.32797334820399,-76.6446711122989),
                        new LatLng(-9.32796805472123,-76.6445510834455),
                        new LatLng(-9.32792967696883,-76.6443683579564),
                        new LatLng(-9.32792603769899,-76.6442158073186),
                        new LatLng(-9.32788137392964,-76.6440458223223),
                        new LatLng(-9.32778278277408,-76.6438436508178),
                        new LatLng(-9.32771826841226,-76.6437155753374),
                        new LatLng(-9.32772223852718,-76.6436334326863),
                        new LatLng(-9.32775995461666,-76.6435442492365),
                        new LatLng(-9.32770238795213,-76.6433595120906),
                        new LatLng(-9.32758923965279,-76.6432140022516),
                        new LatLng(-9.32750685972756,-76.6431026905775),
                        new LatLng(-9.32749064841467,-76.6429705917835),
                        new LatLng(-9.32740628340691,-76.6427892073988),
                        new LatLng(-9.32730405284076,-76.6426202282309),
                        new LatLng(-9.32718561091759,-76.6423697769641),
                        new LatLng(-9.32701820410842,-76.6420452296733),
                        new LatLng(-9.3268478196253,-76.6418145596981),
                        new LatLng(-9.32676808627549,-76.6417002305388),
                        new LatLng(-9.32670588763312,-76.6415248811244),
                        new LatLng(-9.32663707210097,-76.6413488611578),
                        new LatLng(-9.32647528937817,-76.6411540657281),
                        new LatLng(-9.32633931243042,-76.6409984976053),
                        new LatLng(-9.3262218627127,-76.6408275067806),
                        new LatLng(-9.32603658983818,-76.6406186297535),
                        new LatLng(-9.32579308819633,-76.640400364995),
                        new LatLng(-9.32541129316031,-76.6401029750704),
                        new LatLng(-9.32491701023144,-76.6396654397249),
                        new LatLng(-9.32464306999341,-76.6394066065549),
                        new LatLng(-9.32428079362371,-76.6391068696975),
                        new LatLng(-9.32397972347781,-76.6388212144374),
                        new LatLng(-9.323721663146,-76.6386545822024),
                        new LatLng(-9.32352216252794,-76.6384332999587),
                        new LatLng(-9.32337857494785,-76.6382338106632),
                        new LatLng(-9.32326707948199,-76.6380175575613),
                        new LatLng(-9.32314367347974,-76.6377952694892),
                        new LatLng(-9.32304838945889,-76.63763333112),
                        new LatLng(-9.32294086405687,-76.637498550117),
                        new LatLng(-9.32278271894389,-76.6374398767948),
                        new LatLng(-9.3226603053135,-76.6373275592923),
                        new LatLng(-9.32255311063974,-76.6371672973036),
                        new LatLng(-9.32243235116845,-76.6370479390025),
                        new LatLng(-9.32224641458574,-76.6368293389678),
                        new LatLng(-9.32210481157019,-76.6366942226886),
                        new LatLng(-9.32196287764883,-76.6364786401391),
                        new LatLng(-9.32184542645816,-76.6363076493144),
                        new LatLng(-9.32177793336168,-76.6361467167735),
                        new LatLng(-9.32164294712959,-76.6359465569257),
                        new LatLng(-9.32149042585844,-76.6358157992362),
                        new LatLng(-9.32133228008827,-76.6356132924556),
                        new LatLng(-9.32121747547802,-76.6353728994727),
                        new LatLng(-9.32114204188069,-76.6351643577218),
                        new LatLng(-9.32109042940997,-76.6349869966506),
                        new LatLng(-9.32104543391646,-76.6347955539822),
                        new LatLng(-9.32092831326643,-76.6345873475074),
                        new LatLng(-9.32079862029737,-76.6344710066914),
                        new LatLng(-9.32070399733547,-76.6342252492904),
                        new LatLng(-9.32059580951194,-76.6339570283889),
                        new LatLng(-9.32053460231864,-76.6337276995182),
                        new LatLng(-9.32041615809788,-76.6334329918026),
                        new LatLng(-9.32038935926024,-76.6332894936203),
                        new LatLng(-9.32027356178997,-76.6331754997372),
                        new LatLng(-9.32018555568689,-76.6330350190401),
                        new LatLng(-9.32006413369621,-76.6327788680791),
                        new LatLng(-9.31995164462084,-76.632498241961),
                        new LatLng(-9.31978158753807,-76.6322685778141),
                        new LatLng(-9.31970218342436,-76.6321351379156),
                        new LatLng(-9.31960226655563,-76.6318987682461),
                        new LatLng(-9.31945073694517,-76.6316717863082),
                        new LatLng(-9.31933030725153,-76.6314562037587),
                        new LatLng(-9.31930946364648,-76.6313261166214),
                        new LatLng(-9.3191675285886,-76.631137356162),
                        new LatLng(-9.31896868707014,-76.6309361904859),
                        new LatLng(-9.31884428699436,-76.6308419778943),
                        new LatLng(-9.31879002737295,-76.6307628527283),
                        new LatLng(-9.31851244295838,-76.6305519640445),
                        new LatLng(-9.31843998644197,-76.6305040195584),
                        new LatLng(-9.31828878717894,-76.6304352879524),
                        new LatLng(-9.31819681035235,-76.6303474456071),
                        new LatLng(-9.31812964740251,-76.6302458569407),
                        new LatLng(-9.31798572675221,-76.6301469504833),
                        new LatLng(-9.31788250086996,-76.6301154345273),
                        new LatLng(-9.31772038323687,-76.6299836710095),
                        new LatLng(-9.31749341842412,-76.6299236565828),
                        new LatLng(-9.31740408820955,-76.6298857703804),
                        new LatLng(-9.3172350224446,-76.6297449544072),
                        new LatLng(-9.31707919071792,-76.6295585408806),
                        new LatLng(-9.31702625423446,-76.629409007728),
                        new LatLng(-9.31688531080805,-76.6292641684412),
                        new LatLng(-9.31675892487028,-76.6291739791631),
                        new LatLng(-9.31664808899717,-76.6290170699357),
                        new LatLng(-9.3165954833018,-76.6288896650075),
                        new LatLng(-9.31661268768102,-76.6288363561034),
                        new LatLng(-9.31655181064301,-76.6287927702069),
                        new LatLng(-9.31649920493314,-76.628637202084),
                        new LatLng(-9.31631227262955,-76.6283699870109),
                        new LatLng(-9.31608431433987,-76.6280739381909),
                        new LatLng(-9.31591028512451,-76.627889201045),
                        new LatLng(-9.31584576856827,-76.6277859359979),
                        new LatLng(-9.31581367571277,-76.6276551783084),
                        new LatLng(-9.31580242666995,-76.6275532543659),
                        new LatLng(-9.3157346015511,-76.6274872049689),
                        new LatLng(-9.31572732275707,-76.6274181380867),
                        new LatLng(-9.31565486566282,-76.6273916512727),
                        new LatLng(-9.31562409620729,-76.6273081675172),
                        new LatLng(-9.31563071329473,-76.6272166371345),
                        new LatLng(-9.31554568371159,-76.6271660104393),
                        new LatLng(-9.31555759447156,-76.6271063312888),
                        new LatLng(-9.31546396265304,-76.6269420459866),
                        new LatLng(-9.31540573225147,-76.626879684627),
                        new LatLng(-9.31543584001713,-76.6268200054764),
                        new LatLng(-9.3153984534506,-76.6267204284667),
                        new LatLng(-9.31547686597934,-76.6265993937849),
                        new LatLng(-9.31544311881723,-76.6265303269028),
                        new LatLng(-9.31546429350756,-76.6263791173696),
                        new LatLng(-9.31541268019762,-76.6261387243867),
                        new LatLng(-9.31537132337326,-76.6260740160942),
                        new LatLng(-9.31542789950776,-76.6259556636214),
                        new LatLng(-9.31539315977713,-76.6258349642157),
                        new LatLng(-9.31538918952198,-76.6257883608341),
                        new LatLng(-9.31534088474739,-76.6257290169596),
                        new LatLng(-9.3153789330293,-76.6256187111139),
                        new LatLng(-9.31537430106477,-76.6255251690745),
                        new LatLng(-9.31542459096213,-76.6254071518778),
                        new LatLng(-9.31531143868289,-76.6252110153436),
                        new LatLng(-9.31532831227071,-76.6250849515199),
                        new LatLng(-9.31520391089818,-76.6246376931667),
                        new LatLng(-9.31506329759132,-76.6242578253149),
                        new LatLng(-9.31496602623532,-76.6239701583981),
                        new LatLng(-9.31482111171596,-76.6236362233757),
                        new LatLng(-9.31458918218886,-76.6232912242412),
                        new LatLng(-9.31448694786909,-76.6228365898132),
                        new LatLng(-9.31441548308411,-76.6225858032703),
                        new LatLng(-9.31449885866516,-76.6223450750112),
                        new LatLng(-9.31442143848342,-76.6222143173217),
                        new LatLng(-9.31423582849063,-76.6221831366419),
                        new LatLng(-9.31409719992901,-76.6219994053244),
                        new LatLng(-9.31392581657154,-76.621852889657),
                        new LatLng(-9.31391489832391,-76.6215866804122),
                        new LatLng(-9.31385170482336,-76.6213724389672),
                        new LatLng(-9.31358768159265,-76.6212778910994),
                        new LatLng(-9.31340107858745,-76.6211682558059),
                        new LatLng(-9.31315028928519,-76.6212805733084),
                        new LatLng(-9.31312845274111,-76.6209563612937),
                        new LatLng(-9.31328130852096,-76.6206589713692),
                        new LatLng(-9.31335939066842,-76.6203592345118),
                        new LatLng(-9.31376038854058,-76.6199300810694),
                        new LatLng(-9.31390596521196,-76.6195780411362),
                        new LatLng(-9.31393607310689,-76.6192957386374),
                        new LatLng(-9.31412631524007,-76.6189507395029),
                        new LatLng(-9.3146421190417,-76.6185011342167),
                        new LatLng(-9.31497098906027,-76.6181920096278),
                        new LatLng(-9.31519266183572,-76.6180669516324),
                        new LatLng(-9.315295888513,-76.6177021712064),
                        new LatLng(-9.31549142357768,-76.6174245625734),
                        new LatLng(-9.31565519651717,-76.6171070560812),
                        new LatLng(-9.31581069802501,-76.6169555112719),
                        new LatLng(-9.31557182121211,-76.6169441118836),
                        new LatLng(-9.31533989218346,-76.6168083250522),
                        new LatLng(-9.31519034585222,-76.6167875379323),
                        new LatLng(-9.31506726785017,-76.6164995357394),
                        new LatLng(-9.31496271768532,-76.6161766648292),
                        new LatLng(-9.3149736359002,-76.615983210504),
                        new LatLng(-9.31479695929022,-76.6157978028059),
                        new LatLng(-9.31469108562343,-76.6156224533915),
                        new LatLng(-9.31451705571394,-76.615539304912),
                        new LatLng(-9.31461862831423,-76.6151202097535),
                        new LatLng(-9.31455179553169,-76.6147202253341),
                        new LatLng(-9.31448992556815,-76.6144603863358),
                        new LatLng(-9.31456536060246,-76.6141747310757),
                        new LatLng(-9.31447239022889,-76.6140013933181),
                        new LatLng(-9.31411804384514,-76.6138816997408),
                        new LatLng(-9.31393574225091,-76.6137563064694),
                        new LatLng(-9.31374913943162,-76.6135259717703),
                        new LatLng(-9.31357047706443,-76.613543741405),
                        new LatLng(-9.3133742792115,-76.6135853156447),
                        new LatLng(-9.31320190292939,-76.6136644408106),
                        new LatLng(-9.31301033686542,-76.6136268898844),
                        new LatLng(-9.31273274785751,-76.6135511174798),
                        new LatLng(-9.31257393640697,-76.6135403886437),
                        new LatLng(-9.31247170149749,-76.6135749220848),
                        new LatLng(-9.31228013503317,-76.6134786978363),
                        new LatLng(-9.3121014719148,-76.6132396459579),
                        new LatLng(-9.31183049934426,-76.6129489615559),
                        new LatLng(-9.31164951998902,-76.6127890348434),
                        new LatLng(-9.31145332105745,-76.6125657409429),
                        new LatLng(-9.31132726401292,-76.6123514994978),
                        new LatLng(-9.31136663616556,-76.6121727973222),
                        new LatLng(-9.31131171366569,-76.6118740662932),
                        new LatLng(-9.31128987700672,-76.6116826236248),
                        new LatLng(-9.3112395865142,-76.6115421429276),
                        new LatLng(-9.31127697352578,-76.6114707291126),
                        new LatLng(-9.31110724307823,-76.6112118959426),
                        new LatLng(-9.31094876174771,-76.6111183539032),
                        new LatLng(-9.31076844365378,-76.610935293138),
                        new LatLng(-9.31063907777126,-76.6105869412422),
                        new LatLng(-9.31051004270009,-76.6102778166532),
                        new LatLng(-9.31039953570382,-76.6101078316569),
                        new LatLng(-9.31026520679304,-76.6098758205771),
                        new LatLng(-9.31012128290278,-76.6097950190305),
                        new LatLng(-9.3098479927779,-76.6097329929471),
                        new LatLng(-9.30953235234352,-76.6095677018165),
                        new LatLng(-9.309211417857,-76.609529480338),
                        new LatLng(-9.30906286148459,-76.6095140576362),
                        new LatLng(-9.30878129901088,-76.6095137223601),
                        new LatLng(-9.30866913716323,-76.6094245389103),
                        new LatLng(-9.30835746599942,-76.6093564778566),
                        new LatLng(-9.30817185278576,-76.6093296557664),
                        new LatLng(-9.30803752301885,-76.6092605888843),
                        new LatLng(-9.30797399759387,-76.6090835630893),
                        new LatLng(-9.30796109399051,-76.6088830679655),
                        new LatLng(-9.30788433408374,-76.6088545694947),
                        new LatLng(-9.30786282807236,-76.6087432578206),
                        new LatLng(-9.30799418015201,-76.6088311001658),
                        new LatLng(-9.30807325606533,-76.6088364645838),
                        new LatLng(-9.30804645628081,-76.6086041182279),
                        new LatLng(-9.30798657033513,-76.6086406633257),
                        new LatLng(-9.30777250281011,-76.6082249209284),
                        new LatLng(-9.30752700326145,-76.6082406789064),
                        new LatLng(-9.30727290112117,-76.6081058979034),
                        new LatLng(-9.30702111483385,-76.6079235076904),
                        new LatLng(-9.30706710472116,-76.6078292950987),
                        new LatLng(-9.30689571791615,-76.607703231275),
                        new LatLng(-9.30689571791615,-76.6074323281645),
                        new LatLng(-9.30683682436145,-76.6072338446974),
                        new LatLng(-9.30653772435745,-76.6069984808564),
                        new LatLng(-9.30623035251597,-76.6069686412811),
                        new LatLng(-9.30603613572333,-76.6070085391402),
                        new LatLng(-9.30569832397519,-76.6068268194794),
                        new LatLng(-9.30551270934979,-76.606844253838),
                        new LatLng(-9.30542436866458,-76.6067745164036),
                        new LatLng(-9.30523709957271,-76.6068080440163),
                        new LatLng(-9.30504718346545,-76.6068181023001),
                        new LatLng(-9.30491913892378,-76.6067812219262),
                        new LatLng(-9.30497803280148,-76.6067108139395),
                        new LatLng(-9.3049674451388,-76.6065847501158),
                        new LatLng(-9.30486090676528,-76.6064788028597),
                        new LatLng(-9.30487612653492,-76.606220640242),
                        new LatLng(-9.30491185990449,-76.606117375195),
                        new LatLng(-9.30475370662997,-76.6060466319322),
                        new LatLng(-9.30480697583713,-76.6060174629092),
                        new LatLng(-9.30468455590797,-76.6058340668678),
                        new LatLng(-9.3045158148543,-76.6053784266114),
                        new LatLng(-9.30440696028823,-76.6047333553433),
                        new LatLng(-9.30442118748268,-76.6039803251624),
                        new LatLng(-9.30430968596648,-76.6036725416779),
                        new LatLng(-9.30437949849266,-76.6033637523651),
                        new LatLng(-9.3044565900338,-76.6030241176486),
                        new LatLng(-9.3045154839894,-76.6027119755744),
                        new LatLng(-9.30459555328393,-76.6023334488272),
                        new LatLng(-9.30470407692657,-76.6022499650716),
                        new LatLng(-9.30467264477744,-76.6020672395825),
                        new LatLng(-9.30468753369053,-76.6018985956907),
                        new LatLng(-9.30464286694933,-76.6018204763531),
                        new LatLng(-9.30452474820623,-76.6016521677374),
                        new LatLng(-9.30443111343196,-76.6016702726483),
                        new LatLng(-9.30433979468802,-76.6016196459531),
                        new LatLng(-9.30450721236702,-76.6015328094363),
                        new LatLng(-9.30468885714945,-76.6013363376259),
                        new LatLng(-9.3047351782082,-76.6011231020092),
                        new LatLng(-9.3047222744853,-76.6008548811078),
                        new LatLng(-9.30462830889912,-76.6005608439445),
                        new LatLng(-9.30450952842131,-76.6003201156854),
                        new LatLng(-9.30432358229982,-76.6000351309776),
                        new LatLng(-9.3043497206396,-76.5997665748),
                        new LatLng(-9.30426204139087,-76.5994266048073),
                        new LatLng(-9.30433053046628,-76.599161401391),
                        new LatLng(-9.30450919755642,-76.5985485166311),
                        new LatLng(-9.304485375283,-76.5983859077095),
                        new LatLng(-9.3044499727348,-76.5980932116508),
                        new LatLng(-9.30437486638234,-76.5980301797389),
                        new LatLng(-9.30428586367948,-76.5978746116161),
                        new LatLng(-9.3042828858935,-76.5977428480982),
                        new LatLng(-9.30412142368189,-76.5975423529744),
                        new LatLng(-9.30394308725909,-76.5973425284028),
                        new LatLng(-9.30385276098428,-76.5973110124468),
                        new LatLng(-9.30360428089181,-76.5972463041543),
                        new LatLng(-9.30346664071135,-76.5971098467707),
                        new LatLng(-9.30323007152462,-76.596817150712),
                        new LatLng(-9.30317845640806,-76.5964295715093),
                        new LatLng(-9.30306761622819,-76.596040315926),
                        new LatLng(-9.30313775986848,-76.5957543253898),
                        new LatLng(-9.30330120773045,-76.5954153612256),
                        new LatLng(-9.30329822993608,-76.5951907262206),
                        new LatLng(-9.30328003230325,-76.5948544442653),
                        new LatLng(-9.30322643199741,-76.5947025641798),
                        new LatLng(-9.30304213952517,-76.5945265442132),
                        new LatLng(-9.3029385783626,-76.5942636877298),
                        new LatLng(-9.30287571374416,-76.594034358859),
                        new LatLng(-9.30282839983969,-76.5938425809144),
                        new LatLng(-9.30264741582433,-76.5937275812029),
                        new LatLng(-9.30263153422436,-76.5934757888317),
                        new LatLng(-9.30261432915691,-76.5933326259255),
                        new LatLng(-9.30236452471693,-76.5930848568677),
                        new LatLng(-9.30227155109835,-76.5928629040718),
                        new LatLng(-9.30233739362795,-76.5925376862287),
                        new LatLng(-9.30215905629549,-76.5921309962868),
                        new LatLng(-9.30209321373234,-76.5917387232184),
                        new LatLng(-9.3019661607111,-76.5915221348404),
                        new LatLng(-9.30198138060671,-76.5910416841506),
                        new LatLng(-9.3019651681092,-76.590556204319),
                        new LatLng(-9.3020723690992,-76.5898313373327),
                        new LatLng(-9.30226063248672,-76.5897461771965),
                        new LatLng(-9.3022728745664,-76.5895771980285),
                        new LatLng(-9.30217196011296,-76.5895094722509),
                        new LatLng(-9.30220438508805,-76.5893767029047),
                        new LatLng(-9.30212365351183,-76.5891718491911),
                        new LatLng(-9.3020181068738,-76.5889549255371),
                        new LatLng(-9.30178749232193,-76.5887852758169),
                        new LatLng(-9.30165382183777,-76.5886749699712),
                        new LatLng(-9.30172396576157,-76.5884037315845),
                        new LatLng(-9.30164952055895,-76.5882387757301),
                        new LatLng(-9.30158731744418,-76.5881127119064),
                        new LatLng(-9.30144504432095,-76.5878592431545),
                        new LatLng(-9.30106520788522,-76.5875088796019),
                        new LatLng(-9.3006612176347,-76.5874116495251),
                        new LatLng(-9.30032704025268,-76.5873720869421),
                        new LatLng(-9.30002264077541,-76.5875987336039),
                        new LatLng(-9.29957232758478,-76.5878213569521),
                        new LatLng(-9.29906311895098,-76.5878035873174),
                        new LatLng(-9.29881695154847,-76.5876476839184),
                        new LatLng(-9.29856185046879,-76.5874441713094),
                        new LatLng(-9.29817770958162,-76.5873026847839),
                        new LatLng(-9.29804469946039,-76.5866777300834),
                        new LatLng(-9.29803576594297,-76.5859924256801),
                        new LatLng(-9.29805925778458,-76.5858616679906),
                        new LatLng(-9.29815521036068,-76.5857483446598),
                        new LatLng(-9.29816745258402,-76.5855404734611),
                        new LatLng(-9.29830410223704,-76.5854425728321),
                        new LatLng(-9.2985201607805,-76.585040576756),
                        new LatLng(-9.29867434642828,-76.5846040472388),
                        new LatLng(-9.29881992938098,-76.5843418613076),
                        new LatLng(-9.29894532919397,-76.5838543698191),
                        new LatLng(-9.29917925431867,-76.583436615765),
                        new LatLng(-9.29924178873143,-76.5825152769684),
                        new LatLng(-9.29943997973304,-76.5819577127695),
                        new LatLng(-9.29963585453584,-76.5816304832696),
                        new LatLng(-9.29940590015311,-76.5811949595808),
                        new LatLng(-9.29929141920983,-76.5809321030974),
                        new LatLng(-9.29930299965377,-76.5806397423148),
                        new LatLng(-9.29929704399694,-76.5805173665285),
                        new LatLng(-9.2994254214663,-76.5804027020931),
                        new LatLng(-9.29941847320241,-76.5801663324236),
                        new LatLng(-9.2996553758362,-76.5799624845385),
                        new LatLng(-9.29989360178664,-76.5797807648777),
                        new LatLng(-9.29985654442723,-76.5794374421238),
                        new LatLng(-9.29981121533047,-76.5790022537112),
                        new LatLng(-9.29978937795327,-76.5787608548998),
                        new LatLng(-9.2998227957572,-76.5782663226127),
                        new LatLng(-9.29997698083098,-76.5779997780919),
                        new LatLng(-9.29996738562451,-76.5778509154915),
                        new LatLng(-9.29979500270298,-76.5777540206431),
                        new LatLng(-9.29962261975532,-76.5776571258902),
                        new LatLng(-9.29920837087204,-76.5776795893907),
                        new LatLng(-9.29867037598326,-76.577667184174),
                        new LatLng(-9.29830277875397,-76.5777090936899),
                        new LatLng(-9.29800962713145,-76.5776614844799),
                        new LatLng(-9.2977383127699,-76.5775072574615),
                        new LatLng(-9.29729759191962,-76.57729703933),
                        new LatLng(-9.29693694156169,-76.5774469077587),
                        new LatLng(-9.29669904447079,-76.5772641822695),
                        new LatLng(-9.29677349072663,-76.5768541395664),
                        new LatLng(-9.29659779753743,-76.576554402709),
                        new LatLng(-9.29625435183945,-76.5762127563357),
                        new LatLng(-9.29606244561867,-76.5755375102162),
                        new LatLng(-9.2956243696446,-76.5748991444706),
                        new LatLng(-9.29569881612902,-76.5746617689728),
                        new LatLng(-9.29559028969312,-76.574310734868),
                        new LatLng(-9.29514824264322,-76.5739855170249),
                        new LatLng(-9.29477203898817,-76.5737152844667),
                        new LatLng(-9.29474656168116,-76.573306582868),
                        new LatLng(-9.29446234068907,-76.5730319917201),
                        new LatLng(-9.29412848826278,-76.5727362781763),
                        new LatLng(-9.29379430464312,-76.5721062943339),
                        new LatLng(-9.29348063497526,-76.5717934817075),
                        new LatLng(-9.29267759963995,-76.5713418647646),
                        new LatLng(-9.29233249571417,-76.5712104365229),
                        new LatLng(-9.29197217111808,-76.5707963705062),
                        new LatLng(-9.29151622265885,-76.5701673924922),
                        new LatLng(-9.29126938819451,-76.5696768835186),
                        new LatLng(-9.29119824954314,-76.5692346543073),
                        new LatLng(-9.29084189431387,-76.5687679499387),
                        new LatLng(-9.29043193645679,-76.5681895986199),
                        new LatLng(-9.29042201011047,-76.5679488703608),
                        new LatLng(-9.2899184131049,-76.5673618018627),
                        new LatLng(-9.28937246283942,-76.5668954327702),
                        new LatLng(-9.28875074025843,-76.566799879074),
                        new LatLng(-9.2883232433039,-76.5663589909672),
                        new LatLng(-9.28764030591029,-76.5654658153653),
                        new LatLng(-9.28685446302773,-76.5646883100271),
                        new LatLng(-9.28667909574392,-76.5643828734755),
                        new LatLng(-9.28590979482981,-76.5642105415463),
                        new LatLng(-9.2856864490868,-76.5643594041466),
                        new LatLng(-9.28487876351539,-76.5640177577733),
                        new LatLng(-9.28464152000123,-76.5640415623784),
                        new LatLng(-9.28362967626474,-76.5634843334555),
                        new LatLng(-9.28327165891279,-76.5632781386375),
                        new LatLng(-9.28292985457475,-76.5630766376852),
                        new LatLng(-9.28199377883923,-76.5627162158489),
                        new LatLng(-9.281670833785,-76.5627661719918),
                        new LatLng(-9.28149281681438,-76.5622629225254),
                        new LatLng(-9.28101336173581,-76.5614062920212),
                        new LatLng(-9.28107027431222,-76.5610210597515),
                        new LatLng(-9.28100078802583,-76.5609654039144),
                        new LatLng(-9.28083964596582,-76.5608088299632),
                        new LatLng(-9.28057857581819,-76.5606649965047),
                        new LatLng(-9.28045548564313,-76.5604269504547),
                        new LatLng(-9.27948631444885,-76.5599508583545),
                        new LatLng(-9.27936289298029,-76.559936441476),
                        new LatLng(-9.27984565917558,-76.5594720846821),
                        new LatLng(-9.28032842477194,-76.5590077266097),
                        new LatLng(-9.27988933645896,-76.5577403828501),
                        new LatLng(-9.27814422796261,-76.5583344921469),
                        new LatLng(-9.27686831454892,-76.5580193325877),
                        new LatLng(-9.2764394795379,-76.5563654154539),
                        new LatLng(-9.27631539523553,-76.5543410181999),
                        new LatLng(-9.2747896510659,-76.5543574467301),
                        new LatLng(-9.27312624815995,-76.5554521232843),
                        new LatLng(-9.27244956817488,-76.5537365153431),
                        new LatLng(-9.27064783931003,-76.552502028644),
                        new LatLng(-9.27012038929357,-76.551754027605),
                        new LatLng(-9.27011807301195,-76.5508108958601),
                        new LatLng(-9.26901452853081,-76.5509587526321),
                        new LatLng(-9.26778987135925,-76.551661491394),
                        new LatLng(-9.26623430903663,-76.5531263127923),
                        new LatLng(-9.26507946252391,-76.5540499985218),
                        new LatLng(-9.26426444964787,-76.5535182505846),
                        new LatLng(-9.26316518852256,-76.554008424282),
                        new LatLng(-9.26164732893083,-76.5534622594714),
                        new LatLng(-9.26060431369897,-76.5535762533545),
                        new LatLng(-9.25939071099445,-76.5529853271403),
                        new LatLng(-9.25817710732207,-76.5523944050073),
                        new LatLng(-9.25682534299328,-76.5521312132477),
                        new LatLng(-9.25585279746179,-76.552398763597)

                ).color(Color.rgb(204, 80, 178)).width(5));
    }
    public void ruta9(GoogleMap googleMap){
       googleMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .add(
                        new LatLng(-9.32727295356873,-76.6589692980051),
                        new LatLng(-9.32681903621898,-76.6586568206548),
                        new LatLng(-9.32657619681122,-76.6586454212665),
                        new LatLng(-9.32676940965075,-76.6579510644078),
                        new LatLng(-9.32699570674456,-76.6577220708131),
                        new LatLng(-9.32719388200291,-76.6569653525948),
                        new LatLng(-9.32735599523571,-76.6566934436559),
                        new LatLng(-9.32771859925516,-76.6562354564666),
                        new LatLng(-9.32792405264269,-76.6557446122169),
                        new LatLng(-9.32793761719381,-76.6554271057248),
                        new LatLng(-9.32769577109352,-76.6544380411505),
                        new LatLng(-9.32781752127167,-76.6537467017769),
                        new LatLng(-9.32796937809193,-76.6534382477402),
                        new LatLng(-9.32805870560237,-76.6530365869402),
                        new LatLng(-9.32822280346358,-76.6529353335976),
                        new LatLng(-9.32838690129616,-76.6528340801596),
                        new LatLng(-9.32858110565906,-76.6523687168955),
                        new LatLng(-9.32881931188498,-76.6520220413803),
                        new LatLng(-9.32886397553422,-76.6516824066638),
                        new LatLng(-9.32916272557432,-76.6508998721837),
                        new LatLng(-9.32939431457662,-76.6506769135594),
                        new LatLng(-9.32960042865933,-76.6503768414258),
                        new LatLng(-9.32986080052869,-76.6501625999808),
                        new LatLng(-9.3299382172859,-76.6500201076269),
                        new LatLng(-9.32993259299216,-76.649891026318),
                        new LatLng(-9.32985054446127,-76.6497146710753),
                        new LatLng(-9.32974996881646,-76.6493052989244),
                        new LatLng(-9.32966428099091,-76.6490625590085),
                        new LatLng(-9.32948099499221,-76.6487903147935),
                        new LatLng(-9.32943302300915,-76.6483477503061),
                        new LatLng(-9.32921665274145,-76.6481791064143),
                        new LatLng(-9.32888945035382,-76.64773453027),
                        new LatLng(-9.32878060338433,-76.6474917903542),
                        new LatLng(-9.32886199048326,-76.647327505052),
                        new LatLng(-9.32891591769688,-76.6471960768103),
                        new LatLng(-9.32888647277759,-76.6470861062407),
                        new LatLng(-9.32878655853855,-76.6469791531562),
                        new LatLng(-9.32872833035954,-76.6468038037419),
                        new LatLng(-9.32869326111069,-76.6466562822461),
                        new LatLng(-9.32857746639601,-76.6465114429593),
                        new LatLng(-9.32854074292133,-76.6463096067309),
                        new LatLng(-9.32850931291739,-76.6461560502648),
                        new LatLng(-9.32841006025482,-76.6460252925753),
                        new LatLng(-9.32838888634983,-76.6458874940872),
                        new LatLng(-9.3283141159875,-76.6457104682922),
                        new LatLng(-9.32820063704413,-76.6455200314521),
                        new LatLng(-9.3281109787275,-76.6452467814087),
                        new LatLng(-9.32802330544356,-76.6449896246195),
                        new LatLng(-9.3280223129157,-76.6448535025119),
                        new LatLng(-9.32794324151973,-76.6447552666068),
                        new LatLng(-9.32797334820399,-76.6446711122989),
                        new LatLng(-9.32796805472123,-76.6445510834455),
                        new LatLng(-9.32792967696883,-76.6443683579564),
                        new LatLng(-9.32792603769899,-76.6442158073186),
                        new LatLng(-9.32788137392964,-76.6440458223223),
                        new LatLng(-9.32778278277408,-76.6438436508178),
                        new LatLng(-9.32771826841226,-76.6437155753374),
                        new LatLng(-9.32772223852718,-76.6436334326863),
                        new LatLng(-9.32775995461666,-76.6435442492365),
                        new LatLng(-9.32770238795213,-76.6433595120906),
                        new LatLng(-9.32758923965279,-76.6432140022516),
                        new LatLng(-9.32750685972756,-76.6431026905775),
                        new LatLng(-9.32749064841467,-76.6429705917835),
                        new LatLng(-9.32740628340691,-76.6427892073988),
                        new LatLng(-9.32730405284076,-76.6426202282309),
                        new LatLng(-9.32718561091759,-76.6423697769641),
                        new LatLng(-9.32701820410842,-76.6420452296733),
                        new LatLng(-9.3268478196253,-76.6418145596981),
                        new LatLng(-9.32676808627549,-76.6417002305388),
                        new LatLng(-9.32670588763312,-76.6415248811244),
                        new LatLng(-9.32663707210097,-76.6413488611578),
                        new LatLng(-9.32647528937817,-76.6411540657281),
                        new LatLng(-9.32633931243042,-76.6409984976053),
                        new LatLng(-9.3262218627127,-76.6408275067806),
                        new LatLng(-9.32603658983818,-76.6406186297535),
                        new LatLng(-9.32579308819633,-76.640400364995),
                        new LatLng(-9.32541129316031,-76.6401029750704),
                        new LatLng(-9.32491701023144,-76.6396654397249),
                        new LatLng(-9.32464306999341,-76.6394066065549),
                        new LatLng(-9.32428079362371,-76.6391068696975),
                        new LatLng(-9.32397972347781,-76.6388212144374),
                        new LatLng(-9.323721663146,-76.6386545822024),
                        new LatLng(-9.32352216252794,-76.6384332999587),
                        new LatLng(-9.32337857494785,-76.6382338106632),
                        new LatLng(-9.32326707948199,-76.6380175575613),
                        new LatLng(-9.32314367347974,-76.6377952694892),
                        new LatLng(-9.32304838945889,-76.63763333112),
                        new LatLng(-9.32294086405687,-76.637498550117),
                        new LatLng(-9.32278271894389,-76.6374398767948),
                        new LatLng(-9.3226603053135,-76.6373275592923),
                        new LatLng(-9.32255311063974,-76.6371672973036),
                        new LatLng(-9.32243235116845,-76.6370479390025),
                        new LatLng(-9.32224641458574,-76.6368293389678),
                        new LatLng(-9.32210481157019,-76.6366942226886),
                        new LatLng(-9.32196287764883,-76.6364786401391),
                        new LatLng(-9.32184542645816,-76.6363076493144),
                        new LatLng(-9.32177793336168,-76.6361467167735),
                        new LatLng(-9.32164294712959,-76.6359465569257),
                        new LatLng(-9.32149042585844,-76.6358157992362),
                        new LatLng(-9.32133228008827,-76.6356132924556),
                        new LatLng(-9.32121747547802,-76.6353728994727),
                        new LatLng(-9.32114204188069,-76.6351643577218),
                        new LatLng(-9.32109042940997,-76.6349869966506),
                        new LatLng(-9.32104543391646,-76.6347955539822),
                        new LatLng(-9.32092831326643,-76.6345873475074),
                        new LatLng(-9.32079862029737,-76.6344710066914),
                        new LatLng(-9.32070399733547,-76.6342252492904),
                        new LatLng(-9.32059580951194,-76.6339570283889),
                        new LatLng(-9.32053460231864,-76.6337276995182),
                        new LatLng(-9.32041615809788,-76.6334329918026),
                        new LatLng(-9.32038935926024,-76.6332894936203),
                        new LatLng(-9.32027356178997,-76.6331754997372),
                        new LatLng(-9.32018555568689,-76.6330350190401),
                        new LatLng(-9.32006413369621,-76.6327788680791),
                        new LatLng(-9.31995164462084,-76.632498241961),
                        new LatLng(-9.31978158753807,-76.6322685778141),
                        new LatLng(-9.31970218342436,-76.6321351379156),
                        new LatLng(-9.31960226655563,-76.6318987682461),
                        new LatLng(-9.31945073694517,-76.6316717863082),
                        new LatLng(-9.31933030725153,-76.6314562037587),
                        new LatLng(-9.31930946364648,-76.6313261166214),
                        new LatLng(-9.3191675285886,-76.631137356162),
                        new LatLng(-9.31896868707014,-76.6309361904859),
                        new LatLng(-9.31884428699436,-76.6308419778943),
                        new LatLng(-9.31879002737295,-76.6307628527283),
                        new LatLng(-9.31851244295838,-76.6305519640445),
                        new LatLng(-9.31843998644197,-76.6305040195584),
                        new LatLng(-9.31828878717894,-76.6304352879524),
                        new LatLng(-9.31819681035235,-76.6303474456071),
                        new LatLng(-9.31812964740251,-76.6302458569407),
                        new LatLng(-9.31798572675221,-76.6301469504833),
                        new LatLng(-9.31788250086996,-76.6301154345273),
                        new LatLng(-9.31772038323687,-76.6299836710095),
                        new LatLng(-9.31749341842412,-76.6299236565828),
                        new LatLng(-9.31740408820955,-76.6298857703804),
                        new LatLng(-9.3172350224446,-76.6297449544072),
                        new LatLng(-9.31707919071792,-76.6295585408806),
                        new LatLng(-9.31702625423446,-76.629409007728),
                        new LatLng(-9.31688531080805,-76.6292641684412),
                        new LatLng(-9.31675892487028,-76.6291739791631),
                        new LatLng(-9.31664808899717,-76.6290170699357),
                        new LatLng(-9.3165954833018,-76.6288896650075),
                        new LatLng(-9.31661268768102,-76.6288363561034),
                        new LatLng(-9.31655181064301,-76.6287927702069),
                        new LatLng(-9.31649920493314,-76.628637202084),
                        new LatLng(-9.31631227262955,-76.6283699870109),
                        new LatLng(-9.31608431433987,-76.6280739381909),
                        new LatLng(-9.31591028512451,-76.627889201045),
                        new LatLng(-9.31584576856827,-76.6277859359979),
                        new LatLng(-9.31581367571277,-76.6276551783084),
                        new LatLng(-9.31580242666995,-76.6275532543659),
                        new LatLng(-9.3157346015511,-76.6274872049689),
                        new LatLng(-9.31572732275707,-76.6274181380867),
                        new LatLng(-9.31565486566282,-76.6273916512727),
                        new LatLng(-9.31562409620729,-76.6273081675172),
                        new LatLng(-9.31563071329473,-76.6272166371345),
                        new LatLng(-9.31554568371159,-76.6271660104393),
                        new LatLng(-9.31555759447156,-76.6271063312888),
                        new LatLng(-9.31546396265304,-76.6269420459866),
                        new LatLng(-9.31540573225147,-76.626879684627),
                        new LatLng(-9.31543584001713,-76.6268200054764),
                        new LatLng(-9.3153984534506,-76.6267204284667),
                        new LatLng(-9.31547686597934,-76.6265993937849),
                        new LatLng(-9.31544311881723,-76.6265303269028),
                        new LatLng(-9.31546429350756,-76.6263791173696),
                        new LatLng(-9.31541268019762,-76.6261387243867),
                        new LatLng(-9.31537132337326,-76.6260740160942),
                        new LatLng(-9.31542789950776,-76.6259556636214),
                        new LatLng(-9.31539315977713,-76.6258349642157),
                        new LatLng(-9.31538918952198,-76.6257883608341),
                        new LatLng(-9.31534088474739,-76.6257290169596),
                        new LatLng(-9.3153789330293,-76.6256187111139),
                        new LatLng(-9.31537430106477,-76.6255251690745),
                        new LatLng(-9.31542459096213,-76.6254071518778),
                        new LatLng(-9.31531143868289,-76.6252110153436),
                        new LatLng(-9.31532831227071,-76.6250849515199),
                        new LatLng(-9.31520391089818,-76.6246376931667),
                        new LatLng(-9.31506329759132,-76.6242578253149),
                        new LatLng(-9.31496602623532,-76.6239701583981),
                        new LatLng(-9.31482111171596,-76.6236362233757),
                        new LatLng(-9.31458918218886,-76.6232912242412),
                        new LatLng(-9.31448694786909,-76.6228365898132),
                        new LatLng(-9.31441548308411,-76.6225858032703),
                        new LatLng(-9.31449885866516,-76.6223450750112),
                        new LatLng(-9.31442143848342,-76.6222143173217),
                        new LatLng(-9.31423582849063,-76.6221831366419),
                        new LatLng(-9.31409719992901,-76.6219994053244),
                        new LatLng(-9.31392581657154,-76.621852889657),
                        new LatLng(-9.31391489832391,-76.6215866804122),
                        new LatLng(-9.31385170482336,-76.6213724389672),
                        new LatLng(-9.31358768159265,-76.6212778910994),
                        new LatLng(-9.31340107858745,-76.6211682558059),
                        new LatLng(-9.31315028928519,-76.6212805733084),
                        new LatLng(-9.31312845274111,-76.6209563612937),
                        new LatLng(-9.31328130852096,-76.6206589713692),
                        new LatLng(-9.31335939066842,-76.6203592345118),
                        new LatLng(-9.31376038854058,-76.6199300810694),
                        new LatLng(-9.31390596521196,-76.6195780411362),
                        new LatLng(-9.31393607310689,-76.6192957386374),
                        new LatLng(-9.31412631524007,-76.6189507395029),
                        new LatLng(-9.3146421190417,-76.6185011342167),
                        new LatLng(-9.31497098906027,-76.6181920096278),
                        new LatLng(-9.31519266183572,-76.6180669516324),
                        new LatLng(-9.315295888513,-76.6177021712064),
                        new LatLng(-9.31549142357768,-76.6174245625734),
                        new LatLng(-9.31565519651717,-76.6171070560812),
                        new LatLng(-9.31581069802501,-76.6169555112719),
                        new LatLng(-9.31557182121211,-76.6169441118836),
                        new LatLng(-9.31533989218346,-76.6168083250522),
                        new LatLng(-9.31519034585222,-76.6167875379323),
                        new LatLng(-9.31506726785017,-76.6164995357394),
                        new LatLng(-9.31496271768532,-76.6161766648292),
                        new LatLng(-9.3149736359002,-76.615983210504),
                        new LatLng(-9.31479695929022,-76.6157978028059),
                        new LatLng(-9.31469108562343,-76.6156224533915),
                        new LatLng(-9.31451705571394,-76.615539304912),
                        new LatLng(-9.31461862831423,-76.6151202097535),
                        new LatLng(-9.31455179553169,-76.6147202253341),
                        new LatLng(-9.31448992556815,-76.6144603863358),
                        new LatLng(-9.31456536060246,-76.6141747310757),
                        new LatLng(-9.31447239022889,-76.6140013933181),
                        new LatLng(-9.31411804384514,-76.6138816997408),
                        new LatLng(-9.31393574225091,-76.6137563064694),
                        new LatLng(-9.31374913943162,-76.6135259717703),
                        new LatLng(-9.31357047706443,-76.613543741405),
                        new LatLng(-9.3133742792115,-76.6135853156447),
                        new LatLng(-9.31320190292939,-76.6136644408106),
                        new LatLng(-9.31301033686542,-76.6136268898844),
                        new LatLng(-9.31273274785751,-76.6135511174798),
                        new LatLng(-9.31257393640697,-76.6135403886437),
                        new LatLng(-9.31247170149749,-76.6135749220848),
                        new LatLng(-9.31228013503317,-76.6134786978363),
                        new LatLng(-9.3121014719148,-76.6132396459579),
                        new LatLng(-9.31183049934426,-76.6129489615559),
                        new LatLng(-9.31164951998902,-76.6127890348434),
                        new LatLng(-9.31145332105745,-76.6125657409429),
                        new LatLng(-9.31132726401292,-76.6123514994978),
                        new LatLng(-9.31136663616556,-76.6121727973222),
                        new LatLng(-9.31131171366569,-76.6118740662932),
                        new LatLng(-9.31128987700672,-76.6116826236248),
                        new LatLng(-9.3112395865142,-76.6115421429276),
                        new LatLng(-9.31127697352578,-76.6114707291126),
                        new LatLng(-9.31110724307823,-76.6112118959426),
                        new LatLng(-9.31094876174771,-76.6111183539032),
                        new LatLng(-9.31076844365378,-76.610935293138),
                        new LatLng(-9.31063907777126,-76.6105869412422),
                        new LatLng(-9.31051004270009,-76.6102778166532),
                        new LatLng(-9.31039953570382,-76.6101078316569),
                        new LatLng(-9.31026520679304,-76.6098758205771),
                        new LatLng(-9.31012128290278,-76.6097950190305),
                        new LatLng(-9.3098479927779,-76.6097329929471),
                        new LatLng(-9.30953235234352,-76.6095677018165),
                        new LatLng(-9.309211417857,-76.609529480338),
                        new LatLng(-9.30906286148459,-76.6095140576362),
                        new LatLng(-9.30878129901088,-76.6095137223601),
                        new LatLng(-9.30866913716323,-76.6094245389103),
                        new LatLng(-9.30835746599942,-76.6093564778566),
                        new LatLng(-9.30817185278576,-76.6093296557664),
                        new LatLng(-9.30803752301885,-76.6092605888843),
                        new LatLng(-9.30797399759387,-76.6090835630893),
                        new LatLng(-9.30796109399051,-76.6088830679655),
                        new LatLng(-9.30788433408374,-76.6088545694947),
                        new LatLng(-9.30786282807236,-76.6087432578206),
                        new LatLng(-9.30799418015201,-76.6088311001658),
                        new LatLng(-9.30807325606533,-76.6088364645838),
                        new LatLng(-9.30804645628081,-76.6086041182279),
                        new LatLng(-9.30798657033513,-76.6086406633257),
                        new LatLng(-9.30777250281011,-76.6082249209284),
                        new LatLng(-9.30752700326145,-76.6082406789064),
                        new LatLng(-9.30727290112117,-76.6081058979034),
                        new LatLng(-9.30702111483385,-76.6079235076904),
                        new LatLng(-9.30706710472116,-76.6078292950987),
                        new LatLng(-9.30689571791615,-76.607703231275),
                        new LatLng(-9.30689571791615,-76.6074323281645),
                        new LatLng(-9.30683682436145,-76.6072338446974),
                        new LatLng(-9.30653772435745,-76.6069984808564),
                        new LatLng(-9.30623035251597,-76.6069686412811),
                        new LatLng(-9.30603613572333,-76.6070085391402),
                        new LatLng(-9.30569832397519,-76.6068268194794),
                        new LatLng(-9.30551270934979,-76.606844253838),
                        new LatLng(-9.30542436866458,-76.6067745164036),
                        new LatLng(-9.30523709957271,-76.6068080440163),
                        new LatLng(-9.30504718346545,-76.6068181023001),
                        new LatLng(-9.30491913892378,-76.6067812219262),
                        new LatLng(-9.30497803280148,-76.6067108139395),
                        new LatLng(-9.3049674451388,-76.6065847501158),
                        new LatLng(-9.30486090676528,-76.6064788028597),
                        new LatLng(-9.30487612653492,-76.606220640242),
                        new LatLng(-9.30491185990449,-76.606117375195),
                        new LatLng(-9.30475370662997,-76.6060466319322),
                        new LatLng(-9.30480697583713,-76.6060174629092),
                        new LatLng(-9.30468455590797,-76.6058340668678),
                        new LatLng(-9.3045158148543,-76.6053784266114),
                        new LatLng(-9.30440696028823,-76.6047333553433),
                        new LatLng(-9.30442118748268,-76.6039803251624),
                        new LatLng(-9.30430968596648,-76.6036725416779),
                        new LatLng(-9.30437949849266,-76.6033637523651),
                        new LatLng(-9.3044565900338,-76.6030241176486),
                        new LatLng(-9.3045154839894,-76.6027119755744),
                        new LatLng(-9.30459555328393,-76.6023334488272),
                        new LatLng(-9.30470407692657,-76.6022499650716),
                        new LatLng(-9.30467264477744,-76.6020672395825),
                        new LatLng(-9.30468753369053,-76.6018985956907),
                        new LatLng(-9.30464286694933,-76.6018204763531),
                        new LatLng(-9.30452474820623,-76.6016521677374),
                        new LatLng(-9.30443111343196,-76.6016702726483),
                        new LatLng(-9.30433979468802,-76.6016196459531),
                        new LatLng(-9.30450721236702,-76.6015328094363),
                        new LatLng(-9.30468885714945,-76.6013363376259),
                        new LatLng(-9.3047351782082,-76.6011231020092),
                        new LatLng(-9.3047222744853,-76.6008548811078),
                        new LatLng(-9.30462830889912,-76.6005608439445),
                        new LatLng(-9.30450952842131,-76.6003201156854),
                        new LatLng(-9.30432358229982,-76.6000351309776),
                        new LatLng(-9.3043497206396,-76.5997665748),
                        new LatLng(-9.30426204139087,-76.5994266048073),
                        new LatLng(-9.30433053046628,-76.599161401391),
                        new LatLng(-9.30450919755642,-76.5985485166311),
                        new LatLng(-9.304485375283,-76.5983859077095),
                        new LatLng(-9.3044499727348,-76.5980932116508),
                        new LatLng(-9.30437486638234,-76.5980301797389),
                        new LatLng(-9.30428586367948,-76.5978746116161),
                        new LatLng(-9.3042828858935,-76.5977428480982),
                        new LatLng(-9.30412142368189,-76.5975423529744),
                        new LatLng(-9.30394308725909,-76.5973425284028),
                        new LatLng(-9.30385276098428,-76.5973110124468),
                        new LatLng(-9.30360428089181,-76.5972463041543),
                        new LatLng(-9.30346664071135,-76.5971098467707),
                        new LatLng(-9.30323007152462,-76.596817150712),
                        new LatLng(-9.30317845640806,-76.5964295715093),
                        new LatLng(-9.30306761622819,-76.596040315926),
                        new LatLng(-9.30313775986848,-76.5957543253898),
                        new LatLng(-9.30330120773045,-76.5954153612256),
                        new LatLng(-9.30329822993608,-76.5951907262206),
                        new LatLng(-9.30328003230325,-76.5948544442653),
                        new LatLng(-9.30322643199741,-76.5947025641798),
                        new LatLng(-9.30304213952517,-76.5945265442132),
                        new LatLng(-9.3029385783626,-76.5942636877298),
                        new LatLng(-9.30287571374416,-76.594034358859),
                        new LatLng(-9.30282839983969,-76.5938425809144),
                        new LatLng(-9.30264741582433,-76.5937275812029),
                        new LatLng(-9.30263153422436,-76.5934757888317),
                        new LatLng(-9.30261432915691,-76.5933326259255),
                        new LatLng(-9.30236452471693,-76.5930848568677),
                        new LatLng(-9.30227155109835,-76.5928629040718),
                        new LatLng(-9.30233739362795,-76.5925376862287),
                        new LatLng(-9.30215905629549,-76.5921309962868),
                        new LatLng(-9.30209321373234,-76.5917387232184),
                        new LatLng(-9.3019661607111,-76.5915221348404),
                        new LatLng(-9.30198138060671,-76.5910416841506),
                        new LatLng(-9.3019651681092,-76.590556204319),
                        new LatLng(-9.3020723690992,-76.5898313373327),
                        new LatLng(-9.30226063248672,-76.5897461771965),
                        new LatLng(-9.3022728745664,-76.5895771980285),
                        new LatLng(-9.30217196011296,-76.5895094722509),
                        new LatLng(-9.30220438508805,-76.5893767029047),
                        new LatLng(-9.30212365351183,-76.5891718491911),
                        new LatLng(-9.3020181068738,-76.5889549255371),
                        new LatLng(-9.30178749232193,-76.5887852758169),
                        new LatLng(-9.30165382183777,-76.5886749699712),
                        new LatLng(-9.30172396576157,-76.5884037315845),
                        new LatLng(-9.30164952055895,-76.5882387757301),
                        new LatLng(-9.30158731744418,-76.5881127119064),
                        new LatLng(-9.30144504432095,-76.5878592431545),
                        new LatLng(-9.30106520788522,-76.5875088796019),
                        new LatLng(-9.3006612176347,-76.5874116495251),
                        new LatLng(-9.30032704025268,-76.5873720869421),
                        new LatLng(-9.30002264077541,-76.5875987336039),
                        new LatLng(-9.29957232758478,-76.5878213569521),
                        new LatLng(-9.29906311895098,-76.5878035873174),
                        new LatLng(-9.29881695154847,-76.5876476839184),
                        new LatLng(-9.29856185046879,-76.5874441713094),
                        new LatLng(-9.29817770958162,-76.5873026847839),
                        new LatLng(-9.29804469946039,-76.5866777300834),
                        new LatLng(-9.29803576594297,-76.5859924256801),
                        new LatLng(-9.29805925778458,-76.5858616679906),
                        new LatLng(-9.29815521036068,-76.5857483446598),
                        new LatLng(-9.29816745258402,-76.5855404734611),
                        new LatLng(-9.29830410223704,-76.5854425728321),
                        new LatLng(-9.2985201607805,-76.585040576756),
                        new LatLng(-9.29867434642828,-76.5846040472388),
                        new LatLng(-9.29881992938098,-76.5843418613076),
                        new LatLng(-9.29894532919397,-76.5838543698191),
                        new LatLng(-9.29917925431867,-76.583436615765),
                        new LatLng(-9.29924178873143,-76.5825152769684),
                        new LatLng(-9.29943997973304,-76.5819577127695),
                        new LatLng(-9.29963585453584,-76.5816304832696),
                        new LatLng(-9.29940590015311,-76.5811949595808),
                        new LatLng(-9.29929141920983,-76.5809321030974),
                        new LatLng(-9.29930299965377,-76.5806397423148),
                        new LatLng(-9.29929704399694,-76.5805173665285),
                        new LatLng(-9.2994254214663,-76.5804027020931),
                        new LatLng(-9.29941847320241,-76.5801663324236),
                        new LatLng(-9.2996553758362,-76.5799624845385),
                        new LatLng(-9.29989360178664,-76.5797807648777),
                        new LatLng(-9.29985654442723,-76.5794374421238),
                        new LatLng(-9.29981121533047,-76.5790022537112),
                        new LatLng(-9.29978937795327,-76.5787608548998),
                        new LatLng(-9.2998227957572,-76.5782663226127),
                        new LatLng(-9.29997698083098,-76.5779997780919),
                        new LatLng(-9.29996738562451,-76.5778509154915),
                        new LatLng(-9.29979500270298,-76.5777540206431),
                        new LatLng(-9.29962261975532,-76.5776571258902),
                        new LatLng(-9.29920837087204,-76.5776795893907),
                        new LatLng(-9.29867037598326,-76.577667184174),
                        new LatLng(-9.29830277875397,-76.5777090936899),
                        new LatLng(-9.29800962713145,-76.5776614844799),
                        new LatLng(-9.2977383127699,-76.5775072574615),
                        new LatLng(-9.29729759191962,-76.57729703933),
                        new LatLng(-9.29693694156169,-76.5774469077587),
                        new LatLng(-9.29669904447079,-76.5772641822695),
                        new LatLng(-9.29677349072663,-76.5768541395664),
                        new LatLng(-9.29659779753743,-76.576554402709),
                        new LatLng(-9.29625435183945,-76.5762127563357),
                        new LatLng(-9.29606244561867,-76.5755375102162),
                        new LatLng(-9.2956243696446,-76.5748991444706),
                        new LatLng(-9.29569881612902,-76.5746617689728),
                        new LatLng(-9.29559028969312,-76.574310734868),
                        new LatLng(-9.29514824264322,-76.5739855170249),
                        new LatLng(-9.29477203898817,-76.5737152844667),
                        new LatLng(-9.29474656168116,-76.573306582868),
                        new LatLng(-9.29446234068907,-76.5730319917201),
                        new LatLng(-9.29412848826278,-76.5727362781763),
                        new LatLng(-9.29379430464312,-76.5721062943339),
                        new LatLng(-9.29348063497526,-76.5717934817075),
                        new LatLng(-9.29267759963995,-76.5713418647646),
                        new LatLng(-9.29233249571417,-76.5712104365229),
                        new LatLng(-9.29197217111808,-76.5707963705062),
                        new LatLng(-9.29151622265885,-76.5701673924922),
                        new LatLng(-9.29126938819451,-76.5696768835186),
                        new LatLng(-9.29119824954314,-76.5692346543073),
                        new LatLng(-9.29084189431387,-76.5687679499387),
                        new LatLng(-9.29043193645679,-76.5681895986199),
                        new LatLng(-9.29042201011047,-76.5679488703608),
                        new LatLng(-9.2899184131049,-76.5673618018627),
                        new LatLng(-9.28937246283942,-76.5668954327702),
                        new LatLng(-9.28875074025843,-76.566799879074),
                        new LatLng(-9.2883232433039,-76.5663589909672),
                        new LatLng(-9.28764030591029,-76.5654658153653),
                        new LatLng(-9.28685446302773,-76.5646883100271),
                        new LatLng(-9.28667909574392,-76.5643828734755),
                        new LatLng(-9.28590979482981,-76.5642105415463),
                        new LatLng(-9.2856864490868,-76.5643594041466),
                        new LatLng(-9.28487876351539,-76.5640177577733),
                        new LatLng(-9.28464152000123,-76.5640415623784),
                        new LatLng(-9.28362967626474,-76.5634843334555),
                        new LatLng(-9.28327165891279,-76.5632781386375),
                        new LatLng(-9.28292985457475,-76.5630766376852),
                        new LatLng(-9.28199377883923,-76.5627162158489),
                        new LatLng(-9.281670833785,-76.5627661719918),
                        new LatLng(-9.28149281681438,-76.5622629225254),
                        new LatLng(-9.28101336173581,-76.5614062920212),
                        new LatLng(-9.28107027431222,-76.5610210597515),
                        new LatLng(-9.28100078802583,-76.5609654039144),
                        new LatLng(-9.28083964596582,-76.5608088299632),
                        new LatLng(-9.28057857581819,-76.5606649965047),
                        new LatLng(-9.28045548564313,-76.5604269504547),
                        new LatLng(-9.27948631444885,-76.5599508583545),
                        new LatLng(-9.27936289298029,-76.559936441476),
                        new LatLng(-9.27984565917558,-76.5594720846821),
                        new LatLng(-9.28032842477194,-76.5590077266097),
                        new LatLng(-9.27988933645896,-76.5577403828501),
                        new LatLng(-9.27814422796261,-76.5583344921469),
                        new LatLng(-9.27686831454892,-76.5580193325877),
                        new LatLng(-9.2764394795379,-76.5563654154539),
                        new LatLng(-9.27631539523553,-76.5543410181999),
                        new LatLng(-9.27560066879938,-76.5532845631241),
                        new LatLng(-9.27522841487053,-76.5517312288284),
                        new LatLng(-9.27429595840648,-76.5495184063911),
                        new LatLng(-9.27345151734126,-76.5485575050115),
                        new LatLng(-9.27280858925856,-76.5482752025127),
                        new LatLng(-9.27244195758572,-76.5485461056232),
                        new LatLng(-9.27011807301195,-76.5508108958601),
                        new LatLng(-9.26901452853081,-76.5509587526321),
                        new LatLng(-9.26778987135925,-76.551661491394),
                        new LatLng(-9.26623430903663,-76.5531263127923),
                        new LatLng(-9.26507946252391,-76.5540499985218),
                        new LatLng(-9.26426444964787,-76.5535182505846),
                        new LatLng(-9.26316518852256,-76.554008424282),
                        new LatLng(-9.26164732893083,-76.5534622594714),
                        new LatLng(-9.26060431369897,-76.5535762533545),
                        new LatLng(-9.25939071099445,-76.5529853271403),
                        new LatLng(-9.25817710732207,-76.5523944050073),
                        new LatLng(-9.25682534299328,-76.5521312132477),
                        new LatLng(-9.25585279746179,-76.552398763597)
                        ).color(Color.rgb(120, 17, 145)).width(5));
    }
    private void agregarMarcador(double latx, double lgnx) {
        LatLng coordenadas = new LatLng(latx, lgnx);
        CameraUpdate miubicacion = CameraUpdateFactory.newLatLngZoom(coordenadas, 16);
        if (marcador != null) {
            marcador.remove();
        }
        Log.d("cordenadaxxxx", ""+coordenadas);
        marcador = mMap.addMarker(new MarkerOptions().position(coordenadas).title("Actual yoo")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
       /* marcador = mMap.addMarker(new MarkerOptions().position(coordenadas).title("Actual yoo")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher)));*/
        mMap.animateCamera(miubicacion);
    }
    private void actualzarUbicacion(Location location) {
        if (location != null) {
            lat = location.getLatitude();
            lgn = location.getLongitude();
            agregarMarcador(lat, lgn);
        }
    }
    private void miUbicacionx() {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        actualzarUbicacion(location);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,15000,0,listenes);
    }
    //escuchadores
    LocationListener listenes = new LocationListener() {

        @Override
        public void onLocationChanged(Location location) {
            actualzarUbicacion(location);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    @Override
    public void onPolygonClick(Polygon polygon) {

    }

    @Override
    public void onPolylineClick(Polyline polyline) {
        Toast.makeText( getContext().getApplicationContext(), "Reservar "+polyline.getId(), Toast.LENGTH_SHORT).show();
        Log.d("idxx",""+polyline.getId());
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
       /* String name= marker.getTitle();

        if (name.equalsIgnoreCase("Mirador"))
        {
            Toast.makeText( getContext().getApplicationContext(), "miradorrr ", Toast.LENGTH_SHORT).show();
            //write your code here
        }*/
        return false;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        String name= marker.getTitle();
        casePlaceRoute1(name);
        casePlaceRoute2(name);
        //casePlaceRoute3(name);
        //casePlaceRoute4(name);
        //casePlaceRoute5(name);
       // casePlaceRoute6(name);
       // casePlaceRoute7(name);
       // casePlaceRoute8(name);
       // casePlaceRoute9(name);
    }
    public void casePlaceRoute9(String name){
        DescriptionPlaceClass des;
        switch(name){
            //ruta 4
            case "Huqlla wagra":
                des=PlaceDetailsRepository.getDescriptionPlace(6);
                verDetalle(des.place,des.imagenPlace,des.ubication, des.schedule,des.history,des.imagenPlace,des.access);
                break;
            default:
                // Console.WriteLine("Other");
                break;
        }
    }
    public void casePlaceRoute8(String name){
        DescriptionPlaceClass des;
        switch(name){
            //ruta 4
            case "Huqlla wagra":
                des=PlaceDetailsRepository.getDescriptionPlace(6);
                verDetalle(des.place,des.imagenPlace,des.ubication, des.schedule,des.history,des.imagenPlace,des.access);
                break;
            default:
                // Console.WriteLine("Other");
                break;
        }
    }
    public void casePlaceRoute7(String name){
        DescriptionPlaceClass des;
        switch(name){
            //ruta 4
            case "Huqlla wagra":
                des=PlaceDetailsRepository.getDescriptionPlace(6);
                verDetalle(des.place,des.imagenPlace,des.ubication, des.schedule,des.history,des.imagenPlace,des.access);
                break;
            default:
                // Console.WriteLine("Other");
                break;
        }
    }
    public void casePlaceRoute6(String name){
        DescriptionPlaceClass des;
        switch(name){
            //ruta 4
            case "Huqlla wagra":
                des=PlaceDetailsRepository.getDescriptionPlace(6);
                verDetalle(des.place,des.imagenPlace,des.ubication, des.schedule,des.history,des.imagenPlace,des.access);
                break;
            default:
                // Console.WriteLine("Other");
                break;
        }
    }
    public void casePlaceRoute5(String name){
        DescriptionPlaceClass des;
        switch(name){
            //ruta 4
            case "Huqlla wagra":
                des=PlaceDetailsRepository.getDescriptionPlace(6);
                verDetalle(des.place,des.imagenPlace,des.ubication, des.schedule,des.history,des.imagenPlace,des.access);
                break;
            default:
                // Console.WriteLine("Other");
                break;
        }
    }
    public void casePlaceRoute4(String name){
        DescriptionPlaceClass des;
        switch(name){
            //ruta 4
            case "San Pedro de Pariarca":
                des=PlaceDetailsRepository.getDescriptionPlace(8);
                verDetalle(des.place,des.imagenPlace,des.ubication, des.schedule,des.history,des.imagenPlace,des.access);
                break;
            case "Pariash":
                des=PlaceDetailsRepository.getDescriptionPlace(9);
                verDetalle(des.place,des.imagenPlace,des.ubication, des.schedule,des.history,des.imagenPlace,des.access);
                break;
            case "Usuy Huayragh":
                des=PlaceDetailsRepository.getDescriptionPlace(10);
                verDetalle(des.place,des.imagenPlace,des.ubication, des.schedule,des.history,des.imagenPlace,des.access);
                break;
            case "Llama Llama":
                des=PlaceDetailsRepository.getDescriptionPlace(11);
                verDetalle(des.place,des.imagenPlace,des.ubication, des.schedule,des.history,des.imagenPlace,des.access);
                break;
            case "Japallan":
                des=PlaceDetailsRepository.getDescriptionPlace(12);
                verDetalle(des.place,des.imagenPlace,des.ubication, des.schedule,des.history,des.imagenPlace,des.access);
                break;
            case "Laguna Blanca":
                des=PlaceDetailsRepository.getDescriptionPlace(13);
                verDetalle(des.place,des.imagenPlace,des.ubication, des.schedule,des.history,des.imagenPlace,des.access);
                break;
            default:
                // Console.WriteLine("Other");
                break;
        }
    }
    public void casePlaceRoute3(String name){
        DescriptionPlaceClass des;
        switch(name){
            //ruta 3

            case "Coyllarbamba":
                des=PlaceDetailsRepository.getDescriptionPlace(7);
                verDetalle(des.place,des.imagenPlace,des.ubication, des.schedule,des.history,des.imagenPlace,des.access);
                break;
            case "Ango":
                des=PlaceDetailsRepository.getDescriptionPlace(8);
                verDetalle(des.place,des.imagenPlace,des.ubication, des.schedule,des.history,des.imagenPlace,des.access);
                break;
            case "Piruro I":
                des=PlaceDetailsRepository.getDescriptionPlace(9);
                verDetalle(des.place,des.imagenPlace,des.ubication, des.schedule,des.history,des.imagenPlace,des.access);
                break;
            case "Piruro II":
                des=PlaceDetailsRepository.getDescriptionPlace(10);
                verDetalle(des.place,des.imagenPlace,des.ubication, des.schedule,des.history,des.imagenPlace,des.access);
                break;
            case "Huqlla wagra":
                des=PlaceDetailsRepository.getDescriptionPlace(11);
                verDetalle(des.place,des.imagenPlace,des.ubication, des.schedule,des.history,des.imagenPlace,des.access);
                break;
            default:
                // Console.WriteLine("Other");
                break;
        }
    }
    public void casePlaceRoute2(String name){
        DescriptionPlaceClass des;
        switch(name){
            //ruta2
            case "Plaza de Pampa Florida":
                des=PlaceDetailsRepository.getDescriptionPlace(3);
                verDetalle(des.place,des.imagenPlace,des.ubication, des.schedule,des.history,des.imagenPlace,des.access);
                break;
            case "Susupillo":
                des=PlaceDetailsRepository.getDescriptionPlace(4);
                verDetalle(des.place,des.imagenPlace,des.ubication, des.schedule,des.history,des.imagenPlace,des.access);
                break;
            case "Isog":
                des=PlaceDetailsRepository.getDescriptionPlace(5);
                verDetalle(des.place,des.imagenPlace,des.ubication, des.schedule,des.history,des.imagenPlace,des.access);
                break;
            case "Jipango":
                des=PlaceDetailsRepository.getDescriptionPlace(6);
                verDetalle(des.place,des.imagenPlace,des.ubication, des.schedule,des.history,des.imagenPlace,des.access);
                break;
            default:
                // Console.WriteLine("Other");
                break;
        }
    }
    public void casePlaceRoute1(String name){
        DescriptionPlaceClass des;
        switch(name){
            //ruta 1
            case "Plaza de Tantamayo":
                des=PlaceDetailsRepository.getDescriptionPlace(0);
                verDetalle(des.place,des.imagenPlace,des.ubication, des.schedule,des.history,des.imagenPlace,des.access);
                break;
            case "Iglesia Matriz de Tantamayo":
                des=PlaceDetailsRepository.getDescriptionPlace(1);
                verDetalle(des.place,des.imagenPlace,des.ubication, des.schedule,des.history,des.imagenPlace,des.access);
                break;
            case "Museo del colegio":
                des=PlaceDetailsRepository.getDescriptionPlace(2);
                verDetalle(des.place,des.imagenPlace,des.ubication, des.schedule,des.history,des.imagenPlace,des.access);
                break;
            default:
                // Console.WriteLine("Other");
                break;
        }
    }
    public void verDetalle(final String name,int imagen,final String ubication,
                           final String schedule,final String history,final int imagePlace,final String access){
        Intent miintent= new Intent(getContext().getApplicationContext(),DetailPlaceActivity.class);
        miintent.putExtra("titulo", name);
        miintent.putExtra("ubication", ubication);
        miintent.putExtra("history", history);
        miintent.putExtra("schedule", schedule);
        miintent.putExtra("imagePlace", imagePlace);
        miintent.putExtra("access",access);
        startActivity(miintent);
    }
    public void showMiDialog(final String name,int imagen,final String ubication,
                             final String schedule,final String history,final int imagePlace){
        //dialogo
        View viewx  = getActivity().getLayoutInflater().inflate(R.layout.dialog_place_touristic, null);
        detalleHistorialDialog = new Dialog(getActivity());
        //detalleHistorialDialog.setCancelable(false);
        detalleHistorialDialog.setContentView(viewx);
        detalleHistorialDialog.setTitle("Atractivo Turístico");
        btnInfo = (Button)viewx.findViewById(R.id.buttonInfo);
        btnInfo.setEnabled(true);
        //para enviar parametros al dialog
        ImageView avatar = (ImageView) viewx.findViewById(R.id.imagePlace);
        TextView namePlace = (TextView) viewx.findViewById(R.id.textViewPlace);
        // Setup.
         avatar.setImageResource(imagen);
        namePlace.setText(name);
        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent miintent= new Intent(getContext().getApplicationContext(),DetailPlaceActivity.class);
                miintent.putExtra("titulo", name);
                miintent.putExtra("ubication", ubication);
                miintent.putExtra("history", history);
                miintent.putExtra("schedule", schedule);
                miintent.putExtra("imagePlace", imagePlace);
                startActivity(miintent);
                //  finish();
                detalleHistorialDialog.cancel();
            }
        });
        detalleHistorialDialog.show();
    }
}
