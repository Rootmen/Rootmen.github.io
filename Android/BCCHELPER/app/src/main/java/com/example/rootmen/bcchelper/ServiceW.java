package com.example.rootmen.bcchelper;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.os.SystemClock;
import android.widget.RemoteViews;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class ServiceW extends Service {
    private static long interval = 10 * 1000; // 10 x 1000 ms
    protected ArrayList<String> MassTime = new ArrayList<String>();
    protected ArrayList<String> MassName = new ArrayList<String>();
    protected ArrayList<String> MassType = new ArrayList<String>();
    protected ArrayList<String> MassTeacher = new ArrayList<String>();

    public ServiceW() {}

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String action = intent.getAction();
        if (! (AppWidgetManager.ACTION_APPWIDGET_DISABLED.equals(action))) {
            updateWidget();
            defineAlarm();
        }

        return START_STICKY;
    }


    private void cancelAlarm() {
        final Intent intent = new Intent(this, CalendarW.class);
        final PendingIntent pending = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT );
        final AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarm.cancel(pending);
    }


    private void defineAlarm() {
        final Intent intent = new Intent(this, CalendarW.class);
        final PendingIntent pending = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT );
        final AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarm.set(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime() + interval, pending);

    }


    private void updateWidget() {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        // Retrieve the identifiers for each instance of your chosen widget.
        ComponentName thisWidget = new ComponentName(this, CalendarW.class);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
        final int NbrOfWidgets = appWidgetIds.length;
        if(MassName.size()==0){
            try { new NewThread().execute().get();}
            catch (Exception e) { e.printStackTrace(); }
        }
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String currentDateandTime = sdf.format(new Date());
        String s1m[]=currentDateandTime.split(":");
        int s1=Integer.parseInt(s1m[0])*60+Integer.parseInt(s1m[1]);
        int s2=0;
        if(MassName.size()==0) return;
        for(int g=0;g<MassTime.size();g++){
            currentDateandTime=MassTime.get(g);
            s1m=currentDateandTime.split(":");
            s2=Integer.parseInt(s1m[0])*60+Integer.parseInt(s1m[1]);
            if(s1-s2>80) {
                MassTime.remove(g);
                MassName.remove(g);
                MassType.remove(g);
                MassTeacher.remove(g);
                g--;
            }
        }
        currentDateandTime=MassTime.get(0);
        s1m=currentDateandTime.split(":");
        s2=Integer.parseInt(s1m[0])*60+Integer.parseInt(s1m[1]); String QWtime="Времени до конца пары : "+(s1-s2);
        String QWtime1=MassTeacher.get(0)+" "+MassName.get(0)+ "\n"+MassType.get(0)+" "+MassTime.get(0);
        String QWtime2="",QWtime3="",QWtime4="";
        if(MassTime.size()>2) QWtime2=MassTeacher.get(1)+" "+MassName.get(1)+ "\n"+MassType.get(1)+" "+MassTime.get(1);
        if(MassTime.size()>3) QWtime3=MassTeacher.get(2)+" "+MassName.get(2)+ "\n"+MassType.get(2)+" "+MassTime.get(2);

        for (int i = 0; i < NbrOfWidgets; i++) {
            int appWidgetId = appWidgetIds[i];
            RemoteViews views = new RemoteViews(getPackageName(), R.layout.calendar);
            views.setTextViewText(R.id.QWtime,QWtime);
            views.setTextViewText(R.id.QWtimeInfo,QWtime1);
            views.setTextViewText(R.id.QW1,QWtime2);
            views.setTextViewText(R.id.QW2,QWtime3);
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }


    @Override
    public void onDestroy() {
        cancelAlarm();
        super.onDestroy();
    }


    public class NewThread extends AsyncTask<String, Void, String> {
        private Elements title;
        @Override
        protected String doInBackground(String... arg) {
            Document doc;
            try {
                doc = Jsoup.connect("http://Rootmen.github.io/Monday.xml").get();
                MassTime.clear();MassName.clear();MassType.clear();MassTeacher.clear();
                title = doc.select("time");
                for (Element titles : title) MassTime.add(titles.text());
                title = doc.select("name");
                for (Element titles : title) MassName.add(titles.text());
                title = doc.select("type");
                for (Element titles : title) MassType.add(titles.text());
                title = doc.select("teacher");
                for (Element titles : title) MassTeacher.add(titles.text());
            } catch (IOException e) { e.printStackTrace(); }
            return null;
        }

    }

}
