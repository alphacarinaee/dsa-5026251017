package LW01.prelab;

public class ColourPrint extends PrintJob {

    public ColourPrint(String id, int pages) {
        super(id, pages);
    }

    @Override
    public int calculateCharge() {
        int pages = getPages();
        int total = 0;

        if (pages <= 10) {
            total = pages * 1500;
        } else {
            total = (10 * 1500) + ((pages - 10) * 1000);
        }

        // tambah biaya setup 2000
        total = total + 2000;

        return total;
    }

    @Override
    public String label() {
        return "Colour";
    }
}