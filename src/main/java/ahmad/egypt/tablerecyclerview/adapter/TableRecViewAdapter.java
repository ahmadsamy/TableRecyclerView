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
        return new TableRowViewHolder(rowView,getMaxColumnsTextLength(),viewType==HEADER);
    }

    @Override
    public void onBindViewHolder(@NonNull final TableRowViewHolder holder, int position) {
        holder.populateRow(rowItems.get(position));
        holder.setCellClickCallBack(cellClickCallBack!=null?cellClickCallBack:new CellClickCallBack(){
            @Override
            public void onCellClick(final String content) {
               showCopyDialog(content,holder.itemView.getContext());
            }

            @Override
            public void onCellLongClick(String content) {
                showCopyDialog(String.format("SELECT * FROM '%s'",content),holder.itemView.getContext());
            }
        });
    }

    public static void showCopyDialog(final String content,final Context c){
        AlertDialog.Builder b=new AlertDialog.Builder(c);
        b.setMessage(content);
        b.setPositiveButton(R.string.ok,null);
        b.setNeutralButton(R.string.copy, new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface p1, int p2)
            {
                ClipboardManager clipboard = (ClipboardManager) c.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("cell", content);
                clipboard.setPrimaryClip(clip);
                p1.dismiss();
            }


        });
        b.create().show();
    }

    @Override
    public int getItemViewType(int position) {
        return position==0&&firstRowHeader?HEADER:ROW;
    }

    @Override
    public int getItemCount() {
        return rowItems!=null?rowItems.size():0;
    }

    private int getMaxColumnCount(){
        int c=0;
        for(int i=0;i<getItemCount();i++){
            int x=rowItems.get(i).getCellCount();
            c=x>c?x:c;
        }
        return c;
    }

    private int[] getMaxColumnsTextLength(){
        int[] lengths=new int[getMaxColumnCount()];
        for (int i=0;i<getItemCount();i++){
            for(int j=0;j<rowItems.get(i).getCellCount();j++){
                lengths[j]=Math.max(rowItems.get(i).getCell(j).length(),lengths[j]);//
            }
        }
        return lengths;
    }

    public void setCellClickCallBack(CellClickCallBack cellClickCallBack) {
        this.cellClickCallBack = cellClickCallBack;
    }
}
