/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diningphilosophers;

/**
 *
 * @author ejs
 */
public class Philosopher implements Runnable {

    private Object leftFork;
    private Object rightFork;
    
    private void doAction(String action) throws InterruptedException
    {
        System.out.println(Thread.currentThread().getName() + " " + action);
        Thread.sleep(((int) (Math.random() * 100)));
    }
    
    public Philosopher(Object leftFork, Object rightFork)
    {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }
    
    @Override
    public void run() 
    {
        try
        {
            while(true)
            {
                
                doAction(System.nanoTime() + ": Thinking...");
                
                synchronized (leftFork) 
                {
                    doAction(System.nanoTime() + ": Picked up left fork");
                    
                    synchronized (rightFork)
                    {
                        doAction(System.nanoTime() + ": Picked up right fork. Eating...");
                        doAction(System.nanoTime() + ": Put down right fork");
                        
                    }
                    
                    doAction(System.nanoTime() + ": Put down left fork. Thinking...");
                }
                
                
            }
        } catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
            return;
        }
    }
    
}
