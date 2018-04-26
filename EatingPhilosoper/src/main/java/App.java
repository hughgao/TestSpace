
import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.CountDownLatch;

public class App {

    public void main(String[] args) throws IOException {

        deadLockAvoid(10);

    }

    static class Philosopher implements Runnable{


        private int id;
        private int totalNumberOfPhilosophers;
        private Lock leftFork;
        private Lock rightFork;

        /**
         *
         * @param id
         * @param totalNumberOfPhilosophers
         * @param leftFork
         * @param rightFork
         */
        public Philosopher(int id, int totalNumberOfPhilosophers,
                          Lock leftFork, Lock rightFork) {

           this.id = id;
           this.leftFork = leftFork;
           this.rightFork = rightFork;
           this.totalNumberOfPhilosophers = totalNumberOfPhilosophers;

        }

        public void run() {

           while(true) {

               this.requireForks();
               System.out.println("philosopher [" + id +"] start eating");
               try {
                   Thread.sleep(1000);
               } catch (InterruptedException e) {
                   return;
               }
               System.out.println("philosopher [" + id +"] start thinking");

               this.releaseForks();

           }
        }

        private void requireForks() {

            if(this.id == this.totalNumberOfPhilosophers ) {

                rightFork.lock();
                leftFork.lock();
            }
            else {

                this.leftFork.lock();
                this.rightFork.lock();
            }



        }

        private void releaseForks() {

            if(this.id == this.totalNumberOfPhilosophers ) {

                rightFork.unlock();
                leftFork.unlock();
            }
            else {

                this.leftFork.unlock();
                this.rightFork.unlock();
            }


        }

    }

    public static  void deadLockAvoid(int numberOfPhilosopher) throws IOException {

        Lock[] locks = new Lock[numberOfPhilosopher];
        for(int i= 0;i <numberOfPhilosopher; i++) {
            locks[i] = new ReentrantLock();
        }
        final CountDownLatch latch = new CountDownLatch(1);

        final Philosopher[] philosophers = new Philosopher[numberOfPhilosopher];
        Thread[] threads  = new Thread[numberOfPhilosopher];

        for(int i =0; i<numberOfPhilosopher;i++) {

            Lock leftFork = locks[i];
            Lock  rightFork = locks[(i+1)%numberOfPhilosopher];
            philosophers[i] = new Philosopher(i, numberOfPhilosopher, leftFork, rightFork);
            final Philosopher cur = philosophers[i];
            threads[i] = new Thread(new Runnable() {
                public void run() {
                    try {
                        latch.wait();
                    }
                    catch(Exception e) {
                        ///Ignore the exception.
                    }
                    cur.run();
                }
            });
            threads[i].setDaemon(true);
            threads[i].start();

        }

        latch.countDown();
        System.out.println("enter any key to stop the application ");
        try {
            System.in.read();
        }
        catch(Exception e) {

            /// Ignore the exception.
        }
        System.exit(-1);

    }


}
