package org.zutjmx.apiservlet.webapp.headers.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet("/cabeceras-request")
public class CabecerasHttpRequestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        String httpMethod = req.getMethod();
        String requestUri = req.getRequestURI();
        String requestUrl = req.getRequestURL().toString();
        String contextPath = req.getContextPath();
        String servletPath = req.getServletPath();
        String ip = req.getLocalAddr();
        String ipCliente = req.getRemoteAddr();
        String scheme = req.getScheme();
        String host = req.getHeader("host");

        int port = req.getLocalPort();

        String url = scheme.concat("://").concat(host).concat(contextPath).concat(servletPath);
        String url2 = scheme.concat("://").concat(ip).concat(":").concat(String.valueOf(port)).concat(contextPath).concat(servletPath);


        try(PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("    <head>");
            out.println("      <meta charset=\"UTF-8\">");
            out.println("      <title>Cabeceras HHTP Request</title>");
            out.println("    </head>");
            out.println("  <body>");
            out.println("      <h1>Cabeceras HHTP Request:</h1>");
            out.println("      <ul>");
            out.println("           <li>Método HHTP: " + httpMethod + "</li>");
            out.println("           <li>Request URI: " + requestUri + "</li>");
            out.println("           <li>Request URL: " + requestUrl + "</li>");
            out.println("           <li>Context Path: " + contextPath + "</li>");
            out.println("           <li>Servlet Path: " + servletPath + "</li>");
            out.println("           <li>IP Local: " + ip + "</li>");
            out.println("           <li>IP Cliente: " + ipCliente + "</li>");
            out.println("           <li>Puerto Local: " + port + "</li>");
            out.println("           <li>Esquema: " + scheme + "</li>");
            out.println("           <li>Host: " + host + "</li>");
            out.println("           <li>Url: " + url + "</li>");
            out.println("           <li>Url2: " + url2 + "</li>");

            Enumeration<String> headerNames = req.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String cabecera = headerNames.nextElement();
                out.println("           <li>" + cabecera.concat(": ").concat(req.getHeader(cabecera)) + "</li>");
            }

            out.println("      </ul>");
            out.println("      <p><a href=\"" + req.getContextPath() + "\">Regresar</a></p>");
            out.println("  </body>");
            out.println("</html>");
        }

    }
}
