package com.example.nhom15bookshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.nhom15bookshop.R;
import com.example.nhom15bookshop.models.CommentDTO;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ListviewCTSPAdapter extends ArrayAdapter<CommentDTO> {
    private final Context context;
    private final int resource;
    private final List<CommentDTO> data;
    private final List<CommentDTO> dataTemp = new ArrayList<>();

    public ListviewCTSPAdapter(@NonNull Context context, int resource, @NonNull List<CommentDTO> data) {
        super(context, resource, data);
        this.context = context;
        this.resource = resource;
        this.data = data;
        dataTemp.addAll(data);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource,null);
        TextView tvTenNguoiBinhLuan = convertView.findViewById(R.id.tvTenNguoiBinhLuan);
        TextView tvNgayBinhLuan = convertView.findViewById(R.id.tvNgayBinhLuan);
        TextView tvNoiDungBinhLuan = convertView.findViewById(R.id.tvNoiDungBinhLuan);
        ImageView ivAnhNguoiBinhLuan = convertView.findViewById(R.id.ivAnhNguoiBinhLuan);

        CommentDTO comment = data.get(position);
        tvTenNguoiBinhLuan.setText(comment.getCustomerComment().getName());
        tvNgayBinhLuan.setText(comment.getConvertDate());
        tvNoiDungBinhLuan.setText(comment.getContent());
        Picasso.get().load(comment.getCustomerComment().getAvatar()).into(ivAnhNguoiBinhLuan);
        return convertView;
    }
}
