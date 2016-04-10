import java.lang.Math;
public class Vector{
  float x, y;
   Vector(float X,float Y){
       x=X;y=Y;
   }
  
  static public Vector toLoc(float fromxpos,float fromypos,float toxpos,float toypos){
        float dx=toxpos-fromxpos;
        float dy=toypos-fromypos;
        float len= (float)Math.pow((dy*dy +dx*dx),0.5);
        return(new Vector(dx/len,dy/len));
  }
  
  static public Vector toFood(Ant_Abstract ant, Food foo){
       float dx=foo.xpos-ant.xpos;
        float dy=foo.ypos-ant.ypos;
        float len= (float)Math.pow((dy*dy +dx*dx),0.5);
        return(new Vector(dx/len,dy/len));    
  }
  
  public Vector randomize(){
     float newx= (float)(this.x+(0.4*((2*Math.random())-1)));
     float newy= (float)(this.y+(0.4*((2*Math.random())-1)));
     
     return(new Vector((float)(1.5*newx/(speed(newx,newy))),  (float)(1.5*newy/(speed(newx,newy)))   ));
  }
  
  public double speed(Vector a){
      return(Math.pow((a.y*a.y +a.x*a.x),0.5));
  }
  
  public double speed(float x, float y){
       return(Math.pow((y*y +x*x),0.5));
  }
  static public double distance(Ant_Abstract a, float xpos, float ypos){
      return(Math.pow( (a.xpos-xpos)*(a.xpos-xpos)  +   (a.ypos-ypos) *(a.ypos-ypos) ,  0.5));
  }
  static public double distance(Ant_Abstract a,Food f){
      return(Math.pow( (a.xpos-f.xpos)*(a.xpos-f.xpos)  +   (a.ypos-f.ypos) *(a.ypos-f.ypos) ,  0.5));
  }
  
  public Vector illusion(double i){
       return (this);
       //return(new Vector((float)(this.x+0.5*Math.cos(i)),(float)(this.y+0.5*Math.sin(i))));
       //return(new Vector((float)(this.x+Math.cos(Math.PI*2*Math.random())),(float)(this.y+Math.sin(Math.PI*2*Math.random()))));
  }
  public float direction(){
     return ((float)Math.atan2((double)x,(double)y));
  }
  public void print(){
     System.out.println("x: "+this.x+"    y: "+this.y);
  }
}