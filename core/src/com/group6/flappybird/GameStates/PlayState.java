package com.group6.flappybird.GameStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.group6.flappybird.Sprites.Bird;
import com.group6.flappybird.Sprites.Tube;
import com.sun.org.apache.xpath.internal.operations.String;


import org.w3c.dom.Text;

import static com.group6.flappybird.MainGame.HEIGHT;
import static com.group6.flappybird.MainGame.WIDTH;
import static com.group6.flappybird.Sprites.Tube.TUBE_WIDTH;

/**
 * Created by TUAN on 5/31/2017.
 * Trạng thái chơi game, khi người dùng nhấn nút Play sẽ hiển thị trạng thái này
 */

public class PlayState extends State{

    private final Bird bird; // mean: con chim trong game
    private final Texture background;//mean: hình nền trong game
    private  final Texture ground;//mặt đất
    private final Vector2 groundPos1;//vị trí bắt đầu của mặt đất
    private  final Vector2 groundPos2; //vị trí kết thúc của mặt đất
    public static final int TUBE_COUNT=2; // biến khởi tạo ngẫu nhiên 2 cột trước, mỗi khi con chim đi
    //qua 2 cột sẽ khởi tạo ngẫu nhiên 2 cột khác
    public static final int DISTANCE_2_TUBE=110; //khoảng cách 2 ống tube (bề ngang)
    private Array<Tube> tubes; //Mảng lưu trữ các ống trong game
    public static final int GROUND_Y_OFFSET = -50; // khoảng cách
    private int score=0; //điểm sô
    private Text textScore; //tính điểm
    private CharSequence str=""; //biến dùng để hiển thị điểm lên màn hình
    private BitmapFont font ; //biến dùng để hiển thị điểm lên màn hình
    float temp=0; //test
    private int count=1;  //biến dùng để hiển thị điểm lên màn hình
    public PlayState(StateManager sm)

    {
        super(sm);
        camera.setToOrtho(false,WIDTH/2,HEIGHT/2); //gán giá trị cho camera , lấy tọa độ điểm giữa
        //làm chuẩn trong ví dụ này là 480/2 = 240 và 800/2=400
        //tọa độ (240,400) là tọa độ của camera
        bird= new Bird(30,200); //khởi tạo vị trí chim ban đầu tại vị trí (30,200)
        background= new Texture("background.png"); //set image background
        ground= new Texture("ground.png"); //set image ground
        //camera.position.x là tọa độ thực tế dao động biến thiên tăng dần khi cảnh game di chuyển
        //tăng từ từ , được tính theo đơn vị float ( số thực)
        //camera.viewportWidth để lấy giá trị nguyển của khung hình = 480
        //set tọa độ cho bắt đầu của mặt đất
        groundPos1 = new Vector2(camera.position.x - (camera.viewportWidth / 2), GROUND_Y_OFFSET);
        //set tọa độ kết thúc của mặt đất
        groundPos2 = new Vector2((camera.position.x - (camera.viewportWidth / 2)) + ground.getWidth(), GROUND_Y_OFFSET);

        tubes= new Array<Tube>(); //khởi tạo mảng tube
        for(int i=1;i<=TUBE_COUNT;i++)
        {
            tubes.add(new Tube(i * (DISTANCE_2_TUBE + TUBE_WIDTH))); //tạo 1 tube rồi thêm vào mảng
        }
        font= new BitmapFont(); //dùng để render điểm số
        font.setColor(Color.NAVY); //set color cho điểm số
    }
    @Override
    protected void handleInput() {
        //người dùng nhấn vào màn hình khi trong trạng thái đang chơi game
        //nếu người dùng thoát khỏi trò chợi bằng mấy cái nút "Back"  của smartphone
        //thì gán trạng thái của game thành
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {

            manager.set(new MenuState(manager));
            return;
        }
        //nếu người dùng nhấn vào màn hình để cho chim nhảy
        if (Gdx.input.justTouched() || Gdx.input.isKeyJustPressed(Input.Keys.J))
            bird.jump();
    }

    @Override
    public void update(float dt) {
        //update lại thông tin sau 1 khoảng thời gian dt
        handleInput(); //xử lí khi chạm màn hình
        updateGround(); //upadte lại mặt đất, cho chạy lại mặt đất 1 lần nữa
        bird.update(dt); //update lại tọa độ chim
        camera.position.x=bird.getPostion().x+70;// quãng đường đi được của chim
        for(int i=0;i<tubes.size;i++)
        {
            //lấy tube
            Tube tube= tubes.get(i);
            //
            if(camera.position.x - (camera.viewportWidth/2) > (tube.getPos_Top_Tube().x + tube.getTop_Tube().getWidth()-82)
                    && tube.getPos_Top_Tube().x>temp)
            {
                //con so -82 nay do demo ma tu tinh len duoc, trừ đi 82 để chim bay ở giữa xem như tính 1 điểm
                //chim không cần bay qua cái tube mới được tính mà bay đến giữa là tính rồi
                score++;
                temp=tube.getPos_Top_Tube().x;
                str= Integer.toString(score);
               // System.out.println("aa"+score);
               // System.out.println("bb"+str);
               // System.out.println("cc"+tube.getPos_Top_Tube().x);

            }
            if(camera.position.x - (camera.viewportWidth/2) > tube.getPos_Top_Tube().x + tube.getTop_Tube().getWidth())
            {
                //nếu con chim vượt qua được 1 cái tube
                //set lại vị trí cho tube
                tube.re_Postion(tube.getPos_Top_Tube().x+ ((TUBE_WIDTH + DISTANCE_2_TUBE) * TUBE_COUNT));


            }

            if(tube.isBirdDie(bird.getRec()) || bird.getPostion().y<=ground.getHeight()+ GROUND_Y_OFFSET)
            {
                //nếu chim chết do đụng trúng cột hoặc rơi xuống đất
                //nếu có tính điểm thì update hàm tính điểm tại đây, kiểm tra lúc chim ko chết

                //redirect về lại menu state
                score=0;
                str="0";
                temp=0;
                count=1;
                manager.set(new MenuState(manager)); //set lại trạng thái là menu state

                return; //lúc này chim đã chết
            }

        }

        camera.update(); //validate lại màn hình

    }
    //update lại mặt đất
     private void updateGround() {
         //nếu như tọa độ của camera vượt qua khỏi tọa độ của bắt đầu của mặt đất thì
         // gán lại tọa độ mặt đất cho điểm mới bắt đầu
        if (camera.position.x - (camera.viewportWidth / 2) > groundPos1.x + ground.getWidth())
            groundPos1.add(ground.getWidth() * 2, 0);
        //nếu như tọa độ của camera vượt qua khỏi tọa độ của kết thúc của mặt đất thì
         // gán lại tọa độ mặt đất cho điểm sắp kết thúc
        if (camera.position.x - (camera.viewportWidth / 2) > groundPos2.x + ground.getWidth())
            groundPos2.add(ground.getWidth() * 2, 0);
    }
    @Override
    public void render(SpriteBatch batch) {
        //hiển thị tất cả hình ảnh lên màn hình
        //thực tế hàm này chạy rất nhiều lần
        batch.setProjectionMatrix(camera.combined);// hàm dùng để vẽ lại màn hình

        batch.begin();
        //vẽ lại background
        batch.draw(background,camera.position.x-(camera.viewportWidth/2),0);
        //hiển thị chim
        batch.draw(bird.getTexture(),bird.getPostion().x,bird.getPostion().y);

        //hiển thị tube
        for(int i =0;i<tubes.size;i++)
        {
            batch.draw(tubes.get(i).getTop_Tube(),tubes.get(i).getPos_Top_Tube().x,tubes.get(i).getPos_Top_Tube().y);
            batch.draw(tubes.get(i).getBottom_Tube(),tubes.get(i).getPos_Bottom_Tube().x,tubes.get(i).getPos_Bottom_Tube().y);
            font.draw(batch,str,tubes.get(i).getPos_Top_Tube().x+21,tubes.get(i).getPos_Top_Tube().y+18);
        }

        //hiển thị mặt đất
        batch.draw(ground, groundPos1.x, groundPos1.y);
        batch.draw(ground, groundPos2.x, groundPos2.y);

        batch.end();

    }

    @Override
    public void dispose() {
        //tạm ngưng hoạt động của tất cả các đối tượng trong game
        background.dispose(); //tạm ngưng hình nền
        ground.dispose(); //tạm ngưng mặt đất
        for(int i=0;i<tubes.size;i++)
        {
            tubes.get(i).dispose(); //tạm ngưng cột
        }
        bird.dispose(); //tạm ngưng chim
        font.dispose(); //tạm ngưng điểm số
    }
    public void displayCurrentScore(int score) {
        //hiển thị điểm số lên màn hình
        textScore.setTextContent(""+score);
    }
}
