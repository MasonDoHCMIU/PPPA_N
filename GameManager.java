import java.awt.event.*;
import java.util.ArrayList;
import java.awt.Font;

import javax.swing.*;

import java.awt.Image;
import java.util.Random;
import java.awt.*;
import java.net.URL;

public class GameManager extends JLayeredPane implements MouseMotionListener, MouseListener, ActionListener {
    private static final int backgroundWidth = 1300;
    private static final int backgroundHeight = 769;
    private static final int START_Y_OFFSET = 109;
    private static final int SPOT_HEIGHT = 120;
    private static final int total_Rows = 5;

    //Plant side
    private final Image backgroundImage;
    private final Image peashooterImage;
    private final Image sunFlowerImage;
    private final Image peaImage;
    //private final Image wallnutImage;
    private final Image sunImage;

    //Zombie side
    private Image zombieImage;

    //Timer setting
    private final Timer sunGenerate;
    private final Timer zombieGenerate;

    //AddRoad attributes
    private ArrayList<ArrayList<Zombie>> zombieRoad;
    private ArrayList<ArrayList<Pea>> shootingLane;
    private ArrayList<Sun> activeSun;
    private int[] rows;
    private PlacingSpot[] placingSpots;

    private int mouX_coor;
    private int mouY_coor;

    private PlanTypeFolder.PlantType plantingBrush = PlanTypeFolder.PlantType.NONE;
    public void setPlantingBrush(PlanTypeFolder.PlantType type){
        this.plantingBrush = type;
        }

    public GameManager(JLabel sunScoreBoard) {
        setSize(1000, 1000); //for testing
        setLayout(null);
        addMouseMotionListener(this);
        addMouseListener(this);



        this.sunScoreBoard = sunScoreBoard;
        this.sunScoreBoard.setBounds(32, 75, 80, 30);
        this.sunScoreBoard.setFont(new Font("Open Sans", 1, 25));
        this.sunScoreBoard.setHorizontalTextPosition(SwingConstants.CENTER);
        this.sunScoreBoard.setForeground(Color.BLACK);
        this.add(this.sunScoreBoard, Integer.valueOf(3));
        setSunScore(100);       //default sun score when user entering the game for first time

        backgroundImage = new ImageIcon(this.getClass().getResource("/images/mainBG.png")).getImage();
        sunFlowerImage = new ImageIcon(this.getClass().getResource("/images/plants/sunflower.gif")).getImage();
        peashooterImage = new ImageIcon(this.getClass().getResource("/images/plants/peashooter.gif")).getImage();
        //wallnutImage = new ImageIcon(this.getClass().getResource("/images/wallnut.gif")).getImage();
        peaImage = new ImageIcon(this.getClass().getResource("/images/pea.png")).getImage();
        sunImage = new ImageIcon(this.getClass().getResource("/images/sun.png")).getImage();

        //Add zombieLane to generate zombie
        zombieRoad = new ArrayList<>();
        zombieRoad.add(new ArrayList<>());
        zombieRoad.add(new ArrayList<>());
        zombieRoad.add(new ArrayList<>());
        zombieRoad.add(new ArrayList<>());
        zombieRoad.add(new ArrayList<>());

        //Set up row - tuong ung y-pixel
        rows = new int[total_Rows];
        for (int i = 0; i<total_Rows; i++) {
            rows[i] = START_Y_OFFSET + (i * SPOT_HEIGHT);
        }

        //Set up chỗ đặt plant
        placingSpots = new PlacingSpot[45];
        for (int i = 0; i < 45; i++) {
            PlacingSpot spots = new PlacingSpot();
            spots.setLocation(44 + (i % 9) * 100, START_Y_OFFSET + (i / 9) * SPOT_HEIGHT);
            spots.setAction(new PlantActionListener((i % 9), (i / 9)));
            placingSpots[i] = spots;
            add(spots, new Integer(0));
        }

        //Sun methods
        activeSun = new ArrayList<>();
        sunGenerate = new Timer(5000, (ActionEvent e) -> {
            Random rand = new Random();
            Sun procedure = new Sun(rand.nextInt(backgroundWidth - 300) + 240, 0, rand.nextInt(backgroundHeight - 300) + 100, this);
            activeSun.add(procedure);
        });
        sunGenerate.start();

        //Zombie Methods
        zombieGenerate = new Timer(7000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random rand = new Random();

                int generateChoice = rand.nextInt(3);
                switch (generateChoice) {
                    case 0:
                        addZombie(ZombieType.NORMALZOMBIE);
                        break;
                    case 1:
                        addZombie(ZombieType.CONEHEAD);
                        break;
                    case 2:
                        addZombie(ZombieType.NORMALZOMBIE);
                        addZombie(ZombieType.CONEHEAD);
                        break;
                }
            }
        });
        zombieGenerate.start();
        addZombie(ZombieType.NORMALZOMBIE);

        createCard("cards/card_sunflower.png", 121, 8, PlanTypeFolder.PlantType.SUNFLOWER);
        //createCard("card_wallnut.png", 180, 8, "Wall-nut");
        createCard("cards/card_peashooter.png", 188, 8, PlanTypeFolder.PlantType.PEASHOOTER);
        createCard("cards/card_freezepeashooter.png", 255, 8, PlanTypeFolder.PlantType.FROZENPEASHOOTER);
        Timer gameLoop = new Timer(16, this);
        WaveManager waveManager = new WaveManager(this);
        waveManager.startLevel();
        gameLoop.start();
    }
    private void createCard(String fileName, int x, int y, PlanTypeFolder.PlantType type){
        URL imgURL = this.getClass().getResource("/images/" + fileName);
        if (imgURL == null) return;

        Image img = new ImageIcon(imgURL).getImage();
        PlantCard card = new PlantCard(img);
        card.setBounds(x, y, 59, 79);

        card.setActionListener(e -> {
            setPlantingBrush(type); // Gọi trực tiếp hàm của GameManager
        });

        // Thêm vào chính nó (GameManager) ở lớp cao nhất
        this.add(card, Integer.valueOf(2));
    }

    //Add zombie to road
    public void addZombie(ZombieType type){
        Random rand = new Random();
        int n = rand.nextInt(5);
        int yRow = rows[n];
        //zombieRoad.get(n).add(new NormalZombie(backgroundWidth, yRow));
        Zombie newZombie = type.generateZom(backgroundWidth, yRow);

        zombieRoad.get(n).add(newZombie);
        }

    //Sun
    private int sunScore;
    private JLabel sunScoreBoard;

    public int getSunScore(){
        return sunScore;
    }
    public void setSunScore(int sunScore){
        this.sunScore = sunScore;
        sunScoreBoard.setText(String.valueOf(sunScore));
    }

    public ArrayList<Sun> getActiveSun(){
        return activeSun;
    }
    public void setActiveSun(ArrayList<Sun> activeSun){
        this.activeSun = activeSun;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        updateSun();
        updatePlants();
        updateZombie();
        checkCollision();
        repaint();
    }

    //actionPerformed
    private void updateZombie(){
        for (int i = 0; i < zombieRoad.size(); i++){
            ArrayList<Zombie> currentLane = zombieRoad.get(i);

            for (int j = currentLane.size() - 1; j >= 0; j--) {
                Zombie newZom = currentLane.get(j);
                if (newZom.isAlive()) {
                    newZom.move();
                } else {
                    currentLane.remove(j);
                }
            }
        }
    }
    private void updateSun(){
        for (int i = 0; i < activeSun.size(); i++){
            activeSun.get(i).update();
        }
    }
    private void checkCollision(){
        //WRITE
    }

    //PlantActionListener: xu ly tac vu trong cay
    private class PlantActionListener implements ActionListener {
        int x, y;

        public PlantActionListener(int x, int y){
            this.x = x;
            this.y = y;
        }
        //private PlanTypeFolder.PlantType plantingBrush = PlanTypeFolder.PlantType.NONE;
        //public void setPlantingBrush(PlanTypeFolder.PlantType type){
            //this.plantingBrush = type;
        //}

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (plantingBrush) {
                case NONE:
                    break;
                case SUNFLOWER:
                    planting(new Sunflower(x, y), 50, x, y);
                    break;
                case PEASHOOTER:
                    planting(new PeaShooter(x, y), 100, x, y);
                    break;
                case FROZENPEASHOOTER:
                    planting(new IcePeaShooter(x, y), 150, x, y);
                    break;
            }
            plantingBrush = PlanTypeFolder.PlantType.NONE;
        }
    }
    //Function execute planting plant
    private void planting(Plant place_plant, int sunCost, int x, int y){
        if (getSunScore() >= sunCost){
            placingSpots[x + y * 9].setPlant(place_plant);

            setSunScore(getSunScore() - sunCost);
        }
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        //Nothing
    }
    @Override
    public void mouseMoved(MouseEvent e) {
        mouX_coor = e.getX();
        mouY_coor = e.getY();
    }

    private void updatePlants(){
        for (int i = 0; i < placingSpots.length; i++){
            PlacingSpot spot = placingSpots[i];

            if (spot.assignedPlant != null){
                Plant p = spot.assignedPlant;
                p.update();

                if (p instanceof Sunflower){
                    Sunflower newSunflower = (Sunflower) p;
                    Sun newSun = newSunflower.produceSun();

                    if (newSun != null){
                        activeSun.add(newSun);
                        System.out.println("Sun just generate new "); //For testing
                    }
                }
            }
        }
    }
    //End PlantActionListener


    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, null);

        //Draw plants
        for (int i = 0; i < 45; i++){
            PlacingSpot newSpot = placingSpots[i];
            if (newSpot.assignedPlant != null){
                Plant newPlant = newSpot.assignedPlant;
                g.drawImage(newPlant.getImage(), 60 + (i % 9) * 100, 129 + (i / 9) * 120, null);
            }
        }
        for (int i = 0; i < activeSun.size(); i++){
            Sun s = activeSun.get(i);
            g.drawImage(sunImage, s.getX(), s.getY(), null);
        }

        //Draw zombies
        for (int i = 0; i < zombieRoad.size(); i++){
            for (Zombie z : zombieRoad.get(i)){
                //g.drawImage(zombieImage, z.getX(), 109 + (i * 120), null);
                g.drawImage(z.getImage(), z.getX(), 109 + (i * 120), null);
            }
        }
    }
    public void showWinReward(){

    }
    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mousePressed(MouseEvent e) {
        int myX = e.getX();
        int myY = e.getY();

        for (int i = activeSun.size() - 1; i >= 0; i--){
            Sun s = activeSun.get(i);

            if(myX >= s.getX() && myX <= s.getX() + 50 && myY >= s.getY() && myY <= s.getY() + 50) {
                activeSun.remove(i);
                setSunScore(getSunScore() + 25);
                return;
            }
        }
    }
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
}

