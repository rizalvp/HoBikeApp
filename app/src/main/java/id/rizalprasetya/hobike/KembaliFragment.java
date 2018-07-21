package id.rizalprasetya.hobike;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

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
public class KembaliFragment extends Fragment {

    private View rootView;
    private Button btnReturn, btnCancel;
    Spinner spinnerHotel;

    public KembaliFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView =  inflater.inflate(R.layout.fragment_kembali, container, false);

        /*String[] values = {"hotel star", "hotel muria", "sepeda 3"};
        Spinner spinner = (Spinner)rootView.findViewById(R.id.spinner_idSepeda);
        //spinner.setOnItemSelectedListener(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(),
                android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);*/

        spinnerHotel = (Spinner)rootView.findViewById(R.id.spinner_hotelKembali);

        initSpinnerHotel("");

        btnReturn = (Button)rootView.findViewById(R.id.btn_return);
        btnCancel = (Button)rootView.findViewById(R.id.btn_returnCancel);

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), TransKembaliActivity.class);
                startActivity(intent);
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

}
