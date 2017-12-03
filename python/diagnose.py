import sys
import numpy as np
import cv2

filename = sys.argv[1]

rawImage = cv2.imread(filename)
sizeImage = cv2.resize(rawImage,(900,1600))
gray = cv2.cvtColor(sizeImage,cv2.COLOR_BGR2GRAY)
#threshImage = cv2.adaptiveThreshold(gray,255,cv2.ADAPTIVE_THRESH_MEAN_C,cv2.THRESH_BINARY,11,2)
ret,thresh = cv2.threshold(gray,170,255,0)
#ret,tcol = cv2.threshold(sizeImage,200,200,200)

white = cv2.bitwise_not(thresh)

cntImage,cntList,Hierarchy = cv2.findContours(white,cv2.RETR_LIST,cv2.CHAIN_APPROX_SIMPLE)
for cnt in cntList:
    if cv2.contourArea(cnt)>5000 and cv2.contourArea(cnt) < 30000:
        [x,y,w,h] = cv2.boundingRect(cnt)
        cv2.rectangle(sizeImage,(x,y),(x+w,y+h),(0,0,255),2)
        roi = gray[y:y+h,0:900]

threshImage = cv2.adaptiveThreshold(roi,255,cv2.ADAPTIVE_THRESH_GAUSSIAN_C,cv2.THRESH_BINARY,11,2)
ret,thresh = cv2.threshold(roi,30,255,0)

kernel = cv2.getStructuringElement(cv2.MORPH_RECT, (5, 5))
closed = cv2.erode(cv2.dilate(thresh, kernel, iterations=1), kernel, iterations=1)

#cv2.imshow('img',gray)
#cv2.imshow('img2',white)
#cv2.imshow('img3',sizeImage)
#cv2.imshow('img4',roi)
#cv2.imshow('img5',threshImage)
#cv2.imshow('img6',thresh)
cv2.imshow('img7',closed)


cntImage,cntList,Hierarchy = cv2.findContours(closed,cv2.RETR_LIST,cv2.CHAIN_APPROX_SIMPLE)
for cnt in cntList:
    if cv2.contourArea(cnt)>100:
        [x,y,w,h] = cv2.boundingRect(cnt)
        cv2.rectangle(roi,(x,y),(x+w,y+h),(0,0,255),2)
cv2.imshow('img4',roi)

while(True):
    key = cv2.waitKey(0)
    if key == 27:  # (escape to quit)
        sys.exit()

#def processImage(filename):
#    rawImage = cv2.imread(filename)
#
#    grayImage = cv2.cvtColor(rawImage,cv2.COLOR_BGR2GRAY)
#    threshImage = cv2.adaptiveThreshold(grayImage,255,cv2.ADAPTIVE_THRESH_MEAN_C,cv2.THRESH_BINARY,11,20)
#
#    cntImage,cntList,Hierarchy = cv2.findContours(threshImage,cv2.RETR_LIST,cv2.CHAIN_APPROX_SIMPLE)
#
#    return threshImage,cntList
#
#def verifyChrs(ocrCode,rawImage,threshImage,cntList,hmin,hmax,wmin,wmax):
#
#    samples =  np.empty((0,100))
#    responses = []
#
#    for cnt in cntList:
#        if cv2.contourArea(cnt)>10:
#            [x,y,w,h] = cv2.boundingRect(cnt)
#
#            if  h>hmin and h<hmax and w>wmin and w<wmax:
#                cv2.rectangle(rawImage,(x,y),(x+w,y+h),(0,0,255),2)
#                roi = threshImage[y:y+h,x:x+w]
#                roismall = cv2.resize(roi,(10,10))
#                cv2.imshow('norm',rawImage)
#                key = cv2.waitKey(0)
#
#                if key == 27:  # (escape to quit)
#                    sys.exit()
#                if key == 10:
#                    responses.append(ocrCode)
#                    sample = roismall.reshape((1,100))
#                    samples = np.append(samples,sample,0)
#
#    responses = np.array(responses,np.float32)
#    responses = responses.reshape((responses.size,1))
#    print("training complete")
#    print(len(responses))
#
#
#    np.savetxt(str(ocrCode)+'samples.data',samples)
#    np.savetxt(str(ocrCode)+'responses.data',responses)
#
#    genresp = open('generalresponses.data','a')
#    newresp = open(str(ocrCode)+'responses.data','r')
#    for resp in newresp:
#        genresp.write(resp)
#    genresp.close()
#    newresp.close()
#
#    gensamp = open('generalsamples.data','a')
#    newsamp = open(str(ocrCode)+'samples.data','r')
#    for samp in newsamp:
#        gensamp.write(samp)
#    gensamp.close()
#    newsamp.close()
#    return 0
