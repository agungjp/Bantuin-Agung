package id.zelory.bantuinagung.controller;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import id.zelory.bantuinagung.data.api.AgungApi;
import id.zelory.bantuinagung.data.api.MahasiswaResponse;
import id.zelory.bantuinagung.data.model.Mahasiswa;
import id.zelory.benih.controller.BenihController;
import id.zelory.benih.util.BenihScheduler;
import timber.log.Timber;

/**
 * Controller yg bertanggung jawab bagaimana mengontroll flow data mahasiswa
 */
public class MahasiswaController extends BenihController<MahasiswaController.Presenter> {

    //list sih mahasiswa
    private List<Mahasiswa> mahasiswaList;

    /**
     * konstruktor ini controller
     *
     * @param presenter siapa yg nanti bertugas menampilkan datanya
     */
    public MahasiswaController(Presenter presenter) {
        super(presenter);
    }

    /**
     * berguna untuk mengambil data mahasiswa dari server lalu di tampilin di presenter
     */
    public void loadMahasiswa() {
        //tampilin loading dulu sebelum load datanya
        presenter.showLoading();

        //dari API nya kita load datanya
        AgungApi.pluck()
                .getApi()
                .getMahasiswa()
                        //ini biar ga ganggu UI thread, kita lakuin pekerjaan ini di IO thread, nanti hasilnya baru dilempar ke UI thread
                .compose(BenihScheduler.pluck().applySchedulers(BenihScheduler.Type.IO))
                        //maping hasilnya kedalam bentuk list mahasiswa
                .map(MahasiswaResponse::getMahasiswa)
                        //listen sampe pekerjaannya selesai
                .subscribe(mahasiswaList -> {
                            //simpen datanya di mahasiswaList
                            this.mahasiswaList = mahasiswaList;
                            //kalo presenternya masih ada kita tampilin datanya
                            if (presenter != null) {
                                //tampilin datanya
                                presenter.showMahasiswa(this.mahasiswaList);
                                //ilangin loadingnya
                                presenter.dismissLoading();
                            }
                        },
                        //kalo terjadi error mau ngapain
                        throwable -> {
                            //log errornya apa
                            Timber.e(throwable.getMessage());
                            if (presenter != null) {
                                //tampilin error ke pengguna
                                presenter.showError(throwable);
                                //ilangin loadingnya
                                presenter.dismissLoading();
                            }
                        });
    }

    /**
     * simpan state si controller
     *
     * @param bundle ini tempat nyimpen state nya
     */
    @Override
    public void saveState(Bundle bundle) {
        //simpan data mahasiswanya
        bundle.putParcelableArrayList("list_mahasiswa", (ArrayList<Mahasiswa>) mahasiswaList);
    }

    /**
     * kalo mau load statenya pake ini
     *
     * @param bundle ini tempat state disimpan
     */
    @Override
    public void loadState(Bundle bundle) {
        //ambil data mahasiswanya
        mahasiswaList = bundle.getParcelableArrayList("list_mahasiswa");

        //kalo berhasil diambil
        if (mahasiswaList != null) {
            //tampilin datanya
            presenter.showMahasiswa(mahasiswaList);
        } else { // kalo gagal ambil dataya
            //load ulang lagi dari server
            loadMahasiswa();
        }
    }

    /**
     * ini presenter nya sih mahasiswa
     */
    public interface Presenter extends BenihController.Presenter {
        //dia bisa nampilin mahasiswa, entah gimana caranya nampilin itu data
        void showMahasiswa(List<Mahasiswa> mahasiswaList);
    }
}
