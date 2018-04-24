package industries.julian.jodohku;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private CardView menuMap,menuProfil;
    private Button keluar;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthStateListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        keluar =(Button) findViewById(R.id.btnKeluar);
        menuMap = (CardView) findViewById(R.id.cardMap);
        menuProfil = (CardView) findViewById(R.id.cardProfil);


        keluar.setOnClickListener(this);
        menuMap.setOnClickListener(this);
        menuProfil.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {
            case R.id.cardMap:
                i = new Intent(this, MapsActivity.class);
                startActivity(i);
                break;
            case R.id.btnKeluar:
                i = new Intent(this, LoginActivity.class);
                FirebaseAuth.getInstance().signOut();
                startActivity(i);

            case R.id.cardProfil:
                i = new Intent(this, ProfileActivity.class);
                startActivity(i);
                break;
            default:
                break;
        }
    }
}
