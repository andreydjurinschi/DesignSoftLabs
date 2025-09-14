package lab01.user_mask_tests;

public class FieldMaskTestClass {
    public static final byte ID = 0b00001;
    public static final byte  NAME = 0b00010;
    public static final byte AGE = 0b00100;
    public static final byte RATING = 0b01000;
    public static final byte ROLE = 0b10000;

    public static byte createMask(byte... fields){
        byte mask = 0;
        for(byte p : fields){
            mask |= p;
        }
        return mask;
    }
}
