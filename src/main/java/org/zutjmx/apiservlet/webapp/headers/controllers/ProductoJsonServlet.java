package org.zutjmx.apiservlet.webapp.headers.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.zutjmx.apiservlet.webapp.headers.models.Producto;
import org.zutjmx.apiservlet.webapp.headers.services.ProductoService;
import org.zutjmx.apiservlet.webapp.headers.services.ProductoServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/productos.json")
public class ProductoJsonServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductoService productoService = new ProductoServiceImpl();
        List<Producto> productos = productoService.listar();
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(productos);
        resp.setContentType("application/json");
        resp.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletInputStream jsonStream = req.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        Producto producto = mapper.readValue(jsonStream, Producto.class);

        resp.setContentType("text/html;charset=UTF-8");
        try(PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("    <head>");
            out.println("      <meta charset=\"UTF-8\">");
            out.println("      <title>Detalles del producto</title>");
            out.println("    </head>");
            out.println("  <body>");
            out.println("      <h1>Detalles del producto desde Json</h1>");
            out.println("      <ul>");
            out.println("       <li>Id: ".concat(producto.getId().toString()).concat("</li>"));
            out.println("       <li>Nombre: ".concat(producto.getNombre()).concat("</li>"));
            out.println("       <li>Tipo: ".concat(producto.getTipo()).concat("</li>"));
            out.println("       <li>Precio: "+producto.getPrecio()+"</li>");
            out.println("      </ul>");
            out.println("  </body>");
            out.println("</html>");
        }

    }

}
