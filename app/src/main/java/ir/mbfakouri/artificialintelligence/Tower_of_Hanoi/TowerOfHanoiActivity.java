package ir.mbfakouri.artificialintelligence.Tower_of_Hanoi;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import ir.mbfakouri.artificialintelligence.R;

public class TowerOfHanoiActivity extends AppCompatActivity implements View.OnDragListener, View.OnTouchListener {

    private final String TAG = TowerOfHanoiActivity.class.getSimpleName();
    private ImageView img_ring3;
    private ImageView img_ring2;
    private ImageView img_ring1;
    private LinearLayout lay_right;
    private LinearLayout lay_center;
    private LinearLayout lay_left;

    Drawable normalShape;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tower_of_hanoi);
        normalShape = getResources().getDrawable(R.drawable.shape);
        findViews();
        implementEvents();
    }

    //Find all views and set Tag to all draggable views
    private void findViews() {
        img_ring1 = (ImageView) findViewById(R.id.img_ring1);
        img_ring2 = (ImageView) findViewById(R.id.img_ring2);
        img_ring3 = (ImageView) findViewById(R.id.img_ring3);

        lay_right = (LinearLayout) findViewById(R.id.lay_right);
        lay_center = (LinearLayout) findViewById(R.id.lay_center);
        lay_left = (LinearLayout) findViewById(R.id.lay_left);
    }


    //Implement long click and drag listener
    private void implementEvents() {
        //add or remove any view that you don't want to be dragged
        img_ring3.setOnTouchListener(this);
        img_ring1.setOnTouchListener(this);
        img_ring2.setOnTouchListener(this);

        //add or remove any layout view that you don't want to accept dragged view
        findViewById(R.id.lay_right).setOnDragListener(this);
        findViewById(R.id.lay_center).setOnDragListener(this);
        findViewById(R.id.lay_left).setOnDragListener(this);
    }

    public boolean bol_first = false;
    public View view_Keep;
    public DragEvent event_Keep;
    LinearLayout container22;
    View v22;
    View s;
    View s2;
    ViewGroup owner22;

    Object first_tag_saver;
    Object tag_saver;
    int Position_First;

    boolean first_DRAG_LOCATION = false;
    boolean DRAG_LOCATION_tag_saver = false;

    // This is the method that the system calls when it dispatches a drag event to the
    // listener.
    @Override
    public boolean onDrag(View view, DragEvent event) {

        // Defines a variable to store the action type for the incoming event
        int action = event.getAction();
        // Handles each of the expected events
        switch (action) {
            case DragEvent.ACTION_DRAG_STARTED:
                Log.i("TAG", "ACTION_DRAG_STARTED");

                v22 = (View) event.getLocalState();
                owner22 = (ViewGroup) v22.getParent();
                s = view;


                first_tag_saver = view.getTag();
                first_DRAG_LOCATION = true;

                Log.i("TAG2", "---->" + tag_saver);
                bol_first = false;
                // Determines if this View can accept the dragged data
                if (event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                    // if you want to apply color when drag started to your view you can uncomment below lines
                    // to give any color tint to the View to indicate that it can accept
                    // data.

                    //  view.getBackground().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);//set background color to your view

                    // Invalidate the view to force a redraw in the new tint
                    //  view.invalidate();

                    // returns true to indicate that the View can accept the dragged data.
                    return true;

                }

                // Returns false. During the current drag and drop operation, this View will
                // not receive events again until ACTION_DRAG_ENDED is sent.
                return false;

            case DragEvent.ACTION_DRAG_ENTERED:
                Log.i("TAG", "ACTION_DRAG_ENTERED");
                // Applies a YELLOW or any color tint to the View, when the dragged view entered into drag acceptable view
                // Return true; the return value is ignored.


                bol_first = false;
                view.getBackground().setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_IN);

                // Invalidate the view to force a redraw in the new tint
                view.invalidate();

                return true;
            case DragEvent.ACTION_DRAG_LOCATION:

                if (first_DRAG_LOCATION) {
                    tag_saver = view.getTag();
                    s2 = view;
                    Position_First = get_Number_Children_View(view, v22.getTag());
                    first_DRAG_LOCATION = false;
                }
                bol_first = false;
                Log.i("TAG", "ACTION_DRAG_LOCATION");
                // Ignore the event
                return true;
            case DragEvent.ACTION_DRAG_EXITED:
                Log.i("TAG", "ACTION_DRAG_EXITED");
                // Re-sets the color tint to blue, if you had set the BLUE color or any color in ACTION_DRAG_STARTED. Returns true; the return value is ignored.
                bol_first = true;
                //tag_saver=view.getTag();
                Log.i("TAG2", "---->" + tag_saver);
                // view.getBackground().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);
                //view.setBackgroundColor(view.getContext().getResources().getColor(android.R.color.background_light));
                //If u had not provided any color in ACTION_DRAG_STARTED then clear color filter.
                view.getBackground().clearColorFilter();
                // Invalidate the view to force a redraw in the new tint
                view.invalidate();

                return true;
            case DragEvent.ACTION_DROP:
                if (bol_first == false) {
                    Log.i("TAG", "ACTION_DROP");
                    agent_function(view.getTag());
                } else {

                }
                // Returns true. DragEvent.getResult() will return true.
                return false;
            case DragEvent.ACTION_DRAG_ENDED:
                Log.i("TAG", "ACTION_DRAG_ENDED");
                // Turns off any color tinting
                view.getBackground().clearColorFilter();

                // Invalidates the view to force a redraw
                view.invalidate();

                // Does a getResult(), and displays what happened.
                if (event.getResult()) {
                    Log.i("TAG", "The drop was handled.");
                } else {
                    Log.i("TAG", "The drop didn't work.");

                }
                if (bol_first) {
                    agent_function(tag_saver);
                }
                s.invalidate();
                // returns true; the value is ignored.
                return true;

            // An unknown action type was received.
            default:
                Log.i("TAG", "default");
                Log.e("DragDrop Example", "Unknown action type received by OnDragListener.");
                break;
        }
        return false;
    }


    private void agent_function(Object Position) {
        owner22.removeView(v22);//remove the dragged view
        LinearLayout f;
        LinearLayout first = SetLinearLayoutFromTag(s2.getTag());

        if (Position_First == 0) {

            f = SetLinearLayoutFromTag(Position);

        } else {
            f = SetLinearLayoutFromTag(tag_saver);
        }
        int sssss = Integer.parseInt(getFirstChildrenTag(f).toString());
        int v22_Tag = Integer.parseInt(v22.getTag().toString());
        if (sssss == -1) {
            point_in_Rod(Position, f);
        } else if (sssss > v22_Tag) {
            point_in_Rod(Position, f);
        } else {
            f = SetLinearLayoutFromTag(tag_saver);
            point_in_Rod(Position, f);
        }

        v22.setVisibility(View.VISIBLE);//finally set Visibility to VISIBLE

        if(lay_right.getChildCount() == 3){
            show_you_Win();
        }

    }


    private void point_in_Rod(Object Position, LinearLayout f) {


        if (bol_first) {
            f.addView(v22, Position_First);//Add the dragged view
        } else {
            try {
                if (Integer.parseInt(v22.getTag().toString()) == 1) {

                    if (f.getChildAt(0).getTag().equals("0")) {
                        f.addView(v22, 1);//Add the dragged view
                    }
                    if (f.getChildAt(1).getTag().equals("2")) {
                        f.addView(v22, 0);//Add the dragged view
                    }


                }

                f.addView(v22, Integer.parseInt(v22.getTag().toString()));//Add the dragged view
            } catch (Exception e1) {
                try {
                    f.addView(v22, Integer.parseInt(v22.getTag().toString()) - 1);//Add the dragged view
                } catch (Exception e2) {
                    try {
                        f.addView(v22, Integer.parseInt(v22.getTag().toString()) - 2);//Add the dragged view
                    } catch (Exception e3) {
                    }
                }
            }


        }
    }

    private int get_all_Tags_for_bag_Number_1(View view, Object tag) {
        LinearLayout lay = SetLinearLayoutFromTag(view.getTag());


        int count = lay.getChildCount();
        View v = null;
        for (int i = 0; i < count; i++) {
            v = lay.getChildAt(i);
            if (tag.equals(v.getTag())) continue;

            return Integer.parseInt(v.getTag().toString());
        }

        return -1;
    }


    private Object getFirstChildrenTag(View view) {
        try {
            LinearLayout lay = SetLinearLayoutFromTag(view.getTag());
            return lay.getChildAt(0).getTag();
        } catch (Exception e1) {
            return -1;
        }

    }


    private int get_Number_Children_View(View view, Object tag) {
        LinearLayout lay = SetLinearLayoutFromTag(view.getTag());


        int count = lay.getChildCount();
        View v = null;
        for (int i = 0; i < count; i++) {
            v = lay.getChildAt(i);
            if (v.getTag().equals(tag)) return i;
        }

        return -1;
    }

    private LinearLayout SetLinearLayoutFromTag(Object Position) {
        LinearLayout f = null;
        if (Position.equals("lay1")) {
            f = lay_left;
        } else if (Position.equals("lay2")) {
            f = lay_center;
        } else if (Position.equals("lay3")) {
            f = lay_right;
        }
        return f;
    }

    private void show_you_Win() {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this);
        builder.setTitle("توجه");

        builder.setMessage("شما برنده شدید \nایا دوست دارید دوباره بازی کنید ؟");


        builder.setPositiveButton("بله", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                finish();
                Intent intent = new Intent(TowerOfHanoiActivity.this, TowerOfHanoiActivity.class);
                startActivity(intent);


            }
        });

        builder.setNegativeButton("خیر", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                finish();
            }
        });

        builder.show();
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            // Create a new ClipData.
            // This is done in two steps to provide clarity. The convenience method
            // ClipData.newPlainText() can create a plain text ClipData in one step.

            // Create a new ClipData.Item from the ImageView object's tag
            ClipData.Item item = new ClipData.Item((CharSequence) view.getTag());

            // Create a new ClipData using the tag as a label, the plain text MIME type, and
            // the already-created item. This will create a new ClipDescription object within the
            // ClipData, and set its MIME type entry to "text/plain"
            String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

            ClipData data = new ClipData(view.getTag().toString(), mimeTypes, item);

            // Instantiates the drag shadow builder.
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);

            // Starts the drag
            view.startDrag(data//data to be dragged
                    , shadowBuilder //drag shadow
                    , view//local data about the drag and drop operation
                    , 0//no needed flags
            );

            //Set view visibility to INVISIBLE as we are going to drag the view
            view.setVisibility(View.INVISIBLE);
        }

        return false;
    }
}

