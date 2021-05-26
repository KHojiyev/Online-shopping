package module3.Extra.OnlineShopping;

import java.util.*;

public class OnlineMarket {
    public static User[] activeUser = new User[1];
    public static List<User> users = new ArrayList<>();
    public static List<Product> products = new ArrayList<>();
    public static Map<Integer, Category> categories = new TreeMap<>();
    public static Set<String> tempCategory = new LinkedHashSet<>();
    public static String[] namesOfCategories = new String[300];
    public static Map<Integer, String> namesOfCategory = new TreeMap<>();
    public static List<ShoppingBox> shoppingBoxes = new ArrayList<>();
    public static List<Order> orders = new ArrayList<>();
    public static int newCategoryNumber = 12;
    public static double totalPayPrice = 0;

    public static void main(String[] args) {
        initBase();
        System.out.println("==Welcome==");
        while (true) {
            try {
                loginRegisterMenu();
                System.out.print("_");
                Scanner scanner = new Scanner(System.in);
                int loginRegisterChoice = scanner.nextInt();
                switch (loginRegisterChoice) {
                    case 1:
                        login();
                        break;
                    case 2:
                        register();
                        break;
                    case 0:
                        System.out.println("==Bye==");
                        return;
                    default:
                        System.out.println("\nincorrect try!!\n");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nincorrect try!!\n");
            } catch (NullPointerException e) {
                System.out.println("ups something went wrong");
            }
        }
    }

    private static void loginRegisterMenu() {
        System.out.println("----------------------------------------");
        System.out.println("1.Login\n" +
                "2.Register\n" +
                "0.Exit");
        System.out.println("----------------------------------------");
    }

    private static void register() {
        System.out.println("==Register==");
        System.out.println("Role: 1.Costumer 2.Seller 3.Manager  4.Director");
        commandLine(4);
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        if (s.equals("c")) {
            System.out.println();
            return;
        } else {
            int role = Integer.parseInt(s);
            System.out.print("Name: ");
            scanner = new Scanner(System.in);
            String registerName = scanner.nextLine();
            System.out.print("Email: ");
            scanner = new Scanner(System.in);
            String registerEmail = scanner.nextLine();
            System.out.print("Password: ");
            String registerPassword = scanner.nextLine();
            int tries = 3;
            while (true) {
                scanner = new Scanner(System.in);
                System.out.print("Confirm Password(" + tries + "): ");
                String confirmPassword = scanner.nextLine();
                if (registerPassword.equals(confirmPassword)) {
                    switch (role) {
                        case 1:
                            users.add(new User(registerName, registerEmail, registerPassword, Role.Costumer));
                            System.out.println("==Successfully register!!!==");
                            operationCostumer();
                            break;
                        case 2:
                            users.add(new User(registerName, registerEmail, registerPassword, Role.Seller));
                            System.out.println("==Successfully register!!!==");
                            operationSeller();
                            break;
                        case 3:
                            users.add(new User(registerName, registerEmail, registerPassword, Role.Manager));
                            System.out.println("==Successfully register!!!==");
                            operationManager();
                            break;
                        case 4:
                            users.add(new User(registerName, registerEmail, registerPassword, Role.Director));
                            System.out.println("==Successfully register!!!==");
                            operationDirector();
                            break;
                    }

                } else {
                    tries--;
                    System.out.println("confirmPassword is incorrect");
                }
                if (tries == 0) {
                    System.out.println("Error !!!");
                    return;
                }
            }
        }
    }

    private static void login() {
        System.out.println("==Login==");
        System.out.print("Email: ");
        Scanner scanner = new Scanner(System.in);
        String loginEmail = scanner.nextLine();
        System.out.print("Password: ");
        String loginPassword = scanner.nextLine();
        boolean incorrectLogin = true;
        for (User user : users) {
            if (user.getEmail().equals(loginEmail) && user.getPassword().equals(loginPassword)) {
                incorrectLogin = false;
                activeUser[0] = user;
                switch (user.getRole()) {
                    case Costumer:
                        operationCostumer();
                        break;
                    case Seller:
                        operationSeller();
                        break;
                    case Director:
                        operationDirector();
                        break;
                    case Manager:
                        operationManager();
                        break;
                }
            }
        }
        if (incorrectLogin) {
            System.out.println("Incorrect Login Please re Try");
        }


    }

    private static void operationManager() {
        System.out.println("===========Manager Account==============");
        commandLine(0);
    }// empty

    private static void operationDirector() {
        System.out.println("==========Director Account==============");
        commandLine(0);
    }// empty

    private static void operationSeller() {
        while (true) {
            System.out.println("===========Seller Account===============");
            commandLine(7);
            System.out.print("_");
            Scanner scanner = new Scanner(System.in);
            String selectCommand = scanner.nextLine();
            switch (selectCommand) {
                case "a":
                    allProducts();
                    break;
                case "e":
                    editProduct();
                    break;
                case "c":
                    return;
                default:
                    System.out.println(" error command!? ");
                    break;
            }

        }
    }

    private static void allProducts() {
        if (products.size() == 0) {
            System.out.println(" empty ");
        } else {
            for (int i = 0; i < products.size(); i++) {
                System.out.println("№" + i + "|| Name:" + products.get(i).getName() + "|| Sort:" + products.get(i).getSort() +
                        "|| Price:" + products.get(i).getPrice() + "|| Category:" + products.get(i).getCategory().getName() +
                        "|| amount:" + products.get(i).getAmount());
            }
            System.out.println();
        }
    }

    private static void editProduct() {
        while (true) {
            categoryList();
            commandLine(9);
            Scanner scanner = new Scanner(System.in);
            System.out.print("_");
            String editCommand = scanner.nextLine();
            if (editCommand.equals("c")) {
                return;
            } else if (editCommand.equals("a")) {
                addProduct();
            } else if (editCommand.equals("r")) {
                removeProductFromStore();
            } else {
                while (true) {
                    int select = Integer.parseInt(editCommand);
                    productInfos(select);
                    commandLine(4);
                    scanner = new Scanner(System.in);
                    String editItem = scanner.nextLine();
                    if (editItem.equals("c")) {
                        break;
                    } else if (Character.isLetter(editItem.charAt(0)) || editItem.length() > 2) {
                        System.out.println("error command");
                    } else {
                        while (true) {
                            int editItemInt = Integer.parseInt(editItem);
                            productInfo(editItemInt);
                            editMenuForProduct();
                            commandLine(4);
                            scanner = new Scanner(System.in);
                            String choiceStr = scanner.nextLine();

                            if (choiceStr.equals("c")) {
                                break;
                            } else if (Character.isLetter(choiceStr.charAt(0)) || choiceStr.length() > 2) {
                                System.out.println("error command");
                            } else {
                                int choice = Integer.parseInt(choiceStr);
                                switch (choice) {
                                    case 1:
                                        System.out.print("New Name: ");
                                        String newName = scanner.nextLine();
                                        products.get(editItemInt).setName(newName);
                                        System.out.println(" access ");
                                        break;
                                    case 2:
                                        System.out.print("New Type: ");
                                        String newType = scanner.nextLine();
                                        products.get(editItemInt).setSort(newType);
                                        System.out.println(" \n access \n ");
                                        break;
                                    case 3:
                                        System.out.print("New Status(1-true or 2-false): ");
                                        String newStatus = scanner.nextLine();
                                        if (newStatus.equals("1")) {
                                            products.get(editItemInt).setNewOne(true);
                                        } else {
                                            products.get(editItemInt).setNewOne(false);
                                        }
                                        System.out.println("\n access \n");
                                        break;
                                    case 4:
                                        System.out.print("New Category Name: ");
                                        String newCategoryName = scanner.nextLine();
                                        System.out.print("Description:");
                                        String description = scanner.nextLine();
                                        products.get(editItemInt).setCategory(new Category(newCategoryName, description));
                                        System.out.println(" \n access \n ");

                                        break;
                                    case 5:
                                        System.out.println("New Price: ");
                                        double newPrice = scanner.nextDouble();
                                        products.get(editItemInt).setPrice(newPrice);
                                        System.out.println(" \n access \n ");
                                        break;
                                    case 6:
                                        System.out.println("New Amount: ");
                                        double newAmount = scanner.nextDouble();
                                        products.get(editItemInt).setAmount(newAmount);
                                        System.out.println(" access ");
                                        break;
                                }
                            }

                        }
                    }
                }
            }
        }
    }

    private static void editMenuForProduct() {
        System.out.println("----------------------------------------");
        System.out.println("1.Name\n" +
                "2.Type\n" +
                "3.Status\n" +
                "4.Category\n" +
                "5.Price\n" +
                "6.Amount");
        System.out.println("----------------------------------------");
    }

    private static void removeProductFromStore() {
        while (true) {
            categoryList();
            commandLine(10);
            System.out.print("_");
            Scanner scanner = new Scanner(System.in);
            String selectOne = scanner.nextLine();
            if (selectOne.equals("c")) {
                return;
            } else if (selectOne.equals("l")) {
                scanner = new Scanner(System.in);
                commandLine(4);
                System.out.print("Number Category: ");
                String deletingCategory = scanner.nextLine();
                if (deletingCategory.equals("c")) {
                    System.out.println();
                } else {
                    int deleteCat = Integer.parseInt(deletingCategory);
                    for (int i = 1; i <= categories.size(); i++) {
                        if (categories.get(i).getName().equals(namesOfCategory.get(deleteCat - 1))) {
                            categories.put(i, null);
                        }
                    }
                    namesOfCategory.remove(deleteCat - 1);
                    for (int size = products.size() - 1; size >= 0; size--) {
                        if (products.get(size).getCategory().getName().equals(namesOfCategory.get(deleteCat - 1))) {
                            products.remove(size);
                        }
                    }
                    System.out.println("successfully removed!!\n");
                }
                return;
            } else {
                char c = selectOne.charAt(0);
                if (Character.isLetter(c)) {
                    System.out.println("ups wrong attempt!?");
                } else {
                    while (true) {
                        int selectOneProduct = Integer.parseInt(selectOne);
                        productInfos(selectOneProduct);
                        commandLine(6);
                        System.out.print("_");
                        scanner = new Scanner(System.in);
                        String selectOneProd = scanner.nextLine();
                        if (selectOneProd.equals("l")) {
                            for (int size = products.size() - 1; size >= 0; size--) {
                                if (products.get(size).getCategory().getName().equals(namesOfCategory.get(selectOneProduct - 1))) {
                                    products.remove(size);
                                }
                            }
                            System.out.println("successfully removed!!");
                        } else if (selectOneProd.equals("c")) {
                            break;
                        } else {
                            int r = Integer.parseInt(selectOneProd);
                            products.remove(r);
                            System.out.println("successfully removed!!\n");
                        }
                    }
                }
            }
        }
    }

    private static void addProduct() {
        while (true) {
            categoryList();
            commandLine(8);
            System.out.print("_");
            Scanner scanner = new Scanner(System.in);
            String selectCategory = scanner.nextLine();
            if (selectCategory.equals("n")) {
                commandLine(4);
                System.out.print("Category: ");
                String newItemCategory = scanner.nextLine();
                System.out.print("Description: ");
                String newItemDescription = scanner.nextLine();
                System.out.print("Product name: ");
                String newProductName = scanner.nextLine();
                System.out.print("Product type: ");
                String newProductSort = scanner.nextLine();
                System.out.print("Price: ");
                double newProductPrice = scanner.nextDouble();
                System.out.print("Amount: ");
                double newProductAmount = scanner.nextDouble();
                categories.put(newCategoryNumber, new Category(newItemCategory, newItemDescription));
                products.add(new Product(newProductName, newProductSort, true,
                        categories.get(newCategoryNumber + 1), newProductPrice, newProductAmount));
                System.out.println("Product was successfully added!!");
            } else if (selectCategory.equals("c")) {
                System.out.println();
                return;
            } else {
                int i = Integer.parseInt(selectCategory);
                productInfos(i);
                commandLine(8);
                String addingSelect = scanner.nextLine();
                if (addingSelect.equals("c")) {
                    return;
                } else if (addingSelect.equals("n")) {
                    System.out.print("Product name: ");
                    String newProductName = scanner.nextLine();
                    System.out.print("Product type: ");
                    String newProductSort = scanner.nextLine();
                    System.out.print("Amount: ");
                    double newProductAmount = scanner.nextDouble();
                    scanner = new Scanner(System.in);
                    System.out.print("Price: ");
                    double newProductPrice = scanner.nextDouble();
                    scanner = new Scanner(System.in);
                    System.out.print("Confirm password: ");
                    String confirmPassword = scanner.nextLine();
                    if (confirmPassword(confirmPassword)) {
                        products.add(new Product(newProductName, newProductSort,
                                true, categories.get(i + 1), newProductPrice, newProductAmount));
                        System.out.println("Product was successfully added!!");
                    } else System.out.println("password not correct!?");
                    break;
                } else {

                }
                int b = Integer.parseInt(addingSelect);
                System.out.print("Amount: ");
                double newProductAmount = scanner.nextDouble();
                System.out.print("Confirm password: ");
                scanner = new Scanner(System.in);
                String confirmPassword = scanner.nextLine();
                if (confirmPassword(confirmPassword)) {
                    products.get(b).setAmount(products.get(b).getAmount() + newProductAmount);
                    System.out.println("Product was successfully added!!");
                } else System.out.println("password not correct!?");

            }

        }
    }

    private static boolean confirmPassword(String password) {
        if (password.equals(activeUser[0].getPassword())) {
            return true;
        } else
            return false;
    }

    private static void operationCostumer() {
        System.out.println("\n==========Costumer Account==============");
        while (true) {
            System.out.println("==============Main menu=================");

            commandLine(0);
            Scanner scanner = new Scanner(System.in);
            String choiceComm = scanner.nextLine();
            switch (choiceComm) {
                case "s":
                    shopping();
                    break;
                case "b":
                    shoppingBox();
                case "o":
                    orderedItems();
                    break;
                case "c":
                    return;
                default:
                    System.out.println("              ==error==                 ");

            }
        }
    }

    private static void orderedItems() {
        while (true) {
            if (orders.size() == 0) {
                System.out.println(" empty ");
            } else {
                for (int i = 0; i < orders.size(); i++) {
                    if (orders.get(i).isOrderStatus()) {
                        System.out.println("№ " + i + " secret id:" + orders.get(i).getId() +
                                "|| Product:" + orders.get(i).getProduct().getName() +
                                "|| Amount:" + orders.get(i).getProductAmount() +
                                "|| Total Price:" + orders.get(i).getTotalPrice());

                    }
                }
            }
            commandLine(4);
            System.out.print("_");
            Scanner scanner = new Scanner(System.in);
            String cancelButton = scanner.nextLine();
            if (cancelButton.equals("c")) {
                return;
            } else
                System.out.println("error command!?");
        }
    }

    private static void shopping() {
        while (true) {
            categoryList();
            commandLine(1);
            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine();
            if (choice.equals("c")) {
                return;
            } else if (choice.equals("b")) {
                shoppingBox();
            } else {
                char c = choice.charAt(0);
                if (Character.isLetter(c)) {
                    System.out.println("Wrong attempt!?");
                } else {
                    int choiceCommand = Integer.parseInt(choice);
                    while (true) {
                        productInfos(choiceCommand);
                        commandLine(1);
                        scanner = new Scanner(System.in);
                        System.out.print("id: ");
                        String choicePr = scanner.nextLine();
                        if (choicePr.equals("c")) {
                            break;
                        } else if (choicePr.equals("b")) {
                            shoppingBox();
                        } else {
                            char d = choicePr.charAt(0);
                            if (Character.isLetter(d)) {
                                System.out.println("Wrong attempt!?");
                            } else {
                                int choiceProduct = Integer.parseInt(choicePr);
                                productInfo(choiceProduct);
                                System.out.println("Add To Shopping box?");
                                commandLine(3);
                                System.out.print("_");
                                scanner = new Scanner(System.in);
                                String yesOrNot = scanner.nextLine();
                                if (yesOrNot.equals("a")) {
                                    System.out.print("Amount: ");
                                    int amountProduct = scanner.nextInt();
                                    if (products.get(choiceProduct).getAmount() < amountProduct) {
                                        System.out.println("not enough product!!");
                                    } else {
                                        if (shoppingBoxes.size() < 1) {
                                            shoppingBoxes.add(new ShoppingBox(getRandomId(),
                                                    products.get(choiceProduct),
                                                    amountProduct,
                                                    (amountProduct * products.get(choiceProduct).getPrice())));
                                            products.get(choiceProduct).setAmount(products.get(choiceProduct).getAmount() - amountProduct);
                                            System.out.println("Successfully added");
                                        } else {
                                            boolean notFound = true;
                                            for (int i = 0; i < shoppingBoxes.size(); i++) {
                                                if (shoppingBoxes.get(i) != null) {
                                                    if (shoppingBoxes.get(i).getProduct().getName().equals(products.get(choiceProduct).getName()) &&
                                                            shoppingBoxes.get(i).getProduct().getSort().equals(products.get(choiceProduct).getSort())) {
                                                        notFound = false;
                                                        shoppingBoxes.get(i).setProductAmount(shoppingBoxes.get(i).getProductAmount() + amountProduct);
                                                        products.get(choiceProduct).setAmount(products.get(choiceProduct).getAmount() - amountProduct);
                                                        System.out.println("Successfully added");
                                                    }
                                                }
                                            }
                                            if (notFound) {
                                                shoppingBoxes.add(new ShoppingBox(getRandomId(),
                                                        products.get(choiceProduct),
                                                        amountProduct,
                                                        (amountProduct * products.get(choiceProduct).getPrice())));
                                                products.get(choiceProduct).setAmount(products.get(choiceProduct).getAmount() - amountProduct);
                                                System.out.println("Successfully added");
                                            }
                                        }
                                    }

                                } else if (yesOrNot.equals("c")) {
                                    break;
                                } else {
                                    System.out.println("error command!!");
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private static void productInfo(int choiceProduct) {
        System.out.println("id:" + choiceProduct + " || " +
                "Type:" + products.get(choiceProduct).getSort() + " || " +
                "Name:" + products.get(choiceProduct).getName() + " || " +
                "Status:" + checkStatus(choiceProduct) +
                " || Price:" + products.get(choiceProduct).getPrice() +
                " || Amount: " + products.get(choiceProduct).getAmount());
    }

    private static String checkStatus(int choiceProduct) {
        if (products.get(choiceProduct).isNewOne()) {
            return "new";
        } else return "old";
    }

    private static void shoppingBox() {
        System.out.println("============Shopping Box================");
        if (shoppingBoxes.size() == 0) {
            System.out.println(" empty ");
            while (true) {
                commandLine(4);
                Scanner scanner = new Scanner(System.in);
                String order = scanner.nextLine();
                switch (order) {
                    default:
                        System.out.println("ups something went wrong!!?");
                        break;
                    case "c":
                        return;
                }
            }
        } else {
            for (int i = 0; i < shoppingBoxes.size(); i++) {
                System.out.println("N: " + i +
                        " || Product: " + shoppingBoxes.get(i).getProduct().getName() +
                        " || Amount: " + shoppingBoxes.get(i).getProductAmount() +
                        " || Total price: " + shoppingBoxes.get(i).getTotalPrice());
                totalPayPrice += shoppingBoxes.get(i).getTotalPrice();
            }
            System.out.println("Total Pay Price:                  " + totalPayPrice);
            while (true) {
                commandLine(5);
                Scanner scanner = new Scanner(System.in);
                String order = scanner.nextLine();
                switch (order) {
                    case "r":
                        removeProductFromShoppingBox();
                        totalPayPrice = 0;
                        break;
                    case "o":
                        orderAll();
                        totalPayPrice = 0;
                        break;
                    case "d":
                        orderOneProduct();
                        totalPayPrice = 0;
                        break;
                    default:
                        System.out.println("Ups something went wrong!!?");
                        break;
                    case "c":
                        return;
                }
            }
        }
    }

    private static void orderOneProduct() {
        while (true) {
            commandLine(4);
            System.out.print("numberProduct: ");
            Scanner scanner = new Scanner(System.in);
            String selectOne = scanner.nextLine();
            if (selectOne.equals("c")) {
                return;
            } else {
                char c = selectOne.charAt(0);
                if (Character.isLetter(c)) {
                    System.out.println("ups wrong attempt!?");
                } else {
                    int selectOneProduct = Integer.parseInt(selectOne);
                    if (selectOneProduct > shoppingBoxes.size()) {
                        System.out.println("error id!?");
                        return;
                    } else {
                        orders.add(new Order(shoppingBoxes.get(selectOneProduct).getId(),
                                activeUser[0],
                                shoppingBoxes.get(selectOneProduct).getProduct(), shoppingBoxes.get(selectOneProduct).getProductAmount(),
                                shoppingBoxes.get(selectOneProduct).getTotalPrice(), true));
                        System.out.println("your secret id for product: " + shoppingBoxes.get(selectOneProduct).getId());
                        System.out.println("successfully ordered");
                        shoppingBoxes.remove(selectOneProduct);
                    }
                }
            }

        }
    }

    private static void orderAll() {
        for (int i = 0; i < shoppingBoxes.size(); i++) {
            orders.add(new Order(shoppingBoxes.get(i).getId(), activeUser[0], shoppingBoxes.get(i).getProduct(), shoppingBoxes.get(i).getProductAmount(), shoppingBoxes.get(i).getTotalPrice(), true));
            System.out.println("your secret id for product(" + i + "): " + shoppingBoxes.get(i).getId());

        }

        for (int size = shoppingBoxes.size() - 1; size >= 0; size--) {
            shoppingBoxes.remove(size);
        }
        System.out.println("successfully ordered ");
    }

    private static void removeProductFromShoppingBox() {
        while (true) {
            commandLine(6);
            System.out.print("numberProduct: ");
            Scanner scanner = new Scanner(System.in);
            String selectOne = scanner.nextLine();
            if (selectOne.equals("c")) {
                return;
            } else if (selectOne.equals("l")) {
                shoppingBoxes.removeAll(shoppingBoxes);
                System.out.println("successfully removed!!\n");
                return;
            } else {
                char c = selectOne.charAt(0);
                if (Character.isLetter(c)) {
                    System.out.println("ups wrong attempt!?");
                } else {
                    int selectOneProduct = Integer.parseInt(selectOne);
                    if (selectOneProduct > shoppingBoxes.size()) {
                        System.out.println("error id!?");
                        return;
                    } else {
                        shoppingBoxes.remove(selectOneProduct);
                        System.out.println("successfully removed!!\n");

                        return;
                    }
                }
            }
        }
    }

    private static void productInfos(int choiceCommand) {
        System.out.println("----------------------------------------");
        boolean notFound = true;
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getCategory().getName().equals(namesOfCategory.get(choiceCommand - 1))) {
                notFound = false;
                System.out.println("№" + i + " || Type:" + products.get(i).getSort() +
                        " || Name:" + products.get(i).getName() +
                        " || Price:" + products.get(i).getPrice() +
                        " || Amount:" + products.get(i).getAmount());
            }
        }
        if (notFound) {
            System.out.println(" empty ");
        }

        System.out.println("----------------------------------------");
    }

    private static void categoryList() {
        if (categories.size() == 0) {
            System.out.println(" empty  ");
        } else {
            System.out.println("Category:");
            System.out.println("----------------------------------------");
            for (int i = 0; i < categories.size(); i++) {
                if (categories.get(i + 1) != null) {
                    tempCategory.add(categories.get(i + 1).getName());
                }
            }
            Iterator<String> iterator = tempCategory.iterator();
            int count = 0;
            while (iterator.hasNext()) {
                namesOfCategory.put(count, iterator.next());
                count++;
            }
            for (int i = 0; i < namesOfCategory.size(); i++) {
                if (namesOfCategory.get(i) != null) {
                    System.out.println(i + 1 + "-" + namesOfCategory.get(i));
                }
            }
            System.out.println("----------------------------------------");
        }
    }

    private static void commandLine(int a) {
        switch (a) {
            case 0:
                System.out.println("COMMANDS: s.Shop b.ShoppingBox o.Ordered Items c.Cancel ");
                break;
            case 1:
                System.out.println("COMMANDS:        b.ShoppingBox c.Cancel ");
                break;
            case 2:
                System.out.println("COMMANDS: s.Buy  b.ShoppingBox c.Cancel ");
                break;
            case 3:
                System.out.println("COMMANDS: a.Add                c.Cancel ");
                break;
            case 4:
                System.out.println("COMMANDS:                      c.Cancel ");
                break;
            case 5:
                System.out.println("COMMANDS: r.remove o.Order All d.Order One c.Cancel ");
                break;
            case 6:
                System.out.println("COMMANDS: l.remove All         c.Cancel ");
                break;
            case 7:
                System.out.println("COMMANDS: a.All products e.Edit Product   c.Cancel ");
                break;
            case 8:
                System.out.println("COMMANDS: n.new One            c.Cancel ");
                break;
            case 9:
                System.out.println("COMMANDS: a.Add New   r.Remove c.Cancel ");
                break;
            case 10:
                System.out.println("COMMANDS: l.remove category    c.Cancel ");
                break;
        }
    }

    private static void initBase() {
        users.add(new User("1", "1", "1", Role.Costumer));
        users.add(new User("2", "2", "2", Role.Seller));
        users.add(new User("3", "3", "3", Role.Manager));

        users.add(new User("Ahmad", "ahmad@gmail.ru", "asd123", Role.Director));
        users.add(new User("Bobur", "bobur@gmail.ru", "asd123", Role.Costumer));
        users.add(new User("Sanjar", "sanjar@gmail.ru", "asd123", Role.Manager));
        users.add(new User("Zafar", "zafar@gmail.ru", "asd123", Role.Seller));

        categories.put(1, new Category("Fruits", "Apple"));
        categories.put(2, new Category("Fruits", "Pear"));
        categories.put(3, new Category("Clothes", "Shirt"));
        categories.put(4, new Category("Clothes", "Hat"));
        categories.put(5, new Category("Vegetables", "Plump"));
        categories.put(6, new Category("Vegetables", "Carrot"));
        categories.put(7, new Category("Techniques", "Mobile Phone"));
        categories.put(8, new Category("Techniques", "Ipod"));
        categories.put(9, new Category("Techniques", "Notebook"));
        categories.put(10, new Category("Accessories", "Earphone"));
        categories.put(11, new Category("Accessories", "PowerBank"));

        products.add(new Product("Golden", "Apple", true, categories.get(1), 5600, 300));
        products.add(new Product("Maldavanka", "Apple", true, categories.get(1), 5000, 500));
        products.add(new Product("Semirinka", "Apple", true, categories.get(1), 3800, 350));
        products.add(new Product("Limonli", "Pear", true, categories.get(2), 7000, 350));
        products.add(new Product("Jeans", "Shirt", true, categories.get(3), 54000, 100));
        products.add(new Product("Classic", "Hat", true, categories.get(4), 44999, 100));
        products.add(new Product("Panama", "Hat", true, categories.get(4), 52300, 100));
        products.add(new Product("Long", "Plump", true, categories.get(5), 24750, 250));
        products.add(new Product("Round", "Plump", true, categories.get(5), 24750, 250));
        products.add(new Product("Yellow", "Carrot", true, categories.get(6), 13500, 250));
        products.add(new Product("Red", "Carrot", true, categories.get(6), 15000, 250));
        products.add(new Product("Redmi", "Mobile Phone", true, categories.get(7), 1000000, 250));
        products.add(new Product("Samsung", "Mobile Phone", true, categories.get(7), 1500000, 250));
        products.add(new Product("Iphone", "Ipod", true, categories.get(8), 1200000, 250));
        products.add(new Product("HP 250 G6", "Notebook", true, categories.get(9), 4500000, 250));
        products.add(new Product("Samsung earphone", "Earphone", true, categories.get(10), 15000, 250));
        products.add(new Product("Redmi earphone", "Earphone", true, categories.get(10), 12000, 250));
        products.add(new Product("ELG PowerBank", "PowerBank", true, categories.get(11), 120000, 250));


    }

    public static String getRandomId() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
