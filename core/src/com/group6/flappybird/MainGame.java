package com.group6.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.group6.flappybird.GameStates.MenuState;
import com.group6.flappybird.GameStates.StateManager;

public class MainGame extends Game {
	public static final int WIDTH =480;
	public static final int HEIGHT = 800;
	public static final String title="Flappy Bird group 6";

	private SpriteBatch batch;
	private StateManager sm;

	@Override
	public void create () {
		batch= new SpriteBatch();
		sm= new StateManager();

		Gdx.gl.glClearColor(0, .2f, 0, 1);
		sm.push(new MenuState(sm));

	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		sm.update(Gdx.graphics.getDeltaTime());
		sm.render(batch);
	}
	
	@Override
	public void dispose () {
		super.dispose();
	}
}
