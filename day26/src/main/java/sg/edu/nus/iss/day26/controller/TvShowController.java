package sg.edu.nus.iss.day26.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.nus.iss.day26.model.TvShow;
import sg.edu.nus.iss.day26.service.TvShowService;

@Controller
@RequestMapping({"","/","index.html"})
public class TvShowController {

    @Autowired
    TvShowService tvShowSvc;

    @GetMapping("")
    public String getAllTypes(Model model){
        List<String> results = tvShowSvc.listAllTypes();
        model.addAttribute("types", results);

        return "index";
    }

    @GetMapping("/tvshow/type/{type}")
    public String getTvShowByType(@PathVariable String type, Model model){
        List<TvShow> results = tvShowSvc.findAllTvShowsByType(type);
        model.addAttribute("tvshowsbytype", results);
        model.addAttribute("type", type);
       

        return "tvshowsbytype";
    }

    @GetMapping("/tvshow/type/id/{id}")
    public String getTvShowDetail(@PathVariable Integer id, Model model){
        List<TvShow> results2 = tvShowSvc.findTvShowDetail(id);
        System.out.println(results2);
        model.addAttribute("details", results2);
        model.addAttribute("id", id);

        return "tvshowdetail";
    }

    // GET /tvshow?lang=English
    @GetMapping("/tvshow")
    public String getTvShowByLang(@RequestParam(defaultValue = "English") String lang, Model model){

        List<TvShow> results = tvShowSvc.findAllTvShowsByLanguage(lang);
        model.addAttribute("language", lang);
        model.addAttribute("tvshows", results);
        //Goes to tvshows.html
        return "tvshows";
    }


}
