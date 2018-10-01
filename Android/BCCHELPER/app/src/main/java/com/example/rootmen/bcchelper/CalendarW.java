package com.example.rootmen.bcchelper;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import java.util.Random;

/**
 * Implementation of App Widget functionality.
 */
public class CalendarW extends AppWidgetProvider {


    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
    }


    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        context.startService(intent);
    }

}

