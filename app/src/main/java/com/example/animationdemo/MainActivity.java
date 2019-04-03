package com.example.animationdemo;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Button btn_animation,layoutTransition,btn_view;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_animation = findViewById(R.id.btn_animation);
        layoutTransition=findViewById(R.id.layoutTransition);
        image = findViewById(R.id.image);
        btn_view=findViewById(R.id.btn_view);
        btn_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,LayoutAnimationControllerActivity.class);
                startActivity(intent);
            }
        });
        layoutTransition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,LayoutTransitionActivity.class);
                startActivity(intent);
            }
        });
        btn_animation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.scale);//从xml中获取动画
//                image.startAnimation(animation);
//                alpha(image);//透明
//                translate(image);//平移
//                rotate(image);//旋转
//                scale(image);//缩放
//                scaleListener(image);//动画监听
//                animationSet(image);//动画组合


//                Intent intent=new Intent(MainActivity.this,LayoutTransitionActivity.class);
//                startActivity(intent);
//                //设置跳转动画
//                overridePendingTransition(R.anim.activity_in,R.anim.activity_out);


                //静态通过xml设置属性动画ObjectAnimator
//                Animator animator= AnimatorInflater.loadAnimator(MainActivity.this,R.animator.object_animation);
//                animator.setTarget(image);
//                animator.start();

                //动态设置属性动画ObjectAnimator
//                ObjectAnimator.ofFloat(image,"scaleX",0.0f,1f).setDuration(2000).start();
//                ObjectAnimator.ofFloat(image, "TranslationY",1f,250f).setDuration(2000).start();
//                ObjectAnimator.ofFloat(image, "TranslationX",1f, 250f).setDuration(2000).start();
//                ObjectAnimator.ofFloat(image, "scaleY", 0.0f,1.0f).setDuration(2000).start();


                //PropertyValuesHolder 优化ObjectAnimator
//                PropertyValuesHolder holder=PropertyValuesHolder.ofFloat("scaleX",0.0f,1f);
//                PropertyValuesHolder holder1=PropertyValuesHolder.ofFloat("TranslationY",1f,250f);
//                PropertyValuesHolder holder2=PropertyValuesHolder.ofFloat("TranslationX",1f,250f);
//                PropertyValuesHolder holder3=PropertyValuesHolder.ofFloat("scaleY",0.0f,1.0f);
//                ObjectAnimator.ofPropertyValuesHolder(image,holder,holder1,holder2,holder3).setDuration(2000).start();

                //AnimatorSet——动画组合
//                AnimatorSet set = new AnimatorSet();
//                ObjectAnimator animator = ObjectAnimator.ofFloat(image, "scaleX", 0.0f, 1f);
//                ObjectAnimator animator2 = ObjectAnimator.ofFloat(image, "TranslationY", 1f, 250f);
//                ObjectAnimator animator3 = ObjectAnimator.ofFloat(image, "TranslationX", 1f, 250f);
//                ObjectAnimator animator4 = ObjectAnimator.ofFloat(image, "scaleY", 0.0f, 1.0f);
//                //设置动画同时进行
//                set.play(animator).with(animator2).with(animator3).with(animator4);
//                //设置动画同时作用
////                set.playTogether(animator,animator2,animator3,animator4);
//                //设置顺序执行动画
////                set.playSequentially(animator4,animator3,animator2,animator);
//                set.setDuration(2000);
//                set.start();


                //ValueAnimator
                //ofInt（）方法表示数值从60变化到0
                ValueAnimator animator=ValueAnimator.ofInt(60,0);
                //设置变化时间
                animator.setDuration(60000);
                //为Animator设置刷新监听
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        Integer integer= (Integer) animation.getAnimatedValue();
                        //赋给button按钮
                        btn_animation.setText(integer+"");
                    }
                });
                //注：记得开启ValueAnimator
                animator.start();

            }
        });
    }


    //AnimationSet动画组合
    private void animationSet(ImageView imageView) {
        AnimationSet set = new AnimationSet(true);
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360);
        //RotateAnimation旋转时长
        rotateAnimation.setDuration(2000);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.2F, 1F);
        //AlphaAnimation渐变时长
        alphaAnimation.setDuration(2000);
        set.addAnimation(rotateAnimation);//添加RotateAnimation
        set.addAnimation(alphaAnimation);//添加AlphaAnimation
        imageView.startAnimation(set);//ImageView开启动画
    }


    //动画监听，已缩放为例
    private void scaleListener(ImageView imageView) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.2F, 1F, 0.2F, 1F);
        scaleAnimation.setDuration(2000);
        scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {//开始时
                Log.i(TAG, "开始时onAnimationStart");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.i(TAG, "结束时onAnimationEnd");
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        image.startAnimation(scaleAnimation);
    }


    //缩放
    private void scale(ImageView imageView) {
        //参数：X轴从0.2倍放大到1倍（原图）Y轴也一样
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.2F, 1F, 0.2F, 1F);
        scaleAnimation.setDuration(2000);
        imageView.startAnimation(scaleAnimation);
    }


    //旋转
    private void rotate(ImageView imageView) {
        /**
         * fromDegrees/toDegrees:开始/结束角度
         * pivotX/pivotY:分别为旋转动画相对于x，y的坐标开始位置（相对值）
         * 注：区别与X，Y：是绝对坐标
         */
        RotateAnimation rotateAnimation = new RotateAnimation(0, 180, 100, 100);
        rotateAnimation.setDuration(2000);
        imageView.startAnimation(rotateAnimation);
    }


    //渐变透明
    private void alpha(ImageView imageView) {
        /**
         * fromAlpha：开始透明度（数值在0-1之间）
         * toAlpha：结束透明度（数值在0-1之间）
         */
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        //设置动画时间
        alphaAnimation.setDuration(2000);
        //设置动画重复次数
        alphaAnimation.setRepeatCount(2);

        imageView.startAnimation(alphaAnimation);//开启动画效果
    }

    //平移
    private void translate(ImageView imageView) {
        /**
         * fromXDela/toXDela：开始/结束的x坐标
         * fromYDela/toYDela：开始/结束的y坐标
         */
        TranslateAnimation translateAnimation = new TranslateAnimation(imageView.getMeasuredWidth(), 0, 0, 300);
        //设置动画时间
        translateAnimation.setDuration(2000);
        //设置是否记录移动后的位置，true时动画将停留在当前位置，false将回到开始位置
        translateAnimation.setFillAfter(false);
        //设置插值器，可以理解为用于改变运动形式的东西
        //（现在设置的运动形式类似于自由落体，会有弹跳效果）
        translateAnimation.setInterpolator(new BounceInterpolator());
        imageView.startAnimation(translateAnimation);
    }
}

