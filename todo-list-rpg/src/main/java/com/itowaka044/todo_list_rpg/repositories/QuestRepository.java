package com.itowaka044.todo_list_rpg.repositories;

import com.itowaka044.todo_list_rpg.entities.Quest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface QuestRepository extends JpaRepository<Quest, Long> {

    @Modifying
    @Transactional
    @Query(value = "update quest set name = :newName where id = :id", nativeQuery = true)
    void changeQuestName(@Param("id") Long id, @Param("newName") String newName);
}
