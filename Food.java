import java.util.*;
import java.lang.Math;
class Food{
    static ArrayList<Food> allFood=new ArrayList<Food>(15);
    int amount;
    float xpos,ypos;
    boolean visible;
    
    Food(){
      //random placement
        xpos=(float)(10+(780*Math.random()));ypos=(float)(10+(680*Math.random()));visible=true;
        amount=5+(int)(20*Math.random());
        allFood.add(this);
    }
    
    
    Food(float x, float y,int a){
        xpos=x;ypos=y;visible=true;
        amount=a;
        allFood.add(this);
    }
    
    public static void Food(int num){
      for (int i=0;i<num;i++){
           new Food(); 
      }
    }
}