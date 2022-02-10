package org.zutjmx.apiservlet.webapp.headers.controllers;

import jakarta.servlet.ServletException;
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

@WebServlet({"/productos.xls","/productos.html"})
public class ProductoXlsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductoService productoService = new ProductoServiceImpl();
        List<Producto> productos = productoService.listar();
        String servletPath = req.getServletPath();
        boolean esXls = servletPath.endsWith(".xls");

        resp.setContentType("text/html;charset=UTF-8");

        if (esXls) {
            resp.setContentType("application/vnd.ms-excel");
            resp.setHeader("Content-Disposition","attachment; filename=productos.xls");
        }

        try(PrintWriter out = resp.getWriter()) {
            if (!esXls) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("    <head>");
                out.println("      <meta charset=\"UTF-8\">");
                out.println("      <title>Listado de Productos</title>");
                out.println("    </head>");
                out.println("  <body>");
                out.println("      <h1>Productos:</h1>");
                out.println("      <p><a href=\"" + req.getContextPath() + "/productos.xls" + "\">Exportar a Excel</a></p>");
                out.println("      <p><a href=\"" + req.getContextPath() + "/productos.json" + "\">Mostrar Json</a></p>");
            }
            out.println("      <table>");
            out.println("      <tr>");
            out.println("       <th>Id</th>");
            out.println("       <th>Nombre</th>");
            out.println("       <th>Tipo</th>");
            out.println("       <th>Precio</th>");
            out.println("      </tr>");
            productos.forEach(p -> {
                out.println("      <tr>");
                out.println("       <td>".concat(p.getId().toString()).concat("</td>"));
                out.println("       <td>".concat(p.getNombre()).concat("</td>"));
                out.println("       <td>".concat(p.getTipo()).concat("</td>"));
                out.println("       <td>" + p.getPrecio() + "</td>");
                out.println("      </tr>");
            });
            out.println("      </table>");
            if (!esXls) {
                out.println("      <p><a href=\"" + req.getContextPath() + "\">Regresar</a></p>");
                out.println("  </body>");
                out.println("</html>");
            }
        }

    }
}
