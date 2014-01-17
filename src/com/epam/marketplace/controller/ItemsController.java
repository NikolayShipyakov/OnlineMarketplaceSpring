package com.epam.marketplace.controller;

import com.epam.marketplace.beans.CategoryTransfer;
import com.epam.marketplace.beans.Goods;
import com.epam.marketplace.beans.GoodsForForm;
import com.epam.marketplace.beans.SearchParameters;
import com.epam.marketplace.dao.CategoryDAO;
import com.epam.marketplace.dao.DAOFactory;
import com.epam.marketplace.dao.GoodsDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class ItemsController {
    @Resource(name = "goodsDAO")
    GoodsDAO dao;
    @Resource (name = "categoryDAO")
    CategoryDAO categoryDAO;

    @RequestMapping(value = "/showItems.spring", method = RequestMethod.GET)
    public String showItems(@RequestBody(required = false)SearchParameters searchParameters, ModelMap model){
        DAOFactory daoFactory = DAOFactory.getDaoFactory();
        List<Goods> goods = dao.selectAllItemsOnInterval(searchParameters != null ? searchParameters : new SearchParameters());
        model.addAttribute("listgoods", goods);
        model.addAttribute("command", "sad");
        model.addAttribute("categorys", getCategorys(">"));
        return "showItems";
    }

    protected List<CategoryTransfer>  getCategorys(String separator) {
        List<CategoryTransfer> categorys = categoryDAO.getAllCategorys();
        for (CategoryTransfer c : categorys) {
            int id = c.getId();
            for (CategoryTransfer category : categorys) {
                int parent = category.getParentId();
                if (parent > 0) {
                    if (id == parent) {
                        category.setFullName(c.getFullName() + separator +
                                category.getName());
                    }
                } else {
                    category.setFullName(category.getName());
                }
            }
        }
        return categorys;
    }
}


