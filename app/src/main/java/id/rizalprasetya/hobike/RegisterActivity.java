package id.rizalprasetya.hobike;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import id.rizalprasetya.hobike.model.Member;
import id.rizalprasetya.hobike.model.MemberResult;
import id.rizalprasetya.hobike.network.ApiClient;
import id.rizalprasetya.hobike.network.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    Button btnRegister;
    EditText edtNama, edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        if (isLogin()) {
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
            finish();
        }

        btnRegister = (Button)findViewById(R.id.btn_Register);
        edtNama = (EditText)findViewById(R.id.edt_namaRegister);
        edtPassword = (EditText)findViewById(R.id.edt_passRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnRegister.setText("Harap bersabar");
                btnRegister.setEnabled(false);

               register(edtNama.getText().toString(), edtPassword.getText().toString());
            }
        });

    }

    public void register (String username, String password) {
        ApiInterface apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);
        Call<MemberResult> registers = apiInterface.registerRequest(username, password);
        registers.enqueue(new Callback<MemberResult>() {
            @Override
            public void onResponse(Call<MemberResult> call, Response<MemberResult> response) {
                btnRegister.setText("Register");
                btnRegister.setEnabled(true);

                if (response.body().getPesan().equals("Member berhasil")) {
                    Member memberList = response.body().getMember();

                    saveSession(memberList.getId(),
                                memberList.getNama());

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

    public void saveSession(Integer id, String name){
        SharedPreferences.Editor editor = getSharedPreferences("SESSION", MODE_PRIVATE).edit();
        editor.putInt("ID", id );
        editor.putString("NAME", name );
        editor.apply();
    }

    public boolean isLogin (){
        SharedPreferences preferences = getSharedPreferences("SESSION",MODE_PRIVATE);
        return !preferences.getString("NAME", "").equals("");
    }
}
