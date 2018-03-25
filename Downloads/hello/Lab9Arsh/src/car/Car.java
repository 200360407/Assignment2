package car;
/**
 *
 * @author Arshpreet
 */
public class Car {

    private String make, model;
    private float mileage;
    private int year;

    public Car(String make, String model, float mileage, int year) {
        setMake(make);
        setModel(model);
        setMileage(mileage);
        setYear(year);
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public float getMileage() {
        return mileage;
    }

    public void setMileage(float mileage) {
        this.mileage = mileage;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    

}
