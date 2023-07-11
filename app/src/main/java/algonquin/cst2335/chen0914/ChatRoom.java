package algonquin.cst2335.chen0914;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import algonquin.cst2335.chen0914.databinding.ActivityChatRoomBinding;
import algonquin.cst2335.chen0914.databinding.ReceiveMessageBinding;
import algonquin.cst2335.chen0914.databinding.SentMessageBinding;
import data.ChatRoomViewModel;

public class ChatRoom extends AppCompatActivity {

    ActivityChatRoomBinding binding;

    ArrayList<ChatMessage> messages;
    ChatRoomViewModel chatModel;
    ChatMessageDAO mDAO;

    RecyclerView.Adapter myAdapter;
    RecyclerView recyclerView;
    TextView theText;
    Button sendButton;
    Button receiveButton;
    EditText textInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityChatRoomBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_chat_room);
        setContentView(binding.getRoot());

        MessageDatabase db = Room.databaseBuilder(getApplicationContext(), MessageDatabase.class, "database-name").build();
        mDAO = db.cmDAO();

        Executor thread = Executors.newSingleThreadExecutor();
        thread.execute(() ->
                {
                    messages.addAll(mDAO.getAllMessages());
                    runOnUiThread(()->binding.recycleView.setAdapter(myAdapter));
                }

        );







        messages = new ArrayList<>();
        chatModel = new ViewModelProvider(this).get(ChatRoomViewModel.class);
        messages = chatModel.messages.getValue();

        if(messages == null)
        {
            chatModel.messages.postValue(messages = new ArrayList<>());
        }


        sendButton = findViewById(R.id.sendButton);
        receiveButton = findViewById(R.id.receiveButton);
        textInput = findViewById(R.id.textInput);


        sendButton.setOnClickListener(click -> {
            String input = textInput.getText().toString();
            SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd-MMM-yyyy hh-mm-ss a");
            String currentDateandTime = sdf.format(new Date());
            messages.add(new ChatMessage(input,currentDateandTime,true));
            myAdapter.notifyDataSetChanged();
            myAdapter.notifyItemInserted(messages.size() - 1);
            textInput.setText("");
           // binding.recycleView.setLayoutManager(new LinearLayoutManager(this));
        });

        receiveButton.setOnClickListener(click->{
            String input = textInput.getText().toString();
            SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd-MMM-yyyy hh-mm-ss a");
            String currentDateandTime = sdf.format(new Date());
            messages.add(new ChatMessage(input,currentDateandTime,false));

            myAdapter.notifyItemInserted(messages.size() - 1);
            textInput.setText("");
         //   binding.recycleView.setLayoutManager(new LinearLayoutManager(this));
        });


        class MyRowHolder extends RecyclerView.ViewHolder {
            TextView messageText;
            TextView timeText;

            public MyRowHolder(@NonNull View itemView) {
                super(itemView);

                itemView.setOnClickListener(click->{
                    int position =getAbsoluteAdapterPosition();
                    ChatMessage toDelete = messages.get(position);

                    AlertDialog.Builder builder = new AlertDialog.Builder(ChatRoom.this);
                    builder.setMessage("Do you want to delete the message: " + messageText.getText())
                    .setTitle("Question")
                    .setNegativeButton("No",(dialog, cl) -> {})
                    .setPositiveButton("Yes",(dialog, cl) -> {

                        messages.remove(position);
                        myAdapter.notifyItemRemoved(position);

                        Snackbar.make(messageText,"You deleted massage #" + position, Snackbar.LENGTH_LONG)
                                .setAction("Undo", clk->{
                                    messages.add(position,toDelete );
                                    myAdapter.notifyItemRemoved(position);
                                })
                                .show();
                    })
                    .create().show();

                });
                messageText = itemView.findViewById(R.id.theMessage);
                timeText = itemView.findViewById(R.id.theTime);

            }
        }

        binding.recycleView.setAdapter(myAdapter = new RecyclerView.Adapter<MyRowHolder>() {


            @NonNull
            @Override
            public MyRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                if (viewType == 1) {
                    SentMessageBinding binding = SentMessageBinding.inflate(getLayoutInflater(),parent,false);
                    return new MyRowHolder(binding.getRoot());
                } else {
                    ReceiveMessageBinding binding = ReceiveMessageBinding.inflate(getLayoutInflater(),parent,false);
                    return new MyRowHolder(binding.getRoot());
                }
            }
               // SentMessageBinding binding = SentMessageBinding.inflate(getLayoutInflater());
               // return new MyRowHolder(binding.getRoot());

           // }

            @Override
            public void onBindViewHolder(@NonNull MyRowHolder holder, int position) {
                ChatMessage obj = messages.get(position);
                holder.messageText.setText(obj.message);
                holder.timeText.setText(obj.timeSent);
            }

            @Override
            public int getItemCount() {
                return messages.size();
            }

            @Override
            public int getItemViewType(int position){
                ChatMessage message = messages.get(position);
                if (message.isSentButton == true) {
                    return 1;
                } else {
                    return 0;
                }
            }

        });
        binding.recycleView.setLayoutManager(new LinearLayoutManager(this));

    }
}

