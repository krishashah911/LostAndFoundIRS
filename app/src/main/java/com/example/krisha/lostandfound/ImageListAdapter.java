package com.example.krisha.lostandfound;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageListAdapter extends ArrayAdapter<ObjectDetails> {
    private Activity context;
    private int resource;
    private List<ObjectDetails> listImage;

    public ImageListAdapter(@NonNull Activity context, @LayoutRes int resource, @NonNull List<ObjectDetails> objects){
        super(context, resource,objects);
        this.context = context;
        this.resource = resource;
        listImage = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();

        View v = inflater.inflate(resource, null);
        TextView object = (TextView) v.findViewById(R.id.tvObject);
        TextView name = (TextView) v.findViewById(R.id.tvName);
        TextView contact = (TextView) v.findViewById(R.id.tvContact);
        ImageView img = (ImageView) v.findViewById(R.id.imgView);

        object.setText(listImage.get(position).getObject());
        name.setText(listImage.get(position).getName());
        contact.setText(listImage.get(position).getContact());
       // Picasso.get().load(listImage).into(img);
        Glide.with(context).load(listImage.get(position).getUrl()).into(img);

        return v;

    }
}
