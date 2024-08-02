public abstract class Product{

    protected String productID;
    protected String productName;
    protected int numAvailableItems;
    protected double price;
    protected String productType;

    Product(String productType,String productID,String productName,int numAvailableItems,double price){
        this.productType=productType;
        this.productID=productID;
        this.productName=productName;
        this.numAvailableItems=numAvailableItems;
        this.price=price;
    }


    public String getProductType(){
        return productType;
    }

    public String getProductID(){
        return productID;
    }
    public void setProductID(String productID){
        this.productID=productID;
    }
    public String getProductName(){
        return productName;
    }
    public void setProductName(String productName){
        this.productName=productName;
    }
    public int getNumAvailableItems(){
        return numAvailableItems;
    }
    public void setNumAvailableItems(int numAvailableItems){
        this.numAvailableItems=numAvailableItems;
    }
    public double getPrice(){
        return price;
    }
    public void setPrice(double price){
        this.price=price;
    }



}
