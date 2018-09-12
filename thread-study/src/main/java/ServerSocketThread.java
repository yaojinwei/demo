import java.io.IOException;
import java.net.ServerSocket;

/**
 * @author Yao.Jinwei
 * @date 2017/12/19 17:56
 */
public class ServerSocketThread extends Thread {
    ServerSocket serverSocket;

    public static void main(String[] args) throws InterruptedException, IOException {
        ServerSocketThread serverSocketThread = new ServerSocketThread();
        serverSocketThread.setDaemon(true);
        System.out.println("Starting thread...");
        serverSocketThread.start();
        Thread.sleep(3000);
        System.out.println("Asking thread to stop...");
        //这个无法中断，线程继续阻塞
        serverSocketThread.interrupt();
        //java提供了解决方案，即调用close方法
//        serverSocketThread.serverSocket.close();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        System.out.println("Stopping application...");
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(9121);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        while(!Thread.currentThread().isInterrupted()){
            try {
                serverSocket.accept();
            } catch (IOException e) {
                System.out.println("accept() failed or interrupted...");
                Thread.currentThread().interrupt();//重新设置中断标示位
            }

        }
        System.out.println("Thread exiting under request...");
    }
}
