package vn.hoidanit.laptopshop.controller.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.service.ProductService;
import vn.hoidanit.laptopshop.service.UploadService;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {

  private final ProductService productService;
  private final UploadService uploadService;

  public ProductController(ProductService productService, UploadService uploadService) {
    this.productService = productService;
    this.uploadService = uploadService;
  }

  @GetMapping("/admin/product")
  public String getProductPage(Model model) {
    List<Product> products = this.productService.getAllProduct();
    model.addAttribute("products", products);
    return "admin/product/show";
  }

  @GetMapping("/admin/product/create") // GET
  public String getCreateUserPage(Model model) {
    model.addAttribute("newProduct", new Product());
    return "admin/product/create";
  }

  @PostMapping(value = "/admin/product/create")
  public String createProductPage(Model model,
      @ModelAttribute("newProduct") @Valid Product product,
      BindingResult newProductBindingResult,
      @RequestParam("fileProduct") MultipartFile file) {
    List<FieldError> errors = newProductBindingResult.getFieldErrors();
    for (FieldError error : errors) {
      System.out.println(">>>>" + error.getField() + " - " + error.getDefaultMessage());
    }

    if (newProductBindingResult.hasErrors()) {
      return "admin/product/create";
    }

    String image = this.uploadService.handleSaveUploadFile(file, "product");
    product.setImage(image);

    this.productService.handleSaveProduct(product);
    return "redirect:/admin/product";
  }

  @GetMapping("/admin/product/update/{id}")
  public String getUpdateProductPage(Model model, @PathVariable long id) {
    Product currentProduct = this.productService.getProductById(id);
    model.addAttribute("newProduct", currentProduct);
    return "admin/product/update";
  }

  @PostMapping("/admin/product/update")
  public String postUpdateProductPage(Model model, @ModelAttribute("newProduct") Product product) {
    Product currentProduct = this.productService.getProductById(product.getId());
    System.out.println("currentProduct" + currentProduct);
    if (currentProduct != null) {
      currentProduct.setName(product.getName());
      currentProduct.setPrice(product.getPrice());
      currentProduct.setDetailDesc(product.getDetailDesc());
      currentProduct.setShortDesc(product.getShortDesc());
      currentProduct.setQuantity(product.getQuantity());
      currentProduct.setFactory(product.getFactory());
      currentProduct.setTarget(product.getTarget());

      this.productService.handleSaveProduct(currentProduct);
    }
    return "redirect:/admin/product";
  }

  @GetMapping("/admin/product/delete/{id}")
  public String getDeleteProductPage(Model model, @PathVariable long id) {
    model.addAttribute("id", id);
    model.addAttribute("newProduct", new Product());
    return "/admin/product/delete";
  }

  @PostMapping("/admin/product/delete")
  public String postDeleteProduct(Model model, @ModelAttribute("newProduct") Product product) {
    this.productService.deleteProductById(product.getId());
    return "redirect:/admin/product";
  }

}
