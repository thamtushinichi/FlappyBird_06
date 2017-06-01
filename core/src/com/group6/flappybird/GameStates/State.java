package com.group6.flappybird.GameStates;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;

import static com.group6.flappybird.MainGame.HEIGHT;
import static com.group6.flappybird.MainGame.WIDTH;

/**
 * Created by TUAN on 5/31/2017.
 */

public abstract class State {
    private final FitViewport viewPort;
    protected OrthographicCamera camera;
    protected Vector3 mouse;
    protected StateManager manager;
    public State(StateManager sm)
    {
        this.manager=sm;
        camera= new OrthographicCamera();

        this.viewPort= new FitViewport(WIDTH,HEIGHT,camera);
        camera.position.set(viewPort.getWorldWidth()/2,viewPort.getWorldHeight()/2,0);
        mouse= new Vector3();
    }
    protected abstract void handleInput();
    public abstract void update(float dt);
    public abstract void render(SpriteBatch batch);
    public abstract void dispose();
}
