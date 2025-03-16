package com.itowaka044.todo_list_rpg.repositories;

import com.itowaka044.todo_list_rpg.entities.Quest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestRepository extends JpaRepository<Quest, Long> {
}
