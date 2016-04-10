//Scout a=new Scout(400,350,0);
AntHill theHill=new AntHill(400,350);
boolean deployable=true;
boolean paused=false;
boolean displayOn=false;
int time=0;
void setup(){
  size(950,700);
  background(250);
  frameRate(60);
  theHill.produceScout(5);
  theHill.produceWorker(20);
  Food.Food(6);
  theHill.food=60;
}





void draw(){
    if (!paused){
      background(255,255,255);
      if (random(300)<3&&Food.allFood.size()<13){
         new Food();
      }
      display();
      }
      
      line(800,0,800,750);
      fill(0, 102, 153);
      textSize(12);
      //textAlign(CENTER,CENTER);
      text(("Num of Scouts: "+AntHill.allScouts.size()), 810, 100);
      text(("Num of Workers: "+AntHill.allWorkers.size()), 810, 150);
      text(("Food: "+theHill.food), 810, 200);
      text(("Workers Out: "+(AntHill.allWorkers.size()-theHill.occupants.size())), 810, 250);
}




void display(){
    displayScouts();
    displayWorkers();
    displayFood();
    displayAntHill();
}





void displayScouts(){
   for (int i=0;i<theHill.allScouts.size();i++){
       theHill.allScouts.get(i).region=(i%4)+1;
       //System.out.println(theHill.allScouts.get(i).region);
       theHill.allScouts.get(i).update();
       if (theHill.allScouts.get(i).state.equals("Returning")&&theHill.allScouts.get(i).atHill(theHill)){
           theHill.allScouts.get(i).signal(theHill);
       }
       if (theHill.allScouts.get(i).state.equals("Returning")){
       fill(#ff7256);}
       else{fill(#40e0d0);}
       
       ellipse(theHill.allScouts.get(i).xpos,theHill.allScouts.get(i).ypos,6,6);
       ellipse(theHill.allScouts.get(i).xpos+12*cos(theHill.allScouts.get(i).direction),theHill.allScouts.get(i).ypos+12*sin(theHill.allScouts.get(i).direction),6,6);
       ellipse(theHill.allScouts.get(i).xpos+6*cos(theHill.allScouts.get(i).direction),theHill.allScouts.get(i).ypos+6*sin(theHill.allScouts.get(i).direction),6,6);
   }
}

void displayWorkers(){
  if (millis() > time + 1000)
  {
    deployable=true;
    time = millis();
  }
  for (int i=0;i<AntHill.allWorkers.size();i++){
       boolean alive=true;
       AntHill.allWorkers.get(i).time=millis()/100;
       AntHill.allWorkers.get(i).update();
       //become hungry
       //become hungry if inactive
       println(AntHill.allWorkers.get(i).hunger);
       if(AntHill.allWorkers.get(i).state.equals("Inactive")){
           if(AntHill.allWorkers.get(i).consume(1)){
             println("DEAD");
               AntHill.allAnts.remove(AntHill.allWorkers.get(i)); 
               theHill.occupants.remove(AntHill.allWorkers.get(i)); 
               AntHill.allWorkers.remove(i);
               i--;  alive=false;            
           }
       }
       //become hungry otherwise
       else{
           if(AntHill.allWorkers.get(i).consume(2)){
               println("dead");
               AntHill.allAnts.remove(AntHill.allWorkers.get(i)); 
               theHill.occupants.remove(AntHill.allWorkers.get(i)); 
               AntHill.allWorkers.remove(i);
               i--;   alive=false;}
       }
       //if still alive
       if (alive){
             //eat if hungry
             if (AntHill.allWorkers.get(i).hunger<2000 && theHill.food>0){
                 AntHill.allWorkers.get(i).eat(1);
             }
             //deploys ants 1 at a time every 3 seconds applicable
             if (AntHill.allWorkers.get(i).state.equals("Deployed")&&deployable==true){
                 AntHill.allWorkers.get(i).state="Seeking";
                 deployable=false;
             }
             /*if (i.state.equals("Seeking")){
                 ellipse(i.xpos,i.ypos,6,6);
             }*/
             //different colors for returning
             if (AntHill.allWorkers.get(i).state.equals("Returning")){
                 if (AntHill.allWorkers.get(i).numOfFood>0){
                    fill(#ff0000);
                 }
                 else{
                  fill(#00ff00);}
                  //if it returns to hill, enter hill
                 if(AntHill.allWorkers.get(i).atHill(theHill)){
                      theHill.food+=(AntHill.allWorkers.get(i).depositFood());
                      theHill.enter(AntHill.allWorkers.get(i));
                 }
             }
             else{
                  fill(#ffb6c1);
             }
             ellipse(AntHill.allWorkers.get(i).xpos,AntHill.allWorkers.get(i).ypos,6,6);
       }
   }
}



void displayAntHill(){
     theHill.update();
     fill(5);
     ellipse(theHill.xpos,theHill.ypos,10,10);
}
void displayFood(){
    for (Food i:Food.allFood){
       fill(#c71585);
       ellipse(i.xpos,i.ypos,6,6);
    }
}

void keyPressed(){
   if (key=='p'||key=='P'){
       if (paused){
           paused=false;
       }
       else{paused=true;}
   }
   if (key=='i'||key=='I'){
       if (displayOn){
           displayOn=false;
       }
       else{displayOn=true;}
   }
}