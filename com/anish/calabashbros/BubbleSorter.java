package com.anish.calabashbros;

public class BubbleSorter<T extends Comparable<T>> implements Sorter<T> {

    private T[][] a;

    @Override
    public void load(T[][] a) {
        this.a = a;
    }

    private void swap(int x0, int y0,int x1,int y1) {
        T temp;
        temp = a[x0][y0];
        a[x0][y0] = a[x1][y1];
        a[x1][y1] = temp;
        plan += "" + a[x0][y0] + "<->" + a[x1][y1] + "\n";
    }

    private String plan = "";

    @Override
    public void sort() {
        boolean sorted = false;
        while (!sorted) {
            sorted = true;
            for (int i = 0; i < a.length; i++) {
                for(int j = 0;j < a[i].length - 1;j++){
                    if (a[i][j].compareTo(a[i][j+1]) > 0){
                        swap(i,j,i,j + 1);
                        sorted = false;
                    }
                }
                int j = a[i].length - 1;
                if (i < a.length - 1){
                    if(a[i][j].compareTo(a[i+1][0]) > 0){
                        swap(i,j,i+1,0);
                        sorted = false;
                    }
                }
            }
        }
    }

    @Override
    public String getPlan() {
        return this.plan;
    }

}