package kz.assadbek.sandbox.controllers;

import kz.assadbek.sandbox.dao.ProductDAO;
import kz.assadbek.sandbox.models.Catalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/catalog")
public class CatalogController {
    private final ProductDAO productDAO;

    @Autowired
    public CatalogController(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("catalogs", productDAO.catalogs());
        return "catalogs/catalog";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("catalogs", productDAO.catalogs());
        model.addAttribute("subcatalogs", productDAO.showSubCatalog(id));
        return "catalogs/subcatalogs";
    }
}
