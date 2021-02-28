package com.reactjs.banner4.service;

import com.reactjs.banner4.entity.Banner;
import com.reactjs.banner4.entity.Category;
import com.reactjs.banner4.entity.RequestUser;
import com.reactjs.banner4.repository.BannerRepo;
import com.reactjs.banner4.repository.CategoryRepo;
import com.reactjs.banner4.repository.RequestUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Transactional
public class BannerService {
  private final BannerRepo bannerRepo;

  private final CategoryRepo categoryRepo;

  @Autowired
  RequestUserRepo requestUserRepo;

  public BannerService(BannerRepo bannerRepo, CategoryRepo categoryRepo) {
    this.bannerRepo = bannerRepo;
    this.categoryRepo = categoryRepo;
  }

  public RequestUser saveRequest(RequestUser requestUser) {
    return requestUserRepo.save(requestUser);
  }

  public List<Banner> findByNameB(String name) {
    return bannerRepo.findByName(name);
  }

  public List<Banner> findByCategory(Category category) {
    Category category1 = categoryRepo.findById(category.getId());

    return bannerRepo.findByCategoryAndDeleted(category1, false);
  }

  public Category findCategory(int id) {
    return categoryRepo.findById(id);
  }

  public void deleteCategory(int id) {
    categoryRepo.deleteById(id);
  }

  public List<Banner> getAllBaners() {
    List<Banner> banners = bannerRepo.findByDeletedOrderByPriceDesc(false);
    return banners;
  }

  public Banner saveBanner(Banner banner) {
    List<Banner> bannersList = bannerRepo.findByName(banner.getName());
    boolean retval = bannersList.isEmpty();
    if (retval) {
      return bannerRepo.save(banner);
    } else return bannerRepo.save(banner);
  }

  public List<Category> allCategories() {
    return categoryRepo.findAll();
  }

  public Category saveCategory(Category category) {
    return categoryRepo.save(category);
  }

  public String deleteBanner(int id) {
    // Integer dd = Integer.valueOf(id);
    bannerRepo.setStatusDeleted(id);
    return "redirect:/all-banners";
  }

  public Banner findBannerById(int id) {
    return bannerRepo.findBannerById(id);
  }

  public Category findCategoryById(int id) {
    return categoryRepo.findById(id);
  }

  public List<Banner> searchBannerByName(String item) {

    List<Banner> bannerList = new ArrayList<>();
    List<Banner> banners = bannerRepo.findAll();
    Pattern p = Pattern.compile(item.trim() + "?");
    Pattern small_case = Pattern.compile(item.toLowerCase().trim() + "?");
    for (Banner banner : banners) {
      Matcher m = p.matcher(banner.getName());
      Matcher small = small_case.matcher(banner.getName().toLowerCase());
      if (m.find() || small.find()) {
        bannerList.add(banner);
      }
    }
    return bannerList;
  }

  public List<Category> searchCategoryByName(String item) {

    List<Category> categories = new ArrayList<>();
    List<Category> categories1 = categoryRepo.findAll();
    Pattern p = Pattern.compile(item.trim() + "?");
    Pattern small_case = Pattern.compile(item.toLowerCase().trim() + "?");
    for (Category category : categories1) {
      Matcher m = p.matcher(category.getName());
      Matcher small = small_case.matcher(category.getName().toLowerCase());
      if (m.find() || small.find()) {
        categories.add(category);
      }
    }
    return categories;
  }
}
