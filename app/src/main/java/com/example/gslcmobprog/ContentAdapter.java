package com.example.gslcmobprog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ContentAdapter extends BaseAdapter {

    Context context;
    String[] namaWarna;
    int[] imageWarna;
    LayoutInflater inflater;

    public ContentAdapter(Context context, String[] namaWarna, int[] imageWarna) {
        this.context = context;
        this.namaWarna = namaWarna;
        this.imageWarna = imageWarna;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return namaWarna.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view =inflater.inflate(R.layout.activity_warna,null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageData);
        TextView namaView = (TextView) view.findViewById(R.id.namaData);
        imageView.setImageResource(imageWarna[i]);
        namaView.setText(namaWarna[i]);
        return view;
    }
}
