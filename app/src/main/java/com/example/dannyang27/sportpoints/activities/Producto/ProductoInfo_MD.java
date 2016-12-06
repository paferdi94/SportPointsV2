package com.example.dannyang27.sportpoints.activities.Producto;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dannyang27.sportpoints.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ProductoInfo_MD extends AppCompatActivity {

    private ImageView img;
    private TextView precio;
    private TextView nombre;
    private TextView telefono;
    private TextView descripcion;

    private Toolbar tb;

    FirebaseStorage firebaseStorageRef = FirebaseStorage.getInstance();
    StorageReference mStorageRef = firebaseStorageRef.getReference();

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
        telefono = (TextView) findViewById(R.id.telefono_producto_info_md);
        descripcion = (TextView) findViewById(R.id.descripcion_producto_info_md);

        ProductoPruebaDanny p = getIntent().getParcelableExtra("PARCELABLE");

        mStorageRef = mStorageRef.child("productos/" + p.getImagen());

        precio.setText(p.getPrecio());
        nombre.setText(p.getNombre());
        telefono.setText(p.getTelefono());
        descripcion.setText(p.getDescripcion());


        mStorageRef.getBytes(Long.MAX_VALUE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                img.setImageBitmap(bmp);

            }
        });
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
