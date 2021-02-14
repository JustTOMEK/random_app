package chess.clocks.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements Animation.AnimationListener{

    boolean buttonRotation =true;
    int Number=6;
    long deegres=0;
    SharedPreferences sharedPreferences;

    ImageButton btnStart,btnUp,btnDown;
    ImageView ruleta,pointer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onviews();

        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        this.Number = this.sharedPreferences.getInt("NUMBER", 6);
        setImageRoulette(this.Number);
    }

    @Override
    public void onAnimationStart(Animation animation) {
        this.buttonRotation =false;
        btnStart.setVisibility(View.VISIBLE);
    }


    @Override
    public void onAnimationEnd(Animation animation) {
        Toast toast =Toast.makeText(this,""+String.valueOf((int)Math.ceil((this.Number)
                -Math.floor((double)this.deegres)/(360.0d/((double)this.Number))))+" ",Toast.LENGTH_LONG);
        toast.setGravity(49,0,0);
        toast.show();
        this.buttonRotation =true;
        btnStart.setVisibility(View.VISIBLE);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    public void onClickButtonRoll(View v)
    {
        if(this.buttonRotation)
        {
            int random=new Random().nextInt(360)+3600;
            RotateAnimation rotateAnimation = new RotateAnimation((float)this.deegres,(float)(this.deegres+((long)random)),1,0.5f,1,0.5f);

            deegres=(this.deegres+((long)random))%360;
            rotateAnimation.setDuration(random);
            rotateAnimation.setFillAfter(true);
            rotateAnimation.setInterpolator(new DecelerateInterpolator());
            rotateAnimation.setAnimationListener(this);
            ruleta.setAnimation(rotateAnimation);
            ruleta.startAnimation(rotateAnimation);
        }
    }
    public void buttonplus(View v)
    {
        if(this.Number<10)
        {
            this.Number++;
            setImageRoulette(this.Number);
            btnDown.setVisibility(View.VISIBLE);
            SharedPreferences.Editor editor = this.sharedPreferences.edit();
            editor.putInt("NUMBER",this.Number);
            editor.commit();

        }
        if(this.Number==10)
        {
            btnUp.setVisibility(View.GONE);
        }
    }
    public void btttonMinus(View v)
    {
        if(this.Number>2)
        {
            this.Number--;
            setImageRoulette(this.Number);
            btnUp.setVisibility(View.VISIBLE);

            SharedPreferences.Editor editor = this.sharedPreferences.edit();
            editor.putInt("NUMBER",this.Number);
            editor.commit();
        }
        if(this.Number==2)
        {
            btnDown.setVisibility(View.GONE);
        }
    }

    private void setImageRoulette(int number) {
        switch (this.Number)
        {
            case 2:
                ruleta.setImageDrawable(getResources().getDrawable(R.drawable.ruleta2));
                return;
            case 3:
                ruleta.setImageDrawable(getResources().getDrawable(R.drawable.ruleta3));
                return;
            case 4:
                ruleta.setImageDrawable(getResources().getDrawable(R.drawable.ruleta4));
                return;
            case 5:
                ruleta.setImageDrawable(getResources().getDrawable(R.drawable.ruleta5));
                return;
            case 6:
                ruleta.setImageDrawable(getResources().getDrawable(R.drawable.ruleta6));
                return;
            case 7:
                ruleta.setImageDrawable(getResources().getDrawable(R.drawable.ruleta7));
                return;
            case 8:
                ruleta.setImageDrawable(getResources().getDrawable(R.drawable.ruleta8));
                return;
            case 9:
                ruleta.setImageDrawable(getResources().getDrawable(R.drawable.ruleta9));
                return;
            case 10:
                ruleta.setImageDrawable(getResources().getDrawable(R.drawable.ruleta10));
                return;

        }
    }

    public void onviews()
    {
        btnDown=findViewById(R.id.minus);
        btnStart=findViewById(R.id.roll);
        btnUp=findViewById(R.id.plus);
        ruleta=findViewById(R.id.ruleta);
        pointer=findViewById(R.id.pointer);

    }
}