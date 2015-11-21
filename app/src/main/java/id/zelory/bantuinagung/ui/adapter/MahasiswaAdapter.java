package id.zelory.bantuinagung.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;

import id.zelory.bantuinagung.R;
import id.zelory.bantuinagung.data.model.Mahasiswa;
import id.zelory.bantuinagung.ui.adapter.viewholder.MahasiswaViewHolder;
import id.zelory.benih.adapter.BenihRecyclerAdapter;

/**
 * Adapter nya sih mahasiswa
 */
public class MahasiswaAdapter extends BenihRecyclerAdapter<Mahasiswa, MahasiswaViewHolder> {

    /**
     * Constructor nya sih mahasiswa adapter
     *
     * @param context activity yg akan menggunakan adapter ini
     */
    public MahasiswaAdapter(Context context) {
        super(context);
    }

    /**
     * Layoutnya untuk satu item mahasiswa
     *
     * @param viewType type view, kalo punya banyak tipe tampilan
     * @return Resource layout untuk masing-masing tipe
     */
    @Override
    protected int getItemView(int viewType) {
        return R.layout.item_mahasiswa;
    }

    /**
     * Holder view, biar kita ga perlu buat view terus menerus karena itu ga bagus
     *
     * @param parent   parent view
     * @param viewType tipe viewnya
     * @return holder viewnya apa
     */
    @Override
    public MahasiswaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MahasiswaViewHolder(getView(parent, viewType), itemClickListener, longItemClickListener);
    }
}
