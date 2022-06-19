package com.example.diskografiartis;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;

public class DBController extends SQLiteOpenHelper {

    private final static int DATABASE_VERSION = 3;
    private final static String DATABASE_NAME = "DB_Diskografi";
    private final static String TABLE_ARTIS = "T_Artis";
    private final static String KEY_ID_ARTIS = "Id_Lagu";
    private final static String KEY_FOTO = "Foto";
    private final static String KEY_ARTIS = "Artis";
    private final static String KEY_ANGGOTA = "Anggota";
    private final static String KEY_NEGARA = "Negara";
    private final static String KEY_GENRE = "Genre";
    private final static String KEY_TAHUN_AKTIF = "Tahun_Aktif";
    private final static String KEY_AKTIF_SAMPAI = "Aktif_Sampai";
    private final static String KEY_SINOPSIS = "Sinopsis";
    private final static String KEY_ALBUM = "Album";
    private final static String KEY_RILIS = "Rilis";
    private final static String KEY_LAGU = "Lagu";
    private Context context;

    public DBController(Context ctx) {
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = ctx;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_ARTIS = "CREATE TABLE " + TABLE_ARTIS
                + "(" + KEY_ID_ARTIS + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_FOTO + " TEXT, "
                + KEY_ARTIS + " TEXT, "
                + KEY_ANGGOTA + " TEXT, "
                + KEY_NEGARA + " TEXT, "
                + KEY_GENRE + " TEXT, "
                + KEY_TAHUN_AKTIF + " TEXT, "
                + KEY_AKTIF_SAMPAI + " TEXT, "
                + KEY_SINOPSIS + " TEXT, "
                + KEY_ALBUM + " TEXT, "
                + KEY_RILIS + " TEXT, "
                + KEY_LAGU + " TEXT);";

        db.execSQL(CREATE_TABLE_ARTIS);
        inisialisasiDataAwal(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_ARTIS;
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    public void tambahArtis(User dataArtis) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_FOTO, dataArtis.getFoto());
        cv.put(KEY_ARTIS, dataArtis.getArtis());
        cv.put(KEY_ANGGOTA, dataArtis.getAnggota());
        cv.put(KEY_NEGARA, dataArtis.getNegara());
        cv.put(KEY_GENRE, dataArtis.getGenre());
        cv.put(KEY_TAHUN_AKTIF, dataArtis.getTahunaktif());
        cv.put(KEY_AKTIF_SAMPAI, dataArtis.getAktifsampai());
        cv.put(KEY_SINOPSIS, dataArtis.getSinopsis());
        cv.put(KEY_ALBUM, dataArtis.getAlbum());
        cv.put(KEY_RILIS, dataArtis.getRilis());
        cv.put(KEY_LAGU, dataArtis.getLagu());

        db.insert(TABLE_ARTIS, null, cv);
        db.close();
    }

    public void tambahArtis(User dataArtis, SQLiteDatabase db) {
        ContentValues cv = new ContentValues();

        cv.put(KEY_FOTO, dataArtis.getFoto());
        cv.put(KEY_ARTIS, dataArtis.getArtis());
        cv.put(KEY_ANGGOTA, dataArtis.getAnggota());
        cv.put(KEY_NEGARA, dataArtis.getNegara());
        cv.put(KEY_GENRE, dataArtis.getGenre());
        cv.put(KEY_TAHUN_AKTIF, dataArtis.getTahunaktif());
        cv.put(KEY_AKTIF_SAMPAI, dataArtis.getAktifsampai());
        cv.put(KEY_SINOPSIS, dataArtis.getSinopsis());
        cv.put(KEY_ALBUM, dataArtis.getAlbum());
        cv.put(KEY_RILIS, dataArtis.getRilis());
        cv.put(KEY_LAGU, dataArtis.getLagu());

        db.insert(TABLE_ARTIS, null, cv);
    }

    public void editArtis(User dataArtis) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_FOTO, dataArtis.getFoto());
        cv.put(KEY_ARTIS, dataArtis.getArtis());
        cv.put(KEY_ANGGOTA, dataArtis.getAnggota());
        cv.put(KEY_NEGARA, dataArtis.getNegara());
        cv.put(KEY_GENRE, dataArtis.getGenre());
        cv.put(KEY_TAHUN_AKTIF, dataArtis.getTahunaktif());
        cv.put(KEY_AKTIF_SAMPAI, dataArtis.getAktifsampai());
        cv.put(KEY_SINOPSIS, dataArtis.getSinopsis());
        cv.put(KEY_ALBUM, dataArtis.getAlbum());
        cv.put(KEY_RILIS, dataArtis.getRilis());
        cv.put(KEY_LAGU, dataArtis.getLagu());

        db.update(TABLE_ARTIS, cv, KEY_ID_ARTIS + "=?", new String[]{String.valueOf(dataArtis.getIdArtis())});
        db.close();
    }

    public void hapusArtis (int idArtis) {
    SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_ARTIS, KEY_ID_ARTIS + "=?", new String[]{String.valueOf(idArtis)});
        db.close();
    }

    public ArrayList<User> getAllArtis() {
        ArrayList<User> dataArtis = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_ARTIS;
        SQLiteDatabase db = getReadableDatabase();
        Cursor csr = db.rawQuery(query, null);
        if (csr.moveToFirst()) {
            do {
                User tempArtis = new User(
                        csr.getInt(0),
                        csr.getString(1),
                        csr.getString(2),
                        csr.getString(3),
                        csr.getString(4),
                        csr.getString(5),
                        csr.getString(6),
                        csr.getString(7),
                        csr.getString(8),
                        csr.getString(9),
                        csr.getString(10),
                        csr.getString(11)
                );

                dataArtis.add(tempArtis);
            } while (csr.moveToNext());
        }
        return dataArtis;
    }

    private String storeImageFile(int id) {
        String location;
        Bitmap image = BitmapFactory.decodeResource(context.getResources(), id);
        location = InputArtis.saveImageToInternalStorage(image, context);
        return location;
    }

    private void inisialisasiDataAwal(SQLiteDatabase db) {
        int idArtis = 0;

        //Data Artis ke-1
        User artis1 = new User(
                idArtis,
                storeImageFile(R.drawable.artis1),
                "Gesaffelstein",
                "-",
                "Belanda",
                "Elektronik",
                "2008",
                "Sekarang",
                "Mike Lévy (French pronunciation: \u200B[majk levi]; born 24 June 1985[1] known professionally as Gesaffelstein (German pronunciation: [ɡəˈzafl̩ʃtaɪ̯n]), is a French music programmer, DJ, songwriter, and record producer from Lyon. He has worked alongside The Weeknd, Daft Punk, Kanye West, A$AP Rocky, Electric Youth, Haim, Miss Kittin, The Hacker, Jean-Michel Jarre, and Pharrell Williams.",
                "Hyperion",
                "8 Maret 2019",
                "Hyperion\n" +
                        "Reset\n" +
                        "Lost in the Fire (with The Weeknd)\n" +
                        "Ever Now\n" +
                        "Blast Off (with Pharrell Williams)\n" +
                        "So Bad (featuring Haim)\n" +
                        "Forever (featuring The Hacker and Electric Youth)\n" +
                        "Vortex\n" +
                        "Memora\n" +
                        "Humanity Gone"
        );
        tambahArtis(artis1, db);
        idArtis++;

        //Data Artis ke-2
        User artis2 = new User(
                idArtis,
                storeImageFile(R.drawable.artis1),
                "Gesaffelstein",
                "-",
                "Belanda",
                "Elektronik",
                "2008",
                "Sekarang",
                "Mike Lévy (French pronunciation: \u200B[majk levi]; born 24 June 1985[1] known professionally as Gesaffelstein (German pronunciation: [ɡəˈzafl̩ʃtaɪ̯n]), is a French music programmer, DJ, songwriter, and record producer from Lyon. He has worked alongside The Weeknd, Daft Punk, Kanye West, A$AP Rocky, Electric Youth, Haim, Miss Kittin, The Hacker, Jean-Michel Jarre, and Pharrell Williams.",
                "Hyperion",
                "8 Maret 2019",
                "Hyperion\n" +
                        "Reset\n" +
                        "Lost in the Fire (with The Weeknd)\n" +
                        "Ever Now\n" +
                        "Blast Off (with Pharrell Williams)\n" +
                        "So Bad (featuring Haim)\n" +
                        "Forever (featuring The Hacker and Electric Youth)\n" +
                        "Vortex\n" +
                        "Memora\n" +
                        "Humanity Gone"
        );
        tambahArtis(artis2, db);
    }
}