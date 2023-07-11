package algonquin.cst2335.chen0914;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ChatMessageDAO {
    @Insert
    public long insertMessage(ChatMessage m);


    @Query("Select * from ChatMessage")
    List<ChatMessage> getAllMessages();


    @Delete
    void deleteMessae(ChatMessage m);


}
