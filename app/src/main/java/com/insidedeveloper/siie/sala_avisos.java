package com.insidedeveloper.siie;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import de.hdodenhof.circleimageview.CircleImageView;


public class sala_avisos extends AppCompatActivity {

    private static final int ID_NOTIFICACION_CREAR = 1;
    private static final int NOTIF_ALERTA_ID = 2;
    public static final String NOTIFICATION_CHANNEL_ID = "4565";
    private static final CharSequence NOTIFICATION_CHANNEL_NAME = "MyChannel" ;

    private RecyclerView rvMensajes;

    private AdapterMensajes adapter;


    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private FirebaseStorage storage;

    @RequiresApi(api = Build.VERSION_CODES.O)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sala_avisos);

        rvMensajes = (RecyclerView) findViewById(R.id.rvMensajes);
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("avisos");//Sala de chat (nombre)
        storage = FirebaseStorage.getInstance();
        adapter = new AdapterMensajes(this);
        LinearLayoutManager l = new LinearLayoutManager(this);
        rvMensajes.setLayoutManager(l);
        rvMensajes.setAdapter(adapter);


        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                setScrollbar();
            }
        });


        databaseReference.addChildEventListener(new ChildEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                MensajeRecibir m = dataSnapshot.getValue(MensajeRecibir.class);
                notificaciones(m.getNombre(),m.getMensaje(),m.getFotoPerfil());
                adapter.addMensaje(m);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }





    public void notificaciones(String nombre, String mensaje, String fotoPerfil){
        Uri defaultSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(sala_avisos.this)
                        .setSmallIcon(android.R.drawable.stat_notify_chat)
                        .setLargeIcon((((BitmapDrawable)getResources()
                                .getDrawable(R.drawable.leongris)).getBitmap()))
                        .setContentTitle(nombre)
                        .setContentText(mensaje)
                        .setContentInfo(nombre)
                        .setTicker("SIIE!")
                        .setSound(defaultSound)
                .setLights(1,2,Color.GREEN);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(NOTIF_ALERTA_ID, mBuilder.build());
    }


    private void setScrollbar() {
        rvMensajes.scrollToPosition(adapter.getItemCount() - 1);
    }

    }




