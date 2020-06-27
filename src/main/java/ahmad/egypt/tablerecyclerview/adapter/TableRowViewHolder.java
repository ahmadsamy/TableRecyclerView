package ahmad.egypt.tablerecyclerview.adapter;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import ahmad.egypt.tablerecyclerview.R;
import ahmad.egypt.tablerecyclerview.model.RowItem;

public class TableRowViewHolder extends RecyclerView.ViewHolder {

    LinearLayout row;
    boolean header;
    CellClickCallBack cellClickCallBack;

    public TableRowViewHolder(View itemView){this(itemView,false);}
    public TableRowViewHolder(@NonNull View itemView,boolean header) {
        super(itemView);
        this.row=itemView.findViewById(R.id.row_container);
        this.header=header;
    }

    public void populateRow(RowItem rowItem){
        row.removeAllViews();
        TextView tv;
        for(String s:rowItem.getCells()){
            tv=getCell(row,s);
            tv.setOnClickListener(getOnClickListener(s));
            row.addView(tv);
        }
        if(header)row.setBackgroundColor(itemView.getResources().getColor(R.color.table_header_bg_color));
    }

    private TextView getCell(LinearLayout v, String s){
        TextView tv=(TextView) LayoutInflater.from(v.getContext()).inflate(R.layout.cell_item,v,false);
        tv.setText(s);
        return tv;
    }

    private View.OnClickListener getOnClickListener(final String text){
        return new View.OnClickListener(){

            @Override
            public void onClick(View p1)
            {
               if(cellClickCallBack!=null){
                   cellClickCallBack.onCellClick(text);
               }
            }


        };
    }

    public void setCellClickCallBack(CellClickCallBack cellClickCallBack) {
        this.cellClickCallBack=cellClickCallBack;
    }
}
