package industries.julian.jodohku;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class LengkapiProfilActivity extends AppCompatActivity {
    private Button mSelesai;
    private EditText mUmur, mPekerjaan, mHoby;
    private FirebaseAuth mAuth;
    private DatabaseReference users;
    private FirebaseAuth.AuthStateListener firebaseAuthStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lengkapi_profil);


        mUmur = (EditText)findViewById(R.id.umur);
        mPekerjaan = (EditText)findViewById(R.id.pekerjaan);
        mHoby = (EditText)findViewById(R.id.hoby);
        mSelesai = (Button) findViewById(R.id.selesai);

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

        mSelesai.setOnClickListener(new View.OnClickListener() {
            final String age = mUmur.getText().toString();
            final String job = mPekerjaan.getText().toString();
            final String hobby = mHoby.getText().toString();
            @Override
            public void onClick(View view) {
                String userId = mAuth.getCurrentUser().getUid();
                DatabaseReference currentUserDb = FirebaseDatabase.getInstance().getReference().child("users").child(userId);

                Map userInfo = new HashMap<>();
                userInfo.put("Umur",age);
                userInfo.put("Pekerjaan",job);
                userInfo.put("Hobby",hobby);
                currentUserDb.updateChildren(userInfo);
                Toast.makeText(LengkapiProfilActivity.this,"Registrasi Berhasil", Toast.LENGTH_LONG).show();
            }
        });
    }
}
