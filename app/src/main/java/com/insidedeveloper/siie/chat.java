package com.insidedeveloper.siie;

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

public class chat extends AppCompatActivity {
    public static final int NOTIFICACION_ID=1;
    private CircleImageView fotoPerfil;
    private EditText nombre;
    private RecyclerView rvMensajes;
    private EditText txtMensaje;
    private Button btnEnviar;
    private AdapterMensajes adapter;
    private ImageButton btnEnviarFoto;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private static final int PHOTO_SEND = 1;
    private static final int PHOTO_PERFIL = 2;
    private String fotoPerfilCadena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);


        fotoPerfil = (CircleImageView) findViewById(R.id.fotoPerfil);
        nombre = (EditText) findViewById(R.id.nombre);
        rvMensajes = (RecyclerView) findViewById(R.id.rvMensajes);
        txtMensaje = (EditText) findViewById(R.id.txtMensaje);
        btnEnviar = (Button) findViewById(R.id.btnEnviar);
        btnEnviarFoto = (ImageButton) findViewById(R.id.btnEnviarFoto);
        fotoPerfilCadena = "";

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("chat");//Sala de chat (nombre)
        storage = FirebaseStorage.getInstance();

        adapter = new AdapterMensajes(this);
        LinearLayoutManager l = new LinearLayoutManager(this);
        rvMensajes.setLayoutManager(l);
        rvMensajes.setAdapter(adapter);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.push().setValue(new MensajeEnviar(txtMensaje.getText().toString(), nombre.getText().toString(), fotoPerfilCadena, "1", ServerValue.TIMESTAMP));
                txtMensaje.setText("");
            }
        });

        btnEnviarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.setType("image/jpeg");
                i.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                startActivityForResult(Intent.createChooser(i, "Selecciona una foto"), PHOTO_SEND);
            }
        });

        fotoPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.setType("image/jpeg");
                i.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                startActivityForResult(Intent.createChooser(i, "Selecciona una foto"), PHOTO_PERFIL);
            }
        });

        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                setScrollbar();
            }
        });

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                MensajeRecibir m = dataSnapshot.getValue(MensajeRecibir.class);
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

    private void notificacion() {
        Intent intent= new Intent(Intent.ACTION_VIEW, Uri.parse("http://developer.android.com/index.html"));
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,0);

        //Construccion de la notificacion;
        NotificationCompat.Builder builder= new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.udg);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.udg));
        builder.setContentTitle("usuario de siie");
        builder.setContentText("mensaje nuevo");
        builder.setSubText("ver mas");
         Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        builder.setSound(uri);

        NotificationManager notificationManager= (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICACION_ID,builder.build());


    }

    private void setScrollbar() {
        rvMensajes.scrollToPosition(adapter.getItemCount() - 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PHOTO_SEND && resultCode == RESULT_OK) {
            Uri u = data.getData();
            storageReference = storage.getReference("imagenes_chat");//imagenes_chat
            final StorageReference fotoReferencia = storageReference.child(u.getLastPathSegment());
            fotoReferencia.putFile(u).addOnSuccessListener(this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Uri u = taskSnapshot.getDownloadUrl();
                    MensajeEnviar m = new MensajeEnviar(" te ha enviado una foto", u.toString(), nombre.getText().toString(), fotoPerfilCadena, "2", ServerValue.TIMESTAMP);
                    databaseReference.push().setValue(m);
                }
            });
        } else if (requestCode == PHOTO_PERFIL && resultCode == RESULT_OK) {
            Uri u = data.getData();
            storageReference = storage.getReference("foto_perfil");//imagenes_chat
            final StorageReference fotoReferencia = storageReference.child(u.getLastPathSegment());
            fotoReferencia.putFile(u).addOnSuccessListener(this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Uri u = taskSnapshot.getDownloadUrl();
                    fotoPerfilCadena = u.toString();
                    MensajeEnviar m = new MensajeEnviar( "ha actualizado su foto de perfil", u.toString(), nombre.getText().toString(), fotoPerfilCadena, "2", ServerValue.TIMESTAMP);
                    databaseReference.push().setValue(m);
                    Glide.with(chat.this).load(u.toString()).into(fotoPerfil);
                }
            });
        }
    }
}
