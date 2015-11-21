package id.zelory.bantuinagung.data.api;


import java.util.List;

import id.zelory.bantuinagung.data.model.Mahasiswa;

public class MahasiswaReponse {
    private List<Mahasiswa> mahasiswa;

    public void setMahasiswa(List<Mahasiswa> mahasiswa) {
        this.mahasiswa = mahasiswa;
    }

    public List<Mahasiswa> getMahasiswa() {
        return mahasiswa;
    }
}
