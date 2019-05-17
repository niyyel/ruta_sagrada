package com.example.djnig.tantamayo;

import android.Manifest;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.djnig.tantamayo.AdapterClass.RouteAdapter;
import com.example.djnig.tantamayo.Entity.DetailRoute;
import com.example.djnig.tantamayo.Repository.RouteRepository;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private final int REQUEST_CODE_ASK_PERMISSIONS_SEND = 1000;
    Dialog detalleHistorialDialog;
    Button btnCerrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Contacte con Nigel", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //para iniciar por defecto las rutas
        onNavigationItemSelected(navigationView.getMenu().getItem(0));
        navigationView.getMenu().getItem(0).setChecked(true);
        checkPermission();
        videoIntro();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_route) {
           // Toast.makeText(this, "Hola rutas ", Toast.LENGTH_LONG).show();
            infoRoute();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        android.support.v4.app.FragmentManager fragmentManager=getSupportFragmentManager();

        if (id == R.id.nav_route) {
            // Handle the camera action
            fragmentManager.beginTransaction().replace(R.id.contenedor,new RouteFragment()).commit();
            getSupportActionBar().setTitle("Rutas");
        } else if (id == R.id.nav_gallery) {
            getSupportActionBar().setTitle("Artesania");
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {
            fragmentManager.beginTransaction().replace(R.id.contenedor,new PromotionFragment()).commit();
            getSupportActionBar().setTitle("Promociones");

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    //verificar permisos
    private void checkPermission() {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
          //  Toast.makeText(this,"This version is not Android 6 or later " + Build.VERSION.SDK_INT,Toast.LENGTH_SHORT).show();

        } else {
            int hasWritesendsms = checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION);
            //permiso para enviar
            if (hasWritesendsms != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION},
                        REQUEST_CODE_ASK_PERMISSIONS_SEND);
               // Toast.makeText(this,"Requiere permisos",Toast.LENGTH_SHORT).show();
            }else if (hasWritesendsms == PackageManager.PERMISSION_GRANTED){
                // Toast.makeText(this, "Los permisos fueron agregdos ", Toast.LENGTH_LONG).show();
                // openCamera();
            }
        }
        return;
    }
    //para registrar los permisos
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        //para enviar
        if(REQUEST_CODE_ASK_PERMISSIONS_SEND == requestCode) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
               // Toast.makeText(this, "OK Permisos agregados enviar mensaje ! " + Build.VERSION.SDK_INT, Toast.LENGTH_LONG).show();
                // openCamera();

            } else {
              //  Toast.makeText(this, "Permisos no agregados ! " + Build.VERSION.SDK_INT, Toast.LENGTH_LONG).show();
            }
        }else{
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
    //PARA EL DIALOGO
    public void infoRoute(){

        ListView mLeadsList;
       final ArrayAdapter<DetailRoute> mLeadsAdapter;
        //el dialo
        View viewx  = this.getLayoutInflater().inflate(R.layout.dialog_info_route, null);
        // Instancia del ListView.
        mLeadsList = (ListView) viewx.findViewById(R.id.list_route);
        mLeadsAdapter = new RouteAdapter(this,RouteRepository.getInstance().getRoutes());
        mLeadsList.setAdapter(mLeadsAdapter);
        mLeadsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                DetailRoute currentLead = mLeadsAdapter.getItem(position);
                Toast.makeText( MainActivity.this, "nombre: "+currentLead.getNameRoute(), Toast.LENGTH_SHORT).show();

            }
        });
        //dialogo
        detalleHistorialDialog = new Dialog(this);
        //detalleHistorialDialog.setCancelable(false);

        detalleHistorialDialog.setContentView(viewx);
        // detalleHistorialDialog.setContentView(R.layout.detalle_historial);
        detalleHistorialDialog.setTitle("Mis rutas");
        btnCerrar = (Button)viewx.findViewById(R.id.buttonCerrar);
        btnCerrar.setEnabled(true);

        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detalleHistorialDialog.cancel();
            }
        });
      //  Toast.makeText( this.getApplicationContext(), "id: ", Toast.LENGTH_SHORT).show();
        detalleHistorialDialog.show();
    }
    //PARA EL DIALOGO
    public void videoIntro(){

        VideoView video;
        //el dialo
        View viewx  = this.getLayoutInflater().inflate(R.layout.fragment_video, null);
        video= (VideoView)viewx.findViewById(R.id.videoView);
        Uri uri =Uri.parse("http://techslides.com/demos/sample-videos/small.mp4");
        String path = "android.resource://" + MainActivity.this.getPackageName() + "/" + R.raw.tantamayo;
        /*String path = "android.resource://" + getActivity().getPackageName() + "/" + R.raw.howtopray;
        view.setVideoURI(Uri.parse(path));*/
        video.setMediaController(new MediaController(MainActivity.this));
        // video.setVideoURI(uri);
        video.setVideoURI(Uri.parse(path));
        video.requestFocus();
        video.start();
        //dialogo
        detalleHistorialDialog = new Dialog(this);
        //detalleHistorialDialog.setCancelable(false);
        detalleHistorialDialog.setContentView(viewx);
        // detalleHistorialDialog.setContentView(R.layout.detalle_historial);
        detalleHistorialDialog.setTitle("Introduccion");
        //  Toast.makeText( this.getApplicationContext(), "id: ", Toast.LENGTH_SHORT).show();
        detalleHistorialDialog.show();
    }
}
