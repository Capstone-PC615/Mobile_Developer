# Mobile_Developer
Application of organic and non-organic waste classification.


> **Logo Aplikasi**


![logo_navbar](https://github.com/Capstone-PC615/Mobile_Developer/assets/87655146/016406c4-84cc-473c-8b60-16be5bc210ea)


# Ringkasan

This is an android application for garbage detection using TensorFlow Lite on mobile devices. I've used a Model which will classify images into two different categories namely organic and inorganic/non organic. We use transfer learning using EfficientNetV2 and EfficientNet-lite as a base to optimize our model training time and performance. The dataset used is a public dataset called Garbage Classification data from kaggle.



# Permission And Requirements

> **Permission**
- Camera
- READ_EXTERNAL_STORAGE
- Internet

> **Requirements**

- Android Studio
- Kotlin
- Gradle ^7.5
- JVM
- Min SDK 21
- Target SDK 33

# Dependencies
Listed bellow are required libraries for the android studio project

- Android core ktx
- Material design
- Tensorflow Lite
- navigation-fragment and ui
- com.google.firebase:firebase-auth-ktx'
- com.google.android.gms:play-services-auth

# How the scan feature works
The scan system consisted of 2 parts
- The trash classifier

> **The trash classifier**
   The trash that has been took is now getting analyzed by our tflite within the app, and then get the result of the object that has been classified

# How to Use

1. Open the application on your Android device.
2. Select the Camera menu
3. Tap the "Select Open Camera" or "Open Gallery" button to take a picture.
4. Once you have selected an image, press the "Filter" button to start the classification process.
5. Wait for the application to analyze the image using the built-in image classifier.
6. After the analysis is complete, the application will fetch the data
7. The application will display the results of the image in the form of Organic or Non-Organic.

# Contributors
Rahmat Hidayat
   


