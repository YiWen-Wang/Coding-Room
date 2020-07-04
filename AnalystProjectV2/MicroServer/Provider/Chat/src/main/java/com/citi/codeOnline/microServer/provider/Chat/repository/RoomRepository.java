package com.citi.codeOnline.microServer.provider.Chat.repository;

import com.citi.codeOnline.microServer.provider.Chat.entity.InterviewRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoomRepository extends JpaRepository<InterviewRoom, Integer> {
    @Query(value = "select * from interview_room where owner_name = ?1 and open = true", nativeQuery = true)
    public InterviewRoom getCurrentOpenRoom(String ownerName);

    public InterviewRoom getInterviewRoomByRoomId(int id);

}
