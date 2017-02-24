package com.example.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.entity.Player;
import com.example.entity.Team;

@Controller
public class IndexController {
	List<Team> teams;
	
	@PostConstruct
	public void init() {
		this.teams = new ArrayList<Team>();
		for (int i = 0; i < 1; i++) {
			Team team = new Team();
			team.setName("Jason s"+i);
			team.setAge(10+i);
			List<Player> players = new ArrayList<Player>();
			for (int j = 0; j < 5; j++) {
				Player player = new Player();
				player.setName("Jason s"+i);
				player.setAge(30+j);
				players.add(player);
			}
			team.setPlayers(players);
			teams.add(team);
		}
	}

	
	@RequestMapping("/")
	public String index(Map<String, Object> model) {
		return "index";
	}

	@ResponseBody
	@RequestMapping("/ajaxJson")
	public List<String> ajaxJson() {
		List<String> jsons = new ArrayList<String>();
		jsons.add("sss");
		return jsons;
	}
	
	
	@RequestMapping("/teams")
	public @ResponseBody List<Team> teams() {
		return teams;
	}
}
