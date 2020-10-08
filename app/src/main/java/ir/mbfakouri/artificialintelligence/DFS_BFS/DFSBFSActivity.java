package ir.mbfakouri.artificialintelligence.DFS_BFS;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import ir.mbfakouri.artificialintelligence.R;

public class DFSBFSActivity extends AppCompatActivity {
    String str_extra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dfs);

        //get PutExtra
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                str_extra = "DFS";
            } else {
                str_extra = extras.getString("STRING_I_NEED");
            }
        } else {
            str_extra = (String) savedInstanceState.getSerializable("STRING_I_NEED");
        }


    }


    public void onClick_Images(View view) {
        Intent intent = new Intent(DFSBFSActivity.this, SelectActivity.class);
        intent.putExtra("TAG", view.getTag().toString());
        intent.putExtra("TYPE", str_extra);
        DFSBFSActivity.this.startActivity(intent);
    }
}
