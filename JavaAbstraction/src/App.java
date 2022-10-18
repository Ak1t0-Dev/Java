public class App {
    public static void main(String[] args) throws Exception {
        CylinderShape cylinderShape = new CylinderShape(10, 5);
        SphereShape sphereShape = new SphereShape(10);

        System.out.printf("%7.2f%n", cylinderShape.getVolume());
        System.out.printf("%7.2f%n", sphereShape.getVolume());
    }
}
