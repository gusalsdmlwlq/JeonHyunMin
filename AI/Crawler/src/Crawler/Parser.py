import Node
from Naked.toolshed.shell import execute_js, muterun_js


class Parser:
    def __init__(self):
        self.NodeList = []

    def parsehtml(self):
        response = muterun_js("Parser.js")
        result = response.stdout.decode().split("[")[1:]
        for r in result:
            r.replace("]", "\n")
            node = r.split(",\n")
            for i in range(0, 2):   # type & content
                node[i] = node[i].split("'")[1].strip()
            for i in range(2, 7):   # x, y, w, h fontsize,
                node[i] = float(node[i])
            node[7] = node[7].split("'")[1]  # bg_color
            node[7] = [node[7].split(",")[0].split("(")[1], node[7].split(",")[1], node[7].split(",")[2], node[7].split(",")[3].split(")")[0]]
            for i in range(len(node[7])):
                node[7][i] = int(node[7][i])
            node[8] = float(node[8].split(']')[0].strip())  # indent
            self.makenode(node)

    def makenode(self, node):
        newnode = Node.Node(node)
        self.NodeList.append(newnode)


parser = Parser()
parser.parsehtml()
print(len(parser.NodeList))