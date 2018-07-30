package id.rizalprasetya.hobike;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

public class TransKembaliActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Toolbar toolbar;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trans_kembali);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_panah);
        actionBar.setDisplayHomeAsUpEnabled(true);

        TextView txtUsername = (TextView)findViewById(R.id.txt_namaDetailKembali);
        TextView txtHotelSewa = (TextView)findViewById(R.id.txt_hotelSewaDetailKembali);
        TextView txtHotelKembali = (TextView)findViewById(R.id.txt_hotelKembaliDetail);
        TextView txtSepeda = (TextView)findViewById(R.id.txt_SepedaDetailKembali);
        TextView txtWaktuSewa = (TextView)findViewById(R.id.txt_waktuSewaDetailKembali);
        TextView txtWaktuKembali = (TextView)findViewById(R.id.txt_waktuKembali);
        TextView txtHarga = (TextView)findViewById(R.id.txt_totalHarga);

        SharedPreferences preferences = this.getSharedPreferences("SEWA", MODE_PRIVATE);
        txtUsername.setText(preferences.getString("NAME", ""));
        txtHotelSewa.setText(preferences.getString("HOTEL_AWAL", ""));
        txtSepeda.setText(preferences.getString("SEPEDA", ""));
        txtWaktuSewa.setText(preferences.getString("WAKTU_SEWA", ""));

        Button btnDashboard = (Button)findViewById(R.id.btn_dashboard);
        btnDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selesaiSewa();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }

    public void selesaiSewa() {
        SharedPreferences.Editor editor = getSharedPreferences("SEWA", MODE_PRIVATE).edit();
        editor.clear();
        editor.apply();
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
