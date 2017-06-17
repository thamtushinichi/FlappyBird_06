package com.group6.flappybird.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by TUAN on 6/1/2017.
 * Class Ống trong game, quản lý và random các ống có trong game
 */

public class Tube {
    private static final int DOMAIN_VALUE=140; //miền giá trị để random ra các tube khác nhau
    private static final int TUBE_GAP=100;
    private static final int TUBE_START=120; // giá trị của ống bắt đầu game
    public static final int TUBE_WIDTH= 54 ; //độ rộng của ống lúc hiển thị

    public Texture getTop_Tube() {
        return top_Tube;
    }

    public Texture getBottom_Tube() {
        return bottom_Tube;
    }

    public Vector2 getPos_Top_Tube() {
        return pos_Top_Tube;
    }

    public Vector2 getPos_Bottom_Tube() {
        return pos_Bottom_Tube;
    }

    private Texture top_Tube;
    private Texture bottom_Tube;
    private Random random;
    private Vector2 pos_Top_Tube;
    private Vector2 pos_Bottom_Tube;
    private Rectangle recTop;//là hình chữ nhật được vẽ từ điểm pos_Top_Tube đến hết bề rộng của Tube
    //mục đích để bắt sự kiện nếu chim đụng trúng hình chữ nhật này là nó sẽ chết
    private Rectangle recBot; //tương tự như trên mà theo chiều ngược lại
    public Tube(float x)
    {
        //khởi tạo 1 cây tube với giá trị x là hoành độ của màn hình( bao gồm top and bot tube)
        top_Tube= new Texture("toptube.png");
        bottom_Tube= new Texture("bottomtube.png");
        random = new Random();
        pos_Top_Tube= new Vector2(x,random.nextInt(DOMAIN_VALUE) + TUBE_GAP+TUBE_START);
        pos_Bottom_Tube= new Vector2(x,pos_Top_Tube.y - TUBE_GAP - bottom_Tube.getHeight());

        recTop= new Rectangle(pos_Top_Tube.x,pos_Top_Tube.y,top_Tube.getWidth(),top_Tube.getHeight());
        recBot= new Rectangle(pos_Bottom_Tube.x,pos_Bottom_Tube.y,bottom_Tube.getWidth(),bottom_Tube.getHeight());

    }
    //set lại vị trí của ống trong game sau 1 khoàn thời gian dt
    public void re_Postion(float x)
    {
        pos_Top_Tube.set(x,random.nextInt(DOMAIN_VALUE)+ TUBE_GAP +TUBE_START);
        pos_Bottom_Tube.set(x,pos_Top_Tube.y - TUBE_GAP - bottom_Tube.getHeight());
        recTop.setPosition(pos_Top_Tube.x,pos_Top_Tube.y);
        recBot.setPosition(pos_Bottom_Tube.x,pos_Bottom_Tube.y);

    }
    //hàm check xem bird die chưa
    public boolean isBirdDie(Rectangle recBird)
    {
        return  (recBird.overlaps(recTop) || recBird.overlaps(recBot));

    }
//    public void getScore(Rectangle recBird,int score)
//    {
//        //tinh diem
//        if(recBird.getY() < recBot.getY() && recBird.getY() > recTop.getY())
//        {
//            if(recBird.getX() >)
//        }
//    }
    public void dispose()
    {
        //ẩn các cột đi
        top_Tube.dispose();
        bottom_Tube.dispose();
    }

}
