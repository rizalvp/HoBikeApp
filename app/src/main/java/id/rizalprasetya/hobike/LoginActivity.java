package id.rizalprasetya.hobike;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import id.rizalprasetya.hobike.model.Login;
import id.rizalprasetya.hobike.model.LoginResult;
import id.rizalprasetya.hobike.network.ApiClient;
import id.rizalprasetya.hobike.network.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity  {

    Button btnLogin, btnRegister;
    EditText edtNama, edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (isLogin()) {
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
            finish();
        }

        btnLogin = (Button)findViewById(R.id.btn_login);
        btnRegister = (Button)findViewById(R.id.btn_Register);
        edtNama = (EditText)findViewById(R.id.edt_email);
        edtPassword = (EditText)findViewById(R.id.edt_passwordLogin);

        btnRegister.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(i);
                finish();
            }
        });

        btnLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                btnLogin.setText("Harap bersabar");
                btnLogin.setEnabled(false);

                login (edtNama.getText().toString(), edtPassword.getText().toString());
            }

        });

    }

    public void login (String nama, String password) {
        ApiInterface apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);
        Call<LoginResult> logins = apiInterface.loginRequest(nama, password);
        logins.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                btnLogin.setText("Login");
                btnLogin.setEnabled(true);

                if (response.body().getStatus().equals("1")) {
                    List<Login> login = response.body().getData();

                    //Memanggil fungsi simpan session login dengan parameter id, nama dan email
                    saveSession(login.get(0).getId(),
                                login.get(0).getNama());

                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Gagal login", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                btnLogin.setText("Login");
                btnLogin.setEnabled(true);
                Toast.makeText(getApplicationContext(), "Gagal terhubung server", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void saveSession(Integer id, String name){
        SharedPreferences.Editor editor = getSharedPreferences("SESSION", MODE_PRIVATE).edit();
        editor.putInt("ID", id );
        editor.putString("NAME", name );
        editor.apply();
    }


    //Fungsi check session login
    //Return true ketika session login tidak kosong
    public boolean isLogin (){
        SharedPreferences preferences = getSharedPreferences("SESSION",MODE_PRIVATE);
        return !preferences.getString("NAME", "").equals("");
    }
}