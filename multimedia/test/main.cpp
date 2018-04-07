#include <opencv2/core/core.hpp>
#include <opencv2/highgui/highgui.hpp>

using namespace cv;

int main()
{
    Mat image;
    image = imread("E:/multi/install_Week1/install_Week1/Desert.jpg",0);
    namedWindow("Display window",CV_WINDOW_AUTOSIZE);
    imshow("Display window",image);
    waitKey(0);
    return 0;
}
