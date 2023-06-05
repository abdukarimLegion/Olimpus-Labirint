package com.fedlo.tom.olimpuslabirint.game;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import static com.fedlo.tom.olimpuslabirint.game.GameFragmentKt.timer;
import static com.fedlo.tom.olimpuslabirint.game.GameFragmentKt.getTimer_time;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.fedlo.tom.olimpuslabirint.R;
import com.fedlo.tom.olimpuslabirint.ui.StartActivity;
import com.fedlo.tom.olimpuslabirint.ui.dialog.LevelCompleteDialog;
import com.fedlo.tom.olimpuslabirint.util.PrefManager;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class GameView extends View {

    enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    private Context context;
    private Cell[][] cells;
    private Cell player, exit;
    private static final int COLS = 5, ROWS = 5;
    private static final float WALL_THICKNESS = 30;
    private float cellsSize, hMargin, vMargin;
    private Paint wallPoint, playerpaint, exitPaint;
    private Random random;

    private Bitmap toj, quduq;

    private PrefManager prefManager;

    private PrefManager getPrefManager() {
        if (prefManager == null) {
            prefManager = PrefManager.instance;
        }
        return prefManager;
    }


    @SuppressLint("SuspiciousIndentation")
    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        wallPoint = new Paint();
        wallPoint.setColor(Color.WHITE);

        wallPoint.setStrokeWidth(WALL_THICKNESS);

        toj = BitmapFactory.decodeResource(getResources(), R.drawable.toj1);
        quduq = BitmapFactory.decodeResource(getResources(), R.drawable.quduq);
        playerpaint = new Paint();
        playerpaint.setShader(null); // Clear any existing shader
        playerpaint.setAntiAlias(true);
        playerpaint.setFilterBitmap(true);
        playerpaint.setDither(true);
        playerpaint.setAlpha(255); // Set the desired alpha value if needed

        exitPaint = new Paint();
        exitPaint.setShader(null); // Clear any existing shader
        exitPaint.setAntiAlias(true);
        exitPaint.setFilterBitmap(true);
        exitPaint.setDither(true);
        exitPaint.setAlpha(255);
//        playerpaint.setColor(Color.RED);


//        exitPaint.setColor(Color.BLUE);

        random = new Random();
        time();
        createLabirint();
    }

    private Cell getNeighbour(Cell cell) {

        ArrayList<Cell> neighbours = new ArrayList<>();


//        left neighbour
        if (cell.col > 0) {
            if (!cells[cell.col - 1][cell.row].visited)
                neighbours.add(cells[cell.col - 1][cell.row]);
        }


//        right neighbour
        if (cell.col < COLS - 1) {
            if (!cells[cell.col + 1][cell.row].visited)
                neighbours.add(cells[cell.col + 1][cell.row]);
        }


//        top neighbour
        if (cell.row > 0) {
            if (!cells[cell.col][cell.row - 1].visited)
                neighbours.add(cells[cell.col][cell.row - 1]);
        }


//        bottom neighbour
        if (cell.row < ROWS - 1) {
            if (!cells[cell.col][cell.row + 1].visited)
                neighbours.add(cells[cell.col][cell.row + 1]);
        }

        if (neighbours.size() > 0) {
            int index = random.nextInt(neighbours.size());

            return neighbours.get(index);
        }
        return null;
    }

    private void removeWall(Cell current, Cell next) {

        if (current.col == next.col && current.row == next.row + 1) {
            current.topWall = false;
            next.bottomWall = false;
        }

        if (current.col == next.col && current.row == next.row - 1) {
            current.bottomWall = false;
            next.topWall = false;
        }

        if (current.col == next.col + 1 && current.row == next.row) {
            current.leftWall = false;
            next.rightWall = false;
        }

        if (current.col == next.col - 1 && current.row == next.row) {
            current.rightWall = false;
            next.leftWall = false;
        }


    }

    public void movePlayer(Direction direction) {
        switch (direction) {
            case UP:
                if (!player.topWall) {
                    player = cells[player.col][player.row - 1];
                }
                break;
            case DOWN:
                if (!player.bottomWall) {
                    player = cells[player.col][player.row + 1];
                }
                break;
            case LEFT:
                if (!player.leftWall) {
                    player = cells[player.col - 1][player.row];
                }
                break;
            case RIGHT:
                if (!player.rightWall) {
                    player = cells[player.col + 1][player.row];
                }

        }
        checkExit();
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {


        if (event.getAction() == MotionEvent.ACTION_DOWN)
            return true;

        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            float x = event.getX();
            float y = event.getY();

            float playerCenterX = hMargin + (player.col + 0.5f) * cellsSize;
            float playerCenterY = vMargin + (player.row + 0.5f) * cellsSize;

            float dx = x - playerCenterX;

            float dy = y - playerCenterY;

            float absDx = Math.abs(dx);
            float absDy = Math.abs(dy);

            if (absDx > cellsSize || absDy > cellsSize) {
                if (absDx > absDy) {
//                    move on x-direction

                    if (dx > 0) {
//                        move to the right
                        movePlayer(Direction.RIGHT);
                    } else {
//                        move to the left
                        movePlayer(Direction.LEFT);
                    }

                } else {
//                    move in y-direction

                    if (dy > 0) {

//                        move down
                        movePlayer(Direction.DOWN);

                    } else {
//                        move up
                        movePlayer(Direction.UP);

                    }
                }
            }
            return true;
        }
        return super.onTouchEvent(event);
    }

    private void checkExit() {
        if (player == exit) {
//            createMaze();
            int level = getPrefManager().getLevel();
            level += 1;
            getPrefManager().saveLevel(level);
            timer.cancel();

            showCompleteDialog();
        }
    }

    private void createLabirint() {
        Stack<Cell> stack = new Stack<>();
        Cell current, next;


        cells = new Cell[COLS][ROWS];


        for (int x = 0; x < COLS; x++) {
            for (int y = 0; y < ROWS; y++) {
                cells[x][y] = new Cell(x, y);

            }
        }

        player = cells[0][0];
        exit = cells[COLS - 1][ROWS - 1];

        current = cells[0][0];
        current.visited = true;
        do {

            next = getNeighbour(current);
            if (next != null) {
                removeWall(current, next);
                stack.push(current);
                current = next;
                current.visited = true;
            } else {
                current = stack.pop();
            }
        } while (!stack.empty());
    }


    public void showCompleteDialog() {
        int coin = prefManager.getLevel() * 9;
        LevelCompleteDialog newInstance = LevelCompleteDialog.newInstance(String.valueOf(getTimer_time()), String.valueOf(coin));

        newInstance.show(((AppCompatActivity) getContext()).getSupportFragmentManager(), "dialog1");
        newInstance.setCancelable(false);


        int balance = prefManager.getBalance();
        balance += coin;
        Log.i(TAG, "onCreateView: complete dialog " + coin);
        prefManager.saveBalance(balance);
        newInstance.setOnClick(new LevelCompleteDialog.OnClick() {
            @Override
            public void onClick(String str) {
                Intent intent = new Intent(((AppCompatActivity) getContext()), StartActivity.class);
                ((AppCompatActivity) getContext()).startActivity(intent);
                ((AppCompatActivity) getContext()).finish();
            }
        });
    }

    private void setCurrentFragment(Fragment fragment) {
        FragmentTransaction transaction = ((AppCompatActivity) getContext()).getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(
                R.anim.slide_in,
                R.anim.fade_out,
                R.anim.fade_in,
                R.anim.slide_out
        );
        transaction.replace(R.id.flContainer, fragment);

        transaction.commit();
    }

    private void time() {
        new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // Update the UI or perform actions on each tick
                long secondsLeft = millisUntilFinished / 1000;
                System.out.println("Seconds left: " + secondsLeft);
            }

            @Override
            public void onFinish() {
                // Perform actions when the countdown finishes
                System.out.println("Countdown finished!");
            }
        }.start();


    }


    @Override
    protected void onDraw(Canvas canvas) {

        int width = getWidth();
        int height = getHeight();


        if (width / height < COLS / ROWS) {
            cellsSize = width / (COLS + 1);

        } else {
            cellsSize = height / (ROWS + 1);
        }

        hMargin = (width - COLS * cellsSize) / 2;
        vMargin = (float) ((int) (height - ROWS * cellsSize) / 2.3);

        canvas.translate(hMargin, vMargin);

        for (int x = 0; x < COLS; x++) {
            for (int y = 0; y < ROWS; y++) {
                if (cells[x][y].topWall) {
                    canvas.drawLine(
                            x * cellsSize,
                            y * cellsSize,
                            (x + 1) * cellsSize,
                            y * cellsSize,
                            wallPoint);
                }

                if (cells[x][y].leftWall) {
                    canvas.drawLine(
                            x * cellsSize,
                            y * cellsSize,
                            x * cellsSize,
                            (y + 1) * cellsSize,
                            wallPoint);
                }


                if (cells[x][y].bottomWall) {
                    canvas.drawLine(
                            x * cellsSize,
                            (y + 1) * cellsSize,
                            (x + 1) * cellsSize,
                            (y + 1) * cellsSize,
                            wallPoint);
                }

                if (cells[x][y].rightWall) {
                    canvas.drawLine(
                            (x + 1) * cellsSize,
                            y * cellsSize,
                            (x + 1) * cellsSize,
                            (y + 1) * cellsSize,
                            wallPoint);
                }

            }
        }

        float margin = cellsSize / 10;
//        @SuppressLint("DrawAllocation") BitmapShader shader = new BitmapShader(toj, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
//        playerpaint.setShader(shader);
//        canvas.drawRect(
//                player.col * cellsSize + margin,
//                player.row * cellsSize + margin,
//                (player.col + 1) * cellsSize - margin,
//                (player.row + 1) * cellsSize - margin,
//                playerpaint
//        );

        canvas.drawBitmap(toj, player.col * cellsSize + margin
                , player.row * cellsSize + margin, playerpaint);

        canvas.drawBitmap(quduq, exit.col * cellsSize + margin,
                exit.row * cellsSize + margin, exitPaint);

//        canvas.drawRect(
//                exit.col * cellsSize + margin,
//                exit.row * cellsSize + margin,
//                (exit.col + 1) * cellsSize - margin,
//                (exit.row + 1) * cellsSize - margin,
//                exitPaint
//        );

    }

    private static class Cell {
        boolean
                topWall = true,
                leftWall = true,
                bottomWall = true,
                rightWall = true,
                visited = false;

        int col, row;

        public Cell(int col, int row) {
            this.col = col;
            this.row = row;
        }
    }
}
