package com.nedu.store;

import com.nedu.store.idgenerator.IdGenerator;
import com.nedu.store.idgenerator.SimpleIdGeneratorImpl;
import com.nedu.store.order.OrderService;
import com.nedu.store.order.OrderServiceImpl;
import com.nedu.store.order.dao.OrderDao;
import com.nedu.store.order.dao.OrderDaoImpl;
import com.nedu.store.product.Product;
import com.nedu.store.product.ProductManagementService;
import com.nedu.store.product.ProductManagementServiceImpl;
import com.nedu.store.product.dao.ProductDao;
import com.nedu.store.product.dao.ProductDaoImpl;
import com.nedu.store.user.User;
import com.nedu.store.user.UserService;
import com.nedu.store.user.UserServiceImpl;
import com.nedu.store.user.dao.UserDao;
import com.nedu.store.user.dao.UserDaoImpl;
import org.apache.log4j.Logger;

import java.util.Scanner;

public class Main {
    private static final Logger LOGGER = Logger.getLogger("Store");

    private static User currentUser;

    final static String MENU = """
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

    public static void main(String[] args) {
        Scanner command = new Scanner(System.in);

        IdGenerator idGenerator = new SimpleIdGeneratorImpl();
        OrderDao orderDao = new OrderDaoImpl(idGenerator);
        UserDao userDao = new UserDaoImpl(idGenerator, orderDao);
        ProductDao productDao = new ProductDaoImpl(idGenerator);

        UserService userService = new UserServiceImpl(userDao);
        ProductManagementService productMS = new ProductManagementServiceImpl(productDao);
        OrderService orderService = new OrderServiceImpl(userDao, orderDao, productDao);

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
                        LOGGER.warn("User wasn't signed up");
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
                        LOGGER.warn("User wasn't signed in");
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
    }
}
