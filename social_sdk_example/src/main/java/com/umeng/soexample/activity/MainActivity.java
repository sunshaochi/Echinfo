package com.umeng.soexample.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.umeng.soexample.R;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onClick(View view) {
        int id = view.getId();
        Intent in = null;
        switch ( id ) {
            case R.id.button_share:
                in = new Intent( this, ShareActivity.class );
                break;
            case R.id.button_oauth:
                in = new Intent( this, OAuthActivity.class );
                break;
            case R.id.button_shake2share:
                in = new Intent( this, Shake2ShareActivity.class );
                break;
            case R.id.button_likecommnet:
                in = new Intent( this, LikeCommentActivity.class );
                break;
            default:
                in = new Intent( this, ShareActivity.class );
        }

        startActivity( in );
    }
}
