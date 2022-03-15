package com.revature.musica;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CartController extends HttpServlet{
    private CartRepository cartRepository;
    private ObjectMapper mapper;
    
    public CartController(CartRepository cartRepository, ObjectMapper mapper) {
        this.cartRepository = cartRepository;
        this.mapper = mapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
                List<Item> items = cartRepository.getItems();
               
            String results = mapper.writeValueAsString(items);
            resp.setContentType("application/json");
            resp.getWriter().println(results);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Item newItem = mapper.readValue(req.getInputStream(), Item.class);
        cartRepository.insertItem(newItem);
    }
}
