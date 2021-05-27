package kz.assadbek.sandbox.controllers;

import kz.assadbek.sandbox.dao.ProductDAO;
import kz.assadbek.sandbox.models.Catalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final ProductDAO productDAO;

    @Autowired
    public AdminController(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("catalogs", productDAO.catalogs());
        return "admin/catalog_admin";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("catalogs", productDAO.catalogs());
        model.addAttribute("subcatalogs", productDAO.showSubCatalog(id));
        return "admin/subcatalogs_admin";
    }

    @GetMapping("/new")
    public String newCatalog(@ModelAttribute("catalog")Catalog catalog) {
        return "admin/newcatalog";
    }

    @PostMapping
    public String createCatalog(@ModelAttribute("catalog")Catalog catalog) {
        productDAO.save(catalog);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("catalog", productDAO.findCatalog(id));
        return "admin/edit_catalog";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("catalog") Catalog catalog,
                         @PathVariable("id") int id) { ;

        productDAO.updateCatalog(id, catalog);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        productDAO.deleteCatalog(id);
        return "redirect:/admin";
    }
}
