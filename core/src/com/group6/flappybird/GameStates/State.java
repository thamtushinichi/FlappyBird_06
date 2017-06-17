package com.group6.flappybird.GameStates;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;

import static com.group6.flappybird.MainGame.HEIGHT;
import static com.group6.flappybird.MainGame.WIDTH;

/**
 * Created by TUAN on 5/31/2017.
 * class State là lớp trừu tượng , không có thật dùng để cho các trạng thái khác kế thừa các phương thức lại
 */

public abstract class State {

    private final FitViewport viewPort; // Khung hình của màn hình điện thoại (giống khung hình chữ nhật)
    protected OrthographicCamera camera; // là dùng để khởi tạo cho view port
    //viewPort là cái khung, còn camera là cái ruột bên trong
    protected Vector3 mouse; // tọa độ điểm chạm (x,y,z)
    protected StateManager manager; // quản lý danh sách các trạng thái
    //hàm khởi tạo mặc định
    public State(StateManager sm)
    {
        this.manager=sm;
        camera= new OrthographicCamera();

        this.viewPort= new FitViewport(WIDTH,HEIGHT,camera);
        camera.position.set(viewPort.getWorldWidth()/2,viewPort.getWorldHeight()/2,0);
        mouse= new Vector3();
    }
    //xử lí đầu vào
    protected abstract void handleInput();
    //update lại màn hình sau 1 khoảng thời gian
    public abstract void update(float dt);
    //hiển thị màn hình
    public abstract void render(SpriteBatch batch);
    //tắt màn hình
    public abstract void dispose();
}
