package com.group6.flappybird.GameStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static com.group6.flappybird.MainGame.HEIGHT;
import static com.group6.flappybird.MainGame.WIDTH;

/**
 * Created by TUAN on 5/31/2017.
 */

public class MenuState extends State {
    //trong menu thì có background và nút play
    private final Texture background;
    private final Texture btnPlay;
    public MenuState(StateManager sm)
    {
        super(sm);
        camera.setToOrtho(false,WIDTH/2, HEIGHT/2);
        background= new Texture("background.png");
        btnPlay= new Texture("playbtn.png");

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
        camera.update();
        handleInput();
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(background, 0, 0);
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
