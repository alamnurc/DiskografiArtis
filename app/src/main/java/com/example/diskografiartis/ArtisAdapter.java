package com.example.diskografiartis;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ArtisAdapter extends RecyclerView.Adapter<ArtisAdapter.ArtisViewHolder> {

    private Context context;
    private ArrayList<User> dataArtis;

    public ArtisAdapter(Context context, ArrayList<User> dataArtis) {
        this.context = context;
        this.dataArtis = dataArtis;
    }

    @NonNull
    @Override
    public ArtisViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.activity_list_artis, parent, false);
        return new ArtisViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtisViewHolder holder, int position) {
        User tempArtis = dataArtis.get(position);
        holder.foto = tempArtis.getFoto();
        holder.idArtis = tempArtis.getIdArtis();
        holder.tvArtis.setText(tempArtis.getArtis());
        holder.anggota = tempArtis.getAnggota();
        holder.tvNegara.setText(tempArtis.getNegara());
        holder.tvGenre.setText(tempArtis.getGenre());
        holder.tahunaktif = tempArtis.getTahunaktif();
        holder.aktifsampai = tempArtis.getAktifsampai();
        holder.sinopsis = tempArtis.getSinopsis();
        holder.album = tempArtis.getAlbum();
        holder.rilis = tempArtis.getRilis();
        holder.lagu = tempArtis.getLagu();

        try {
            File file = new File(holder.foto);
            Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
            holder.imgArtis.setImageBitmap(bitmap);
            holder.imgArtis.setContentDescription(holder.foto);
        } catch (FileNotFoundException er) {
            er.printStackTrace();
            Toast.makeText(context, "Gambar tidak ditemukan", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getItemCount() {
        return dataArtis.size();
    }

    public class ArtisViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private ImageView imgArtis;
        private TextView tvArtis, tvNegara, tvGenre;
        private int idArtis;
        private String foto, anggota, tahunaktif, aktifsampai, sinopsis, album, rilis, lagu;

        public ArtisViewHolder(@NonNull View itemView) {
            super(itemView);

            imgArtis = itemView.findViewById(R.id.foto_artis);
            tvArtis = itemView.findViewById(R.id.label_artis);
            tvNegara = itemView.findViewById(R.id.label_negara);
            tvGenre = itemView.findViewById(R.id.label_genre);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent tampilDiskografi = new Intent(context, TampilArtis.class);
            tampilDiskografi.putExtra("ID", idArtis);
            tampilDiskografi.putExtra("FOTO", foto);
            tampilDiskografi.putExtra("ARTIS", tvArtis.getText().toString());
            tampilDiskografi.putExtra("ANGGOTA", anggota);
            tampilDiskografi.putExtra("NEGARA", tvNegara.getText().toString());
            tampilDiskografi.putExtra("GENRE", tvGenre.getText().toString());
            tampilDiskografi.putExtra("TAHUNAKTIF", tahunaktif);
            tampilDiskografi.putExtra("AKTIFSAMPAI", aktifsampai);
            tampilDiskografi.putExtra("SINOPSIS", sinopsis);
            tampilDiskografi.putExtra("ALBUM", album);
            tampilDiskografi.putExtra("RILIS", rilis);
            tampilDiskografi.putExtra("LAGU", lagu);
            context.startActivity((tampilDiskografi));
        }

        @Override
        public boolean onLongClick(View v) {
            Intent tampilInput = new Intent(context, InputArtis.class);
            tampilInput.putExtra("CRUD", "UPDATE");
            tampilInput.putExtra("ID", idArtis);
            tampilInput.putExtra("FOTO", foto);
            tampilInput.putExtra("ARTIS", tvArtis.getText().toString());
            tampilInput.putExtra("ANGGOTA", anggota);
            tampilInput.putExtra("NEGARA", tvNegara.getText().toString());
            tampilInput.putExtra("GENRE", tvGenre.getText().toString());
            tampilInput.putExtra("TAHUNAKTIF", tahunaktif);
            tampilInput.putExtra("AKTIFSAMPAI", aktifsampai);
            tampilInput.putExtra("SINOPSIS", sinopsis);
            tampilInput.putExtra("ALBUM", album);
            tampilInput.putExtra("RILIS", rilis);
            tampilInput.putExtra("LAGU", lagu);
            context.startActivity(tampilInput);
            return true;
        }
    }
}