package id.ac.unsyiah.android.ble_scanner.Activity;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import id.ac.unsyiah.android.ble_scanner.BTLEDevice;
import id.ac.unsyiah.android.ble_scanner.BroadcastReceiverBTState;
import id.ac.unsyiah.android.ble_scanner.Adapter.ListBTLEDevicesAdapter;
import id.ac.unsyiah.android.ble_scanner.Model.Ble;
import id.ac.unsyiah.android.ble_scanner.R;
import id.ac.unsyiah.android.ble_scanner.ScannerBTLE;
import id.ac.unsyiah.android.ble_scanner.Database.BleData;
import id.ac.unsyiah.android.ble_scanner.Utils;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener, LocationListener {

    //kode request
    public static final int REQUEST_ENABLE_BT = 1;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 1;

    private HashMap<String, BTLEDevice> mBTDevicesHashMap;
    private ArrayList<BTLEDevice> mBTDevicesArrayList;
    private ListBTLEDevicesAdapter adapter;
    private BroadcastReceiverBTState mBTStateUpdateReceiver;
    private ScannerBTLE mBTLeScanner;

    private Button btnScan;
    private EditText roomName;

    private BleData bleData;
    private Ble pojoBle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Untuk mengecek apakah perangkat mendukung Bluetooth Low Energy atau tidak.
        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            Utils.toast(getApplicationContext(), "BLE Not Supported");
            finish();
        }

        // Aplikasi meminta izin untuk menghidupkan lokasi pada perangkat.
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);
            }else{
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);
            }
        }

        mBTStateUpdateReceiver = new BroadcastReceiverBTState(getApplicationContext());
        // parameter scanPeriod menunjukkan berapa lama perangkat akan melakukan scan Bluetooth,
        // parameter signalStrength menunjukkan seberapa jauh RSSI yang di scan.
        mBTLeScanner = new ScannerBTLE(this, 7500, -1000);

        mBTDevicesHashMap = new HashMap<>(); // membuat objek kelas HashMap.
        mBTDevicesArrayList = new ArrayList<>(); // membuat objek kelas ArrayList.

        adapter = new ListBTLEDevicesAdapter(this, R.layout.btle_device_list_item, mBTDevicesArrayList);
        ListView listView = new ListView(this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        btnScan  = findViewById(R.id.btn_scan);
        roomName = findViewById(R.id.room_name);

        ((ScrollView) findViewById(R.id.scroll_view)).addView(listView);
        findViewById(R.id.btn_scan).setOnClickListener(this);
        findViewById(R.id.btn_save_random).setOnClickListener(this);
        findViewById(R.id.btn_save_sequence).setOnClickListener(this);
        findViewById(R.id.btn_show).setOnClickListener(this);
    }

    /** Method Constructor yang dibutuhkan oleh kelas LocationListener untuk meminta izin mengakses lokasi pada perangkat. */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == MY_PERMISSIONS_REQUEST_LOCATION) {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            }
        }
    }


    /** Activity LifeCyle untuk mengelola method-method dari kelas BluetoothAdapter. */

    @Override
    protected void onStart() {
        super.onStart();

        registerReceiver(mBTStateUpdateReceiver, new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED));
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopScan();
    }

    @Override
    protected void onStop() {
        super.onStop();

        unregisterReceiver(mBTStateUpdateReceiver);
        stopScan();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * @param requestCode mengidentifikasi request yang dibutuhkan.
     * @param resultCode mengidentifikasi hasil dari request.
     * @param data mengidentifikasi data yang dihasilkan.
     * Method ini dipanggil ketika ingin memulai Activity lain dari Activity saat ini.
     */

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        // Periksa request yang akan ditanggapi
        if (requestCode == REQUEST_ENABLE_BT) {
            // Pastikan request berhasil
            if (resultCode == RESULT_OK) {
            }
            else if (resultCode == RESULT_CANCELED) {
                Utils.toast(getApplicationContext(), "Mohon Hidupkan Bluetooth!");
            }
        }
    }

    /** Dipanggil ketika item pada ListView diklik. */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {}

    /**
     * Dipanggil ketika tombol Scan diklik.
     * @param v adalah tampilan yang diklik.
     */
    @Override
    public void onClick(View v) {

        // switch case untuk pengecekan aksi dari tombol yang d klik.
        switch (v.getId()) {
            case R.id.btn_save_sequence: // tombol menyimpan data ke tabel titik urut.
                if (roomName.getText().toString().equals("")) {
                    roomName.setError("Required!");
                } else {
                    bleData =new BleData(this);
                    if(mBTDevicesArrayList.size() > 0) {
                        Ble pojoBle = new Ble();
                        pojoBle.setRoomName(roomName.getText().toString());

                        for(BTLEDevice ble : mBTDevicesArrayList){
                            pojoBle.setBle(ble.getAddress(), ble.getRSSI());
                        }

                        bleData.open();
                        bleData.addSequencePointData(pojoBle);
                        Toast.makeText(MainActivity.this, "Data Successfully Added!", Toast.LENGTH_LONG).show();

                        bleData.close();

                        startActivity(new Intent(MainActivity.this, ListViewActivity.class));
                    }else{
                        Toast.makeText(MainActivity.this,"No Data", Toast.LENGTH_LONG).show();
                    }

                }
                break;

            case R.id.btn_save_random: // tombol menyimpan data ke tabel titik acak.
                    if (roomName.getText().toString().equals("")) {
                        roomName.setError("Required!");
                    } else {
                        bleData =new BleData(this);
                        if(mBTDevicesArrayList.size() > 0) {
                            Ble pojoBle = new Ble();
                            pojoBle.setRoomName(roomName.getText().toString());

                            for(BTLEDevice ble : mBTDevicesArrayList){
                                pojoBle.setBle(ble.getAddress(), ble.getRSSI());
                            }
                            bleData.open();
                            bleData.addRandomPointData(pojoBle);
                            Toast.makeText(MainActivity.this, "Data Successfully Added!", Toast.LENGTH_LONG).show();

                            bleData.close();

                            startActivity(new Intent(MainActivity.this, ListViewActivity.class));
                        }else{
                            Toast.makeText(MainActivity.this,"No Data", Toast.LENGTH_LONG).show();
                        }

                    }
                    break;

            case R.id.btn_scan: // tombol scan Bluetooth.
                Utils.toast(getApplicationContext(), "Scan Button Pressed");

                if (!mBTLeScanner.isScanning()) {
                    startScan();
                }
                else {
                    stopScan();
                }
                break;

            case R.id.btn_show: // tombol untuk menampilkan list data Bluetooth.
                startActivity(new Intent(MainActivity.this, ListViewActivity.class));
                break;
        }
    }
    /**
     * Tambah device ke dalam ArrayList dan Hashmap yang dikelola oleh kelas ListAdapter.
     * @param device merupakan parameter dari kelas BluetoothDevice yang akan ditambahkan.
     * @param rssi adalah rssi dari kelas BluetoothDevice.
     */
    public void addDevice(BluetoothDevice device, int rssi) {

        String address = device.getAddress();
        Utils.toast(getApplicationContext(), address);

        if (!mBTDevicesHashMap.containsKey(address)) {
            if (address.equals(pojoBle.ADDRESS_1) || address.equals(pojoBle.ADDRESS_2) || address.equals(pojoBle.ADDRESS_3)
                    || address.equals(pojoBle.ADDRESS_4)|| address.equals(pojoBle.ADDRESS_5)|| address.equals(pojoBle.ADDRESS_6)
                    || address.equals(pojoBle.ADDRESS_7) || address.equals(pojoBle.ADDRESS_8) || address.equals(pojoBle.ADDRESS_9))
            {
                BTLEDevice btleDevice = new BTLEDevice(device);
                btleDevice.setRSSI(rssi);
                mBTDevicesHashMap.put(address, btleDevice);
                mBTDevicesArrayList.add(btleDevice);
            }
        }
        else {
            mBTDevicesHashMap.get(address).setRSSI(rssi);
        }
        Utils.toast(getApplicationContext(), address);

        adapter.notifyDataSetChanged();
    }

    /**
     * Hapus ArrayList dan Hashmap yang dikelola oleh kelas ListAdapter.
     * Memulai ScannerBTLE.
     * Mengubah teks tombol Scan.
     */
    public void startScan(){
        btnScan.setText("Scanning...");

        mBTDevicesArrayList.clear();
        mBTDevicesHashMap.clear();

        adapter.notifyDataSetChanged();

        mBTLeScanner.start();

    }

    /**
     * Menghentikan ScannerBTLE.
     * Mengubah teks tombol Scan.
     */
    public void stopScan() {
        btnScan.setText("Scan");

        mBTLeScanner.stop();
        Toast.makeText(MainActivity.this, mBTDevicesArrayList.size() +" device(s) available", Toast.LENGTH_LONG).show();
    }


    /** Method-method abstract yang dibutuhkan oleh kelas LocationListener. */

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
