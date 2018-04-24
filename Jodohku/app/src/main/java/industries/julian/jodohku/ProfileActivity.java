package industries.julian.jodohku;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    TextView profilNama;
    Firebase fb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile); /*
        Firebase.setAndroidContext(this);

        fb = new Firebase("https://jodohku-17e7d.firebaseio.com/Name");
        profilNama = (TextView) findViewById(R.id.txvNama);

        fb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                profilNama.setText(value);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        })*/
    }
}
