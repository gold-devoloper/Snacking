package com.erif.snacking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.erif.snacking.helper.AdapterRecyclerView;
import com.erif.snacking.helper.MainActivityHelper;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String[] titles;
    private MainActivityHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View parentView = findViewById(R.id.parentView);
        ExtendedFloatingActionButton fab = findViewById(R.id.fab);
        helper = new MainActivityHelper(parentView, fab);

        RecyclerView recyclerView = findViewById(R.id.act_main_recyclerView);
        AdapterRecyclerView adapter = new AdapterRecyclerView(callback());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        titles = new String[]{
                "Basic", "With Icon", "With Action", "With Margin", "Corner Radius",
                "Corner Radius (Custom)", "With Border", "Background Color" , "Background Image", "Text Color",
                "Custom Font Family", "Anchor View", "Top Position", "With State", "Message Max Lines", "Landscape Screen"
        };
        List<String> list = new ArrayList<>(Arrays.asList(titles));
        adapter.setList(list);
    }

    private AdapterRecyclerView.Callback callback() {
        return message -> {
            if (message.equals(titles[0])) {
                helper.snackBarBasic();
            } else if (message.equals(titles[1])) {
                helper.snackBarIcon();
            } else if (message.equals(titles[2])) {
                helper.snackBarAction();
            } else if (message.equals(titles[3])) {
                helper.snackBarMargin();
            } else if (message.equals(titles[4])) {
                helper.snackBarCorner();
            } else if (message.equals(titles[5])) {
                helper.snackBarCornerCustom();
            } else if (message.equals(titles[6])) {
                helper.snackBarBorder();
            } else if (message.equals(titles[7])) {
                helper.snackBarBackground();
            } else if (message.equals(titles[8])) {
                helper.snackBarBackgroundImage();
            } else if (message.equals(titles[9])) {
                helper.snackBarTextColor();
            } else if (message.equals(titles[10])) {
                helper.snackBarFont();
            } else if (message.equals(titles[11])) {
                helper.snackBarAnchor();
            } else if (message.equals(titles[12])) {
                helper.snackBarPosition();
            } else if (message.equals(titles[13])) {
                helper.snackBarState();
            } else if (message.equals(titles[14])) {
                helper.snackBarMaxLines();
            } else if (message.equals(titles[15])) {
                helper.snackBarLandscape();
            }
        };
    }
}