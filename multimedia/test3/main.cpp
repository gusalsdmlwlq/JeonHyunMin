#include <opencv2/highgui/highgui.hpp>
#include <iostream>
#include <opencv2/opencv.hpp>
#include "opencv/cv.h"
#include "opencv/highgui.h"

using namespace cv;
using namespace std;

int main()
{
    //Threshold
    /*Mat img = imread("E:/multi/Week4/image/finger_print.png", CV_LOAD_IMAGE_GRAYSCALE);
     Mat thresh_img=cvCreateMat(img.rows,img.cols,CV_8UC1);

     int x,y;
     int threshold_value =120;

     for(y=0;y<img.rows;y++){
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
    /*Mat gray = imread("E:/multi/Week4/image/finger_print.png",CV_LOAD_IMAGE_GRAYSCALE);
    Mat dst;
    threshold(gray,dst,60,255,THRESH_BINARY);
    namedWindow("origin",1);
    namedWindow("thresholded",1);
    imshow("origin",gray);
    imshow("thresholded",dst);
    waitKey(0);
    destroyAllWindows();*/

    //OTSU
    /*Mat img = imread("E:/multi/Week4/image/finger_print.png",CV_LOAD_IMAGE_GRAYSCALE);
    Mat thresh_img(img.rows,img.cols,CV_8UC1);
    threshold(img,thresh_img,1,255,THRESH_OTSU);
    namedWindow("Origin Image",CV_WINDOW_AUTOSIZE);
    imshow("Origin Image",img);
    namedWindow("thresholded image",1);
    imshow("thresholded image",thresh_img);
    waitKey(0);
    destroyAllWindows;
    return 0;*/

    //Add Subtract
    /*Mat img1 = imread("E:/multi/Week4/image/lena.jpg",0);
    Mat img2 = imread("E:/multi/Week4/image/pattern.jpg",0);
    Mat img3 = imread("E:/multi/Week4/image/pattern2.png",0);
    Mat plus(img1.rows,img1.cols,CV_8UC1);
    Mat minus(img1.rows,img1.cols,CV_8UC1);
    float alpha = 0.8;
    float beta = 0.2;
    plus = (alpha * img1) + (beta * img2);
    minus = img2 - img3;
    namedWindow("Origin Image",1);
    imshow("Origin Image",img1);
    namedWindow("Pattern 1",1);
    imshow("Pattern 1",img2);
    namedWindow("Pattern 2",1);
    imshow("Pattern 2",img3);
    namedWindow("plus",1);
    imshow("plus",plus);
    namedWindow("minus",1);
    imshow("minus",minus);
    waitKey(0);
    destroyAllWindows();*/

    //blurring
    /*Mat img = imread("E:/multi/Week4/convolution_image/lena.png",CV_LOAD_IMAGE_COLOR);
    Mat st(img.rows,img.cols,CV_8UC3);
    float mask[] = {1.0/9.0,1.0/9.0,1.0/9.0,
                    1.0/9.0,1.0/9.0,1.0/9.0,
                    1.0/9.0,1.0/9.0,1.0/9.0};
    Vec3b color;
    int sum[3];
    int cnt;
    for(int y=1; y<img.rows-1; y++){
        for(int x=1; x<img.cols-1; x++){
            for(int c=0; c<3; c++) sum[c]=0;
            cnt=0;
            for(int h=y-1; h<=y+1; h++){
                for(int w=x-1; w<=x+1; w++){
                    color = img.at<Vec3b>(h,w);
                    sum[0] += color[0] * mask[cnt];
                    sum[1] += color[1] * mask[cnt];
                    sum[2] += color[2] * mask[cnt];
                    cnt++;
                }
            }
            for(int c=0; c<3; c++){
                if(sum[c]<0) sum[c]=0;
                else if(sum[c]>255) sum[c]=255;
                st.at<Vec3b>(y,x)[c]=sum[c];
            }
        }
    }
    namedWindow("Origin",CV_WINDOW_AUTOSIZE);
    imshow("Origin",img);
    namedWindow("Result",CV_WINDOW_AUTOSIZE);
    imshow("Result",st);
    waitKey(0);
    destroyAllWindows;*/

    //sharpening
    /*Mat img = imread("E:/multi/Week4/convolution_image/lena.png",CV_LOAD_IMAGE_COLOR);
    Mat st(img.rows,img.cols,CV_8UC3);
    int mask[] = {-1,-1,-1,
                    -1,9,-1,
                    -1,-1,-1};
    Vec3b color;
    int sum[3];
    int cnt;
    for(int y=1; y<img.rows-1; y++){
        for(int x=1; x<img.cols-1; x++){
            for(int c=0; c<3; c++) sum[c]=0;
            cnt=0;
            for(int h=y-1; h<=y+1; h++){
                for(int w=x-1; w<=x+1; w++){
                    color = img.at<Vec3b>(h,w);
                    sum[0] += color[0] * mask[cnt];
                    sum[1] += color[1] * mask[cnt];
                    sum[2] += color[2] * mask[cnt];
                    cnt++;
                }
            }
            for(int c=0; c<3; c++){
                if(sum[c]<0) sum[c]=0;
                else if(sum[c]>255) sum[c]=255;
                st.at<Vec3b>(y,x)[c]=sum[c];
            }
        }
    }
    namedWindow("Origin",CV_WINDOW_AUTOSIZE);
    imshow("Origin",img);
    namedWindow("Result",CV_WINDOW_AUTOSIZE);
    imshow("Result",st);
    waitKey(0);
    destroyAllWindows;*/

    //reduction average
    /*Mat img = imread("E:/multi/Week4/convolution_image/gaussian.png",CV_LOAD_IMAGE_GRAYSCALE);
    Mat st(img.rows,img.cols,CV_8UC1);
    float mask[] = {1.0/9.0,1.0/9.0,1.0/9.0,
                    1.0/9.0,1.0/9.0,1.0/9.0,
                    1.0/9.0,1.0/9.0,1.0/9.0};
    int sum,cnt;
    for(int y=1; y<img.rows-1; y++){
        for(int x=1; x<img.cols-1; x++){
            sum=0;
            cnt=0;
            for(int h=y-1; h<=y+1; h++){
                for(int w=x-1; w<=x+1; w++){
                    sum += img.at<uchar>(h,w) * mask[cnt];
                    cnt++;
                }
            }
            st.at<uchar>(y,x) = sum;
        }
    }
    namedWindow("Origin",CV_WINDOW_AUTOSIZE);
    imshow("Origin",img);
    namedWindow("Result",CV_WINDOW_AUTOSIZE);
    imshow("Result",st);
    waitKey(0);
    destroyAllWindows;*/

    //opencv
    /*Mat img = imread("E:/multi/Week4/convolution_image/gaussian.png",CV_LOAD_IMAGE_GRAYSCALE);
    Mat st(img.rows,img.cols,CV_8UC3);
    GaussianBlur(img,st,Size(3,3),NULL,NULL);
    namedWindow("Origin",CV_WINDOW_AUTOSIZE);
    imshow("Origin",img);
    namedWindow("Result",CV_WINDOW_AUTOSIZE);
    imshow("Result",st);
    waitKey(0);
    destroyAllWindows;*/

    //reduction median
    /*Mat img = imread("E:/multi/Week4/convolution_image/impulse.png",CV_LOAD_IMAGE_GRAYSCALE);
    Mat st(img.rows,img.cols,CV_8UC1);
    int sort_array[9];
    int cnt=0;
    for(int y=1; y<img.rows-1; y++){
        for(int x=1; x<img.cols-1; x++){
            int cnt=0;
            for(int h=y-1; h<=y+1; h++){
                for(int w=x-1; w<=x+1; w++){
                    sort_array[cnt] = img.at<uchar>(h,w);
                    cnt++;
                }
            }
            vector<int> myvector(sort_array,sort_array+9);
            sort(myvector.begin(),myvector.end());
            st.at<uchar>(y,x) = myvector.at(4);
        }
    }
    namedWindow("Origin",CV_WINDOW_AUTOSIZE);
    imshow("Origin",img);
    namedWindow("Result",CV_WINDOW_AUTOSIZE);
    imshow("Result",st);
    waitKey(0);
    destroyAllWindows;*/

    //opencv
    Mat img = imread("E:/multi/Week4/convolution_image/impulse.png",CV_LOAD_IMAGE_GRAYSCALE);
    Mat st(img.rows,img.cols,CV_8UC1);
    medianBlur(img,st,3);
    namedWindow("Origin",CV_WINDOW_AUTOSIZE);
    imshow("Origin",img);
    namedWindow("Result",CV_WINDOW_AUTOSIZE);
    imshow("Result",st);
    waitKey(0);
    destroyAllWindows;
}
