package com.rest.webservices.restfulwebservices.versoning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {
	
	@GetMapping("v1/person")
	public PersonV1 personV1() {
		return new PersonV1("Manish Bhumarkar");
	}
	
	@GetMapping("v2/person")
	public PersonV2 personV2() {
		return new PersonV2(new Name("Manish", "Bhumarkar"));
	}
	
	@GetMapping(path="person/param",params = "version1")
	public PersonV1 paramV1() {
		return new PersonV1("Manish Bhumarkar");
	}
	
	@GetMapping(path="person/param",params = "version2")
	public PersonV2 paramV2() {
		return new PersonV2(new Name("Manish", "Bhumarkar"));
	}
	
	@GetMapping(path="person/header",headers = "X-API-VERSIN=1")
	public PersonV1 headerV1() {
		return new PersonV1("Manish Bhumarkar");
	}
	
	@GetMapping(path="person/header",headers = "X-API-VERSIN=2")
	public PersonV2 headerV2() {
		return new PersonV2(new Name("Manish", "Bhumarkar"));
	}
}
