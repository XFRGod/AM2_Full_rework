package am2.capabilities;

public class AM2Capabilities implements IAM2Capabilites {

    private int syncCode = 0;
    private int MarkDimensionID = -512;
    private float currentMana;
    private float currentBurnout;
    private float currentXP;
    private int currentLevel;

    private static enum SYNC_TYPE{
        CONTINGENCY (0x1),
        MARK        (0x2),
        MANA        (0x4),
        FATIGUE     (0x8),
        LEVEL       (0x10),
        XP          (0x20);
        private int value;
        SYNC_TYPE(int value){
            this.value = value;
        }
        private int value() { return value;}
        //SUMMONS,
        //FALL_PROTECTION,
        //FLIP_ROTATION,
        //INVERSION_STATE,
        //SHRINK_STATE,
        //TK_DISTANCE,
        //MANA_SHIELD,
        //SHRINK_PERCENTAGE,
        //HEAL_COOLDOWN,
        //AFFINITY_HEAL_COOLDOWN,
        //DISABLE_GRAVITY
    }

    private double MarkX, MarkY, MarkZ;

    private void addSyncCode(int code) {
        this.syncCode |= code;
    }

    @Override
    public float getCurrentMana() {
        return this.currentMana;
    }

    @Override
    public float getMaxMana() {
        return (float) (Math.pow(this.getCurrentLevel(), 1.5f) * (85f * ((float) this.getCurrentLevel() / 100f)) + 500f);
    }

    @Override
    public int getCurrentLevel() {
        return this.currentLevel;
    }

    @Override
    public float getCurrentBurnout() {
        return this.currentBurnout;
    }

    @Override
    public float getMaxBurnout() {
        return this.getCurrentLevel() * 10 + 1;
    }

    @Override
    public float getCurrentXP() {
        return this.currentXP;
    }

    @Override
    public float getMaxXP() {
        return (float) (0.2 + Math.log(1 + (this.getCurrentLevel() * 0.2)));
    }

    @Override
    public double getMarkX() {
        return MarkX;
    }

    @Override
    public double getMarkY() {
        return MarkY;
    }

    @Override
    public double getMarkZ() {
        return MarkZ;
    }

    @Override
    public void setCurrentBurnout(float currentBurnout) {
        if (this.currentBurnout != currentBurnout) {
            this.addSyncCode(SYNC_TYPE.FATIGUE.value());
            this.currentBurnout = currentBurnout;
        }
    }

    @Override
    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    @Override
    public void setCurrentMana(float currentMana){
        if (this.currentMana != currentMana) {
            this.addSyncCode(SYNC_TYPE.MANA.value());
            this.currentMana = currentMana;
        }
    }


    @Override
    public void setCurrentXP(float currentXP) {
        if (this.currentXP != currentXP) {
            while (currentXP >= this.getMaxXP()) {
                currentXP -= this.getMaxXP();
                this.setMagicLevelWithMana(this.getCurrentLevel() + 1);
            }
            this.addSyncCode(SYNC_TYPE.XP.value());
            this.currentXP = currentXP;
        }
    }

    @Override
    public boolean setMagicLevelWithMana(int level) {
        if (level < 0) level = 0;
        this.setCurrentLevel(level);
        this.setCurrentMana(this.getMaxMana());
        this.setCurrentBurnout(0);
        return true;
    }

    @Override
    public void setMarkX(double MarkX) {
        this.MarkX = MarkX;
    }

    @Override
    public void setMarkY(double MarkY) {
        this.MarkY = MarkY;
    }

    @Override
    public void setMarkZ(double MarkZ) {
        this.MarkZ = MarkZ;
    }

    @Override
    public void setMarkDimensionID(int MarkDimensionID) {
        this.MarkDimensionID = MarkDimensionID;
    }

    @Override
    public void setMark(double x, double y, double z, int dimID) {
        this.MarkX = x;
        this.MarkY = y;
        this.MarkZ = z;
        this.MarkDimensionID = dimID;
    }

    @Override
    public boolean hasEnoughMana(float cost) {
        return cost < getCurrentMana();
    }

    @Override
    public int getMarkDimensionID() {
        return this.MarkDimensionID;
    }
}
