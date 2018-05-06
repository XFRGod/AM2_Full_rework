package am2.network.messages;

import am2.capabilities.AM2Capabilities;
import am2.utils.LogHelper;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class AM2NBTMessage implements IMessage {

    public AM2NBTMessage(float CurrentMana, int CurrentLevel, float CurrentXP,
                         float CurrentBurnout, double MarkX, double MarkY, double MarkZ, int DimensionID, boolean DoUpdate){
        currMana = CurrentMana;
        currLevel = CurrentLevel;
        currXP = CurrentXP;
        currBurnout = CurrentBurnout;

        markX = MarkX;
        markY = MarkY;
        markZ = MarkZ;
        dimID = DimensionID;

        doUpdate = DoUpdate;
    }
    
    public AM2NBTMessage(AM2Capabilities input) {
        currMana    =   input.getCurrentMana();
        currLevel   =   input.getCurrentLevel();
        currXP      =   input.getCurrentXP();
        currBurnout =   input.getCurrentBurnout();
        
        markX = input.getMarkX();
        markY = input.getMarkY();
        markZ = input.getMarkZ();
        dimID = input.getMarkDimensionID();

        doUpdate = input.getDoUpdate();
    }

    public AM2NBTMessage() {
    }

    public float getCurrMana() {
        return currMana;
    }

    public int getCurrLevel() {
        return currLevel;
    }

    public float getCurrXP() {
        return currXP;
    }

    public float getCurrBurnout() {
        return currBurnout;
    }

    public double getMarkX() {
        return markX;
    }

    public double getMarkY() {
        return markY;
    }

    public double getMarkZ() {
        return markZ;
    }

    public int getDimID() {
        return dimID;
    }

    public boolean getDoUpdate() { return doUpdate; }

    float   currMana;
    int     currLevel;
    float   currXP;
    float   currBurnout;

    double  markX;
    double  markY;
    double  markZ;
    int     dimID;

    boolean doUpdate;


    /*

        NBTTagCompound am2tag = NBTUtils.getAM2Tag((NBTTagCompound)nbt);
        instance.setCurrentMana(am2tag.getFloat("CurrentMana"));
        instance.setCurrentLevel(am2tag.getInteger("CurrentLevel"));
        instance.setCurrentXP(am2tag.getFloat("CurrentXP"));
        instance.setCurrentBurnout(am2tag.getFloat("CurrentBurnout"));

        instance.setMarkX(am2tag.getDouble("MarkX"));
        instance.setMarkY(am2tag.getDouble("MarkY"));
        instance.setMarkZ(am2tag.getDouble("MarkZ"));
        instance.setMarkDimensionID(am2tag.getInteger("MarkDimensionID"));
     */

    @Override
    public void fromBytes(ByteBuf buf) {
        try {
            currMana = buf.readFloat();
            currLevel = buf.readInt();
            currXP = buf.readFloat();
            currBurnout = buf.readFloat();

            markX = buf.readDouble();
            markY = buf.readDouble();
            markZ = buf.readDouble();
            dimID = buf.readInt();

            doUpdate = buf.readBoolean();
        } catch (Exception e) {
            LogHelper.error("Error reading NetworkMessage.\n Stacktrace: " + e);
        }
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeFloat(currMana);
        buf.writeInt(currLevel);
        buf.writeFloat(currXP);
        buf.writeFloat(currBurnout);
        
        buf.writeDouble(markX);
        buf.writeDouble(markY);
        buf.writeDouble(markZ);
        buf.writeInt(dimID);

        buf.writeBoolean(doUpdate);
    }
}
