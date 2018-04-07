#include <opencv2/highgui/highgui.hpp>
#include <iostream>
#include <opencv2/opencv.hpp>
#include "opencv/cv.h"
#include "opencv/highgui.h"

using namespace cv;
using namespace std;

int main()
{
    //1
    /*Mat img = imread("E:/multi/hw1/Baboon.BMP",CV_LOAD_IMAGE_COLOR);
    namedWindow("Image", CV_WINDOW_AUTOSIZE);
    imshow("Image", img);
    imwrite("E:/multi/hw1/Newimage.BMP",img);
    waitKey(0);
    destroyAllWindows;*/

    //Inverse
    /*Mat img = imread("E:/multi/hw1/lya-lc.bmp",CV_LOAD_IMAGE_COLOR);
    //Mat img = imread("E:/multi/hw1/FRUIT.BMP",CV_LOAD_IMAGE_GRAYSCALE);
    namedWindow("Original",CV_WINDOW_AUTOSIZE);
    imshow("Original",img);
    img = ~img;
    namedWindow("Inverse",CV_WINDOW_AUTOSIZE);
    imshow("Inverse",img);
    waitKey(0);
    destroyAllWindows;*/

    //Stretching
    /*//Mat img = imread("E:/multi/hw1/lya-lc.bmp",CV_LOAD_IMAGE_GRAYSCALE);
    //Mat img = imread("E:/multi/hw1/lya-lc-bright.bmp",CV_LOAD_IMAGE_COLOR);
    //Mat img = imread("E:/multi/hw1/lya-lc-dark.bmp",CV_LOAD_IMAGE_GRAYSCALE);
    Mat img = imread("E:/multi/hw1/lya-lc-no.bmp",CV_LOAD_IMAGE_GRAYSCALE);
    Mat stretching(img.rows,img.cols,CV_8UC1);
    int x,y;
    int min = 256, max = -1;
    int MN = (img.rows * img.cols);
    float slope = 0;
    int hist[256] = {0}, sum_1 = 0, sum_2 = 0, hist2[256] = {0};
    for(y=0; y<img.rows; y++)
    {
        for(x=0; x<img.cols; x++)
        {
            hist[(int)img.at<uchar>(y,x)]++;
        }
    }
    for(int i=0; i<256; i++)
    {
        sum_1 += hist[i];
        if(sum_1>MN*0.05)
        {
            min = i;
            break;
        }
    }
    for(int i=255; i>=0; i--)
    {
        sum_2 += hist[i];
        if(sum_2>MN*0.05)
        {
            max = i;
            break;
        }
    }
    slope = 255/(max-min);
    for(y=0; y<img.rows; y++)
    {
        for(x=0; x<img.cols; x++)
        {
            int k;
            k = (int)(img.at<uchar>(y,x));
            if(k<min) stretching.at<uchar>(y,x) = 0;
            else if(k>max) stretching.at<uchar>(y,x) = 255;
            else stretching.at<uchar>(y,x) = (k-min)*slope;
        }
    }
    for(y=0; y<img.rows; y++)
    {
        for(x=0; x<img.cols; x++)
        {
            hist2[(int)stretching.at<uchar>(y,x)]++;
        }
    }
    namedWindow("Original",CV_WINDOW_AUTOSIZE);
    imshow("Original",img);
    namedWindow("Stretching",1);
    imshow("Stretching",stretching);
    waitKey(0);
    destroyAllWindows;
    return 0;*/

    //Equalization
    /*Mat img = imread("E:/multi/hw1/lya-lc.bmp", CV_LOAD_IMAGE_GRAYSCALE);
    //Mat img = imread("E:/multi/hw1/lya-lc-no.bmp",CV_LOAD_IMAGE_GRAYSCALE);
    //Mat img = imread("E:/multi/hw1/eq-no.png", CV_LOAD_IMAGE_GRAYSCALE);
    Mat equalization(img.rows, img.cols, 0);
    namedWindow("Original", CV_WINDOW_AUTOSIZE);
    imshow("Original", img);
    int x, y, i, k;
    int acc_hist = 0;
    float N = (img.rows * img.cols) / 256;
    int hist[256] ={0}, sum[256] = {0};
    for (y = 0 ; y <img.rows ; y++){
        for (x = 0 ; x < img.rows ; x++){
            hist[(int)img.at<uchar>(y, x)]++;
        }
    }

    for (i = 0 ; i < 256 ; i++){
        acc_hist = acc_hist + hist[i];
        sum[i] = ((int)acc_hist / N);
    }

    for (y =  0 ; y < img.rows ; y++){
        for (x = 0 ; x < img.cols ; x++){
            equalization.at<uchar>(y, x) = sum[img.at<uchar>(y, x)];
        }
    }
    namedWindow("Equalization", 1);
    imshow("Equalization", equalization);
    waitKey(0);
    destroyAllWindows;
    return 0;*/

    //Thresholding
    /*Mat img = imread("E:/multi/hw1/fingerprint-gray.bmp", CV_LOAD_IMAGE_GRAYSCALE);
    Mat thresholding(img.rows,img.cols,CV_8UC1);
    int x,y;
    //int threshold_value =120;
    //int threshold_value =150;
    //int threshold_value =180;
    //int threshold_value =200;
    int threshold_value =240;
    for(y=0;y<img.rows;y++){
        for(x=0;x<img.cols;x++){
            int k =(int)(img.at<uchar>(y,x));
            if(k<= threshold_value)
                thresholding.at<uchar>(y,x)=0;
            else
                thresholding.at<uchar>(y,x)=255;
        }
    }
    namedWindow("Origin Image",CV_WINDOW_AUTOSIZE);
    imshow("Origin Image",img);
    namedWindow("thresholded image",1);
    imshow("thresholded image",thresholding);
    waitKey(0);
    destroyAllWindows;*/

    //Add/Subtract
    Mat img1 = imread("E:/multi/hw1/FRUIT.BMP",CV_LOAD_IMAGE_COLOR);
    Mat img2 = imread("E:/multi/hw1/FRUIT.BMP",CV_LOAD_IMAGE_COLOR);
    //Mat img2 = imread("E:/multi/hw1/colorbar.bmp",CV_LOAD_IMAGE_COLOR);
    Mat plus(img1.rows,img1.cols,CV_8UC1);
    Mat minus(img1.rows,img1.cols,CV_8UC1);
    //float alpha = 0.8;
    //float alpha = 0.5;
    float alpha = 0.2;
    float beta = 1-alpha;
    plus = img1 + img2;
    minus = img1 - img2;
    namedWindow("Image1",1);
    imshow("Image1",img1);
    namedWindow("Image2",1);
    imshow("Image2",img2);
    namedWindow("plus",1);
    imshow("plus",plus);
    namedWindow("minus",1);
    imshow("minus",minus);
    waitKey(0);
    destroyAllWindows();
}
