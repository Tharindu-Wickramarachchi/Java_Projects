public class Clothing extends Product{
    protected String color;
    protected String size;
    
    public Clothing(String productType,String productID,String productName,int numAvailableItems,double price,String color,String size){
        super(productType,productID,productName,numAvailableItems,price);
        this.color=color;
        this.size=size;
    }

    public String getColor(){
        return color;
    }
    public void setColor(String color){
        this.color=color;
    }
    public String getSize(){
        return size;
    }
    public void setSize(String size){
        this.size=size;
    }

    public String toString() {
        return "Product type : "+productType+"\nProduct Id : " + productID + "\nProduct name : " + productName + "\nNumber of available items : " + numAvailableItems + "\nPrice : " + price + "\nProduct color : " + color + "\nProduct size : " + size;
    }

}
