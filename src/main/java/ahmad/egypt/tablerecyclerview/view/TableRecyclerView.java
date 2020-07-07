package ahmad.egypt.tablerecyclerview.view;

import android.content.Context;

import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import ahmad.egypt.tablerecyclerview.R;
import ahmad.egypt.tablerecyclerview.adapter.TableRecViewAdapter;

import static androidx.recyclerview.widget.DividerItemDecoration.VERTICAL;

public class TableRecyclerView extends HorizontalScrollView {
    TableRecViewAdapter adapter;
    RecyclerView recyclerView;
    public TableRecyclerView(Context context) {
        super(context);
        init();
    }

    public TableRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TableRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setAdapter(TableRecViewAdapter adapter){
        this.adapter=adapter;
        recyclerView.setAdapter(this.adapter);
    }
    private void init(){
        inflate(getContext(),R.layout.recyclerview,this);
        recyclerView=findViewById(R.id.recyclerview);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),VERTICAL));
    }


}
