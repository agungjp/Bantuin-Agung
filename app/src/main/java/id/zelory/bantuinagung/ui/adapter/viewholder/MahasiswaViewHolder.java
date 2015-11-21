package id.zelory.bantuinagung.ui.adapter.viewholder;

import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import id.zelory.bantuinagung.R;
import id.zelory.bantuinagung.data.model.Mahasiswa;
import id.zelory.benih.adapter.viewholder.BenihItemViewHolder;

import static id.zelory.benih.adapter.BenihRecyclerAdapter.OnItemClickListener;
import static id.zelory.benih.adapter.BenihRecyclerAdapter.OnLongItemClickListener;

/**
 * Ini view holdernya untuk satu item mahasiswa
 */
public class MahasiswaViewHolder extends BenihItemViewHolder<Mahasiswa> {

    //viewnya apa saja
    @Bind(R.id.nama) TextView nama;
    @Bind(R.id.nim) TextView nim;
    @Bind(R.id.alamat) TextView alamat;

    /**
     * Contructor untuk buat ini view holder
     *
     * @param itemView              viewnya satu item
     * @param itemClickListener     click listener
     * @param longItemClickListener long click listener
     */
    public MahasiswaViewHolder(View itemView, OnItemClickListener itemClickListener, OnLongItemClickListener longItemClickListener) {
        super(itemView, itemClickListener, longItemClickListener);
    }

    /**
     * tampilin data untuk setiap mahasiswa
     *
     * @param mahasiswa data yg ingin ditampilin
     */
    @Override
    public void bind(Mahasiswa mahasiswa) {
        //set viewnya sesuai data masing-masing
        nama.setText(mahasiswa.getNama());
        nim.setText(mahasiswa.getNim());
        alamat.setText(mahasiswa.getAlamat());
    }
}
