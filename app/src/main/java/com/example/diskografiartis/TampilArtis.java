package com.example.diskografiartis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class TampilArtis extends AppCompatActivity {

    private ImageView imgArtis;
    private TextView tvArtis, anggota, tvNegara, tvGenre, tahunaktif, aktifsampai, sinopsis, album, rilis, lagu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_artis);

        imgArtis = findViewById(R.id.iv_lagu);
        tvArtis = findViewById(R.id.edit_artist);
        anggota = findViewById(R.id.edit_anggota);
        tvNegara = findViewById(R.id.edit_negara);
        tvGenre = findViewById(R.id.edit_genre);
        tahunaktif = findViewById(R.id.edit_tahun_aktif);
        aktifsampai = findViewById(R.id.edit_aktif_sampai);
        sinopsis = findViewById(R.id.edit_isi_sinopsis);
        album = findViewById(R.id.edit_nama_album);
        rilis = findViewById(R.id.edit_tahun_rilis);
        lagu = findViewById(R.id.edit_isi_lagu);

        Intent terimaData = getIntent();
        tvArtis.setText(terimaData.getStringExtra("ARTIS"));
        anggota.setText(terimaData.getStringExtra("ANGGOTA"));
        tvNegara.setText(terimaData.getStringExtra("NEGARA"));
        tvGenre.setText(terimaData.getStringExtra("GENRE"));
        tahunaktif.setText(terimaData.getStringExtra("TAHUNAKTIF"));
        aktifsampai.setText(terimaData.getStringExtra("AKTIFSAMPAI"));
        sinopsis.setText(terimaData.getStringExtra("SINOPSIS"));
        album.setText(terimaData.getStringExtra("ALBUM"));
        rilis.setText(terimaData.getStringExtra("RILIS"));
        lagu.setText(terimaData.getStringExtra("LAGU"));
        String imgLocation = terimaData.getStringExtra("FOTO");

        try {
            File file = new File(imgLocation);
            Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
            imgArtis.setImageBitmap(bitmap);
            imgArtis.setContentDescription(imgLocation);
        } catch (FileNotFoundException er) {
            er.printStackTrace();
            Toast.makeText(this, "Gagal mengambil foto", Toast.LENGTH_SHORT).show();
        }
    }
}