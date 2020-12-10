package com.onurkanbakirci.aktuelBak.data.repository

import com.onurkanbakirci.aktuelBak.data.model.Catalog
import com.onurkanbakirci.aktuelBak.data.network.IApi
import io.reactivex.Observable
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import java.io.IOException

class CatalogRepository {

    fun getBIMCatalogs(): Observable<List<Catalog>> {
        return Observable.create{emitter ->

            var document: Elements

            try {
                document  = IApi("https://www.bim.com.tr/Categories/680/afisler.aspx")
                .select("div.genelgrup.col-12.col-md-6")

                var catalogDetails = arrayListOf<Catalog>()
                for (headline : Element in document) {
                    catalogDetails.add(
                        Catalog(
                            "BIM",
                            headline.select("a.subTabArea.triangle").select("span.text").text(),
                            "https://www.bim.com.tr"+
                                    headline.select("div.imageArea.col-9.col-sm-10.col-md-8")
                                        .select("div.row")
                                        .select("div.bigArea.col-8.col-md-9")
                                        .select("a.fancyboxImage").attr("href")
                        )
                    )
                }

                emitter.onNext(catalogDetails)
            }catch (e:IOException){
                emitter.onError(e)
            }
        }
    }
    /*
    fun getA101Catalogs():Observable<List<Catalog>>{
        return Observable.create { emitter ->
            var document : Elements = IApi("https://www.a101.com.tr/afisler-haftanin-yildizlari")
                .select("div.brochure-tabs").select("div.contents").select("div.content")
                .select("img")

            var title = IApi("https://www.a101.com.tr/afisler-haftanin-yildizlari").select("div.nav")
                .select("span").first().text()

            var catalogDetails = arrayListOf<Catalog>()

            for (headline : Element in document) {
                catalogDetails.add(
                    Catalog(
                        "A101",
                        title,
                        headline.attr("src")
                    )
                )
            }
            emitter.onNext(catalogDetails)
        }
    }
     */
    fun getHAKMARCatalogs():Observable<List<Catalog>>{
        return Observable.create { emitter ->

            var catalogDetails  = arrayListOf<Catalog>()
            var titleList        = arrayListOf<String>()

            var titleUrls = IApi("https://www.hakmarexpress.com.tr/sayfa/haftalik-aktuel-urunler")
                .select("div.col-12").select("div[style*=font-weight:bold]")

            for((index,i) in titleUrls.withIndex()){
                titleList.add(i.text())
            }

            for(i in 1..6){
                try{
                    var document:Elements = IApi("https://www.hakmarexpress.com.tr/sayfa/insert-${i}")
                        .select("div.slider-image")
                    println("şklasd "+"https://www.hakmarexpress.com.tr/sayfa/insert-${i}")

                    for(headline in document){
                        catalogDetails.add(
                            Catalog("HAKMAR",
                                titleList[i-1],
                                headline.select("img").attr("src").replace("//","http://")
                            )
                        )
                    }
                }catch (e:IOException){
                    continue
                }
            }
            emitter.onNext(catalogDetails)
        }
    }
}