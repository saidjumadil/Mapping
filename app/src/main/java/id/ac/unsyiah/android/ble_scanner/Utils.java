package id.ac.unsyiah.android.ble_scanner;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.widget.Toast;

import id.ac.unsyiah.android.ble_scanner.Activity.MainActivity;

/**
 * Created by Kelvin on 5/8/16.
 */
public class Utils {

    public static boolean checkBluetooth(BluetoothAdapter bluetoothAdapter) {

        // pastikan Bluetooth tersedia di sebuah parangkat dan hidup. Jika belum hidup,
        // tampilkan sebuah dialog untuk meminta user permission untuk menghidupkan Bluetooth.
        if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled()) {
            return false;
        }
        else {
            return true;
        }
    }

    public static void requestUserBluetooth(Activity activity) {
        Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        activity.startActivityForResult(enableBtIntent, MainActivity.REQUEST_ENABLE_BT);
    }

    public static void toast(Context context, String string) {

        Toast toast = Toast.makeText(context, string, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER | Gravity.BOTTOM, 0, 0);
        toast.show();
    }
}
