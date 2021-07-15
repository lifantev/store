package com.nedu.store;

import com.nedu.store.order.Basket;
import com.nedu.store.product.Product;
import com.nedu.store.user.User;
import com.nedu.store.user.UserService;
import com.nedu.store.user.UserServiceImpl;
import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    final static String MENU = """
            --menu
            --sign up
            --sign in
            --add item
            --delete item
            --show items
            --add to card
            --remove from card
            --purchase card
            --exit
            """;

    public static void main(String[] args) {
        Scanner command = new Scanner(System.in);

        Logger logger = Logger.getLogger("Store");
        logger.info("Program started");

        UserService userService = new UserServiceImpl(userDao);
        logger.trace("User service created");
        //ProductManagementService productMS = new ProductManagementServiceImpl(productDao);
        logger.trace("Product management service created");
        //OrderService orderService = new OrderServiceImpl(userDao, productDao, orderDao);
        logger.trace("Order service created");

        logger.debug("Store services initialized");

        System.out.println("Welcome to Store\n" + MENU);
        boolean running = true;
        while (running) {
            switch (command.nextLine()) {

                case "--sign up":
                    logger.info("Sign up attempt");

                    System.out.println("Create login:");
                    String login = command.nextLine();
                    System.out.println("Create password:");
                    String password = command.nextLine();
                    Basket basket = new Basket(new LinkedList<>());
                    User signUpUser = new User(login, password, basket);

                    if (userService.signUp(signUpUser)) {
                        System.out.println("Account created. Hello " + login);

                        logger.debug("User " + signUpUser + " signed up");
                    } else {
                        System.out.println("Please try again");

                        logger.debug("Sign up error");
                    }

                    logger.info("Sign up ended");
                    break;

                case "--sign in":
                    logger.info("Sign in attempt");

                    System.out.println("Enter login:");
                    String signInLogin = command.nextLine();
                    System.out.println("Enter password:");
                    String signInPassword = command.nextLine();

                    User signInUser = new User();
                    signInUser.setLogin(signInLogin);
                    signInUser.setPassword(signInPassword);
                    if (userService.signIn(signInUser) != null) {
                        System.out.println("Hello " + signInLogin);

                        logger.debug("User " + signInUser+ " signed in");
                    } else {
                        System.out.println("Please try again");

                        logger.debug("Sign in error");
                    }

                    logger.info("Sign in ended");
                    break;

                case "--add item":
                    logger.info("Adding item to store");

                    Product product = new Product();
                    System.out.println("Enter name:");
                    product.setName(command.nextLine());
                    System.out.println("Enter cost:");
                    product.setCost(command.nextDouble());
                    System.out.println("Enter quantity:");
                    product.setQuantity(command.nextLong());

                    if (productMS.add(product)) {
                        logger.debug("Item " + product + " added");
                    } else {
                        logger.debug("Item adding error");
                    }

                    logger.info("Adding procedure ended");
                    break;

                case "--show items":
                    logger.info("Showing products");

                    productMS.show();

                    logger.debug("Exit from showing mode");
                    break;

                case "--delete item":
                    logger.info("Deleting item");

                    System.out.println("Enter product id:");
                    long id = command.nextLong();
                    if (productMS.delete(id)) {
                        logger.debug("Product with id: " + id + " deleted");
                    } else {
                        logger.debug("Product deleting error, id: " + id);
                    }

                    logger.info("Deleting procedure ended");
                    break;

                case "--add to card":
                    System.out.println("Enter product id:");
                    long idInCard = command.nextLong();
                    //orderService.addToBasket(userService.getCurrentUser().getBasket(), )
                    break;

                case "--menu":
                    System.out.println("\n" + MENU + "\n");
                    break;

                case "--exit":
                    System.out.println("Store Closed");
                    running = false;
                    break;

                default:
                    System.out.println("Command not recognized");
                    break;
            }
        }
        command.close();
    }
}
