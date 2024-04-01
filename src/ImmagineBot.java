import javax.swing.*;
import java.awt.*;

public class ImmagineBot extends Immagine implements Runnable{
    private Disegna palestra;
     private int velocità;
     private Thread t;

    public ImmagineBot(String nomeImg, int x, int y, int lunghezza, int altezza, boolean visible, Disegna palestra, int velocità) {
         super(nomeImg, x, y, lunghezza, altezza, visible);
         this.palestra = palestra;
         this.velocità = velocità;
         t = new Thread(this);
         t.start();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(4000);

            while((this.getY() < 500) && (palestra.punteggio > -5) && (palestra.punteggio < 10)) {
                this.spostaVerticale(this.velocità);

                try {
                    Thread.sleep(100);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                SwingUtilities.invokeLater(() -> palestra.repaint());

                //ricaduta pallone
                if (this.getY() >= 500) {
                    this.setY(-100);
                    this.setX(genX());
                }

                //collisione
                if (collision()) {
                    this.setY(-100);
                    this.setX(genX());
                }
            }
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean collision() {
        Rectangle RectCestino = new Rectangle(palestra.cestino.getX(), palestra.cestino.getY(), palestra.cestino.getLunghezza(), palestra.cestino.getAltezza());
        Rectangle RectObj = new Rectangle(this.getX(), this.getY(), this.getLunghezza(), this.getAltezza());

        if (RectCestino.intersects(RectObj)) {
            if (this == palestra.p1) {
                palestra.punteggio += 1;
                System.out.println("-- COLLISISONE CON PALLONE DA BASKET --\n" +
                        "PUNTEGGIO: " + palestra.punteggio + "\n");
                palestra.setTesto("PUNTEGGIO: " + palestra.punteggio);
            }
            else if (this == palestra.b1) {
                palestra.punteggio -= 1;
                System.out.println("-- COLLISISONE CON BOMBA --\n" +
                        "PUNTEGGIO: " + palestra.punteggio + "\n");
                palestra.setTesto("PUNTEGGIO: " + palestra.punteggio);
            }
            else if (this == palestra.p3) {
                palestra.punteggio += 3;
                System.out.println("-- COLLISISONE CON PALLONE DA PALLAVOLO --\n" +
                        "PUNTEGGIO: " + palestra.punteggio + "\n");
                palestra.setTesto("PUNTEGGIO: " + palestra.punteggio);
            }
            else if (this == palestra.b2) {
                palestra.punteggio -= 2;
                System.out.println("-- COLLISISONE CON PALLONE DA BASKET --\n" +
                        "PUNTEGGIO: " + palestra.punteggio + "\n");
                palestra.setTesto("PUNTEGGIO: " + palestra.punteggio);
            }

            return true;
        }
        else {
            return false;
        }
    }
}