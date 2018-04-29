package industries.julian.jodohku;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.CardView;
<<<<<<< Updated upstream
=======
import android.util.Log;
import android.view.LayoutInflater;
>>>>>>> Stashed changes
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
<<<<<<< Updated upstream

    private CardView menuMap,menuProfil,menuCari,menuChat;
=======
    private CardView menuMap,menuProfil, menuCari, menuChat;
>>>>>>> Stashed changes
    private Button keluar;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthStateListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
<<<<<<< Updated upstream

        keluar =(Button) findViewById(R.id.btnKeluar);
        menuMap = (CardView) findViewById(R.id.cardMap);
        menuProfil = (CardView) findViewById(R.id.cardProfil);
        menuCari = (CardView) findViewById(R.id.cardCari);
        menuChat = (CardView) findViewById(R.id.cardChat);


        keluar.setOnClickListener(this);
        menuMap.setOnClickListener(this);
        menuProfil.setOnClickListener(this);
        menuCari.setOnClickListener(this);
        menuChat.setOnClickListener(this);
    }
=======
        keluar =(Button) findViewById(R.id.btnKeluar);
        menuMap = (CardView) findViewById(R.id.cardMap);
        menuProfil = (CardView) findViewById(R.id.cardProfil);
        menuCari = (CardView)findViewById(R.id.cardCari);
        menuChat = (CardView)findViewById(R.id.cardChat);

        menuCari.setOnClickListener(this);
        menuMap.setOnClickListener(this);
        menuProfil.setOnClickListener(this);
        menuChat.setOnClickListener(this);

        keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplication(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }

>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
                toastMessage("Proses keluar ..");
                startActivity(i);
                break;
            case R.id.cardProfil:
                i = new Intent(this, ProfileActivity.class);
                startActivity(i);
                break;
            case R.id.cardCari:
                i = new Intent(this, SaranTemanActivity.class);
                startActivity(i);
                break;
            case R.id.cardChat:
                i = new Intent(this, ChatActivity.class);
                startActivity(i);
                break;
            default:
                break;
        }
    }
   /* public void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(firebaseAuthStateListener);
    }

    public void onStop(){
        super.onStop();
        if (firebaseAuthStateListener != null){
            mAuth.removeAuthStateListener(firebaseAuthStateListener);
        }
    }*/
    private void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
=======
                startActivity(i);

            case R.id.cardProfil:
                i = new Intent(this, ProfileActivity.class);
                startActivity(i);
                break;
            case R.id.cardCari:
                i = new Intent(this, CariTemanActivity.class);
                startActivity(i);
                break;
            case R.id.cardChat:
                i = new Intent(this, ChatActivity.class);
                startActivity(i);
                break;
            default:
                break;
        }
>>>>>>> Stashed changes
    }
}
