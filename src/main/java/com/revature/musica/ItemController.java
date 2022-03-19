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
                System.out.println("doGet, made array of items from itemrepo.getitems");
               
            String results = mapper.writeValueAsString(items);
            System.out.println("doGet, made results from mapper.writeValueString(items)");
            resp.setContentType("application/json");
            System.out.println("doGet SetcontentType to app/json");
            resp.getWriter().println(results);
            System.out.println("doGet, resp.getWrinter.println(results)");
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Item newItem = mapper.readValue(req.getInputStream(), Item.class);
        System.out.println("doPost, made newItem from mapper.readValue(reqgetInputStream)");
        itemRepository.insertItem(newItem);
        System.out.println("doPost used itemRepo.inserItem using newItem");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doDelete, init");
        Item newItem = mapper.readValue(req.getInputStream(), Item.class);
        //newItem.setid(newItem.getid());
        System.out.println("Item: " + newItem);
        System.out.println("Item ID: " + newItem.getid());
        itemRepository.deleteItem(newItem);
        System.out.println("doDelete, used itemRepo.deleteItem using newItem");
    }

    
}
