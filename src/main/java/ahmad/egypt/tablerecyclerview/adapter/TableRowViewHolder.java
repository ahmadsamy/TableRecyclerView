package ahmad.egypt.tablerecyclerview.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import ahmad.egypt.tablerecyclerview.R;
import ahmad.egypt.tablerecyclerview.model.RowItem;


public class TableRowViewHolder extends RecyclerView.ViewHolder {

    private LinearLayout row;
    private boolean header;
    private CellClickCallBack cellClickCallBack;
    private int columnCount;

    public TableRowViewHolder(View itemView, int columnCount) {
        this(itemView, columnCount, false);
    }

    public TableRowViewHolder(@NonNull View itemView, int columnCount, boolean header) {
        super(itemView);
        this.row = (LinearLayout) itemView.findViewById(R.id.row_container);
        this.header = header;
        this.columnCount = columnCount;
        addColumns();
    }

    private void addColumns() {
        TextView tv;
        for (int i = 0; i < columnCount; i++) {
            tv = getCell(row);
            row.addView(tv);
        }
        if (header)
            row.setBackgroundColor(itemView.getResources().getColor(R.color.table_header_bg_color));
    }

    public void populateRow(RowItem rowItem) {
        TextView tv;
        int i = 0;
        for (String s : rowItem.getCells()) {
            tv = getCellAt(i);
            tv.setText(s);
            tv.setOnClickListener(getOnClickListener(s));
            tv.setOnLongClickListener(getOnLongClickListener(s));
            i++;
        }
        if (header)
            row.setBackgroundColor(itemView.getResources().getColor(R.color.table_header_bg_color));
    }

    private TextView getCellAt(int i) {
        return (TextView) this.row.getChildAt(i);
    }

    private TextView getCell(LinearLayout v) {
        TextView tv = (TextView) LayoutInflater.from(v.getContext()).inflate(R.layout.cell_item, v, false);
        tv.setTextColor(Color.WHITE);
        return tv;
    }

    private View.OnClickListener getOnClickListener(final String text) {
        return new View.OnClickListener() {

            @Override
            public void onClick(View p1) {
                if (cellClickCallBack != null) {
                    cellClickCallBack.onCellClick(text);
                }
            }


        };
    }

    private View.OnLongClickListener getOnLongClickListener(final String text) {
        return new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View p1) {
                if (cellClickCallBack != null) {
                    cellClickCallBack.onCellLongClick(text);
                    return true;
                }
                return false;
            }


        };
    }

    public void setCellClickCallBack(CellClickCallBack cellClickCallBack) {
        this.cellClickCallBack = cellClickCallBack;
    }

}