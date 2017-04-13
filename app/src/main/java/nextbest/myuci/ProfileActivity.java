package nextbest.myuci;

import android.app.Dialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.DialogTitle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{
    private FirebaseAuth mFirebaseAuth;
    private DatabaseReference mRef;

//    private EditText nameText, majorText, phoneText, levelText;
    private EditText editTextName, editTextMajor, editTextPhone, editTextLevel;
    private AppCompatButton appCompatButtonUpdate;

    private Dialog d;

    private FloatingActionButton editProfileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        editProfileButton = (FloatingActionButton) findViewById(R.id.editProfileID);
//
//       nameText = (EditText) findViewById(R.id.name);
//        levelText = (EditText) findViewById(R.id.level);
//        majorText = (EditText) findViewById(R.id.major);
//        phoneText = (EditText) findViewById(R.id.phone);

        editProfileButton.setOnClickListener(this);

        mFirebaseAuth = FirebaseAuth.getInstance();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseUser user = mFirebaseAuth.getCurrentUser();
        DatabaseReference myRef = database.getReference().child("users").child(user.getUid());

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.createPost);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_editProfile) {
            startActivity(new Intent(this,EditProfileActivity.class));
        }

        if (id == R.id.action_logout){
            mFirebaseAuth.signOut();
            finish();
            startActivity(new Intent(this,MainActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }


    private void displayEditProfileDialog(){
        d = new Dialog(this);
        d.setTitle("Edit Profile");
        d.setContentView(R.layout.activity_edit_profile);

        d.show();
        int width = (int)(getResources().getDisplayMetrics().widthPixels*.90);
        int height = (int)(getResources().getDisplayMetrics().heightPixels*.70);

        d.getWindow().setLayout(width,height);

        editTextName = (EditText) d.findViewById(R.id.name);
        editTextLevel = (EditText) d.findViewById(R.id.level);
        editTextMajor = (EditText) d.findViewById(R.id.major);
        editTextPhone = (EditText) d.findViewById(R.id.phone);

        appCompatButtonUpdate = (AppCompatButton) d.findViewById(R.id.updateProfile);
        appCompatButtonUpdate.setOnClickListener(this);
    }

//    private void saveInformation(){
//        String name = editTextName.getText().toString();
//        String major = editTextMajor.getText().toString();
//        String phone= editTextPhone.getText().toString();
//        String level = editTextLevel.getText().toString();
//
//        User  mUser =  new User(name, major, level, phone);
//
//
//        FirebaseUser user =  firebaseAuth.getCurrentUser();
//        mRef  =  FirebaseDatabase.getInstance().getReference("users");
//
//        mRef.child(user.getUid()).setValue(mUser);
//        Toast.makeText(this, " Information Successfully updated" , Toast.LENGTH_SHORT).show();
//
//    }

    @Override
    public void onClick(View v) {
        if(v==editProfileButton){
            displayEditProfileDialog();
        }
        if(v==appCompatButtonUpdate){
            String name = editTextName.getText().toString();
            String major = editTextMajor.getText().toString();
            String phone= editTextPhone.getText().toString();
            String level = editTextLevel.getText().toString();

            User  mUser =  new User(name, major, level, phone);


            FirebaseUser user =  mFirebaseAuth.getCurrentUser();
            mRef  =  FirebaseDatabase.getInstance().getReference("users");
            String Uid = mRef.push().getKey();



            mRef.child(user.getUid()).setValue(mUser);
            Toast.makeText(this, " Information Successfully updated" , Toast.LENGTH_SHORT).show();

        }
    }
}