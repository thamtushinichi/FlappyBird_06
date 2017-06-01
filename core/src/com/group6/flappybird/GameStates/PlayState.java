package com.group6.flappybird.GameStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.group6.flappybird.Sprites.Bird;
import com.group6.flappybird.Sprites.Tube;


import static com.group6.flappybird.MainGame.HEIGHT;
import static com.group6.flappybird.MainGame.WIDTH;
import static com.group6.flappybird.Sprites.Tube.TUBE_WIDTH;

/**
 * Created by TUAN on 5/31/2017.
 */

public class PlayState extends State{

    private final Bird bird;
    private final Texture background;
    private  final Texture ground;//mặt đất
    private final Vector2 groundPos1;//vị trí bắt đầu của mặt đất
    private  final Vector2 groundPos2; //vị trí kết thúc của mặt đất
    public static final int TUBE_COUNT=4;
    public static final int DISTANCE_2_TUBE=110; //khoảng cách 2 ống tube (bề ngang)
    private Array<Tube> tubes;
    public static final int GROUND_Y_OFFSET = -50;
    int score=0;
    public PlayState(StateManager sm)

    {
        super(sm);
        camera.setToOrtho(false,WIDTH/2,HEIGHT/2);
        bird= new Bird(30,200);
        background= new Texture("background.png");
        ground= new Texture("ground.png");
        groundPos1 = new Vector2(camera.position.x - (camera.viewportWidth / 2), GROUND_Y_OFFSET);
        groundPos2 = new Vector2((camera.position.x - (camera.viewportWidth / 2)) + ground.getWidth(), GROUND_Y_OFFSET);

        tubes= new Array<Tube>();
        for(int i=1;i<=TUBE_COUNT;i++)
        {
            tubes.add(new Tube(i * (DISTANCE_2_TUBE + TUBE_WIDTH)));
        }
    }
    @Override
    protected void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            manager.set(new MenuState(manager));
            return;
        }

        if (Gdx.input.justTouched() || Gdx.input.isKeyJustPressed(Input.Keys.J))
            bird.jump();
    }

    @Override
    public void update(float dt) {
        handleInput();
        updateGround();
        bird.update(dt);
        camera.position.x=bird.getPostion().x+70;// quãng đường đi được của chim
        for(int i=0;i<tubes.size;i++)
        {
            Tube tube= tubes.get(i);
            if(camera.position.x - (camera.viewportWidth/2) > tube.getPos_Top_Tube().x + tube.getTop_Tube().getWidth())
            {
                //nếu con chim vượt qua được 1 cái tube
                tube.re_Postion(tube.getPos_Top_Tube().x+ ((TUBE_WIDTH + DISTANCE_2_TUBE) * TUBE_COUNT));
                score++;
                System.out.println(score);

            }

            if(tube.isBirdDie(bird.getRec()) || bird.getPostion().y<=ground.getHeight()+ GROUND_Y_OFFSET)
            {
                //nếu có tính điểm thì update hàm tính điểm tại đây, kiểm tra lúc chim ko chết

                //redirect về lại menu state

                manager.set(new MenuState(manager));
                return; //lúc này chim đã chết
            }

        }

        camera.update(); //validate lại màn hình

    }
     private void updateGround() {
        if (camera.position.x - (camera.viewportWidth / 2) > groundPos1.x + ground.getWidth())
            groundPos1.add(ground.getWidth() * 2, 0);

        if (camera.position.x - (camera.viewportWidth / 2) > groundPos2.x + ground.getWidth())
            groundPos2.add(ground.getWidth() * 2, 0);
    }
    @Override
    public void render(SpriteBatch batch) {
        //hiển thị tất cả hình ảnh lên màn hình
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(background,camera.position.x-(camera.viewportWidth/2),0);
        //hiển thị chim
        batch.draw(bird.getTexture(),bird.getPostion().x,bird.getPostion().y);

        //hiển thị tube
        for(int i =0;i<tubes.size;i++)
        {
            batch.draw(tubes.get(i).getTop_Tube(),tubes.get(i).getPos_Top_Tube().x,tubes.get(i).getPos_Top_Tube().y);
            batch.draw(tubes.get(i).getBottom_Tube(),tubes.get(i).getPos_Bottom_Tube().x,tubes.get(i).getPos_Bottom_Tube().y);
        }

        //hiển thị mặt đất
        batch.draw(ground, groundPos1.x, groundPos1.y);
        batch.draw(ground, groundPos2.x, groundPos2.y);

        batch.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        ground.dispose();
        for(int i=0;i<tubes.size;i++)
        {
            tubes.get(i).dispose();
        }
        bird.dispose();

    }
}
