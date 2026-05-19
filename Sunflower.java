public class Sunflower extends Plant {
    private final int produceCooldown;
    private int produceTimer;
    private boolean isReadyToProduce;

    public Sunflower(int x, int y) {
        super(x, y, 300, 50);
        this.produceCooldown = 1440;
        this.produceTimer = 0;
        this.isReadyToProduce = false;
    }

    public Sun produceSun() {
        if (isReadyToProduce) {
            produceTimer = 0;
            isReadyToProduce = false;
            return new Sun(this.getX(), this.getY());
        }
        return null;
    }

    @Override
    public void update() {
        if (!isReadyToProduce) {
            produceTimer++;
            if (produceTimer >= produceCooldown) {
                isReadyToProduce = true;
            }
        }
    }

//----------Getters and setters----------
    public int getProduceCooldown() {
        return produceCooldown;
    }
    public int getProduceTimer() {
        return produceTimer;
    }
    public boolean isReadyToProduce() {
        return isReadyToProduce;
    }
    public void setProduceTimer(int produceTimer) {
        this.produceTimer = produceTimer;
    }
    public void setReadyToProduce(boolean readyToProduce) {
        isReadyToProduce = readyToProduce;
    }
}
