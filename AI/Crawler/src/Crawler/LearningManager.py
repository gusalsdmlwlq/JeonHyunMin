from sklearn.svm import SVC
from sklearn.model_selection import GridSearchCV
import pandas as pd


class LearningManager:
    def __init__(self):
        self.classifer = SVC(random_state=1)
        self.param_range = [0.001, 0.01, 0.1, 1.0, 10.0, 100.0, 1000.0]
        self.param_grid = [{"C": self.param_range, "gamma": self.param_range, "kernel": ["rbf"]},
                           {"C": self.param_range, "gamma": self.param_range, "kernel": ["poly"]}]
        self.gs = GridSearchCV(estimator=self.classifer, param_grid=self.param_grid, scoring="accuracy", cv=5, n_jobs=-1)
        self.x_train = []
        self.y_train = []
        self.weights = []

    def readdate(self, filedir):
        csv = pd.read_csv(filedir, header=None)
        for index, row in csv.iterrows():
            if not (row[0] == 0.0 and row[1] == 0.0 and row[2] == 0.0 and row[3] == 0.0):
                block = [row[0], row[1], row[2], row[3]]
                self.x_train.append(block)
                self.y_train.append(row[4])
                print(block, row[4])


    def fit(self):
        self.gs.fit(self.x_train, self.y_train)
        print("Best score : ", self.gs.best_score_)
        print("Best param : ", self.gs.best_params_)
        return self.gs.best_params_

learningmanager = LearningManager()
learningmanager.readdate("labeled.csv")
print(len(learningmanager.x_train))
learningmanager.fit()