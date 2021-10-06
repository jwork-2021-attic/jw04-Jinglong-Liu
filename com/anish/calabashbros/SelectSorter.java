package com.anish.calabashbros;

public class SelectSorter<T extends Comparable<T>> implements Sorter<T>  {
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
    public void sort(){
        for(int i = 0;i<a.length;i++){
            for(int j = 0;j < a[0].length;j++){

                int min_i = i;
                int min_j = j;
                
                for(int j1 = j + 1;j1<a[0].length;j1++){
                    if(a[i][min_j].compareTo(a[i][j1]) > 0){
                        min_j = j1;
                    }
                }

                for(int i1 = i + 1;i1<a.length;i1++){
                    for(int j1 = 0;j1 < a[i].length;j1++){
                        if(a[min_i][min_j].compareTo(a[i1][j1]) > 0){

                            min_i = i1;
                            min_j = j1;
                        }
                    }
                }
                if(min_i != i || min_j != j){
                    swap(i,j,min_i,min_j);
                }
            }
        }
    }

    @Override
    public String getPlan() {
        return this.plan;
    }
}
