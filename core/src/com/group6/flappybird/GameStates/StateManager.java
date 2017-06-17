package com.group6.flappybird.GameStates;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Created by TUAN on 5/31/2017.
 * Class dùng để quản lý các trạng thái của game
 * StateManager này hoạt động theo cơ chế khi có 1 state trong stack, trạng thái đó sẽ hoạt động cho
 * game .Ex: Hiển thị menu game hoặc chơi game. Tại 1 thời điểm cho cho phép 1 trạng thái có trong
 * stack, các trạng thái khác muốn thực hiện phải đợi trạng thái có trong stack kết thức mới thực
 * hiện được.
 */

public class StateManager {
    private Stack<State>  states; // Ngăn xếp để quản lý trạng thái
    public StateManager()
    {
        states= new Stack<State>();

    }
    //đưa 1 phần tử state vào ngăn xếp
    public void push(State state)
    {
        states.push(state);
    }
    //lấy 1 phần tử state ra khởi ngăn xếp
    public void pop()
    {
        states.pop().dispose();
    }
    //set State
    public void set(State state)
    {
        pop(); //lấy 1 phần tử ra khỏi Stack
        push(state); //rồi sau đó mới thêm mới vào
    }
    //update lại các trạng thái có trong ngăn xếp
    // lấy giá trị của phần tử đầu tiên rồi cập nhập lại
    public void update(float deltaTime)
    {
        states.peek().update(deltaTime);
    }
    //lấy trạng thái đang tồn tại trong stack , hiển thị ra màn hình
    public void render(SpriteBatch batch){
        states.peek().render(batch);
    }
}
