package id.ac.unsyiah.android.ble_scanner.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import id.ac.unsyiah.android.ble_scanner.BTLEDevice;
import id.ac.unsyiah.android.ble_scanner.R;

/** ArrayAdapter adalah sebuah kelas yang digunakan pada data yang berupa Array. */
public class ListBTLEDevicesAdapter extends ArrayAdapter<BTLEDevice> {

    private Activity activity;
    private int layoutResourceID;
    //ArrayList yang digunakan untuk menampung data BTLEDevice.
    private ArrayList<BTLEDevice> devices; // untuk mendapatkan ArrayList devices.

    public ListBTLEDevicesAdapter(Activity activity, int resource, ArrayList<BTLEDevice> objects) {
        super(activity.getApplicationContext(), resource, objects);

        this.activity = activity;
        layoutResourceID = resource;
        devices = objects;
    }


    // Override method abstract.
    /**
     * Method ini menciptakan View yang menampilkan data untuk item / baris yang ditentukan (berdasarkan posisi) di AdapterView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) activity.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layoutResourceID, parent, false);
        }


        BTLEDevice device = devices.get(position); // di dalam metode getView () terdapat parameter position sebagai indeks array dan mengambil item di indeks itu.
        String name = device.getName(); //method yang ada di kelas BTLEDevice
        String address = device.getAddress(); //method yang ada di kelas BTLEDevice
        int rssi = device.getRSSI(); //method yang ada di kelas BTLEDevice

        // pengecekan untuk menginisialisasi TextView menggunakan method setText() sesuai data yang didapatkan.
        TextView tv_name = convertView.findViewById(R.id.tv_dvc_name);
        if (name != null && name.length() > 0) {
            tv_name.setText(device.getName());
        }
        else {
            tv_name.setText("No Name");
        }

        TextView tv_rssi = convertView.findViewById(R.id.tv_rssi);
        tv_rssi.setText(Integer.toString(rssi));

        TextView tv_macaddr = convertView.findViewById(R.id.tv_mac_addr);
        if (address != null && address.length() > 0) {
            tv_macaddr.setText(device.getAddress());
        }
        else {
            tv_macaddr.setText("No Address");
        }

        return convertView;
    }
}
