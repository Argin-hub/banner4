package com.reactjs.banner4.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.reactjs.banner4.entity.Banner;
import com.reactjs.banner4.entity.Category;
import com.reactjs.banner4.entity.RequestUser;
import com.reactjs.banner4.entity.Views;
import com.reactjs.banner4.service.BannerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class SimpleController {

  private final BannerService bannerService;

  public SimpleController(BannerService bannerService) {
    this.bannerService = bannerService;
  }




  @GetMapping("/category-delete/{id}")
  public String getOne(@PathVariable("id") int id) {
    Category category = bannerService.findCategory(id);
    System.out.println(category.getName());
    bannerService.deleteCategory(id);
    return "redirect:/banners";
  }

  @GetMapping("/create-cat")
  // @PreAuthorize("hasAuthority('developers:read')")
  public String catForm(Category category) {
    return "category";
  }

  @GetMapping("/categories")
  // @PreAuthorize("hasAuthority('developers:read')")
  public String allCategory(Model model) {
    List<Category> categories = bannerService.allCategories();
    model.addAttribute("categories", categories);
    return "categories";
  }

  @PostMapping("/create-cat")
  public String catCreate(Category category) {
    bannerService.saveCategory(category);
    return "redirect:/banners";
  }

  @GetMapping("/create-banner")
  // @PreAuthorize("hasAuthority('developers:read')")
  @JsonView(Views.IdName.class)
  public String bannerForm(Banner banner, Model model) {
    List<Category> categories = bannerService.allCategories();
    model.addAttribute("categories", categories);
    return "banner";
  }

  @PostMapping("/create-banner")
  public String bannerForms(Banner banner, Model model) {
    List<Banner> banners = bannerService.searchBannerByName(banner.getName());
    boolean exist = banners.isEmpty();
    if (!exist) {
      model.addAttribute("errorName", "error this banner exist");
      return "banner";
    }
    bannerService.saveBanner(banner);
    return "redirect:/banners";
  }

  @GetMapping("/index")
  public String startPage() {
    return "search";
  }

  @GetMapping("/check")
  public String checkPage() {
    return "check";
  }

  @PostMapping("/found")
  public String foundPage(String banner, Model model) {
    System.out.println(banner);
    List<Banner> banners = bannerService.searchBannerByName(banner);
    model.addAttribute("banners", banners);
    return "first";
  }

  @PostMapping("/found_cat")
  public String foundCat(String cat, Model model) {
    System.out.println(cat);
    List<Category> categories = bannerService.searchCategoryByName(cat);
    model.addAttribute("categories", categories);
    return "first";
  }

  @GetMapping("banner-info/{id}")
  public String infoBanner(@PathVariable("id") Integer id, Model model) {
    Banner banner1 = bannerService.findBannerById(id);
    RequestUser requestUser = new RequestUser();
    requestUser.setBannerId(id);
    bannerService.saveRequest(requestUser);
    model.addAttribute("banner", banner1);
    return "info_banner";
  }

  @GetMapping("/all-banners")
  public String allBanners(Model model) {
    List<Banner> banners = bannerService.getAllBaners();
    model.addAttribute("banners", banners);
    return "all_banners";
  }

  @GetMapping("/ban-upd/{id}")
  public String updBanner(@PathVariable("id") String id, Model model) {
    int ids = Integer.valueOf(id);
    Banner banner = bannerService.findBannerById(ids);
    model.addAttribute("banner", banner);
    return "ban-update";
  }

  @PostMapping("/update-ban")
  public String banUpdate(Banner banner) {
    bannerService.saveBanner(banner);
    return "redirect:/all-banners";
  }

  @GetMapping("banner-delete/{id}")
  public String deleteBanner(@PathVariable("id") Integer id) {
    bannerService.deleteBanner(id);
    return "redirect:/all-banners";
  }

  @GetMapping("/category/{id}")
  public String banCat(@PathVariable("id") String id, Model model) {
    int in = Integer.valueOf(id);
    Category category1 = bannerService.findCategory(in);
    List<Banner> banners = bannerService.findByCategory(category1);
    model.addAttribute("banners", banners);
    return "ban-cat";
  }
}
