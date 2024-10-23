package vn.hoidanit.laptopshop.controller.admin;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.service.UploadService;
import vn.hoidanit.laptopshop.service.UserService;

@Controller
public class UserController {
  private final PasswordEncoder passwordEncoder;
  private final UserService userService; // dependency
  private final UploadService uploadService; // dependency

  // Constructor Injection => đây là mô hình dependence injection
  public UserController(UserService userService, UploadService uploadService,
      PasswordEncoder passwordEncoder) {
    this.userService = userService;
    this.uploadService = uploadService;
    this.passwordEncoder = passwordEncoder;
  }

  @RequestMapping("/admin/user")
  public String getUserPage(Model model) {
    List<User> users = this.userService.getAllUsers();
    model.addAttribute("users1", users);
    return "admin/user/show";
  }

  @RequestMapping("/admin/user/{id}")
  public String getUserDetailPage(Model model, @PathVariable long id) {
    User userById = this.userService.getUserById(id);
    model.addAttribute("userById", userById);
    return "admin/user/detail";
  }

  @GetMapping("/admin/user/create") // GET
  public String getCreateUserPage(Model model) {
    model.addAttribute("newUser", new User());
    return "admin/user/create";
  }

  @PostMapping(value = "/admin/user/create")
  public String createUserPage(Model model, @ModelAttribute("newUser") User user,
      @RequestParam("fileAvatar") MultipartFile file) {
    String avatar = this.uploadService.handleSaveUploadFile(file, "avatar");
    String hashPassword = this.passwordEncoder.encode(user.getPassword());
    user.setPassword(hashPassword);
    user.setAvatar(avatar);
    user.setRole(this.userService.getRoleByName(user.getRole().getName()));
    this.userService.handleSaveUser(user);
    return "redirect:/admin/user";
  }

  @RequestMapping("/admin/user/update/{id}")
  public String getUpdateUserPage(Model model, @PathVariable long id) {
    User currentUser = this.userService.getUserById(id);
    model.addAttribute("newUser", currentUser);
    return "admin/user/update";
  }

  @PostMapping("/admin/user/update")
  public String postUpdateUserPage(Model model, @ModelAttribute("newUser") User user) {
    User currentUser = this.userService.getUserById(user.getId());
    if (currentUser != null) {
      currentUser.setPhone(user.getPhone());
      currentUser.setAddress(user.getAddress());
      currentUser.setFullName(user.getFullName());

      this.userService.handleSaveUser(currentUser);
    }
    return "redirect:/admin/user";
  }

  @GetMapping("/admin/user/delete/{id}")
  public String getDeleteUserPage(Model model, @PathVariable long id) {
    model.addAttribute("id", id);
    model.addAttribute("newUser", new User());
    return "admin/user/delete";
  }

  @PostMapping("/admin/user/delete")
  public String postDeleteUser(Model model, @ModelAttribute("newUser") User user) {
    this.userService.deleteByUser(user.getId());
    return "redirect:/admin/user";
  }
}
