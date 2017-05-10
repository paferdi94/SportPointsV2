package com.dannyang27.sportpoints.activities.PruebasDanny;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.dannyang27.sportpoints.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


public class PruebaBajadaImagen extends AppCompatActivity {

    private ImageView img;
    private Button btn;
    private Button upload_btn;

    public static final int GALLERY_INTENT = 2;

    FirebaseStorage firebaseStorageRef = FirebaseStorage.getInstance();
    StorageReference mStorageRef =firebaseStorageRef.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aaa_activity_prueba_bajada_imagen);

        img = (ImageView) findViewById(R.id.download_img);
        btn = (Button) findViewById(R.id.downloadBtn);
        upload_btn = (Button) findViewById(R.id.upload_Btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StorageReference logoUpv = mStorageRef.child("eventos/miau.jpg");

                logoUpv.getBytes(Long.MAX_VALUE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                    @Override
                    public void onSuccess(byte[] bytes) {
                        Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                        img.setImageBitmap(bmp);
                        Toast.makeText(getApplicationContext(), "Imagen descargada...",
                                Toast.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Error al descargar...",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }

        });

        upload_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_PICK);
                i.setType("image/*");
                startActivityForResult(i, GALLERY_INTENT);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GALLERY_INTENT && resultCode == RESULT_OK) {

            Uri uri = data.getData();
            StorageReference eventoRef = mStorageRef.child("eventos").child(uri.getLastPathSegment());
            eventoRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(getApplicationContext(), "Imagen subida", Toast.LENGTH_LONG).show();
                }
            });
        }
    }

}
