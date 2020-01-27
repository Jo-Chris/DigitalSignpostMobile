package com.example.digitalsignpostmobile.classes;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.digitalsignpostmobile.R;
import com.example.digitalsignpostmobile.activities.MainActivity;

public class DialogHandler {

    public static void showSuccessDialogAndStartActivity(final Activity activity, String msg){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.found_signs_dialog);

        TextView text = (TextView) dialog.findViewById(R.id.game_won_text);
        text.setText(msg);

        Button dialogButton = (Button) dialog.findViewById(R.id.btn_dialog);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                activity.startActivity(new Intent(activity, MainActivity.class));

            }
        });

        dialog.show();
    }

}
