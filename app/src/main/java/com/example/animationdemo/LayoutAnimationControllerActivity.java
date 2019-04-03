package com.example.animationdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class LayoutAnimationControllerActivity extends AppCompatActivity {
    private ListView list_item;
    private List<String> list=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_animation_controller);
        list_item=findViewById(R.id.list_item);
        for (int i=0;i<10;i++){
            list.add("View"+i);
        }

        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,list);
        list_item.setAdapter(adapter);
        //布局动画控制器
        LayoutAnimationController animationController=new LayoutAnimationController(AnimationUtils.loadAnimation(LayoutAnimationControllerActivity.this,R.anim.alpha));
        animationController.setOrder(LayoutAnimationController.ORDER_RANDOM);
        list_item.setLayoutAnimation(animationController);
        list_item.startLayoutAnimation();
    }
}
