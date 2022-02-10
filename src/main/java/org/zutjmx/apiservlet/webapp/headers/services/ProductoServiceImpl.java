package org.zutjmx.apiservlet.webapp.headers.services;

import org.zutjmx.apiservlet.webapp.headers.models.Producto;

import java.util.Arrays;
import java.util.List;

public class ProductoServiceImpl implements ProductoService {

    @Override
    public List<Producto> listar() {
        return Arrays.asList(new Producto(1L,"Laptop Asus VivoBook","Computación",20000),
                             new Producto(2L,"Laptop Dell Vostro 1720","Computación",12000),
                             new Producto(3L,"Laptop Dell Vostro 3500","Computación",15000),
                             new Producto(4L,"Laptop XPS M1210","Computación",15000),
                             new Producto(5L,"Huawei Y9","Telefonía",5000));
    }

}
