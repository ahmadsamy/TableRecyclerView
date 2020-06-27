package ahmad.egypt.tablerecyclerview.model;

import java.util.ArrayList;
import java.util.List;

public class RowItem {
    List<String> cells;

    public RowItem(List<String> cells) {
        this.cells = cells;
    }
    public RowItem(){this(new ArrayList<String>());}

    public List<String> getCells() {
        return cells;
    }

    public String getCell(int idx){return (cells !=null&&idx< cells.size())? cells.get(idx):null;}
    private void addCell(String text,int idx){if(cells !=null) cells.add(idx,text);}
    public void addCell(String text){if(cells !=null) cells.add(text);}
    public void addCellAtStart(String text){addCell(text,0);}
}
