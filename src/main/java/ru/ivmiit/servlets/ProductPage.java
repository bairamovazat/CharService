package ru.ivmiit.servlets;

import ru.ivmiit.models.Category;
import ru.ivmiit.models.Product;
import ru.ivmiit.repositories.BaseRepository;
import ru.ivmiit.service.StorageService;
import ru.ivmiit.service.StorageServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/product")
public class ProductPage extends HttpServlet {
    private StorageService storage = StorageServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        BaseRepository productRepository = storage.getProductRepository();
        PrintWriter writer = resp.getWriter();
        writer.write(
                "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "\t<title></title>\n" +
                "\t<meta charset=\"utf-8\">\n" +
                "</head>\n" +
                "<body>");
        writer.write("<h1>Товары</h1><br>");
        productRepository.getAll().forEach(e -> writer.write(e.toString() + "<br>"));

        writer.write("<h2>Добавить товар:</h2>" +
                "<form method=\"post\" action=\"/product\">\n" +
                "\tНазвание товара<br>\n" +
                "\t<input type=\"name\" name=\"product-name\" placeholder=\"\"><br>\n" +
                "\tКатегория товара<br>\n");
        int i = 0;
        for (Category category : Category.values()) {
            writer.write("\t<input type=\"radio\" name=\"product-category\" value=\"" + category.toString() + "\">" + category.toString().toLowerCase() + "<br>\n");
        }
        writer.write("\t<input type=\"radio\" name=\"product-category\" value=\"BAD CATEGORY\">BAD CATEGORY<br>\n");

        writer.write(
                "\t<input type=\"submit\" value=\"Отправить\">\n" +
                "</form>");
        writer.write("</body>\n" +
                "</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        BaseRepository<Long, Product> productRepository = storage.getProductRepository();
        PrintWriter writer = resp.getWriter();
        String productName = req.getParameter("product-name");
        Category productCategory;
        if(productName.length() < 5){
            writer.write("Error. Minimal product name 5");
            return;
        }
        try {
            productCategory = Category.valueOf(req.getParameter("product-category"));
            Product product = new Product(productName, productCategory);
            try {
                productRepository.save(product);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            writer.write("Success!");
        } catch (IllegalArgumentException | NullPointerException e){
            writer.write("Error. Bad category");
        }
    }
}
