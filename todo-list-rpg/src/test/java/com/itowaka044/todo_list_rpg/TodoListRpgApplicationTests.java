package com.itowaka044.todo_list_rpg;

import com.itowaka044.todo_list_rpg.entities.DailyQuest;
import com.itowaka044.todo_list_rpg.entities.Player;
import com.itowaka044.todo_list_rpg.entities.Quest;
import com.itowaka044.todo_list_rpg.entities.enums.QuestTypeAttributes;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class TodoListRpgApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void dailyQuestRoutine(){

		Player player = new Player("joao");

		DailyQuest quest = new DailyQuest("correr", "ficar rapido", 100, 3, QuestTypeAttributes.STRENGTH);

		if(quest.canRefresh(quest.lastComplete)){
			System.out.println("pode refazer");
		} else {
			System.out.println("nao pode refazer");
		}

		quest.questReward(player);

		if(quest.canRefresh(quest.lastComplete)){
			System.out.println("pode refazer");
		} else {
			System.out.println("nao pode refazer");
		}

		quest.lastComplete = LocalDateTime.of(2025,3,15,1,1,1);

		System.out.println("--------------");

		if(quest.canRefresh(quest.lastComplete)){
			System.out.println("pode refazer");
		} else {
			System.out.println("nao pode refazer");
		}




	}

	@Test
	void questRewardTest(){

		Player player = new Player("joao");

		Quest quest = new Quest("correr", "ficar rapido",130 , 3, QuestTypeAttributes.STRENGTH);

		System.out.println(player);

		quest.questReward(player);

		System.out.println(player);

	}



}
