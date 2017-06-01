package com.group6.flappybird.Sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;


/**
 * Created by TUAN on 5/31/2017.
 */

public class Animation {
    private Array<TextureRegion> frames;// mảng các khung hình
    private float maxFrameTime;
    private float currentFrameTime;
    private int frameCount; //tổng số lượng hình của file birdanimation.png
    private int frame; // chỉ số của frame trong mảng frames
    public Animation(TextureRegion textureRegion,  int frameCount, float cycleTime)
    {
        //dùng để chia hình
        //cycleTime là thời gian mỗi frame chạy
        frames= new Array<TextureRegion>();
        int frameWidth= textureRegion.getRegionWidth()/frameCount ;

        for(int i=0;i<frameCount;i++)
        {
            frames.add(new TextureRegion(textureRegion,i*frameWidth,0,frameWidth,textureRegion.getRegionHeight()));
        }
        this.frameCount=frameCount;
        maxFrameTime=cycleTime/frameCount;
        frame=0;
    }
    public void update(float deltaTime)
    {
        currentFrameTime=currentFrameTime+deltaTime;
        if(currentFrameTime> maxFrameTime)
        {
            frame++;
            currentFrameTime=0;
        }
        if(frame>= frameCount)
        {
            frame=0;
        }
    }
    public TextureRegion getFrame()
    {
        return frames.get(frame);
    }


}
