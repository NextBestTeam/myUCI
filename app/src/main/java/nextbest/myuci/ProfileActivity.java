package nextbest.myuci;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mFirebaseAuth;
    private DatabaseReference mRef;

    String name, major, phone, level;
    //    private EditText nameText, majorText, phoneText, levelText;
    private EditText editTextName, editTextMajor, editTextPhone, editTextLevel;
    private AppCompatButton appCompatButtonUpdate;
    private TextView nameText, majorText, phoneText, levelText;
    private EditText nameEditTxt, propTxt, descTxt;

    private Button saveBtn;
    private Dialog d;

    private FloatingActionButton editProfileButton, postButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        editProfileButton = (FloatingActionButton) findViewById(R.id.editProfileID);

        postButton = (FloatingActionButton) findViewById(R.id.postButton);

        editProfileButton.setOnClickListener(this);
        postButton.setOnClickListener(this);

        mFirebaseAuth = FirebaseAuth.getInstance();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseUser user = mFirebaseAuth.getCurrentUser();
        DatabaseReference myRef = database.getReference().child("users").child(user.getUid());

        mFirebaseAuth = FirebaseAuth.getInstance();
        nameText = (TextView) findViewById(R.id.nameUser);
        majorText = (TextView) findViewById(R.id.majorLevel);
        phoneText = (TextView) findViewById(R.id.phoneNumber);


        ValueEventListener postListener = new ValueEventListener()

        {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                User user = dataSnapshot.getValue(User.class);
                if (user != null) {
                    nameText.setText(user.getName());
                    majorText.setText(user.getMajor() + "/" + user.getLevel());
                    phoneText.setText(user.getDescription());

                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        };

        myRef.addValueEventListener(postListener);


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
            startActivity(new Intent(this, EditProfileActivity.class));
        }

        if (id == R.id.action_logout) {
            mFirebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }


//    private void displayEditProfileDialog() {
//        d = new Dialog(this);
//        d.setTitle("Edit Profile");
//        d.setContentView(R.layout.activity_edit_profile);
//
//        d.show();
//        int width = (int) (getResources().getDisplayMetrics().widthPixels * .90);
//        int height = (int) (getResources().getDisplayMetrics().heightPixels * .70);
//
//        d.getWindow().setLayout(width, height);
//
//        appCompatButtonUpdate = (AppCompatButton) d.findViewById(R.id.updateProfile);
//
//
//        editTextName = (EditText) d.findViewById(R.id.nameUser);
//        editTextLevel = (EditText) d.findViewById(R.id.level);
//        editTextMajor = (EditText) d.findViewById(R.id.major);
//        editTextPhone = (EditText) d.findViewById(R.id.phone);
//
//
//        name = editTextName.getText().toString();
//        major = editTextMajor.getText().toString();
//        phone = editTextPhone.getText().toString();
//        level = editTextLevel.getText().toString();
//        appCompatButtonUpdate.setOnClickListener(this);
//    }



    private void displayInputDialog(){
        d = new Dialog(this);
        d.setTitle("Save Posts");
        d.setContentView(R.layout.input_dialog);

        d.show();
        int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
        int height = (int)(getResources().getDisplayMetrics().heightPixels*0.90);
        d.getWindow().setLayout(width,height);

        nameEditTxt = (EditText) d.findViewById(R.id.nameEditText);
        propTxt = (EditText) d.findViewById(R.id.propellantEditText);
        descTxt = (EditText) d.findViewById(R.id.descEditText);
        saveBtn = (Button) d.findViewById(R.id.saveBtn);

        saveBtn.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        if (v == editProfileButton) {
            startActivity(new Intent(this, PostActivity.class ));
        }
        if (v == postButton) {
                displayInputDialog();

        }

        if(v == saveBtn){
            //GET DATA
            String name = nameEditTxt.getText().toString();
            String propellant = propTxt.getText().toString();
            String desc = descTxt.getText().toString();
            //SET DATA
            Postmodel p = new Postmodel(name, propellant,desc);

            //saving data to firebase
            mFirebaseAuth  = FirebaseAuth.getInstance();

            FirebaseUser user =  mFirebaseAuth.getCurrentUser();
            mRef  =  FirebaseDatabase.getInstance().getReference("posts");
            String id = mRef.push().getKey();


            mRef.child(id).setValue(p);
            d.dismiss();
            Toast.makeText(getApplicationContext(), " Information Successfully updated" , Toast.LENGTH_SHORT).show();

        }
    }
}