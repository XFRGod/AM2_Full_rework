package am2.capabilities;

public interface IAM2Capabilites {

    float getCurrentMana();
    float getMaxMana();
    int getCurrentLevel();
    float getCurrentBurnout();
    float getMaxBurnout();
    float getCurrentXP();
    float getMaxXP();
    double getMarkX();
    double getMarkY();
    double getMarkZ();

    void setCurrentBurnout(float currentBurnout);
    void setCurrentLevel(int currentLevel);
    void setCurrentMana(float currentMana);
    void setCurrentXP(float currentXP);
    boolean setMagicLevelWithMana(int level);
    void setMarkX(double markX);
    void setMarkY(double markX);
    void setMarkZ(double markZ);
    void setMarkDimensionID(int markDimensionID);
    void setMark(double x, double y, double z, int dimID);

    boolean hasEnoughMana(float cost);

    int getMarkDimensionID();
}
