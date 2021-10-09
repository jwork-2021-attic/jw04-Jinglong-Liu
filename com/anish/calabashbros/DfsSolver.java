package com.anish.calabashbros;

import java.util.ArrayList;
import java.util.List;

public class DfsSolver implements Solver{
    int [][]matrix;
    @Override
    public void load(int[][] matrix) {
        this.matrix = matrix;
    }
    @Override
    public void solve() {
        dfs(0, 0,0,0);
    }
    @Override
    public List<Node> getPath() {
        return path;
    }
    private Boolean dfs(int xPos,int yPos,int px,int py){
        if(end){
            return true;
        }
        path.add(new Node(xPos,yPos));
        
        if(isEnd(xPos, yPos)){
            end = true;
            return true;
        }
        for(int i = 0;i < 4;i++){
            int x = xPos + dx[i];
            int y = yPos + dy[i];
            if(x == px && y == py){
                continue;
            }
            if(isValid(x, y)){
                if(dfs(x,y,xPos,yPos)){
                    return true;
                }
                path.add(new Node(xPos,yPos));
            }
            
        }
        return false;
    }
    private Boolean isValid(int xPos,int yPos){
        return isValid(xPos, yPos,1);
    }
    private Boolean isValid(int xPos,int yPos,int valid){
        if(xPos < 0 || yPos < 0 || xPos >= matrix.length || yPos >= matrix[0].length){
            return false;
        }
        return matrix[xPos][yPos] == valid;
    }
    private Boolean isEnd(int xPos,int yPos){
        return (xPos == matrix.length - 1 )  && (yPos == matrix[0].length - 1);
    }
    private List<Node>path = new ArrayList<>();
    private final int []dx = {0,1,0,-1};
    private final int []dy = {1,0,-1,0};
    Boolean end = false;
}
