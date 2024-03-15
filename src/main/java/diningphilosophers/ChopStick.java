package diningphilosophers;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ChopStick {

    private static int Count = 0;
    private boolean iAmFree = true;
    private final int Number;
    private final static Lock lock = new ReentrantLock();

    public ChopStick() {
        Number = ++Count;
    }

    public boolean tryTake(int delay) throws InterruptedException {
        if (!iAmFree) {
            boolean locked = lock.tryLock(300, java.util.concurrent.TimeUnit.MILLISECONDS);
            if (!locked) 
            {
                return false; 
            }
        }
        else{
            lock.lock();
        }
        iAmFree = false;
        return true; 
    }

    public void release() {
        try {
            iAmFree = true;
            System.out.println("Stick " + Number + " Released");
        }
        finally {
            lock.unlock();
        }
    }

    @Override
    public String toString() {
        return "Stick#" + Number;
    }
}
