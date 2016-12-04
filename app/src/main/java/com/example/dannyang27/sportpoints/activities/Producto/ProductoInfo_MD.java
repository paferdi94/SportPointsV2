package com.example.dannyang27.sportpoints.activities.Producto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dannyang27.sportpoints.R;

public class ProductoInfo_MD extends AppCompatActivity {

    private ImageView img;
    private TextView precio;
    private TextView nombre;
    private TextView descripcion;

    private Toolbar tb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aaa_activity_producto_info__md);

        tb = (Toolbar) findViewById(R.id.toolbar_productoInfo);
        /////////////////////Añadir Toolbar////////////////////////
        tb.setTitle("Información de Productos");
        setSupportActionBar(tb);
        tb.setTitleTextColor(0xFFFFFFFF);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //////////////////////////////////////////////////////////

        img = (ImageView) findViewById(R.id.image_producto_info_md);
        precio = (TextView) findViewById(R.id.precio_producto_info_md);
        nombre = (TextView) findViewById(R.id.nombre_producto_info_info);
        descripcion = (TextView) findViewById(R.id.descripcion_producto_info_md);

        ProductoPruebaDanny p = getIntent().getParcelableExtra("PARCELABLE");

        precio.setText(p.getPrecio());
        nombre.setText(p.getNombre());
        descripcion.setText(p.getDescripcion());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //Back arrow
            case android.R.id.home:
                onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }


}
