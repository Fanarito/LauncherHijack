package com.baronkiko.nostealinggoingonhere;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class BroadcastReceiverOnBootComplete extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent)
    {
        switch (intent.getAction())
        {
            case Intent.ACTION_PACKAGE_REMOVED:
                SharedPreferences settings = context.getSharedPreferences("NoStealingGoingOnHere", MODE_PRIVATE);
                String s = settings.getString("ChosenLauncher", "");

                if (intent.getData().getSchemeSpecificPart().equals(s))
                {
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("ChosenLauncher", "com.baronkiko.nostealinggoingonhere");
                    editor.putString("ChosenLauncherName", "com.baronkiko.nostealinggoingonhere.MainActivity");
                    editor.commit(); // Commit the edits!
                }
                break;

            case  Intent.ACTION_BOOT_COMPLETED:
                MainActivity.SetContext(context);
                ServiceMan.Start(context);
                break;
        }
    }


}

