public class Electronics extends Product{
    protected String brand;
    protected String warrantyPeriod;


    public Electronics(String productType,String productID,String productName,int numAvailableItems,double price,String brand,String warrantyPeriod){
       super(productType,productID,productName,numAvailableItems,price);
       this.brand=brand;
       this.warrantyPeriod=warrantyPeriod;
    }

    public String getBrand(){
        return brand;
    }
    public void setBrand(String brand){
        this.brand=brand;
    }
    public String getWarrantyPeriod(){
        return warrantyPeriod;
    }
    public void setWarrantyPeriod(String warrantyPeriod){
        this.warrantyPeriod=warrantyPeriod;
    }

    public String toString(){
        return "Product type : "+productType+"\nProduct Id : "+productID+"\nProduct name : "+productName+"\nNumber of available items : "+numAvailableItems+"\nPrice : "+price+"\nProduct brand : "+brand+"\nWarranty period : "+warrantyPeriod;
    }

}

