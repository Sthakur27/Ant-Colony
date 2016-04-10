import java.lang.Math;
class Scout extends Ant_Abstract
{
  static String[] states={"Inactive","Searching","Returning"};
  int detectedFoodAmount=0;
  int region=0;
  float foodLocx,foodLocy;
  
  Scout(float x, float y, int mes, AntHill hill, int hung){
     xpos=x;ypos=y; state=states[mes]; a=hill; hunger=hung;
  }

  
   //detect food
  public void detect(){
       for (Food l:Food.allFood){
            if (Vector.distance(this,l)<30){
                this.state=states[2];
                this.detectedFoodAmount=l.amount;
                this.foodLocx=l.xpos;
                this.foodLocy=l.ypos;
                this.distanceAway=(float)Vector.distance(this,400,350);
            }
       }
  }
  
  //signal workers at Anthill to go get food
  public void signal(AntHill hill){
       for (int i=0;i<(int)(detectedFoodAmount/10 +1)&&hill.occupants.size()>0;i++){
            
            hill.occupants.get(0).state="Deployed";
            hill.occupants.get(0).locationx=foodLocx;
            hill.occupants.get(0).locationy=foodLocy;
            hill.occupants.get(0).requiredDistance=distanceAway;
            hill.occupants.get(0).temp=Vector.toLoc(400,350,foodLocx,foodLocy);
            hill.occupants.get(0).distanceGone=0;
            hill.leave(0);
            
       }
  }
  
  //check if ant is near hill
  public boolean atHill(AntHill hill){
       if (Math.pow( ((xpos-hill.xpos)*(xpos-hill.xpos)  + (ypos-hill.ypos)*(ypos-hill.ypos)),0.5)<10){
              state=states[0];
              return true;
          }
       return false;
  }
  
  
  //find new search location based on which quadrant/region
  public void findRegion(){
     if (this.region==1){
         locationx=(float)(400*Math.random());
         locationy=(float)(350*Math.random());         
     }
     if (this.region==2){
         locationx=(float)(400+(400*Math.random()));
         locationy=(float)(350+(350*Math.random()));
     }
     if (this.region==3){
         locationx=(float)(400*Math.random());
         locationy=(float)(350+(350*Math.random()));
     }
     if (this.region==4){
         locationx=(float)(400+(400*Math.random()));
         locationy=(float)(350*Math.random());
     }
  }
  
  
  public void update(){
      if (state.equals("Inactive")){
         //choose new location
         findRegion();
         //start searching
         state=states[1];
         myvector=Vector.toLoc(xpos,ypos,locationx,locationy);
      }
      if (state.equals("Searching")){
          myvector=myvector.randomize();
          //reset searching location after a bit
          if (distanceGone>200){
              state=states[0];
              distanceGone=0;
          }
          move();
          this.detect();
        
      }
      if (state.equals("Returning")){
          myvector=Vector.toLoc(xpos,ypos,400,350);
          move();
          //call check if near anthill in ant.pde file for returning         
      }
  }
}