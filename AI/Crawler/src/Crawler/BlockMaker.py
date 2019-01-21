import Parser
import Block
import Node

class BlockMaker:
    def __init__(self):
        self.parser = Parser.Parser()
        self.__url = ""
        self.__BlockList = []
        self.x_th = 80
        self.y_th = 40
        self.nodeset = []

    def makeblock(self):
        self.parser.parsehtml()
        self.__NodeList = self.parser.getnodelist()
        for node in self.__NodeList:
            if len(self.nodeset) == 0:   # 노드 리스트가 비어 있는 경우
                if node.type == "img":  # 처음 노드가 img
                    self.bindnodes(node)
                    self.nodeset.clear()
                else:   # 처음 노드가 text
                    self.nodeset.append(node)
            else:   # 노드 리스트가 이미 차 있는 경우
                if node.type == "img":  # 새로운 노드가 img
                    self.bindnodes(self.nodeset)
                    self.nodeset.clear()
                    self.bindnodes(node)
                else:   # 새로운 노드가 text
                    lastnode = self.nodeset[len(self.nodeset) - 1]
                    if lastnode.bg_color != node.bg_color:  #bg_color가 다른 경우
                        self.bindnodes(self.nodeset)
                        self.nodeset.clear()
                        self.nodeset.append(node)
                    elif lastnode.fontsize != node.fontsize:    #fontsize가 다른 경우
                        self.bindnodes(self.nodeset)
                        self.nodeset.clear()
                        self.nodeset.append(node)
                    else:   # 좌표로 체크
                        if lastnode.y == node.y:
                            if (node.x - node.w/2) - (lastnode.x + lastnode.w/2) < self.x_th:
                                self.nodeset.append(node)
                            else:
                                self.bindnodes(self.nodeset)
                                self.nodeset.clear()
                                self.nodeset.append(node)
                        else:
                            if (node.y - node.h/2) - (lastnode.y + lastnode.h/2) < self.y_th:
                                self.nodeset.append(node)
                            else:
                                self.bindnodes(self.nodeset)
                                self.nodeset.clear()
                                self.nodeset.append(node)

    def bindnodes(self, nodelist):
        if type(nodelist) == Node.Node:  # 노드가 하나
            node = nodelist
            block = Block.Block(node.type, node.content, node.x, node.y, node.w, node.h)
        elif len(nodelist) == 1:
            node = nodelist[0]
            block = Block.Block(node.type, node.content, node.x, node.y, node.w, node.h)
        else:   # 노드가 여러개
            x_sum = 0
            x_mean = 0
            y_sum = 0
            y_mean = 0
            w = 0
            h = 0
            content = ""
            for node in nodelist:
                x_sum += node.x
                y_sum += node.y
                content = content + node.content + " "
            x_mean = x_sum/len(nodelist)
            y_mean = y_sum / len(nodelist)
            first = nodelist[0]
            last = nodelist[len(nodelist) - 1]
            if nodelist[0].y == nodelist[1].y:  # y좌표가 같은 노드들
                h = nodelist[0].h
                w = (last.x + last.w/2) - (first.x - first.w/2)
            else:   # 아닌 경우
                w = 0
                for node in nodelist:
                    if node.w > w:
                        w = node.w
                h = (last.y + last.h/2) - (first.y - first.h/2)
            block = Block.Block("text", content, x_mean, y_mean, w, h)
        self.__BlockList.append(block)
        print(block.type, block.content, block.x, block.y, block.w, block.h)

    def seturl(self, url):
        self.__url = url
        self.parser.seturl(self.__url)

    def getnodelist(self):
        return self.__NodeList


blockmaker = BlockMaker()
blockmaker.seturl("https://news.v.daum.net/v/20190121121304923")
blockmaker.makeblock()
