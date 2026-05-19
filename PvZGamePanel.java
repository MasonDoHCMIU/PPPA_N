import javax.swing.*;
import java.awt.*;

public class PvZGamePanel extends JPanel {
    private Image backgroundImage;
    private String path = "/Users/jaydenpham1602/Downloads/";

    public PvZGamePanel() {
        this.setLayout(null);

        backgroundImage = new ImageIcon(path + "mainBG.png").getImage();

        createCard("card_sunflower.png", 108, 8, "Sunflower");
        createCard("card_wallnut.png", 180, 8, "Wall-nut");
        createCard("card_peashooter.png", 252, 8, "Peashooter");
        createCard("card_freezepeashooter.png", 324, 8, "Freeze Peashooter");
    }

    private void createCard(String fileName, int x, int y, String name) {
        Image img = new ImageIcon(path + fileName).getImage();
        PlantCard card = new PlantCard(img);

        card.setBounds(x, y, 59, 79);

        card.setActionListener(e -> {
            System.out.println("Bạn đã click vào thẻ: " + name);
        });

        this.add(card);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
