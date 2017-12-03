package com.example.connor.texequation;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.connor.texequation.PupilDetect;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.*;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import static org.opencv.imgproc.Imgproc.initUndistortRectifyMap;
import static org.opencv.imgproc.Imgproc.rectangle;
import static org.opencv.imgproc.Imgproc.threshold;
import android.content.Intent; import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity; import android.os.Bundle;
import android.view.View; import android.widget.Button;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {
    private static final int CAMERA_REQUEST = 1888;

    static {
        try

        {
            //System.loadLibrary("MyLib");
            System.loadLibrary("Opencv_java3");
            Mat grwb = new Mat();
        } catch (UnsatisfiedLinkError e) {
            System.err.println("unable to load the opencv   library" + e.toString());
        }
    }

    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS: {
                    Log.i("OpenCV", "OpenCV loaded successfully");
                    Mat imageMat = new Mat();
                }
                break;
                default: {
                    super.onManagerConnected(status);
                }
                break;
            }
        }
    };

    private ImageView imageView;
    private TextView textView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.imageView = (ImageView) this.findViewById(R.id.imageView1);
        Button photoButton = (Button) this.findViewById(R.id.button1);
        photoButton.setOnClickListener(new View.OnClickListener() {

            //PupilDetect test1 = new PupilDetect();

            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);
            Bitmap photo2 = photo.copy(photo.getConfig(), false);
            MyMatrixShit(photo2);
        }
    }

    public int MyMatrixShit(Bitmap rawPhoto) {
        try

        {
            //System.loadLibrary("MyLib");
            System.loadLibrary("Opencv_java3");
            Mat grwb = new Mat();
        } catch (UnsatisfiedLinkError e) {
            System.err.println("unable to load the opencv   library" + e.toString());
        }
        //String test = new String("she sells sea shells by the sea shore");
        Mat tmp = new Mat(rawPhoto.getWidth(), rawPhoto.getHeight(), CvType.CV_8UC4);
        Utils.bitmapToMat(rawPhoto,tmp);
        PupilDetect class1 = new PupilDetect();
        //int ans = class1.Radius(tmp);
        String ans = class1.Radius(tmp);
        //Log.d("YESSS",String.valueOf(ans));
        TextView textView = (TextView) findViewById(R.id.textView1);
        //textView.setText(String.valueOf(ans));
        textView.setText(ans);
        return 1;
        //return ans;
    }

    public void onResume() {
        super.onResume();
        if (!OpenCVLoader.initDebug()) {
            Log.d("OpenCV", "Internal OpenCV library not found. Using OpenCV Manager for initialization");
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_0_0, this, mLoaderCallback);
        } else {
            Log.d("OpenCV", "OpenCV library found inside package. Using it!");
            mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        }


    }
}
