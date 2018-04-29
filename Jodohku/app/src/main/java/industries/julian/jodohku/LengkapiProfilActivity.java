package industries.julian.jodohku;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LengkapiProfilActivity extends AppCompatActivity {
    private Button mSelesai;
    private ImageView FotoProfil;
    private EditText mUmur, mPekerjaan, mHoby;
    private FirebaseAuth mAuth;
    private DatabaseReference users;
    private Uri resultUri;
    private String mProfileUrl, userId;
    private DatabaseReference currentUserDb;
    private FirebaseAuth.AuthStateListener firebaseAuthStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lengkapi_profil);


        mUmur = (EditText)findViewById(R.id.umur);
        mPekerjaan = (EditText)findViewById(R.id.pekerjaan);
        mHoby = (EditText)findViewById(R.id.hoby);
        mSelesai = (Button) findViewById(R.id.selesai);
        FotoProfil = (ImageView)findViewById(R.id.fotoProfil);

        FotoProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
            }
        });


        firebaseAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if(user!=null){
                    Intent intent = new Intent(getApplication(), MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                    return;
                }
            }
        };
        mAuth = FirebaseAuth.getInstance();
        users = FirebaseDatabase.getInstance().getReference("users");
        userId = mAuth.getCurrentUser().getUid();
        currentUserDb = FirebaseDatabase.getInstance().getReference().child("users").child(userId);

        getUserInfo();

        mSelesai.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                final String age = mUmur.getText().toString();
                final String job = mPekerjaan.getText().toString();
                final String hobby = mHoby.getText().toString();


                final Map userInfo = new HashMap<>();
                userInfo.put("Umur",age);
                userInfo.put("Pekerjaan",job);
                userInfo.put("Hobby",hobby);
                currentUserDb.updateChildren(userInfo);
                if(resultUri != null){
                    StorageReference filePath = FirebaseStorage.getInstance().getReference().child("foto_profil").child(userId);
                    Bitmap bitmap = null;
                    try{
                        bitmap = MediaStore.Images.Media.getBitmap(getApplication().getContentResolver(), resultUri);
                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
<<<<<<< Updated upstream
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos);
=======
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 60, baos);
>>>>>>> Stashed changes
                    byte[] data = baos.toByteArray();
                    UploadTask uploadTask = filePath.putBytes(data);
                    uploadTask.addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            finish();
                            return;
                        }
                    });
                    uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Uri downloadUri = taskSnapshot.getDownloadUrl();
                            Map newImage = new HashMap();
                            newImage.put("fotoProfilUrl", downloadUri.toString());
                            currentUserDb.updateChildren(newImage);

<<<<<<< Updated upstream
                            finish();
                            return;
                        }
                    });
                }
                Toast.makeText(LengkapiProfilActivity.this,"Registrasi Berhasil", Toast.LENGTH_LONG).show();
=======

                        }
                    });
                    Intent intent = new Intent(getApplication(), MainActivity.class);
                    startActivity(intent);

                    Toast.makeText(LengkapiProfilActivity.this,"Registrasi Berhasil", Toast.LENGTH_LONG).show();
                }

            }
        });

    }
    public void getUserInfo(){
        currentUserDb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists() && dataSnapshot.getChildrenCount() > 0){
                    Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                    if(map.get("fotoProfilUrl") != null){
                        mProfileUrl = map.get("fotoProfilUrl").toString();
                        Glide.with(getApplication()).load(mProfileUrl).into(FotoProfil);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

>>>>>>> Stashed changes
            }
        });

    }
    public void getUserInfo(){
        currentUserDb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists() && dataSnapshot.getChildrenCount() > 0){
                    Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                    if(map.get("fotoProfilUrl") != null){
                        mProfileUrl = map.get("fotoProfilUrl").toString();
                        Glide.with(getApplication()).load(mProfileUrl).into(FotoProfil);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == Activity.RESULT_OK){
            final Uri imageUri = data.getData();
            resultUri = imageUri;
            FotoProfil.setImageURI(resultUri);
        }
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == Activity.RESULT_OK){
            final Uri imageUri = data.getData();
            resultUri = imageUri;
            FotoProfil.setImageURI(resultUri);
        }
    }
}
