package com.example.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Player;
import com.example.entity.Team;

@RestController
@RequestMapping("/xml/")
public class XmlController {
	Team team;
	
	@PostConstruct
	public void init() {
		Team team = new Team();
		team.setName("Jason s");
		team.setAge(10);
		List<Player> players = new ArrayList<Player>();
		for (int j = 0; j < 5; j++) {
			Player player = new Player();
			player.setName("Jason s"+j);
			player.setAge(30+j);
			players.add(player);
		}
		team.setPlayers(players);
	}

	
	@RequestMapping("/")
	public String index(Map<String, Object> model) {
		return "index";
	}

	@RequestMapping("/ajaxJson")
	public List<String> ajaxJson() {
		List<String> jsons = new ArrayList<String>();
		jsons.add("sss");
		return jsons;
	}

	@RequestMapping("/team")
	public Team team() {
		Team team = new Team();
		team.setName("Jason s");
		team.setAge(10);
		List<Player> players = new ArrayList<Player>();
		for (int j = 0; j < 1; j++) {
			Player player = new Player();
			player.setName("Jason s"+j);
			player.setAge(30+j);
			players.add(player);
		}
		team.setPlayers(players);
		return team;
	}
}
