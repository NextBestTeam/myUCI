package nextbest.myuci;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private VideoView videoView;

    private AppCompatButton buttonLogin;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignUp;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    private TextWatcher loginTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            checkFieldsForEmptyValues();
        }
    };

    void checkFieldsForEmptyValues(){
        buttonLogin = (AppCompatButton) findViewById(R.id.loginID);

        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(email.equals("")||password.equals("")){
            buttonLogin.setEnabled(false);
            buttonLogin.setTextColor(Color.parseColor("#80ffffff"));
        }else{
            buttonLogin.setEnabled(true);
            buttonLogin.setTextColor(Color.parseColor("#ffffff"));
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getSupportActionBar().hide();
        videoView = (VideoView) findViewById(R.id.bgvideoView);

        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.login_bg_quick_compressed);

        videoView.setVideoURI(uri);
        videoView.start();

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        }
        );


        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null){
            //start profile Activity
            finish();
            startActivity(new Intent(getApplicationContext(),ProfileActivity.class ));
        }

        progressDialog = new ProgressDialog(this);

        buttonLogin = (AppCompatButton) findViewById(R.id.loginID);
        editTextEmail = (EditText) findViewById(R.id.emailID);
        editTextPassword = (EditText) findViewById(R.id.passwordID);
        //add listeners for email and password to activate login button
        editTextEmail.addTextChangedListener(loginTextWatcher);
        editTextPassword.addTextChangedListener(loginTextWatcher);
        checkFieldsForEmptyValues();

        textViewSignUp = (TextView) findViewById(R.id.signupID);

        buttonLogin.setOnClickListener(this);
        textViewSignUp.setOnClickListener(this);

    }

    private void loginUser(){
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
        //email is empty
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            //password is empty
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Logging In...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    progressDialog.dismiss();
                    if(task.isSuccessful()){
                        finish();
                        startActivity(new Intent(getApplicationContext(),ProfileActivity.class ));
                    }else{
                        Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                    }
                }

        });
    }

    @Override
    public void onClick(View v) {

        if(v == buttonLogin){
            loginUser();
        }

        if(v == textViewSignUp){
            startActivity(new Intent(this,SignUpActivity.class));
        }

    }
}
