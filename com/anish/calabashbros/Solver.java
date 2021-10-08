package com.anish.calabashbros;

import java.util.List;

public interface Solver {
    public void load(int [][]matrix);
    public void solve();
    public List<Node>getPath();
}
