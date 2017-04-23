package nextbest.myuci;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<Postmodel> postItems;
    private Context context;

    public MyAdapter(List<Postmodel> postItems, Context context) {
        this.postItems = postItems;
        this.context = context;
    }

    public  MyAdapter(Context context){
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v  = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.model, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Postmodel postItem = postItems.get(position);

        holder.textViewTitle.setText(postItem.getPostTitle());
        holder.textViewDesc.setText(postItem.getPostDescription());
        holder.textViewName.setText(postItem.getUserName());

        holder.mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return postItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewTitle;
        public TextView textViewDesc;
        public TextView textViewName;
        public LinearLayout mLinearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewTitle = (TextView) itemView.findViewById(R.id.titleText);
            textViewDesc = (TextView) itemView.findViewById(R.id.descTxt);
           textViewName = (TextView) itemView.findViewById(R.id.usernameText);
            mLinearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout);

        }
    }

}
