package nextbest.myuci;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class  EditProfileActivity extends AppCompatActivity  implements View.OnClickListener{

    private Button sendButton;
    private DatabaseReference mRef;
    private FirebaseAuth firebaseAuth;

    private Button update;
    private EditText nameText, majorText, phoneText, levelText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        firebaseAuth =  FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        nameText = (EditText) findViewById(R.id.nameUser);
        majorText = (EditText) findViewById(R.id.major);

        phoneText = (EditText) findViewById(R.id.phone);
        levelText= (EditText) findViewById(R.id.level);
        update = (Button) findViewById(R.id.updateProfile);


        update.setOnClickListener(this);



    }

    private void saveInformation(){
        String name = nameText.getText().toString();
        String major = majorText.getText().toString();
        String phone= phoneText.getText().toString();
        String level = levelText.getText().toString();

        User  mUser =  new User(name, major, level, phone);
        FirebaseUser user =  firebaseAuth.getCurrentUser();
        mRef  =  FirebaseDatabase.getInstance().getReference("users");

        mRef.child(user.getUid()).setValue(mUser);
        Toast.makeText(this, " Information Successfully updated" , Toast.LENGTH_SHORT).show();

    }



    @Override
    public void onClick(View v) {
        if(v  ==  update){
            saveInformation();
            finish();
            startActivity(new Intent(this, ProfileActivity.class));
        }
    }
}