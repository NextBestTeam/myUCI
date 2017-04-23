package nextbest.myuci;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PostActivity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth;
    private DatabaseReference mRef;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private List<Postmodel> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        recyclerView  = (RecyclerView) findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listItems  = new ArrayList<>();
        mRef= FirebaseDatabase.getInstance().getReference().child("/posts");

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                String name, title, desc;
                for(DataSnapshot child : snapshot.getChildren()){
                    Postmodel post = child.getValue(Postmodel.class);
                    name = post.getUserName();
                    title =  post.getPostTitle();
                    desc = post.getPostDescription();

                    Postmodel x = new Postmodel(desc, name , title);
                    listItems.add(x);

                }

                adapter =  new MyAdapter(listItems,getApplicationContext());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });




    }
}
