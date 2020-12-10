package com.onurkanbakirci.aktuelBak.data.repository

import com.onurkanbakirci.aktuelBak.data.model.Product
import com.onurkanbakirci.aktuelBak.data.network.IApi
import io.reactivex.Observable
import org.jsoup.nodes.Element
import org.jsoup.select.Elements

class ProductRepository {


    fun getProducts(): Observable<List<Product>> {
        return Observable.create{emitter ->
            var document : Elements ? =null
            try {
                document= IApi("https://www.bim.com.tr/default.aspx")
                    .select("div.product.col-xl-3.col-lg-3.col-md-4.col-sm-6.col-12")
            }catch (e:Exception){
                emitter.onError(e)
            }

            var element : Elements? = document?.select("div.product.col-xl-3.col-lg-3.col-md-4.col-sm-6.col-12")

            var productDetails = arrayListOf<Product>()

            for ((index,headline : Element) in element.orEmpty().withIndex()) {
                if(index>6){
                    productDetails.add(
                        Product(
                            "BIM",
                            headline.select("h2.title").text(),
                            headline.select("h2.subtitle").text(),
                            headline.select("div.buttonArea.col-12.col-lg-6.order-lg-1.order-2").text(),
                            "https://www.bim.com.tr/" + headline.select("div.col-6.col-sm-12.col-lg-12.imageArea").select("div.image").select("img").attr("xsrc")
                        )
                    )

                }else{
                    productDetails.add(
                        Product(
                            "BIM",
                            headline.select("h2.title").text(),
                            headline.select("h2.subtitle").text(),
                            headline.select("div.buttonArea.col-12.col-lg-6.order-lg-1.order-2").text(),
                            "https://www.bim.com.tr/" + headline.select("div.col-6.col-sm-12.col-lg-12.imageArea").select("div.image").select("img.img-fluid").attr("src")
                        )
                    )

            }
            }
            emitter.onNext(productDetails)
        }
    }
    fun getHAKMARProducts():Observable<List<Product>>{
        return Observable.create{
            emitter->
            var document : Elements = IApi("https://www.hakmarexpress.com.tr/kategori/haftanin-urunleri")
                .select("div.showcase-container")
                .select("div.col-6.col-lg-4")

            var productDetails = arrayListOf<Product>()
            for (headline : Element in document) {
                productDetails.add(
                    Product(
                        "HAKMAR",
                        headline.select("div.showcase-title").text(),
                        null,
                        headline.select("div.showcase-price-new").text().replace(" TL","₺"),
                        "http://"+headline.select("div.showcase-image").select("img").attr("data-src")
                    )
                )

            }
            emitter.onNext(productDetails)
        }
    }
    /*
    fun getA101Products():Observable<List<Product>>{
        return Observable.create{
            emitter->
            var document : Elements = IApi("https://www.a101.com.tr/online-alisveris")
                .select("div.col-md-3.col-sm-6.col-xs-6.set-product-item")
            var productDetails = arrayListOf<Product>()

            //var elements = document.select("div.ins-image-box")
            for (headline : Element in document.orEmpty()) {
                productDetails.add(Product(
                    "A101",
                    headline.select("div.product-desc").select("h3.name").text(),
                    //headline.select("h2.title").text(),
                    null,
                    headline.select("div.add-basket.js-add-basket-area").select("div.price").text().replace(" TL","₺"),
                    headline.select("div.product-actions").select("div.product-image").select("img").attr("data-src")
                ))

            }
            emitter.onNext(productDetails)
        }
    }
     */
    fun getProductByName(nameOfProduct:String):Observable<List<Product>>{
        return Observable.create {
            emitter ->

            var documentHakmar : Elements

            try {
                documentHakmar  = IApi("https://www.hakmarexpress.com.tr/arama/$nameOfProduct")
                .select("div.col-6.col-lg-4:gt(1)")

                var productDetails = arrayListOf<Product>()

                for (headline : Element in documentHakmar!!) {
                    productDetails.add(Product(
                        "HAKMAR",
                        headline.select("div.showcase-title").select("a").text(),
                        null,
                        headline.select("div.showcase-content").select("div.showcase-price-new").text(),
                        headline.select("div.showcase-image").select("img").attr("data-src").replace("//","http://")
                    ))
                }
/*
                var documentA101 : Elements = IApi("https://www.a101.com.tr/list/?search_text=$nameOfProduct")
                    .select("div.col-md-4.col-sm-6.col-xs-6.set-product-item")

                for (headline : Element in documentA101) {
                    productDetails.add(Product(
                        "A101",
                        headline.select("div.product-desc").select("h3.name").text(),
                        null,
                        headline.select("div.product-desc").select("div.prices").select("span.current").text(),
                        headline.select("div.product-actions").select("div.product-image").select("img").attr("data-src")
                    ))
                }

 */
                var documentKARACA : Elements = IApi("https://www.krc.com.tr/product/search?q=$nameOfProduct")
                    .select("div.item.col-xs-6.col-sm-3.gtm-product")

                for(headline : Element in documentKARACA){
                    productDetails.add(Product(
                        "KARACA",
                        headline.select("div.caption").select("h4").text(),
                        null,
                        headline.select("span.price").text().replace("TL","₺"),
                        headline.select("div.text-center").select("img").attr("data-src")
                    ))
                }

                var documentMADAMECOCO: Elements = IApi("https://www.madamecoco.com/list/?search_text=$nameOfProduct")
                    .select("div.col-sm-4.col-xs-6.list__content__item")

                for(headline : Element in documentMADAMECOCO){
                    if(headline.select("div.product-item__value").select("div.product-item__amount").text().startsWith("Ö")){
                        productDetails.add(Product(
                            "MADAMECOCO",
                            headline.select("div.product-item__title").select("a").first().text(),
                            null,
                            headline.select("div.product-item__value").select("div.product-item__amount").text().replace("TL","₺"),
                            headline.select("img").first().attr("src")
                        ))
                    }else{
                        productDetails.add(Product(
                            "MADAMECOCO",
                            headline.select("div.product-item__title").select("a").first().text(),
                            null,
                            headline.select("div.product-item__value").select("div.product-item__amount").text().substringAfter("TL").replace("TL","₺"),
                            headline.select("img").first().attr("src")
                        ))
                    }
                }

                var documentENGLISHHOME: Elements = IApi("https://www.englishhome.com/list/?search_text=$nameOfProduct")
                    .select("div.col-sm-3.col-xs-6.pl-reset.product-area")

                for(headline : Element in documentENGLISHHOME){
                    productDetails.add(Product(
                        "ENGLISHHOME",
                        headline.select("div.details").select("div.product-name").select("a").text(),
                        null,
                        headline.select("div.details").select("span.product-pure-price").text()+"₺",
                        headline.select("a.product-image-area").select("img.product-item-image").attr("src")
                    ))
                }

                emitter.onNext(productDetails)
            }catch (e:Exception){
                emitter.onError(e)
            }

        }
    }
    fun getKARACAProducts():Observable<List<Product>>{
        return Observable.create {
                emitter ->
            var document : Elements = IApi("https://www.krc.com.tr/indirim")
                .select("div.item.col-xs-6.col-sm-3.gtm-product")
            var productDetails = arrayListOf<Product>()

            for (headline : Element in document) {
                productDetails.add(Product(
                    "KARACA",
                    headline.select("div.caption").select("h4").text(),
                    null,
                    headline.select("span.price").text().replace(" TL","₺"),
                    headline.select("img").first().attr("data-src")
                ))
            }
            emitter.onNext(productDetails)
        }
    }

    fun getENGLISHHHOMEProducts():Observable<List<Product>>{
        return Observable.create {
                emitter ->
            var document : Elements = IApi("https://www.englishhome.com/firsat-urunleri/")
                .select("div.col-sm-3.col-xs-6.pl-reset.product-area")
            var productDetails = arrayListOf<Product>()

            for (headline : Element in document) {
                if(!headline.select("div.details").select("div.product-name").select("a").first().text().contains("Çorap")){
                    productDetails.add(Product(
                        "ENGLISHHOME",
                        headline.select("div.details").select("div.product-name").select("a").first().text(),
                        null,
                        headline.select("div.details").select("div.product-list-price-wrap").select("span").text()+"₺",
                        headline.select("div.product-image.product-double-image").select("a.product-image-area").select("img.product-item-image").attr("src")
                    ))
                }
            }
            emitter.onNext(productDetails)
        }
    }
    fun getMADAMECOCOProducts():Observable<List<Product>>{
        return Observable.create {
                emitter ->
            var document : Elements = IApi("https://www.madamecoco.com/yeni-urunler")
                .select("div.col-sm-4.col-xs-6.list__content__item")
            var productDetails = arrayListOf<Product>()

            for (headline : Element in document) {
                productDetails.add(
                    Product(
                        "MADAMECOCO",
                        headline.select("div.product-item__title").select("a").first().text(),
                        null,
                        headline.select("div.product-item__value").select("div.product-item__amount").text().substringAfter("TL").replace("TL","₺"),
                        headline.select("a.product-item__img").select("img.product-item-image").attr("src")
                    )
                )
            }
            emitter.onNext(productDetails)
        }
    }
}


