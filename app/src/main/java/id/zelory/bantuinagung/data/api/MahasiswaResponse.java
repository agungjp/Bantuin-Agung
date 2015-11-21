package id.zelory.bantuinagung.data.api;


import java.util.List;

import id.zelory.bantuinagung.data.model.Mahasiswa;

/**
 * Holder response untuk API getMahasiswa()
 */
public class MahasiswaResponse {
    //punya list mahasiswa
    private List<Mahasiswa> mahasiswa;

    /**
     * Setter mahasiswa
     *
     * @param mahasiswa list mahasiswanya
     */
    public void setMahasiswa(List<Mahasiswa> mahasiswa) {
        this.mahasiswa = mahasiswa;
    }

    /**
     * Getter mahasiswa
     *
     * @return data list mahasiswa
     */
    public List<Mahasiswa> getMahasiswa() {
        return mahasiswa;
    }
}
