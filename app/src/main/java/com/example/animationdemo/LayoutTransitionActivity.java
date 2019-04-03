package com.example.animationdemo;

import android.animation.AnimatorInflater;
import android.animation.LayoutTransition;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

public class LayoutTransitionActivity extends AppCompatActivity {
    private Button button;
    private LinearLayout linearLayout;
    private int count;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_transition);
        button=findViewById(R.id.button);
        linearLayout=findViewById(R.id.linearLayout);
        //创建LayoutTransition
        LayoutTransition layoutTransition=new LayoutTransition();
        //设置动画时间
        layoutTransition.setDuration(2000);
        //第一个参数可以有多种：APPEARING：表示设置进入时动画
        //CHANGE_APPEARING、CHANGE_DISAPPEARING、CHANGING、DISAPPEARING
        //第二个参数是设置的动画，由于使用的是属性动画
        //因此通过AnimatorInflater.loadAnimator获得

        layoutTransition.setAnimator(LayoutTransition.APPEARING, AnimatorInflater.loadAnimator(LayoutTransitionActivity.this,R.animator.object_animation));
        //注：不要忘记将设置的布局动画添加到布局中
        linearLayout.setLayoutTransition(layoutTransition);
        //按钮监听，每次点击，添加一个按钮
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                Button btn=new Button(LayoutTransitionActivity.this);
                //设置宽高为：WRAP_CONTENT
                ViewGroup.LayoutParams params=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                btn.setLayoutParams(params);
                btn.setText("按钮"+count);
                //按钮监听，点击隐藏
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        v.setVisibility(View.GONE);
                    }
                });
                //添加View
                linearLayout.addView(btn);
            }
        });

    }
}
