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
        if ((this.xAxis >= 1 && this.xAxis < 4) && (this.yAxis >= 1 && this.yAxis < 4)) {
            return 1;
        } else if ((this.xAxis >= 4 && this.xAxis < 7) && (this.yAxis >= 1 && this.yAxis < 4)) {
            return 2;
        } else if ((this.xAxis >= 7 && this.xAxis < 10) && (this.yAxis >= 1 && this.yAxis < 4)) {
            return 3;
        } else if ((this.xAxis >= 1 && this.xAxis < 4) && (this.yAxis >= 4 && this.yAxis < 7)) {
            return 4;
        } else if ((this.xAxis >= 4 && this.xAxis < 7) && (this.yAxis >= 4 && this.yAxis < 7)) {
            return 5;
        } else if ((this.xAxis >= 7 && this.xAxis < 10) && (this.yAxis >= 4 && this.yAxis < 7)) {
            return 6;
        } else if ((this.xAxis >= 1 && this.xAxis < 4) && (this.yAxis >= 7 && this.yAxis < 10)) {
            return 7;
        } else if ((this.xAxis >= 4 && this.xAxis < 7) && (this.yAxis >= 7 && this.yAxis < 10)) {
            return 8;
        } else if ((this.xAxis >= 7 && this.xAxis < 10) && (this.yAxis >= 7 && this.yAxis < 10)) {
            return 9;
        }
        return 0; //nigdy nie zwroci zera, ale checkstyle się czepia ze metoda nic nie zwraca, wiec wpisalem
    }

    public int getxAxis() {
        return xAxis;
    }

    public int getyAxis() {
        return yAxis;
    }

    public int getSector() {
        return sector;
    }

    public int getValue() {
        return value;
    }
}
