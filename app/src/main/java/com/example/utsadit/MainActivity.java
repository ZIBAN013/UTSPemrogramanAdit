package com.example.utsadit;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.utsadit.data.response.GithubSearchResponse;
import com.example.utsadit.data.response.GithubUser;
import com.example.utsadit.data.retrofit.ApiConfig;
import com.example.utsadit.data.retrofit.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view); // Inisialisasi RecyclerView di sini

        ApiService apiService = ApiConfig.getApiService();
        Call<GithubSearchResponse> call = apiService.searchUsers("Adit");

        call.enqueue(new Callback<GithubSearchResponse>() {
            @Override
            public void onResponse(Call<GithubSearchResponse> call, Response<GithubSearchResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<GithubUser> users = response.body().getUsers();
                    if (users.isEmpty()) {
                        Toast.makeText(MainActivity.this, "Pengguna Tidak Ada", Toast.LENGTH_SHORT).show();
                    } else {
                        adapter = new UserAdapter(users);
                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Gagal Mendapatkan Pengguna", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GithubSearchResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Untuk menyimpan data yang penting saat perubahan konfigurasi
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        // Misalnya, menyimpan posisi item di RecyclerView
        if (recyclerView != null && recyclerView.getLayoutManager() != null) {
            int position = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
            outState.putInt("position", position);
        }
    }

    // Untuk memulihkan data setelah perubahan konfigurasi
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Misalnya, memulihkan posisi item di RecyclerView
        int position = savedInstanceState.getInt("position", 0);
        if (recyclerView != null && recyclerView.getLayoutManager() != null) {
            recyclerView.getLayoutManager().scrollToPosition(position);
        }
    }
}