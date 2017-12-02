package dhruvipatel.c0719320_cricketplayerrank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import dhruvipatel.c0719320_cricketplayerrank.Model.InsertPlayerActivity;

public class LoginActivity extends AppCompatActivity
{
    public static String TAG = "LoginActivity";
    EditText editEmail, editPassword;
    Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        //set Actionbar properties
        //getSupportActionBar().setHomeButtonEnabled(true);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("IPL 2017");
        getSupportActionBar().setLogo(R.drawable.ipl);

        editEmail = (EditText) findViewById(R.id.editTextEmail);
        editPassword = (EditText) findViewById(R.id.editTextPassword);
        btn_login = (Button) findViewById(R.id.btn_login);



        btn_login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(loginValidate())
                {
                    Log.e(TAG , "------------Pass");
                    startActivity(new Intent(getApplicationContext() , InsertPlayerActivity.class));
                }
                else
                {
                    Log.e(TAG , "------------ Fail");
                }
            }
        });


    }//on Create





    public boolean loginValidate()
    {
        int flag = 0;
        if(!isEmailValid(String.valueOf(editEmail.getText())))
        {
            flag = flag+1;
            Log.e(TAG , "------------ Email not velid");
        }
        if(editPassword.getText().toString().compareTo("abc") != 0)
        {
            flag = flag+1;
            Log.e(TAG , "------------ Password not velid");
        }
        if(flag != 0)
        {
            return false;
        }
        else
        {
            return true;
        }

    }//loginvelidate

    public static boolean isEmailValid(String email)
    {
        return !(email == null || TextUtils.isEmpty(email)) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
