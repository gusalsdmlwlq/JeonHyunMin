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
                newblock = pd.DataFrame([[block.content, block.x, block.y, block.w, block.h]])
                newcsv = pd.concat([newcsv, newblock])
            newcsv = pd.concat([newcsv, pd.DataFrame([[0, 0, 0, 0, 0]])])
        newcsv.to_csv("unlabeled.csv", index=False, header=False)

creator = DataCreator()
creator.makecsv(["https://news.naver.com/main/hotissue/read.nhn?mid=hot&sid1=102&cid=1080997&iid=2987262&oid=005&aid=0001167116&ptype=052",
                 "https://news.naver.com/main/read.nhn?mode=LSD&mid=shm&sid1=103&oid=025&aid=0002880476",
                 "https://news.naver.com/main/read.nhn?mode=LSD&mid=shm&sid1=105&oid=011&aid=0003493068",
                 "https://news.naver.com/main/read.nhn?mode=LSD&mid=shm&sid1=105&oid=016&aid=0001491422",
                 "https://news.naver.com/main/read.nhn?mode=LSD&mid=shm&sid1=105&oid=018&aid=0004298590",
                 "https://entertain.v.daum.net/v/20190124143606348?rcmd=re",
                 "https://entertain.v.daum.net/v/20190124141332219?rcmd=re",
                 "https://entertain.v.daum.net/v/20190124092917077",
                 "https://entertain.v.daum.net/v/20190124124606430",
                 "https://news.v.daum.net/v/20190124132453598"])