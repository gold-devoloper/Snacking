package com.erif.snacking.helper;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.erif.snacking.R;
import com.erif.snacking.Snacking;
import com.erif.snacking.SnackingState;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

public class MainActivityHelper {

    private final View parentView;
    private final Context context;
    private final ExtendedFloatingActionButton fab;

    public MainActivityHelper(View parentView, ExtendedFloatingActionButton fab) {
        this.parentView = parentView;
        context = parentView.getContext();
        this.fab = fab;
    }

    public void snackBarBasic() {
        new Snacking.Builder(parentView, "Hello! this is basic message")
                .build().show();
    }

    public void snackBarIcon() {
        new Snacking.Builder(parentView, "This message with icon")
                .icon(R.drawable.ic_info, R.color.teal_200)
                .build().show();
    }

    public void snackBarAction() {
        new Snacking.Builder(parentView, "Click to dismiss message")
                .action("Dismiss", R.color.teal_200, snackBar -> {
                    snackBar.dismiss();
                    toast("Action Click");
                })
                .build().show();
    }

    public void snackBarCorner() {
        new Snacking.Builder(parentView, "This message with corner")
                .cornerRadius(R.dimen.snack_bar_corner_radius)
                .build().show();
    }

    public void snackBarCornerCustom() {
        new Snacking.Builder(parentView, "This message with custom corner")
                .useMargin(true)
                .cornerRadius(R.dimen.snack_bar_corner_radius, 0, 0, R.dimen.snack_bar_corner_radius)
                .build()
                .show();
    }

    public void snackBarMargin() {
        new Snacking.Builder(parentView, "This message with margin")
                .useMargin(true)
                .build().show();
    }

    public void snackBarBackground() {
        new Snacking.Builder(parentView, "This is custom background color")
                .backgroundColor(R.color.purple_200)
                .build()
                .show();
    }

    public void snackBarBackgroundImage() {
        new Snacking.Builder(parentView, "This is custom background image")
                .background(R.mipmap.img_gradient)
                .useMargin(true)
                .cornerRadius(R.dimen.snack_bar_corner_radius_small, 0, 0, R.dimen.snack_bar_corner_radius_small)
                .border(R.dimen.snack_bar_border_size_large, R.color.black)
                .build()
                .show();
    }

    public void snackBarBorder() {
        new Snacking.Builder(parentView, "This message with border")
                .border(R.dimen.snack_bar_border_size, R.color.teal_700)
                .useMargin(true)
                .cornerRadius(R.dimen.snack_bar_corner_radius_small)
                .build()
                .show();
    }

    public void snackBarTextColor() {
        new Snacking.Builder(parentView, "This is custom text color")
                .textColor("#ffca28")
                .build()
                .show();
    }

    public void snackBarFont() {
        new Snacking.Builder(parentView, "This is custom font family")
                .fontFamily(R.font.montserrat)
                .build()
                .show();
    }

    public void snackBarAnchor() {
        new Snacking.Builder(parentView, "This message with anchor view")
                .anchorView(fab)
                .build()
                .show();
    }

    public void snackBarPosition() {
        new Snacking.State(parentView, SnackingState.WARNING)
                .message("This message is on top position")
                .icon(R.drawable.ic_info)
                .position(Snacking.TOP)
                .useMargin(true)
                .cornerRadius(R.dimen.snack_bar_corner_radius_large)
                .border(R.dimen.snack_bar_border_size)
                .action("Cancel", snackBar -> toast("Action Click"))
                .show();
    }

    public void snackBarState() {
        new Snacking.State(parentView, Snacking.State.INFO)
                .message("This message is using state")
                .icon(R.drawable.ic_info)
                .useMargin(true)
                .cornerRadius(R.dimen.snack_bar_corner_radius_small)
                .action("CLOSE", snackBar -> {
                    toast("Action Clicked");
                    snackBar.dismiss();
                })
                .show();
    }

    public void snackBarMaxLines() {
        new Snacking.Builder(parentView, "This is long message, this is long message, this is long message, this is long message, this is long message, this is long message")
                .action("LONG BUTTON TEXT", snackBar -> toast("Action Click"))
                .messageMaxLines(2)
                .build()
                .show();
    }

    public void snackBarLandscape() {
        new Snacking.State(parentView, Snacking.State.INFO)
                .message("This message is on landscape screen")
                .icon(R.drawable.ic_info)
                .useMargin(true)
                .cornerRadius(R.dimen.snack_bar_corner_radius_small)
                .action("CLOSE", snackBar -> {
                    toast("Action Click");
                    snackBar.dismiss();
                })
                .landscapeStyle(Snacking.CENTER)
                .border(R.dimen.snack_bar_border_size)
                .show();
    }

    private void toast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

}
