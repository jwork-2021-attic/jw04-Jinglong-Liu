package com.anish.screen;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.Random;

import com.anish.calabashbros.BubbleSorter;
import com.anish.calabashbros.Calabash;
import com.anish.calabashbros.Monster;
import com.anish.calabashbros.World;

import asciiPanel.AsciiPanel;
import com.anish.calabashbros.ColorReader;
public class WorldScreen implements Screen {
    private World world;
    private Calabash[] bros;
    private Monster[][]monsters;
    String[] sortSteps;

    public WorldScreen() {
        world = new World();
        initMonster();
        /** 
        bros = new Calabash[7];

        bros[3] = new Calabash(new Color(204, 0, 0), 1, world);
        bros[5] = new Calabash(new Color(255, 165, 0), 2, world);
        bros[1] = new Calabash(new Color(252, 233, 79), 3, world);
        bros[0] = new Calabash(new Color(78, 154, 6), 4, world);
        bros[4] = new Calabash(new Color(50, 175, 255), 5, world);
        bros[6] = new Calabash(new Color(114, 159, 207), 6, world);
        bros[2] = new Calabash(new Color(173, 127, 168), 7, world);

        world.put(bros[0], 10, 10);
        world.put(bros[1], 12, 10);
        world.put(bros[2], 14, 10);
        world.put(bros[3], 16, 10);
        world.put(bros[4], 18, 10);
        world.put(bros[5], 20, 10);
        world.put(bros[6], 22, 10);
*/
        BubbleSorter<Monster> b = new BubbleSorter<>();
        b.load(monsters);
        b.sort();

        sortSteps = this.parsePlan(b.getPlan());
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
                world.put(monsters[i][j],2*j+10, i + 10);
            }
        }
    }
    private String[] parsePlan(String plan) {
        return plan.split("\n");
    }

    private void execute(Calabash[] bros, String step) {
        String[] couple = step.split("<->");
        getBroByRank(bros, Integer.parseInt(couple[0])).swap(getBroByRank(bros, Integer.parseInt(couple[1])));
    }
    private void execute(Monster[][] monsters, String step) {
        String[] couple = step.split("<->");
        getBroByRank(bros, Integer.parseInt(couple[0])).swap(getBroByRank(bros, Integer.parseInt(couple[1])));
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
    private Calabash getBroByRank(Calabash[] bros, int rank) {
        for (Calabash bro : bros) {
            if (bro.getRank() == rank) {
                return bro;
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

        if (i < this.sortSteps.length) {
            this.execute(bros, sortSteps[i]);
            i++;
        }

        return this;
    }

}
