package com.sample.knowmerce.feature.main.card.models

internal sealed class SearchViewData {
    data class Image(
        val thumbnail: String,
        val collection: String,
        val siteName: String,
        val link: String,
    ) : SearchViewData()

    data class Video(
        val thumbnail: String,
        val author: String,
        val title: String,
        val link: String,
    ) : SearchViewData()
}

internal val sampleSearchViewDataImages = listOf(
    SearchViewData.Image(
        thumbnail = "https://search3.kakaocdn.net/argon/130x130_85_c/75tKpqdPXL8",
        collection = "blog",
        siteName = "네이버블로그",
        link = "http://blog.naver.com/kimtaehyun20_/223452477777"
    ),
    SearchViewData.Image(
        thumbnail = "https://search2.kakaocdn.net/argon/130x130_85_c/8JHLtx6m8g4",
        collection = "blog",
        siteName = "티스토리",
        link = "https://forestsoop.tistory.com/4"
    ),
    SearchViewData.Image(
        thumbnail = "https://search2.kakaocdn.net/argon/130x130_85_c/CDFEM32xIKs",
        collection = "news",
        siteName = "머니투데이",
        link = "http://v.media.daum.net/v/20221114095406887"
    ),
    SearchViewData.Image(
        thumbnail = "https://search3.kakaocdn.net/argon/130x130_85_c/5a9PhAL5Tia",
        collection = "blog",
        siteName = "티스토리",
        link = "https://livingthegoodlonglife.tistory.com/93"
    ),
)

internal val sampleSearchViewDataVideos = listOf(
    SearchViewData.Video(
        thumbnail = "https://search4.kakaocdn.net/argon/138x78_80_pr/3FskUvCLNU6",
        author = "Honey Kids TV",
        title = "ABC Song l 해변에서 알파벳 배우기 l 알파벳송 l ABC송",
        link = "http://www.youtube.com/watch?v=2fIaDXO7sxk"
    ),
    SearchViewData.Video(
        thumbnail = "https://search3.kakaocdn.net/argon/138x78_80_pr/C3UXMcmOfCO",
        author = "Magnet Art",
        title = "네오큐브 자석 알파벳 송 ABC Song",
        link = "http://www.youtube.com/watch?v=MsgagnYe8Xg"
    ),
    SearchViewData.Video(
        thumbnail = "https://search1.kakaocdn.net/argon/138x78_80_pr/H3eJbLPGMC9",
        author = "oricon",
        title = "FRUITS ZIPPER、スニーカースタイルで個性発揮！メイキング&インタビュー映像公開 ABC-MART『adidas「PARK ST LITE」』",
        link = "http://www.youtube.com/watch?v=UloWbqJX1m4"
    ),
    SearchViewData.Video(
        thumbnail = "https://search1.kakaocdn.net/argon/138x78_80_pr/4A4AqScZpag",
        author = "Simple Color",
        title = "동요와 함께 ABC를 배워요 | 영어배우기 | ABC 배우기 | 알파벳 읽는 방법 | 심플컬러 Simple Color",
        link = "http://www.youtube.com/watch?v=Ge7pwvvP-EM"
    ),
)