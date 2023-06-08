package com.devb.book_store.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("")
public class RedirectClass {
	@GetMapping
	public RedirectView redirect() {
		return new RedirectView("swagger-ui/index.html");
	}

}
