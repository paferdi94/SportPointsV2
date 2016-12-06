package com.example.dannyang27.sportpoints.activities.Principal;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import com.example.dannyang27.sportpoints.R;
import com.example.dannyang27.sportpoints.activities.Promocion.PromocionFragmento;
import com.example.dannyang27.sportpoints.activities.Equipo.EquipoFragmento;
import com.example.dannyang27.sportpoints.activities.Evento.EventoFragmento;

public class PPrincipal extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pprincipal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        boolean FragmentTransaction = false;
        Fragment fragment = null;

        fragment = new EventoFragmento();
        FragmentTransaction = true;

        if(FragmentTransaction){
            getSupportFragmentManager().beginTransaction().replace(R.id.content_pprincipal,fragment).commit();

            getSupportActionBar().setTitle("Eventos");
        }
    }

    @Override
    public void onBackPressed() { // cuando se presiona el navigation drawer lo abre
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { // menú de los tres puntitos de la parte superior derecha
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.pprincipal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //Este es el menu de las opciones de los tres puntitos
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        boolean FragmentTransaction = false;
        Fragment fragment = null;



        if (id == R.id.nav_eventos) {
            fragment = new EventoFragmento();
            FragmentTransaction = true;

        } else if (id == R.id.nav_equipos){
            fragment = new EquipoFragmento();
            FragmentTransaction = true;

        } else if (id == R.id.nav_promociones) {
            fragment = new PromocionFragmento();
            FragmentTransaction = true;

        } else if (id == R.id.nav_productos) {
            Log.i("NavigationDrawer","Aqui productos a la venta");
        } else if (id == R.id.nav_perfil) {
            Log.i("NavigationDrawer","Aqui perfil usuario");
        } else if (id == R.id.nav_cerrarSesion) {

            System.exit(0);


        }

        if(FragmentTransaction){
            getSupportFragmentManager().beginTransaction().replace(R.id.content_pprincipal,fragment).commit();

            item.setChecked(true);
            getSupportActionBar().setTitle(item.getTitle());
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
