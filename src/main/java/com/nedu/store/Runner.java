package com.nedu.store;

import com.nedu.store.order.OrderService;
import com.nedu.store.product.Product;
import com.nedu.store.product.ProductManagementService;
import com.nedu.store.user.User;
import com.nedu.store.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Slf4j
@Component
public class Runner implements CommandLineRunner {

    final UserService userService;
    final ProductManagementService productMS;
    final OrderService orderService;

    private static User currentUser;

    private final static String MENU = """
            --menu
            --sign up
            --sign in
            --add item
            --update item
            --delete item
            --show items
            --add to card
            --remove from card
            --purchase card
            --exit
            """;

    public Runner(UserService userService, ProductManagementService productMS, OrderService orderService) {
        this.userService = userService;
        this.productMS = productMS;
        this.orderService = orderService;
    }

    @Override
    public void run(String[] args) {
        Scanner command = new Scanner(System.in);

        System.out.println("Welcome to Store\n" + MENU);

        boolean running = true;
        while (running) {
            switch (command.nextLine()) {

                case "--sign up":

                    User user = User.builder().build();
                    System.out.println("Create login:");
                    user.setLogin(command.nextLine());
                    System.out.println("Create password:");
                    user.setPassword(command.nextLine());

                    try {
                        currentUser = userService.signUp(user);
                    } catch (Exception e) {
                        log.warn("User wasn't signed up");
                    }

                    break;

                case "--sign in":

                    user = User.builder().build();
                    System.out.println("Enter login:");
                    user.setLogin(command.nextLine());
                    System.out.println("Enter password:");
                    user.setPassword(command.nextLine());

                    try {
                        currentUser = userService.signIn(user);
                    } catch (Exception e) {
                        log.warn("User wasn't signed in");
                    }

                    break;

                case "--add item":

                    Product product = Product.builder().build();
                    System.out.println("Enter name:");
                    product.setName(command.nextLine());
                    System.out.println("Enter cost:");
                    product.setCost(command.nextDouble());

                    productMS.add(product);

                    break;

                case "--update item":

                    product = Product.builder().build();
                    System.out.println("Enter id:");
                    product.setId(Long.parseLong(command.nextLine()));
                    System.out.println("Enter name:");
                    product.setName(command.nextLine());
                    System.out.println("Enter cost:");
                    product.setCost(Double.parseDouble(command.nextLine()));

                    productMS.update(product);

                    break;

                case "--show items":

                    productMS.show();

                    break;

                case "--delete item":
                    System.out.println("Enter product id:");

                    productMS.delete(Long.parseLong(command.nextLine()));

                    break;

                case "--add to card":
                    System.out.println("Enter product id:");

                    orderService.addToBasket(currentUser.getId(),
                            Long.parseLong(command.nextLine()));

                    break;

                case "--remove from card":
                    System.out.println("Enter product id:");

                    orderService.deleteFromBasket(currentUser.getId(),
                            Long.parseLong(command.nextLine()));

                    break;

                case "--purchase card":

                    orderService.purchaseBasket(currentUser.getId(), true);

                    break;

                case "--menu":
                    System.out.println("\n" + MENU + "\n");

                    break;

                case "--exit":
                    System.out.println("Store Closed");
                    running = false;

                    break;

                default:
                    break;
            }
        }
        command.close();
        System.exit(0);
    }
}
