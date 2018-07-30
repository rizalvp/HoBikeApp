package id.rizalprasetya.hobike;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import id.rizalprasetya.hobike.model.Bike;
import id.rizalprasetya.hobike.model.BikeResult;
import id.rizalprasetya.hobike.model.Hotel;
import id.rizalprasetya.hobike.model.HotelResult;
import id.rizalprasetya.hobike.model.Member;
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
public class SewaFragment extends Fragment  {
    Spinner spinnerSepeda, spinnerHotel;
    TextView txtUsername, txtID;
    Button btnSewa;
    String idMember;
    List<String> idHotelList = new ArrayList<>();
    List<String> idSepedaList = new ArrayList<>();

    public SewaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_sewa, container, false);


       /* ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(),
                android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);*/

       //todo deklarasi
       spinnerSepeda = (Spinner)rootView.findViewById(R.id.spinner_idSepeda);
       spinnerHotel = (Spinner)rootView.findViewById(R.id.spinner_hotelSewa);
       txtUsername = (TextView) rootView.findViewById(R.id.txt_nama);
       txtID = (TextView)rootView.findViewById(R.id.txt_idMemberSewa);

       //todo panggil method yg dibawah
       initSpinnerSepeda("");
       initSpinnerHotel("");

       //todo sharedpref
        SharedPreferences preferences = this.getActivity().getSharedPreferences("SESSION", MODE_PRIVATE);
        txtUsername.setText(preferences.getString("NAME", ""));
        //txtID.setText(preferences.getInt(String.valueOf("ID"), 0));
        //txtID.setText(preferences.getInt("ID", 0));
        txtID.setText(String.valueOf(preferences.getInt("ID", 0)));

       btnSewa = (Button)rootView.findViewById(R.id.btn_sewaOK);
       Button btnCancel = (Button)rootView.findViewById(R.id.btn_sewaCancel);


       btnSewa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sewaSepeda (txtID.getText().toString(),
                            idSepedaList.get(spinnerSepeda.getSelectedItemPosition()),
                            idHotelList.get(spinnerHotel.getSelectedItemPosition()));
            }
        });

       btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sewaCancel = new Intent(getContext(), MainActivity.class);
                startActivity(sewaCancel);
                getActivity().finish();
            }
        });


       return rootView;
    }


    private void sewaSepeda(String nama, final String sepeda, String hotel) {
        ApiInterface apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);
        Call<SewaResult> sewaResultCall = apiInterface.sewaRequest(nama, sepeda, hotel);
        sewaResultCall.enqueue(new Callback<SewaResult>() {
            @Override
            public void onResponse(Call<SewaResult> call, Response<SewaResult> response) {
                //Log.d("Response", new Gson().toJson(response));
                if (response.body().getPesan().equals("Penyewaan Sukses")) {
                    //List<String> listIdHotel = new ArrayList<>();
                    Sewa sewaList = response.body().getSewa();

                    saveID(sewaList.getId(),
                            sewaList.getMemberId(),
                            sewaList.getHotelIdAwal(),
                            sewaList.getBikeId(),
                            sewaList.getCreatedAt(),
                            txtUsername.getText().toString(),
                            spinnerSepeda.getSelectedItem().toString(),
                            spinnerHotel.getSelectedItem().toString());

                    Intent sewaOK = new Intent (getContext(), TransSewaActivity.class);
                    sewaOK.putExtra("dataNama", txtUsername.getText().toString());
                    sewaOK.putExtra("dataSepeda", spinnerSepeda.getSelectedItem().toString());
                    sewaOK.putExtra("dataHotel", spinnerHotel.getSelectedItem().toString());
                    startActivity(sewaOK);
                    //btnSewa.setEnabled(false);
                }
                else {
                    Toast.makeText(getContext(), "Gagal sewa", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call< SewaResult > call, Throwable t) {

            }
        });
    }

    public void saveID(Integer id, String idnama, String idHotelAwal, String idSepeda, String waktuSewa,
                       String nama, String hotelAwal, String sepeda){
        SharedPreferences.Editor editor = getActivity().getSharedPreferences("SEWA", MODE_PRIVATE).edit();
        editor.putInt("ID", id );
        editor.putString("ID_NAME", idnama);
        editor.putString("ID_HOTEL_AWAL", idHotelAwal);
        editor.putString("ID_SEPEDA", idSepeda);
        editor.putString("WAKTU_SEWA", waktuSewa);
        editor.putString("NAME", nama);
        editor.putString("HOTEL_AWAL", hotelAwal);
        editor.putString("SEPEDA", sepeda);
        editor.apply();
    }

    private void initSpinnerHotel(String namaHotel) {
        ApiInterface apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);
        Call<HotelResult> hotels = apiInterface.getNamaHotel(namaHotel);
        hotels.enqueue(new Callback<HotelResult>() {
            @Override
            public void onResponse(Call<HotelResult> call, Response<HotelResult> response) {
                List<Hotel> hotelList = response.body().getHotels();
                List<String> stringList = new ArrayList<>();
                //List<String> idHotelList = new ArrayList<>();
                for (int i = 0; i < hotelList.size(); i++) {
                    idHotelList.add(String.valueOf(hotelList.get(i).getId()));
                    stringList.add(hotelList.get(i).getNama());
                    //idHotelList.add("21");
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

    private void initSpinnerSepeda(String merekSepeda){
        ApiInterface apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);
        Call<BikeResult> bikes = apiInterface.getBike(merekSepeda);
        bikes.enqueue(new Callback<BikeResult>() {
            @Override
            public void onResponse(Call<BikeResult> call, Response<BikeResult> response) {
                List<Bike> bikeList = response.body().getBikes();
                List<String> stringList = new ArrayList<String>();
                for (int i = 0; i < bikeList.size(); i++) {
                    idSepedaList.add(String.valueOf(bikeList.get(i).getId()));
                    stringList.add(bikeList.get(i).getMerekSepeda());
                    //Edited
                    //idSepedaList.add("21");
                }

                //Log.d("Response server", new Gson().toJson(response));

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                        android.R.layout.simple_spinner_item, stringList);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerSepeda.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<BikeResult> call, Throwable t) {
                Toast.makeText(getContext(), "Hilih gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
