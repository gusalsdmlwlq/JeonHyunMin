#include <opencv2/opencv2/highgui/highgui.hpp

using namespace cv;

int main()
{
    Mat img(200,200,CV_8UC3,Scalar(0,0,200));
    namedWindow("MyWindow",CV_WINDOW_AUTOSIZE);
    imshow("MyWindow",img);
    waitKey(0);
    destroyWindow();
    return 0;


}
