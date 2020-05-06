package com.example.labo3capas.controller;

import com.example.labo3capas.domain.Product;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {

    private List<Product> products = new ArrayList<Product>();

    @RequestMapping("/compraProducto")
    public ModelAndView productos(){
        ModelAndView mod = new ModelAndView();
        products.clear();

        products.add(new Product(0,"GTA1","10"));
        products.add(new Product(1,"GTA2","20"));
        products.add(new Product(2,"GTA3","30"));
        products.add(new Product(3,"GTA4","40"));
        products.add(new Product(4,"GTA5","50"));

        mod.setViewName("productos");
        mod.addObject("product", new Product());
        mod.addObject("products", products);
        return mod;
    }

    @RequestMapping("/validar")
    public ModelAndView validar(@Valid @ModelAttribute Product product){
        ModelAndView mod = new ModelAndView();

        if(Integer.parseInt(product.getCantidad()) > Integer.parseInt(products.get(product.getId()).getCantidad())){
            mod.addObject("nombre","El producto " + products.get(product.getId()).getNombre() + " no se puede adquirir");
            mod.setViewName("error");
        }else {
            mod.addObject("nombre","El producto " + products.get(product.getId()).getNombre() + " se adquirio");
            mod.setViewName("compra");
        }

        return mod;
    }
}
