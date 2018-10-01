package com.example.rootmen.bcchelper.menu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;

import com.example.rootmen.bcchelper.Menu;
import com.example.rootmen.bcchelper.R;

import java.util.ArrayList;

@SuppressLint("Registered")
public class Sup extends AppCompatActivity implements SurfaceHolder.Callback,View.OnTouchListener  {
    private SurfaceView mSurface;
    private Sup.DrawingThread mThread;
    @SuppressLint("ClickableViewAccessibility")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sup);
        mSurface = (SurfaceView) findViewById(R.id.Paint);
        mSurface.setZOrderOnTop(true);
        mSurface.getHolder().setFormat(PixelFormat.TRANSLUCENT);;
        mSurface.setOnTouchListener(this);
        mSurface.getHolder().addCallback(this);

        TextView textView =findViewById(R.id.Telega);

        //Экранируем кавычки в атрибуте html тега слэшем:
        String textWithLink = "Актуальная версия всегда в телеграмме <a href=\"https://t.me/Rootmen_Support\">https://t.me/Rootmen_Support</a>";
//Указываем с помощью Html.fromHtml, что у нас не просто текст:
        textView.setText(Html.fromHtml(textWithLink, null, null));
////Указываем что разрешаем ссылки кликать:
        textView.setLinksClickable(true);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
//Научаемся отлавливать клики пропустив текст через наш класс из пред. пункта.
        CharSequence text = textView.getText();
        if (text instanceof Spannable)
        {
            textView.setText(Sup.MakeLinksClicable.reformatText(text));
        }
    }
    static class MakeLinksClicable
    {
        private final static String LOG = Sup.MakeLinksClicable.class.getSimpleName();

        public static class CustomerTextClick extends ClickableSpan
        {
            String mUrl;

            public CustomerTextClick(String url)
            {
                mUrl = url;
            }

            @Override
            public void onClick(View widget)
            {
                //Тут можно как-то обработать нажатие на ссылку
                //Сейчас же мы просто открываем браузер с ней
                Log.i(LOG, "url clicked: " + this.mUrl);
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(mUrl));
                widget.getContext().startActivity(i);
            }
        }

        static SpannableStringBuilder reformatText(CharSequence text)
        {
            int end = text.length();
            Spannable sp = (Spannable) text;
            URLSpan[] urls = sp.getSpans(0, end, URLSpan.class);
            SpannableStringBuilder style = new SpannableStringBuilder(text);
            for (URLSpan url : urls)
            {
                style.removeSpan(url);
                Sup.MakeLinksClicable.CustomerTextClick click = new Sup.MakeLinksClicable.CustomerTextClick(url.getURL());
                style.setSpan(click, sp.getSpanStart(url), sp.getSpanEnd(url),
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }

            return style;
        }
    }

@SuppressLint("ClickableViewAccessibility")
public boolean onTouch(View v, MotionEvent event) {
    if (event.getAction() == MotionEvent.ACTION_DOWN) {
        mThread.addItem((int) event.getX(), (int) event.getY());
    }
    return true;
}

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mThread = new DrawingThread(holder, BitmapFactory.decodeResource(
                getResources(), R.drawable.iconl));
        mThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
        mThread.updateSize(width, height);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mThread.quit();
        mThread = null;
    }

    private static class DrawingThread extends HandlerThread implements
            Handler.Callback {
        private static final int MSG_ADD = 100;
        private static final int MSG_MOVE = 101;
        private static final int MSG_CLEAR = 102;
        private int mDrawingWidth, mDrawingHeight;
        private SurfaceHolder mDrawingSurface;
        private Paint mPaint;
        private Handler mReceiver;
        private Bitmap mIcon;
        private ArrayList<DrawingItem> mLocations;

        private class DrawingItem {
            // Current location marker
            int x, y;
            // Direction markers for motion
            boolean horizontal, vertical;

            public DrawingItem(int x, int y, boolean horizontal,
                               boolean vertical) {
                this.x = x;
                this.y = y;
                this.horizontal = horizontal;
                this.vertical = vertical;
            }
        }

        public DrawingThread(SurfaceHolder holder, Bitmap icon) {
            super("DrawingThread");
            mDrawingSurface = holder;
            mLocations = new ArrayList<DrawingItem>();
            mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mIcon = icon;
        }

        @Override
        protected void onLooperPrepared() {
            mReceiver = new Handler(getLooper(), this);
            // Start the rendering
            mReceiver.sendEmptyMessage(MSG_MOVE);
        }

        @Override
        public boolean quit() {
            // Clear all messages before dying
            mReceiver.removeCallbacksAndMessages(null);
            return super.quit();
        }

        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_ADD:
                    // Create a new item at the touch location,
                    // with a randomized start direction
                    DrawingItem newItem = new DrawingItem(msg.arg1, msg.arg2,
                            Math.round(Math.random()) == 0, Math.round(Math
                            .random()) == 0);
                    mLocations.add(newItem);
                    break;
                case MSG_CLEAR:
                    // Remove all objects
                    mLocations.clear();
                    break;
                case MSG_MOVE:
                    // Render a frame
                    Canvas c = mDrawingSurface.lockCanvas();
                    if (c == null) {
                        break;
                    }
                    // Clear Canvas first
                    c.drawColor(Color.BLACK);
                    // Draw each item
                    for (DrawingItem item : mLocations) {
                        // Update location
                        item.x += (item.horizontal ? 5 : -5);
                        if (item.x >= (mDrawingWidth - mIcon.getWidth())) {
                            item.horizontal = false;
                        } else if (item.x <= 0) {
                            item.horizontal = true;
                        }
                        item.y += (item.vertical ? 5 : -5);
                        if (item.y >= (mDrawingHeight - mIcon.getHeight())) {
                            item.vertical = false;
                        } else if (item.y <= 0) {
                            item.vertical = true;
                        }
                        // Draw to the Canvas
                        c.drawBitmap(mIcon, item.x, item.y, mPaint);
                    }
                    // Release to be rendered to the screen
                    mDrawingSurface.unlockCanvasAndPost(c);
                    break;
            }
            // Post the next frame
            mReceiver.sendEmptyMessage(MSG_MOVE);
            return true;
        }

        public void updateSize(int width, int height) {
            mDrawingWidth = width;
            mDrawingHeight = height;
        }

        public void addItem(int x, int y) {
            // Pass the location into the Handler using Message arguments
            Message msg = Message.obtain(mReceiver, MSG_ADD, x, y);
            mReceiver.sendMessage(msg);
        }

        public void clearItems() {
            mReceiver.sendEmptyMessage(MSG_CLEAR);
        }
    }
}