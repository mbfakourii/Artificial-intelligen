package ir.mbfakouri.artificialintelligence.Main_Page;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import androidx.core.content.res.ResourcesCompat;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import ir.mbfakouri.artificialintelligence.R;

/**
 * Created by mf on 30/09/2017.
 * this class can crate piece
 */

public class Cls_Piece {
    private Activity activity_Global;

    private float textSizeLarge;
    private float textSizeSmall;
    private float textSizeWide;

    private int int_id_error_global;
    private int int_id_Loading_global;


    Cls_Piece(Activity activity) {
        activity_Global = activity;
        textSizeLarge = 60 / activity_Global.getResources().getDisplayMetrics().density;
        textSizeSmall = 40 / activity_Global.getResources().getDisplayMetrics().density;
        textSizeWide = 40 / activity_Global.getResources().getDisplayMetrics().density;
    }


    /*
     * int_id_error for Error Download Image
     * int_id_Loading for Loading Download Image Can Get Gif
     */
    Cls_Piece(Activity activity, int int_id_error, int int_id_Loading) {
        activity_Global = activity;

        textSizeLarge = 60 / activity_Global.getResources().getDisplayMetrics().density;
//        //------------>get with and height
//        DisplayMetrics displayMetrics = new DisplayMetrics();
//        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//        int width = displayMetrics.widthPixels;

        textSizeSmall = 2;
        textSizeWide = 40 / activity_Global.getResources().getDisplayMetrics().density;

        int_id_error_global = int_id_error;
        int_id_Loading_global = int_id_Loading;
    }


    //---------->Listeners

    public interface inf_listener_Style_One {
        View.OnClickListener viw_left_listener();

        View.OnClickListener viw_center_listener();

        View.OnClickListener viw_right_listener();
    }

    public interface inf_listener_Style_Two {
        View.OnClickListener viw_left_listener();

        View.OnClickListener viw_right_listener();
    }

    public interface inf_listener_Style_three {
        View.OnClickListener viw_Large_listener();

        View.OnClickListener viw_Top_listener();

        View.OnClickListener viw_Down_listener();
    }


    //---------->Styles

    //---------->Styles one
    public View CreateNewPieceStyleOne(inf_listener_Style_One listener, String str_title_left, int left_resource_id, String str_title_center, int center_resource_id, String str_title_right, int right_resource_id) {

        RelativeLayout lnl_Piece_Category = new RelativeLayout(activity_Global);

        if (left_resource_id != 0) {
            View viw_view_left = getPiece_small(listener.viw_left_listener(), str_title_left, left_resource_id, "", false);
            viw_view_left.setY(percent(2, 2));
            viw_view_left.setX(percent(1, 2));
            lnl_Piece_Category.addView(viw_view_left, percent(1, 30.6), percent(2, 19.5));
        }

        if (center_resource_id != 0) {
            View viw_view_center = getPiece_small(listener.viw_center_listener(), str_title_center, center_resource_id, "", false);
            viw_view_center.setY(percent(2, 2));
            viw_view_center.setX(percent(1, 34.6));
            lnl_Piece_Category.addView(viw_view_center, percent(1, 30.6), percent(2, 19.5));
        }

        if (right_resource_id != 0) {
            View viw_view_right = getPiece_small(listener.viw_right_listener(), str_title_right, right_resource_id, "", false);
            viw_view_right.setY(percent(2, 2));
            viw_view_right.setX(percent(1, 67.2));
            lnl_Piece_Category.addView(viw_view_right, percent(1, 30.6), percent(2, 19.5));
        }

        return lnl_Piece_Category;

    }

    public View CreateNewPieceStyleOne(inf_listener_Style_One listener, String str_title_left, String left_resource_url, String str_title_center, String center_resource_url, String str_title_right, String right_resource_url) {

        RelativeLayout lnl_Piece_Category = new RelativeLayout(activity_Global);

        View viw_view_left = getPiece_small(listener.viw_left_listener(), str_title_left, 0, left_resource_url, false);
        View viw_view_center = getPiece_small(listener.viw_center_listener(), str_title_center, 0, center_resource_url, false);
        View viw_view_right = getPiece_small(listener.viw_right_listener(), str_title_right, 0, right_resource_url, false);


        viw_view_left.setY(percent(2, 2));
        viw_view_center.setY(percent(2, 2));
        viw_view_right.setY(percent(2, 2));


        viw_view_left.setX(percent(1, 2));
        viw_view_center.setX(percent(1, 34.6));
        viw_view_right.setX(percent(1, 67.2));

        lnl_Piece_Category.addView(viw_view_left, percent(1, 30.6), percent(2, 19.5));
        lnl_Piece_Category.addView(viw_view_center, percent(1, 30.6), percent(2, 19.5));
        lnl_Piece_Category.addView(viw_view_right, percent(1, 30.6), percent(2, 19.5));


        return lnl_Piece_Category;

    }


    //---------->Styles Two
    public View CreateNewPieceStyleTwo(inf_listener_Style_Two listener, String str_title_left, int left_resource_id, String str_title_right, int right_resource_id, boolean bolLeftWide, boolean bol_Image_wide_large) {

        RelativeLayout lnl_Piece_Category = new RelativeLayout(activity_Global);
        View viw_view_left;
        View viw_view_right;

        if (bolLeftWide) {
            viw_view_left = getPiece_wide(listener.viw_left_listener(), str_title_left, left_resource_id, "", bol_Image_wide_large);
            viw_view_right = getPiece_small(listener.viw_right_listener(), str_title_right, right_resource_id, "", false);


            viw_view_left.setY(percent(2, 2));
            viw_view_right.setY(percent(2, 2));


            viw_view_left.setX(percent(1, 2));
            viw_view_right.setX(percent(1, 67.4));

            lnl_Piece_Category.addView(viw_view_left, percent(1, 63.4), percent(2, 19.5));
            lnl_Piece_Category.addView(viw_view_right, percent(1, 30.6), percent(2, 19.5));
        } else {
            viw_view_left = getPiece_small(listener.viw_left_listener(), str_title_left, left_resource_id, "", false);
            viw_view_right = getPiece_wide(listener.viw_right_listener(), str_title_right, right_resource_id, "", bol_Image_wide_large);


            viw_view_left.setY(percent(2, 2));
            viw_view_right.setY(percent(2, 2));


            viw_view_left.setX(percent(1, 2));
            viw_view_right.setX(percent(1, 34.6));

            lnl_Piece_Category.addView(viw_view_left, percent(1, 30.6), percent(2, 19.5));
            lnl_Piece_Category.addView(viw_view_right, percent(1, 63.4), percent(2, 19.5));
        }

        return lnl_Piece_Category;

    }

    public View CreateNewPieceStyleTwo(inf_listener_Style_Two listener, String str_title_left, String left_resource_url, String str_title_right, String right_resource_url, boolean bolLeftWide, boolean bol_Image_wide_large) {

        RelativeLayout lnl_Piece_Category = new RelativeLayout(activity_Global);
        View viw_view_left;
        View viw_view_right;

        if (bolLeftWide) {
            viw_view_left = getPiece_wide(listener.viw_left_listener(), str_title_left, 0, left_resource_url, bol_Image_wide_large);
            viw_view_right = getPiece_small(listener.viw_right_listener(), str_title_right, 0, right_resource_url, false);


            viw_view_left.setY(percent(2, 2));
            viw_view_right.setY(percent(2, 2));


            viw_view_left.setX(percent(1, 2));
            viw_view_right.setX(percent(1, 67.4));

            lnl_Piece_Category.addView(viw_view_left, percent(1, 63.4), percent(2, 19.5));
            lnl_Piece_Category.addView(viw_view_right, percent(1, 30.6), percent(2, 19.5));
        } else {
            viw_view_left = getPiece_small(listener.viw_left_listener(), str_title_left, 0, left_resource_url, false);
            viw_view_right = getPiece_wide(listener.viw_right_listener(), str_title_right, 0, right_resource_url, bol_Image_wide_large);


            viw_view_left.setY(percent(2, 2));
            viw_view_right.setY(percent(2, 2));


            viw_view_left.setX(percent(1, 2));
            viw_view_right.setX(percent(1, 34.6));

            lnl_Piece_Category.addView(viw_view_left, percent(1, 30.6), percent(2, 19.5));
            lnl_Piece_Category.addView(viw_view_right, percent(1, 63.4), percent(2, 19.5));
        }

        return lnl_Piece_Category;

    }

    //---------->Styles Three
    public View CreateNewPieceStyleThree(inf_listener_Style_three listener, String str_title_Large, int Large_resource_id, String str_title_top, int top_resource_id, String str_title_down, int down_resource_id) {

        RelativeLayout lnl_Piece_Category = new RelativeLayout(activity_Global);
        View viw_view_large;
        View viw_view_top;
        View viw_view_down;

        viw_view_large = getPiece_Large(listener.viw_Large_listener(), str_title_Large, Large_resource_id, "");
        viw_view_top = getPiece_small(listener.viw_Top_listener(), str_title_top, top_resource_id, "", true);
        viw_view_down = getPiece_small(listener.viw_Down_listener(), str_title_down, down_resource_id, "", false);


        viw_view_large.setY(percent(2, 2));
        viw_view_top.setY(percent(2, 2));
        viw_view_down.setY(percent(2, 23.5));


        viw_view_large.setX(percent(1, 2));
        viw_view_top.setX(percent(1, 67.4));
        viw_view_down.setX(percent(1, 67.4));

        lnl_Piece_Category.addView(viw_view_large, percent(1, 63.4), percent(2, 41));
        lnl_Piece_Category.addView(viw_view_top, percent(1, 30.6), percent(2, 19.5));
        lnl_Piece_Category.addView(viw_view_down, percent(1, 30.6), percent(2, 19.5));

        return lnl_Piece_Category;

    }

    public View CreateNewPieceStyleThree(inf_listener_Style_three listener, String str_title_Large, String Large_resource_url, String str_title_top, String top_resource_url, String str_title_down, String down_resource_url) {

        RelativeLayout lnl_Piece_Category = new RelativeLayout(activity_Global);
        View viw_view_large;
        View viw_view_top;
        View viw_view_down;

        viw_view_large = getPiece_Large(listener.viw_Large_listener(), str_title_Large, 0, Large_resource_url);
        viw_view_top = getPiece_small(listener.viw_Top_listener(), str_title_top, 0, top_resource_url, true);
        viw_view_down = getPiece_small(listener.viw_Down_listener(), str_title_down, 0, down_resource_url, false);

        viw_view_large.setY(percent(2, 2));
        viw_view_top.setY(percent(2, 2));
        viw_view_down.setY(percent(2, 23.5));


        viw_view_large.setX(percent(1, 2));
        viw_view_top.setX(percent(1, 67.4));
        viw_view_down.setX(percent(1, 67.4));

        lnl_Piece_Category.addView(viw_view_large, percent(1, 63.4), percent(2, 41));
        lnl_Piece_Category.addView(viw_view_top, percent(1, 30.6), percent(2, 19.5));
        lnl_Piece_Category.addView(viw_view_down, percent(1, 30.6), percent(2, 19.5));


        return lnl_Piece_Category;

    }


    //---------->Pieces

    private View getPiece_small(View.OnClickListener onClickListener, String str_title, int res_id, String str_url, boolean bol_Top_view) {
        //---------> initialize and enable other fathers
        FrameLayout frl_Layout_Add_Piece = new FrameLayout(activity_Global);


        //--------> Add Image
        ImageView img_image_piece = new ImageView(activity_Global);

        if (res_id == 0) {
            setImageOnUrl(img_image_piece, str_url, int_id_error_global, int_id_Loading_global, true);
        } else {
            Glide.with(activity_Global)
                    .load(res_id)
                    .dontAnimate()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .fitCenter()
                    .into(img_image_piece);
        }

        img_image_piece.setScaleType(ImageView.ScaleType.CENTER_INSIDE);


        //--------> Add Image Shadow
        ImageView img_image_shadow = new ImageView(activity_Global);
        img_image_shadow.setBackgroundResource(R.drawable.shadow);
        img_image_shadow.setScaleType(ImageView.ScaleType.CENTER);


        //---------> add Text
        TextView txt_Title = new TextView(activity_Global);
        txt_Title.setText(str_title);
        txt_Title.setTextColor(Color.WHITE);
        txt_Title.setGravity(Gravity.CENTER);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            txt_Title.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        }
        txt_Title.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSizeSmall);

        //---------> add Click style ripple
        ImageView img_Click = new ImageView(activity_Global);
        Drawable drw_add_ripple = ResourcesCompat.getDrawable(activity_Global.getResources(), R.drawable.ripple, null);
        img_Click.setImageDrawable(drw_add_ripple);
        img_Click.setOnClickListener(onClickListener);


        //------------>Manage Top Piece
        if (bol_Top_view) {
            frl_Layout_Add_Piece.addView(img_image_piece, percent(1, 30.6), percent(2, 20));
            img_image_shadow.setY(percent(2, 13));
            txt_Title.setY(percent(2, 15));
        } else {
            frl_Layout_Add_Piece.addView(img_image_piece, percent(1, 30.6), percent(2, 17));
            img_image_shadow.setY(percent(2, 11));
            txt_Title.setY(percent(2, 13));
        }

        //---------> add objects
        frl_Layout_Add_Piece.addView(img_image_shadow, percent(1, 30.6), percent(2, 8));
        frl_Layout_Add_Piece.addView(txt_Title, percent(1, 30.6), percent(2, 3.7));
        frl_Layout_Add_Piece.addView(img_Click, percent(1, 30.6), percent(2, 19.5));
        return frl_Layout_Add_Piece;
    }

    private View getPiece_wide(View.OnClickListener onClickListener, String str_title, int res_id, String str_url, boolean bol_Image_wide_large) {
        //---------> initialize and enable other fathers
        FrameLayout frl_Layout_Add_Piece = new FrameLayout(activity_Global);


        //--------> Add Image
        ImageView img_image_piece = new ImageView(activity_Global);
        if (res_id == 0) {
            setImageOnUrl(img_image_piece, str_url, int_id_error_global, int_id_Loading_global, false);
        } else {
            Glide.with(activity_Global)
                    .load(res_id)
                    .dontAnimate()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .fitCenter()
                    .into(img_image_piece);
        }
        img_image_piece.setScaleType(ImageView.ScaleType.FIT_XY);


        //--------> Add Image Shadow
        ImageView img_image_shadow = new ImageView(activity_Global);
        img_image_shadow.setBackgroundResource(R.drawable.shadow);
        img_image_shadow.setScaleType(ImageView.ScaleType.CENTER);
        img_image_shadow.setY(percent(2, 11));


        //---------> add Text
        TextView txt_Title = new TextView(activity_Global);
        txt_Title.setText(str_title);
        txt_Title.setTextColor(Color.WHITE);
        txt_Title.setY(percent(2, 13));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            txt_Title.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        }
        txt_Title.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSizeWide);


        //---------> add Click style ripple
        ImageView img_Click = new ImageView(activity_Global);
        Drawable drw_add_ripple = ResourcesCompat.getDrawable(activity_Global.getResources(), R.drawable.ripple, null);
        img_Click.setImageDrawable(drw_add_ripple);
        img_Click.setOnClickListener(onClickListener);


        //---------> add objects
        if (bol_Image_wide_large) {
            frl_Layout_Add_Piece.addView(img_image_piece, percent(1, 63.4), percent(2, 19.5));
        } else {
            img_image_piece.setX(percent(1, 15.85));
            frl_Layout_Add_Piece.addView(img_image_piece, percent(1, 31.7), percent(2, 18.5));
        }


        frl_Layout_Add_Piece.addView(img_image_shadow, percent(1, 63.4), percent(2, 8));
        frl_Layout_Add_Piece.addView(txt_Title, percent(1, 63.4), percent(2, 3.7));
        frl_Layout_Add_Piece.addView(img_Click, percent(1, 63.4), percent(2, 19.5));
        return frl_Layout_Add_Piece;
    }

    private View getPiece_Large(View.OnClickListener onClickListener, String str_title, int res_id, String str_url) {
        //---------> initialize and enable other fathers
        FrameLayout frl_Layout_Add_Piece = new FrameLayout(activity_Global);


        //--------> Add Image
        ImageView img_image_piece = new ImageView(activity_Global);
        if (res_id == 0) {
            setImageOnUrl(img_image_piece, str_url, int_id_error_global, int_id_Loading_global, true);
        } else {
            Glide.with(activity_Global)
                    .load(res_id)
                    .dontAnimate()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .fitCenter()
                    .into(img_image_piece);
        }
        img_image_piece.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

        //--------> Add Image Shadow
        ImageView img_image_shadow = new ImageView(activity_Global);
        img_image_shadow.setBackgroundResource(R.drawable.shadow);
        img_image_shadow.setScaleType(ImageView.ScaleType.CENTER);
        img_image_shadow.setY(percent(2, 26));


        //---------> add Text
        TextView txt_Title = new TextView(activity_Global);
        txt_Title.setText(str_title);
        txt_Title.setTextColor(Color.WHITE);
        txt_Title.setY(percent(2, 30));
        txt_Title.setGravity(Gravity.CENTER);
        txt_Title.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSizeLarge);

        //---------> add Click style ripple
        ImageView img_Click = new ImageView(activity_Global);
        Drawable drw_add_ripple = ResourcesCompat.getDrawable(activity_Global.getResources(), R.drawable.ripple, null);
        img_Click.setImageDrawable(drw_add_ripple);
        img_Click.setOnClickListener(onClickListener);


        //---------> add objects
        frl_Layout_Add_Piece.addView(img_image_piece, percent(1, 63.4), percent(2, 38.8));
        frl_Layout_Add_Piece.addView(img_image_shadow, percent(1, 63.4), percent(2, 18));
        frl_Layout_Add_Piece.addView(txt_Title, percent(1, 63.4), percent(2, 9.4));
        frl_Layout_Add_Piece.addView(img_Click, percent(1, 63.4), percent(2, 41));
        return frl_Layout_Add_Piece;
    }


    //---------> Load Image In ImageView with Url if Load Have Error Show Error And Can Add Loading Image Or Gif Image
    private void setImageOnUrl(final ImageView imageView, String str_Url, final int int_id_Error, int int_id_Loading, boolean bol_thumbnail) {

        //----------------> Separate thumbnails
        DrawableRequestBuilder<Integer> requestManager;
        if (bol_thumbnail) {
            requestManager = Glide.with(activity_Global)
                    .load(int_id_Loading)
                    .diskCacheStrategy(DiskCacheStrategy.ALL);
        } else {
            requestManager = Glide.with(activity_Global)
                    .load(0)
                    .diskCacheStrategy(DiskCacheStrategy.ALL);
        }

        Glide.with(activity_Global)
                .load(str_Url)
                .thumbnail(requestManager)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        Glide.with(activity_Global).load(int_id_Error).into(imageView);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        return false;
                    }
                })
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .fitCenter()
                .into(imageView);
    }

    //---------> int_Number_Target if equal 1 equal width if 2 equal height
    private int percent(int int_Number_Target, double dbl_Percent) {
        //------------>get with and height
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity_Global.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;


        if (int_Number_Target == 1) {
            int_Number_Target = width;
        } else if (int_Number_Target == 2) {
            int_Number_Target = height;
        } else {
            return 0;
        }

        return (int) ((double) (int_Number_Target / 100.0f) * dbl_Percent);
    }

}
