package id.rizalprasetya.hobike;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import id.rizalprasetya.hobike.model.Bike;
import id.rizalprasetya.hobike.model.BikeResult;
import id.rizalprasetya.hobike.model.Hotel;
import id.rizalprasetya.hobike.model.HotelResult;
import id.rizalprasetya.hobike.network.ApiClient;
import id.rizalprasetya.hobike.network.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class SewaFragment extends Fragment  {
    private View rootView;
    private Button btnSewa, btnCancel;
    Spinner spinnerSepeda, spinnerHotel;
    //String selectedBike = "";

    public SewaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        rootView = inflater.inflate(R.layout.fragment_sewa, container, false);


        //String[] values = {"sepeda 1", "sepeda 2", "sepeda 3"};
        //spinner.setOnItemSelectedListener(this);

       /* ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(),
                android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);*/

       //todo deklarasi
       spinnerSepeda = (Spinner)rootView.findViewById(R.id.spinner_idSepeda);
       spinnerHotel = (Spinner)rootView.findViewById(R.id.spinner_hotelSewa);

       //todo panggil method yg dibawah
       initSpinnerSepeda("");
       initSpinnerHotel("");

       /*spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               *//*selectedBike = adapterView.getItemAtPosition(i).toString();
               initSpinnerSepeda(selectedBike);
               Toast.makeText(getContext(), "Km pilih" + selectedBike, Toast.LENGTH_SHORT).show();*//*
           }

           @Override
           public void onNothingSelected(AdapterView<?> adapterView) {

           }
       });*/


        btnSewa = (Button)rootView.findViewById(R.id.btn_sewaOK);
        btnCancel = (Button)rootView.findViewById(R.id.btn_sewaCancel);

        btnSewa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sewaOK = new Intent (getContext(), TransSewaActivity.class);
                startActivity(sewaOK);
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

    private void initSpinnerHotel(String namaHotel) {
        ApiInterface apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);
        Call<HotelResult> hotels = apiInterface.getHotel(namaHotel);
        hotels.enqueue(new Callback<HotelResult>() {
            @Override
            public void onResponse(Call<HotelResult> call, Response<HotelResult> response) {
                List<Hotel> hotelList = response.body().getHotels();
                List<String> stringList = new ArrayList<String>();
                for (int i = 0; i < hotelList.size(); i++) {
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

    private void initSpinnerSepeda(String merekSepeda){
        ApiInterface apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);
        Call<BikeResult> bikes = apiInterface.getBike(merekSepeda);
        bikes.enqueue(new Callback<BikeResult>() {
            @Override
            public void onResponse(Call<BikeResult> call, Response<BikeResult> response) {
                List<Bike> bikeList = response.body().getBikes();
                List<String> stringList = new ArrayList<String>();
                for (int i = 0; i < bikeList.size(); i++) {
                    stringList.add(bikeList.get(i).getMerekSepeda());
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
