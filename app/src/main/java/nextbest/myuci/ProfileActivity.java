package nextbest.myuci;

import android.app.Dialog;
import android.content.Intent;
<<<<<<< HEAD
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
=======
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.DialogTitle;
>>>>>>> eca1b0871ad4d0b4f6f11a98a44d41cc578be73e
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

<<<<<<< HEAD
=======
import com.google.android.gms.common.api.GoogleApiClient;
>>>>>>> eca1b0871ad4d0b4f6f11a98a44d41cc578be73e
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

<<<<<<< HEAD
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
=======
public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{
    private FirebaseAuth mFirebaseAuth;
    private DatabaseReference mRef;

//    private EditText nameText, majorText, phoneText, levelText;
    private EditText editTextName, editTextMajor, editTextPhone, editTextLevel;
    private AppCompatButton appCompatButtonUpdate;

    private Dialog d;

    private FloatingActionButton editProfileButton;
>>>>>>> eca1b0871ad4d0b4f6f11a98a44d41cc578be73e

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        editProfileButton = (FloatingActionButton) findViewById(R.id.editProfileID);
<<<<<<< HEAD

        postButton = (FloatingActionButton) findViewById(R.id.postButton);

        editProfileButton.setOnClickListener(this);
        postButton.setOnClickListener(this);
=======
//
//       nameText = (EditText) findViewById(R.id.name);
//        levelText = (EditText) findViewById(R.id.level);
//        majorText = (EditText) findViewById(R.id.major);
//        phoneText = (EditText) findViewById(R.id.phone);

        editProfileButton.setOnClickListener(this);
>>>>>>> eca1b0871ad4d0b4f6f11a98a44d41cc578be73e

        mFirebaseAuth = FirebaseAuth.getInstance();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseUser user = mFirebaseAuth.getCurrentUser();
        DatabaseReference myRef = database.getReference().child("users").child(user.getUid());

<<<<<<< HEAD
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


=======
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.createPost);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
>>>>>>> eca1b0871ad4d0b4f6f11a98a44d41cc578be73e
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
<<<<<<< HEAD
            startActivity(new Intent(this, EditProfileActivity.class));
        }

        if (id == R.id.action_logout) {
            mFirebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, MainActivity.class));
=======
            startActivity(new Intent(this,EditProfileActivity.class));
        }

        if (id == R.id.action_logout){
            mFirebaseAuth.signOut();
            finish();
            startActivity(new Intent(this,MainActivity.class));
>>>>>>> eca1b0871ad4d0b4f6f11a98a44d41cc578be73e
        }

        return super.onOptionsItemSelected(item);
    }


<<<<<<< HEAD
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
=======
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
>>>>>>> eca1b0871ad4d0b4f6f11a98a44d41cc578be73e

        }
    }
}