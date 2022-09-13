package com.deadly.orderapp.services;

import com.deadly.orderapp.models.response.*;
import com.deadly.orderapp.models.response.auth.AuthenticationResponse;
import com.deadly.orderapp.models.response.auth.VerifyResponse;
import com.deadly.orderapp.models.response.event.EventListResponse;
import com.deadly.orderapp.models.response.event.EventResponse;
import com.deadly.orderapp.models.response.generic.ChangedResponse;
import com.deadly.orderapp.models.response.generic.VersionResponse;
import com.deadly.orderapp.models.response.order.OrderListResponse;
import com.deadly.orderapp.models.response.order.OrderResponse;
import com.deadly.orderapp.models.response.product.ProductListResponse;
import com.deadly.orderapp.models.response.product.ProductResponse;
import com.deadly.orderapp.models.response.user.UserListResponse;
import com.deadly.orderapp.models.response.user.UserResponse;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ClientInterface {

    // Authentication

    @POST("/auth/login")
    @Headers("Authorize: false")
    Call<AuthenticationResponse> postLogin(@Body HashMap<String, String> body);

    @GET("/auth/test")
    Call<VerifyResponse> getAuthTest();

    @GET("/auth/admin/test")
    Call<VerifyResponse> getAuthAdminTest();

    // Generics

    @GET("/health")
    Call<BaseResponse> getHealth();

    @GET("/version")
    Call<VersionResponse> getVersion();

    @GET("/last_changed")
    Call<ChangedResponse> getLastChanged();

    // Users

    @GET("/user/all")
    Call<UserListResponse> getAllUsers();

    @GET("/user/{uuid}")
    Call<UserResponse> getUserByUuid(@Path("uuid") String uuid);

    @Deprecated
    @GET("/user/last_changed")
    Call<ResponseBody> getUsersLastChanged();

    // Products

    @GET("/product/all")
    Call<ProductListResponse> getAllProducts();

    @GET("/product/{uuid}")
    Call<ProductResponse> getProductByUuid(@Path("uuid") String uuid);

    @Deprecated
    @GET("/product/last_changed")
    Call<ResponseBody> getProductsLastChanged();

    // Orders

    @GET("/order/current")
    Call<OrderResponse> getCurrentOrder();

    @POST("/order/add")
    Call<ResponseBody> postAddOrder(@Body HashMap<String, ?> body);

    @DELETE("/order/delete")
    Call<ResponseBody> deleteOrder();

    @PUT("/order/edit")
    Call<ResponseBody> putEditOrder(@Body HashMap<String, ?> body);

    @GET("/order/all")
    Call<OrderListResponse> getAllOrders();

    @GET("/order/{uuid}")
    Call<OrderResponse> getOrderByUuid(@Path("uuid") String uuid);

    @GET("/order/{event}")
    Call<OrderResponse> getOrderByEvent(@Path("event") String event);

    @Deprecated
    @GET("/order/last_changed")
    Call<ResponseBody> getOrdersLastChanged();

    // Events

    @GET("/event/current")
    Call<EventResponse> getCurrentEvent();

    @GET("/event/all")
    Call<EventListResponse> getAllEvents();

    @GET("/event/{uuid}")
    Call<EventResponse> getEventByUuid(@Path("uuid") String uuid);

    @Deprecated
    @GET("/event/last_changed")
    Call<?> getEventsLastChanged();

}
