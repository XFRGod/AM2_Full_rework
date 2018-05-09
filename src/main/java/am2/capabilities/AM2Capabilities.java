package am2.capabilities;

import am2.network.messages.AM2NBTMessage;
import am2.utils.NBTUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.capabilities.Capability;

public class AM2Capabilities implements IAM2Capabilites {

    private int syncCode = 0;
    private int MarkDimensionID = -512;
    private float currentMana;
    private float currentBurnout;
    private float currentXP;
    private int currentLevel;
    private boolean doUpdate;

    /*private static enum SYNC_TYPE{
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
    }*/

    private double MarkX, MarkY, MarkZ;

    //private void addSyncCode(int code) { this.syncCode |= code; }

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
    public boolean getDoUpdate() {
        return doUpdate;
    }

    @Override
    public void setCurrentBurnout(float currentBurnout) {
        if (this.currentBurnout != currentBurnout) {
            //this.addSyncCode(SYNC_TYPE.FATIGUE.value());
            this.currentBurnout = currentBurnout;
            doUpdate = true;
        }
    }

    @Override
    public void update() {
        doUpdate = false;
    }

    @Override
    public String toString() {
        return String.format("Mana: %s\n" +
                "Level: %s\n" +
                "Burnout: %s\n" +
                "XP: %s", this.getCurrentMana(), this.getCurrentLevel(), this.getCurrentBurnout(), this.getCurrentXP());
    }

    @Override
    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
        doUpdate = true;
    }

    @Override
    public void setCurrentMana(float currentMana){
        if (this.currentMana != currentMana) {
            //this.addSyncCode(SYNC_TYPE.MANA.value());
            this.currentMana = currentMana;
            doUpdate = true;
        }
    }


    @Override
    public void setCurrentXP(float currentXP) {
        if (this.currentXP != currentXP) {
            while (currentXP >= this.getMaxXP()) {
                currentXP -= this.getMaxXP();
                this.setMagicLevelWithMana(this.getCurrentLevel() + 1);
            }
            //this.addSyncCode(SYNC_TYPE.XP.value());
            this.currentXP = currentXP;
            doUpdate = true;
        }
    }

    @Override
    public boolean setMagicLevelWithMana(int level) {
        if (level < 0) level = 0;
        this.setCurrentLevel(level);
        this.setCurrentMana(this.getMaxMana());
        this.setCurrentBurnout(0);
        doUpdate = true;
        return true;
    }

    @Override
    public void setMarkX(double MarkX) {
        this.MarkX = MarkX;
        doUpdate = true;
    }

    @Override
    public void setMarkY(double MarkY) {
        this.MarkY = MarkY;
        doUpdate = true;
    }

    @Override
    public void setMarkZ(double MarkZ) {
        this.MarkZ = MarkZ;
        doUpdate = true;
    }

    @Override
    public void setMarkDimensionID(int MarkDimensionID) {
        this.MarkDimensionID = MarkDimensionID;
        doUpdate = true;
    }

    @Override
    public void setMark(double x, double y, double z, int dimID) {
        this.MarkX = x;
        this.MarkY = y;
        this.MarkZ = z;
        this.MarkDimensionID = dimID;
        doUpdate = true;
    }

    @Override
    public boolean hasEnoughMana(float cost) {
        return cost <= getCurrentMana();
    }

    @Override
    public boolean decreaseMana(float amount) {
        if (hasEnoughMana(amount)) {
            setCurrentMana(getCurrentMana() - amount);
            return true;
        }
        return false;
    }

    @Override
    public int getMarkDimensionID() {
        return this.MarkDimensionID;
    }


    public void fromAM2NBTMessage(AM2NBTMessage input) {
        this.setCurrentMana(input.getCurrMana());
        this.setCurrentBurnout(input.getCurrBurnout());
        this.setCurrentLevel(input.getCurrLevel());
        this.setCurrentXP(input.getCurrXP());

        this.setMarkX(input.getMarkX());
        this.setMarkY(input.getMarkY());
        this.setMarkZ(input.getMarkY());
        this.setMarkDimensionID(input.getDimID());

        doUpdate = true;
    }

    public AM2Capabilities fromNBT(NBTBase input) {
        NBTTagCompound am2tag = NBTUtils.getAM2Tag((NBTTagCompound)input);
        this.setCurrentMana(am2tag.getFloat("CurrentMana"));
        this.setCurrentLevel(am2tag.getInteger("CurrentLevel"));
        this.setCurrentXP(am2tag.getFloat("CurrentXP"));
        this.setCurrentBurnout(am2tag.getFloat("CurrentBurnout"));

        this.setMarkX(am2tag.getDouble("MarkX"));
        this.setMarkY(am2tag.getDouble("MarkY"));
        this.setMarkZ(am2tag.getDouble("MarkZ"));
        this.setMarkDimensionID(am2tag.getInteger("MarkDimensionID"));
        return this;
    }


    public AM2Capabilities IncreaseLevelWithMana(int i) {
        this.setMagicLevelWithMana(this.getCurrentLevel() + i);
        doUpdate = true;
        return this;
    }

    public AM2Capabilities IncreaseLevel(int i) {
        this.setCurrentLevel(this.getCurrentLevel() + i);
        doUpdate = true;
        return this;
    }

    public AM2Capabilities IncreaseXP(float i) {
        this.setCurrentXP(this.getCurrentXP() + i);
        doUpdate = true;
        return this;
    }
}
