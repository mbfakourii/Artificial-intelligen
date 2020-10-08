package ir.mbfakouri.artificialintelligence.DFS_BFS;

import android.content.Intent;
import android.graphics.BitmapFactory;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import ir.mbfakouri.artificialintelligence.R;

public class SelectActivity extends AppCompatActivity {
    String str_tag;
    String str_type;
    TextView edt_number_search;
    ImageView img_tree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        edt_number_search = (TextView) findViewById(R.id.edt_number_search);
        img_tree = (ImageView) findViewById(R.id.img_tree);

        //get PutExtra
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                str_type = "DFS";
                str_tag = "1";
            } else {
                str_type = extras.getString("TYPE");
                str_tag = extras.getString("TAG");
            }
        } else {
            str_type = (String) savedInstanceState.getSerializable("TYPE");
            str_tag = (String) savedInstanceState.getSerializable("TAG");
        }

        Log.i("TAG", "TAG= " + str_tag);
        Log.i("TAG", "TYPE= " + str_type);


        if(str_tag.equals("1")) {
            img_tree.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.pic_1));
        }else if(str_tag.equals("2")) {
            img_tree.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.pic_2));
        }else if(str_tag.equals("3")) {
            img_tree.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.pic_3));
        }

        findViewById(R.id.btn_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectActivity.this, ShowActivity.class);
                intent.putExtra("TAG", str_tag);
                intent.putExtra("TYPE", str_type);
                if(edt_number_search.getText().length() == 0){
                    intent.putExtra("NUMBER_SEARCH", 4);
                }else {
                    intent.putExtra("NUMBER_SEARCH", Integer.parseInt(edt_number_search.getText().toString()));
                }
                startActivity(intent);
            }
        });
    }
}
