package main.java.com.accueil.start;

import java.util.Timer;
import java.util.TimerTask;

public class Compteur extends TimerTask{
	

    int countdown = 100;
    
    public Compteur()
    {
    	   long startTime = System.currentTimeMillis();
    	long currTime = startTime;

        long elapsedTime =  System.currentTimeMillis()-currTime;
        
        currTime+= elapsedTime;
       long minutes= (currTime/1000)/60;
       
    // setting time left
   
   	TimerTask task = new TimerTask()
   	{
   	    int seconds = 60;
   	    int i = 0;
   	    @Override
   	    public void run()
   	    {
   	       i++;

   	       if(i % seconds == 0)
   	           System.out.println("g");
   	       else
   	       {
   	         
   	       }
   	    }
   	};

   	Timer timer = new Timer();
   	timer.schedule(task, 0, 1000);
    	
    }

    public void run() {
        countdown = countdown - 1;
        System.out.println(countdown);
        //label.setText(countdown +"second's left");
    }


}
