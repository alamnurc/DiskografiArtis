package com.example.diskografiartis;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;
import java.util.UUID;

public class InputArtis extends AppCompatActivity implements  View.OnClickListener{

    private DBController dbController;
    private boolean updateData = false;
    private int idArtis = 0;
    private ImageView ivArtis;
    private EditText editArtis, editAnggota, editNegara, editGenre, editTahunAktif, editAktifSampai, editSinopsis, editAlbum, editRilis, editLagu;
    private Button btnSimpan;
    private Object CropImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_artis);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        ivArtis = findViewById(R.id.foto_artis);
        editArtis = findViewById(R.id.edit_artist);
        editAnggota = findViewById(R.id.edit_anggota);
        editNegara = findViewById(R.id.edit_negara);
        editGenre = findViewById(R.id.edit_genre);
        editTahunAktif = findViewById(R.id.edit_tahun_aktif);
        editAktifSampai = findViewById(R.id.edit_aktif_sampai);
        editSinopsis = findViewById(R.id.edit_isi_sinopsis);
        editAlbum = findViewById(R.id.edit_nama_album);
        editRilis = findViewById(R.id.edit_rilis);
        editLagu = findViewById(R.id.edit_isi_lagu);

        dbController = new DBController(this);

        Intent terimaIntent = getIntent();
        Bundle data = terimaIntent.getExtras();
        if (data.getString("CRUD").equals("INSERT")) {
            updateData = false;
        } else {
            updateData = true;
            idArtis = data.getInt("ID");
            loadImageFromInternalStorage(data.getString("FOTO"));
            editArtis.setText(data.getString("ARTIS"));
            editAnggota.setText(data.getString("ANGGOTA"));
            editNegara.setText(data.getString("NEGARA"));
            editGenre.setText(data.getString("GENRE"));
            editTahunAktif.setText(data.getString("TAHUNAKTIF"));
            editAktifSampai.setText(data.getString("AKTIFSAMPAI"));
            editSinopsis.setText(data.getString("SINOPSIS"));
            editAlbum.setText(data.getString("ALBUM"));
            editRilis.setText(data.getString("RILIS"));
            editLagu.setText(data.getString("LAGU"));
        }

        ivArtis.setOnClickListener(this);
        btnSimpan.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    public static String saveImageToInternalStorage(Bitmap image, Context context) {
        ContextWrapper ctxWrapper = new ContextWrapper(context);
        File file = ctxWrapper.getDir("images", MODE_PRIVATE);
        String uniqueID = UUID.randomUUID().toString();
        file = new File(file, "lagu-"+ uniqueID + ".jpg");
        try {
            OutputStream stream = null;
            stream = new FileOutputStream(file);
            image.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            stream.flush();
            stream.close();
        } catch (IOException er) {
            er.printStackTrace();
        }
        Uri savedImage = Uri.parse(file.getAbsolutePath());
        return savedImage.toString();
    }

    private void loadImageFromInternalStorage(String imageLocation) {
        try {
            File file = new File(imageLocation);
            Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
            ivArtis.setImageBitmap(bitmap);
            ivArtis.setContentDescription(imageLocation);
        } catch (FileNotFoundException er) {
            er.printStackTrace();
            Toast.makeText(this, "Gagal mengambil gambar dari media penyimpanan", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        MenuItem item = menu.findItem(R.id.item_menu_hapus);

        if(updateData==true) {
            item.setEnabled(true);
            item.getIcon().setAlpha(255);
        } else {
            item.setEnabled(false);
            item.getIcon().setAlpha(125);
        }

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.input_artis_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id==R.id.item_menu_hapus) {
            hapusData();
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void simpanData() {
        String foto, artis, anggota, negara, genre, tahunaktif, aktifsampai, sinopsis, album, rilis, lagu;
        foto = ivArtis.getContentDescription().toString();
        artis = editArtis.getText().toString();
        anggota = editAnggota.getText().toString();
        negara = editNegara.getText().toString();
        genre = editGenre.getText().toString();
        tahunaktif = editTahunAktif.getText().toString();
        aktifsampai = editAktifSampai.getText().toString();
        sinopsis = editSinopsis.getText().toString();
        album = editAlbum.getText().toString();
        rilis = editRilis.getText().toString();
        lagu = editLagu.getText().toString();

        User tempArtis = new User (
                idArtis, foto, artis, anggota, negara, genre, tahunaktif, aktifsampai, sinopsis, album, rilis, lagu
        );

        if (updateData == true) {
            dbController.editArtis(tempArtis);
            Toast.makeText(this, "Data artis diperbarui", Toast.LENGTH_SHORT).show();
        } else {
            dbController.tambahArtis(tempArtis);
            Toast.makeText(this, "Data artis ditambahkan", Toast.LENGTH_SHORT).show();
        }
        finish();
    }

    private void hapusData() {
        dbController.hapusArtis(idArtis);
        Toast.makeText(this, "Data artis dihapus", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        int idView = v.getId();

        if (idView == R.id.btn_simpan) {
            String namaArtis = editArtis.getText().toString();
            String namaNegara = editNegara.getText().toString();
            String namaGenre = editGenre.getText().toString();

            boolean isEmpty = false;
            if (TextUtils.isEmpty(namaArtis)){
                isEmpty = true;
                editArtis.setError("Masukkan Nama Artis");
                Toast.makeText(InputArtis.this, "Masukkan Nama Artis", Toast.LENGTH_SHORT).show();
            } else if (TextUtils.isEmpty(namaNegara)){
                isEmpty = true;
                editNegara.setError("Masukkan Negara");
                Toast.makeText(InputArtis.this, "Masukkan Negara", Toast.LENGTH_SHORT).show();
            } else if (TextUtils.isEmpty(namaGenre)){
                isEmpty = true;
                editGenre.setError("Masukkan Genre");
                Toast.makeText(InputArtis.this, "Masukkan Genre", Toast.LENGTH_SHORT).show();
            } else {
                simpanData();
            }
        }
    }
}