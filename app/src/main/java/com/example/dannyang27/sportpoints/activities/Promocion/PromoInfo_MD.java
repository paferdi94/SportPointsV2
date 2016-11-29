package com.example.dannyang27.sportpoints.activities.Promocion;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.dannyang27.sportpoints.R;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;



public class PromoInfo_MD extends AppCompatActivity {


    private View rootView;
    private ImageView promo_info_img;
    private TextView nombre_promo_info;
    private TextView lugar_promo_info;
    private TextView fechaIn_promo_info;
    private TextView fechaF_promo_info;
    private TextView descripcion_promo_info;
    private FirebaseAuth mAuth;
    Toolbar toolbar;

    FirebaseStorage firebaseStorageRef = FirebaseStorage.getInstance();
    StorageReference mStorageRef =firebaseStorageRef.getReference();

    FirebaseDatabase mDataRef = FirebaseDatabase.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promo_info__md);
        FirebaseStorage storage = FirebaseStorage.getInstance();

        StorageReference storageRef = storage.getReferenceFromUrl("gs://sport-points-7f5e0.appspot.com/promociones/");
        //StorageReference imagesRef = storageRef.child("promociones");


        //final DatabaseReference promosRef = mDataRef.getReference().child("Promociones");

        System.out.println(storage);
        System.out.println(mAuth);

        mAuth = FirebaseAuth.getInstance();

        toolbar = (Toolbar) findViewById(R.id.toolbar_promoInfo);
        rootView = (View) findViewById(R.id.activity_promo_info__md);
        promo_info_img = (ImageView) findViewById(R.id.promo_info_img);
        nombre_promo_info = (TextView) findViewById(R.id.nombre_promo_info_md);
        lugar_promo_info = (TextView) findViewById(R.id.lugar_promo_info_md);
        fechaIn_promo_info = (TextView) findViewById(R.id.fechaIn_promo_info_md);
        fechaF_promo_info = (TextView) findViewById(R.id.fechaF_promo_info_md);
        descripcion_promo_info = (TextView) findViewById(R.id.descripcion_promo_info_md);


        /////////////////////Añadir Toolbar////////////////////////
        toolbar.setTitle("Información de promoción");
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(0xFFFFFFFF);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //////////////////////////////////////////////////////////

        PromocionParceable e = getIntent().getParcelableExtra("PARCELABLE");

        //vamos a la ruta de la imagen
        StorageReference imagesRef = storageRef.child(e.getImagen());
        //mStorageRef = mStorageRef.child("promociones/" + e.getImagen());
        System.out.println(imagesRef);
        nombre_promo_info.setText(e.getNombre().toString());
        lugar_promo_info.setText(e.getLugar());
        fechaIn_promo_info.setText(e.getFechaIn());
        fechaF_promo_info.setText(e.getFechaF());
        descripcion_promo_info.setText(e.getDescripcion());

        imagesRef.getBytes(Long.MAX_VALUE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                promo_info_img.setImageBitmap(bmp);

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


    //Comprobar si tenemos internet en un momento determinado
    public Boolean isOnlineNet() {

        try {
            Process p = Runtime.getRuntime().exec("ping -c 1 www.google.es");

            int val = p.waitFor();
            boolean reachable = (val == 0);
            return reachable;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

}
