import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame gioco = new JFrame("Palloni Cadenti");

        gioco.setDefaultCloseOperation(3);

        //Immagini
        Disegna img = new Disegna(gioco);
        gioco.add(img);

        //dimensione finestra
        gioco.pack(); //impone alla finestra le dimensioni del pannello
        gioco.setLocationRelativeTo(null); //per posizionare al centro la schermata
        gioco.setResizable(false); //impedimento ridimensionamento finestra

        //visibilità
        gioco.setVisible(true);
    }
}