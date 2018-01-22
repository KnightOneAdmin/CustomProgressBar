package com.knight.progressbar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

/**
 *   Create By Time:  2018/1/22
 *
 * @author knightoneadmin
 * @e-mail: 37442216knight@gmail.com
 * @describe: MyLinearLayout
 */

public class VerticalProgressBar extends View {
    /**
     * 画笔
     */
    private Paint mPaint;
    /**
     * 进度值
     */
    private int mProgress;
    /**
     * 宽度值
     */
    private int width;
    private int height;// 高度值
    /**
     * 画进度条的矩形
     */
    private RectF mRectF;

    /**
     * 外描边的宽度
     */
    private float BORDER_STROCK;
    /**
     * 进度条进度矩形与控件边界的距离,≥BORDER_STROCK
     */
    private float PROGRESS_STROCK;
    /**
     * 渐变颜色组
     */
    private int[] GRADIENT_COLORS = {Color.parseColor("#25DBD3"), Color.parseColor("#64B5F2"),};
    /**
     * 最大进度
     */
    private int max = 100;
    private int type;

    public VerticalProgressBar(Context context, AttributeSet attrs,
                               int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public VerticalProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public VerticalProgressBar(Context context) {
        super(context);
        init();
    }

    private void init() {

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mRectF = new RectF();
        BORDER_STROCK = getResources().getDimension(R.dimen.x3);
        PROGRESS_STROCK = getResources().getDimension(R.dimen.x5);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth() - 1;// 宽度值
        height = getMeasuredHeight() - 1;// 高度值
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int round = width / 2;//弧度为高度的一半
        mRectF.set(0, 0, width, height);//第一层矩形(描边层)
        mPaint.setColor(Color.parseColor("#0089F5"));//第一层矩形颜色(进度条描边的颜色)
        canvas.drawRoundRect(mRectF, round, round, mPaint);//画第一层圆角矩形
        mPaint.setColor(Color.parseColor("#FFFFFF"));//第二层矩形颜色(背景层颜色)
        mRectF.set(BORDER_STROCK, BORDER_STROCK, width - BORDER_STROCK, height - BORDER_STROCK);//第二层矩形(背景层)
        canvas.drawRoundRect(mRectF, round, round, mPaint);//画背景层圆角矩形(盖在描边层之上)


        if (mProgress == 0)//进度为 0不画进度
            return;
        float section = mProgress / max;
        //第三层矩形(进度层)
        mRectF.set(+8, height - mProgress / 100f * height + 10, width - 8, height - 8);
        //创建线性颜色渐变器
        LinearGradient shader = new LinearGradient(PROGRESS_STROCK, PROGRESS_STROCK,
                (width - PROGRESS_STROCK) * section, height - PROGRESS_STROCK, GRADIENT_COLORS, null, Shader.TileMode.MIRROR);
        mPaint.setShader(shader);//第三层矩形颜色(进度渐变色)
        canvas.drawRoundRect(mRectF, round, round, mPaint);//画第三层(进度层)圆角矩形(盖在背景层之上)
        mPaint.setShader(null);//清除之前传递的shader
    }


    /**
     * 设置progressbar进度
     */
    public void setProgress(int currentCount) {
        this.mProgress = currentCount > max ? max : currentCount;
        postInvalidate();
    }

    public void setMax(int maxProgress) {
        this.max = maxProgress;
    }

    public void setGradientColor(int[] colors) {
        this.GRADIENT_COLORS = colors;
    }

    public void setFrameColor(int type) {
        this.type = type;
    }
}
