import random
import blocks
import sys
import time

class Game:

    WIDTH = 20
    HEIGHT = 20
    speed = 1
    def __init__(self):
        self._gameMap = self.__initGameMap()
        self._currentBlock = self.__makeRandomBlock()
        self._position = [9, 0]
        self._timeCounter = 0

    def __initGameMap(self):
        gameMap = []
        for y in range(20):
            gameMap.append([])
            for x in range(20):
                gameMap[y].append(0)
        return gameMap

    def __makeRandomBlock(self):
        time.sleep(0.5)
        rand = random.randint(1, 7)
        if rand == 1:
            return blocks.Block1()
        elif rand == 2:
            return blocks.Block2()
        elif rand == 3:
            return blocks.Block3()
        elif rand == 4:
            return blocks.Block4()
        elif rand == 5:
            return blocks.Block5()
        elif rand == 6:
            return blocks.Block6()
        elif rand == 7:
            return blocks.Block7()

    def __addBlockToGameMap(self):
        """Takes the current block (which the player moves) and adds it to the
        game map, making it a part of the "environment".
        """
        for y in range(self._currentBlock.getHeight()):
            for x in range(self._currentBlock.getWidth()):
                value = self._currentBlock.getRotatedShape()[y][x]
                if value is 0:  # Avoid overwriting nonzero numbers in gameMap
                    continue
                y2 = self._position[1] + y
                x2 = self._position[0] + x
                self._gameMap[y2][x2] = value

    def __doesBlockCollide(self): #블럭에 닿은 경우
        for y in range(self._currentBlock.getHeight()):
            for x in range(self._currentBlock.getWidth()):
                shapeValue = self._currentBlock.getRotatedShape()[y][x]
                y2 = self._position[1] + y
                x2 = self._position[0] + x
                if(x2<=-1 or x2>=20 or y2>=20):
                    mapValue = 0
                else :
                    mapValue = self._gameMap[y2][x2]
                if shapeValue and mapValue:
                    return True
        return False
    def __doesBlockCollidewithwall(self): #벽에 닿은 경우
        for y in range(self._currentBlock.getHeight()):
            for x in range(self._currentBlock.getWidth()):
                shapeValue = self._currentBlock.getRotatedShape()[y][x]
                y2 = self._position[1] + y
                x2 = self._position[0] + x
                if shapeValue and (x2<=-1 or x2>=20 or y2>=20):
                    return True
        return False
    def lineclear(self,line):
        for x in range(0,20):
            self._gameMap[line][x] = 0
        for y in range(0,line):
            for z in range(0,20):
                self._gameMap[line-y][z] = self._gameMap[line-y-1][z]
    def linesclear(self):
        for y in range(0,20):
            x = 0
            while x<20:
                if self._gameMap[y][x] == 0:
                    break
                x += 1
            if x == 20:
                self.lineclear(y)
    def update(self, time):
        self._timeCounter += time
        if self._timeCounter < 500/self.speed:  # Only move down block every 1 second
            return
        self._timeCounter -= 500/self.speed

        self._position[1] += 1  # Move down block

        # If the block has reach the bottom or collides with a solid tile
        if self.__doesBlockCollidewithwall() or self.__doesBlockCollide(): #블럭이나 벽에 닿으면 멈추고 새로운 블럭을 만듬
            while self.__doesBlockCollidewithwall() or self.__doesBlockCollide(): #블럭끼리 겹치면 안 겹칠 때 까지 위로 밀어냄
                self._position[1] -= 1
                if self._position[1] <= -1:
                    sys.exit()
            self.__addBlockToGameMap()
            self._position = [10, 0]
            self._currentBlock = self.__makeRandomBlock()
        self.linesclear()

    def rotate(self):
        self._currentBlock.rotateCounterclockwise()
        xmove = 0
        ymove = 0
        for y in range(self._currentBlock.getHeight()): #회전했을 때 벽에 닿으면 밀어냄
            for x in range(self._currentBlock.getWidth()):
                shapeValue = self._currentBlock.getRotatedShape()[y][x]
                y2 = self._position[1] + y
                x2 = self._position[0] + x
                if shapeValue and x2<=-1:
                    xmove += 1
                if shapeValue and x2>=20:
                    xmove -= 1
                if shapeValue and y2>=20:
                    ymove -= 1
        self._position[0] += xmove
        self._position[1] += ymove
    def moveLeft(self):
        if True:#self._position[0]:
            self._position[0] -= 1
            if self.__doesBlockCollidewithwall() or self.__doesBlockCollide():
                self._position[0] += 1

    def moveRight(self):
        if True:#self._position[0] + self._currentBlock.getWidth() < Game.WIDTH:
            self._position[0] += 1
            if self.__doesBlockCollidewithwall() or self.__doesBlockCollide():
                self._position[0] -= 1

    def getPosition(self):
        return self._position

    def getGameMap(self):
        return self._gameMap

    def getBlock(self):
        return self._currentBlock
