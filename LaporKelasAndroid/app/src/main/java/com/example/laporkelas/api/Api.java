package com.example.laporkelas.api;

import com.example.laporkelas.model.UserRegistrationModel;
import com.example.laporkelas.model.*;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {
    //Semua Tentang User
    @POST("/register")
    Call<UserRegistrationModel> register(@Body UserRegistrationModel user);

    @POST("/login")
    Call<AuthResponse> login(@Body AuthRequest authRequest);

    @GET("/users/profile")
    Call<UserDto> myProfile(@Header("Authorization")String token);

    @PATCH("/users/update/{userId}")
    Call<String> updateUser(
            @Path("userId") Long userId,
            @Body UserDto userDto,
            @Query("emailValidation") String emailValidation,
            @Query("passwordValidation") String passwordValidation
    );

    @GET("/users/all")
    Call<List<User>> getAllUsers(@Header("Authorization")String token);

    @GET("/users/cari")
    Call<List<User>> searchUsers(@Query("keyword") String keyword);
    @DELETE("/users/delete/{userId}")
    Call<Void> deleteUser(@Path("userId") Long userId);

    //Semua Tentang Riwayat
    @GET("/riwayat/all")
    Call<List<RiwayatDto>> getAllRiwayats(@Header("Authorization") String authToken);

    @GET("/riwayat/search")
    Call<List<RiwayatDto>> findRiwayatByDateOrClass(
            @Header("Authorization") String authToken,
            @Query("date") String date,
            @Query("class") Long id
    );

    @DELETE("/riwayat/delete/{id}")
    Call<String> deleteRiwayatById(@Path("id") Long id);

    //Semua Tentang Laporan
    @PATCH("/laporan/lapor/{kelas}")
    Call<String> updateLaporan(
            @Header("Authorization") String token,
            @Path("kelas") Long kelas,
            @Body LaporanDto laporanDto
    );

    @GET("/laporan/all")
    Call<List<LaporanDto>> getLaporanAll(@Header("Authorization")String token);

    @GET("/laporan/kelas/{kelas}")
    Call<List<LaporanDto>> getLaporanByKelas(
            @Header("Authorization")String token,
            @Path("kelas") Long kelas);

    @POST("/laporan/konfirmasi/{kelas}")
    Call<String> konfirmasiPerubahan(
            @Path("kelas") Long kelas,
            @Body List<String> attributes
    );
}
