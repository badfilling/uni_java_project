package servlet;

import java.io.Console;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import dao.ProductDao;
import dao.PurchaseDao;
import entities.BasketItem;
import entities.Product;
import entities.Purchase;
import entities.PurchaseElement;

@WebServlet("/")
public class ProductsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductDao productDao;
    private PurchaseDao purchaseDao;

    public void init() {
        productDao = new ProductDao();
        purchaseDao = new PurchaseDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        System.out.println(action);
        try {
            switch (action) {
                case "/buying":
                    // add selected product to session and redirect to selecting count
                    saveSelectedProduct(request);
                    selectProductCount(request, response);
                    break;
                case "/buying-count":
                    saveSelectedCountToSession(request);
                    addToBasket(request);
                    showBasketPage(request, response);
                    break;
                case "/purchase":
                    persistPurchase(request, response);
                    showPurchaseHistory(request, response);
                    break;
                default:
                    doGet(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void showPurchaseHistory(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Purchase> purchases = purchaseDao.getAllPurchase();
        request.setAttribute("listPurchase", purchases);
        RequestDispatcher dispatcher = request.getRequestDispatcher("purchase-list.jsp");
        dispatcher.forward(request, response);
    }

    private void persistPurchase(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        List<BasketItem> currentBaksetItems = (List<BasketItem>) session.getAttribute("basketItems");
        if (currentBaksetItems == null || currentBaksetItems.isEmpty()) { return; }

        Double totalPrice = currentBaksetItems.stream().map( e -> e.getTotalPrice()).reduce((l, r) -> l + r).get();
        List<PurchaseElement> elements = currentBaksetItems.stream()
        .map(e -> new PurchaseElement(e.getProduct().getId(), e.getCount()))
        .toList();
        Purchase purchase = new Purchase(totalPrice, elements);

        purchaseDao.savePurchase(purchase);
    }

    private void showBasketPage(HttpServletRequest request, HttpServletResponse response)
     throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("products-basket.jsp");
        dispatcher.forward(request, response);
    }

    private void saveSelectedCountToSession(HttpServletRequest request) {
        Integer providedCount = Integer.parseInt(request.getParameter("count"));
        request.getSession().setAttribute("productsCount", providedCount);
    }

    private void addToBasket(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer productsCount = Integer.parseInt(request.getParameter("count"));
        Integer preselectedProductId = (Integer) session.getAttribute("selectedProductId");
        if (preselectedProductId != null) {
            Product existingProduct = productDao.getProduct(preselectedProductId);
            BasketItem basketItem = new BasketItem(existingProduct, productsCount);

            List<BasketItem> currentBaksetItems = (List<BasketItem>) session.getAttribute("basketItems");
            if (currentBaksetItems == null) {
                currentBaksetItems = new ArrayList<>();
            }
            currentBaksetItems.add(basketItem);
            session.setAttribute("basketItems", currentBaksetItems);
        }
    }

    private void saveSelectedProduct(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer productId = Integer.parseInt(request.getParameter("product"));
        session.setAttribute("selectedProductId", productId);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        System.out.println(action);
        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertProduct(request, response);
                    break;
                case "/delete":
                    deleteProduct(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateProduct(request, response);
                    break;
                case "/buying":
                    buyingProducts(request, response);
                    break;
                case "/basket":
                    showBasketPage(request, response);
                    break;
                case "/purchase-history":
                    showPurchaseHistory(request, response);
                    break;
                default:
                    listProducts(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void buyingProducts(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Product> listProducts = productDao.getAllProduct();
        request.setAttribute("listProduct", listProducts);

        HttpSession session = request.getSession();
        Integer preselectedProductId = (Integer) session.getAttribute("selectedProductId");
        if (preselectedProductId != null) {
            Product existingProduct = productDao.getProduct(preselectedProductId);
            request.setAttribute("selectedProduct", existingProduct);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("product-select.jsp");
        dispatcher.forward(request, response);
    }

    private void selectProductCount(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession();
        Integer preselectedProductId = (Integer) session.getAttribute("selectedProductId");
        if (preselectedProductId != null) {
            Product existingProduct = productDao.getProduct(preselectedProductId);
            request.setAttribute("selectedProduct", existingProduct);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("product-count.jsp");
        dispatcher.forward(request, response);
        // response.sendRedirect("product-count.jsp");
    }

    private void listProducts(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Product> listProducts = productDao.getAllProduct();
        System.out.println(listProducts);
        System.out.println("DIDPRINTPRODUCTS");
        request.setAttribute("listProduct", listProducts);
        RequestDispatcher dispatcher = request.getRequestDispatcher("product-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("product-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product existingProduct = productDao.getProduct(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("product-form.jsp");
        request.setAttribute("product", existingProduct);
        dispatcher.forward(request, response);

    }

    private void insertProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String title = request.getParameter("title");
        Double price = Double.parseDouble(request.getParameter("price"));
        Product newProduct = new Product(title, price);
        productDao.saveProduct(newProduct);
        response.sendRedirect("list");
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        Double price = Double.parseDouble(request.getParameter("price"));

        Product product = new Product(id, title, price);
        productDao.updateProduct(product);
        response.sendRedirect("list");
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        productDao.deleteProduct(id);
        response.sendRedirect("list");
    }
}