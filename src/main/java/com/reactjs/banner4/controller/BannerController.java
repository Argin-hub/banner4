package com.reactjs.banner4.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.reactjs.banner4.entity.Banner;
import com.reactjs.banner4.entity.Views;
import com.reactjs.banner4.repository.BannerRepo;
import com.reactjs.banner4.service.BannerService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("banner")
@CrossOrigin(origins = "http://localhost:3000")
public class BannerController {
  private final BannerRepo bannerRepo;

  private final BannerService bannerService;

  public BannerController(BannerRepo bannerRepo, BannerService bannerService) {
    this.bannerRepo = bannerRepo;
    this.bannerService = bannerService;
  }

  @GetMapping
  @JsonView(Views.IdName.class)
  public List<Banner> list() {
    return bannerService.getAllBaners();
  }

  @GetMapping("{id}")
  @JsonView(Views.FullMessage.class)
  public Banner getOne(@PathVariable("id") Banner banner) {
    return banner;
  }

  @PostMapping
  public Banner create(@RequestBody Banner banner) {
    return bannerService.saveBanner(banner);
  }

  @PutMapping("{id}")
  public Banner update(@PathVariable("id") Banner bannerFromDb, @RequestBody Banner banner) {
    BeanUtils.copyProperties(banner, bannerFromDb, "id");
    return bannerService.saveBanner(bannerFromDb);
  }

  @DeleteMapping("{id}")
  public void delete(@PathVariable("id") int id) {
    bannerService.deleteBanner(id);
  }
}
