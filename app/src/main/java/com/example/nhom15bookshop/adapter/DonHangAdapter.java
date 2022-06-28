package com.example.nhom15bookshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.nhom15bookshop.DonHangActivity;
import com.example.nhom15bookshop.R;
import com.example.nhom15bookshop.models.BookDTO;
import com.example.nhom15bookshop.models.OrderDTO;
import com.example.nhom15bookshop.models.OrderDetailDTO;
import com.example.nhom15bookshop.ui.trangchu.TrangChuActivity;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class DonHangAdapter extends ArrayAdapter<OrderDetailDTO> {
    private final Context context;
    private final int resource;
    private final List<OrderDetailDTO> data;
    private final List<OrderDetailDTO> dataTemp = new ArrayList<>();

    public DonHangAdapter(@NonNull Context context, int resource, @NonNull List<OrderDetailDTO> data) {
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
        TextView tvTitle = convertView.findViewById(R.id.tvTitle);
        TextView tvPrice = convertView.findViewById(R.id.tvPrice);
        TextView tvSoLuong = convertView.findViewById(R.id.tvQuantity);
        TextView tvTrangThaiDonHang = convertView.findViewById(R.id.tvTrangThaiDonHang);
        TextView tvMaDonHang = convertView.findViewById(R.id.tvMaDonHang);
        TextView tvNgay = convertView.findViewById(R.id.tvNgay);
        ImageView ivAnhSanPham = convertView.findViewById(R.id.ivAnhSanPham);

        OrderDetailDTO orderDetailDTODTO = data.get(position);
            tvTitle.setText(orderDetailDTODTO.getBookOrder().getName());
            tvPrice.setText("Giá: " + orderDetailDTODTO.getBookOrder().getPrice());
            tvSoLuong.setText("Số lượng: " + orderDetailDTODTO.getNumber());
            tvTrangThaiDonHang.setText(orderDetailDTODTO.getOrderDetail().getStatus());
            tvMaDonHang.setText(orderDetailDTODTO.getId()+"");
            tvNgay.setText(orderDetailDTODTO.getOrderDetail().getConvertDate());
            Picasso.get().load(orderDetailDTODTO.getBookOrder().getUrlImage()).into(ivAnhSanPham);



        return convertView;
    }

}

