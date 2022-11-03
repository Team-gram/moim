package ajou.gram.moim.util.enumeration;

public class EnumContract {

    private JoinStatus joinStatus;

    public enum JoinStatus implements EnumInterface{
        EXIST("1"),
        NONE("0");

        private String value;

        JoinStatus(String value) {
            this.value = value;
        }

        @Override
        public String getKey() {
            return name();
        }

        public String getValue() {
            return value;
        }
    }
}
