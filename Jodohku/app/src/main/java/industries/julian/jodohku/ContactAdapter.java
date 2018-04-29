package industries.julian.jodohku;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactHolder> {
    private List<Teman> listTeman;
    private Context mContext;

    public ContactAdapter(List<Teman> listTeman, Context context){
        this.listTeman = listTeman;
        this.mContext = context;
    }
    //konversi layout item_teman.xml ke bentuk java
    @Override
    public ContactHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_teman, parent,false);
        return new ContactHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ContactAdapter.ContactHolder holder, int position) {
        Teman teman = listTeman.get(position);
        holder.txvNamaLengkap.setText(teman.nama_lengkap);
        holder.txvJenisKelamin.setText(teman.jenis_kelamin);
    }

    @Override
    public int getItemCount() {
        return listTeman.size();
    }

    public class ContactHolder extends RecyclerView.ViewHolder{
        Intent i;
        private TextView txvNamaLengkap, txvJenisKelamin;
        public ContactHolder(View itemView){
            super(itemView);
            mContext = itemView.getContext();

            txvNamaLengkap = (TextView)itemView.findViewById(R.id.txvNamaLengkap);
            txvJenisKelamin = (TextView)itemView.findViewById(R.id.txvJenisKelamin);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    /*Toast.makeText(mContext, "Teman ini bernama "+txvNamaLengkap.getText()
                            .toString(), Toast.LENGTH_LONG).show();*/
                    final Intent intent;
                    intent =  new Intent(mContext, ChatActivity.class);
                    mContext.startActivity(intent);
                }
            });
        }
    }
}