package com.anish.screen;

import java.awt.Color;
import java.awt.event.KeyEvent;

import com.anish.calabashbros.Calabash;
import com.anish.calabashbros.World;

import asciiPanel.AsciiPanel;
import mazeGenerator.MazeGenerator;
import com.anish.calabashbros.Wall;
import com.anish.calabashbros.Floor;
public class WorldScreen implements Screen {

    private World world;
    //private Calabash[] bros;
    String[] sortSteps;
    private Calabash guard;
    private MazeGenerator mazeGenerator;
    private int [][]maze;
    public WorldScreen() {
        world = new World();

        //bros = new Calabash[7];

        //bros[3] = new Calabash(new Color(204, 0, 0), 1, world);
        //bros[5] = new Calabash(new Color(255, 165, 0), 2, world);
        //bros[1] = new Calabash(new Color(252, 233, 79), 3, world);
        //bros[0] = new Calabash(new Color(78, 154, 6), 4, world);
        //bros[4] = new Calabash(new Color(50, 175, 255), 5, world);
        //bros[6] = new Calabash(new Color(114, 159, 207), 6, world);
        //bros[2] = new Calabash(new Color(173, 127, 168), 7, world);

        //world.put(bros[0], 10, 10);
        //world.put(bros[1], 12, 10);
        //world.put(bros[2], 14, 10);
        //world.put(bros[3], 16, 10);
        //world.put(bros[4], 18, 10);
        //world.put(bros[5], 20, 10);
        //world.put(bros[6], 22, 10);

        guard = new Calabash(new Color(204,0,0), 1, world);
        
        this.initMaze();
        world.put(guard,0,0);
    }
    private void initMaze(){
        mazeGenerator = new MazeGenerator(30);
        mazeGenerator.generateMaze();
        maze = mazeGenerator.getMaze();
        for(int i = 0;i < World.HEIGHT;i++){
            for(int j = 0;j<World.WIDTH;j++){
                if(maze[i][j] == 0){
                    world.put(new Wall(world),i,j);
                }
            }
        }
    }
/*
    private String[] parsePlan(String plan) {
        return plan.split("\n");
    }

    private void execute(Calabash[] bros, String step) {
        String[] couple = step.split("<->");
        getBroByRank(bros, Integer.parseInt(couple[0])).swap(getBroByRank(bros, Integer.parseInt(couple[1])));
    }

    private Calabash getBroByRank(Calabash[] bros, int rank) {
        for (Calabash bro : bros) {
            if (bro.getRank() == rank) {
                return bro;
            }
        }
        return null;
    }
*/
    @Override
    public void displayOutput(AsciiPanel terminal) {

        for (int x = 0; x < World.WIDTH; x++) {
            for (int y = 0; y < World.HEIGHT; y++) {

                terminal.write(world.get(x, y).getGlyph(), x, y, world.get(x, y).getColor());

            }
        }
    }

    int i = 0;

    @Override
    public Screen respondToUserInput(KeyEvent key) {
        //
        
        int x = guard.getX();
        int y = guard.getY();   
        switch (key.getKeyCode()){
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                y++;
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                y--;
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                x--;
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                x++;
                break;
        }
        if(isValid(x, y)){
            world.put(new Floor(world),guard.getX(),guard.getY());
            guard.moveTo(x, y);
            //System.out.println(x + " " + y);
        }
        
        return this;
    }
    private Boolean isValid(int x,int y){
        if(x < 0 || y < 0 || x >= world.WIDTH || y >= world.HEIGHT){
            return false;
        }
        return maze[x][y] == 1;
    }

}
