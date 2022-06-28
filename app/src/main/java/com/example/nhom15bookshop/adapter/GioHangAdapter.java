package com.example.nhom15bookshop.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.nhom15bookshop.GioHangActivity;
import com.example.nhom15bookshop.R;
import com.example.nhom15bookshop.models.CartDTO;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class GioHangAdapter extends ArrayAdapter<CartDTO>  {
    private final Context context;
    private final int resource;
    private final List<CartDTO> data;
    private final List<CartDTO> dataTemp = new ArrayList<>();
    int soLuong = 1;

    public GioHangAdapter(@NonNull Context context, int resource, @NonNull List<CartDTO> data) {
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
        CheckBox cbGioHang = convertView.findViewById(R.id.checkbox);
        TextView tvTitle = convertView.findViewById(R.id.tvTitle);
        TextView tvPrice = convertView.findViewById(R.id.tvPrice);
        TextView tvSoLuong = convertView.findViewById(R.id.tvSoLuong);
        ImageView ivAnhSanPham = convertView.findViewById(R.id.ivAnhSanPham);
        Button btnGiam = convertView.findViewById(R.id.btnGiam);
        Button btnTang = convertView.findViewById(R.id.btnTang);
        ImageView ivXoa = convertView.findViewById(R.id.ivXoa);

        CartDTO cart = data.get(position);
        Picasso.get().load(cart.getBookCart().getUrlImage()).into(ivAnhSanPham);
        tvTitle.setText(cart.getBookCart().getName());
        tvPrice.setText(cart.getBookCart().getPrice()+" đ");
        tvSoLuong.setText(cart.getNumber()+"");
        btnGiam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soLuong = Integer.parseInt(tvSoLuong.getText().toString());
                soLuong = soLuong - 1;
                if (soLuong >= 1) {
                    tvSoLuong.setText(soLuong + "");
                    ((GioHangActivity) context).soLuongSanPham(position,soLuong);
                }
            }
        });

        btnTang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soLuong = Integer.parseInt(tvSoLuong.getText().toString());
                soLuong = soLuong + 1;
              if (soLuong <= 10) {
                  tvSoLuong.setText(soLuong + "");
                   ((GioHangActivity) context).soLuongSanPham(position,soLuong);
               }
            }
        });

        cbGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( ((CheckBox)v).isChecked() ) {
                    // perform logic
                    ((GioHangActivity) context).sanPhamDaChon(position,1, cart.getBookCart().getId());
                } else {
                    ((GioHangActivity) context).sanPhamDaChon(position,0, cart.getBookCart().getId());
                }
            }
        });

        ivXoa.setOnClickListener(view -> {
            ((GioHangActivity) context).xoaSanPham(cart);
        });

        return convertView;
    }
}
