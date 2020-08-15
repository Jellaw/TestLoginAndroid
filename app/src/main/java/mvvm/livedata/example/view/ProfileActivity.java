package mvvm.livedata.example.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import mvvm.livedata.example.R;
import mvvm.livedata.example.databinding.ActivityProfileBinding;
import mvvm.livedata.example.model.User;
import mvvm.livedata.example.viewmodel.UserViewModel;
import mvvm.livedata.example.viewmodel.factory.UserViewModelFactory;

public class ProfileActivity extends AppCompatActivity
{
    private Button LogoutBnt;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        User user = (User) getIntent().getSerializableExtra("USER_OBJ");

        /**
         * Create instance for data binding auto generated class file
         */
        ActivityProfileBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_profile);

        /**
         * Create instance for ViewModel class
         */

        UserViewModel userViewModel = ViewModelProviders.of(this, new UserViewModelFactory(this, user)).get(UserViewModel.class);

        /**
         * Set ViewModel instance to binding class
         */
        binding.setUserViewModel(userViewModel);

        setupUI();

        LogoutBnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
                finish();
            }
        });
    }

    private void setupUI(){
        LogoutBnt = (Button)findViewById(R.id.button3);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId() == android.R.id.home)
        {
            finish();
            return  true;
        }

        return super.onOptionsItemSelected(item);
    }
}