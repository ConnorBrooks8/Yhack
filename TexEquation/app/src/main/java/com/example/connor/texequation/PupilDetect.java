package com.example.connor.texequation;

import android.os.Environment;
import android.util.Log;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.opencv.core.Core.bitwise_not;
import static org.opencv.imgcodecs.Imgcodecs.imread;
import static org.opencv.imgproc.Imgproc.CHAIN_APPROX_SIMPLE;
import static org.opencv.imgproc.Imgproc.COLOR_BGR2GRAY;
import static org.opencv.imgproc.Imgproc.MORPH_RECT;
import static org.opencv.imgproc.Imgproc.RETR_LIST;
import static org.opencv.imgproc.Imgproc.boundingRect;
import static org.opencv.imgproc.Imgproc.contourArea;
import static org.opencv.imgproc.Imgproc.cvtColor;
import static org.opencv.imgproc.Imgproc.dilate;
import static org.opencv.imgproc.Imgproc.erode;
import static org.opencv.imgproc.Imgproc.findContours;
import static org.opencv.imgproc.Imgproc.getStructuringElement;
import static org.opencv.imgproc.Imgproc.rectangle;
import static org.opencv.imgproc.Imgproc.threshold;
import static org.opencv.imgproc.Imgproc.warpAffine;

/**
 * Created by connor on 12/2/17.
 */

public class PupilDetect {

    String Radius(Mat Image1) {
        //File root = Environment.getExternalStorageDirectory();
        //Mat Image1 = imread(filename.getAbsolutePath());

        Mat resizeImage = new Mat(1600, 900, Image1.type());
        int interpolation = Imgproc.INTER_CUBIC;
        Imgproc.resize(Image1, resizeImage, resizeImage.size(), 0, 0, interpolation);


        Mat grayImage = new Mat(1600, 900, Image1.type());
        cvtColor(resizeImage, grayImage, COLOR_BGR2GRAY);

        Mat threshImage = new Mat(1600, 900, Image1.type());
        threshold(grayImage, threshImage, 170, 255, 0);

        Mat notImage = new Mat(1600, 900, Image1.type());
        bitwise_not(threshImage, notImage);


        Mat heir = new Mat();
        List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
        findContours(notImage, contours, heir, RETR_LIST, CHAIN_APPROX_SIMPLE, new Point(0,0));

        //Double hello = new Double(contours.get(1).get(1,0)[0]);
        //int ans = hello.intValue();
        //contours.get(0).size()}}
        Rect boundingRect = new Rect();
        String test1 = null;
        int maxx = 0;
        int minx = 0;
        int maxy = 0;
        int miny = 0;

        for (int cnt = 0; cnt < contours.size(); cnt++) {
            MatOfPoint contour = contours.get(cnt);
            return contour.get(0,0).toString();}/*
            double contourarea = Imgproc.contourArea(contour);
            if (contourarea > 5000 && contourarea < 30000) {



                for (int x = 0; cnt < contour.width(); x++) {
                    Double lend = new Double(contours.get(cnt).get(0, 0).length);
                    //MatOfPoint testt = contours.get(cnt)
                    int leni = lend.intValue();
                    for (int i = 0; i < leni; i++) {
                        Double dubx = new Double(contours.get(cnt).get(i, 0)[0]);
                        Double duby = new Double(contours.get(cnt).get(i, 0)[1]);
                        int inx = dubx.intValue();
                        int iny = duby.intValue();
                        if (inx > maxx) {
                            maxx = inx;
                        }
                        if (inx < minx) {
                            minx = inx;
                        }
                        if (iny > maxy) {
                            maxy = iny;
                        }
                        if (iny < maxy) {
                            maxy = iny;
                        }
                        Rect BndRect = new Rect(minx, miny, (maxx - minx), (maxy - miny));
                    }
                }
                test1 = new String(String.valueOf(minx) + String.valueOf(miny) + String.valueOf(maxx - minx) + String.valueOf(maxy - miny));
            }
            //Rect BndRect = boundingRect(MatOfPoint.);
            /*
                        boundingRect = BndRect;
                        test2 = test1;
                    }
                }

                //Rect BndRct = boundingRect(contour);
                //Scalar color = new Scalar(255, 0, 0);
                //rectangle(Image1, BndRct.tl(), BndRct.br(), color);
                //Mat Image2 = Image1.submat(BndRct);
                //BoundedRect = BndRct;
                }
            }


            //Rect BoundedRect = new Rect();
            //for (MatOfPoint cnt : contours) {
            //Log.d("1","2");
            //if (contourArea(cnt) > 5000 && contourArea(cnt) < 30000) {
            //Rect BndRct = boundingRect(cnt);
            //Scalar color = new Scalar(255, 0, 0);
            //rectangle(Image1, BndRct.tl(), BndRct.br(), color);
            //Mat Image2 = Image1.submat(BndRct);
            //BoundedRect = BndRct;
            //}
            //}



        Mat threshImage2 = new Mat(boundingRect.height, 900, Image1.type());
        threshold(grayImage, threshImage2, 170, 255, 0);

        Size ksize = new Size(5, 5);
        Mat kernal = getStructuringElement(MORPH_RECT, ksize);
        Mat eroded = new Mat(boundingRect.height, 900, Image1.type());
        Mat dilated = new Mat(boundingRect.height, 900, Image1.type());
        dilate(threshImage2, dilated, kernal);
        erode(dilated, eroded, kernal);

        List<MatOfPoint> contours2 = new ArrayList<MatOfPoint>();
        Mat heir2 = new Mat();
        findContours(eroded, contours2, heir2, RETR_LIST, CHAIN_APPROX_SIMPLE);

        Rect BoundedRect2 = new Rect();
        for (MatOfPoint cnt : contours2) {
            if (contourArea(cnt) > 5000 && contourArea(cnt) < 30000) {
                Rect BndRct = boundingRect(cnt);
                Scalar color = new Scalar(255, 0, 0);
                rectangle(Image1, BndRct.tl(), BndRct.br(), color);
                Mat Image2 = Image1.submat(BndRct);
                BoundedRect2 = BndRct;
            }
        }
        return (BoundedRect2.width/2);
    }
}

     */return test1;}}