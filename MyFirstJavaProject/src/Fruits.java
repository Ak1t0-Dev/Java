public class Fruits {
    // Fields
    private String Name;
    private String Color;
    private String Weight;
    private String isTasty;
    private int Price;
    private int Quantity;

    // Default Constructor
    public Fruits() {
        this.Name = "";
        this.Color = "";
        this.Weight = "";
        this.isTasty = "";
        this.Price = 0;
        this.Quantity = 0;
    }

    // Dynamic Constructor
    public Fruits(String Name, String Color, String Weight, String isTasty, int Price, int Quantity) {
        this.Name = Name;
        this.Color = Color;
        this.Weight = Weight;
        this.isTasty = isTasty;
        this.Price = Price;
        this.Quantity = Quantity;
    }

    // adding Quantity method
    public void addQuantity(String Weight, int Price) {
        this.Weight = Weight;
        this.Price = Price;
        this.Quantity += 1;
    }

    // removing Quantity method
    public void removeQuantity(String Weight, int Price) {
        this.Weight = Weight;
        this.Price = Price;
        if (this.Quantity > 0) {
            this.Quantity -= 1;
        }
    }
}
