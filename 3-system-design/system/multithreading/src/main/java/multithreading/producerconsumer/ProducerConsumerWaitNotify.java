package multithreading.producerconsumer;

import java.util.LinkedList;
import java.util.Queue;

/**
 */
public class ProducerConsumerWaitNotify {

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<Integer>();
        new Thread(new Consumer(queue)).start();
        new Thread(new Producer(queue)).start();
    }

    static class Consumer implements Runnable {
        private Queue<Integer> queue;

        Consumer(Queue<Integer> queue) {
            this.queue = queue;
        }

        public void run() {
            try {
                for (int i = 0; i < 20; i++) {
                    if (queue.size() >= 5)
                        queue.wait();

                    synchronized (queue) {
                        queue.offer(i);
                    }
                    Thread.sleep(1);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Producer implements Runnable {
        private Queue<Integer> queue;

        Producer(Queue<Integer> queue) {
            this.queue = queue;
        }

        public void run() {

            while (queue.isEmpty()) {
                try {
                    queue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            while (true) {
                System.out.println(queue.poll());
            }
        }
    }

}
