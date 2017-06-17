package com.group6.flappybird.GameStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static com.group6.flappybird.MainGame.HEIGHT;
import static com.group6.flappybird.MainGame.WIDTH;

/**
 * Created by TUAN on 5/31/2017.
 * class này dùng để khởi tạo trạng thái của menu
 */

public class MenuState extends State {
    //trong menu thì có background và nút play
    private final Texture background; //background
    private final Texture btnPlay; // nút play
    public MenuState(StateManager sm)
    {
        super(sm);
        camera.setToOrtho(false,WIDTH/2, HEIGHT/2); //gán giá trị cho camera , lấy tọa độ điểm giữa
        //làm chuẩn trong ví dụ này là 480/2 = 240 và 800/2=400
        //tọa độ (240,400) là tọa độ của camera
        background= new Texture("background.png");//set image background
        btnPlay= new Texture("playbtn.png");//set image button "Play"

    }
    @Override
    protected void handleInput() {
    //bắt sự kiện khi tay nhấn vào màn hình smart phone
        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER) || Gdx.input.justTouched())
        {
            //tức là chỉ nhấn vào 1 lần và nhả ra 1 lần thì chạy code dưới đây
            manager.set(new PlayState(manager));
            return;
        }
        //nếu dùng để thoát
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))
            Gdx.app.exit();
    }

    @Override
    public void update(float dt) {
        camera.update(); //update lại camera
        handleInput(); // xử lí khi người dùng chạm vào màn hình menu state
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(camera.combined); // dùng để vẽ lên màn hình
        batch.begin();
        batch.draw(background, 0, 0); //vẽ hình nền
        //để canh nút button play ở chính giữa màn hình
        batch.draw(btnPlay, camera.position.x - (btnPlay.getWidth() / 2), camera.position.y  - (btnPlay.getHeight() / 2));
        batch.end();
    }

    @Override
    public void dispose() {
        //cho ẩn đi hết các nút và background
        background.dispose();
        btnPlay.dispose();
    }
}
