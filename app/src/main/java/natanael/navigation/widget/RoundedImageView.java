package natanael.navigation.widget;

import android.util.AttributeSet;
import android.graphics.Canvas;
import android.content.Context;
import android.graphics.Path;
import android.graphics.RectF;

public class RoundedImageView extends android.support.v7.widget.AppCompatImageView {

    public static float radius = 18.0f;

    public RoundedImageView(Context context) {
        super(context);
    }

    public RoundedImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RoundedImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        this.radius = 300.0f;
        Path clipPath = new Path();
        RectF rect = new RectF(0, 0, this.getWidth(), this.getHeight());
        clipPath.addRoundRect(rect, radius, radius, Path.Direction.CW);
        canvas.clipPath(clipPath);
        super.onDraw(canvas);
    }
}