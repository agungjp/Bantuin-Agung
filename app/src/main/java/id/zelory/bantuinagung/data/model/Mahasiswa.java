package id.zelory.bantuinagung.data.model;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * Model data mahasiswa, apa saja data yg harus disimpen oleh mahasiswa ada disini implements
 * Parcelable biar bisa disimpan statenya, dan mudah di passing antar activity
 */
public class Mahasiswa implements Parcelable {

    //data apa saja yg dipunya mahasiswa
    private String nama;
    private int nim;
    private String alamat;

    /**
     * Default constructor dibutuhkan oleh GSON
     */
    public Mahasiswa() {

    }

    /**
     * Constructor untuk parcelable
     *
     * @param in ini data sebelumya
     */
    protected Mahasiswa(Parcel in) {
        nama = in.readString();
        nim = in.readInt();
        alamat = in.readString();
    }

    //CREATOR dibutuhkan oleh parcelable biar bisa bentuk instance mahasiswa lagi
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

    /**
     * Dua object mahasiswa dikatakan sama kalo dia punya nim yg sama
     *
     * @param o mahasiswa lainnya
     * @return boolean apakah sama
     */
    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    @Override
    public boolean equals(Object o) {
        if (o instanceof Mahasiswa) {
            Mahasiswa mahasiswa = (Mahasiswa) o;
            return mahasiswa.nim == this.nim;
        }

        return false;
    }
}
