import java.util.ArrayList;

public class ShoppingCart {

    ArrayList<Product> productLists=new ArrayList< >();


    public void addElectronics(Electronics addElectronics){
        productLists.add(addElectronics);
    }

    public void addClothing(Clothing addClothing){
        productLists.add(addClothing);
    }

    public  void remove(Product element){
        productLists.remove(element);

    }
    public void total(){

    }

}
