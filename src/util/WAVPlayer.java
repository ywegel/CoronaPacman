package util;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicBoolean;

public class WAVPlayer {

    private static final AtomicBoolean isPaused = new AtomicBoolean(true);
    private static final Object mutex = new Object();

    private static final AtomicBoolean isKill = new AtomicBoolean(false);

    public static void playGameMusic() {
        while (!isKill.get()) {
            play("sounds/solodrillbeat1.wav");
        }
    }

    private static void play(String filename) {
        System.out.println("Musik läuft");

        int EXTERNAL_BUFFER_SIZE = 524288;

        InputStream soundInputStream = Data.loadFileFromResAsStream(filename);

        AudioInputStream audioInputStream;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(soundInputStream));
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        AudioFormat format = audioInputStream.getFormat();

        SourceDataLine auline;

        //Describe a desired line
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

        try {
            auline = (SourceDataLine) AudioSystem.getLine(info);

            //Opens the line with the specified format,
            //causing the line to acquire any required
            //system resources and become operational.
            auline.open(format);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        //Allows a line to engage in data I/O
        auline.start();

        int nBytesRead = 0;
        byte[] abData = new byte[EXTERNAL_BUFFER_SIZE];

        isPaused.set(false);

        try {
            while (nBytesRead != -1 && !isKill.get()) {
                synchronized (mutex) {
                    if (isPaused.get()) {
                        mutex.wait();
                    }
                }

                nBytesRead = audioInputStream.read(abData, 0, abData.length);
                if (nBytesRead >= 0) {
                    //Writes audio data to the mixer via this source data line
                    //NOTE : A mixer is an audio device with one or more lines
                    auline.write(abData, 0, nBytesRead);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //Drains queued data from the line
            //by continuing data I/O until the
            //data line's internal buffer has been emptied
            auline.drain();

            //Closes the line, indicating that any system
            //resources in use by the line can be released
            auline.close();

            isPaused.set(true);

            System.out.println(Thread.currentThread().getName() + ": Thread stopped");
        }
    }

    public static void killThread() {
        isKill.set(true);
    }

    public static void pauseMusic() {
        isPaused.set(true);
    }

    public static void continueMusic() {
        isPaused.set(false);

        synchronized (mutex) {
            mutex.notifyAll();
        }
    }
}