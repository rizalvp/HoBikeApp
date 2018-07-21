package id.rizalprasetya.hobike.network;

import id.rizalprasetya.hobike.model.BikeResult;
import id.rizalprasetya.hobike.model.HotelResult;
import id.rizalprasetya.hobike.model.LoginResult;
import id.rizalprasetya.hobike.model.MemberResult;
import id.rizalprasetya.hobike.model.TransSewaResult;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
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

    //todo get id bike
    @GET("api/bike/{merek_sepeda}")
    Call<BikeResult> getBike(@Path("merek_sepeda") String merekSepeda);

    @GET("api/hotel/{nama}")
    Call<HotelResult> getHotel(@Path("nama") String nama);

    @POST("api/sewa")
    Call<TransSewaResult> sewaRequest(@Field("member_id") String member_id,
                                      @Field("bike_id") String bike_id,
                                      @Field("hotel_id_awal") String hotel_id_awal);

   /* @POST("api/member")
    Call<String> setMember(@Body Member member);*/

}
