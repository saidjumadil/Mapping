package id.ac.unsyiah.android.ble_scanner.Model;

public class Ble {
    private int id;
    private int[] rssiValue = {-1000,-1000,-1000,-1000,-1000,-1000,-1000,-1000,-1000};
    private String roomName;

    public static final String ADDRESS_1 = "DC:0D:30:47:02:E1";
    public static final String ADDRESS_2 = "DC:0D:30:47:02:D8";
    public static final String ADDRESS_3 = "DC:0D:30:47:02:EC";
    public static final String ADDRESS_4 = "DC:0D:30:47:03:76";
    public static final String ADDRESS_5 = "DC:0D:30:47:02:62";
    public static final String ADDRESS_6 = "DC:0D:30:47:02:C6";
    public static final String ADDRESS_7 = "C1:00:F7:00:01:4C";
    public static final String ADDRESS_8 = "C1:00:F7:00:01:49";
    public static final String ADDRESS_9 = "C1:00:F7:00:00:7C";

    /** Method-method Getter dan Setter yang dibutuhkan untuk modifikasi item-item yang ada pada kelas model BLE. */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBle1() {
        return rssiValue[0];
    }

    public void setBle1(int ble1) {
        this.rssiValue[0] = ble1;
    }

    public int getBle2() {
        return rssiValue[1];
    }

    public void setBle2(int ble2) {
        this.rssiValue[1] = ble2;
    }

    public int getBle3() {
        return rssiValue[2];
    }

    public void setBle3(int ble3) {
        this.rssiValue[2] = ble3;
    }

    public int getBle4() {
        return rssiValue[3];
    }

    public void setBle4(int ble4) {
        this.rssiValue[3] = ble4;
    }

    public int getBle5() {
        return rssiValue[4];
    }

    public void setBle5(int ble5) {
        this.rssiValue[4] = ble5;
    }

    public int getBle6() {
        return rssiValue[5];
    }

    public void setBle6(int ble6) {
        this.rssiValue[5] = ble6;
    }

    public int getBle7() {
        return rssiValue[6];
    }

    public void setBle7(int ble7) {
        this.rssiValue[6] = ble7;
    }

    public int getBle8() {
        return rssiValue[7];
    }

    public void setBle8(int ble8) {
        this.rssiValue[7] = ble8;
    }

    public int getBle9() {
        return rssiValue[8];
    }

    public void setBle9(int ble9) {
        this.rssiValue[8] = ble9;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public void setBle(String address, int rssi){
        switch (address){
            case ADDRESS_1:
                setBle1(rssi);
                break;
            case ADDRESS_2:
                setBle2(rssi);
                break;
            case ADDRESS_3:
                setBle3(rssi);
                break;
            case ADDRESS_4:
                setBle4(rssi);
                break;
            case ADDRESS_5:
                setBle5(rssi);
                break;
            case ADDRESS_6:
                setBle6(rssi);
                break;
            case ADDRESS_7:
                setBle7(rssi);
                break;
            case ADDRESS_8:
                setBle8(rssi);
                break;
            case ADDRESS_9:
                setBle9(rssi);
                break;
        }

    }
}
