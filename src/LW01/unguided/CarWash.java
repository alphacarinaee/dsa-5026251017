
public class CarWash extends WashService {

    public CarWash(String id, int days) {
        super(id, days);
    }

    @Override
    public int calculateCharge() {
        if (getDays() <= 3) {
            return (getDays() * 35000);
            
        } else {
            return (3 * 35000) + (getDays() - 3) * 25000;
        }
    }

    @Override
    public String label() {
        return "Car Wash";
    }
    
}
