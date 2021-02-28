package com.reactjs.banner4.controller;

import com.reactjs.banner4.entity.Banner;
import com.reactjs.banner4.entity.Category;
import com.reactjs.banner4.service.BannerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api")
public class CategoryController {
  private final BannerService bannerService;

  public CategoryController(BannerService bannerService) {
    this.bannerService = bannerService;
  }

  @GetMapping("cat-info/{id}")
  public String bannersByCategory(@PathVariable("id") Integer id, Model model) {
    Category category = bannerService.findCategoryById(id);
    List<Banner> banners = bannerService.findByCategory(category);
    model.addAttribute("banners", banners);
    return "banners-category";
  }
}
