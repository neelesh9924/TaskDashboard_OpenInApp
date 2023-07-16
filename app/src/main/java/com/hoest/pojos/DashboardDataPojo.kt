package com.hoest.pojos

import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import org.json.JSONObject

class DashboardDataPojo {

    @SerializedName("status")
    var status: Boolean? = null

    @SerializedName("statusCode")
    var statusCode: Int? = null

    @SerializedName("message")
    var message: String? = null

    @SerializedName("support_whatsapp_number")
    var supportWhatsappNumber: String? = null

    @SerializedName("extra_income")
    var extraIncome: Double? = null

    @SerializedName("total_links")
    var totalLinks: Int? = null

    @SerializedName("total_clicks")
    var totalClicks: Int? = null

    @SerializedName("today_clicks")
    var todayClicks: Int? = null

    @SerializedName("top_source")
    var topSource: String? = null

    @SerializedName("top_location")
    var topLocation: String? = null

    @SerializedName("startTime")
    var startTime: String? = null

    @SerializedName("links_created_today")
    var linksCreatedToday: Int? = null

    @SerializedName("applied_campaign")
    var appliedCampaign: Int? = null

    @SerializedName("data")
    var data: Data? = Data()


    data class Data(

        @SerializedName("recent_links") var recentLinks: ArrayList<RecentLinks> = arrayListOf(),
        @SerializedName("top_links") var topLinks: ArrayList<TopLinks> = arrayListOf(),
        @SerializedName("overall_url_chart") var overallUrlChart: JsonElement? = null

    )

    data class RecentLinks(

        @SerializedName("url_id") var urlId: Int? = null,
        @SerializedName("web_link") var webLink: String? = null,
        @SerializedName("smart_link") var smartLink: String? = null,
        @SerializedName("title") var title: String? = null,
        @SerializedName("total_clicks") var totalClicks: Int? = null,
        @SerializedName("original_image") var originalImage: String? = null,
        @SerializedName("thumbnail") var thumbnail: String? = null,
        @SerializedName("times_ago") var timesAgo: String? = null,
        @SerializedName("created_at") var createdAt: String? = null,
        @SerializedName("domain_id") var domainId: String? = null,
        @SerializedName("url_prefix") var urlPrefix: String? = null,
        @SerializedName("url_suffix") var urlSuffix: String? = null,
        @SerializedName("app") var app: String? = null
    )

    data class TopLinks(

        @SerializedName("url_id") var urlId: Int? = null,
        @SerializedName("web_link") var webLink: String? = null,
        @SerializedName("smart_link") var smartLink: String? = null,
        @SerializedName("title") var title: String? = null,
        @SerializedName("total_clicks") var totalClicks: Int? = null,
        @SerializedName("original_image") var originalImage: String? = null,
        @SerializedName("thumbnail") var thumbnail: String? = null,
        @SerializedName("times_ago") var timesAgo: String? = null,
        @SerializedName("created_at") var createdAt: String? = null,
        @SerializedName("domain_id") var domainId: String? = null,
        @SerializedName("url_prefix") var urlPrefix: String? = null,
        @SerializedName("url_suffix") var urlSuffix: String? = null,
        @SerializedName("app") var app: String? = null

    )

    /* data class OverallUrlChart(

         @SerializedName("2023-06-14") var 2023-06-14 : Int? = null,
     @SerializedName("2023-06-15")
     var 2023-06-15 : Int? = null,

     @SerializedName("2023-06-16")
     var 2023-06-16 : Int? = null,

     @SerializedName("2023-06-17")
     var 2023-06-17 : Int? = null,

     @SerializedName("2023-06-18")
     var 2023-06-18 : Int? = null,

     @SerializedName("2023-06-19")
     var 2023-06-19 : Int? = null,

     @SerializedName("2023-06-20")
     var 2023-06-20 : Int? = null,

     @SerializedName("2023-06-21")
     var 2023-06-21 : Int? = null,

     @SerializedName("2023-06-22")
     var 2023-06-22 : Int? = null,

     @SerializedName("2023-06-23")
     var 2023-06-23 : Int? = null,

     @SerializedName("2023-06-24")
     var 2023-06-24 : Int? = null,

     @SerializedName("2023-06-25")
     var 2023-06-25 : Int? = null,

     @SerializedName("2023-06-26")
     var 2023-06-26 : Int? = null,

     @SerializedName("2023-06-27")
     var 2023-06-27 : Int? = null,

     @SerializedName("2023-06-28")
     var 2023-06-28 : Int? = null,

     @SerializedName("2023-06-29")
     var 2023-06-29 : Int? = null,

     @SerializedName("2023-06-30")
     var 2023-06-30 : Int? = null,

     @SerializedName("2023-07-01")
     var 2023-07-01 : Int? = null,

     @SerializedName("2023-07-02")
     var 2023-07-02 : Int? = null,

     @SerializedName("2023-07-03")
     var 2023-07-03 : Int? = null,

     @SerializedName("2023-07-04")
     var 2023-07-04 : Int? = null,

     @SerializedName("2023-07-05")
     var 2023-07-05 : Int? = null,

     @SerializedName("2023-07-06")
     var 2023-07-06 : Int? = null,

     @SerializedName("2023-07-07")
     var 2023-07-07 : Int? = null,

     @SerializedName("2023-07-08")
     var 2023-07-08 : Int? = null,

     @SerializedName("2023-07-09")
     var 2023-07-09 : Int? = null,

     @SerializedName("2023-07-10")
     var 2023-07-10 : Int? = null,

     @SerializedName("2023-07-11")
     var 2023-07-11 : Int? = null,

     @SerializedName("2023-07-12")
     var 2023-07-12 : Int? = null,

     @SerializedName("2023-07-13")
     var 2023-07-13 : Int? = null,

     @SerializedName("2023-07-14")
     var 2023-07-14 : Int? = null

     )*/


}