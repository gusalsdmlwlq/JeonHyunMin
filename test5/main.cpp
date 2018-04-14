#include <opencv2/highgui/highgui.hpp>
#include <iostream>
#include <opencv2/opencv.hpp>
#include "opencv/cv.h"
#include "opencv/highgui.h"
#include <stdio.h>
#include <stdlib.h>
#include "opencv2/imgproc/imgproc.hpp"
#include <opencv/cvaux.h>
#include "opencv2/features2d/features2d.hpp"
#include "opencv2/nonfree/features2d.hpp"

using namespace cv;
using namespace std;

int main()
{
    Mat img = imread("E:/multi/hw2/test.jpg",CV_LOAD_IMAGE_UNCHANGED);
    Mat gray_img;
    cvtColor(img,gray_img,CV_BGR2GRAY);
    normalize(gray_img,gray_img,0,255,NORM_MINMAX);
    Mat src, descriptors, dst;
    vector<KeyPoint> keypoints;
    SIFT sift(2000,3,0.004);
    sift(gray_img,gray_img,keypoints,descriptors,false);
    drawKeypoints(img,keypoints,dst);
    imshow("sift",dst);
    SurfFeatureDetector surf(400);
    surf.detect(img,keypoints);
    drawKeypoints(img,keypoints,dst);
    imshow("surf",dst);
    int threshold = 50;
    bool nonmax = true;
    FAST(gray_img,keypoints,threshold,nonmax);
    drawKeypoints(img,keypoints,dst);
    imshow("fast",dst);
    waitKey(0);
    destroyAllWindows();
    return 0;
}
