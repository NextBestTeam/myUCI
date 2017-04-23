package nextbest.myuci;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

public class CreatePostActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText postText, postTitleText;
    private AppCompatButton createPostButton;

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
        createPostButton = (AppCompatButton) findViewById(R.id.createPostButtonID);

        String post = postText.getText().toString().trim();
        String title = postTitleText.getText().toString().trim();

        if(post.equals("")||title.equals("")){
            createPostButton.setEnabled(false);
            createPostButton.setTextColor(Color.parseColor("#80ffffff"));
        }else{
            createPostButton.setEnabled(true);
            createPostButton.setTextColor(Color.parseColor("#ffffff"));
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);

        createPostButton = (AppCompatButton) findViewById(R.id.createPostButtonID);

        postText = (EditText) findViewById(R.id.postID);
        postTitleText = (EditText) findViewById(R.id.postTitleID);
        //add listeners for email and password to activate login button
        postText.addTextChangedListener(loginTextWatcher);
        postTitleText.addTextChangedListener(loginTextWatcher);
        checkFieldsForEmptyValues();



        createPostButton.setOnClickListener((View.OnClickListener) this);


    }

    @Override
    public void onClick(View v) {
        if(v == createPostButton){

        }

    }
}
