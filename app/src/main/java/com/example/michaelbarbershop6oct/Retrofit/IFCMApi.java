package com.example.michaelbarbershop6oct.Retrofit;

import com.example.michaelbarbershop6oct.Model.FCMResponse;
import com.example.michaelbarbershop6oct.Model.FCMSendData;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;


public interface IFCMApi {
    @Headers({
            "Content-Type:application/json",
            "Authorization:key=AAAABMLwwGc:APA91bHNDZHFbPZman8DT2MNxurN-dXYDLmAfTOuCY-hruxpUkiFkg9kAe1k5tfWRobEwvi2FLnt5EOZ08txZP_IJHIVr5Ugup12uZCxG2iL6yKXED4-igBZZNu8VesQSvIGnk2BNTWy"
    })
    @POST("fcm/send")
    Observable<FCMResponse> sendNotification(@Body FCMSendData body);
}
