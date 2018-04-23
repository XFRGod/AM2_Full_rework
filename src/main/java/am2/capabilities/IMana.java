package am2.capabilities;

public interface IMana {

    long getCurrentMana();
    void setCurrentMana(long mana);

    long getMaxMana();
    void setMaxMana(long mana);

    int getMagicLevel();
    void setMagicLevel(int level);

    int getMagicXP();
    void setMagicXP(int xp);
}
