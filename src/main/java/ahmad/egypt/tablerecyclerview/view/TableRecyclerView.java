package ahmad.egypt.tablerecyclerview.view;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import ahmad.egypt.tablerecyclerview.R;
import ahmad.egypt.tablerecyclerview.adapter.TableRecViewAdapter;

public class TableRecyclerView extends LinearLayout {
    TableRecViewAdapter adapter;
    RecyclerView recyclerView;
    public TableRecyclerView(Context context) {
        super(context);
    }

    public TableRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TableRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setAdapter(TableRecViewAdapter adapter){this.adapter=adapter;setUpView();}
    private void setUpView(){
        removeAllViews();
        addView(inflate(getContext(),R.layout.recyclerview,null));
        recyclerView=findViewById(R.id.recyclerview);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),VERTICAL));
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
    }

}
