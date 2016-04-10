public abstract class Ant_Abstract
{
  int hunger;
  float xpos,ypos,distanceGone=0;
  float direction;
  float locationx,locationy,requiredDistance,distanceAway;
  String state;
  AntHill a;
  Vector myvector=new Vector(0,0);
  Vector heading=new Vector(0,0);
  Vector temp;
  static String[] states;
  int numOfAnts;
  public void move(){
        //movement
        this.xpos+=myvector.x;
        this.ypos+=myvector.y;
        direction=myvector.direction();
        distanceGone+=myvector.speed(myvector);
        //edge hit detection
        if (xpos>800){
           xpos=798; this.myvector.x=-this.myvector.x;
        }
        if (xpos<0){
           xpos=2; this.myvector.x=-this.myvector.x;
        }
        if (ypos>700){
           ypos=698; this.myvector.y=-this.myvector.y;
        }
        if (ypos<0){
           ypos=2; this.myvector.y=-this.myvector.y;
        }
  }
  public void eat(int i){
      if(a.food>0){
      hunger+=(1000*i);  a.food-=1;}
  }
  //returns if true if dead
  public boolean consume(int i){
     for (int j=0;j<i;j++){
          hunger--;
     }
     if (hunger<=0){
         return true;
     }
     return false;
  }
  abstract void update();
}