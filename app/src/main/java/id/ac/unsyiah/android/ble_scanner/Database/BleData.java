package id.ac.unsyiah.android.ble_scanner.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import id.ac.unsyiah.android.ble_scanner.Model.Ble;

import static id.ac.unsyiah.android.ble_scanner.Database.BleDBHelper.COL_10_SEQUENCE_POINT;
import static id.ac.unsyiah.android.ble_scanner.Database.BleDBHelper.COL_11_SEQUENCE_POINT;
import static id.ac.unsyiah.android.ble_scanner.Database.BleDBHelper.COL_1_RANDOM_POINT;
import static id.ac.unsyiah.android.ble_scanner.Database.BleDBHelper.COL_1_SEQUENCE_POINT;
import static id.ac.unsyiah.android.ble_scanner.Database.BleDBHelper.COL_2_SEQUENCE_POINT;
import static id.ac.unsyiah.android.ble_scanner.Database.BleDBHelper.COL_3_SEQUENCE_POINT;
import static id.ac.unsyiah.android.ble_scanner.Database.BleDBHelper.COL_4_SEQUENCE_POINT;
import static id.ac.unsyiah.android.ble_scanner.Database.BleDBHelper.COL_5_SEQUENCE_POINT;
import static id.ac.unsyiah.android.ble_scanner.Database.BleDBHelper.COL_6_SEQUENCE_POINT;
import static id.ac.unsyiah.android.ble_scanner.Database.BleDBHelper.COL_7_SEQUENCE_POINT;
import static id.ac.unsyiah.android.ble_scanner.Database.BleDBHelper.COL_8_SEQUENCE_POINT;
import static id.ac.unsyiah.android.ble_scanner.Database.BleDBHelper.COL_9_SEQUENCE_POINT;
import static id.ac.unsyiah.android.ble_scanner.Database.BleDBHelper.RANDOM_POINT_TABLE;
import static id.ac.unsyiah.android.ble_scanner.Database.BleDBHelper.SEQUENCE_POINT_TABLE;

public class BleData {

    private BleDBHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;
    private Context context;

    /** Method Constructor. */
    public BleData(Context context) {
        this.context = context;
        dbHelper= new BleDBHelper(context);
    }

    /** Method yang berfungsi untuk membuka database. */
    public void open(){
            sqLiteDatabase =dbHelper.getWritableDatabase();
        }

    /** Method yang berfungsi untuk menutuo database. */
    public void close(){
            sqLiteDatabase.close();
        }

    /**
     * Method untuk menambah data ke table titik urut.
     * @param pojoBle objek POJO pada kelas BLE.
     */
    public void addSequencePointData(Ble pojoBle){
        ContentValues cv= new ContentValues();
        cv.put(BleDBHelper.COL_2_SEQUENCE_POINT, pojoBle.getRoomName());
        cv.put(BleDBHelper.COL_3_SEQUENCE_POINT, pojoBle.getBle1());
        cv.put(BleDBHelper.COL_4_SEQUENCE_POINT, pojoBle.getBle2());
        cv.put(BleDBHelper.COL_5_SEQUENCE_POINT, pojoBle.getBle3());
        cv.put(BleDBHelper.COL_6_SEQUENCE_POINT, pojoBle.getBle4());
        cv.put(BleDBHelper.COL_7_SEQUENCE_POINT, pojoBle.getBle5());
        cv.put(BleDBHelper.COL_8_SEQUENCE_POINT, pojoBle.getBle6());
        cv.put(BleDBHelper.COL_9_SEQUENCE_POINT, pojoBle.getBle7());
        cv.put(BleDBHelper.COL_10_SEQUENCE_POINT, pojoBle.getBle8());
        cv.put(BleDBHelper.COL_11_SEQUENCE_POINT, pojoBle.getBle9());
        sqLiteDatabase.insert(SEQUENCE_POINT_TABLE,null,cv);
    }

    /**
     * Method untuk menambah data ke table titik acak.
     * @param pojoBle objek POJO pada kelas BLE.
     */
    public void addRandomPointData(Ble pojoBle){
        ContentValues cv= new ContentValues();
        cv.put(BleDBHelper.COL_2_RANDOM_POINT, pojoBle.getRoomName());
        cv.put(BleDBHelper.COL_3_RANDOM_POINT, pojoBle.getBle1());
        cv.put(BleDBHelper.COL_4_RANDOM_POINT, pojoBle.getBle2());
        cv.put(BleDBHelper.COL_5_RANDOM_POINT, pojoBle.getBle3());
        cv.put(BleDBHelper.COL_6_RANDOM_POINT, pojoBle.getBle4());
        cv.put(BleDBHelper.COL_7_RANDOM_POINT, pojoBle.getBle5());
        cv.put(BleDBHelper.COL_8_RANDOM_POINT, pojoBle.getBle6());
        cv.put(BleDBHelper.COL_9_RANDOM_POINT, pojoBle.getBle7());
        cv.put(BleDBHelper.COL_10_RANDOM_POINT, pojoBle.getBle8());
        cv.put(BleDBHelper.COL_11_RANDOM_POINT, pojoBle.getBle9());
        sqLiteDatabase.insert(RANDOM_POINT_TABLE,null,cv);
    }

    /** Method untuk mengambil semua data yang ada pada tabel titik urut. */
    public List<Ble> getAllData(){
        List<Ble> data = new ArrayList<>();
        String query="SELECT * FROM " + SEQUENCE_POINT_TABLE;
        Cursor cursor= sqLiteDatabase.rawQuery(query,null);
            if(cursor.moveToFirst()){
                do {
                    Ble pojoBle = new Ble();
                    pojoBle.setId(cursor.getInt(cursor.getColumnIndex(COL_1_SEQUENCE_POINT)));
                    pojoBle.setRoomName(cursor.getString(cursor.getColumnIndex(COL_2_SEQUENCE_POINT)));
                    pojoBle.setBle1(cursor.getInt(cursor.getColumnIndex(COL_3_SEQUENCE_POINT)));
                    pojoBle.setBle2(cursor.getInt(cursor.getColumnIndex(COL_4_SEQUENCE_POINT)));
                    pojoBle.setBle3(cursor.getInt(cursor.getColumnIndex(COL_5_SEQUENCE_POINT)));
                    pojoBle.setBle4(cursor.getInt(cursor.getColumnIndex(COL_6_SEQUENCE_POINT)));
                    pojoBle.setBle5(cursor.getInt(cursor.getColumnIndex(COL_7_SEQUENCE_POINT)));
                    pojoBle.setBle6(cursor.getInt(cursor.getColumnIndex(COL_8_SEQUENCE_POINT)));
                    pojoBle.setBle7(cursor.getInt(cursor.getColumnIndex(COL_9_SEQUENCE_POINT)));
                    pojoBle.setBle8(cursor.getInt(cursor.getColumnIndex(COL_10_SEQUENCE_POINT)));
                    pojoBle.setBle9(cursor.getInt(cursor.getColumnIndex(COL_11_SEQUENCE_POINT)));

                    data.add(pojoBle);

                } while(cursor.moveToNext());
            }
            return data;
    }

    /**
     * Method untuk menghapus data pada tabel titik urut dan tabel titik acak.
     * @param id merupakan id dari data tertentu.
     */
    public void delete(int id){
        sqLiteDatabase.delete(SEQUENCE_POINT_TABLE, COL_1_SEQUENCE_POINT + "=" + id, null);
        sqLiteDatabase.delete(RANDOM_POINT_TABLE, COL_1_RANDOM_POINT + "=" + id, null);
    }
}
