package com.group6.flappybird;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.group6.flappybird.MainGame;
/*
 * Create by Tuan: 17/6/2017
 * Class đầu tiên khi chạy chương trình
 */
public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// hàm chính để chạy chương trình
		// chương trình bắt đầu chạy sẽ chạy lớp này đầu tiên
		//khởi tạo chuong trình
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		//khởi tạo đối tượng chạy là MainGame() với config được thiết lập mặc định như trên
		initialize(new MainGame(), config);
	}
}
