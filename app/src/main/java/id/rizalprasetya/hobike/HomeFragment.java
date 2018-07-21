package id.rizalprasetya.hobike;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.vision.barcode.Barcode;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {

    private View rootView;
    private Button btnCekLokasi, btnCekSepeda;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_home, container, false);

        //todo deklarasi
        btnCekLokasi = (Button) rootView.findViewById(R.id.btn_cekHotel);
        Button btnSewa = (Button) rootView.findViewById(R.id.btn_sewa);
        btnCekSepeda = (Button) rootView.findViewById(R.id.btn_cekSepeda);
        Button btnKembali = (Button) rootView.findViewById(R.id.btn_kembali);

        btnSewa.setOnClickListener(this);
        btnKembali.setOnClickListener(this);

        btnCekLokasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), ScanActivity.class);
                startActivity(i);
            }
        });

        return rootView;
    }

    @Override
    public void onClick (View view) {
        Fragment fragment = null;
        switch (view.getId()) {
            case R.id.btn_sewa:
                fragment = new SewaFragment();
                replaceFragment(fragment);
                break;
            case R.id.btn_kembali:
                fragment = new KembaliFragment();
                replaceFragment(fragment);
                break;
        }
    }

    private void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_home, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
