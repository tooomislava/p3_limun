package zilic.limuni;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.Red> {

    private List<Limun> limuni;
    private LayoutInflater layoutInflater;
    private ItemClickListener itemClickListener;

    public Adapter(Context context){
        layoutInflater = LayoutInflater.from(context);
        limuni=new ArrayList<>();
    }

    public void setLimuni(List<Limun> limuni) {
        this.limuni = limuni;
    }

    @NonNull
    @Override
    public Red onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.red_layout, parent, false);
        return new Red(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Red holder, int position) {
        Limun a = this.limuni.get(position);
        holder.zemlja.setText(a.getZemlja());

    }

    @Override
    public int getItemCount() {
        return this.limuni==null ? 0 : this.limuni.size();
    }

    public class Red extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView zemlja;


        public Red(@NonNull View itemView) {
            super(itemView);
            zemlja = itemView.findViewById(R.id.zemlja);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(itemClickListener!=null){
                itemClickListener.onItemClick(limuni.get(getAdapterPosition()));
            }
        }
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(Limun limun);
    }

}
