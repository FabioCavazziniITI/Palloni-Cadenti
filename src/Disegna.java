import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

public class Disegna extends JPanel {
    protected Immagine sfondo, cestino;
    protected ImmagineBot p1, p3, b1, b2;
    private JFrame i;
    protected int punteggio = 0;
    protected String testo;
    private int flag = 0, flag1 = 0;
    private Sound suono;

    public Disegna(JFrame i) {
        this.setPreferredSize(new Dimension(900, 600));
        this.i = i;
        sfondo = new Immagine("sfondo.jpg", 0,0, 900, 600, true);
        cestino = new Immagine("cestino.png", 410, 420, 85, 85, true);
        p1 = new ImmagineBot("p1.png", 150, -100, 40, 40, true, this, 30);
        b1 = new ImmagineBot("bomba.jpg", 350, -100, 40, 40, true, this, 30);
        p3 = new ImmagineBot("p3.png", 550, -100, 40,40,true, this, 30);
        b2 = new ImmagineBot("bomba.jpg", 750, -100, 40, 40, true, this, 30);
        testo = "PUNTEGGIO: 0";

        i.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            //tasto premuto
            @Override
            public void keyPressed(KeyEvent e) {
                int pressed = e.getKeyCode();

                //spostamento a destra
                if (pressed == KeyEvent.VK_D || pressed == KeyEvent.VK_RIGHT) {
                    cestino.spostaOrrizzontale(20);
                }
                //spostamento a sinistra
                if (pressed == KeyEvent.VK_A || pressed == KeyEvent.VK_LEFT) {
                    cestino.spostaOrrizzontale(-20);
                }
            }

            //tasto rilasciato
            @Override
            public void keyReleased(KeyEvent e) {}
        });
    }

    //moficia testo
    public void setTesto(String testo) {
        this.testo = testo;
        repaint();
    }

    public void paint(Graphics g) {
        this.sfondo.disegna(g);
        this.p1.disegna(g);
        this.b1.disegna(g);
        this.p3.disegna(g);
        this.b2.disegna(g);
        this.cestino.disegna(g);

        //settaggio scritta
        g.setColor(Color.BLACK);
        g.setFont(new Font("Verdana", Font.BOLD, 25));
        g.drawString(testo, 670, 580);

        //canzone
        if ((punteggio > -5 && punteggio < 10) && flag == 0) {
            suono = new Sound(new File("Massive-Bass.wav"), 0);
            flag = 1;
        }

        //settaggio situazione vinto/perso
        if (punteggio >= 10) { //vittoria
            sfondo = new Immagine("vinto.jpg",0,0,900,600,true);
            this.sfondo.disegna(g);
            if (flag1 == 0) {
                suono.ferma();
                suono = new Sound(new File("vinto.wav"), 0);
                flag1 = 1;
            }
        }
        else if (punteggio <= -5) { //perdita
            sfondo = new Immagine("perso.jpg",0,0,900,600,true);
            this.sfondo.disegna(g);
            if (flag1 == 0) {
                suono.ferma();
                suono = new Sound(new File("perso.wav"), 0);
                flag1 = 1;
            }
        }
    }
}
