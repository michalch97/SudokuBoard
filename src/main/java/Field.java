public class Field {
    private int xAxis;
    private int yAxis;
    private int sector;
    private int value;
    private int index;

    public Field(int index, int value) {
        this.value = value;
        this.index = index;
        int k = (index + 1) % 9;
        if (k == 0) {
            this.xAxis = 9;
        } else {
            this.xAxis = k;
        }
        if (this.xAxis == 9) {
            this.yAxis = (index + 1) / 9;
        } else {
            this.yAxis = (index + 1) / 9 + 1;
        }
        this.sector = calculateSector();
    }

    public int calculateSector() {
        return (this.xAxis - 1) / 3 + 1 + ((this.yAxis - 1) / 3) * 3;
    }

    public int getXAxis() {
        return xAxis;
    }

    public int getYAxis() {
        return yAxis;
    }

    public int getSector() {
        return sector;
    }

    public int getValue() {
        return value;
    }
}
