package ir.mbfakouri.artificialintelligence.Main_Page;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Space;

import ir.mbfakouri.artificialintelligence.DFS_BFS.DFSBFSActivity;
import ir.mbfakouri.artificialintelligence.Missionaries_and_cannibals.MissionariesCannibalsActivity;
import ir.mbfakouri.artificialintelligence.R;
import ir.mbfakouri.artificialintelligence.Tower_of_Hanoi.TowerOfHanoiActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getPercent percent = new getPercent(this);

        //----------->initialize Class Cls_Piece and mLayout ScrollView
        Cls_Piece cls_piece = new Cls_Piece(this);
        LinearLayout mLayout = (LinearLayout) findViewById(R.id.myLayout);


        //----------> add menus items

        mLayout.addView(cls_piece.CreateNewPieceStyleThree(new Cls_Piece.inf_listener_Style_three() {
            @Override
            public View.OnClickListener viw_Large_listener() {
                return new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, MissionariesCannibalsActivity.class);
                        MainActivity.this.startActivity(intent);
                    }
                };
            }

            @Override
            public View.OnClickListener viw_Top_listener() {
                return new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, TowerOfHanoiActivity.class);
                        MainActivity.this.startActivity(intent);
                    }
                };
            }

            @Override
            public View.OnClickListener viw_Down_listener() {
                return new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, DFSBFSActivity.class);
                        intent.putExtra("STRING_I_NEED", "DFS");
                        MainActivity.this.startActivity(intent);
                    }
                };
            }
        }, "کشیش و آدمخوار", R.mipmap.m_c, "برج هانوی", R.mipmap.t_h, "DFS", R.mipmap.pic_1));


        mLayout.addView(cls_piece.CreateNewPieceStyleOne(new Cls_Piece.inf_listener_Style_One() {
            @Override
            public View.OnClickListener viw_left_listener() {
                return new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, DFSBFSActivity.class);
                        intent.putExtra("STRING_I_NEED", "BFS");
                        MainActivity.this.startActivity(intent);
                    }
                };
            }

            @Override
            public View.OnClickListener viw_center_listener() {
                return null;
            }

            @Override
            public View.OnClickListener viw_right_listener() {
                return null;
            }
        }, "BFS", R.mipmap.pic_2, "", 0, "", 0));

        //--------------->add Empty Space 2 percent y In Down Page
        Space space = new Space(this);
        mLayout.addView(space, percent.percent(1, 100), percent.percent(2, 2));
    }


}
