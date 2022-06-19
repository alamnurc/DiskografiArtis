package com.example.diskografiartis;

public class User {
    private int idArtis;
    private String foto, artis, anggota, negara, genre, tahunaktif, aktifsampai, sinopsis, album, rilis, lagu;

    public User(int idArtis, String foto, String artis , String anggota, String negara, String genre, String tahunaktif, String aktifsampai, String sinopsis, String album, String rilis, String lagu){
        this.idArtis = idArtis;
        this.foto = foto;
        this.artis = artis;
        this.anggota = anggota;
        this.negara = negara;
        this.genre = genre;
        this.tahunaktif = tahunaktif;
        this.aktifsampai = aktifsampai;
        this.sinopsis = sinopsis;
        this.album = album;
        this.rilis = rilis;
        this.lagu = lagu;
    }

    public int getIdArtis(){
        return idArtis;
    }
    public void setIdArtis(int idArtis) { this.idArtis = idArtis; }

    public String getFoto(){
        return foto;
    }
    public void setFoto(String foto) { this.foto = foto; }

    public String getArtis(){
        return artis;
    }
    public void setArtis(String artis) { this.artis = artis; }

    public String getAnggota(){
        return anggota;
    }
    public void setAnggota(String anggota) { this.anggota = anggota; }

    public String getNegara(){
        return negara;
    }
    public void setNegara(String negara){
        this.negara = negara;
    }

    public String getGenre(){
        return genre;
    }
    public void setGenre(String genre){
        this.genre = genre;
    }

    public String getTahunaktif(){
        return tahunaktif;
    }
    public void setTahunaktif(String tahunaktif){
        this.tahunaktif = tahunaktif;
    }

    public String getAktifsampai(){
        return aktifsampai;
    }
    public void setAktifsampai(String aktifsampai){
        this.aktifsampai = aktifsampai;
    }

    public String getSinopsis(){
        return sinopsis;
    }
    public void setSinopsis(String sinopsis){
        this.sinopsis = sinopsis;
    }

    public String getAlbum(){
        return album;
    }
    public void setAlbum(String album){
        this.album = album;
    }

    public String getRilis(){
        return rilis;
    }
    public void setRilis(String rilis){
        this.rilis = rilis;
    }

    public String getLagu(){
        return lagu;
    }
    public void setLagu(String lagu){
        this.lagu = lagu;
    }
}
