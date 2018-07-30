package id.rizalprasetya.hobike;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import id.rizalprasetya.hobike.model.Hotel;
import id.rizalprasetya.hobike.model.HotelResult;
import id.rizalprasetya.hobike.model.Kembali;
import id.rizalprasetya.hobike.model.Sewa;
import id.rizalprasetya.hobike.model.SewaResult;
import id.rizalprasetya.hobike.network.ApiClient;
import id.rizalprasetya.hobike.network.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class KembaliFragment extends Fragment {

    Spinner spinnerHotel;
    List<String> idHotelList = new ArrayList<>();
    SharedPreferences preferences;

    public KembaliFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_kembali, container, false);

        spinnerHotel = (Spinner)rootView.findViewById(R.id.spinner_hotelKembali);
        initSpinnerHotel("");

        TextView txtUsername = (TextView)rootView.findViewById(R.id.txt_namaKembali);
        TextView txtHotel = (TextView)rootView.findViewById(R.id.txt_hotelSewa);
        TextView txtSepeda = (TextView)rootView.findViewById(R.id.txt_SepedaKembali);
        TextView txtWaktuSewa = (TextView)rootView.findViewById(R.id.txt_waktuSewaKembali);

        //todo ambil shared pref
        preferences = getActivity().getSharedPreferences("SEWA", MODE_PRIVATE);
        txtUsername.setText(preferences.getString("NAME", ""));
        txtHotel.setText(preferences.getString("HOTEL_AWAL", ""));
        txtSepeda.setText(preferences.getString("SEPEDA", ""));
        txtWaktuSewa.setText(preferences.getString("WAKTU_SEWA", ""));

        //todo putExtra dari sewa fragment
        /*txtUsername.setText(getActivity().getIntent().getStringExtra("dataNama"));
        txtHotel.setText(getActivity().getIntent().getStringExtra("dataHotel"));
        txtSepeda.setText(getActivity().getIntent().getStringExtra("dataSepeda"));*/

        Button btnReturn = (Button)rootView.findViewById(R.id.btn_return);
        Button btnCancel = (Button)rootView.findViewById(R.id.btn_returnCancel);

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kembaliSepeda(idHotelList.get(spinnerHotel.getSelectedItemPosition()));

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return rootView;
    }

    private void kembaliSepeda (String hotelKembali) {
        Kembali kembali = new Kembali();
        kembali.setHotelIdKembali(hotelKembali);

        ApiInterface apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);
        Call<SewaResult> kembaliResultCall = apiInterface.kembaliRequest(
                preferences.getInt("ID", 0), kembali);
        kembaliResultCall.enqueue(new Callback<SewaResult>() {
            @Override
            public void onResponse(Call<SewaResult> call, Response<SewaResult> response) {
                if (response.body().getPesan().equals("Sepeda telah dikembalikan")) {
                    Log.d("Response", new Gson().toJson(response));

                    Intent intent = new Intent(getContext(), TransKembaliActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getContext(), "Gagal sewa", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<SewaResult> call, Throwable t) {
                Toast.makeText(getContext(), "Hilih gagal :(", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initSpinnerHotel(String namaHotel) {
        ApiInterface apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);
        Call<HotelResult> hotels = apiInterface.getNamaHotel(namaHotel);
        hotels.enqueue(new Callback<HotelResult>() {
            @Override
            public void onResponse(Call<HotelResult> call, Response<HotelResult> response) {
                List<Hotel> hotelList = response.body().getHotels();
                List<String> stringList = new ArrayList<String>();
                for (int i = 0; i < hotelList.size(); i++) {
                    idHotelList.add(String.valueOf(hotelList.get(i).getId()));
                    stringList.add(hotelList.get(i).getNama());
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                        android.R.layout.simple_spinner_item, stringList);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerHotel.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<HotelResult> call, Throwable t) {
                Toast.makeText(getContext(), "Hilih gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
