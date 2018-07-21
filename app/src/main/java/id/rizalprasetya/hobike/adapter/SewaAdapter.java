package id.rizalprasetya.hobike.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedList;

import id.rizalprasetya.hobike.R;

/**
 * Created by Rizal Prasetya on 04/06/2018.
 */

public class SewaAdapter extends RecyclerView.Adapter<SewaAdapter.SewaViewHolder>{
    private final LinkedList<String> listSewa;
    private LayoutInflater layoutInflater;

    public SewaAdapter(Context context, LinkedList<String> listSewa) {
        layoutInflater = LayoutInflater.from(context);
        this.listSewa = listSewa;
    }

    @Override
    public SewaAdapter.SewaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewID = layoutInflater.inflate(R.layout.item_sewa, parent, false);
        return new SewaViewHolder(viewID, this);
    }

    @Override
    public void onBindViewHolder(SewaAdapter.SewaViewHolder holder, int position) {
        String mCurrent = listSewa.get(position);
        holder.sewaItemView.setText(mCurrent);
    }

    @Override
    public int getItemCount() {
        return listSewa.size();
    }

    class SewaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final TextView sewaItemView;
        final SewaAdapter msewaAdapter;

        public SewaViewHolder(View itemView, SewaAdapter adapter) {
            super(itemView);
            sewaItemView = (TextView) itemView.findViewById(R.id.txt_ID);
            this.msewaAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int mPosition = getLayoutPosition();
            String element = listSewa.get(mPosition);
            listSewa.set(mPosition, ""+ element);
            msewaAdapter.notifyDataSetChanged();
        }
    }
}
