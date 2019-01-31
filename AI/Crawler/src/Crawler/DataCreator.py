import BlockMaker
import pandas as pd


class DataCreator:
    def __init__(self):
        self.blockmaker = BlockMaker.BlockMaker()

    def getblocklist(self, url):
        self.blockmaker.seturl(url)
        return self.blockmaker.makeblock()

    def makecsv(self, urls):
        newcsv = pd.DataFrame()
        for url in urls:
            self.__BlockList = self.getblocklist(url)
            for block in self.__BlockList:
                newblock = pd.DataFrame([[block.content, block.x, block.y, block.w, block.h, block.fontsize]])
                newcsv = pd.concat([newcsv, newblock])
            newcsv = pd.concat([newcsv, pd.DataFrame([[0, 0, 0, 0, 0]])])
        newcsv.to_csv("unlabeled_shop.csv", index=False, header=False, encoding="utf-8")

creator = DataCreator()
'''
creator.makecsv(["https://news.naver.com/main/hotissue/read.nhn?mid=hot&sid1=102&cid=1080997&iid=2987262&oid=005&aid=0001167116&ptype=052",
                 "https://news.naver.com/main/read.nhn?mode=LSD&mid=shm&sid1=103&oid=025&aid=0002880476",
                 "https://news.naver.com/main/read.nhn?mode=LSD&mid=shm&sid1=105&oid=011&aid=0003493068",
                 "https://news.naver.com/main/read.nhn?mode=LSD&mid=shm&sid1=105&oid=016&aid=0001491422",
                 "https://news.naver.com/main/read.nhn?mode=LSD&mid=shm&sid1=105&oid=018&aid=0004298590",
                 "https://entertain.v.daum.net/v/20190124143606348?rcmd=re",
                 "https://entertain.v.daum.net/v/20190124141332219?rcmd=re",
                 "https://entertain.v.daum.net/v/20190124092917077",
                 "https://entertain.v.daum.net/v/20190124124606430",
                 "https://news.v.daum.net/v/20190124132453598",
                 "https://news.nate.com/view/20190129n10213",
                 "https://news.nate.com/view/20190129n03109",
                 "https://news.nate.com/view/20190129n05985",
                 "https://news.nate.com/view/20190129n02447",
                 "https://news.sbs.co.kr/news/endPage.do?news_id=N1005117997&plink=STAND&cooper=NAVER",
                 "https://news.sbs.co.kr/news/endPage.do?news_id=N1005118081&plink=STAND&cooper=NAVER",
                 "http://sbsfune.sbs.co.kr/news/news_content.jsp?article_id=E10009357290&plink=STAND&cooper=NAVER",
                 "http://sbsfune.sbs.co.kr/news/news_content.jsp?article_id=E10009373162",
                 "http://sbsfune.sbs.co.kr/news/news_content.jsp?article_id=E10009373156",
                 "http://sbsfune.sbs.co.kr/news/news_content.jsp?article_id=E10009372730",
                 "http://news.jtbc.joins.com/html/502/NB11763502.html",
                 "http://news.jtbc.joins.com/html/918/NB11761918.html?log=jtbc|news|outsider",
                 "http://news.jtbc.joins.com/html/497/NB11763497.html?log=jtbc|news|outsider",
                 "http://news.jtbc.joins.com/html/893/NB11762893.html",
                 "http://news.kbs.co.kr/news/view.do?ncd=4126462",
                 "http://news.kbs.co.kr/news/view.do?ncd=4126457",
                 "https://news.naver.com/main/ranking/read.nhn?mid=etc&sid1=111&rankingType=popular_day&oid=081&aid=0002973793&date=20190130&type=1&rankingSeq=1&rankingSectionId=100",
                 "https://news.naver.com/main/ranking/read.nhn?mid=etc&sid1=111&rankingType=popular_day&oid=025&aid=0002881768&date=20190130&type=1&rankingSeq=2&rankingSectionId=100",
                 "https://news.naver.com/main/ranking/read.nhn?mid=etc&sid1=111&rankingType=popular_day&oid=020&aid=0003196115&date=20190130&type=1&rankingSeq=2&rankingSectionId=103",
                 "https://news.naver.com/main/ranking/read.nhn?mid=etc&sid1=111&rankingType=popular_day&oid=277&aid=0004403906&date=20190130&type=1&rankingSeq=2&rankingSectionId=105"])
                 
creator.makecsv(["https://blog.naver.com/kmjlove1983/221453447130",
                 "https://blog.naver.com/rim7033/221453444004",
                 "https://blog.naver.com/canchoyang/221453374417",
                 "https://blog.naver.com/usk4660/221453440830",
                 "https://blog.naver.com/biby2004/221453440433",
                 "https://blog.naver.com/nickykim156423/221453437556",
                 "https://blog.naver.com/rnldya12?Redirect=Log&logNo=221361755582",
                 "https://blog.naver.com/ziokorea/221422622487",
                 "https://blog.naver.com/228112lee?Redirect=Log&logNo=221448359851",
                 "https://blog.naver.com/trpd2233?Redirect=Log&logNo=221306745082",
                 "https://lastzone.com/1316",
                 "https://dosimulator.tistory.com/578",
                 "https://free2world.tistory.com/1951",
                 "https://damduck01.com/895",
                 "https://arisurang.tistory.com/298",
                 "https://hobbylibrary.tistory.com/327",
                 "https://dukyong15.tistory.com/2050",
                 "https://ggumtree.tistory.com/210",
                 "https://xuronghao.tistory.com/1504",
                 "https://ramideunioni.tistory.com/105",
                 "https://blog.naver.com/miniworldgo/221454277016",
                 "https://blog.naver.com/skyblue20726/221454208373",
                 "https://blog.naver.com/nas6249/221454295519",
                 "https://blog.naver.com/bluejive1004/221454212607",
                 "https://blog.naver.com/kanggi21/221454194468",
                 "https://sset20.tistory.com/157",
                 "https://tifa-lockhart.tistory.com/359",
                 "https://prolite.tistory.com/1404",
                 "https://itqnara.tistory.com/64",
                 "https://viewingcat.tistory.com/1655"])
'''
creator.makecsv(["http://shopping.interpark.com/product/productInfo.do?prdNo=5065709084&dispNo=001310&smid1=ad_recom",
                 "http://shopping.interpark.com/product/productInfo.do?prdNo=2494043157&smid1=ssendeal&smid2=pd&smid3=0",
                 "http://shopping.interpark.com/product/productInfo.do?prdNo=6248813497&smid1=ssendeal&smid2=pd&smid3=2",
                 "http://shopping.interpark.com/product/productInfo.do?prdNo=5979205291",
                 "http://shopping.interpark.com/product/productInfo.do?prdNo=6245957454",
                 "http://shopping.interpark.com/product/productInfo.do?prdNo=6169921543",
                 "http://shopping.interpark.com/product/productInfo.do?prdNo=6080045139&smid1=popular&smid2=digitalelectronics&smid3=prd",
                 "http://shopping.interpark.com/product/productInfo.do?prdNo=6210949058&dispNo=001110101",
                 "http://shopping.interpark.com/product/productInfo.do?prdNo=6257040582&dispNo=001110217&smid1=md_recom",
                 "http://shopping.interpark.com/product/productInfo.do?prdNo=5142295190&dispNo=001800",
                 "http://item.gmarket.co.kr/Item?goodscode=1219040495",
                 "http://item.gmarket.co.kr/Item?goodscode=1267464808",
                 "http://item.gmarket.co.kr/Item?goodscode=1545214141",
                 "http://item.gmarket.co.kr/Item?goodsCode=1486659418",
                 "http://item.gmarket.co.kr/detailview/item.asp?goodscode=1533758559",
                 "http://item.gmarket.co.kr/Item?goodsCode=1549888498",
                 "http://item.gmarket.co.kr/Item?goodscode=1553352450",
                 "http://item.gmarket.co.kr/Item?goodscode=1235925209",
                 "http://item.gmarket.co.kr/Item?goodscode=979399873",
                 "http://item.gmarket.co.kr/detailview/item.asp?goodscode=1548849916",
                 "http://www.yes24.com/24/Goods/68827654",
                 "http://www.yes24.com/24/Goods/57660812",
                 "http://www.yes24.com/24/Goods/8664361",
                 "http://www.yes24.com/24/Goods/13511254",
                 "http://www.yes24.com/24/Goods/7969504",
                 "https://front.wemakeprice.com/product/142281140",
                 "https://front.wemakeprice.com/deal/600104467",
                 "https://front.wemakeprice.com/product/115995461",
                 "http://www.wemakeprice.com/deal/adeal/4305578/105600/?source=105600&no=3",
                 "https://front.wemakeprice.com/product/101575236"])
