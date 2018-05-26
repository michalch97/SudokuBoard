
public enum DifficultyLevel {
    Easy(0), Medium(1), Hard(2);

    private final int value;
    private static final DifficultyLevel[] valueList = DifficultyLevel.values();

    private DifficultyLevel(int id) {
        this.value = id;
    }

    public int getValue() {
        return value;
    }

    public static DifficultyLevel fromInteger(int x) {
        if (x < valueList.length) {
            return valueList[x];
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        switch (fromInteger(value)) {
            case Easy:
                return "Łatwy";
            case Medium:
                return "Średni";
            case Hard:
                return "Trudny";
            default:
                return null;
        }
    }
}
