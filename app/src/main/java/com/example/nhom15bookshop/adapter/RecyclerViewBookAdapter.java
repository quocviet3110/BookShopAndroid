package com.example.nhom15bookshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhom15bookshop.R;
import com.example.nhom15bookshop.models.BookDTO;
import com.example.nhom15bookshop.ui.trangchu.TrangChuActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewBookAdapter extends RecyclerView.Adapter<RecyclerViewBookAdapter.RecyclerViewBookHolder> {

    private List<BookDTO> productDataArrayList;
    private Context mcontext;

    public RecyclerViewBookAdapter() {
    }

    public RecyclerViewBookAdapter(List<BookDTO> productDataArrayList, Context mcontext ) {
        this.productDataArrayList = productDataArrayList;
        this.mcontext = mcontext;
    }
    public void setData(List<BookDTO> list){
        this.productDataArrayList= list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public RecyclerViewBookHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate Layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trangchu_card_layout, parent, false);
        return new RecyclerViewBookHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewBookHolder holder, int position) {
        // Set the data to textview and imageview.
        BookDTO bookDTO = productDataArrayList.get(position);
        holder.tvTitle.setText(bookDTO.getName());
        holder.tvPrice.setText(bookDTO.getPrice() + " đ");
        Picasso.get().load(bookDTO.getUrlImage()).into(holder.ivProduct);
    }

    @Override
    public int getItemCount() {
        // this method returns the size of recyclerview
        if(productDataArrayList != null){
            return productDataArrayList.size();
        }
        return 0;

    }

    // View Holder Class to handle Recycler View.
    public class RecyclerViewBookHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle;
        private TextView tvPrice;
        private ImageView ivProduct;

        public RecyclerViewBookHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            ivProduct = itemView.findViewById(R.id.ivProduct);
        }
    }
}
