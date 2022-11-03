package ajou.gram.moim.util.enumeration;

public class EnumValue {
    private String key;
    private String value;

    public EnumValue(EnumInterface enumInterface) {
        key = enumInterface.getKey();
        value = enumInterface.getValue();
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
