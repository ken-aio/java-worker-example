package sample;

public class SampleJob implements java.lang.Runnable {

    private int time;

    public SampleJob(int time) {
        this.time = time;
    }

    @Override
    public void run() {
        //sleep();
        infinitLoop();
        System.out.println(String.format("Done!!"));
    }

    private void sleep() {
        System.out.println(String.format("run sample job. I'm sleeping now... %d sec", this.time));
        try {
            Thread.sleep(this.time * 1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void infinitLoop() {
        System.out.println("starting tooooooo long loop...");
        for (long i = 0; i < 10000000000L; i++) {

        }
    }
}
