package industries.julian.jodohku;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListContactActivity extends AppCompatActivity {
    private  ContactAdapter contactAdapter;
    private RecyclerView rcyTeman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_contact);

        rcyTeman = (RecyclerView)findViewById(R.id.rcyTeman);
        List<Teman> listTeman = new ArrayList<Teman>();
        contactAdapter = new ContactAdapter(listTeman, this);

        //menggabungkan recyclerview dengan temanadapter
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        rcyTeman.setLayoutManager(lm);
        rcyTeman.setItemAnimator(new DefaultItemAnimator());
        rcyTeman.setAdapter(contactAdapter);

        listTeman.add(new Teman("Katon Gilang Bagaskara", "Laki Laki"));
        listTeman.add(new Teman("Marcellinus", "Laki Laki"));
        listTeman.add(new Teman("Felix Evan", "Laki Laki"));
        listTeman.add(new Teman("Dio Pramantha", "Laki Laki"));
        listTeman.add(new Teman("Mukidi", "Laki Laki"));
        listTeman.add(new Teman("Samidi", "Laki Laki"));
        listTeman.add(new Teman("Dody Lydia", "Laki Laki"));
        listTeman.add(new Teman("Sule", "Laki Laki"));
        contactAdapter.notifyDataSetChanged();
    }
}
