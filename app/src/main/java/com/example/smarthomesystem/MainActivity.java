package com.example.smarthomesystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    // تعريف قاعدة بيانات Firebase
    private DatabaseReference databaseReference;
   // A7A
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // تهيئة Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference();

        // كتابة بيانات في Firebase
        databaseReference.child("test_key").setValue("Hello Firebase!")
                .addOnSuccessListener(aVoid -> Log.d("Firebase", "Data written successfully"))
                .addOnFailureListener(e -> Log.e("Firebase", "Failed to write data", e));

        // قراءة بيانات من Firebase
        databaseReference.child("test_key").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                String value = task.getResult().getValue(String.class);
                Log.d("Firebase", "Read value: " + value);
            } else {
                Log.e("Firebase", "Error getting data", task.getException());
            }
        });
    }
}
