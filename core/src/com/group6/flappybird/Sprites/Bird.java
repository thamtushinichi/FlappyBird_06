package com.group6.flappybird.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by TUAN on 5/31/2017.
 * Class Bird trong game
 */

public class Bird {
    private final String TAG=getClass().getSimpleName();
    private static final int GRAVITY=-20; // độ rơi của con chim
    private static final int MOVEMENT_SPEED=100; //Tốc độ bay của con chim
    private final Vector3 postion; // Tọa độ hiện tại của chim
    private final Vector3 velocity; //tọa độ lúc chim nhảy lên
    private final Texture animation_of_bird; //các animation của bird
    private final Rectangle rec; // hình chữ nhật bao bọc bên ngoài con chim và cột để xử lí đụng độ giữa
    //chim và cột
    private Animation birdAnimation; //tạo ra animation của chim
    public Bird(int x,int y)
    {
        //khởi tạo bird ở tọa độ x,y
        postion= new Vector3(x,y,0);
        velocity= new Vector3(0,0,0);
        animation_of_bird=new Texture("birdanimation.png"); //set image animation của chim
        birdAnimation= new Animation(new TextureRegion(animation_of_bird),3,.2f);// tạo biến animation cho chim
        //vẽ hình chữ nhật bao bọc bên ngoài con chim để xử lí đụng độ
        rec= new Rectangle(postion.x,postion.y,animation_of_bird.getWidth()/3,animation_of_bird.getHeight());
    }
    public Vector3 getPostion()
    {
        return this.postion;
    }
    public TextureRegion getTexture()
    {return this.birdAnimation.getFrame();}
    public Rectangle getRec()
    {return this.rec;}
    public void update(float deltaTime)
    {
        birdAnimation.update(deltaTime);
        //nếu chim chưa chết
        if(postion.y > 0)
        {
            velocity.add(0, GRAVITY,0); // gán lại vị trí cho chim khi đang bay
        }
        velocity.scl(deltaTime);
        postion.add(MOVEMENT_SPEED * deltaTime , velocity.y,0);
        if(postion.y<0)
        {
            //nếu con chim nó chết
            postion.y=0;
        }
        velocity.scl(1/deltaTime); //vị trí bay của chim bị thấp xuống dần theo thời gian
        rec.setPosition(postion.x,postion.y); //set lại vị trí hình chữ nhật cho chim

    }
    public void jump()
    {
        velocity.y=390;//độ nhảy của chim
    }
    public void dispose()
    {
        animation_of_bird.dispose(); //dispose animation lại
        Gdx.app.log(TAG, "disposed");
    }


}
