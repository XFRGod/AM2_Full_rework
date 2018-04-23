package am2.capabilities;

public class Mana implements IMana {

    private int magicLevel = 0;
    private int magicXP = 0;
    private long maxMana = 10;
    private long currentMana = 2;
    private int manaRegenRate = 1;

    @Override
    public long getCurrentMana() {
        return this.currentMana;
    }

    @Override
    public void setCurrentMana(long mana){
        this.currentMana = mana;
    }

    @Override
    public long getMaxMana() {
        return this.maxMana;
    }

    @Override
    public void setMaxMana(long mana) {
        this.maxMana = mana;
    }

    @Override
    public int getMagicLevel() {
        return this.magicLevel;
    }

    @Override
    public void setMagicLevel(int level) {
        this.magicLevel = level;
    }

    @Override
    public int getMagicXP() {
        return this.magicXP;
    }

    @Override
    public void setMagicXP(int xp) {
        this.magicXP = xp;
    }
}
