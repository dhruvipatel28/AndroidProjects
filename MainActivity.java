package demo.firebase.dhruvi.firebasedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity
{

    FirebaseDatabase fb;
    DatabaseReference ref;
    Button btn;
    EditText txtmsg;
    TextView showMsg;

    private ChildEventListener childEventListener;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn);
        txtmsg = findViewById(R.id.editText);
        showMsg = findViewById(R.id.showText);

        //set up firebase

        fb = FirebaseDatabase.getInstance();
        ref = fb.getReference();

        attachedMessageEL();



        // create insert data

        //Users uObj = new Users("Dhruvi", "DhruviPatel");
        //ref.child("tblUser").push().setValue(uObj);


        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Date d = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
                String date = sdf.format(d);

                String text = txtmsg.getText().toString().trim();
                if(!text.isEmpty())
                {
                    Message m = new Message("Dhruvi", text, date);
                    ref.child("messages").push().setValue(m);
                }
            }
        });




    } // oncreate

    public void attachedMessageEL()
    {
        if(childEventListener == null)
        {
            childEventListener = new ChildEventListener()
            {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s)
                {
                    Message m = dataSnapshot.getValue(Message.class);
                    String txt = m.date + ": " + m.name + " says: " + m.message + "\n";

                    // 2. show this item in the textview
                    showMsg.append(txt);


                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s)
                {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot)
                {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s)
                {

                }

                @Override
                public void onCancelled(DatabaseError databaseError)
                {

                }
            };
            ref.child("messages").addChildEventListener(childEventListener);
        }
    }
}
