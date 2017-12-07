package dhruvipatel.littercontrol;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreenActivity extends Activity
{
    ImageView imageView;
    Animation animation_1 , animation_2 , animation_3;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen_layout);

        imageView = (ImageView) findViewById(R.id.imageView);
        animation_1 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate);
        animation_2 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.antirotate);
        animation_3 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.abc_fade_out);

        /*Handler handler = new Handler();
        handler.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                Intent intent = new Intent(getApplicationContext(), FullscreenActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);*/

        imageView.startAnimation(animation_2);
        animation_2.setAnimationListener(new Animation.AnimationListener()
        {
            @Override
            public void onAnimationStart(Animation animation) { }

            @Override
            public void onAnimationEnd(Animation animation)
            {
                imageView.startAnimation(animation_1);
            }

            @Override
            public void onAnimationRepeat(Animation animation) { }
        });

        animation_1.setAnimationListener(new Animation.AnimationListener()
        {
            @Override
            public void onAnimationStart(Animation animation)
            {  }

            @Override
            public void onAnimationEnd(Animation animation)
            {
                imageView.startAnimation(animation_3);
                finish();
                Intent i = new Intent(getBaseContext(),LoginActivity.class);
                startActivity(i);
            }

            @Override
            public void onAnimationRepeat(Animation animation) { }
        });

    }

    @Override
    public void onWindowFocusChanged (boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus)
            imageView.startAnimation(animation_2);
    }
}
