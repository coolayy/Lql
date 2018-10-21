package com.lql.cheng.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lql.cheng.R;
import com.lql.cheng.aty.RenRenGameAty;
import com.lql.cheng.utils.Constant;
import com.lql.cheng.utils.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Stack;

import static com.lql.cheng.utils.Constant.ground;
import static com.lql.cheng.utils.Constant.isnoPlaySound;

//import com.whale.nangua.pumpkingobang.utils.AssetsLoad;
//import com.whale.nangua.pumpkingobang.utils.Utils;

public  class RenRenGoBang extends SurfaceView implements Runnable,SurfaceHolder.Callback {


        RenRenGameAty father;
    private Canvas canvas;
    private SurfaceHolder sfh;
    private Thread th = new Thread(this);



    protected static int GRID_SIZE = 2;    //设置为国际标准
    protected static int GRID_WIDTH = 36; // 棋盘格的宽度
    protected static int CHESS_DIAMETER = 37; // 棋的直径
    protected static int mStartX;// 棋盘定位的左上角X
    protected static int mStartY;// 棋盘定位的左上角Y

    //    private static int[][] mGridArray; // 网格
    private Stack<String> storageArray;


    int wbflag = 2; //该下白棋了=2，该下黑棋了=1. 这里先下黑棋（黑棋以后设置为机器自动下的棋子）
    int mLevel = 1; //游戏难度
    int mWinFlag = 0;
    private final int BLACK = 2;
    private final int WHITE = 1;
    private Paint paint1;

    private boolean WHITE_FLAG ;
    private Bitmap whiteMap;
    private Bitmap blackMap;
;
    boolean focus=false;
    int selectqizi=0;
    int startI,startJ;
    int endI,endJ;
    private int whoWin = 0;// 0没有，1白色win，2黑色win
    private boolean isStop = false;





    //private TextView mStatusTextView; //  根据游戏状态设置显示的文字
    private TextView mStatusTextView; //  根据游戏状态设置显示的文字

    private Bitmap btm1;
    private final Paint mPaint = new Paint();

    CharSequence mText;
    CharSequence STRING_WIN = "白棋赢啦!  ";
    CharSequence STRING_LOSE = "黑棋赢啦!  ";
    CharSequence STRING_EQUAL = "和棋！  ";


    public RenRenGoBang(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.father=(RenRenGameAty) context;

//        setBackgroundColor(Color.GRAY);
//        setZOrderOnTop(true);
//        setZOrderMediaOverlay(true);
//        getHolder().setFormat(PixelFormat.TRANSPARENT);
        sfh = this.getHolder();
        sfh.addCallback((SurfaceHolder.Callback) this);


//        ImageView ima=new ImageView(this);
//
//        View v=new View.findViewById(R.id.)
//        SurfaceView.setImageResource(R.drawable.renrengame_background)
//
//        SetBackgroundRes(R.drawable.renrengame_background);


        init();
    }



    //
//    public Void setBackgroundRes(int viewId, int backgroundRes) {
//        View view = retrieveView(viewId);
//        view.setBackgroundResource(backgroundRes);
//        return this;
//    }
    //按钮监听器
    MyButtonListener myButtonListener;

    // 初始化黑白棋的Bitmap
    public void init() {



//        storageArray = new Stack<>();
        myButtonListener = new MyButtonListener();
        wbflag = 2; //初始为先下黑棋
        mWinFlag = 0; //清空输赢标志。
//        mGridArray = new int[GRID_SIZE - 1][GRID_SIZE - 1];



        paint1=new Paint();
        paint1.setColor(Color.YELLOW);
        paint1.setTextSize(28);
        paint1.setAntiAlias(true);

        Bitmap bitmap = Bitmap.createBitmap(CHESS_DIAMETER, CHESS_DIAMETER, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Resources r = this.getContext().getResources();
        whiteMap = BitmapFactory.decodeResource(r, R.drawable.human);
        blackMap = BitmapFactory.decodeResource(r, R.drawable.ai);
//        renren=BitmapFactory.decodeResource(r,R.drawable.renrengame_background);


    }

    //设置显示的textView
    public void setTextView(TextView tv) {
        mStatusTextView = tv;
        mStatusTextView.setVisibility(View.VISIBLE);
    }

    //悔棋按钮
    private Button huiqi;
    //刷新那妞
    private Button refresh;

    //设置两个按钮
    public void setButtons(Button huiqi, Button refresh) {
        this.huiqi = huiqi;
        this.refresh = refresh;
        huiqi.setOnClickListener(myButtonListener);
        refresh.setOnClickListener(myButtonListener);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mStartX = w / 2 - GRID_SIZE * 6*GRID_WIDTH / 2;
        mStartY = h / 2 - GRID_SIZE *6* GRID_WIDTH / 2;
    }

    /**
     * 点下出现棋子


     */
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        int x;
//        int y;
//        float x0 = GRID_WIDTH - (event.getX() - mStartX) % GRID_WIDTH;
//        float y0 = GRID_WIDTH - (event.getY() - mStartY) % GRID_WIDTH;
//        if (x0 < GRID_WIDTH / 2) {
//            x = (int) ((event.getX() - mStartX) / GRID_WIDTH);
//        } else {
//            x = (int) ((event.getX() - mStartX) / GRID_WIDTH) - 1;
//        }
//        if (y0 < GRID_WIDTH / 2) {
//            y = (int) ((event.getY() - mStartY) / GRID_WIDTH);
//        } else {
//            y = (int) ((event.getY() - mStartY) / GRID_WIDTH) - 1;
//        }
//        if ((x >= 0 && x < GRID_SIZE - 1)
//                && (y >= 0 && y < GRID_SIZE - 1)) {
//            if (mGridArray[x][y] == 0) {
//                if (wbflag == BLACK) {
//                    putChess(x, y, BLACK);
//                    //this.mGridArray[x][y] = 1;
//                    if (checkWin(BLACK)) { //如果是黑棋赢了
//                        mText = STRING_LOSE;
//                        showTextView(mText);
//                    } else if (checkFull()) {//如果棋盘满了
//                        mText = STRING_EQUAL;
//                        showTextView(mText);
//                    }
//                    wbflag = WHITE;
//                } else if (wbflag == WHITE) {
//                    putChess(x, y, WHITE);
//                    //this.mGridArray[x][y] = 2;
//                    if (checkWin(WHITE)) {
//                        mText = STRING_WIN;
//                        showTextView(mText);
//                    } else if (checkFull()) {//如果棋盘满了
//                        mText = STRING_EQUAL;
//                        showTextView(mText);
//                    }
//                    wbflag = BLACK;
//                }
//            }
//        }
//
//        this.invalidate();
//        return true;
//
//    }


    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
        // TODO Auto-generated method stub
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // TODO Auto-generated method stub
        th.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // TODO Auto-generated method stub
        Utils.initGroup();
        isStop = true;
    }

    // 触摸事件
    @Override

    public boolean onTouchEvent(MotionEvent event) {

        // TODO Auto-generated method stub
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (!isStop) {
                for (int y = 0;y < 3; y++) {
                    for (int x = 0; x < 3; x++) {

                        if(isInCircle(event.getX(), event.getY(), x, y)) {

                            if (Utils.hewin()) {
                                whoWin = 2;
                                Toast.makeText(getContext(), "恭喜黑狼赢了", Toast.LENGTH_SHORT).show();
//                                isStop=true;
                                father.playSound(4,1);
                                mText = STRING_LOSE;
                                showTextView(mText);
//                                RenRenGameAty.onPutChess("BLACK");

                            }else
                            if (Utils.baiwin()) {
                                whoWin = 1;
                                Toast.makeText(getContext(), "恭喜红狼赢了", Toast.LENGTH_SHORT).show();
//                                isStop=true;
                                father.playSound(4,1);
                                mText = STRING_WIN;
                                showTextView(mText);
//                                RenRenGameAty.onPutChess("WHITE");
                            }


                            if (WHITE_FLAG) {
                                if (Utils.baiwulu(x,y)){
                                    WHITE_FLAG=false;
                                    Toast.makeText(getContext(), "红狼无路,请黑狼让路", Toast.LENGTH_SHORT).show();
                                }

                                    int i = -1, j = -1;
                                    int[] pos = getpos(event);
                                    i = pos[0];
                                    j = pos[1];
                                    if (focus == false) {//之前没有选中棋子
                                        if (Constant.ground[y][x] != 0) {//有棋子
                                            if (Constant.ground[y][x] == 1) {//点击的是自己的棋子

                                                selectqizi = ground[i][j];
                                                focus = true;
                                                startI = i;
                                                startJ = j;
//                                            AssetsLoad.playSound(getContext(),
//                                                    AssetsLoad.putSoundId);
                                                // ai在此处落子
                                            }
                                        }
                                    } else {//之前选中棋子
                                        if (Constant.ground[y][x] != 0) {//点击有棋子
                                            if (Constant.ground[y][x] == 1) {//是自己的棋子
                                                selectqizi = ground[i][j];

                                                startI = i;
                                                startJ = j;
                                            }

                                        } else {//点击的位置没有棋子

                                            endI = i;
                                            endJ = j;
                                            WHITE_FLAG = true;
                                            if (Math.abs(endI - startI) <= 1 && Math.abs(endJ - startJ) <= 1 && (Math.abs((endI - startI) * (endI - startI)) + Math.abs((endJ - startJ) * (endJ - startJ))) <= 1) {
                                                if (endI == 0 && startI == 0) {
                                                    continue;
                                                }

                                                ground[endJ][endI] = 1;
//										ground[startI][startJ];
                                                ground[startJ][startI] = 0;
                                                father.playSound(2,1);


                                                startI = -1;
                                                startJ = -1;
                                                endI = -1;
                                                endJ = -1;//还原保存点
                                                focus = false;//标记买选中棋子
                                                WHITE_FLAG = false;
                                            }
//                                        else {
//                                            WHITE_FLAG=true;
//                                            Toast.makeText(getContext(),"白棋无路可走",Toast.LENGTH_SHORT).show();
//                                        }

                                        }

                                    }


//								Toast.makeText(getContext(),  "白棋成"+Utils.baisi(x,y) + " 干", Toast.LENGTH_SHORT).show();
//
                                }

                            if (!WHITE_FLAG) {
                                if(Utils.hewulu(x,y)){
                                    WHITE_FLAG=true;
                                    Toast.makeText(getContext(), "黑狼无路可走，请红狼让道", Toast.LENGTH_SHORT).show();
                                }

                                    int i = -1, j = -1;
                                    int[] pos = getpos(event);
                                    i = pos[0];
                                    j = pos[1];
                                    if (focus == false) {
                                        if (Constant.ground[y][x] != 0) {
                                            if (Constant.ground[y][x] == 2) {

                                                selectqizi = ground[i][j];
                                                focus = true;
                                                startI = i;
                                                startJ = j;
//                                            AssetsLoad.playSound(getContext(),
//                                                    AssetsLoad.putSoundId);
                                                // ai在此处落子
                                            }
                                        }
                                    } else {
                                        if (Constant.ground[y][x] != 0) {
                                            if (Constant.ground[y][x] == 2) {
                                                selectqizi = ground[i][j];
                                                focus = true;
                                                startI = i;
                                                startJ = j;
                                            }
//										else if (Constant.ground[y][x] == 1) {
//											endI = i;
//											endJ = j;
////													boolean canMove=guiZe.canMove(qizi,	startI,	startI,endI,endJ);
////													if (canMove){
//											if (Utils.islegalmove(endI,endJ)) {
//												ground[endJ][endI] = 2;
////											ground[startI][startJ];
//												ground[startJ][startI] = 0;
//												startI = -1;
//												startJ = -1;
//												endI = -1;
//												endJ = -1;
//												WHITE_FLAG = true;
//											}
//
////													}
//										}
                                        } else {//点击的位置没有棋子

                                            endI = i;
                                            endJ = j;
                                            WHITE_FLAG = false;
                                            if (Math.abs(endI - startI) <= 1 && Math.abs(endJ - startJ) <= 1 && (Math.abs((endI - startI) * (endI - startI)) + Math.abs((endJ - startJ) * (endJ - startJ))) <= 1) {

                                                if (startI == 0 && endI == 0) {
                                                    continue;
                                                }
                                                ground[endJ][endI] = 2;
//										ground[startI][startJ];
                                                ground[startJ][startI] = 0;

                                                father.playSound(2,1);
                                                startI = -1;
                                                startJ = -1;
                                                endI = -1;
                                                endJ = -1;//还原保存点
                                                focus = false;//标记买选中棋子

                                                WHITE_FLAG = true;
                                            }
//                                        else{
//                                            Toast.makeText(getContext(),"hei棋无路可走",Toast.LENGTH_SHORT).show();
//                                            WHITE_FLAG=false;
//                                        }


                                        }

                                    }
//                                AssetsLoad.playSound(getContext(),
//                                        AssetsLoad.putSoundId);
//								Toast.makeText(getContext(),  "黑棋成"+Utils.hesi(x,y)+ " 干", Toast.LENGTH_SHORT).show();

                            }

                            //									WHITE_FLAG = !WHITE_FLAG;
                            return super.onTouchEvent(event);

                        }

                    }

                }
            }
        }



//        this.invalidate();
        return super.onTouchEvent(event);
    }
    public int [] getpos(MotionEvent event){

        int []pos=new int[2];
        double x=event.getX();
        double y=event.getY();
        if (x>mStartX-GRID_WIDTH*2&&x<mStartY+14*GRID_WIDTH&y>mStartY-2*GRID_WIDTH&&y<mStartY+14*GRID_WIDTH){
            pos[0]= (int) Math.round ((x-mStartX)/(6*GRID_WIDTH));
            pos[1]= (int) Math.round((y-mStartY)/(6*GRID_WIDTH));


        }else {
            pos[0]=-1;
            pos[1]=-1;
        }
        return pos;
    }

    // 判断是否与某点最近
    private boolean isInCircle(float touch_x, float touch_y, int x, int y) {

        return ((touch_x - x  * 6 * GRID_WIDTH-mStartX)
                * (touch_x - x * 6 * GRID_WIDTH-mStartX) + (touch_y - y*6 * GRID_WIDTH-mStartY)
                * (touch_y - y * 6 * GRID_WIDTH-mStartY))< 9*GRID_WIDTH*GRID_WIDTH;
    }

    public void draw() {


        try {
            canvas = sfh.lockCanvas();



            //canvas.drawColor(Color.YELLOW);
            //先画实木背景
            Paint paintBackground = new Paint();
            Bitmap bitmap = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.chess_background);
            canvas.drawBitmap(bitmap, null, new Rect(mStartX - 2 * GRID_WIDTH, mStartY - 2 * GRID_WIDTH, mStartX + 6 * GRID_WIDTH * GRID_SIZE + 2 * GRID_WIDTH, mStartY + 6 * GRID_WIDTH * GRID_SIZE + 2 * GRID_WIDTH), paintBackground);
            // 画棋盘
            Paint paintRect = new Paint();
            paintRect.setColor(Color.BLACK);
            paintRect.setStrokeWidth(2);
            paintRect.setStyle(Paint.Style.STROKE);
//        for (int i = 0; i < GRID_SIZE; i++) {
//            for (int j = 0; j < GRID_SIZE; j++) {
//                int mLeft = i * GRID_WIDTH + mStartX;
//                int mTop = j * GRID_WIDTH + mStartY;
//                int mRright = mLeft + GRID_WIDTH;
//                int mBottom = mTop + GRID_WIDTH;
//                canvas.drawRect(mLeft, mTop, mRright, mBottom, paintRect);
//            }
//        }
            // 横线
            for (int i = 0; i < 3; i++) {
                canvas.drawLine(mStartX+36, 6 * GRID_WIDTH * i
                        + mStartY, mStartX + 12 * GRID_WIDTH, 6
                        * GRID_WIDTH * i + mStartY, paintRect);
            }
            for (int j = 1; j < 3; j++) {
                canvas.drawLine(mStartX + 6 * GRID_WIDTH * j,
                        mStartY, mStartX + 6
                                * GRID_WIDTH * j, GRID_WIDTH * 12 + mStartY,
                        paintRect);
            }
            for (int i = 0; i < 3; i++) {
                paintRect.setColor(Color.rgb(128,0,0));
                canvas.drawCircle(mStartX, mStartY + i * 6 * GRID_WIDTH, 36, paintRect);
            }




            for (int y = 0; y < 3; y += 1) {
                for (int x = 0; x < 3; x += 1) {
                    if (ground[y][x] != 0)
                        if (ground[y][x] == Constant.WHITE_CHESS)
                            canvas.drawBitmap(whiteMap, (x * 6 * GRID_WIDTH + mStartX)
                                    - 2 * Constant.CHESS_R, (y * 6 * GRID_WIDTH + mStartY)
                                    - 2 * Constant.CHESS_R, null);
                        else {
                            canvas.drawBitmap(blackMap, (x * 6 * GRID_WIDTH + mStartX)
                                    - 2 * Constant.CHESS_R, (y * 6 * GRID_WIDTH + mStartY)
                                    - 2 * Constant.CHESS_R, null);
                        }
                    if (focus) {
                        canvas.drawCircle(startI * 6 * GRID_WIDTH + mStartX, startJ * 6 * GRID_WIDTH + mStartY, 18, paint1);
                    }
//                if (focus){
//                    drawFocus();
//                }

                }// 这里加入判断是否有人获胜

            }
            //画棋盘的外边框
            paintRect.setStrokeWidth(4);
            canvas.drawRect(mStartX - 2 * GRID_WIDTH, mStartY - 2 * GRID_WIDTH, mStartX + 6 * GRID_WIDTH * GRID_SIZE + 2 * GRID_WIDTH, mStartY + GRID_WIDTH * 6 * GRID_SIZE + 2 * GRID_WIDTH, paintRect);


        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            if (canvas != null) {
                sfh.unlockCanvasAndPost(canvas);
            }
        }
    }


//        private void drawFocus() {
//        if (focus){
//            canvas.drawCircle(startI*6 * Constant.RECT_R+4*Constant.RECT_R,startJ*6 * Constant.RECT_R+4*Constant.RECT_R,18,paint1);
//        }
//    }
    public static boolean isLegal(int x, int y) {
        return x >= 0 && x < 3&& y >= 0 && y < 3;
    }




    public void putChess(int x, int y, int blackwhite) {
//        mGridArray[x][y] = blackwhite;
        String temp = x + ":" + y;
        storageArray.push(temp);

    }


    /**
     * 检查棋盘是否满了
     *
     * @return
     */
//    public boolean checkFull() {
//        int mNotEmpty = 0;
//        for (int i = 0; i < GRID_SIZE - 1; i++)
//            for (int j = 0; j < GRID_SIZE - 1; j++) {
//                if (mGridArray[i][j] != 0) mNotEmpty += 1;
//            }
//
//        if (mNotEmpty == (GRID_SIZE - 1) * (GRID_SIZE - 1)) return true;
//        else return false;
//    }

    public void showTextView(CharSequence mT) {
        this.mStatusTextView.setText(mT);
        mStatusTextView.setVisibility(View.VISIBLE);
    }

    private int[] showtime;

    public void setShowTimeTextViewTime(int[] showtime) {
        this.showtime = showtime;
    }

    class MyButtonListener implements OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                //如果是悔棋
                case R.id.renren_btn1:
                    isnoPlaySound=!isnoPlaySound;

                    break;
                //如果是刷新
                case R.id.renren_btn2:
                    setVisibility(View.VISIBLE);
                    mStatusTextView.invalidate();
                    init();
                    invalidate();
                    Utils.initGroup();
                    for (int i = 0; i < showtime.length; i++) {
                        showtime[i] = 0;
                    }
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
                    mStatusTextView.setText("人人模式  妙计：" + simpleDateFormat.format(new Date()));
                    break;
            }
        }
    }



    @Override
    public void run() {
        // TODO Auto-generated method stub
        while (!isStop) {
          draw();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


}
