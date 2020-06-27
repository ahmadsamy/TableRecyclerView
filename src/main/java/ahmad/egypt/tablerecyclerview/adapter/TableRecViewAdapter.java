package ahmad.egypt.tablerecyclerview.adapter;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

import ahmad.egypt.tablerecyclerview.R;
import ahmad.egypt.tablerecyclerview.model.RowItem;

public class TableRecViewAdapter extends RecyclerView.Adapter<TableRowViewHolder> {
    private static final int HEADER = 0, ROW=1;

    public void setFirstRowHeader(boolean firstRowHeader) {
        this.firstRowHeader = firstRowHeader;
    }

    private boolean firstRowHeader=true;

    List<RowItem> rowItems;

    private CellClickCallBack cellClickCallBack;

    public TableRecViewAdapter(List<RowItem> rowItems){
        this.rowItems=rowItems;
    }

    @NonNull
    @Override
    public TableRowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rowView= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent,false);
        return new TableRowViewHolder(rowView,viewType==HEADER);
    }

    @Override
    public void onBindViewHolder(@NonNull final TableRowViewHolder holder, int position) {
        holder.populateRow(rowItems.get(position));
        holder.setCellClickCallBack(cellClickCallBack!=null?cellClickCallBack:new CellClickCallBack(){
            @Override
            public void onCellClick(final String content) {
                AlertDialog.Builder b=new AlertDialog.Builder(holder.itemView.getContext());
                b.setMessage(content);
                b.setPositiveButton(R.string.ok,null);
                b.setNeutralButton(R.string.copy, new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface p1, int p2)
                    {
                        ClipboardManager clipboard = (ClipboardManager) holder.itemView.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                        ClipData clip = ClipData.newPlainText("cell", content);
                        clipboard.setPrimaryClip(clip);
                        p1.dismiss();
                    }


                });
                b.create().show();
            }

            @Override
            public void onCellClick(boolean head, String content) {

            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return position==0&&firstRowHeader?HEADER:ROW;
    }

    @Override
    public int getItemCount() {
        return rowItems!=null?rowItems.size():0;
    }



    public void setCellClickCallBack(CellClickCallBack cellClickCallBack) {
        this.cellClickCallBack = cellClickCallBack;
    }
}