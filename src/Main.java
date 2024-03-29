import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello and welcome!");

        List<Product> productList= new ArrayList<>();

        Supplier<Integer> randomNumbersSupplier = () -> {
            Random rndm = new Random();
            return rndm.nextInt(1, 10000);
        };
        LocalDate today = LocalDate.now();

        Product book1 = new Product(randomNumbersSupplier.get(), "book1", "Books", 110);
        productList.add(book1);
        Product book2 = new Product(randomNumbersSupplier.get(), "book2", "Books", 120);
        productList.add(book2);
        Product book3 = new Product(randomNumbersSupplier.get(), "book3", "Books", 90);
        productList.add(book3);
        Product toy1 = new Product(randomNumbersSupplier.get(), "toy1", "Baby" ,30);
        productList.add(toy1);
        Product toy2 = new Product(randomNumbersSupplier.get(), "toy2", "Baby" ,40);
        productList.add(toy2);
        Product toy3 = new Product(randomNumbersSupplier.get(), "toy3", "Baby" ,50);
        productList.add(toy3);
        Product videoGame1 = new Product(randomNumbersSupplier.get(), "videogame1", "Boys", 80);
        productList.add(videoGame1);
        Product videoGame2 = new Product(randomNumbersSupplier.get(), "videogame2", "Boys", 80);
        productList.add(videoGame2);
        Product videoGame3 = new Product(randomNumbersSupplier.get(), "videogame3", "Boys", 80);
        productList.add(videoGame3);
//                                                    ESERCIZIO 1
        List<Product> books = productList.stream()
                .filter(product -> product.getCategory().equals("Books"))
                .filter(product -> product.getPrice()>100)
                .collect(Collectors.toList());

        System.out.println("Prodotti nella categoria Books con prezzo > 100:");
        books.forEach(System.out::println);

//                                                    ESERCIZIO 2
        Customer Aldo= new Customer(randomNumbersSupplier.get(), "Aldo", 1);
        Customer Giovanni= new Customer(randomNumbersSupplier.get(), "Giovanni", 2);
        Customer Giacomo= new Customer(randomNumbersSupplier.get(), "giacomo", 3);


        List<Product> babyProduct = productList.stream()
         .filter(product -> product.getCategory().equals("Baby"))
         .collect(Collectors.toList());

        List<Order> listOftoy = new ArrayList<>();

        Order toyOrder1 = new Order(randomNumbersSupplier.get(), "shipped", today.minusDays(5), today, babyProduct, Aldo);
        listOftoy.add(toyOrder1);
        Order toyOrder2 = new Order(randomNumbersSupplier.get(), "shipped", today.minusDays(5), today, books, Giovanni);
        listOftoy.add(toyOrder2);
        Order toyOrder3 = new Order(randomNumbersSupplier.get(), "shipped", today.minusDays(5), today, babyProduct, Giacomo);
        listOftoy.add(toyOrder3);



        List<Order> babyProductOrder = listOftoy.stream()
                .filter(order -> order.getProducts().stream().allMatch(product -> babyProduct.contains(product)))
                .collect(Collectors.toList());



       System.out.println("ordini con prodotti nella categoria Baby");
        babyProductOrder.forEach(System.out::println);

//                                                 ESERCIZIO 3
        List<Product> videoGameDiscount = productList.stream()
                .filter(product -> product.getCategory().equals("Boys"))
                .map(product -> {
                    double prezzoScontato= product.getPrice()* 0.9;
                    product.setPrice(prezzoScontato);
                    return product;
                })
                .collect(Collectors.toList());

        System.out.println("Prodotti nella categoria Boys con lo sconto del 10%:");
        videoGameDiscount.forEach(System.out::println);



    }


}