#include <opencv2/highgui/highgui.hpp>
#include <iostream>
#include <opencv2/opencv.hpp>
#include "opencv/cv.h"
#include "opencv/highgui.h"
#include <stdio.h>
#include <stdlib.h>
#include "opencv2/imgproc/imgproc.hpp"

using namespace cv;
using namespace std;

//int main()
//{
    //sobel
    /*Mat img = imread("E:/multi/Week5/lena.png", CV_LOAD_IMAGE_GRAYSCALE);
    Mat sobel = cvCreateMat(img.rows,img.cols,CV_8UC1);
    int mask_y[] = {-1,-2,-1,
                    0,0,0,
                    1,2,1};
    int mask_x[] = {-1,0,1,
                    -2,0,2,
                    -1,0,1};
    int cnt;
    float sum_x;
    float sum_y;
    for(int y=1; y<img.rows-1; y++)
    {
        for(int x=1; x<img.cols-1; x++)
        {
            cnt=0;
            sum_x=0;
            sum_y=0;
            for(int h=y-1; h<=y+1; h++)
            {
                for(int w=x-1; w<=x+1; w++)
                {
                    sum_x = sum_x + mask_x[cnt] * img.at<uchar>(h,w);
                    sum_y = sum_y + mask_y[cnt] * img.at<uchar>(h,w);
                    cnt++;
                }
            }
            sobel.at<uchar>(y,x) = sqrt((sum_x*sum_x) + (sum_y*sum_y));
        }
    }
    namedWindow("Origin",1);
    imshow("Origin",img);
    namedWindow("Result",1);
    imshow("Result",sobel);
    waitKey(0);
    destroyAllWindows;*/

    //sobel opencv
    /*Mat img = imread("E:/multi/Week5/lena.png", 0);
    imshow("origin image",img);
    Mat sobel_draw,sobel_X,sobel_Y;
    Sobel(img,sobel_X,CV_8U,1,0);
    Sobel(img,sobel_Y,CV_8U,0,1);
    sobel_draw = abs(sobel_X) + abs(sobel_Y);
    imshow("sobel",sobel_draw);
    waitKey(0);
    destroyAllWindows;*/

    //canny
    /*Mat img = imread("E:/multi/Week5/lena.png", 0);
    imshow("origin image",img);
    Mat canny_edge;
    Canny(img,canny_edge,70,200,3);
    imshow("canny",canny_edge);
    waitKey(0);
    destroyAllWindows;*/
//}

//Harris Corner
Mat src,src_gray;
int thresh = 200;
int max_thresh = 255;
char * source_window = "Source image";
char * corners_window = "Corners image";
void cornerHarris_demo(int,void *);
int main(int argc,char ** argv)
{
    src = imread("E:/multi/Week5/boy.jpg",1);
    cvtColor(src,src_gray,CV_BGR2GRAY);
    namedWindow(source_window,CV_WINDOW_NORMAL);
    int desiredWidth=200, desiredheight=300;
    resizeWindow(source_window,desiredWidth,desiredheight);
    createTrackbar("Threshold: ",source_window,&thresh,max_thresh,cornerHarris_demo);
    imshow(source_window,src);
    cornerHarris_demo(0,0);
    waitKey(0);
    return 0;
}
void cornerHarris_demo(int,void *)
{
    Mat dst,dst_norm,dst_norm_scaled;
    dst = Mat::zeros(src.size(),CV_32FC1);
    int blockSize = 2;
    int apertureSize = 3;
    double k = 0.04;
    cornerHarris(src_gray,dst,blockSize,apertureSize,k,BORDER_DEFAULT);
    normalize(dst,dst_norm,0,255,NORM_MINMAX,CV_32FC1,Mat());
    convertScaleAbs(dst_norm,dst_norm_scaled);
    for(int j=0; j<dst_norm.rows; j++)
    {
        for(int i=0; i<dst_norm.cols; i++)
        {
            if((int)dst_norm.at<float>(j,i)>thresh)
            {
                circle(dst_norm_scaled,Point(i,j),5,Scalar(0),2,8,0);
            }
        }
    }
    namedWindow(corners_window,CV_WINDOW_AUTOSIZE);
    imshow(corners_window,dst_norm_scaled);
}

