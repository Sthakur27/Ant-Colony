import java.util.*;  import java.lang.Math;
public class AntHill{
  int food,maxfood;
  float xpos,ypos;
  ArrayList<Ant_Abstract> occupants=new ArrayList<>();
  static ArrayList<Worker> allWorkers=new ArrayList<Worker>();
  static ArrayList<Scout> allScouts=new ArrayList<Scout>();
  static ArrayList<Ant_Abstract> allAnts=new ArrayList<>();
  
  
  AntHill(float x, float y){
     xpos=x;ypos=y; food=0;
  }
  public void autoproduce(){
       if (food-(3*allAnts.size())>0){
            if(allScouts.size()*5<allWorkers.size()){
               produceScout();
            }
            else{produceWorker();}
       }
  }
  public void enter(Ant_Abstract ant){
       occupants.add(ant);
       //System.out.println("Ant entered at position "+(occupants.indexOf(ant)+1));
       ant.xpos=this.xpos;
       ant.ypos=this.ypos;
  }
  public void leave(Ant_Abstract ant){
       occupants.remove(ant);
  }
  public void leave(int i){
       occupants.remove(i);
  }
  public void produceWorker(int num){
      for (int i=0;i<num;i++){
           this.produceWorker(); 
      }
  }
  public void produceScout(int num){
      for (int i=0;i<num;i++){
           this.produceScout(); 
      }
  }
  public Worker produceWorker(){
      food-=10;
      Worker a=new Worker(xpos,ypos,0,this, ((int)Math.random()*1000)+5000); //5000
      allAnts.add(a);
      allWorkers.add(a);
      enter(a);
      return(a);
  }
  public Scout produceScout(){
      food-=10;
      Scout a=new Scout(xpos,ypos,0,this,((int)Math.random()*1000)+5000);
      allAnts.add(a);
      allScouts.add(a);
      return(a);
  }
  public void feed(){
      int rationing=(int)((1/3)*(food/occupants.size()));
      for (Ant_Abstract ant:occupants){
         if (ant.hunger>3){
              this.food-=rationing;
              ant.eat(rationing);
         }
      }
  } 
  public void update(){
       autoproduce();
  }
}