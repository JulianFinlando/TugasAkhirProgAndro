package industries.julian.jodohku;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class TemanAdapter extends RecyclerView.Adapter<TemanAdapter.TemanHolder> {
    private List<Teman> listTeman;
    private Context mContext;

    public TemanAdapter(List<Teman> listTeman, Context context){
        this.listTeman = listTeman;
        this.mContext = context;
    }
    //konversi layout item_teman.xml ke bentuk java
    @Override
    public TemanHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_teman, parent,false);
        return new TemanHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TemanHolder holder, int position) {
        Teman teman = listTeman.get(position);
        holder.txvNamaLengkap.setText(teman.nama_lengkap);
        holder.txvJenisKelamin.setText(teman.jenis_kelamin);
    }

    @Override
    public int getItemCount() {
        return listTeman.size();
    }

    public class TemanHolder extends RecyclerView.ViewHolder{
        private TextView txvNamaLengkap, txvJenisKelamin;
        public TemanHolder(View itemView){
            super(itemView);

            txvNamaLengkap = (TextView)itemView.findViewById(R.id.txvNamaLengkap);
            txvJenisKelamin = (TextView)itemView.findViewById(R.id.txvJenisKelamin);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(mContext, "Teman ini bernama "+txvNamaLengkap.getText()
                            .toString(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
