package id.ac.unsyiah.android.ble_scanner.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import id.ac.unsyiah.android.ble_scanner.Adapter.ListViewAdapter;
import id.ac.unsyiah.android.ble_scanner.Model.Ble;
import id.ac.unsyiah.android.ble_scanner.R;
import id.ac.unsyiah.android.ble_scanner.Database.BleData;


public class ListViewActivity extends AppCompatActivity {
        private ListView listView;
        private ListViewAdapter listViewAdapter;
        private List<Ble> listData;
        private BleData bleData;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_list);

            listView = findViewById(R.id.list_view);

            bleData  = new BleData(this);
            bleData.open();
            listData = bleData.getAllData();
            bleData.close();

            listViewAdapter = new ListViewAdapter(this,0,listData);
            listView.setAdapter(listViewAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> pa, View v, int p, long id) {
                }
            });
        }
}
