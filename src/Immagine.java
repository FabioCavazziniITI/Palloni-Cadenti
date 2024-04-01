import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Immagine {
    private Image img;
    private int x, y, lunghezza, altezza;
    private boolean visible;

    //--------------------------------------------------------------------------------------------

    //METODO COMPLETO
    public Immagine(String nomeImmagine, int x, int y, int lunghezza, int altezza, boolean visible) {
        this.img = new ImageIcon(nomeImmagine).getImage();
        this.x = x;
        this.y = y;
        this.lunghezza = lunghezza;
        this.altezza = altezza;
        this.visible = visible;
    }

    //--------------------------------------------------------------------------------------------

    //GETTER

    public Image getImg() {
        return img;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getLunghezza() {
        return lunghezza;
    }

    public int getAltezza() {
        return altezza;
    }

    public boolean isVisible() {
        return visible;
    }

    //--------------------------------------------------------------------------------------------

    //SETTER

    public void setImg(Image img) {
        this.img = img;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setLunghezza(int lunghezza) {
        this.lunghezza = lunghezza;
    }

    public void setAltezza(int altezza) {
        this.altezza = altezza;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    //--------------------------------------------------------------------------------------------

    //METODO PER DISEGNARE
    public void disegna(Graphics g) {
        g.drawImage(img, x, y, lunghezza, altezza, null);
    }

    //--------------------------------------------------------------------------------------------

    //SPOSTA GENERALE
    public void sposta(int dx, int dy) {
        this.x = dx;
        this.y = dy;
    }

    //SPOSTA GENERALE
    public void spostaOrrizzontale(int dx) {
        this.x += dx;
    }
    //SPOSTA GENERALE
    public void spostaVerticale(int dy) {
        this.y += dy;
    }

    //--------------------------------------------------------------------------------------------

    //GENERAZIONE CASUALE X
    public int genX() {
        Random random = new Random();
        return(random.nextInt(600)+1);
    }
}
