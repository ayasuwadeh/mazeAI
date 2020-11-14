/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Solver;

import java.lang.Math;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author citycomp
 */
public class Solver {
    
    Node f=new Node(1,1,null); //first state 
    Node g=new Node(50,50);      //goal state   
    Node bst=new Node();

    List <Node> openL=new ArrayList<Node>();
    List <Node> closedL=new ArrayList<Node>();
    List <Node> solution=new ArrayList<Node>();

    public Solver(Node f,Node g) 
    {
        this.f=f;
        this.g=g;
        A_star();
        System.out.println("const");
    }

    /*this function for calculating manhatance distance which is the heuristic function*/
public static int m_distance(Node w,Node g)
{
    int x0=w.getX();
    int x1=g.getX();
    int y0=w.getY();
    int y1=g.getY();
    int distance;
    distance=Math.abs(x1-x0)+ Math.abs(y1-y0);
    return distance;
}

/*function for tie breaker according to the least h function first
, if more than one have the same h we put them in another arraylist and return the first  element of it*/
public static Node tie_breaker(List <Node> temp)
{
    List <Node> tL=new ArrayList<Node>();
    Node t=new Node();
    t=temp.get(0);
    
    for(int i=1;i<temp.size();i++)
    {
     if(temp.get(i).getHn()<t.getHn())
     {t=temp.get(i);
     }
     
    }
    
    for(int i=1;i<temp.size();i++)
    {
     if(temp.get(i).getHn()==t.getHn())
     {tL.add(temp.get(i));
     }
     
    }

    
    return tL.get(0);
}

/* a function to check if any two given nodes are the same according to the x and 
y values we use it in the code to check if we reached the goal or not*/
public static boolean equal_nodes(Node f,Node g)
{
    boolean r;
    if((f.getX()==g.getX())&&(f.getY()==g.getY()))
        return true;
    else 
        return false;
}

/* checking if a node is in the open or closed list */
public boolean contain_in(Node n)
{   boolean in=false;
    
for(int i=0;i<openL.size();i++)
{
        if((n.getX()==openL.get(i).getX())&&(n.getY()==openL.get(i).getY()))
          return true;
}

for(int i=0;i<closedL.size();i++)
{
        if((n.getX()==closedL.get(i).getX())&&(n.getY()==closedL.get(i).getY()))
          return true;
}

    return in;
}

/* to check if the path of a visited node befor better than the new one or not*/
public boolean better(Node n)
{   boolean b=false;
    
for(int i=0;i<openL.size();i++)
{
        if((n.getX()==openL.get(i).getX())&&(n.getY()==openL.get(i).getY())&&((n.getFn())<openL.get(i).getFn()))
        { 
          //closedL.remove(openL.get(i));
          openL.add(n);
          return true;
        }
}

for(int i=0;i<closedL.size();i++)
{
        if((n.getX()==closedL.get(i).getX())&&(n.getY()==closedL.get(i).getY())&&((n.getFn())<closedL.get(i).getFn()))
        {
          closedL.remove(openL.get(i));
          openL.add(n);
          return true;

        }    
}

    return b;
}

/* a function to choose the smallest f fun in the openlist to expand after finding it if we 
have two or more hav the same f we pass the arraylist contain then to the tie breaker fun*/
public void printt()
{
    for(int i=0;i<openL.size();i++)
        System.out.println(openL.get(i).getFn());
}
public Node bst_in()
{                               

    Node bst= new Node();
    List <Node> bstL=new ArrayList<Node>();

    bst=openL.get(0);
   /* for(int i=1;i<openL.size();i++)
    {
        if(openL.get(i).getFn()<bst.getFn())
            bst=openL.get(i);
    }*/
    
    for(int i=1;i<openL.size();i++)
    {
        if(openL.get(i).getFn()==bst.getFn())
            bstL.add(openL.get(i));
    }
    
    if(bstL.size()>1)
        bst=tie_breaker(bstL);
                                    

return bst;
}

/*filling the solution arraylist*/
public List<Node> sol(int ind,Node bst)
{  solution.add(bst);

   if(bst.getParent()==null)  
    return solution;
   else 
   {
       solution.add(bst.getParent());
       sol(1,bst.getParent());
       System.out.println("sol"+bst.getX());
       return solution;
   }
       
}

public void sorting(List <Node> t)
{   Node temp=new Node();
    Node temp1=new Node();
    Node temp2=new Node();
     for (int i = 0; i < t.size(); i++) 
        {
            for (int j = i + 1; j < t.size(); j++) { 
                if (t.get(i).getFn() > t.get(j).getFn()) 
                {
                    temp = t.get(i);
                    temp1=t.get(i);
                    temp2=t.get(j);
                    temp1=temp2;
                    temp2=temp;
                }
            }
        }
}

/*the fun of the algorithm*/
public void A_star ()
{   openL.add(f);//adding the first state to be expand in the open list which is row 0 in traditional table
    boolean bet;  //checking if the bath is better 
    
   // System.out.println("a star");
    do
    {              
        //System.out.println("do");
        if(openL.size()==0)//no solution case
        {//sol(0,f);
         System.out.println("empty");
        break;
        }
            
    bst=bst_in();
        // System.out.println(bst.getX()+","+bst.getY()+","+bst.getE());

         if ((equal_nodes(bst,g)))//reached the goal
        {         //  System.out.println("found");
                //System.out.println(bst.getX());
              //  System.out.println(this.g.getY());
            sol(1,bst);
            break;
        }
        else
        {         

            /* all the if statments below for checking which children to expand if any exists*/
        if(bst.getW()==1)
        {                     //               System.out.println("west");

            Node w=new Node();
            w.setY(bst.getY()-1);
            w.setX(bst.getX());
            w.setGn(bst.getGn()+1);
            w.setHn(m_distance(w,g));
            if(!contain_in(w))
            {   w.setParent(bst);
                openL.add(w);
            }
            else
            {
             bet=better(w);
             if(bet)
             {
                w.setParent(bst);
             }
            }          
        }
//System.out.println("west");
        if(bst.getE()==1)
        {//System.out.println("east");
            Node E=new Node();
            E.setX(bst.getX());
            E.setY(bst.getY()+1);
            E.setHn(m_distance(E,g));
            E.setGn(bst.getGn()+1);
            if(!contain_in(E))
            {   E.setParent(bst);
                openL.add(E);
                                                //    System.out.println("in east");

                                   // System.out.println(E.getHn());
            }
            else
            {
             bet=better(E);
             if(bet)
             {
                E.setParent(bst);
             }
            }          

        }

        if(bst.getS()==1)
        {
            Node s=new Node();
            s.setX(bst.getX()+1);
            s.setY(bst.getY());
            s.setHn(m_distance(s,g));
            s.setGn(bst.getGn()+1);
                                    System.out.println(s.getHn());

            if(!contain_in(s))
            {   s.setParent(bst);
                openL.add(s);

            }
            else
            {
             bet=better(s);
             if(bet)
             { 
                s.setParent(bst);
             }
            }          
        }

        if(bst.getN()==1)
        {
            Node N=new Node();
            N.setX(bst.getX()+1);
            N.setY(bst.getY());
                        
            N.setHn(m_distance(N,g));
            N.setGn(bst.getGn()+1);
                        System.out.println(N.getHn());

            if(!contain_in(N))
            {   N.setParent(bst);
                openL.add(N);
            }
            else
            {
             bet=better(N);
             if(bet)
             {
                N.setParent(bst);
             }
            }          

        }
        }
        closedL.add(bst);
        openL.removeIf(n-> ((n.getX()==bst.getX())&& (n.getY()==bst.getY())));
       sorting(openL);
       printt();
        System.out.println("end");
    }while(openL.size()!=0);
}
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       // int g=m_distance(0,0,2,2);
       // System.out.println(g);
       Node f=new Node(1,1);
       Node g= new Node(50,50);
                     //  System.out.println(g.getY());

       Solver sol=new Solver(f,g);
        // TODO code application logic here
    }
    
    

}

