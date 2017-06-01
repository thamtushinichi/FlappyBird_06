package com.group6.flappybird.GameStates;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Created by TUAN on 5/31/2017.
 */

public class StateManager {
    private Stack<State>  states;
    public StateManager()
    {
        states= new Stack<State>();

    }
    public void push(State state)
    {
        states.push(state);
    }
    public void pop()
    {
        states.pop().dispose();
    }
    public void set(State state)
    {
        pop();
        push(state);
    }
    public void update(float deltaTime)
    {
        states.peek().update(deltaTime);
    }
    public void render(SpriteBatch batch){
        states.peek().render(batch);
    }
}
