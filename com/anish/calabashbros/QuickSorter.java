package com.anish.calabashbros;

public class QuickSorter<T extends Comparable<T>> implements Sorter<T>  {
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
    private void swap(int i,int j){
        int x0 = i / a.length;
        int y0 = i % a.length;
        int x1 = j / a.length;
        int y1 = j % a.length;
        swap(x0,y0,x1,y1);
    }
    private String plan = "";
    
    public void sort(){
        quickSort(0, a.length * a[0].length - 1);
    }
    
    public void quickSort(int start,int end) {    
        T pivot = a[start / a.length][start % a.length];

        int i = start;        
        int j = end;        
        //System.out.println(i + " " + j);
        while (i<j) {       
            while ((i<j)&&(a[j/ a.length][j% a.length].compareTo(pivot) > 0)) {                
                j--;            
            }            
            while ((i<j)&&(a[i/ a.length][i% a.length].compareTo(pivot) < 0)) {                
                i++;            
            }            
            if ((a[i / a.length][i % a.length] == a[j/ a.length][j% a.length])&&(i<j)) {                
                i++;            
            } else {           
                swap(i,j);         
            }        
        }        
        if (i-1>start){
            quickSort(start,i-1);  
        }       
        if (j+1<end){
            quickSort(j+1,end);   
        }        
    }

    @Override
    public String getPlan() {
        return plan;
    }  
}
