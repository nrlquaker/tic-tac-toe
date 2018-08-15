package com.example.vasylustynov.tictactoe;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class BoardView extends View {
    private static final float PARTITION_RATIO = 1 / 3f;
    private static final int X = 1;
    private static final int O = 2;
    private Rect[][] squaresCoordinates = new Rect[3][3];
    private int[][] board = new int[3][3];
    private Bitmap xMark;
    private Bitmap oMark;
    private Paint paint = new Paint();
    private Paint highLightPaint = new Paint();
    private boolean touching = false;
    private Point rectIndex = new Point(0, 0);
    private OnSquarePressedListener listener;

    @SuppressWarnings("unused")
    BoardView(Context ctx) {
        super(ctx);
    }

    @SuppressWarnings("unused")
    BoardView(Context ctx, AttributeSet attrs) {
        super(ctx, attrs);
    }

    public void drawX(int x, int y) {
        drawMark(x, y, X);
    }

    public void drawO(int x, int y) {
        drawMark(x, y, O);
    }

    public void setOnSquarePressedListener(OnSquarePressedListener listener) {
        this.listener = listener;
    }

    @SuppressLint("ClickableViewAccessibility") // ignore blind people for now
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                rectIndex = getRectIndexesFor(x, y);
                touching = true;
                invalidate(squaresCoordinates[rectIndex.x][rectIndex.y]);
                break;
            case MotionEvent.ACTION_UP:
                touching = false;
                invalidate(squaresCoordinates[rectIndex.x][rectIndex.y]);
                Point upRectIndex = getRectIndexesFor(x, y);
                if (upRectIndex.equals(rectIndex)) {
                    if (listener != null) {
                        listener.onSquarePressed(rectIndex.x, rectIndex.y);
                    }
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                touching = false;
                break;

        }
        return true;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int size = Math.min(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(size, size);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawVerticalLines(canvas);
        drawHorizontalLines(canvas);
        drawSquares(canvas);
        if (touching) {
            drawHighlightRectangle(canvas);
        }
    }

    private void drawMark(int x, int y, int mark) {
        board[x][y] = mark;
        invalidate(squaresCoordinates[x][y]);
    }

    private Point getRectIndexesFor(float x, float y) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (squaresCoordinates[i][j].contains((int) x, (int) y))
                    return new Point(i, j);
            }
        }
        return new Point(-1, -1);
    }

    private void init() {
        xMark = BitmapFactory.decodeResource(getResources(), R.drawable.x_mark);
        oMark = BitmapFactory.decodeResource(getResources(), R.drawable.o_mark);

        paint.setColor(ContextCompat.getColor(getContext(), android.R.color.black));
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(getResources().getDisplayMetrics().density * 5);

        highLightPaint.setColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
        highLightPaint.setStyle(Paint.Style.FILL);
        highLightPaint.setAntiAlias(true);
        initializeTicTacToeSquares();
    }

    private void initializeTicTacToeSquares() {
        int xUnit = (int) (getWidth() * PARTITION_RATIO);
        int yUnit = (int) (getHeight() * PARTITION_RATIO);
        for (int x = 0; x < squaresCoordinates.length; x++) {
            for (int y = 0; y < squaresCoordinates.length; y++) {
                squaresCoordinates[y][x] = new Rect(x * xUnit, y * yUnit, (x + 1) * xUnit, (y + 1) * yUnit);
            }
        }
    }

    private void drawSquares(Canvas canvas) {
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board.length; y++) {
                if (board[x][y] != 0) {
                    Bitmap bitmap = board[x][y] == 1 ? xMark : oMark;
                    drawBitmap(canvas, squaresCoordinates[x][y], bitmap);
                }
            }
        }
    }

    private void drawHighlightRectangle(Canvas canvas) {
        canvas.drawRect(squaresCoordinates[rectIndex.x][rectIndex.y], highLightPaint);
    }

    private void drawVerticalLines(Canvas canvas) {
        canvas.drawLine(getWidth() * PARTITION_RATIO, 0f, getWidth() * PARTITION_RATIO, getHeight(), paint);
        canvas.drawLine(getWidth() * (2 * PARTITION_RATIO), 0f, getWidth() * (2 * PARTITION_RATIO), getHeight(), paint);
    }

    private void drawHorizontalLines(Canvas canvas) {
        canvas.drawLine(0f, getWidth() * PARTITION_RATIO, getWidth(), getWidth() * PARTITION_RATIO, paint);
        canvas.drawLine(0f, getWidth() * (2 * PARTITION_RATIO), getWidth(), getWidth() * (2 * PARTITION_RATIO), paint);
    }

    private void drawBitmap(Canvas canvas, Rect rect, Bitmap bitmap) {
        canvas.drawBitmap(bitmap, null, rect, null);
    }

    interface OnSquarePressedListener {
        void onSquarePressed(int x, int y);
    }
}