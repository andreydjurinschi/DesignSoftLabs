package lab01.softlab.mask;

import org.springframework.stereotype.Component;

@Component
public final class UserByteFieldMask {

    public static final byte ID     = 0b00001;
    public static final byte NAME   = 0b00010;
    public static final byte AGE    = 0b00100;
    public static final byte RATING = 0b01000;
    public static final byte ROLE   = 0b10000;

    private byte mask = 0;


    public void addField(byte field) {
        this.mask |= field;
    }

    public boolean hasField(byte field) {
        return (mask & field) != 0;
    }

    public boolean hasID()     { return hasField(ID); }
    public boolean hasNAME()   { return hasField(NAME); }
    public boolean hasAGE()    { return hasField(AGE); }
    public boolean hasRATING() { return hasField(RATING); }
    public boolean hasROLE()   { return hasField(ROLE); }




    public byte getMask() {
        return mask;
    }

    public void setMask(byte mask) {
        this.mask = mask;
    }
}
