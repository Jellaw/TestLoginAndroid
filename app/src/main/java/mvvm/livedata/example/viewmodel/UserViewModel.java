package mvvm.livedata.example.viewmodel;

import android.app.Activity;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import android.view.View;

import android.widget.Toast;

import mvvm.livedata.example.model.User;
import mvvm.livedata.example.view.ProfileActivity;


public class UserViewModel extends ViewModel
{

    public MutableLiveData<String> email = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();
    public MutableLiveData<String> welcome = new MutableLiveData<>();

    private MutableLiveData<Integer> busy;

    private User user;
    private Context context;



    public UserViewModel(Context context, User user)
    {
        this.user = user;
        this.context = context;

        this.welcome.setValue(user.getWelcomeMessage());
    }



    public MutableLiveData<Integer> getBusy() {

        if (busy == null)
        {
            busy = new MutableLiveData<>();
            busy.setValue(View.GONE);
        }

        return busy;
    }



    public void onLoginClick()
    {
        user.setEmail(email.getValue());
        user.setPassword(password.getValue());

        if(!user.isValidEmail())
        {
            Toast.makeText(context, "Email không hợp lệ.", Toast.LENGTH_SHORT).show();
        }

        else if(!user.isValidPassword())
        {
            Toast.makeText(context, "Mật khẩu có ít hơn 6 kí tự.", Toast.LENGTH_SHORT).show();
        }

        else if(user.isValidCredential())
        {
            getBusy().setValue(View.VISIBLE);

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run()
                {
                    getBusy().setValue(View.GONE);

                    Toast.makeText(context, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(context, ProfileActivity.class);
                    intent.putExtra("USER_OBJ", user);
                    context.startActivity(intent);

                    ((Activity) context).finish();
                }
            }, 500);
        }

        else
        {
            Toast.makeText(context, "Sai thông tin đăng nhập", Toast.LENGTH_SHORT).show();
        }

    }

}