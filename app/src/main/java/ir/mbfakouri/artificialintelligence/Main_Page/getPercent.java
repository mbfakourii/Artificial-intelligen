package ir.mbfakouri.artificialintelligence.Main_Page;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedHashMap;

/**
 * Created by mohammadbagher fakouri
 */

public class getPercent {
    private Activity activity;
    private Context Global_Context;
    private LinkedHashMap<Double, Integer> list = new LinkedHashMap<>();


    public getPercent(Context context) {
        Global_Context = context;
    }

    public getPercent(Activity activity) {
        this.activity = activity;
        Global_Context = activity;
    }

    //---------> int_Number_Target if equal 1 equal width if 2 equal height
    public int percent(int model, double percent) {
        boolean negative_number = false;
        if (percent < 0) {
            negative_number = true;
            percent = Math.abs(percent);
        }

        Double dbl_Merge = Double.parseDouble(model + "" + percent);
        if (list.get(dbl_Merge) != null) {
            return list.get(dbl_Merge);
        }

        //------------>get with and height
        //DisplayMetrics displayMetrics = new DisplayMetrics();
        //((Activity)Global_Context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        DisplayMetrics displayMetrics = Global_Context.getResources().getDisplayMetrics();
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        if (model == 1) {
            model = width;
        } else if (model == 2) {
            model = height;
        } else {
            return 0;
        }

        list.put(dbl_Merge, (int) ((double) (model / 100.0f) * percent));
        if (negative_number) {
            return -((int) ((double) (model / 100.0f) * percent));
        } else {
            return (int) ((double) (model / 100.0f) * percent);
        }
    }

    public void setPaddingWithPercent(int id_view, double Left_Percent, double Top_Percent, double Right_Percent, double Bottom_Percent) {
        View view=(View) activity.findViewById(id_view);
        view.setPadding(percent(1, Left_Percent), percent(2, Top_Percent), percent(1, Right_Percent), percent(2, Bottom_Percent));
    }

    public void setMarginWithPercent(int id_view, double Left_Percent, double Top_Percent, double Right_Percent, double Bottom_Percent) {
        View view=(View) activity.findViewById(id_view);
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        params.setMargins(percent(1, Left_Percent), percent(2, Top_Percent), percent(1, Right_Percent), percent(2, Bottom_Percent));
        view.setLayoutParams(params);
    }
}
