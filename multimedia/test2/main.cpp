#include <opencv2/highgui/highgui.hpp>
#include <iostream>
#include <opencv2/opencv.hpp>
#include "opencv/cv.h"
#include "opencv/highgui.h"

using namespace cv;
using namespace std;

int main()
{
    /*Mat img(200,200,CV_8UC3,Scalar(0,0,200));
    namedWindow("MyWindow",CV_WINDOW_AUTOSIZE);
    imshow("MyWindow",img);
    waitKey(0);
    destroyAllWindows();
    return 0;*/

    /*Mat img = imread("E:/multi/Week2/Lena.JPG",CV_LOAD_IMAGE_GRAYSCALE);
    namedWindow("Lena",CV_WINDOW_AUTOSIZE);
    imshow("Lena",img);
    img = img/2;
    namedWindow("Lena/2",CV_WINDOW_AUTOSIZE);
    imshow("Lena/2",img);
    waitKey(0);
    destroyAllWindows();
    return 0;*/

    /*Mat img = imread("E:/multi/Week2/Lena.JPG",CV_LOAD_IMAGE_GRAYSCALE);
    namedWindow("Lena",CV_WINDOW_AUTOSIZE);
    imshow("Lena",img);
    img = ~img;
    namedWindow("Lena2",CV_WINDOW_AUTOSIZE);
    imshow("Lena2",img);
    waitKey(0);
    destroyWindow("Lena");
    destroyWindow("Lena2");
    return 0;*/


    //Histogram
    Mat img = imread("E:/multi/hw1/eq-no.png",CV_LOAD_IMAGE_GRAYSCALE);
    int histogram[256] = {0};
    for(int y=0; y<img.rows; y++)
        for(int x=0; x<img.cols; x++)
            histogram[(int)img.at<uchar>(y,x)]++;
    for(int i=0; i<256; i++)
        cout << "histogram[" << i << "]=" << histogram[i] << endl;
    int hist_w = 256;
    int hist_h = 400;
    int bin_w = cvRound((double)hist_w/256);
    Mat histimage(hist_h,hist_w,CV_8UC1,Scalar(255));
    int max = histogram[0];
    for(int i=1; i<256; i++){
        if(max<histogram[i]){
            max = histogram[i];
        }
    }
    for(int i=0; i<256; i++){
        histogram[i] = ((double)histogram[i]/max)*histimage.rows;
    }
    for(int i=0; i<256; i++){
        line(histimage,Point(bin_w*(i),hist_h),Point(bin_w*(i),hist_h-histogram[i]),Scalar(0));
    }
    namedWindow("Lena",CV_WINDOW_AUTOSIZE);
    imshow("Lena",img);
    namedWindow("Histogram",CV_WINDOW_AUTOSIZE);
    imshow("Histogram",histimage);
    waitKey(0);
    destroyAllWindows;
    return 0;

    //Histogram
    /*Mat gray = imread("E:/multi/Week2/Lena.JPG",0);
    int histSize = 256;
    float range[] = {0,255};
    const float *ranges[] = {range};
    Mat hist;
    calcHist(&gray,1,0,Mat(),hist,1,&histSize,ranges);
    int hist_w = 256;
    int hist_h = 400;
    int bin_w = cvRound((double)hist_w/histSize);
    Mat histimage(hist_h,hist_w,CV_8UC1,Scalar(255));
    normalize(hist,hist,0,histimage.rows,NORM_MINMAX,-1,Mat());
    for(int i=0; i<256; i++){
        line(histimage,Point(bin_w*(i),hist_h),Point(bin_w*(i),hist_h-cvRound(hist.at<float>(i))),Scalar(0));
    }
    namedWindow("Lena",1);
    imshow("Lena",gray);
    namedWindow("Result",1);
    imshow("Result",histimage);
    waitKey(0);
    destroyAllWindows;
    return 0;*/

    //Stretching
    /*Mat img = imread("E:/multi/Week3/image/lena2.png",CV_LOAD_IMAGE_GRAYSCALE);
    Mat st = cvCreateMat(img.rows,img.cols,CV_8UC1);
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
        if(sum_2 > MN*0.05)
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
            if(k<min) st.at<uchar>(y,x) = 0;
            else if(k>max) st.at<uchar>(y,x) = 255;
            else st.at<uchar>(y,x) = (k-min)*slope;
        }
    }
    for(y=0; y<img.rows; y++)
    {
        for(x=0; x<img.cols; x++)
        {
            hist2[(int)st.at<uchar>(y,x)]++;
        }
    }
    namedWindow("Origin Image",CV_WINDOW_AUTOSIZE);
    imshow("Origin Image",img);
    namedWindow("Image stretching",1);
    imshow("Image stretching",st);
    waitKey(0);
    destroyAllWindows;
    return 0;*/

    //Equalization
    /*Mat img = imread("E:/multi/Week3/image/lena2.png", CV_LOAD_IMAGE_GRAYSCALE);
    Mat eq = cvCreateMat(img.rows, img.cols, 0);

    namedWindow("Lena", CV_WINDOW_AUTOSIZE);
    imshow("Lena", img);

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
            eq.at<uchar>(y, x) = sum[img.at<uchar>(y, x)];
        }
    }

    namedWindow("Lena eq", 1);
    imshow("Lena eq", eq);

    waitKey(0);
    destroyAllWindows;

    return 0;*/

    //Equalization
    /*Mat gray = imread("E:/multi/Week3/image/Lena2.png", CV_LOAD_IMAGE_GRAYSCALE);
    Mat dst;

    equalizeHist(gray, dst);

    namedWindow("origin", 1);
    namedWindow("equalized", 1);

    imshow("origin", gray);
    imshow("equalized", dst);

    waitKey(0);
    destroyAllWindows();*/

    //Threshold
    /*Mat img = imread(""C:\\Users\\Cafemug\\Desktop\\Week3\\Lena.jpg"", CV_LOAD_IMAGE_GRAYSCALE);
     Mat thresh_img=cvCreateMat(img.rows,img.cols,CV_8UC1);

     int x,y,;
     int threshold_value =120;

     for(y=0;u<img.rows;y++){
        for(x=0;x<img.cols;x++){
            int k =(int)(img.at<uchar>(y,x));
            if(k<= threshold_value)
                thresh_img.at<uchar>(y,x)=0;
            else
                thresh_img.at<uchar>(y,x)=255;
        }
     }
     namedWindow("Origin Image", CV_WINDOW_AUTOSIZE);
     imshow("Origin Image", img);

     namedWindow("thresholded image", 1);
     imshow("thresholded image", thresh_img);

     waitKey(0);

     destroyAllWindows;*/

    //Threshold
    /*Mat gray = imread("E:/multi/Week3/image/Lena2.png",CV_LOAD_IMAGE_GRAYSCALE);
    Mat dst;
    threshold(gray,dst,60,255,THRESH_BINARY);
    namedWindow("origin",1);
    namedWindow("threshold",1);
    imshow("origin",gray);
    imshow("thresholded",dst);
    waitKey(0);
    destroyAllWindows();*/


}
