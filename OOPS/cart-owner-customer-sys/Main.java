import java.util.HashMap;
import java.util.Scanner;

class Product{
    int p_id;
    String product_Name;
    int product_Price;
    int count;

    Product(int p_id , String product_Name , int product_Price , int count){
        this.p_id = p_id;
        this.product_Name = product_Name;
        this.product_Price = product_Price;
        this.count = count;
    }

    @Override
    public String toString() {
        return "Product{" +
                "p_id=" + p_id +
                ", product_Name='" + product_Name + '\'' +
                ", product_Price=" + product_Price +
                ", count=" + count +
                '}';
    }
}


class Owner{
    int p_id;
    String product_Name;
    int product_Price;

    Owner(int p_id , String product_Name , int product_Price){
        this.p_id = p_id;
        this.product_Name = product_Name;
        this.product_Price = product_Price;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "p_id=" + p_id +
                ", product_Name='" + product_Name + '\'' +
                ", product_Price=" + product_Price +
                '}';
    }
}


class Customer{
    int p_id;
    String product_Name;
    int product_Price;
    int count;

    Customer(int p_id , String product_Name , int product_Price){
        this.p_id = p_id;
        this.product_Name = product_Name;
        this.product_Price = product_Price;
        this.count=0;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "p_id=" + p_id +
                ", product_Name='" + product_Name + '\'' +
                ", product_Price=" + product_Price +
                ", count=" + count +
                '}';
    }
}


class cart{
    
    int p_id;
    String product_Name;
    int product_Price;
    boolean isAvailable;
    int count;
    int total_price;

    cart(int p_id , String product_Name , int product_Price , boolean isAvailable){
        this.p_id = p_id;
        this.product_Name = product_Name;
        this.product_Price = product_Price;
        this.isAvailable = isAvailable;
        this.count=0;
        this.total_price = 0;
    }

    @Override
    public String toString() {
        return "cart{" +
                "p_id=" + p_id +
                ", product_Name='" + product_Name + '\'' +
                ", product_Price=" + product_Price +
                ", isAvailable=" + isAvailable +
                ", count=" + count +
                ", total_price=" + total_price +
                '}';
    }
}


class Owner_Operations{
    
    static HashMap<Integer , Product> productHashMap = new HashMap<>();

    static void create_Product(Scanner sc){
        System.out.println("Enter Product ID :");
        int p_id = sc.nextInt();
        System.out.println("Enter Product Name :");
        String product_Name = sc.next();
        System.out.println("Enter Product Price :");
        int product_Price = sc.nextInt();
        System.out.println("Enter Product Count :");
        int count = sc.nextInt();

        Product product = new Product(p_id , product_Name , product_Price , count);
        productHashMap.put(p_id, product);
        System.out.println("Product " + product.product_Name + " Has Been created ....");
        System.out.println(product.toString());
    }


    static void update_Product(int p_id , Scanner sc){
        Product product = productHashMap.get(p_id);

        System.out.println("Type 1 to update Product Name..");
        System.out.println("Type 2 to update Product Price");
        System.out.println("Type 3 to update Product Count");

        int input = sc.nextInt();

        switch (input){
            case 1:
                System.out.println("Enter New Product Name :");
                String new_product_Name = sc.next();
                product.product_Name = new_product_Name;
                break;
            case 2:
                System.out.println("Enter New Product Price :");
                int new_product_Price = sc.nextInt();
                product.product_Price = new_product_Price;
                break;
            case 3:
                System.out.println("Enter New Product Count :");
                int new_count = sc.nextInt();
                product.count = new_count;
                break;
            default:
                System.out.println("Invalid Input ..");
        }

        productHashMap.put(p_id, product);
        System.out.println("Product " + product.product_Name + " Has Been Updated ....");
        System.out.println(product.toString());
    } 

    static void view_All_Products(){
        System.out.println("The complete list of All Products is :");
        System.out.println(productHashMap);
    }

    static void remove_Product(int p_id){
        Product product = productHashMap.get(p_id);
        if(product != null){
            productHashMap.remove(p_id);
            System.out.println("Product removed successfully ....");
        }else{
            System.out.println("Product Not Found ....");
        }
    }
}


class Customer_Operations{
    
    static HashMap<Integer , Product> productHashMap = Owner_Operations.productHashMap;
    static HashMap<Integer , cart> cartHashMap = new HashMap<>();

   static void add_to_cart(int p_id, Scanner sc) {
        Product product = productHashMap.get(p_id);

        if (product.count <= 0) {
            System.out.println("Product Not Available ....");
            return;
        }

        System.out.println("Enter Count of Product to Add to Cart :");
        int count = sc.nextInt();

        if (count > product.count) {
            System.out.println("Not Enough Product in Stock ....");
            return;
        }

        cart cart_item = new cart(
            product.p_id,
            product.product_Name,
            product.product_Price,
            true
        );

        cart_item.count = count;
        cart_item.total_price = count * product.product_Price;

        cartHashMap.put(p_id, cart_item);

        System.out.println("Product " + product.product_Name + " Has Been Added to Cart ....");
        System.out.println(cart_item);
}

    static void remove_from_cart(int p_id){
            cartHashMap.remove(p_id);
            System.out.println("Product removed successfully ....");
    }

    static void view_all_products(){
        System.out.println("The complete list of All Products is :");
        System.out.println(productHashMap);
    }

    static void view_cart(){
        System.out.println("All Products in Cart :");
        System.out.println(cartHashMap);
    }

    static void checkout(){
        int total_price = 0;
        for(cart cart_item : cartHashMap.values()){
            total_price += cart_item.total_price;
        }
        System.out.println("Total Price : " + total_price);
    }
}


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("Type 1 for Owner Operations ..");
            System.out.println("Type 2 for Customer Operations ..");
            System.out.println("Type 3 to Exit ..");
            int input = sc.nextInt();
            switch (input){
                case 1:
                    System.out.println("Type 1 to Create Product ..");
                    System.out.println("Type 2 to Update Product ..");
                    System.out.println("Type 3 to View All Products ..");
                    int owner_input = sc.nextInt();
                    switch (owner_input){
                        case 1:
                            Owner_Operations.create_Product(sc);
                            break;
                        case 2:
                            System.out.println("Enter Product ID to Update :");
                            int p_idToUpdate = sc.nextInt();
                            Owner_Operations.update_Product(p_idToUpdate , sc);
                            break;
                        case 3:
                            Owner_Operations.view_All_Products();
                            break;
                        case 4:
                            System.out.println("Enter Product ID to Remove :");
                            int p_idToRemove = sc.nextInt();
                            Owner_Operations.remove_Product(p_idToRemove);
                            break;
                        default:
                            System.out.println("Invalid Input ..");
                    }
                    break;
                case 2:
                    System.out.println("Type 1 to View All Products ....");
                    System.out.println("Type 2 to Add Product to Cart ....");
                    System.out.println("Type 3 to Remove Product from Cart ....");
                    System.out.println("Type 4 to View Cart ..");
                    System.out.println("Type 5 to Checkout ..");
                    int customer_input = sc.nextInt();
                    switch (customer_input){
                        case 1:
                            Customer_Operations.view_all_products();
                            break;
                        case 2:
                            System.out.println("Enter Product ID to Add to Cart :");
                            int p_idToAdd = sc.nextInt();
                            Customer_Operations.add_to_cart(p_idToAdd , sc);
                            break;
                        case 3:
                            System.out.println("Enter Product ID to Remove from Cart :");
                            int p_idToRemove = sc.nextInt();
                            Customer_Operations.remove_from_cart(p_idToRemove);
                            break;
                        case 4:
                            Customer_Operations.view_cart();
                            break;
                        case 5:
                            Customer_Operations.checkout();
                            break;
                        default:
                            System.out.println("Invalid Input ..");
                    }
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.out.println("Invalid Input ..");
            }
        }
    }
}
