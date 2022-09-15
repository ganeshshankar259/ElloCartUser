package com.ellocartuser.apiservices;

import com.ellocartuser.RoomsNew.room_info.model.Example;
import com.ellocartuser.apiservices.Responce.AddressResponce;
import com.ellocartuser.apiservices.Responce.B2BResponce;
import com.ellocartuser.apiservices.Responce.B2BStoreResponce;
import com.ellocartuser.apiservices.Responce.B2categoryResponce;
import com.ellocartuser.apiservices.Responce.BannerResponce;
import com.ellocartuser.apiservices.Responce.CartResponce;
import com.ellocartuser.apiservices.Responce.CatStoreAllResponce;
import com.ellocartuser.apiservices.Responce.CategoriesResponce;
import com.ellocartuser.apiservices.Responce.CheckOutPaymentResponce;
import com.ellocartuser.apiservices.Responce.CouponResponce;
import com.ellocartuser.apiservices.Responce.ElloRoom2Response;
import com.ellocartuser.apiservices.Responce.ElloRoomFilterResponse;
import com.ellocartuser.apiservices.Responce.LocationResponce;
import com.ellocartuser.apiservices.Responce.MyProfileResponce;
import com.ellocartuser.apiservices.Responce.OrdersResponce;
import com.ellocartuser.apiservices.Responce.ProductDetailedResponce;
import com.ellocartuser.apiservices.Responce.ProductsResponce;
import com.ellocartuser.apiservices.Responce.RegisterResponce;
import com.ellocartuser.apiservices.Responce.ServicePostDetailedResponce;
import com.ellocartuser.apiservices.Responce.ServiceProfileResponce;
import com.ellocartuser.apiservices.Responce.ServiceResponce;
import com.ellocartuser.apiservices.Responce.SplashResponce;
import com.ellocartuser.apiservices.Responce.StoreInfoResponce;
import com.ellocartuser.apiservices.Responce.StoresCatResponce;
import com.ellocartuser.apiservices.Responce.SubCatResponce;
import com.ellocartuser.cart.checkoutmodel.CheckoutCheckResponce;
import com.ellocartuser.rooms_old.ModelsandResponces.Availdates_Responce;
import com.ellocartuser.rooms_old.ModelsandResponces.BookedHotels_Responce;
import com.ellocartuser.rooms_old.ModelsandResponces.BookingsHistory_Responce;
import com.ellocartuser.rooms_old.ModelsandResponces.CartResponce_R;
import com.ellocartuser.rooms_old.ModelsandResponces.Cartdisplay_Responce;
import com.ellocartuser.rooms_old.ModelsandResponces.Dates_Responce;
import com.ellocartuser.rooms_old.ModelsandResponces.Delete_Cart_ById_Responce;
import com.ellocartuser.rooms_old.ModelsandResponces.Hotels_Responce;
import com.ellocartuser.rooms_old.ModelsandResponces.Roomcategories_Responce;
import com.ellocartuser.rooms_old.ModelsandResponces.Rooms_Responce;
import com.ellocartuser.tutions.tutmodelsandresponces.NewDates_Responce;
import com.ellocartuser.tutions.tutmodelsandresponces.TAppointListBUid_Responce;
import com.ellocartuser.tutions.tutmodelsandresponces.TAppoint_Responce;
import com.ellocartuser.tutions.tutmodelsandresponces.TDTimes_Responce;
import com.ellocartuser.tutions.tutmodelsandresponces.TDates_Responce;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("register")
    Call<RegisterResponce> register(
            @Field("user_email") String user_email,
            @Field("user_phone") String user_phone,
            @Field("user_phone_code") String user_phone_code,
            @Field("user_name") String user_name,
            @Field("user_lat") String user_lat,
            @Field("user_long") String user_long,
            @Field("user_device") String user_device,
            @Field("user_app") String user_app,
            @Field("user_ref") String user_ref);

    @FormUrlEncoded
    @POST("login_verify")
    Call<RegisterResponce> login(
            @Field("user_phone") String user_phone,
            @Field("user_phone_code") String user_phone_code,
            @Field("user_otp") String user_otp,
            @Field("user_lat") String user_lat,
            @Field("user_long") String user_long
    );

    @FormUrlEncoded
    @POST("login1")
    Call<RegisterResponce> checkuser(
            @Field("user_phone") String user_phone,
            @Field("user_phone_code") String user_phone_code
    );

    @FormUrlEncoded
    @POST("resend_otp")
    Call<RegisterResponce> resendotp(@Field("user_phone") String boy_phone,
                                      @Field("user_phone_code") String boy_phone_code

    );

    @FormUrlEncoded
    @POST("get_banners")
    Call<BannerResponce> getBanners( @Field("user_id") String user_id,@Field("user_lat") String user_lat,
                                     @Field("user_long") String user_long
    );

    @FormUrlEncoded
    @POST("get_banners_b2b")
    Call<BannerResponce> getBannersb2b(
            @Field("user_id") String user_id
    );

    @FormUrlEncoded
    @POST("get_home_categories1")
    Call<CategoriesResponce> getCategory(@Field("user_lat") String user_lat,
                                         @Field("user_long") String user_long,
                                         @Field("kms") String kms
                                         );

    @FormUrlEncoded
    @POST("get_stores_cat")  //seller_minimum_order , seller_offer
    Call<StoresCatResponce> getStoreCategory(
            @Field("user_id") String user_id,
                                         @Field("category_id") String category_id,
                                         @Field("seller_id") String seller_id,
                                         @Field("banner_id") String banner_id,
                                         @Field("user_lat") String user_lat,
                                         @Field("user_long") String user_long,
                                         @Field("kms") String kms,
                                         @Field("type") String type
    );

    @FormUrlEncoded
    @POST("get_stores_trending")
    Call<CategoriesResponce> getStoreTrending(

            @Field("user_lat") String user_lat,
            @Field("user_long") String user_long

    );

    @FormUrlEncoded
    @POST("get_stores_featured")
    Call<CategoriesResponce> getStoreFeatured(

            @Field("user_lat") String user_lat,
            @Field("user_long") String user_long

    );

    @FormUrlEncoded
    @POST("get_b2b_stores")
    Call<B2BStoreResponce> getB2BStoreCategory(
            @Field("user_id") String user_id,
            @Field("b2category_id") String b2category_id
    );

    @FormUrlEncoded
    @POST("wishlistb2b")
    Call<StoresCatResponce> updateB2BWishList(
            @Field("user_id") String user_id,
            @Field("b2b_id") String b2b_id
    );


    @FormUrlEncoded
    @POST("get_store_all")
    Call<CatStoreAllResponce> getStoreall(
            @Field("category_id") String category_id,
            @Field("seller_id") String seller_id
    );

    @FormUrlEncoded
    @POST("get_subcategories")
    Call<SubCatResponce> getSubCat(
            @Field("category_id") String category_id,
            @Field("seller_id") String seller_id
    );

  //  "seller_id": "","category_id":"","subcategory_id":""

    @FormUrlEncoded
    @POST("get_products")
    Call<ProductsResponce> getProducts(
            @Field("user_id") String user_id,
            @Field("seller_id") String seller_id,
            @Field("category_id") String category_id,
            @Field("subcategory_id") String subcategory_id
    );

    @FormUrlEncoded
    @POST("get_profile")
    Call<MyProfileResponce> getProgile(
            @Field("type") String type,
            @Field("user_id") String category_id
    );

    @FormUrlEncoded
    @POST("get_Wallet")
    Call<MyProfileResponce> get_wallet(
            @Field("user_id") String category_id
    );


    @FormUrlEncoded
    @POST("getAddress")
    Call<AddressResponce> getAddress(
            @Field("type") String type,
            @Field("user_id") String category_id,
            @Field("addr_type") String addr_type
    );

    @FormUrlEncoded
    @POST("addAddress")
    Call<AddressResponce> addAddresshome(
            @Field("type") String type, //places
            @Field("user_id") String category_id,
            @Field("addr_address") String addr_address,
            @Field("addr_lat") String addr_lat,
            @Field("addr_long") String addr_long
    );

    @FormUrlEncoded
    @POST("get_product_detail")
    Call<ProductDetailedResponce> getProductDetail(
            @Field("seller_id") String type,
            @Field("user_id") String category_id,
            @Field("product_id") String product_id,
            @Field("vproduct_id") String vproduct_id,
            @Field("sproduct_id") String sproduct_id
    );

    @FormUrlEncoded
    @POST("get_store_reviews")
    Call<ProductDetailedResponce> get_store_reviews(
            @Field("seller_id") String type,
            @Field("user_id") String category_id
    );

    @FormUrlEncoded
    @POST("get_b2product_detail")
    Call<ProductDetailedResponce> getProductB2BDetail(
            @Field("user_id") String category_id,
            @Field("product_id") String product_id,
            @Field("vproduct_id") String vproduct_id,
            @Field("sproduct_id") String sproduct_id
    );

    @FormUrlEncoded
    @POST("cart")
    Call<CartResponce> setCart(
            @Field("user_id") String category_id,
            @Field("type") String type,
            @Field("product_id") String seller_id,
            @Field("product_qty") String product_qty,
            @Field("sproduct_id") String sproduct_id,
            @Field("seller_id") String product_id
    );


    @FormUrlEncoded
    @POST("orders")
    Call<OrdersResponce> getOrders(
            @Field("type") String type,
            @Field("user_id") String category_id,
            @Field("order_id") String order_id
    );


    @FormUrlEncoded
    @POST("addAddress")
    Call<MyProfileResponce> addAddress(
            @Field("type") String type,
            @Field("user_id") String category_id,
            @Field("addr_address") String addr_address,
            @Field("addr_pincode") String addr_pincode,
            @Field("addr_city") String addr_city,
            @Field("addr_name") String addr_name,
            @Field("addr_phone") String addr_phone,
            @Field("addr_lat") String addr_lat,
            @Field("addr_long") String addr_long,
            @Field("addr_type") String addr_type
    );

    //orderto friend
    @FormUrlEncoded
    @POST("order_address")
    Call<MyProfileResponce> addAddressFriend(
            @Field("type") String type,
            @Field("user_id") String category_id,
            @Field("receiver_address") String addr_address,
            @Field("receiver_pincode") String addr_pincode,
            @Field("receiver_city") String addr_city,
            @Field("receiver_name") String addr_name,
            @Field("receiver_phone") String addr_phone,
            @Field("receiver_lat") String addr_lat,
            @Field("receiver_long") String addr_long,
            @Field("receiver_type") String addr_type
    );

    @FormUrlEncoded
    @POST("order_address")
    Call<AddressResponce> getAddressFriend(
            @Field("type") String type,
            @Field("user_id") String category_id,
            @Field("receiver_type") String addr_type
    );

    @FormUrlEncoded
    @POST("order_address")
    Call<MyProfileResponce> deleteAddressFriend(
            @Field("type") String type,
            @Field("user_id") String category_id,
            @Field("receiver_id") String addr_id
    );


    //

    @FormUrlEncoded
    @POST("notifys")
    Call<B2BResponce> getNotifications(
            @Field("type") String type,
            @Field("user_id") String user_id
    );

    @FormUrlEncoded
    @POST("editAddress")
    Call<MyProfileResponce> editAddress(
            @Field("addr_id") String addr_id,
            @Field("type") String type,
            @Field("user_id") String category_id,
            @Field("addr_address") String addr_address,
            @Field("addr_pincode") String addr_pincode,
            @Field("addr_city") String addr_city,
            @Field("addr_name") String addr_name,
            @Field("addr_phone") String addr_phone,
            @Field("addr_lat") String addr_lat,
            @Field("addr_long") String addr_long
    );

    @FormUrlEncoded
    @POST("dltAddress")
    Call<MyProfileResponce> deleteaddress(
            @Field("type") String type,
            @Field("user_id") String category_id,
            @Field("addr_id") String addr_id
    );


    @FormUrlEncoded
    @POST("getCoupons")
    Call<CouponResponce> getCoupon(
            @Field("version") String version,
            @Field("addr-id") String addr_id,
            @Field("user_id") String user_id
    );

    @POST("get_b2b_categories")
    Call<B2categoryResponce> getB2b(
    );

    @FormUrlEncoded
    @POST("get_b2b_subcategories")
    Call<B2categoryResponce> getB2bCat(
            @Field("b2b_id") String b2b_id,
            @Field("b2category_id") String b2category_id
    );

    @FormUrlEncoded
    @POST("get_b2products")
    Call<B2categoryResponce> getB2bsubCat(
            @Field("b2b_id") String b2b_id,
            @Field("b2subcategory_id") String b2subcategory_id
    );

    @FormUrlEncoded
    @POST("mywishlistb2b")
    Call<B2BStoreResponce> getB2BWishlist(
            @Field("user_id") String user_id
    );

    @FormUrlEncoded
    @POST("b2order")
    Call<B2categoryResponce> sendB2Bquote(
            @Field("seller_id") String seller_id,
            @Field("order_phone") String order_phone,
            @Field("user_id") String user_id,
            @Field("order_landmark") String order_landmark,
            @Field("product_id") String product_id,
            @Field("order_city") String order_city,
            @Field("order_address") String order_address,
            @Field("sproduct_id") String sproduct_id,
            @Field("order_name") String order_name,
            @Field("order_lat") String order_lat,
            @Field("order_long") String order_long,
            @Field("product_qty") String product_qty,
            @Field("order_pincode") String order_pincode,
            @Field("order_email") String order_email
    );




    @FormUrlEncoded
    @POST("reviewadd")
    Call<MyProfileResponce> setReview(
            @Field("type") String type,
            @Field("user_id") String user_id,
            @Field("product_id") String product_id,
            @Field("order_id") String order_id,
            @Field("review_id") String review_id,
            @Field("review_rate") String review_rate,
            @Field("review_message") String review_message
    );

    @FormUrlEncoded
    @POST("reviewadd")
    Call<MyProfileResponce> setStoreReview(
            @Field("type") String type,
            @Field("user_id") String user_id,
            @Field("seller_id") String seller_id,
            @Field("order_id") String order_id,
            @Field("review_rate") String review_rate,
            @Field("review_message") String review_message
    );

    @FormUrlEncoded
    @POST("track")
    Call<LocationResponce> getLocationData(
            @Field("type") String type,
            @Field("user_id") String user_id,
            @Field("order_id") String order_id
    );

    @Multipart
    @POST("update_profile")
    Call<MyProfileResponce> setProfile(
            @Part("type") RequestBody type,  //update
            @Part("user_id") RequestBody user_id,
            @Part("user_name") RequestBody user_name,
            @Part("user_email") RequestBody user_email,
            @PartMap Map<String, String> params,
            @Part MultipartBody.Part user_image
    );

    @Multipart
    @POST("ag_register")
    Call<MyProfileResponce> agent_register(
            @Part("user_id") RequestBody user_id,
            @Part("user_pan") RequestBody user_pan,
            @Part("user_bank_acc") RequestBody user_bank_acc,
            @Part("user_bank_ifsc") RequestBody user_bank_ifsc,
            @Part MultipartBody.Part pan_image,
            @Part MultipartBody.Part image_image
    );

    @FormUrlEncoded
    @POST("b2orders")
    Call<B2BResponce> getB2Borders(
            @Field("user_id") String user_id
    );

    @FormUrlEncoded
    @POST("wishlist")
    Call<StoresCatResponce> getwishlist(
            @Field("user_id") String user_id,
            @Field("type") String type,
            @Field("user_lat") String user_lat,
            @Field("user_long") String user_long,
            @Field("kms") String kms
    );

    @FormUrlEncoded
    @POST("wishlist")
    Call<StoresCatResponce> setwishlist(
            @Field("user_id") String user_id,
            @Field("type") String type,
            @Field("seller_id") String seller_id
    );


    @FormUrlEncoded
    @POST("orders")
    Call<StoresCatResponce> cancelorder( //4
            @Field("user_id") String user_id,
            @Field("type") String type,
            @Field("order_id") String order_id,
            @Field("order_status") String order_status
    );

    @FormUrlEncoded
    @POST("search")
    Call<StoresCatResponce> productsearch( //4
                                               @Field("search") String search,
                                               @Field("user_lat") String user_lat,
                                               @Field("user_long") String user_long,
                                               @Field("seller_id") String seller_id
    );

    @FormUrlEncoded
    @POST("update_device")
    Call<RegisterResponce> updateToken( //4
                                           @Field("user_phone") String user_phone,
                                           @Field("user_phone_code") String user_phone_code,
                                           @Field("user_id") String user_id,
                                           @Field("user_token") String user_token,
                                           @Field("user_device") String user_device,
                                        @Field("user_app") String user_app,
                                           @Field("user_lat") String user_lat,
                                           @Field("user_long") String user_long
    );

    @FormUrlEncoded
    @POST("notifys")
    Call<StoresCatResponce> getNotification(
                                         @Field("user_id") String user_id,
                                         @Field("type") String type
    );



    @FormUrlEncoded
    @POST("statement")
    Call<StoresCatResponce> getStatements(
            @Field("user_id") String user_id

    );

    @FormUrlEncoded
    @POST("ag_check")
    Call<StoresCatResponce> agent_check(
            @Field("user_id") String user_id
    );

    @FormUrlEncoded
    @POST("ag_sellers")
    Call<StoresCatResponce> agent_sellers(
            @Field("user_id") String user_id
    );

    @FormUrlEncoded
    @POST("ag_earnings")
    Call<StoresCatResponce> agent_earning(
            @Field("user_id") String user_id
    );

    @FormUrlEncoded
    @POST("ag_sellers_r")
    Call<StoresCatResponce> agent_sellers_r(
            @Field("user_id") String user_id
    );


    @GET("get_splash") //splash
    Call<SplashResponce> getSplash();

    @FormUrlEncoded
    @POST("check_ver")
    Call<StoresCatResponce> getVersioncode(

            @Field("ver") String ver,
            @Field("os") String os

    );
    // services

    @FormUrlEncoded
    @POST("apiserv/") //home service
    Call<CategoriesResponce> getHomeService(@Field("api") String api,
                                            @Field("user_lat") String lat,
                                            @Field("user_long") String longi);

    //rooms code starts hear////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    //@FormUrlEncoded
    @POST("apir/roomcategories") //home service
    Call<Roomcategories_Responce> getHomeRooms();

    @POST("apir/roomcategories") //home service
    Call<Roomcategories_Responce> getHomeSeemore();

    @FormUrlEncoded
    @POST("apir/roomsubcategories") //home service
    Call<Hotels_Responce> hotels_list(
            @Field("rsub_main") String rsub_main,
            @Field("rsub_city") String rsub_city
    );
    @FormUrlEncoded
    @POST("apir/roomchildcategories") //home service
    Call<Rooms_Responce> rooms_list(
            @Field("rchild_main") String rchild_main

    );
    @FormUrlEncoded
    @POST("apir/avail_dates_rooms")
    Call<Availdates_Responce> avail_rooms(
            @Field("rd_date") String rd_date,
            @Field("room_id") String room_id
    );
    @FormUrlEncoded
    @POST("apir/get_room_dates")
    Call<Dates_Responce> dates_list(
            @Field("room_id") String room_id
    );
    @FormUrlEncoded
    @POST("apir/cart_count_rooms")
    Call<CartResponce_R> cart_count(
            @Field("user_id") String user_id,
            @Field("type") String type
    );
    @FormUrlEncoded
    @POST("apir/add_room_to_cart")
    Call<CartResponce_R> set_cart_data(
            @Field("user_id") String user_id,
            @Field("type") String type,
            @Field("room_id") String room_id,
            @Field("room_qty") String room_qty,
            @Field("bcart_per") String bcart_per,
            @Field("user_date") String user_date,
            @Field("user_time") String user_time,
            @Field("hotel_id") String hotel_id

    );
    @FormUrlEncoded
    @POST("apir/get_rooms_cart")
    Call<Cartdisplay_Responce> cart_display(
            @Field("user_id") String user_id,
            @Field("type") String type
    );

    @FormUrlEncoded
    @POST("apir/room_delete_cart_by_id")
    Call<Delete_Cart_ById_Responce> delete_cart_bybcartId(
            @Field("type") String type,
            @Field("bcart_userid") String bcart_userid,
            @Field("bcart_id") String bcart_id

    );

    @FormUrlEncoded
    @POST("apir/room_clear_cart")
    Call<Delete_Cart_ById_Responce> delete_clear_cart(
            @Field("type") String type,
            @Field("bcart_userid") String bcart_userid
    );
    @FormUrlEncoded
    @POST("apir/room_booking")
    Call<Delete_Cart_ById_Responce> book_rooms(
            @Field("b_user_id") String b_user_id,
            @Field("b_total") String b_total,
            @Field("razorpayid") String razorpayid
    );
    @FormUrlEncoded
    @POST("apir/get_rooms_booked")
    Call<BookedHotels_Responce> get_rooms_booked(
            @Field("user_id") String user_id,
            @Field("type") String type
    );

    @FormUrlEncoded
    @POST("apir/get_rooms_history_by_OrderId")
    Call<BookingsHistory_Responce> bookings_history_display(
            @Field("user_id") String user_id,
            @Field("type") String type,
            @Field("b_orderid") String b_orderid

    );
    @FormUrlEncoded
    @POST("apiserv/get_tut_dates")
    Call<TDates_Responce> get_tut_dates(
            @Field("tut_id") String tut_id,
            @Field("post_id") String post_id

    );

    @FormUrlEncoded
    @POST("apiserv/get_post_detail")
    Call<NewDates_Responce> get_get_post_detail(
            @Field("post_id") String post_id
    );

    @FormUrlEncoded
    @POST("apiserv/get_tut_timings")
    Call<TDTimes_Responce> get_tut_timings(
            @Field("tut_id") String tut_id,
            @Field("date_id") String date_id
    );
    @FormUrlEncoded
    @POST("apiserv/checkUserTimeDatefor_aptbooking")
    Call<TAppoint_Responce> set_tut_booking(
            @Field("u_id") String u_id,
            @Field("tut_id") String tut_id,
            @Field("a_date") String date_id,
            @Field("a_time") String a_time,
            @Field("appoint_post_id") String appoint_post_id
    );

    @FormUrlEncoded
    @POST("apiserv/check_booking_availability")
    Call<TAppoint_Responce> check_booking_availability(
            @Field("post_id") String post_id,
            @Field("time") String time,
            @Field("user_id") String user_id,
            @Field("service_id") String service_id,
            @Field("date") String date
    );

    @FormUrlEncoded
    @POST("apiserv/services_my_list") //home service
    Call<TAppointListBUid_Responce> getappointlist_byuid(
            @Field("u_id") String u_id
    );

    @FormUrlEncoded
    @POST("apir/room_cancle_byOrderid")
    Call<BookingsHistory_Responce> cancle_booking(
            @Field("type") String type,
            @Field("b_orderid") String b_orderid
    );
    //===================Tutions Concept ===========//


    //==============================/////////////////////
    ///////////////////////////////////////////////////////
    @FormUrlEncoded
    @POST("apiserv")
    Call<SubCatResponce> getServiceSubCat(
            @Field("api") String api,
            @Field("servcat_id") String cat_id,
            @Field("user_id") String user_id
    );

    @FormUrlEncoded
    @POST("apiserv")
    Call<ServiceResponce> checkservice(
            @Field("api") String api,
            @Field("user_id") String user_id
    );


    @FormUrlEncoded
    @POST("apiserv")
    Call<ServiceProfileResponce> getServiceProfiles(
            @Field("api") String api,
            @Field("user_lat") String lat,
            @Field("user_long") String longi,
            @Field("servsubcategory_id") String servsubcategory_id
    );

    //payment success
    @FormUrlEncoded
    @POST("apiserv")
    Call<ServiceResponce> serviceonPaymentSuccess(
            @Field("api") String api,
            @Field("user_id") String user_id,
            @Field("subsc_id") String subsc_id,
            @Field("pay_ref") String pay_ref,
            @Field("subsc_amount") String subsc_amount
    );

    // add post in service

    @Multipart
    @POST("apiserv")
    Call<ServiceResponce> serviceonaddpost(
            @Part("api") RequestBody api,
            @Part("user_id") RequestBody user_id,
            @Part("post_cat") RequestBody post_cat,
            @Part("post_scats") RequestBody post_scats,
            @Part("post_title") RequestBody post_title,
            @Part("post_address") RequestBody post_address,
            @Part("post_description") RequestBody post_description,
            @Part("post_status") RequestBody post_status,
            @Part("post_lat") RequestBody post_lat,
            @Part("post_long") RequestBody post_long,
            @Part("post_expr") RequestBody post_expr,
            @Part MultipartBody.Part user_image
    );

    @FormUrlEncoded
    @POST("apiserv")
    Call<ServicePostDetailedResponce> getPostDetails(
            @Field("api") String api,
            @Field("post_id") String user_id
    );

    @FormUrlEncoded
    @POST("checkDelivery")
    Call<StoresCatResponce> getCheckDelivery(
            @Field("user_id") String user_id,
            @Field("addr_lat") String addr_lat,
            @Field("addr_long") String addr_long,
            @Field("seller_lat") String seller_lat,
            @Field("seller_long") String seller_long
    );

    @FormUrlEncoded
    @POST("check_delivery4")
    Call<CheckoutCheckResponce> check_delivery_checkout(
            @Field("user_id") String user_id,
            @Field("addr_id") String addr_id,
            @Field("coup_id") String coup_id,
            @Field("wallet") String wallet,
            @Field("tips") String tips,
            @Field("pwallet") String pwallet,
            @Field("type") String type
    );

    @FormUrlEncoded
    @POST("get_store_detail")
    Call<StoreInfoResponce> getStoreDetail(
            @Field("seller_id") String seller_id
    );

    @FormUrlEncoded
    @POST("checkout6")
    Call<MyProfileResponce> placeorderPicked(
            @Field("user_id") String user_id,
            @Field("addr_id") String addr_id,
            @Field("wallet") String wallet,
            @Field("pwallet") String pwallet,
            @Field("type") String type,
            @Field("tips") String tips,
            @Field("order_wallet") String order_wallet,
            @Field("pick_date") String pick_date,
            @Field("pick_time") String pick_time, //not re
            @Field("coup_id") String coup_id,
            @Field("order_type") String order_type, //get popop value pass pic=0,deli=1
            @Field("order_payref") String order_payref, //0
            @Field("order_address") String order_address, //0
            @Field("order_pay_type") String order_pay_type, //cod
            @Field("order_final") String order_final, //final amt
            @Field("order_tax") String order_tax, //0
//          @Field("order_delivery") String order_delivery, //0
            @FieldMap HashMap<String, String> hashFields
    );


    @FormUrlEncoded
    @POST("checkout6")
    Call<CheckOutPaymentResponce> getcheckout2(
            @Field("user_id") String user_id,
            @Field("addr_id") String addr_id,
            @Field("wallet") String wallet,
            @Field("pwallet") String pwallet,
            @Field("type") String type,
            @Field("tips") String tips,
            @Field("order_wallet") String order_wallet,
            @Field("pick_date") String pick_date,
            @Field("pick_time") String pick_time, //not re
            @Field("coup_id") String coup_id,
            @Field("coup_amount") String coup_amt,
            @Field("order_type") String order_type, //get popop value pass pic=0,deli=1
            @Field("order_payref") String order_payref, //0
            @Field("order_address") String order_address, //0
            @Field("order_pay_type") String order_pay_type, //cod
            @Field("order_final") String order_final, //final amt
            @Field("order_tax") String order_tax, //0
     //     @Field("order_delivery") String order_delivery, //0
            @FieldMap HashMap<String, String> hashFields
    );

    @FormUrlEncoded
    @POST("checkoutpay")    //checkout payment status
    Call<CheckOutPaymentResponce> getcheckoutpay(
            @Field("user_id") String user_id,
            @Field("order_id") String order_id,
            @Field("order_payref") String order_payref
    );


    @FormUrlEncoded
    @POST("update_store_reviews")    //checkout payment status
    Call<MyProfileResponce> update_store_reviews(
            @Field("user_id") String user_id,
            @Field("reviews_id") String reviews_id,
            @Field("reviews_rate") String reviews_rate,
            @Field("reviews_message") String reviews_message
    );

    @FormUrlEncoded
    @POST("roomtypes2")
    Call<Example> roomtypes2(
            @Field("hotel_id") String hotel_id
    );

    //ellorooms
    @FormUrlEncoded
    @POST("get_languages")
    Call<ElloRoomFilterResponse> getlanguages(
            @Field("test") String test
    );

    @FormUrlEncoded
    @POST("get_amenities")
    Call<ElloRoomFilterResponse> get_amenities(
            @Field("test") String test
    );

    @FormUrlEncoded
    @POST("get_rooms")
    Call<ElloRoomFilterResponse> get_rooms(
            @Field("type") String type,
            @Field("user_lat") String user_lat,
            @Field("user_long") String user_long
    );

    @FormUrlEncoded
    @POST("get_rooms")
    Call<ElloRoomFilterResponse> get_rooms1(
            @Field("type") String type,
            @Field("from") String from,
            @Field("to") String to,
            @Field("persons") String persons,
            @Field("rooms") String rooms,
            @Field("user_lat") String user_lat,
            @Field("user_long") String user_long,
            @Field("price") String price,
            @Field("language") String language,
            @Field("ratings") String ratings,
            @Field("amenities") String amenities
    );

//checkot room page
    @FormUrlEncoded
    @POST("room_check")
    Call<ElloRoomFilterResponse> get_room_cart(
            @Field("user_id") String user_id,
            @Field("room_id") String room_id,
            @Field("from") String from,
            @Field("to") String to,
            @Field("persons") String persons,
            @Field("rooms") String rooms,
            @Field("user_lat") String user_lat,
            @Field("user_long") String user_long
    );

    @FormUrlEncoded
    @POST("get_room_detail")
    Call<ElloRoom2Response> get_room_detail(
            @Field("room_id") String room_id
    );

    @FormUrlEncoded
    @POST("checkout6")
    Call<CheckOutPaymentResponce> getroomcheckout(
            @Field("user_id") String user_id,
            @Field("room_id") String room_id,
            @Field("from") String from,
            @Field("to") String to,
            @Field("persons") String persons,
            @Field("rooms") String rooms,
            @Field("pay_type") String pay_type
    );


    @FormUrlEncoded
    @POST("checkoutpay")    //checkout payment status
    Call<CheckOutPaymentResponce> getcheckoutpay_room(
            @Field("user_id") String user_id,
            @Field("b_id") String order_id,
            @Field("b_payref") String order_payref
    );

//    @FormUrlEncoded
//    @POST("verify_otp")
//    Call<RegisterResponce> verifyotp(@Field("type") String type,
//                                     @Field("boy_id") String boy_id,
//                                     @Field("boy_phone") String boy_phone,
//                                     @Field("boy_phone_code") String boy_phone_code,
//                                     @Field("otp") String otp
//                                     );
//    @FormUrlEncoded
//    @POST("login")
//    Call<RegisterResponce> login(@Field("type") String type,
//                                     @Field("boy_phone") String boy_phone,
//                                     @Field("boy_phone_code") String boy_phone_code
//
//    );
//
//    @FormUrlEncoded
//    @POST("get_my_deliverys")  //ongoing
//    Call<MyDelhiveryResponce> mydelihiverys(
//            @Field("boy_id") String boy_id,
//            @Field("from") String from,
//            @Field("to") String to
//                                            );
//
//    @FormUrlEncoded
//    @POST("get_new_deliverys") //home
//    Call<MyDelhiveryResponce> newdelihiverys(@Field("boy_id") String boy_id
//    );
//
//    @FormUrlEncoded
//    @POST("get_delivery_detail")  //home onclick
//    Call<MyDelhiveryResponce> getOrderDetailsById(@Field("boy_id") String boy_id,@Field("order_id") String order_id
//    );
//
//    @FormUrlEncoded
//    @POST("get_completed_deliverys") //delhivery
//    Call<MyDelhiveryResponce> getCompletedDelhivery(@Field("boy_id") String boy_id,   @Field("from") String from,
//                                                    @Field("to") String to
//    );
//
//    @FormUrlEncoded
//    @POST("get_delivery_detail") //delhivery
//    Call<MyDelhiveryResponce> getDeliveryDetail(@Field("boy_id") String boy_id,   @Field("order_id") String orderid
//    );
//
//    @FormUrlEncoded
//    @POST("assign_delivery") //accpt
//    Call<MyDelhiveryResponce> getAccept(@Field("boy_id") String boy_id,   @Field("order_id") String orderid
//    );
//
//    @FormUrlEncoded
//    @POST("update_delivery") //udate delivery statsnin ongoing
//    Call<MyDelhiveryResponce> updateDelivery(@Field("boy_id") String boy_id,
//                                             @Field("order_id") String orderid ,  @Field("order_assign") String order_assign
//    );
//
//    @FormUrlEncoded
//    @POST("getProfile") //udate delivery statsnin ongoing
//    Call<RegisterResponce> getProfile(@Field("boy_id") String boy_id);
//
//
//
//    @FormUrlEncoded
//    @POST("update_device") //udate delivery statsnin ongoing
//    Call<RegisterResponce> updaeDevice(@Field("boy_id") String boy_id);
//
//
//    @FormUrlEncoded
//    @POST("bankDetail") //bank details
//    Call<BankDetailsResponce> updateBankDetails(@Field("boy_id") String boy_id,
//                                                @Field("type") String type,
//                                                @Field("boy_bank_name") String boy_bank_name,
//                                                @Field("boy_bank_acc") String boy_bank_acc,
//                                                @Field("boy_bank_ifsc") String boy_bank_ifsc,
//                                                @Field("boy_bank_branch") String boy_bank_branch,
//                                                @Field("boy_bank_phone") String boy_bank_phone
//    );
//
//    @FormUrlEncoded
//    @POST("makeDeposit") //payment
//    Call<RegisterResponce> paymentDone(@Field("boy_id") String boy_id,@Field("pay_ref") String pay_ref,@Field("pay_amt") String pay_amt);
//
//    @FormUrlEncoded
//    @POST("getWallet") //wallet
//    Call<WalleteResponce> getWallet(@Field("boy_id") String boy_id);

}
