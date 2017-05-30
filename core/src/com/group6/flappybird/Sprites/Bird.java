package com.group6.flappybird.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by TUAN on 5/31/2017.
 */

public class Bird {
    private final String TAG=getClass().getSimpleName();
    private static final int GRAVITY=-25; // độ rơi của con chim
    private static final int MOVEMENT_SPEED=120; //Tốc độ bay của con chim
    private final Vector3 postion; // Tọa độ hiện tại của chim
    private final Vector3 velocity; //tọa độ lúc chim nhảy lên
    private final Texture animation_of_bird; //các animation của bird
    private final Rectangle rec;
    private Animation birdAnimation;
    public Bird(int x,int y)
    {
        //khởi tạo bird ở tọa độ x,y
        postion= new Vector3(x,y,0);
        velocity= new Vector3(0,0,0);
        animation_of_bird=new Texture("birdanimation.png");
        birdAnimation= new Animation(new TextureRegion(animation_of_bird),3,.5f);
        rec= new Rectangle(postion.x,postion.y,animation_of_bird.getWidth(),animation_of_bird.getHeight()/3);

    }
    public Vector3 getPostion()
    {
        return this.postion;
    }
    public TextureRegion getTexture()
    {return this.birdAnimation.getFrame();}
    public Rectangle getRec()
    {return this.rec;}



}
