import javax.swing.*;
import java.awt.*;

public class PvZGamePanelTest {
    public static void main(String[] args) {
        JFrame application = new JFrame("Plants vs Zombies - Lucas Version");

        // 1. Tạo nhãn hiển thị điểm (Sun Score)
        // Nhãn này sẽ được đặt vào bảng điều khiển phía trên trong game
        JLabel scoreLabel = new JLabel("100");

        // 2. Khởi tạo GameManager và truyền nhãn điểm vào
        GameManager gameManager = new GameManager(scoreLabel);

        // Thiết lập kích thước cho GameManager khớp với ảnh nền (1300x769)
        gameManager.setPreferredSize(new Dimension(1300, 769));

        // 3. Thiết lập JFrame
        application.add(gameManager);
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Tự động điều chỉnh kích thước cửa sổ ôm khít GameManager
        application.pack();

        application.setLocationRelativeTo(null); // Hiển thị giữa màn hình
        application.setVisible(true); // Chính thức hiện cửa sổ game
    }
}
