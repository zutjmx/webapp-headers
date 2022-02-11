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
import java.util.Optional;

@WebServlet("/buscar-producto")
public class BuscarProductoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductoService productoService = new ProductoServiceImpl();
        String nombre = req.getParameter("producto").toUpperCase();

        Optional<Producto> encontrado = productoService.listar().stream().filter(
                                                p -> {
                                                    if (nombre == null || nombre.isBlank()) {
                                                        return false;
                                                    }
                                                    return p.getNombre().toUpperCase().contains(nombre);
                                                }
                                        ).findFirst();

        if (encontrado.isPresent()) {
            resp.setContentType("text/html;charset=UTF-8");
            try(PrintWriter out = resp.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("    <head>");
                out.println("      <meta charset=\"UTF-8\">");
                out.println("      <title>Producto Encontrado</title>");
                out.println("    </head>");
                out.println("  <body>");
                out.println("      <h1>Detalle del producto encontrado:</h1>");
                out.println("      <p>Nombre: " + encontrado.get().getNombre() +"</p>");
                out.println("      <p>Tipo: " + encontrado.get().getTipo() +"</p>");
                out.println("      <p>Precio: " + encontrado.get().getPrecio() +"</p>");
                out.println("      <p><a href=\"" + req.getContextPath() + "/buscar.html" + "\">Regresar a la búsqueda</a></p>");
                out.println("  </body>");
                out.println("</html>");
            }

        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND,"No se encontró el producto: ".concat(nombre));
        }
    }
}
