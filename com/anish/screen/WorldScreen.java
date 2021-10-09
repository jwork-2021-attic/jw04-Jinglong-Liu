package com.anish.screen;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.Random;

import com.anish.calabashbros.SelectSorter;
import com.anish.calabashbros.QuickSorter;
import com.anish.calabashbros.Sorter;
import com.anish.calabashbros.Monster;
import com.anish.calabashbros.World;

import asciiPanel.AsciiPanel;
import com.anish.calabashbros.ColorReader;
public class WorldScreen implements Screen {
    private World world;
    //private Calabash[] bros;
    private Monster[][]monsters;
    String[] sortSteps;

    public WorldScreen() {
        world = new World();
        initMonster();
        Sorter<Monster> b = new QuickSorter<>();
        b.load(monsters);
        b.sort();

        sortSteps = this.parsePlan(b.getPlan());
        System.out.println("press any key to start...");
    }
    private void initMonster(){
        int [][][] colors = ColorReader.getColor(); 
        monsters = new Monster[ColorReader.row][ColorReader.col];
        for(int i = 0;i < ColorReader.row;i++){
            for(int j = 0;j<ColorReader.col;j++){
                Color color = new Color(colors[i][j][0],colors[i][j][1],colors[i][j][2]);
                monsters[i][j] = new Monster(color, i *ColorReader.row + j, world);
            }
        }
        
        Random r = new Random();
        for(int i = 0;i<1000;i++){
            int x0 = r.nextInt(ColorReader.row);
            int x1 = r.nextInt(ColorReader.row);
            int y0 = r.nextInt(ColorReader.col);
            int y1 = r.nextInt(ColorReader.col);
            Monster m = monsters[x0][y0];
            monsters[x0][y0] = monsters[x1][y1];
            monsters[x1][y1] = m;
        }
        for(int i = 0;i < ColorReader.row;i++){
            for(int j = 0;j<ColorReader.col;j++){
                world.put(monsters[i][j],2*j+5, 2*i + 5);
            }
        }
    }
    private String[] parsePlan(String plan) {
        return plan.split("\n");
    }

    private void execute(Monster[][] monsters, String step) {
        String[] couple = step.split("<->");
        getMosByRank(monsters, Integer.parseInt(couple[0])).swap(getMosByRank(monsters,Integer.parseInt(couple[1])));
    }
    private Monster getMosByRank(Monster[][] monsters, int rank) {
        for(int i = 0;i < monsters.length;i++){
            for(int j = 0;j < monsters[0].length;j++){
                if(monsters[i][j].getRank() == rank){
                    return monsters[i][j];
                }
            }
        }
        return null;
    }

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
        

        //if (i < this.sortSteps.length) {
            //this.execute(bros, sortSteps[i]);
        //    this.execute(monsters, sortSteps[i]);
        //    i++;
        //}
        new Thread(new Runnable(){
            @Override
            public void run() {
                // TODO Auto-generated method stub
                while(true){
                    try{
                        doNextStep();
                        Thread.sleep(50);
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        
        return this;
    }
    private void doNextStep(){
        if (i < this.sortSteps.length) {
            //this.execute(bros, sortSteps[i]);
            this.execute(monsters, sortSteps[i]);
            i++;
        }
    }
}
