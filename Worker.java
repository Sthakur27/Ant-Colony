class Worker extends Ant_Abstract
{
  static String[] states={"Inactive","Seeking","Detected","Returning","Deployed"};
  int numOfFood;
  double time;
  float locationx,locationy;
  Food target;
  
  //constructor
  Worker(float x, float y,int mes, AntHill hill, int hung){
     xpos=x;ypos=y;state=states[mes];  a=hill; hunger=hung;
  } 
  
  public void changeRequiredDistance(float i){
      this.requiredDistance=i;
  }
  public boolean collectFood(){
       if(Vector.distance(this,target)<3){
                if (target.amount>=10){
                    target.amount-=10;
                    numOfFood+=10;
                }
                else{
                    this.numOfFood=target.amount;
                    Food.allFood.remove(target);
                    target=null;
                }
                return true;
       }
       return false;
  }
  
  public void detect(){
    for (int i=0;i<Food.allFood.size();i++){
            if (Vector.distance(this,Food.allFood.get(i))<20){             
                this.target=Food.allFood.get(i);
                myvector=Vector.toFood(this,target);
                this.state=states[2];
            }
    }
  }
  
  public int depositFood(){
       int temp=numOfFood;
       numOfFood=0;
       state=states[0];
       return temp;   
  }
  
  public boolean atHill(AntHill hill){
       if (Math.pow( ((this.xpos-hill.xpos)*(this.xpos-hill.xpos)  + (this.ypos-hill.ypos)*(this.ypos-hill.ypos)),0.5)<10){
              state=states[0];
              return true;
          }
       return false;
  }
  
  public void moveOverride(float x, float y){
      this.xpos+=x;
      this.ypos+=y;
  }


  public void update(){
      if (state.equals("Seeking")){
          //random walk illusion
          //myvector=temp;
           myvector=temp.illusion(time);        
        
          //myvector=myvector.randomize();
          move();
          
          //check if food is nearby
          this.detect();
          
          //turns back if gone too far without detecting
          distanceAway=(float)Vector.distance(this,400,350);
          if (distanceAway>requiredDistance+15){
              myvector=Vector.toLoc(xpos,ypos,400,350);
              state=states[3];
          }
          
        
      }
      if (state.equals("Detected")){
          
          move();
          if (collectFood()){
              this.state=states[3];
              myvector=Vector.toLoc(xpos,ypos,400,350);
          }
          
      }
      if (state.equals("Returning")){
          //System.out.println("x : "+myvector.x+" y : "+myvector.y);
          moveOverride(myvector.x,myvector.y);
      }
  }
}