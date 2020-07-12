package ahmad.egypt.tablerecyclerview.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import ahmad.egypt.tablerecyclerview.R;
import ahmad.egypt.tablerecyclerview.model.RowItem;
import ahmad.egypt.tablerecyclerview.tools.DimenTools;


public class TableRowViewHolder extends RecyclerView.ViewHolder {

    private LinearLayout row;
    private boolean header;
    private CellClickCallBack cellClickCallBack;
    private int[] columnTextMaxLength;

    public TableRowViewHolder(View itemView, int[] columnTextMaxLength) {
        this(itemView, columnTextMaxLength, false);
    }

    public TableRowViewHolder(@NonNull View itemView, int[] columnTextMaxLength, boolean header) {
        super(itemView);
        this.row = itemView.findViewById(R.id.row_container);
        this.header = header;
        this.columnTextMaxLength = columnTextMaxLength;
        addColumns();
    }

    private void addColumns() {
        TextView tv;
        for (int i = 0; i < columnTextMaxLength.length; i++) {
            tv = getCell(row);
            tv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
            tv.setWidth(calculateWidth(columnTextMaxLength[i],tv.getTextSize()));
            row.addView(tv);
        }
        if (header)
            row.setBackgroundColor(itemView.getResources().getColor(R.color.table_header_bg_color));
    }

    private int calculateWidth(int maxLength,float textSize){
        int measuredWidth=DimenTools.getWidthByTextLength(maxLength,Math.round(textSize));
        int maxWidth=DimenTools.getDimenValue(itemView.getContext(),R.dimen.max_column_width);
        int minWidth=DimenTools.getDimenValue(itemView.getContext(),R.dimen.min_column_width);
        if(measuredWidth>minWidth)return Math.min(measuredWidth,maxWidth);
        else return minWidth;
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