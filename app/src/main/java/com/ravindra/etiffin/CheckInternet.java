package com.ravindra.etiffin;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

/**
 * Created by Cod_PlYr on 1/24/2017.
 */

public class CheckInternet  {
  Context ctx;
    public CheckInternet(Context context)
    {
        ctx=context;
    }

    public boolean isNetworkAvailable() {
        boolean connected=false;
        ConnectivityManager cm = (ConnectivityManager)ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) { // connected to the internet
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                // connected to wifi
               connected=true;
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                // connected to the mobile provider's data plan
               connected=true;
            }
        } else {
            // not connected to the internet
            connected=false;
        }
        return connected;
    }

    public void showNoInternetDialogue()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        builder.setTitle("ERROR !!");
        builder.setMessage("Sorry there was an error getting data from the Internet.\nNetwork Unavailable!");
        AlertDialog dialog = builder.create();
        builder.setPositiveButton("Retry", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();

            }
        });

        dialog.show();
        Toast.makeText(ctx, "Network Unavailable!", Toast.LENGTH_LONG).show();
    }

}
