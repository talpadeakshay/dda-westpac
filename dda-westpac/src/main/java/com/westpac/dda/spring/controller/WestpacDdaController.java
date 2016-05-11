package com.westpac.dda.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WestpacDdaController {

	@RequestMapping(value = { "/" })
	public String homePage() {
		return "index";
	}
}
