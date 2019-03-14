package com.example.cse.mywidgets;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

public class SampeWidget extends AppWidgetProvider {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        for (int myWidgetId:appWidgetIds){

            sharedPreferences=context.getSharedPreferences(MainActivity.NAME,Context.MODE_PRIVATE);
            String s=sharedPreferences.getString("bindu","No dataAvailable");

            Intent intent=new Intent(context,MainActivity.class);
            PendingIntent pendingIntent= PendingIntent.getActivity(context,1,intent,0);

            RemoteViews views=new RemoteViews(context.getPackageName(),R.layout.design);
            views.setTextViewText(R.id.widgettext,s);
            views.setOnClickPendingIntent(R.id.widgettext,pendingIntent);

            appWidgetManager.updateAppWidget(myWidgetId,views);

        }

    }
}
