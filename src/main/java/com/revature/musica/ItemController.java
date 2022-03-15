package com.revature.musica;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ItemController extends HttpServlet{
    private ItemRepository itemRepository;
    private ObjectMapper mapper;
    
    public ItemController(ItemRepository itemRepository, ObjectMapper mapper) {
        this.itemRepository = itemRepository;
        this.mapper = mapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
                List<Item> items = itemRepository.getItems();
               
            String results = mapper.writeValueAsString(items);
            resp.setContentType("application/json");
            resp.getWriter().println(results);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Item newItem = mapper.readValue(req.getInputStream(), Item.class);
        itemRepository.insertItem(newItem);
    }
}
