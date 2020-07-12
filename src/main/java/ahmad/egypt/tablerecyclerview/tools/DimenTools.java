package ahmad.egypt.tablerecyclerview.tools;

import android.content.Context;
import android.graphics.Paint;

public class DimenTools {

    public static int getDimenValue(Context context,int dimenResId){
        return (int) context.getResources().getDimension(dimenResId);
    }


    public static int getWidthByTextLength(int length,int textSize){
        Paint paint=new Paint();
        paint.setTextSize(textSize);
        StringBuilder sb=new StringBuilder();
        for (int i=0;i<length;i++){sb.append("M");}
        float mW=paint.measureText(sb.toString());
        return Math.round(mW);
    }

}
