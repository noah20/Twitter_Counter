package com.example.twitterintegration.data.utils

object ApiKeys {

    const val POST_TWEET_URL  = "https://twitter.com/intent/tweet?text="

    const val redirectUrl: String = "myapp://callback"

    const val baseUrl = "https://api.twitter.com/"

    const val apiKey = "myzCsYas08779O5mxmHPt1NfD"
    const val apiKeySecret = "jhigOCsmTQUdEJ1mJqwXqonDVgKOkoTJteEGz1RQm03KPZK840"
    const val clientId = "OHJBam02YmhrRTlVZW82bUc5OVE6MTpjaQ"
    const val clientSecret = "PBylIgqwUZE2lTe4HSvLst6tfAMPKyH6y7nm95BvmQjs9tzxCf"
    const val bearerToken = "AAAAAAAAAAAAAAAAAAAAALDvugEAAAAAznE4gA045oZWIOQOiSSpZhwZC88%3DEsHiXpZ47bBx9b6BV90kaojk4uKHGHWCFCuMd6LfzvrZGI2sYw"
    const val accessToken = "4622262399-OZqUVuvlH6zYy8nvS993ZaRKbqZE6zmah4RDU69"
    const val accessTokenSecret = "QPFTZ2YXLPerzhk6Ms0mQEtXH4o2BTnMB3qx5VCjUTPfF"


    fun buildClientAuth(responseType:String , clientId:String, redirectUrl:String, state:String,codeChallenge:String):String{
        return "https://twitter.com/i/oauth2/authorize" +
                "?response_type=$responseType" +
                "&client_id=$clientId" +
                "&redirect_uri=$redirectUrl" +
                "&scope=tweet.read%20tweet.write" +
                "&state=$state" +
                "&code_challenge=$codeChallenge" +
                "&code_challenge_method=S256"
    }
}