package id.rizalprasetya.hobike;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import id.rizalprasetya.hobike.model.MemberResult;
import id.rizalprasetya.hobike.network.ApiClient;
import id.rizalprasetya.hobike.network.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    Button btnRegister;
    EditText edtNama, edtPassword, edtAlamat, edtJK, edtTelepon, edtPekerjaan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnRegister = (Button)findViewById(R.id.btn_Register);

        edtNama = (EditText)findViewById(R.id.edt_namaRegister);
        edtPassword = (EditText)findViewById(R.id.edt_passRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnRegister.setText("Harap bersabar");
                btnRegister.setEnabled(false);

                ApiInterface apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);
                Call<MemberResult> registers = apiInterface.registerRequest(
                        edtNama.getText().toString(),
                        edtPassword.getText().toString());
                registers.enqueue(new Callback<MemberResult>() {
                    @Override
                    public void onResponse(Call<MemberResult> call, Response<MemberResult> response) {
                        btnRegister.setText("Register");
                        btnRegister.setEnabled(true);

                        if (response.body().getPesan().equals("Member berhasil")) {
                            Intent i = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(i);
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Gagal register", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<MemberResult> call, Throwable t) {
                        btnRegister.setText("Register");
                        btnRegister.setEnabled(true);
                        Toast.makeText(getApplicationContext(), "Gagal terhubung server", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}
