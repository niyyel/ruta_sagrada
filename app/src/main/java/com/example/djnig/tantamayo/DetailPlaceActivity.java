package com.example.djnig.tantamayo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailPlaceActivity extends AppCompatActivity {

    String txtTitle ="";
    String txtUbication = "";
    String txtHistory = "";
    String txtSchedule = "";
    int imagePlace = 0;

    TouristAttractionFragment fragInfo;
    CraftsFragment CraftsFrag;
    VideoFragment videoFragment;
    ArFragment arFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            android.support.v4.app.FragmentManager fragmentManager=getSupportFragmentManager();
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragmentManager.beginTransaction().replace(R.id.contenDetail,fragInfo).commit();
                    getSupportActionBar().setTitle(txtTitle);
                    return true;
                case R.id.navigation_dashboard:
                    // Handle the camera action
                    fragmentManager.beginTransaction().replace(R.id.contenDetail,CraftsFrag).commit();
                    getSupportActionBar().setTitle("Artesania");
                   // mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    // mTextMessage.setText(R.string.title_notifications);
                    fragmentManager.beginTransaction().replace(R.id.contenDetail,arFragment).commit();
                    getSupportActionBar().setTitle("Navegación 360");
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_place);

         txtTitle = getIntent().getStringExtra("titulo");
         txtUbication = getIntent().getStringExtra("ubication");
         txtHistory = getIntent().getStringExtra("history");
         txtSchedule = getIntent().getStringExtra("schedule");
         imagePlace = getIntent().getIntExtra("imagePlace",-1);

        this.setTitle(txtTitle);
        //para enviar parametros
        Bundle bundle = new Bundle();
        bundle.putString("titulo", txtTitle );
        bundle.putString("ubication", txtUbication );
        bundle.putString("history", txtHistory );
        bundle.putString("schedule", txtSchedule );
        bundle.putString("access", getIntent().getStringExtra("access") );
        bundle.putInt("imagePlace", imagePlace );

        fragInfo = new TouristAttractionFragment();
        CraftsFrag = new CraftsFragment();
        videoFragment = new VideoFragment();
        arFragment = new ArFragment();
        fragInfo.setArguments(bundle);

        //para iniciar por defecto las rutas
        android.support.v4.app.FragmentManager fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.contenDetail,fragInfo).commit();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
