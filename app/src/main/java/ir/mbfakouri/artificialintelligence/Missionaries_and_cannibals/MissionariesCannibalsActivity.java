package ir.mbfakouri.artificialintelligence.Missionaries_and_cannibals;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import ir.mbfakouri.artificialintelligence.R;
import ir.mbfakouri.artificialintelligence.Main_Page.getPercent;

public class MissionariesCannibalsActivity extends AppCompatActivity {
    private boolean ship_right = true;

    int int_number_center = 0;

    boolean anim_start = false;

    ImageView img_center_1;
    ImageView img_center_11;
    ImageView img_center_2;
    ImageView img_center_22;

    ImageView img_priest_right_1;
    ImageView img_priest_right_2;
    ImageView img_priest_right_3;

    ImageView img_priest_left_1;
    ImageView img_priest_left_2;
    ImageView img_priest_left_3;

    ImageView img_monster_right_1;
    ImageView img_monster_right_2;
    ImageView img_monster_right_3;

    ImageView img_monster_left_1;
    ImageView img_monster_left_2;
    ImageView img_monster_left_3;
    ImageView img_direction;

    CardView btn_direction;
    boolean bol_show_dialog = false;
    AlphaAnimation show_btn;
    AlphaAnimation hidden_btn;
    ConstraintLayout lay_ship;
    TranslateAnimation animMoveLeft;
    TranslateAnimation animMoveRigth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_missionaries_cannibals);

        //initialize
        img_center_1 = (ImageView) findViewById(R.id.img_center_1);
        img_center_11 = (ImageView) findViewById(R.id.img_center_11);
        img_center_2 = (ImageView) findViewById(R.id.img_center_2);
        img_center_22 = (ImageView) findViewById(R.id.img_center_22);


        img_center_11.setEnabled(false);
        img_center_22.setEnabled(false);


        img_priest_right_1 = (ImageView) findViewById(R.id.img_priest_right_1);
        img_priest_right_2 = (ImageView) findViewById(R.id.img_priest_right_2);
        img_priest_right_3 = (ImageView) findViewById(R.id.img_priest_right_3);

        img_priest_left_1 = (ImageView) findViewById(R.id.img_priest_left_1);
        img_priest_left_2 = (ImageView) findViewById(R.id.img_priest_left_2);
        img_priest_left_3 = (ImageView) findViewById(R.id.img_priest_left_3);

        img_monster_right_1 = (ImageView) findViewById(R.id.img_monster_right_1);
        img_monster_right_2 = (ImageView) findViewById(R.id.img_monster_right_2);
        img_monster_right_3 = (ImageView) findViewById(R.id.img_monster_right_3);

        img_monster_left_1 = (ImageView) findViewById(R.id.img_monster_left_1);
        img_monster_left_2 = (ImageView) findViewById(R.id.img_monster_left_2);
        img_monster_left_3 = (ImageView) findViewById(R.id.img_monster_left_3);

        img_direction = (ImageView) findViewById(R.id.img_direction);

        btn_direction = (CardView) findViewById(R.id.btn_direction);

        lay_ship = (ConstraintLayout) findViewById(R.id.lay_ship);

        show_btn = new AlphaAnimation(0.0f, 1.0f);
        show_btn.setDuration(500);

        final getPercent percent = new getPercent(this);

        hidden_btn = new AlphaAnimation(1.0f, 0.0f);
        hidden_btn.setDuration(500);
        //anim move left
        animMoveLeft = new TranslateAnimation(0, percent.percent(1, -18), 0, 0);
        animMoveLeft.setDuration(1000);
        animMoveLeft.setFillAfter(true);
        animMoveLeft.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                anim_start = true;
                agent_function();


                btn_direction.setEnabled(false);
                img_direction.setEnabled(false);

                btn_direction.setVisibility(View.INVISIBLE);
                btn_direction.startAnimation(hidden_btn);


            }

            @Override
            public void onAnimationEnd(Animation animation) {
                anim_start = false;
                ship_right = false;
                agent_function();


                btn_direction.setEnabled(true);
                img_direction.setEnabled(true);


                img_direction.setBackgroundResource(R.mipmap.right);
                btn_direction.setVisibility(View.VISIBLE);
                btn_direction.startAnimation(show_btn);

                img_center_11.setEnabled(true);
                img_center_22.setEnabled(true);
                img_center_1.setEnabled(false);
                img_center_2.setEnabled(false);


            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        /////////////////////////


        //anim move right
        animMoveRigth = new TranslateAnimation(percent.percent(1, -17.9), 0, 0, 0);
        animMoveRigth.setDuration(1000);
        animMoveRigth.setFillAfter(true);
        animMoveRigth.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                anim_start = true;
                agent_function();


                btn_direction.setEnabled(false);
                img_direction.setEnabled(false);

                btn_direction.setVisibility(View.INVISIBLE);
                btn_direction.startAnimation(hidden_btn);


            }

            @Override
            public void onAnimationEnd(Animation animation) {
                anim_start = false;
                ship_right = true;
                agent_function();


                btn_direction.setEnabled(true);
                img_direction.setEnabled(true);


                img_direction.setBackgroundResource(R.mipmap.left);
                btn_direction.setVisibility(View.VISIBLE);
                btn_direction.startAnimation(show_btn);

                img_center_11.setEnabled(false);
                img_center_22.setEnabled(false);
                img_center_1.setEnabled(true);
                img_center_2.setEnabled(true);


            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        /////////////////////////
    }

    public void on_Click_Right_Monster(View view) {
        if (ship_right == false) return;
        if (check_ship(true)) return;


        if (img_center_1.getVisibility() == View.INVISIBLE) {

            img_center_1.setImageResource(R.mipmap.monster);
            img_center_1.setVisibility(View.VISIBLE);
            img_center_1.setTag("Monster");

            img_center_11.setVisibility(View.VISIBLE);
            img_center_11.setTag("Monster");

        } else if (img_center_2.getVisibility() == View.INVISIBLE) {

            img_center_2.setImageResource(R.mipmap.monster);
            img_center_2.setVisibility(View.VISIBLE);
            img_center_2.setTag("Monster");

            img_center_22.setVisibility(View.VISIBLE);
            img_center_22.setTag("Monster");
        } else {
        }

        view.setVisibility(View.INVISIBLE);
    }

    public void on_Click_Right_Priest(View view) {

        if (ship_right == false) return;

        if (check_ship(true)) return;
        if (img_center_1.getVisibility() == View.INVISIBLE) {

            img_center_1.setImageResource(R.mipmap.priest);
            img_center_1.setVisibility(View.VISIBLE);
            img_center_1.setTag("Priest");

            img_center_11.setVisibility(View.VISIBLE);
            img_center_11.setTag("Priest");
        } else if (img_center_2.getVisibility() == View.INVISIBLE) {

            img_center_2.setImageResource(R.mipmap.priest);
            img_center_2.setVisibility(View.VISIBLE);
            img_center_2.setTag("Priest");

            img_center_22.setVisibility(View.VISIBLE);
            img_center_22.setTag("Priest");
        } else {
        }

        view.setVisibility(View.INVISIBLE);
    }

    public void on_Click_Left_Monster(View view) {
        if (ship_right == true) return;
        if (check_ship(true)) return;


        if (img_center_1.getVisibility() == View.INVISIBLE) {

            img_center_1.setImageResource(R.mipmap.monster);
            img_center_1.setVisibility(View.VISIBLE);
            img_center_1.setTag("Monster");

            img_center_11.setVisibility(View.VISIBLE);
            img_center_11.setTag("Monster");

        } else if (img_center_2.getVisibility() == View.INVISIBLE) {

            img_center_2.setImageResource(R.mipmap.monster);
            img_center_2.setVisibility(View.VISIBLE);
            img_center_2.setTag("Monster");

            img_center_22.setVisibility(View.VISIBLE);
            img_center_22.setTag("Monster");
        } else {
        }

        view.setVisibility(View.INVISIBLE);
    }

    public void on_Click_Left_Priest(View view) {
        if (ship_right == true) return;

        if (check_ship(true)) return;


        if (img_center_1.getVisibility() == View.INVISIBLE) {

            img_center_1.setImageResource(R.mipmap.priest);
            img_center_1.setVisibility(View.VISIBLE);
            img_center_1.setTag("Priest");

            img_center_11.setVisibility(View.VISIBLE);
            img_center_11.setTag("Priest");
        } else if (img_center_2.getVisibility() == View.INVISIBLE) {

            img_center_2.setImageResource(R.mipmap.priest);
            img_center_2.setVisibility(View.VISIBLE);
            img_center_2.setTag("Priest");

            img_center_22.setVisibility(View.VISIBLE);
            img_center_22.setTag("Priest");
        } else {
        }

        view.setVisibility(View.INVISIBLE);
    }


    public void on_Click_Center_Img(View view) {
        check_ship(false);
        check_visible_View(view);
        view.setVisibility(View.INVISIBLE);
    }

    public void on_Click_btn_direction(View view) {
        if (int_number_center == 0) {
            show_Toast("قایق خالی است");
            return;
        }
        if (ship_right) {
            lay_ship.startAnimation(animMoveLeft);
        } else {
            lay_ship.startAnimation(animMoveRigth);
        }
    }

    private boolean check_ship(boolean added) {
        if (added) {
            if (int_number_center == 2) {
                show_Toast("قایق پر است");
                return true;
            } else {
                int_number_center++;

                if (int_number_center == 1) {
                    if (ship_right) {
                        img_direction.setBackgroundResource(R.mipmap.left);
                    } else {
                        img_direction.setBackgroundResource(R.mipmap.right);
                    }
                    btn_direction.setVisibility(View.VISIBLE);
                    btn_direction.startAnimation(show_btn);

                }
            }
        } else {
            int_number_center--;

            if (int_number_center == 0) {
                if (ship_right) {
                    img_direction.setBackgroundResource(R.mipmap.left);
                } else {
                    img_direction.setBackgroundResource(R.mipmap.right);
                }

                btn_direction.setVisibility(View.INVISIBLE);
                btn_direction.startAnimation(hidden_btn);
            }
        }

        return false;
    }

    private void check_visible_View(View view) {
        if (view.getTag().equals("Monster")) {
            if (ship_right) {
                if (img_monster_right_1.getVisibility() == View.INVISIBLE) {
                    img_monster_right_1.setVisibility(View.VISIBLE);
                } else if (img_monster_right_2.getVisibility() == View.INVISIBLE) {
                    img_monster_right_2.setVisibility(View.VISIBLE);
                } else if (img_monster_right_3.getVisibility() == View.INVISIBLE) {
                    img_monster_right_3.setVisibility(View.VISIBLE);
                }
            } else {
                agent_function();
                if (img_monster_left_1.getVisibility() == View.INVISIBLE) {
                    img_monster_left_1.setVisibility(View.VISIBLE);
                } else if (img_monster_left_2.getVisibility() == View.INVISIBLE) {
                    img_monster_left_2.setVisibility(View.VISIBLE);
                } else if (img_monster_left_3.getVisibility() == View.INVISIBLE) {
                    img_monster_left_3.setVisibility(View.VISIBLE);
                }
            }
        } else {
            if (ship_right) {
                if (img_priest_right_1.getVisibility() == View.INVISIBLE) {
                    img_priest_right_1.setVisibility(View.VISIBLE);
                } else if (img_priest_right_2.getVisibility() == View.INVISIBLE) {
                    img_priest_right_2.setVisibility(View.VISIBLE);
                } else if (img_priest_right_3.getVisibility() == View.INVISIBLE) {
                    img_priest_right_3.setVisibility(View.VISIBLE);
                }
            } else {
                agent_function();
                if (img_priest_left_1.getVisibility() == View.INVISIBLE) {
                    img_priest_left_1.setVisibility(View.VISIBLE);
                } else if (img_priest_left_2.getVisibility() == View.INVISIBLE) {
                    img_priest_left_2.setVisibility(View.VISIBLE);
                } else if (img_priest_left_3.getVisibility() == View.INVISIBLE) {
                    img_priest_left_3.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    private void show_Toast(String value) {
        Toast.makeText(this, value, Toast.LENGTH_SHORT).show();
    }


    private void show_Dialog_End_Game(boolean bol_Won) {

        if (bol_show_dialog == true) return;

        bol_show_dialog = true;


        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this);
        builder.setTitle("توجه");

        if (bol_Won) {
            builder.setMessage("شما برنده شدید \nایا دوست دارید دوباره بازی کنید ؟");
        } else {
            builder.setMessage("شما باختید \nایا دوست دارید دوباره بازی کنید ؟");
        }


        builder.setPositiveButton("بله", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                bol_show_dialog = false;
                finish();
                Intent intent = new Intent(MissionariesCannibalsActivity.this, MissionariesCannibalsActivity.class);
                startActivity(intent);


            }
        });

        builder.setNegativeButton("خیر", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                bol_show_dialog = false;
                finish();
            }
        });

        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                bol_show_dialog = false;
                finish();
            }
        });

        builder.show();

    }

    public void on_Click_Center_Img_11(View view) {
        check_ship(false);
        check_visible_View(img_center_1);
        img_center_1.setVisibility(View.INVISIBLE);
    }

    public void on_Click_Center_Img_22(View view) {
        check_ship(false);
        check_visible_View(img_center_2);
        img_center_2.setVisibility(View.INVISIBLE);
    }

    private void agent_function() {

        int monster = 0;
        int priest = 0;

        //right check

        if (anim_start == false) {
            if (img_center_1.getVisibility() == View.VISIBLE) {
                if (img_center_1.getTag().equals("Monster")) {
                    monster++;
                } else {
                    priest++;
                }
            }

            if (img_center_2.getVisibility() == View.VISIBLE) {
                if (img_center_2.getTag().equals("Monster")) {
                    monster++;
                } else {
                    priest++;
                }
            }
        }


        if (ship_right) {
            if (img_priest_right_1.getVisibility() == View.VISIBLE) priest++;
            if (img_priest_right_2.getVisibility() == View.VISIBLE) priest++;
            if (img_priest_right_3.getVisibility() == View.VISIBLE) priest++;

            if (img_monster_right_1.getVisibility() == View.VISIBLE) monster++;
            if (img_monster_right_2.getVisibility() == View.VISIBLE) monster++;
            if (img_monster_right_3.getVisibility() == View.VISIBLE) monster++;
        } else {
            if (img_priest_left_1.getVisibility() == View.VISIBLE) priest++;
            if (img_priest_left_2.getVisibility() == View.VISIBLE) priest++;
            if (img_priest_left_3.getVisibility() == View.VISIBLE) priest++;

            if (img_monster_left_1.getVisibility() == View.VISIBLE) monster++;
            if (img_monster_left_2.getVisibility() == View.VISIBLE) monster++;
            if (img_monster_left_3.getVisibility() == View.VISIBLE) monster++;

            if ((monster == 3) && (priest == 3)) {
                show_Dialog_End_Game(true);
            }
        }


        if (monster > priest) {
            if (priest != 0) {
                show_Dialog_End_Game(false);
            }
        }

    }

}