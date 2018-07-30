package id.rizalprasetya.hobike;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class TransSewaActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Toolbar toolbar;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trans_sewa);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_panah);
        actionBar.setDisplayHomeAsUpEnabled(true);

        //todo deklarasi
        Button btnDashboard = (Button)findViewById(R.id.btn_dashboard);
        TextView txtUsername = (TextView)findViewById(R.id.txt_namaDetailSewa);
        TextView txtHotel = (TextView)findViewById(R.id.txt_hotelDetailSewa);
        TextView txtSepeda = (TextView)findViewById(R.id.txt_SepedaDetailSewa);
        TextView txtWaktu = (TextView)findViewById(R.id.txt_waktuSewa);

        //todo ambil shared pref
        SharedPreferences preferences = this.getSharedPreferences("SEWA", MODE_PRIVATE);
        /*txtUsername.setText(preferences.getString("NAME", ""));
        txtHotel.setText(preferences.getString("HOTEL_AWAL", ""));
        txtSepeda.setText(preferences.getString("SEPEDA", ""));*/
        txtWaktu.setText(preferences.getString("WAKTU_SEWA", ""));

        //todo putExtra dari sewa fragment
        txtUsername.setText(getIntent().getStringExtra("dataNama"));
        txtHotel.setText(getIntent().getStringExtra("dataHotel"));
        txtSepeda.setText(getIntent().getStringExtra("dataSepeda"));


        btnDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home) {
            onBackPressed();
            return true;
        }
        return false;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
