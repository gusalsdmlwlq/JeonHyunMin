import Node
from Naked.toolshed.shell import execute_js, muterun_js


class Parser:
    def __init__(self):
        self.__NodeList = []
        self.__url = ""

    def parsehtml(self):
        response = muterun_js("Parser.js", self.__url)
        result = response.stdout.decode()
        result = result.split("\n")
        node = []
        for i in range(len(result)):
            if(i % 9 == 0):
                node.clear()
            node.append(result[i])
            if(i % 9 == 8):
                self.makenode(node)

    def makenode(self, node):
        newnode = Node.Node(node)
        self.__NodeList.append(newnode)

    def getnodelist(self):
        return self.__NodeList

    def seturl(self, url):
        self.__url = url
