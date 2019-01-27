package soft.deal.best.topline.islamzajel.utils;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AlertDialog;

import soft.deal.best.topline.islamzajel.R;

/**
 * Created by hashoma on 4/25/2016.
 */
public class makeDialog {


    public void makeDialog(Context con, String value) {


        AlertDialog.Builder builder =
                new AlertDialog.Builder((Activity)con, R.style.AppCompatAlertDialogStyle);
        builder.setTitle(con.getResources().getString(R.string.Alert));
        builder.setMessage(value);
        builder.setPositiveButton(con.getResources().getString(R.string.OK), null);
        builder.setNegativeButton(con.getResources().getString(R.string.Cancel), null);
        builder.show();


    }
}