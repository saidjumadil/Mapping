package id.ac.unsyiah.android.ble_scanner.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class BleDBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "rssi.db";

    // Nama tabel
    public static final String SEQUENCE_POINT_TABLE = "SEQUENCE_POINT_TABLE";
    public static final String RANDOM_POINT_TABLE   = "RANDOM_POINT_TABLE";

    // Nama kolom yang ada pada tabel titik urut
    public static final String COL_1_SEQUENCE_POINT  = "id";
    public static final String COL_2_SEQUENCE_POINT  = "room_name";
    public static final String COL_3_SEQUENCE_POINT  = "ble1";
    public static final String COL_4_SEQUENCE_POINT  = "ble2";
    public static final String COL_5_SEQUENCE_POINT  = "ble3";
    public static final String COL_6_SEQUENCE_POINT  = "ble4";
    public static final String COL_7_SEQUENCE_POINT  = "ble5";
    public static final String COL_8_SEQUENCE_POINT  = "ble6";
    public static final String COL_9_SEQUENCE_POINT  = "ble7";
    public static final String COL_10_SEQUENCE_POINT = "ble8";
    public static final String COL_11_SEQUENCE_POINT = "ble9";

    // Nama kolom yang ada pada tabel titik acak
    public static final String COL_1_RANDOM_POINT  = "id";
    public static final String COL_2_RANDOM_POINT  = "room_name";
    public static final String COL_3_RANDOM_POINT  = "ble1";
    public static final String COL_4_RANDOM_POINT  = "ble2";
    public static final String COL_5_RANDOM_POINT  = "ble3";
    public static final String COL_6_RANDOM_POINT  = "ble4";
    public static final String COL_7_RANDOM_POINT  = "ble5";
    public static final String COL_8_RANDOM_POINT  = "ble6";
    public static final String COL_9_RANDOM_POINT  = "ble7";
    public static final String COL_10_RANDOM_POINT = "ble8";
    public static final String COL_11_RANDOM_POINT = "ble9";

    //Hapus tabel
    public static final String SQL_DROP_SEQUENCE_POINT_TABLE = "DROP TABLE IF EXISTS "+ SEQUENCE_POINT_TABLE;
    public static final String SQL_DROP_RANDOM_POINT_TABLE   = "DROP TABLE IF EXISTS "+ RANDOM_POINT_TABLE;

    public BleDBHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    /** Method-method Constructor yang dibutuhkan oleh kelas SQLiteDatabase.
     *  onCreate untuk membuat tabel pada database.
     *  onUpgrade untuk modifikasi tabel pada database.
     * */
    @Override
    public void onCreate(SQLiteDatabase sqliteDatabase) {
        String SQL_CREATE_SEQUENCE_POINT_TABLE = "CREATE TABLE "+ SEQUENCE_POINT_TABLE +
                " ("+ COL_1_SEQUENCE_POINT +" INTEGER PRIMARY KEY AUTOINCREMENT, "+ COL_2_SEQUENCE_POINT + " VARCHAR, "+ COL_3_SEQUENCE_POINT +
                " INTEGER, "+ COL_4_SEQUENCE_POINT +" INTEGER, "+ COL_5_SEQUENCE_POINT +" INTEGER, "+ COL_6_SEQUENCE_POINT +" INTEGER, "+ COL_7_SEQUENCE_POINT +
                " INTEGER, "+ COL_8_SEQUENCE_POINT +" INTEGER, "+ COL_9_SEQUENCE_POINT +" INTEGER, "+ COL_10_SEQUENCE_POINT +" INTEGER, "+ COL_11_SEQUENCE_POINT +" INTEGER)";

        String SQL_CREATE_RANDOM_POINT_TABLE = "CREATE TABLE "+ RANDOM_POINT_TABLE +
                " ("+ COL_1_RANDOM_POINT +" INTEGER PRIMARY KEY AUTOINCREMENT, "+ COL_2_RANDOM_POINT + " VARCHAR, "+ COL_3_RANDOM_POINT +
                " INTEGER, "+ COL_4_RANDOM_POINT +" INTEGER, "+ COL_5_RANDOM_POINT +" INTEGER, "+ COL_6_RANDOM_POINT +" INTEGER, "+ COL_7_RANDOM_POINT +
                " INTEGER, "+ COL_8_RANDOM_POINT +" INTEGER, "+ COL_9_RANDOM_POINT +" INTEGER, "+ COL_10_RANDOM_POINT +" INTEGER, "+ COL_11_RANDOM_POINT +" INTEGER)";

        sqliteDatabase.execSQL(SQL_CREATE_SEQUENCE_POINT_TABLE);
        sqliteDatabase.execSQL(SQL_CREATE_RANDOM_POINT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqliteDatabase, int oldVersion, int newVersion) {
        sqliteDatabase.execSQL(SQL_DROP_SEQUENCE_POINT_TABLE);
        sqliteDatabase.execSQL(SQL_DROP_RANDOM_POINT_TABLE);
        onCreate(sqliteDatabase);
    }
}
