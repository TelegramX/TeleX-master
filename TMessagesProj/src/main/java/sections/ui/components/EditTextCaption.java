package sections.ui.components;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.view.MotionEvent;
import android.widget.EditText;
import android.widget.TextView;

import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLog;

import java.lang.reflect.Field;

import sections.backend.volley.DefaultRetryPolicy;


public class EditTextCaption extends EditText {
    private String f398a;
    private StaticLayout f399b;
    private int f400c;
    private int f401d;
    private int f402e;
    private Object f403f;
    private Field f404g;
    private Drawable[] f405h;
    private Field f406i;
    private int f407j;

    public EditTextCaption(Context context) {
        super(context);
        this.f407j = 0;
        try {
            Field declaredField = TextView.class.getDeclaredField("mEditor");
            declaredField.setAccessible(true);
            this.f403f = declaredField.get(this);
            Class cls = Class.forName("android.widget.Editor");
            this.f404g = cls.getDeclaredField("mShowCursor");
            this.f404g.setAccessible(true);
            this.f406i = cls.getDeclaredField("mCursorDrawable");
            this.f406i.setAccessible(true);
            this.f405h = (Drawable[]) this.f406i.get(this.f403f);
        } catch (Throwable th) {
        }
    }

    protected void onDraw(Canvas canvas) {
        try {
            super.onDraw(canvas);
            if (this.f399b != null && this.f400c == length()) {
                Paint paint = getPaint();
                int color = getPaint().getColor();
                paint.setColor(0xFF0000);
                canvas.save();
                canvas.translate((float) this.f401d, (float) this.f402e);
                this.f399b.draw(canvas);
                canvas.restore();
                paint.setColor(color);
            }
        } catch (Throwable e) {
            FileLog.e( e);
        }
        try {
            if (this.f404g != null && this.f405h != null && this.f405h[0] != null) {
                if (((SystemClock.uptimeMillis() - this.f404g.getLong(this.f403f)) % 1000 < 500 ? 1 : null) != null) {
                    canvas.save();
                    canvas.translate(0.0f, (float) getPaddingTop());
                    this.f405h[0].draw(canvas);
                    canvas.restore();
                }
            }
        } catch (Throwable th) {
        }
    }

    @SuppressLint({"DrawAllocation"})
    protected void onMeasure(int i, int i2) {
        try {
            super.onMeasure(i, i2);
        } catch (Throwable e) {
            setMeasuredDimension(MeasureSpec.getSize(i), AndroidUtilities.dp(51.0f));
            FileLog.e( e);
        }
        this.f399b = null;
        if (this.f398a != null && this.f398a.length() > 0) {
            CharSequence text = getText();
            if (text.length() > 1 && text.charAt(0) == '@') {
                int indexOf = TextUtils.indexOf(text, ' ');
                if (indexOf != -1) {
                    TextPaint paint = getPaint();
                    int ceil = (int) Math.ceil((double) paint.measureText(text, 0, indexOf + 1));
                    int measuredWidth = (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
                    this.f400c = text.subSequence(0, indexOf + 1).length();
                    CharSequence ellipsize = TextUtils.ellipsize(this.f398a, paint, (float) (measuredWidth - ceil), TruncateAt.END);
                    this.f401d = ceil;
                    try {
                        this.f399b = new StaticLayout(ellipsize, getPaint(), measuredWidth - ceil, Alignment.ALIGN_NORMAL,  DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.0f, false);
                        if (this.f399b.getLineCount() > 0) {
                            this.f401d = (int) (((float) this.f401d) + (-this.f399b.getLineLeft(0)));
                        }
                        this.f402e = ((getMeasuredHeight() - this.f399b.getLineBottom(0)) / 2) + AndroidUtilities.dp(0.5f);
                    } catch (Throwable e2) {
                        FileLog.e( e2);
                    }
                }
            }
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        try {
            return super.onTouchEvent(motionEvent);
        } catch (Throwable e) {
            FileLog.e( e);
            return false;
        }
    }

    public void setCaption(String str) {
        if ((this.f398a != null && this.f398a.length() != 0) || (str != null && str.length() != 0)) {
            if (this.f398a == null || str == null || !this.f398a.equals(str)) {
                this.f398a = str;
                if (this.f398a != null) {
                    this.f398a = this.f398a.replace('\n', ' ');
                }
                requestLayout();
            }
        }
    }
}
