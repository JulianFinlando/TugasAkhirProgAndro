package industries.julian.jodohku;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView menuMap,menuProfil,menuCari,menuChat;
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
        menuCari = (CardView) findViewById(R.id.cardCari);
        menuChat = (CardView) findViewById(R.id.cardChat);


        keluar.setOnClickListener(this);
        menuMap.setOnClickListener(this);
        menuProfil.setOnClickListener(this);
        menuCari.setOnClickListener(this);
        menuChat.setOnClickListener(this);
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
                i = new Intent(this, ListContactActivity.class);
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
    }
}
