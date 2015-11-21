package id.zelory.bantuinagung.data.model;


import android.os.Parcel;
import android.os.Parcelable;

public class Mahasiswa implements Parcelable {

    private String nama;
    private int nim;
    private String alamat;

    public Mahasiswa() {

    }

    protected Mahasiswa(Parcel in) {
        nama = in.readString();
        nim = in.readInt();
        alamat = in.readString();
    }

    public static final Creator<Mahasiswa> CREATOR = new Creator<Mahasiswa>() {
        @Override
        public Mahasiswa createFromParcel(Parcel in) {
            return new Mahasiswa(in);
        }

        @Override
        public Mahasiswa[] newArray(int size) {
            return new Mahasiswa[size];
        }
    };

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getNim() {
        return nim;
    }

    public void setNim(int nim) {
        this.nim = nim;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nama);
        dest.writeInt(nim);
        dest.writeString(alamat);
    }

    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    @Override
    public boolean equals(Object o) {
        Mahasiswa mahasiswa = (Mahasiswa) o;
        return mahasiswa.nim == this.nim;
    }
}
