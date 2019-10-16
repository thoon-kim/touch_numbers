package org.techtown.numbertouch;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.ArrayList;
import java.util.Comparator;

import static java.util.Collections.*;

public class RankAdapter extends RecyclerView.Adapter<RankAdapter.ViewHolder>  {
    ArrayList<Rank> items = new ArrayList<Rank>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.rank_item, viewGroup, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Rank item = items.get(position);
        viewHolder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(Rank item) {
        items.add(item);
    }

    public void setItems(ArrayList<Rank> items) {
        this.items = items;
    }

    public Rank getItem(int position) {
        return items.get(position);
    }

    public void setItem(int position, Rank item) {
        items.set(position, item);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        TextView textView2;
        TextView textView3;
        TextView textView4;

        public ViewHolder(View itemView){
            super(itemView);

            textView = itemView.findViewById(R.id.item_rank_text);
            textView2 = itemView.findViewById(R.id.item_time_text);
            textView3 = itemView.findViewById(R.id.item_type_text);
            textView4 = itemView.findViewById(R.id.item_date_text);
        }

        public void setItem(Rank item){
            textView.setText(String.valueOf(item.getRank()));
            textView2.setText(item.getTime());
            textView3.setText(item.getType());
            textView4.setText(item.getDate());
        }
    }
}
