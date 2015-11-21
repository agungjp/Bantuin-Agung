package id.zelory.bantuinagung.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.Snackbar;
import android.view.View;

import java.util.List;

import butterknife.Bind;
import id.zelory.bantuinagung.R;
import id.zelory.bantuinagung.controller.MahasiswaController;
import id.zelory.bantuinagung.data.model.Mahasiswa;
import id.zelory.bantuinagung.ui.adapter.MahasiswaAdapter;
import id.zelory.benih.BenihActivity;
import id.zelory.benih.adapter.BenihRecyclerAdapter;
import id.zelory.benih.view.BenihRecyclerView;

/**
 * Activity yg akan menampilkan list mahasiswa nantinya
 */

public class MainActivity extends BenihActivity implements MahasiswaController.Presenter,
        BenihRecyclerAdapter.OnItemClickListener {

    //Controller yg bertugas untuk mengontrol hubungan data source dengan presenter
    private MahasiswaController mahasiswaController;
    //Biar usernya tau kalo lagi loading, nanti kita pake ini aja
    private ProgressDialog progressDialog;
    //Gimana data sih mahasiswa mau ditampilin itu tanggung jawabnya ini adapter
    private MahasiswaAdapter mahasiswaAdapter;

    //Ini sih recycler view nya yg di xml
    @Bind(R.id.recycler_view)
    BenihRecyclerView recyclerView;

    /**
     * Layout untuk activity ini
     *
     * @return resource layout nya
     */
    @Override
    protected int getActivityView() {
        return R.layout.activity_main;
    }

    /**
     * Lakuin segala macem setelah view nya siap disini
     *
     * @param savedInstanceState state yg udah kesimpan sebelumnya
     */
    @Override
    protected void onViewReady(Bundle savedInstanceState) {
        //buat instance si adapter
        mahasiswaAdapter = new MahasiswaAdapter(this);
        //set kalo item nya di klik mau ngapain
        mahasiswaAdapter.setOnItemClickListener(this);

        //set recycler view nya sebagai list, bisa juga diset sebagai grid
        recyclerView.setUpAsList();
        //set adapternya si recycler view
        recyclerView.setAdapter(mahasiswaAdapter);

        setupController(savedInstanceState);
    }

    /**
     * set up controllernya di method ini
     *
     * @param savedInstanceState state yg udah kesimpan sebelumnya
     */
    private void setupController(Bundle savedInstanceState) {
        //kalo null kita bikinin instance baru
        if (mahasiswaController == null) {
            mahasiswaController = new MahasiswaController(this);
        }

        //kalo ada state sebelumnya ambil aja data sebelumnya, ga usah load ulang dari server
        if (savedInstanceState != null) {
            mahasiswaController.loadState(savedInstanceState);
        } else { //kalo ga ada baru load data dari server
            mahasiswaController.loadMahasiswa();
        }
    }

    /**
     * implementasi in method yg dari presenter, showMahasiswa mau diapain
     *
     * @param mahasiswaList list mahasiswanya
     */
    @Override
    public void showMahasiswa(List<Mahasiswa> mahasiswaList) {
        //bersihin dulu data yg ada di adapter, biar ga duplicate
        mahasiswaAdapter.clear();
        //masukin datanya ke adapter
        mahasiswaAdapter.add(mahasiswaList);
    }

    /**
     * kalo terjadi error mau ngapain
     *
     * @param throwable ini penyebab errornya
     */
    @Override
    public void showError(Throwable throwable) {
        //tampilin pesan errornya apa
        Snackbar snackbar = Snackbar.make(recyclerView, "Terjadi kesalahan: " + throwable.getMessage(), Snackbar.LENGTH_LONG);
        snackbar.getView().setBackgroundResource(R.color.colorAccent);
        snackbar.show();
    }

    /**
     * implementasi in gimana caranya nampilin loading
     */
    @Override
    public void showLoading() {
        //tampilin progress dialog aja
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Silahkan tunggu...");
        progressDialog.show();
    }

    /**
     * ini implementasi cara ilangin loadingnya gimana
     */
    @Override
    public void dismissLoading() {
        progressDialog.dismiss();
    }

    /**
     * ini implementasi kalo item nya di klik mau nagapain
     *
     * @param view     sih item viewnya
     * @param position ini posisi itemnya
     */
    @Override
    public void onItemClick(View view, int position) {
        //ambil dulu datanya si mahasiswa
        Mahasiswa mahasiswa = mahasiswaAdapter.getData().get(position);

        //terus mau diapain datanya, kalo ini cuma ditampilin namanya
        Snackbar snackbar = Snackbar.make(recyclerView, "Item click: " + mahasiswa.getNama(), Snackbar.LENGTH_LONG);
        snackbar.getView().setBackgroundResource(R.color.colorPrimary);
        snackbar.show();
    }

    /**
     * simpan statenya, biar nanti bisa digunain lagi
     *
     * @param outState           tempat nyimpan statenya
     * @param outPersistentState ini kurang tau apaan :v
     */
    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        //simpan state sih controller
        mahasiswaController.saveState(outState);
        super.onSaveInstanceState(outState, outPersistentState);
    }
}
