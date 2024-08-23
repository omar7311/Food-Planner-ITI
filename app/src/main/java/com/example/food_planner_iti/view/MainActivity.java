package com.example.food_planner_iti.view;

import static com.google.android.gms.common.util.CollectionUtils.setOf;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.food_planner_iti.R;
import com.example.food_planner_iti.local_database.DatabaseManger;
import com.example.food_planner_iti.local_database.Meal;
import com.example.food_planner_iti.local_database.MealPlan;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        NavController navController = navHostFragment.getNavController();
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        FirebaseAuth auth=FirebaseAuth.getInstance();
        if (item.getItemId() == R.id.logout) {
            auth.signOut();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        } else if (item.getItemId() == R.id.sync) {
            FirebaseDatabase.getInstance().getReference("Meals")
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .child("meal_fav").addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                            for(int i=0;i<snapshot.getChildrenCount();i++) {
                               new Thread( ()-> new DatabaseManger(MainActivity.this, MainActivity.this)
                                        .insertFavMeal(snapshot.getValue(Meal.class))).start();
                            }
                        }

                        @Override
                        public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                        }

                        @Override
                        public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                            for(int i=0;i<snapshot.getChildrenCount();i++) {
                               new Thread( ()-> new DatabaseManger(MainActivity.this, MainActivity.this)
                                        .deleteFavMeal(snapshot.getValue(Meal.class))).start();
                            }
                        }

                        @Override
                        public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

            FirebaseDatabase.getInstance().getReference("Meals")
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .child("meal_plan").addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                            for(int i=0;i<snapshot.getChildrenCount();i++) {
                                new Thread( ()-> new DatabaseManger(MainActivity.this, MainActivity.this)
                                        .insertPlanMeal(snapshot.getValue(MealPlan.class))).start();
                            }
                        }

                        @Override
                        public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                        }

                        @Override
                        public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                            for(int i=0;i<snapshot.getChildrenCount();i++) {
                                new Thread( ()-> new DatabaseManger(MainActivity.this, MainActivity.this)
                                        .deletePlanMeal(snapshot.getValue(MealPlan.class))).start();
                            }
                        }

                        @Override
                        public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
        }
        return super.onOptionsItemSelected(item);
    }
}