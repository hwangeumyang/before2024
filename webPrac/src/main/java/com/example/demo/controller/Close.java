package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//조금 느리지만 이클립스에서 죽이는 거보다 편하다.
@RestController
public class Close {
	@RequestMapping("exit")
	public void exit() {
		System.exit(0);
	}
}
