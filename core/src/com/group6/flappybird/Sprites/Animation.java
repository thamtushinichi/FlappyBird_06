package com.group6.flappybird.Sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;


/**
 * Created by TUAN on 5/31/2017.
 * Đây là class animation, dùng để tách ra 1 con chim từ 1 hình có 3 con chim có động tác khác nhau
 * cho hiển thị trong thời gian ngắn , như vậy người dùng nghĩ rằng con chim đó đang cử động
 */

public class Animation {
    private Array<TextureRegion> frames;// mảng các khung hình
    private float maxFrameTime; //số khung hình tối đa
    private float currentFrameTime; //số khung hình hiện tại
    private int frameCount; //tổng số lượng hình của file birdanimation.png
    private int frame; // chỉ số của frame trong mảng frames
    public Animation(TextureRegion textureRegion,  int frameCount, float cycleTime)
    {
        //dùng để chia hình
        //cycleTime là thời gian mỗi frame chạy
        // tham số truyền vào
        //1: biến để giữ image animation
        //2: số lượng hình để tạo thành animation
        //3: thời gian để các hình chạy thành animation
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
    //update lại animation sau 1 khoảng thời gian
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
