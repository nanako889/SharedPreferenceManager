package com.qbw.sharedpreferencemanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.qbw.spm.P;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = findViewById(R.id.tv);
        P.init(this, true);//这个函数最好在application的oncreate里面调用，这里只是展示
        P.putBoolean("b", true);
        P.putFloat("f", 1.0F);
        P.putInt("i", 10);
        P.putString("s", "ppp");
        P.putLong("l", 100);
        User user = new User();
        user.setId(10000);
        user.setName("good");
        P.putObject("u", user);
        User user1 = null;
        P.putObject("u1", user1);
        User u = P.getObject("u", User.class);
        User u1 = P.getObject("u1",User.class);
        StringBuilder sb = new StringBuilder();
        sb.append(P.getBoolean("b")).append("\n");
        sb.append(P.getFloat("f")).append("\n");
        sb.append(P.getInt("i")).append("\n");
        sb.append(P.getString("s")).append("\n");
        sb.append(P.getLong("l")).append("\n");
        sb.append(u.getId()).append(" ").append(u.getName()).append("\n");
        sb.append(u1==null?"u1==null":"u1 != null");
        tv.setText(sb);

    }
}
