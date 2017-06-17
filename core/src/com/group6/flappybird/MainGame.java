package com.group6.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.group6.flappybird.GameStates.MenuState;
import com.group6.flappybird.GameStates.StateManager;
/*
 * Create on by Tuan: 17/6/2017
 * Class main chính để thực thi chương trình
 */
public class MainGame extends Game {
	public static final int WIDTH =480; // độ rộng của màn hình smartphone
	public static final int HEIGHT = 800; //độ cao của màn hình smartphone
	public static final String title="Flappy Bird group 6"; // title game

	private SpriteBatch batch; //biến để hiển thị lên màn hình
	private StateManager sm; // quản lý các trạng thái của game

	@Override
	public void create () {
		batch= new SpriteBatch(); //khởi tạo lớp SpriteBatch để vẽ
		sm= new StateManager(); // khởi tạo đê quản lý danh sách các trạng thái của State

		Gdx.gl.glClearColor(0, .2f, 0, 1);
		sm.push(new MenuState(sm)); // vừa mới vào nên để ở trạng thái menu State

	}

	@Override
	public void render () {
		//hiển thị lên màn hình
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		sm.update(Gdx.graphics.getDeltaTime());
		sm.render(batch);
	}
	
	@Override
	public void dispose () {
		super.dispose();
	}
}
