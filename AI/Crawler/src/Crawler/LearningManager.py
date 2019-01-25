from sklearn.svm import SVC
from sklearn.model_selection import GridSearchCV
import pandas as pd
import time
from sklearn.externals import joblib
from sklearn.metrics import accuracy_score

class LearningManager:
    def __init__(self):
        self.classifer = joblib.load("C://users\jeon\jupyter\crawler\svm.pkl")
        self.param_range = [0.001, 0.01, 0.1, 1.0, 10.0, 100.0, 1000.0]
        self.param_grid = [{"C": self.param_range, "gamma": self.param_range, "kernel": ["rbf"]}]
        self.gs = GridSearchCV(estimator=self.classifer, param_grid=self.param_grid, scoring="accuracy", cv=5)
        self.x_train = []
        self.y_train = []
        self.weights = []

    def readdate(self, filedir):
        csv = pd.read_csv(filedir, header=None, encoding="utf-8")
        for index, row in csv.iterrows():
            if not (row[1] == 0.0 and row[2] == 0.0 and row[3] == 0.0 and row[4] == 0.0):
                block = [row[1], row[2], row[3]]
                self.x_train.append(block)
                self.y_train.append(row[5])
                print(block, row[5])

    def fit(self):
        self.gs.fit(self.x_train, self.y_train)
        print("Best score : ", self.gs.best_score_)
        print("Best param : ", self.gs.best_params_)
        return self.gs.best_params_

start = time.time()
learningmanager = LearningManager()
learningmanager.readdate("C://users\jeon\jupyter\crawler\labeled.csv")
print(len(learningmanager.x_train))
#learningmanager.fit()
pred = learningmanager.classifer.predict(learningmanager.x_train[400:])
end = time.time()
print(end-start)
print(accuracy_score(pred,learningmanager.y_train[400:]))