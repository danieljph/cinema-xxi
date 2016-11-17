package com.karyasarma.cinemaxxi.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.karyasarma.cinemaxxi.R;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Daniel Joi Partogi Hutapea
 */
public class SimpleWidgetProvider extends AppWidgetProvider
{
    private static final String ACTION_1 = "ACTION_1";
    private static final String ACTION_2 = "ACTION_2";

    private static final List<String> data = new ArrayList<>();
    private int currentIndex;

    static
    {
        data.add("Daniel");
        data.add("Joi");
        data.add("Partogi");
        data.add("Hutapea");
    }

    public SimpleWidgetProvider()
    {
    }

    @Override
    public void onReceive(Context context, Intent intent)
    {
        super.onReceive(context, intent);

        String action = intent.getAction();
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.simple_widget);

        if(ACTION_1.equals(action))
        {
            currentIndex = 1;
        }
        else if(ACTION_2.equals(action))
        {
            currentIndex = 2;
        }

        updateViewData(remoteViews);
        registerButtonListener(context, remoteViews);

        ComponentName widgetComponentName = new ComponentName(context, SimpleWidgetProvider.class);
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        appWidgetManager.updateAppWidget(widgetComponentName, remoteViews);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds)
    {
        int length = appWidgetIds.length;

        for(int i = 0; i<length; i++)
        {
            int widgetId = appWidgetIds[i];
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.simple_widget);
            updateViewData(remoteViews);
            registerButtonListener(context, remoteViews);
            appWidgetManager.updateAppWidget(widgetId, remoteViews);
        }
    }

    private void updateViewData(RemoteViews remoteViews)
    {
        remoteViews.setTextViewText(R.id.tv_reminder_title, data.get(currentIndex));
    }

    private void registerButtonListener(Context context, RemoteViews remoteViews)
    {
        remoteViews.setOnClickPendingIntent(R.id.btn1, getPendingIntent(context, ACTION_1));
        remoteViews.setOnClickPendingIntent(R.id.btn2, getPendingIntent(context, ACTION_2));
    }

    private PendingIntent getPendingIntent(Context context, String action)
    {
        Intent intent = new Intent(context, getClass());
        intent.setAction(action);
        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }
}
