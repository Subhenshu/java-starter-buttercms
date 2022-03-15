package com.buttercms.springstarterbuttercms.controller;

import com.buttercms.springstarterbuttercms.controller.dto.LandingPageDto;
import com.buttercms.springstarterbuttercms.model.landingpage.Section;
import com.buttercms.springstarterbuttercms.model.landingpage.Seo;
import com.buttercms.springstarterbuttercms.service.PageCollectionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
public class IndexController {
    private final PageCollectionService pageCollectionService;

    public IndexController(PageCollectionService pageCollectionService) {
        this.pageCollectionService = pageCollectionService;
    }

    @GetMapping("/")
    public String index(Model model) {
        LandingPageDto landingPage = pageCollectionService.getLandingPage();
        Seo seo = landingPage.getFields().getSeo();
        List<Section> sections = landingPage.extractSections(landingPage.getFields());
        model.addAttribute("posts", landingPage.getPosts());
        model.addAttribute("seoTitle", seo.getTitle());
        model.addAttribute("seoDescription", seo.getDescription());
        model.addAttribute("sections", sections);
        return "index";
    }

    // TODO - verify whether the preview works; might need to wait for SDK changes
    @GetMapping("/landing-page/{slug}")
    public String indexSlug(Model model) {
        return index(model);
    }
}
