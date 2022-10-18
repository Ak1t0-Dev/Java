public class SphereShape extends Shape {
    private double r;

    public SphereShape() {
    }

    public SphereShape(double r) {
        this.r = r;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    @Override
    public double getVolume() {
         return (double)4 / 3 * Math.PI * Math.pow(r, 3);
        
    }
}
