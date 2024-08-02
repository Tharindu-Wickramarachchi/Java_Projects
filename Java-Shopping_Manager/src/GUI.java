
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.*;
import javax.swing.JButton;


public class GUI {
    private JFrame mainFrame;

    private JFrame cartFrame;
    private JComboBox<String> comboBox;
    private JTable table;
    private JTable tableSc;
    private JTextArea detailsArea;
    private JComboBox<String> typeComboBox;
    private JButton addToCartButton;

    ArrayList<Product> allData;
    ArrayList<Product> selectProductList = new ArrayList<>();

    public GUI(ArrayList<Product> initialList) {
        allData=new ArrayList<>(initialList);
    }

    JPanel cartCenter=new JPanel();



    public void frame() {


        //---------------------------Westminster Shopping Center----------------------------//

        mainFrame = new JFrame();
        mainFrame.setTitle("Westminster Shopping Center");
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(900, 700);
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setResizable(false);

        JPanel topPanel=new JPanel();
        JPanel bottomPanel=new JPanel();
        JPanel eastPanel=new JPanel();
        JPanel westPanel=new JPanel();
        JPanel centerPanel=new JPanel();

        JPanel bottomSubPanel=new JPanel();


        topPanel.setPreferredSize(new Dimension(900,70));
        bottomPanel.setPreferredSize(new Dimension(900,300));
        eastPanel.setPreferredSize(new Dimension(40,600));
        westPanel.setPreferredSize(new Dimension(40,600));
        centerPanel.setPreferredSize(new Dimension(800,320));

        bottomSubPanel.setPreferredSize(new Dimension(900,50));

        mainFrame.add(topPanel,BorderLayout.NORTH);
        mainFrame.add(bottomPanel,BorderLayout.SOUTH);
        mainFrame.add(eastPanel,BorderLayout.EAST);
        mainFrame.add(westPanel,BorderLayout.WEST);
        mainFrame.add(centerPanel,BorderLayout.CENTER);

        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.add(bottomSubPanel,BorderLayout.SOUTH);


        //Create a DefaultTableModel with some data and column names
        String[] columnNames = {"Product ID", "Product Name", "Category", "Price", "Info"};

        // Create a comparator based on the ProductID
        Comparator<Product> idComparator = Comparator.comparing(Product::getProductID);
        // Sort the list using the custom comparator
        Collections.sort(allData, idComparator);

        Object[][] data = new Object[allData.size()][5];
        for (int i = 0; i < allData.size(); i++) {
            Product product = allData.get(i);

            if(allData.get(i).getProductType().equals("Electronics")) {
                Electronics electronicsProduct = (Electronics) product;

                data[i][0] = electronicsProduct.getProductID();
                data[i][1] = electronicsProduct.getProductName();
                data[i][2] = electronicsProduct.getProductType();
                data[i][3] = electronicsProduct.getPrice();
                data[i][4] = electronicsProduct.getBrand() + ", " + electronicsProduct.getWarrantyPeriod();
            } else {
                Clothing clothingProduct = (Clothing) product;

                data[i][0] = clothingProduct.getProductID();
                data[i][1] = clothingProduct.getProductName();
                data[i][2] = clothingProduct.getProductType();
                data[i][3] = clothingProduct.getPrice();
                data[i][4] = clothingProduct.getSize() + ", " + clothingProduct.getColor();
            }

        }


        // Create a table model with the data and column names
        DefaultTableModel model = new DefaultTableModel(data, columnNames){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make the table non-editable
            }
        };

        // Create a JTable with the model
        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(800,280));
        //scrollPane.setLayout(centerPanel,BorderLayout.SOUTH);



        JButton cartButton = new JButton("Shopping Cart");
        //frame.add(cartButton);
        cartButton.setPreferredSize(new Dimension(150,30));
        //cartButton.setPreferredSize(new Dimension(150,30));
        cartButton.setFocusable(false);
        cartButton.addActionListener(e -> {
            cartFrame.setVisible(true);
        });


        JPanel filterPanel = new JPanel();
        filterPanel.add(new JLabel("Select Product Cattogery "));


        // Populate the product types statically
        Set<String> productTypes = new HashSet<>();
        productTypes.add("Electronics");
        productTypes.add("Clothing");

        // Create a JComboBox for selecting product types
        typeComboBox = new JComboBox<>(productTypes.toArray(new String[0]));
        typeComboBox.insertItemAt("All", 0);
        typeComboBox.setSelectedIndex(0);
        typeComboBox.setPreferredSize(new Dimension(150,30));

        typeComboBox.addActionListener(e -> {
            String selectedType = (String) typeComboBox.getSelectedItem();
            filterTableByType(selectedType);
        });



        Border border= BorderFactory.createLineBorder(Color.black, 1);
        // Create a JTextArea for displaying details
        detailsArea = new JTextArea();
        detailsArea.setEditable(false);
        detailsArea.setBackground(Color.WHITE);
        detailsArea.setPreferredSize(new Dimension(900,300));
        detailsArea.setVisible(true);
        bottomPanel.setBorder(border);



        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {

                        displayProductDetails(allData.get(selectedRow));
                    }
                }
            }
        });



        addToCartButton=new JButton("Add to Shopping Cart");
        addToCartButton.setPreferredSize(new Dimension(200,25));
        addToCartButton.setFocusable(false);

        cartButton.addActionListener(e -> {
            viewCart();
            cartButton.setVisible(true);
        });





        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER,10,20));
        topPanel.add(filterPanel);
        topPanel.add(typeComboBox);
        topPanel.add(cartButton);

        centerPanel.setLayout(new BorderLayout());
        centerPanel.add(scrollPane,BorderLayout.NORTH);

        bottomPanel.add(detailsArea);
        bottomSubPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        bottomSubPanel.add(addToCartButton);
        bottomSubPanel.setBackground(Color.WHITE);



    }




    private void displayProductDetails(Product product) {

        if(product.getProductType().equals("Electronics")) {
            Electronics electronicsProduct = (Electronics) product;
            // Display details in the JTextArea
            detailsArea.setText(
                    "\n\n\tSelected Product - Details"+
                            "\n\n\tProduct ID: " + electronicsProduct.getProductID() +
                            "\n\tName: " + electronicsProduct.getProductName() +
                            "\n\tCategory: " + electronicsProduct.getProductType() +
                            "\n\tBrand: " + electronicsProduct.getBrand() +
                            "\n\tWarranty period: " + electronicsProduct.getWarrantyPeriod() +
                            "\n\tItems Available: " + product.getNumAvailableItems() +
                            "\n\tPrice: $" + electronicsProduct.getPrice()+"\n"
            );
        } else {
            Clothing clothingProduct = (Clothing) product;

            detailsArea.setText(
                    "\n\n\tSelected Product - Details"+
                            "\n\n\tProduct ID: " + clothingProduct.getProductID() +
                            "\n\tName: " + clothingProduct.getProductName() +
                            "\n\tCategory: " + clothingProduct.getProductType() +
                            "\n\tSize: " + clothingProduct.getSize() +
                            "\n\tColor: " + clothingProduct.getColor() +
                            "\n\tItems Available: " + clothingProduct.getNumAvailableItems() +
                            "\n\tPrice: $" + clothingProduct.getPrice()+"\n"
            );
        }


        addToCartButton.addActionListener(e -> {

            selectProductList.add(product);

        });

    }



    private void filterTableByType(String selectedType) {
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>((DefaultTableModel) table.getModel());
        table.setRowSorter(sorter);

        if (!"All".equals(selectedType)) {
            sorter.setRowFilter(RowFilter.regexFilter(selectedType, 2));
        } else {
            sorter.setRowFilter(null);
        }
    }


    //---------------------------Shopping Cart----------------------------//
    private void viewCart() {

        cartFrame = new JFrame();
        cartFrame.setTitle("Shopping Cart");
        cartFrame.setVisible(false);
        cartFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        cartFrame.setSize(800, 500);
        cartFrame.setLayout(new BorderLayout());
        cartFrame.setResizable(false);


        JPanel cartTop=new JPanel();
        JPanel cartBottom=new JPanel();
        JPanel cartEast=new JPanel();
        JPanel cartWest=new JPanel();


        cartTop.setPreferredSize(new Dimension(800,70));
        cartBottom.setPreferredSize(new Dimension(800,200));
        cartEast.setPreferredSize(new Dimension(50,500));
        cartWest.setPreferredSize(new Dimension(50,500));
        cartCenter.setPreferredSize(new Dimension(700,200));

        cartFrame.add(cartTop,BorderLayout.NORTH);
        cartFrame.add(cartBottom,BorderLayout.SOUTH);
        cartFrame.add(cartEast,BorderLayout.EAST);
        cartFrame.add(cartWest,BorderLayout.WEST);
        cartFrame.add(cartCenter,BorderLayout.CENTER);



        String[] cartColumns = {"Product", "Quantity", "Price"};

       Object[][] fullCartData = new Object[selectProductList.size()][3];

            // Create a table model with the data and column names
        DefaultTableModel modelSc = new DefaultTableModel(fullCartData, cartColumns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make the table non-editable
            }
        };

        // Create a JTable with the model
        tableSc = new JTable(modelSc);

        JScrollPane scrollPaneSc = new JScrollPane(tableSc);
        scrollPaneSc.setPreferredSize(new Dimension(800, 280));
        cartCenter.add(scrollPaneSc);
    }


}

















