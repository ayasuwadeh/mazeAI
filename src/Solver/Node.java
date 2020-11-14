/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Solver;

/**
 *
 * @author citycomp
 */
public class Node {
    private int x;
    private int y;
    private boolean visitedN;
    int max=5;      //for testing 
    int hn=0;       //h(n) heuristic fun       
    int gn=0;       //g(n) cost fun

    public boolean isVisitedN() {
        return visitedN;
    }

    public void setVisitedN(boolean visitedN) {
        this.visitedN = visitedN;
    }
    int fn=0;       //h+g
    private Node parent;
    private int s,n,e,w;//children

    public Node(int x, int y, Node parent) 
    {
        this.parent = parent;
        setX(x);
        setY(y);
    }
    public Node() {
    }

    public Node(int x, int y) 
    {
        setX(x);
        setY(y);

        
    }

    
    public void setGn(int gn) 
    {
        this.gn = gn;
        this.setFn(gn, hn);
    }
    
    public void setHn(int h) {
        this.hn = h;
        this.setFn(this.gn,this.hn);
    }
    
    public void setFn(int gn,int hn) {
        this.fn = gn+hn;
    }

    public int getHn() {
        
        return hn;
    }


    public int getGn() {
        return gn;
    }

    public int getFn() {
        return fn;
    }
    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) 
    {
        this.parent = parent;
    }
    
    public void setS(int s) {
        this.s = s;
    }
    public void setN(int n) {
        this.n = n;
    }

    public void setE(int e) {
        this.e = e;
    }

    public void setW(int w) {
        this.w = w;
    }
    public int getS() {
        return s;
    }

    public int getN() {
        return n;
    }

    public int getE() {
        return e;
    }

    public int getW() {
        return w;
    }

    public void setX(int x)  //based on the x value --> set childrens value east and weast(checking just for the outer borders ) 
    {
        this.x = x;
        if (this.x==0)
            setW(0);
        else 
            setW(1);
        
        if(this.x==max)
            setE(0);
        else
            setE(1);
        
    }


    public void setY(int y) { //based on the y value --> set childrens value south and north (checking just for the outer borders )

        this.y = y;
        if (this.y==0)
            setN(0);
        else 
            setN(1);
        
        if(this.y==max)
            setS(0);
        else
            setS(1);  
    }

   

    public int getX() {
        return x;
    }

    

    public int getY() {
        return y;
    }
}


