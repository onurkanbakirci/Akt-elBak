package com.onurkanbakirci.aktuelBak.data.network

import org.jsoup.Jsoup
import org.jsoup.nodes.Document

interface IApi {


    companion object : IApi{

        operator fun invoke(connectionUrl: String) : Document{
            var document = Jsoup.connect(connectionUrl).validateTLSCertificates(false).get()
            return document
        }
    }
}
