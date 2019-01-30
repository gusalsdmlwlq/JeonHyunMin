from sklearn.svm import SVC
from sklearn.model_selection import GridSearchCV
import pandas as pd
import time
from sklearn.externals import joblib
from sklearn.model_selection import cross_val_score
import numpy as np
from sklearn.exceptions import ConvergenceWarning
from sklearn.preprocessing import StandardScaler
from sklearn.metrics import precision_score
import warnings
warnings.filterwarnings('ignore', category=ConvergenceWarning, module='sklearn')

class LearningManager:
    def __init__(self, mode):
        if mode == 1:  # shopping mall
            self.mode = 1
            try:
                self.classifer = joblib.load("model\svm_shop.pkl")
            except:
                self.classifer = SVC(random_state=1)
        elif mode == 2:    # others
            self.mode = 2
            try:
                self.classifer = joblib.load("model\svm_others.pkl")
            except:
                self.classifer = SVC(random_state=1)
        self.param_range = [0.01, 0.1, 1.0, 10.0, 100.0]
        self.iters = [500, 1000, 1500, 2000]
        self.weight_grid = [{0: 0.1, 1: 0.9}, {0: 0.2, 1: 0.8}, {0: 0.3, 1: 0.7}]
        self.param_grid = [{"C": self.param_range, "gamma": self.param_range, "kernel": ["rbf"], "max_iter": self.iters,
                            "class_weight": self.weight_grid}]
        self.gs = GridSearchCV(estimator=self.classifer, param_grid=self.param_grid, scoring="precision_macro", cv=10,
                               n_jobs=-1)
        self.x_train = []
        self.y_train = []
        self.sc = StandardScaler()

    def readdate(self, filedir):
        csv = pd.read_csv(filedir, header=None, encoding="utf-8")
        for index, row in csv.iterrows():
            if not (row[1] == 0.0 and row[2] == 0.0 and row[3] == 0.0 and row[4] == 0.0):
                block = [row[1], row[2], row[3], row[4]]
                self.x_train.append(block)
                self.y_train.append(row[6])
        self.sc.fit(self.x_train)
        self.x_train_std = self.sc.transform(self.x_train)

    def fit(self):
        self.classifer.fit(self.x_train_std, self.y_train)
        if self.mode == 1:
            joblib.dump(self.classifer, "model\svm_shop.pkl")
        elif self.mode == 2:
            joblib.dump(self.classifer, "model\svm_others.pkl")

    def resetmodel(self):
        self.classifer = SVC(random_state=1)
        self.gs = GridSearchCV(estimator=self.classifer, param_grid=self.param_grid, scoring="precision_macro", cv=10,
                               n_jobs=-1)

    def setmodel(self, params):
        self.classifer = SVC(random_state=1, C=params["C"], gamma=params["gamma"], kernel=params["kernel"],
                             max_iter=params["max_iter"], class_weight=params["class_weight"])

    def hyparam(self):
        self.gs.fit(self.x_train_std, self.y_train)
        print("Best score : ", self.gs.best_score_)
        print("Best param : ", self.gs.best_params_)
        params = self.gs.best_params_
        return params

    def eval(self):
        scores = cross_val_score(estimator=self.classifer, X=self.x_train_std, y=self.y_train, cv=10, n_jobs=-1,
                                 scoring="precision_macro")
        print('CV accuracy scores: %s' % scores)
        print('CV accuracy: {0:.3f} +/- {1:.3f}'.format(np.mean(scores), np.std(scores)))

start = time.time()
learningmanager = LearningManager(2)
#learningmanager.resetmodel()
print(learningmanager.classifer)
learningmanager.readdate("dataset\labeled_news.csv")
#params = learningmanager.hyparam()
#learningmanager.setmodel(params)
learningmanager.eval()
#learningmanager.fit()
end = time.time()
print(end-start, "seconds")
