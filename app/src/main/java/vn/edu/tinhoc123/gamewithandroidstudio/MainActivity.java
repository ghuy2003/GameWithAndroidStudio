package vn.edu.tinhoc123.gamewithandroidstudio;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {


    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("MainActivity.java", "onCreate()");
        super.onCreate(savedInstanceState);


        //tỉ lệ cửa sổ game toàn màn hình điện thoại (fullscreen)
        Window window = getWindow();
        window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );



        //render content trong game
        game = new Game(this);
        setContentView(game);
    }
    //khoi tao acticity lifecycle(ctrl+o)
    @Override
    protected void onStart() {

        Log.d("MainActivity.java", "onStart()");
        super.onStart();
    }
    protected void onResume() {
        Log.d("MainActivity.java", "onResume()");
        super.onResume();
    }
    //dung ung dung
    protected void onPause() {
        Log.d("MainActivity.java", "onPause()");
        game.pause();
        super.onPause();
    }
    protected void onStop() {
        Log.d("MainActivity.java", "onStop()");
        super.onStop();
    }
    protected void onDestroy() {
        Log.d("MainActivity.java", "onDestroy()");
        super.onDestroy();
    }
}