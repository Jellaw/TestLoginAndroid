package mvvm.livedata.example.view;

import android.arch.lifecycle.ViewModelProviders;


import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;



import android.widget.EditText;

import mvvm.livedata.example.R;
import mvvm.livedata.example.databinding.ActivityLoginBinding;
import mvvm.livedata.example.model.User;
import mvvm.livedata.example.viewmodel.UserViewModel;
import mvvm.livedata.example.viewmodel.factory.UserViewModelFactory;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener
{

    SharedPreferences sharedPreferences;

    SharedPreferences.Editor editor;



     EditText email;
     EditText pass;
     Button btnSave;

    private void initPreferences() {

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityLoginBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_login);



        UserViewModel userViewModel = ViewModelProviders.of(this, new UserViewModelFactory(this, new User())).get(UserViewModel.class);


        binding.setUserViewModel(userViewModel);

        setup();

    }



    private void setup() {
        initPreferences();
        email = findViewById(R.id.editEmail);
        pass = findViewById(R.id.editPassword);
        btnSave = findViewById(R.id.btnSave);
        String savedData = sharedPreferences.getString("DATA", "");
        email.setText(savedData);
        String savedData2 = sharedPreferences.getString("editPassword","");
        pass.setText(savedData2);

        btnSave.setOnClickListener(this);


    }



    @Override
    public void onClick(View view) {
        if (view == btnSave) {

            String data = email.getText().toString();

            editor.putString("DATA", data);
            editor.commit();
        }
    }
}
