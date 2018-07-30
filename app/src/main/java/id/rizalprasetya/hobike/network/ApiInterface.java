package id.rizalprasetya.hobike.network;

import java.sql.Time;

import id.rizalprasetya.hobike.model.BikeResult;
import id.rizalprasetya.hobike.model.HotelResult;
import id.rizalprasetya.hobike.model.Kembali;
import id.rizalprasetya.hobike.model.LoginResult;
import id.rizalprasetya.hobike.model.MemberResult;
import id.rizalprasetya.hobike.model.Sewa;
import id.rizalprasetya.hobike.model.SewaResult;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by Rizal Prasetya on 03/07/2018.
 */

public interface ApiInterface {

    @FormUrlEncoded
    @POST("api/login")
    Call<LoginResult> loginRequest(@Field("nama") String nama,
                                   @Field("password") String password);

    @FormUrlEncoded
    @POST("api/member")
    Call<MemberResult> registerRequest(@Field("nama") String nama,
                                       @Field("password") String password);

    @GET("api/member/{id}")
    Call<MemberResult> getMember(@Path("id") String id);

    //todo get id bike
    @GET("api/bike/{merek_sepeda}")
    Call<BikeResult> getBike(@Path("merek_sepeda") String merekSepeda);

    @GET("api/hotel/{nama}")
    Call<HotelResult> getNamaHotel(@Path("nama") String nama);

    @GET("api/hotel/{id}")
    Call<HotelResult> getIDHotel(@Path("id") String id);

    @FormUrlEncoded
    @POST("api/sewa")
    Call<SewaResult> sewaRequest(@Field("member_id") String member_id,
                                 @Field("bike_id") String bike_id,
                                 @Field("hotel_id_awal") String hotel_id_awal);

    @PUT("api/sewa/{id}")
    Call<SewaResult> kembaliRequest(@Path("id") int id,
                                    @Body Kembali hotel_id);

   /* @GET("api/sewa/show/{created_at}")
    Call<SewaResult> getSewa(@Field("created_at") Time created_at);*/

   /* @POST("api/member")
    Call<String> setMember(@Body Member member);*/

}
