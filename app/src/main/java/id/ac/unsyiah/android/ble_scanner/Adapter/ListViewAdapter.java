package id.ac.unsyiah.android.ble_scanner.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import id.ac.unsyiah.android.ble_scanner.Model.Ble;
import id.ac.unsyiah.android.ble_scanner.R;
import id.ac.unsyiah.android.ble_scanner.Database.BleData;

/** ArrayAdapter adalah sebuah kelas yang digunakan pada data yang berupa Array. */
public class ListViewAdapter extends ArrayAdapter<Ble> {

    private Context context;
    private List<Ble> listData; // untuk mendapatkan ArrayList listData.
    private BleData bleData;

    public ListViewAdapter(Context context, int resource, List<Ble> objects) {
        super(context, resource, objects);

        this.context=context;
        this.listData=objects;
        bleData = new BleData(context);
    }

    // Override method abstract.
    /**
     * Method ini menciptakan View yang menampilkan data untuk item / baris yang ditentukan (berdasarkan posisi) di AdapterView.
     */
    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final Ble pojoBle =listData.get(position); // di dalam metode getView () terdapat parameter position sebagai indeks array dan mengambil item di indeks itu.

        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.item_rssi,null);

        TextView txtPosition    = v.findViewById(R.id.txt_room_name);
        TextView txtSs1         = v.findViewById(R.id.txt_rssi1);
        TextView txtSs2         = v.findViewById(R.id.txt_rssi2);
        TextView txtSs3         = v.findViewById(R.id.txt_rssi3);
        TextView txtSs4         = v.findViewById(R.id.txt_rssi4);
        TextView txtSs5         = v.findViewById(R.id.txt_rssi5);
        TextView txtSs6         = v.findViewById(R.id.txt_rssi6);
        TextView txtSs7         = v.findViewById(R.id.txt_rssi7);
        TextView txtSs8         = v.findViewById(R.id.txt_rssi8);
        TextView txtSs9         = v.findViewById(R.id.txt_rssi9);

//        TextView txtMac1= (TextView) v.findViewById(R.idEnam.txt_mac1);
//        TextView txtMac2= (TextView) v.findViewById(R.idEnam.txt_mac2);
//        TextView txtMac3= (TextView) v.findViewById(R.idEnam.txt_mac3);
        ImageButton btnDelete= (ImageButton) v.findViewById(R.id.btn_delete);
//        ImageButton btnEdit = (ImageButton) v.findViewById(R.idEnam.btn_edit);

        // inisialisasi TextView menggunakan method setText() sesuai data yang didapatkan.
        txtPosition.setText(pojoBle.getId() + ". " + pojoBle.getRoomName());
        txtSs1.setText(pojoBle.getBle1() + "dB");
        txtSs2.setText(pojoBle.getBle2() + "dB");
        txtSs3.setText(pojoBle.getBle3() + "dB");
        txtSs4.setText(pojoBle.getBle4() + "dB");
        txtSs5.setText(pojoBle.getBle5() + "dB");
        txtSs6.setText(pojoBle.getBle6() + "dB");
        txtSs7.setText(pojoBle.getBle7() + "dB");
        txtSs8.setText(pojoBle.getBle8() + "dB");
        txtSs9.setText(pojoBle.getBle9() + "dB");

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // membuat Dialog yang meminta pengguna untuk membuat keputusan atau memasukkan informasi tambahan.
                android.support.v7.app.AlertDialog dialog = new android.support.v7.app.AlertDialog.Builder(context)
                        .setTitle("DELETE") // judul
                        .setMessage("Are You Sure to Delete " // area konten
                                + pojoBle.getRoomName()+"?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) { // tombol aksi
                                bleData.open();
                                bleData.delete(pojoBle.getId());
                                bleData.close();

                                listData.remove(position); // menghapus data di dalam list
                                notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("No",null) // tombol aksi
                        .create();

                dialog.show();
            }
        });
        return v;
    }
}
