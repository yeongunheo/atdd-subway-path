package wooteco.subway.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserPageController {
    @GetMapping
    public String index() {
        return "service/index";
    }

    @GetMapping("/map")
    public String map() {
        return "service/map";
    }

    @GetMapping("/search")
    public String search() {
        return "service/search";
    }
}
